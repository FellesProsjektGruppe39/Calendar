package notification;

import mysql.sqlExecute;

public class ClearNotification {
	
	sqlExecute sql = new sqlExecute();
	private int brukerid;
	
	ClearNotification(int brukerid){
		this.brukerid = brukerid;
	}
	
	public void clearAll(){
		sql.execute("DELETE FROM notification WHERE bruker_brukerid ='"+this.brukerid+ "'");
	}
	
	public void clearOld(){
		sql.execute("DELETE FROM notification"
				+ "WHERE time_stamp < NOW( ) - INTERVAL 1 DAY "
				+ "AND bruker_brukerid =" + this.brukerid);
	}
	
	

}
