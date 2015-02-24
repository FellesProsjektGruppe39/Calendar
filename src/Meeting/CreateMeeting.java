package Meeting;

import java.util.Date;
import mysql.sqlExecute;

public class CreateMeeting {
	
	private int Meetingid;
	private Date starttid;
	private Date slutttid;
	private String Beskrivelse;
	
	CreateMeeting(int Meetingid, Date starttid, Date slutttid, String Beskrivelse){
		this.Meetingid = Meetingid;
		this.starttid = starttid;
		this.slutttid = slutttid;
		this.Beskrivelse = Beskrivelse;
	}
	
	private void create(){
		sqlExecute cre = new sqlExecute();
		cre.execute("INSERT INTO møte (møteid,starttidspunkt,sluttidspunkt,beskrivelse) VALUES ('" + Meetingid +
				"','" + starttid + "','" + slutttid + "','" 
	            + Beskrivelse + "')");
	}

}
