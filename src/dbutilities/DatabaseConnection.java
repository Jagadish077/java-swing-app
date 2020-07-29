package dbutilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	Connection con;
	
	public Connection getConnection(){
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:/home/jagadish/Documents/mobileApp.db");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
