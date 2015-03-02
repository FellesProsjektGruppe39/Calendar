package Meeting;

import java.util.Scanner;

import mysql.sqlExecute;

public class CreateMeeting {
	
	private String startTid;
	private String sluttTid;
	private String Beskrivelse;
	private String room; 
	private String date;
	private int userID;
	private int moteid;
	
	CreateMeeting() {
		
	}
	
	public void setMeeting(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn start-tid for motet");
		this.startTid = scanner.nextLine();
		System.out.println("Skriv inn slutt-tid for motet");
		this.sluttTid = scanner.nextLine();
		System.out.println("Skriv inn en beskrivelse for motet");
		this.Beskrivelse = scanner.nextLine();
		System.out.println("Skriv inn dato for motet");
		this.date= scanner.nextLine();

		
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
