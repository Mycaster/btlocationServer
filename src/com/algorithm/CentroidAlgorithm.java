package com.algorithm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.ScanRecord;
import com.controller.dbHelper;

public class CentroidAlgorithm {
	public String cacluateLocation(String Mac){
		double result;
		//String sql = "select irssi  from ScanRecord where iaddress= '"+Mac+"'&& order by ScanTimeStamp limit 3";
		//取前三项数据进行平均
		String sql = "select scanrecord.irssi,tx_power from ibeacon inner join scanrecord where iBeacon.iaddress='"+Mac+"' order by scanrecord.ScanTimeStamp DESC limit 3";
		ResultSet rs =null;
	    dbHelper db;
	    int rssi =0,tx_power=0;
		try {
			db = new dbHelper(sql);
			rs = db.pst.executeQuery(sql);
			while (rs.next()){
				rssi += rs.getInt(1);
				tx_power += rs.getInt(2);
			}
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = new RssiToDis(tx_power/3, rssi/3).calculateDistance();
		return String.format("%.2f", result);
	}

}
