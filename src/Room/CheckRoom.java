package Room;

import java.sql.SQLException;

import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class CheckRoom {
	
	public static void main(String[] args) {
		
		
		sqlRetrieve test = new sqlRetrieve("SELECT * from bruker WHERE brukernavn = \"KimHD\"");
		sqlExecute test2 = new sqlExecute();
		
		test2.execute("INSERT INTO bruker (brukerid,fornavn,etternavn,tlfnr, brukernavn, passord, stilling, admin) VALUES (20, 'hei', 'heihei', 94370528, 'heibaa', 'abcde', 'admin', 1)");
		
		for (int i = 0; i < test.getQuery().length; i++) {
			for (int j = 0; j < test.getQuery()[0].length; j++) {
				System.out.println(test.getQuery()[i][j]);
			}
		}
		
	}
	
}