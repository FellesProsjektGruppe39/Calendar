 package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import mysql.sqlExecute;

import com.mysql.jdbc.PreparedStatement;

public class CreateUser {
	
	static int brukerid;
	static String fornavn;
	static String etternavn;
	static int TlfNr;
	static String stilling;
	static String passord;
	static int admin;
	static String brukernavn;
	
	CreateUser(int brukerid, String fornavn, String etternavn, int TlfNr,
			String brukernavn, String passord, String stilling, int admin){
		
		sqlExecute create = new sqlExecute();
		
		create.execute("INSERT INTO bruker (brukerid,fornavn,etternavn,tlfnr,"
				+ " brukernavn, passord, stilling, admin) VALUES ('" + brukerid +
				"','" + fornavn + "','" + etternavn + "','" 
	            + TlfNr + "','" + brukernavn + "','" + passord + "','"  + stilling +
	            "','" + admin + "')");
		
	}
    
}