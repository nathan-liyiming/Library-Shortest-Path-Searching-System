package util;

import java.sql.*;

public class MySQLConnection {

	public static Connection connection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "123456");
		} catch (SQLException el) {
			el.printStackTrace();
		}
		return con;
	}
}
