package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection con;

	public void getDBConnection(String url, String userName, String password) throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getDBConnection() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/praticeapi", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDBConnection() throws SQLException {
		con.close();
	}

	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet result = null;
		try {
			result = con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int excecuteNonSelectQuery(String query) throws SQLException {

		int result = 0;
		try {
			result = con.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
