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
import com.bean.iBeacon;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetBeaconInfo
 */
@WebServlet("/GetBeaconInfo")
public class GetBeaconInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<iBeacon> iBeacons = new ArrayList<iBeacon>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBeaconInfo() {
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
		 String data =gson.toJson(getBeaconInfo());
	     response.getWriter().print(data);
	     System.out.println("data :"+data.toString());
	     System.out.println("已返回iBeacon数据至前台");
	     iBeacons.clear();//记得清空数据
	}
	public ArrayList<iBeacon> getBeaconInfo(){
		iBeacon ibeacon;
		String sql = "select iBeaconNode_Name,iBeaconNode_Mac from iBeaconNode";
		ResultSet rs =null;
	    dbHelper db;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
			while (rs.next()){
				ibeacon = new iBeacon();
				ibeacon.setName(rs.getString(1));
				ibeacon.setAddress(rs.getString(2));
				iBeacons.add(ibeacon);
			}
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iBeacons;
	}

}
