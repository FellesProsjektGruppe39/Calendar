package Room;

import java.sql.SQLException;

import mysql.sqlRetrieve;

public class CheckRoom {
	

	
public static void main(String[] args) throws SQLException {
	
	sqlRetrieve test = new sqlRetrieve("SELECT * FROM bruker");
	
	while (test.getQuery().next()) {
		System.out.println(test.getQuery().getInt(1));
	}
}

}
