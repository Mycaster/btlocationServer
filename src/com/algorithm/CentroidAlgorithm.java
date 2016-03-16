package com.algorithm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.ScanRecord;
import com.controller.dbHelper;

public class CentroidAlgorithm {
	public String cacluateLocation(String Mac){
		String result = "(1,1)";
		//String sql = "select iBeacon_Rssi from ScanRecord where iBeaconNode_Mac= '"+Mac+"'&&order by ScanTimeStamp limit 3";
//		ResultSet rs =null;
//	    dbHelper db;
//		try {
//			db = new dbHelper(sql);
//			rs = db.pst.executeQuery(sql);
//			while (rs.next()){
//				rs.getFloat(1);
//			}
//			db.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return result;
	}

}
