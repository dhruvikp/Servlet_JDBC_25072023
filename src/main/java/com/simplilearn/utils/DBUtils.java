package com.simplilearn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private Connection connection;

	private String dbUrl = "jdbc:mysql://localhost:3306/mphasis_25072023";
	private String username = "root";
	private String password = "root";

	public DBUtils() {
		try {
			// STEP 1: Register Driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 2: Create connection object
			this.connection = DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
