package Room;

import java.sql.SQLException;

import mysql.sqlRetrieve;

public class CheckRoom {
	
	public static void main(String[] args) {
		sqlRetrieve test = new sqlRetrieve("SELECT * FROM rom WHERE romnavn = 'D116';");
		
		System.out.println(test.getQuery()[0][2]);
	}
	
}
