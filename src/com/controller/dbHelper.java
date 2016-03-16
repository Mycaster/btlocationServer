package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbHelper
{
  private static String JDBC_CLASS = "com.mysql.jdbc.Driver";
  private static String DataBaseAddress = "jdbc:mysql://localhost:3306/scandatabase?useUnicode=true&characterEncoding=UTF8";
  private static String USER_NAME = "root";
  private static String USER_PASSWORD = "root";
  private Connection connection = null;
  public  PreparedStatement pst = null;
  
  public dbHelper(String sql) throws IOException
  {
//	   //读取配置文件中的数据库参数
//	    Properties pro = new Properties();
//	    InputStream in = new BufferedInputStream(new FileInputStream("config.properties"));
//	    pro.load(in);
//	    String ip = pro.getProperty("ip");
//	    String port = pro.getProperty("port");
//	    String databasename = pro.getProperty("databasename");
//	    DataBaseAddress = "jdbc:mysql://" + ip + ":" + port + "/" + databasename + "?useUnicode=true&characterEncoding=UTF8";
//	    USER_NAME = pro.getProperty("user");
//	    USER_PASSWORD = pro.getProperty("password");
	    //连接数据库
	    try
	    {
	    	Class.forName(JDBC_CLASS);
	    	System.out.println("Success loading Mysql Driver!");
	    	//获取连接
    		connection = DriverManager.getConnection(DataBaseAddress, USER_NAME, USER_PASSWORD);
    	      //准备执行语句
    	     pst = connection.prepareStatement(sql);//准备执行语句
        } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (Exception e) {
	      System.out.print("Error loading Mysql Driver!");
	      e.printStackTrace();
	    }
  }
  

  
  public void close() {  
      try {  
          this.connection.close();  
          this.pst.close();  
      } catch (SQLException e) {  
          e.printStackTrace();  
      }  
  }  

}