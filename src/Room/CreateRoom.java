package Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import mysql.sqlExecute;

import com.mysql.jdbc.PreparedStatement;

public class CreateRoom {
	
	static String romnavn;
	static int Kapasitet;
	static String Sted;
	static String Beskrivelse;
	
	CreateRoom(String romnavn, int Kapasitet, String Sted, String Beskrivelse){
		this.romnavn = romnavn;
		this.Kapasitet = Kapasitet;
		this.Sted = Sted;
		this.Beskrivelse = Beskrivelse;
		
	}

   public void SetRoom() {


   }
}
