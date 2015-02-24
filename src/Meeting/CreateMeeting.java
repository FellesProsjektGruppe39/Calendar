package Meeting;

import java.util.Date;

public class CreateMeeting {
	
	private int Meetingtid;
	private Date starttid;
	private Date slutttid;
	private String Beskrivelse;
	
	CreateMeeting(int Meetingid, Date starttid, Date slutttid, String Beskrivelse){
		this.Meetingtid = Meetingid;
		this.starttid = starttid;
		this.slutttid = slutttid;
		this.Beskrivelse = Beskrivelse;
	}
	
	private void create(){
		
	}

}
