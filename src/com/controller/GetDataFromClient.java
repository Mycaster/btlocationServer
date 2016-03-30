package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AnchorNode;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetDataFromClient
 */
@WebServlet("/GetDataFromClient")
public class GetDataFromClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sql1 = "insert into AnchorNode(aaddress, aname, ax, ay) values (?,?,?,?)";
	private String sql2 = "insert into iBeacon (iaddress, iname,iuuid, imajor, iminor, irssi,tx_power) values (?,?,?,?,?,?,?)";
	private String sql3 ="insert into ScanRecord (ScanTimeStamp, aaddr, iaddr, irssi, ax,ay) values (?,?,?,?,?,?)";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        // 读取请求内容
        //BufferedReader br = request.getReader();
        BufferedReader br =new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        String info = URLDecoder.decode(sb.toString(), "UTF-8");
        System.out.println("收到数据"+info);
        saveData(info);
	}
	
	public void saveData(String data){
		  Gson gson = new Gson();
	      AnchorNode anchornode = gson.fromJson(data, AnchorNode.class);
	      dbHelper db;
	      //插入表AnchorNode:
		  try {
				db = new dbHelper(sql1);
				if(!hasMac("AnchorNode",anchornode.getA_address(),"aaddress")){
					db.pst.setString(1, anchornode.getA_address());
					db.pst.setString(2, anchornode.getA_name());
					db.pst.setFloat(3, anchornode.getA_x());
					db.pst.setFloat(4, anchornode.getA_y());
					db.pst.executeUpdate();
					System.out.println("表1更新成功");	
				}
		  } catch (IOException e1) {
				e1.printStackTrace();
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	      //插入表iBeaconNode:
		  try {
			  db = new dbHelper(sql2);
		      for(String beaconmac:anchornode.getScanresult().keySet()){
		    	  if(!hasMac("iBeacon",beaconmac,"iaddress")){
						db.pst.setString(1, beaconmac);
						db.pst.setString(2, anchornode.getScanresult().get(beaconmac).getName());
						db.pst.setString(3, anchornode.getScanresult().get(beaconmac).getUuid());
						db.pst.setInt(4, anchornode.getScanresult().get(beaconmac).getMajor());
						db.pst.setInt(5, anchornode.getScanresult().get(beaconmac).getMinor());
						db.pst.setInt(6, anchornode.getScanresult().get(beaconmac).getRssi());
						db.pst.setInt(7, anchornode.getScanresult().get(beaconmac).getTx_power());
						db.pst.executeUpdate();
		    	  }
		      }
			  System.out.println("表2更新成功");
		  } catch (IOException e1) {
				e1.printStackTrace();
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
		  
	      //插入表scanRecord:
	      for(String beaconmac:anchornode.getScanresult().keySet()){
	    	  try {
					db =  new dbHelper(sql3);
					//db.pst.setDate(1, new Date(new java.util.Date().getTime()));
					Timestamp date = Timestamp.valueOf(getCurrentTime());
					db.pst.setTimestamp(1, date);
					//System.out.println(date);
					db.pst.setString(2, anchornode.getA_address());
					db.pst.setString(3, beaconmac);
					db.pst.setInt(4, anchornode.getScanresult().get(beaconmac).getRssi());
					db.pst.setFloat(5, anchornode.getA_x());
					db.pst.setFloat(6, anchornode.getA_y());
					db.pst.executeUpdate();
					System.out.println("存储数据成功");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	      }
	  }
	
	 public static boolean hasMac(String tablename,String Mac,String column) throws SQLException {
		String sql = "select * from "+tablename+" where "+column+"='" + Mac + "'";
	    ResultSet rs =null;
	    System.out.println(sql);
	    dbHelper db;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    if (rs.next()){
	      rs.close();
	      System.out.println("包含此Mac");
	      return true;
	    }
	    return false;
	 }
	/*
	 * 获取系统当前时间
	 */
	public static String getCurrentTime(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new java.util.Date());
	}
}
