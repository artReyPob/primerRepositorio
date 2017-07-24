package com.consist.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBUtil {
	private static final Logger log = Logger.getLogger(DBUtil.class) ;
	

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el objeto Connection:",e);
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el objeto Statement:",e);
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.error("Error al cerrar el objeto ResultSet:",e);
			}
		}
	}
}