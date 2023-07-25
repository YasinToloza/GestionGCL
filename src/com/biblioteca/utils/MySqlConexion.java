package com.biblioteca.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {
	
	public static Connection getConexion() {
		Connection cn=null;
		try {
			String url,login,clave;
			Class.forName("com.mysql.cj.jdbc.Driver");
			url="jdbc:mysql://localhost:3306/bd_proyecto_lp?serverTimezone=UTC";//serverTimezone
			login="root";
			clave="mysql";
			cn=DriverManager.getConnection(url,login,clave);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cn;
	}
	
}
