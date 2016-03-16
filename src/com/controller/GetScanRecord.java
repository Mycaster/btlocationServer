package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ScanRecord;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetScanRecord
 */
@WebServlet("/GetScanRecord")
public class GetScanRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<ScanRecord> recordlist = new ArrayList<ScanRecord>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScanRecord() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Gson gson = new Gson();
		 String data =gson.toJson(getScanRecord());
		 System.out.println(data);
	     response.getWriter().print(data);
	     System.out.println("已返回数据至前台");
	     recordlist.clear();//记得清空数据
	}
	public ArrayList<ScanRecord> getScanRecord(){
		ScanRecord record;
		String sql = "select ScanTimeStamp,AnchorNode_Mac,iBeaconNode_Mac,iBeacon_Rssi,AnchorNode_Location from ScanRecord";
		ResultSet rs =null;
	    dbHelper db;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
			while (rs.next()){
				record = new ScanRecord();
				record.setTime(rs.getTimestamp(1).toString());
				record.setAnchornode_mac(rs.getString(2));
				record.setIbeacon_mac(rs.getString(3));
				record.setRssi(rs.getFloat(4));
				record.setLocation(rs.getString(5));
				recordlist.add(record);
			}
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordlist;
	}

}
