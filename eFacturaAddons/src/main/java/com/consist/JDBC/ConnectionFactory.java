package com.consist.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.consist.ConstantesMensajes.MSG_JDBC;
import com.consist.EmpresaEnrolada.MainClass;

public class ConnectionFactory {
	//static reference to itself
	private static ConnectionFactory instance = new ConnectionFactory();
	private static final Logger log = Logger.getLogger(MainClass.class) ;
	
	public static final String URL = "jdbc:mysql://localhost/jdbcdb";
	public static final String USER = "YOUR_DATABASE_USERNAME";
	public static final String PASSWORD = " YOUR_DATABASE_PASSWORD";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	
	public static final String PROPS_SQL_URL = "SQL_URL";
	public static final String PROPS_SQL_USER = "SQL_USER";
	public static final String PROPS_SQL_PASS = "SQL_PASSWORD";
	
	//private constructor
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			log.error(MSG_JDBC.ERROR_CONEXION,e);
			System.exit(1);
		}
		return connection;
	}	
	
	private Connection createConnection(Properties xProps) {
		System.out.println(PROPS_SQL_URL+"="+xProps.getProperty(PROPS_SQL_URL)+"  "+xProps.getProperty(PROPS_SQL_USER)+"   "+xProps.getProperty(PROPS_SQL_PASS));
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(xProps.getProperty(PROPS_SQL_URL), xProps.getProperty(PROPS_SQL_USER), xProps.getProperty(PROPS_SQL_PASS));
		} catch (SQLException e) {
			log.error(MSG_JDBC.ERROR_CONEXION,e);
			System.exit(1);
		}
		return connection;
	}	
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
	
	public static Connection getConnection(Properties xProps) {
		return instance.createConnection(xProps);
	}
}
