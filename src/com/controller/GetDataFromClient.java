package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Date;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class GetDataFromClient
 */
@WebServlet("/GetDataFromClient")
public class GetDataFromClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sql1 = "insert into AnchorNode (AnchorNode_Mac,AnchorNode_Name,AnchorNode_X,AnchorNode_Y) values (?,?,?,?)";
	private String sql2 = "insert into iBeaconNode (iBeaconNode_Mac,iBeaconNode_Name) values (?,?)";
	private String sql3 ="insert into ScanRecord (ScanTimeStamp,AnchorNode_Mac,iBeaconNode_Mac,iBeacon_Rssi,AnchorNode_Location) values (?,?,?,?,?)";

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
				if(!hasMac("AnchorNode",anchornode.getAnchor_mac())){
					db.pst.setString(1, anchornode.getAnchor_mac());
					db.pst.setString(2, anchornode.getAnchor_name());
					db.pst.setFloat(3, anchornode.getAnchor_x());
					db.pst.setFloat(4, anchornode.getAnchor_y());
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
		    	  if(!hasMac("iBeaconNode",beaconmac)){
						db.pst.setString(1, beaconmac);
						db.pst.setString(2, anchornode.getScanresult().get(beaconmac).getName());
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
					System.out.println(date);
					db.pst.setString(2, anchornode.getAnchor_mac());
					db.pst.setString(3, beaconmac);
					db.pst.setFloat(4, anchornode.getScanresult().get(beaconmac).getRSSI());
					db.pst.setString(5,"("+anchornode.getAnchor_x()+","+anchornode.getAnchor_y()+")");
					db.pst.executeUpdate();
					System.out.println("存储数据成功");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	      }
	  }
	
	 public static boolean hasMac(String tablename,String Mac) throws SQLException {
		 String columnname = tablename+"_Mac";   
		 String sql = "select * from "+tablename+" where "+columnname+"='" + Mac + "'";
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
