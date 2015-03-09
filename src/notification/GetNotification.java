package notification;

import mysql.sqlRetrieve;

public class GetNotification {
	
	private int brukerid;
	
	GetNotification(int brukerid){
		this.brukerid = brukerid;
	}
	
	public void get(){
		
		sqlRetrieve notifications = new sqlRetrieve(("SELECT beskrivelse, time_stamp FROM notification WHERE bruker_brukerid = '" +  brukerid +"'"));
		for (int i = 0; i < notifications.getQuery().length; i++) {
			System.out.println(String.format("%-60s", notifications.getQuery()[i][0]) + "        -  "+
					String.format("%-8s",notifications.getQuery()[i][1]));
		}
	}
	
	public static void main(String[] args) {
		GetNotification Notif = new GetNotification(5);
		Notif.get();
	}
}
