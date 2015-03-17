package notification;

import mysql.sqlExecute;

public class ClearNotification {
	
	sqlExecute sql = new sqlExecute();
	private int brukerid;
	
	public ClearNotification(int brukerid){
		this.brukerid = brukerid;
	}
	
	public void clearMyAll(){
		sql.execute("DELETE FROM notification WHERE bruker_brukerid ='"+this.brukerid+ "'");
	}
	
	public void clearMyOld(){
		sql.execute("DELETE FROM notification"
				+ " WHERE time_stamp < NOW( ) - INTERVAL 1 DAY "
				+ " AND bruker_brukerid = " + this.brukerid);
	}
	
	public static void main(String[] args) {
		
	for (int i = 1; i < 6; i++){ 
		ClearNotification cn = new ClearNotification(i);
		cn.clearMyOld();
	
	}
	}
}
