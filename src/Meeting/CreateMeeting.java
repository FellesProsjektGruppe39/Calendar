package Meeting;

import java.sql.Time;
import java.util.Date;

import mysql.sqlExecute;

public class CreateMeeting {
	
	private String startTid;
	private String sluttTid;
	private String Beskrivelse;
	private String room; 
	private String date;
	private int userID;
	//private Time time;
	private int moteid;
	public CreateMeeting( String starttid, String slutttid, String Beskrivelse, String room, String date, int userID){
		this.startTid = starttid;
		this.sluttTid = slutttid;
		this.Beskrivelse = Beskrivelse;
		this.room=room;
		this.date=date;
		this.userID=userID;
		
	}
	
	public void create(){
		sqlExecute cre = new sqlExecute();
		cre.execute("INSERT INTO mote (starttidspunkt,sluttidspunkt,beskrivelse,dato,opprettet_av) "
				+ "VALUES ('" + startTid + "','" + sluttTid + "','" 
	            + Beskrivelse + "','"+date+"','"+userID+"')");
		//cre.execute("INSERT INTO mote_has_rom(mote_moteid,rom_romnavn) "
			//	+ "VALUES('"  +"','"+room+"')");
	}

}
