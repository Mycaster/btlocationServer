package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algorithm.CentroidAlgorithm;
import com.algorithm.RssiToDis;
import com.bean.iBeacon;
import com.google.gson.Gson;

/**
 * Servlet implementation class Locatedservlet
 */
@WebServlet("/Locatedservlet")
public class Locatedservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    ArrayList<iBeacon> resultlist = new ArrayList<iBeacon>();
	    iBeacon beacon;
        BufferedReader br = request.getReader();
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        String[] iBeacons=sb.toString().split("--");
        System.out.println(iBeacons.length);
        CentroidAlgorithm  centroidAl= new CentroidAlgorithm();
        for (int i = 0; i < iBeacons.length; i+=2){
        	beacon = new iBeacon();
            beacon.setName(iBeacons[i]);
            beacon.setAddress(iBeacons[i+1]);
            beacon.setIlocation(centroidAl.cacluateLocation(iBeacons[i+1]));
            System.out.println(iBeacons[i+1]+"  ----  "+beacon.getLocation());
            resultlist.add(beacon);
        }
        Gson gsonresult = new Gson();
        String results = gsonresult.toJson(resultlist);
        System.out.println(results);
        response.getWriter().print(results);
//        System.out.println("已返回定位结果至前台");
        //resultlist.clear();
	}

}
