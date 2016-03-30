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
		 BufferedReader br = request.getReader();
         String line = null;
         StringBuilder sb = new StringBuilder();
         while((line = br.readLine())!=null){
            sb.append(line);
         }
         String aaddr = sb.toString();
		 Gson gson = new Gson();
		 String data =gson.toJson(getScanRecord(aaddr));
	     response.getWriter().print(data);
	     System.out.println("已返回数据至前台");
	     recordlist.clear();//记得清空数据
	}
	public ArrayList<ScanRecord> getScanRecord(String aaddr){
		ScanRecord record;
		//根据前台的Mac地址查询扫描数据
		String sql = "select ScanTimeStamp,iaddr,irssi from ScanRecord  where aaddr='"+aaddr+"' order by  ScanTimeStamp DESC limit 30";
		ResultSet rs =null;
	    dbHelper db;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
			while (rs.next()){
				record = new ScanRecord();
				String time = rs.getTimestamp(1).toString();
				record.setTime(time.substring(5,time.length()-2));
				record.setI_addr(rs.getString(2));
				record.setRssi(rs.getFloat(3));
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
