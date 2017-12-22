package com.test.iot.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCon {
 private static Connection conn;
 public static Connection getCon() {
	 if(DBCon.conn == null) {
		 Context initContext;
		 try {
			 initContext = new InitialContext();
			 Context envContext = (Context)initContext.lookup("java:/comp/env");
			 DataSource ds =(DataSource)envContext.lookup("jdbc/IOT");
			 DBCon.conn = ds.getConnection();
		 } catch(NamingException | SQLException e) {
			 e.printStackTrace();
			
		 }
	 }
	 return DBCon.conn;
 }
 
}
