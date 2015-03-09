package notification;

import mysql.sqlExecute;

public class ClearNotification {
	
	sqlExecute sql = new sqlExecute();
	private int brukerid;
	
	ClearNotification(int brukerid){
		this.brukerid = brukerid;
	}
	
	public void clear(){
		sql.execute("DELETE FROM notification WHERE bruker_brukerid ='"+this.brukerid+ "'");
	}
	
	

}
