package com.controller;

import java.io.BufferedReader;
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
		 BufferedReader br = request.getReader();
         String line = null;
         StringBuilder sb = new StringBuilder();
         while((line = br.readLine())!=null){
            sb.append(line);
         }
         String sql = sb.toString();
         System.out.println("查询语句为："+sql);
		 Gson gson = new Gson();
		 String ibeaconinfo =gson.toJson(getBeaconInfo(sql));
	     response.getWriter().print(ibeaconinfo);
	     System.out.println("查询结果为"+ibeaconinfo);
	     iBeacons.clear();
	}
	public ArrayList<iBeacon> getBeaconInfo(String sql){
		iBeacon ibeacon;
		ResultSet rs =null;
	    dbHelper db;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
			while (rs.next()){
				ibeacon = new iBeacon();
				ibeacon.setAddress(rs.getString(1));
				ibeacon.setName(rs.getString(2));
				ibeacon.setUuid(rs.getString(3));
				ibeacon.setMajor(rs.getInt(4));
				ibeacon.setMinor(rs.getInt(5));
				ibeacon.setTx_power(rs.getInt(6));
				iBeacons.add(ibeacon);
			}	
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iBeacons;
	}

}
