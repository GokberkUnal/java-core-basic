package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "199311";

	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }

}
