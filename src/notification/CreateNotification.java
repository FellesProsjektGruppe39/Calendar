package notification;

import mysql.sqlExecute;

	public class CreateNotification {
		
	
		
		
	public void create(int brukerid, String beskrivelse){
		
			sqlExecute cre = new sqlExecute();
			cre.execute("INSERT INTO notification (bruker_brukerid,beskrivelse) "
					+ "VALUES ('" + brukerid + "','" + beskrivelse +"')");
		}
	
	
	public static void main(String[] args) {
		CreateNotification Notif = new CreateNotification();
		Notif.create(5, "Se bak deg!");
	}
	}	
