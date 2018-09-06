package com.tellez.facturacion.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Bd {
	
	
    private static final String url = "jdbc:postgresql://localhost/facturacion";
    private static final String user = "mati";
    private static final String password = "rivasciclon";
 

    public static Connection connect() throws SQLException {
		try {
	    	Class.forName("org.postgresql.Driver");
	        return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    

}
