package notification;

import mysql.sqlRetrieve;

public class GetNotification {
	
	private int brukerid;
	public String out = "";
	
	GetNotification(int brukerid){
		this.brukerid = brukerid;
	}
	
	public String get(){
		
		sqlRetrieve notifications = new sqlRetrieve(("SELECT beskrivelse, time_stamp FROM notification WHERE bruker_brukerid = '" +  brukerid +"'"));
		for (int i = 0; i < notifications.getQuery().length; i++) {
			out += String.format(" %-60s", notifications.getQuery()[i][0]) + "        -  " +
					String.format("%-8s %n",notifications.getQuery()[i][1] );
			
			
		}
		return out;
	}
	
	public static void main(String[] args) {
		GetNotification Notif = new GetNotification(5);
		System.out.println(Notif.get());
	}
}
