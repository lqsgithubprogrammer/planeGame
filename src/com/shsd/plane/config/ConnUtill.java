package com.shsd.plane.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnUtill {

	public static Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Connection conn;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/myplanegame",
						"root", "wdlqs16279");
		return conn;
	}
	
	// while(rs.next()){
	// System.out.println(rs.getInt("usersid"));
	// System.out.println(rs.getString("username"));
	// System.out.println(rs.getString("plane"));
	// System.out.println(rs.getString("scorebest"));
	// System.out.println(rs.getString("money"));
	// }
	// rs.close();
	// st.close();
	// conn.close();
	
}
