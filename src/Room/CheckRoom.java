package Room;

import java.sql.SQLException;

import mysql.sqlRetrieve;

public class CheckRoom {
	
	public static void main(String[] args) {
		sqlRetrieve test = new sqlRetrieve("SELECT * from bruker WHERE brukernavn = \"KimHD\"");

		for (int i = 0; i < test.getQuery().length; i++) {
			for (int j = 0; j < test.getQuery()[0].length; j++) {
				System.out.println(test.getQuery()[i][j]);
			}
		}
	}
	
}
