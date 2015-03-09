package notification;

import mysql.sqlExecute;

	public class CreateNotification {
		
		private int brukerid;
		private String beskrivelse;
		
		CreateNotification(int brukerid, String beskrivelse){
			this.brukerid = brukerid;
			this.beskrivelse = beskrivelse;
		}
		
	public void create(){
			sqlExecute cre = new sqlExecute();
			cre.execute("INSERT INTO notification (bruker_brukerid,beskrivelse) "
					+ "VALUES ('" + brukerid + "','" + beskrivelse +"')");
		}
	
	
	public static void main(String[] args) {
		CreateNotification Notif = new CreateNotification(5,"XX har invitert deg til å spille Candy Crush!");
		Notif.create();
	}
	}	
