package User;

import mysql.encryption;
import mysql.sqlExecute;

public class CreateUser {
	
	static String fornavn;
	static String etternavn;
	static int TlfNr;
	static String stilling;
	static String passord;
	static int admin;
	static String brukernavn;

	
	CreateUser(String fornavn, String etternavn, int TlfNr,
			String brukernavn, String passord, String stilling, int admin){
		
		sqlExecute create = new sqlExecute();
		
		create.execute("INSERT INTO bruker (fornavn,etternavn,tlfnr,"
				+ " brukernavn, passord, stilling, admin) VALUES ('" + fornavn + "','" + etternavn + "','" 
	            + TlfNr + "','" + brukernavn + "','" + encryption.md5(passord) + "','"  + stilling +
	            "','" + admin + "')");
		
	}
    
}