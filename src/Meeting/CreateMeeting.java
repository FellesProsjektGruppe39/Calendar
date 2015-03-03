package Meeting;

import java.util.Scanner;

import Room.CheckRoom;
import mysql.sqlExecute;

public class CreateMeeting {
	
	private String startTid;
	private String sluttTid;
	private String Beskrivelse;
	private String room; 
	private String date;
	private int userID;
	private int moteid;
	private int capacity;
	
	CreateMeeting() {
		this.userID=4;
	}
	
	public void setMeeting(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn start-tid for motet: hh:mm:ss");
		this.startTid = scanner.nextLine();
		System.out.println("Skriv inn slutt-tid for motet: hh:mm:ss");
		this.sluttTid = scanner.nextLine();
		System.out.println("Skriv inn en beskrivelse for motet");
		this.Beskrivelse = scanner.nextLine();
		System.out.println("Skriv inn dato for motet: yyyy-mm-dd");
		this.date= scanner.nextLine();
		System.out.println("Hvor mange skal delta pa mote:");
		this.capacity=Integer.parseInt(scanner.nextLine());

	}
	public void ChooseRoom(){
		CheckRoom roomOption=new CheckRoom();
		if(roomOption.check(this.date, this.startTid, this.sluttTid, this.capacity)){
			//int choice
		}
		
	}
	
	public void create(){
		sqlExecute cre = new sqlExecute();
		cre.execute("INSERT INTO mote (starttidspunkt,sluttidspunkt,beskrivelse,dato,opprettet_av) "
				+ "VALUES ('" + startTid + "','" + sluttTid + "','" 
	            + Beskrivelse + "','"+date+"','"+userID+"')");
		cre.execute("INSERT INTO mote_has_rom(mote_moteid,rom_romnavn) "
				+ "VALUES('" +8 +"','"+"S4"+"')");
	}
	
	public static void main(String[] args){
		CreateMeeting meeting=new CreateMeeting();
		meeting.setMeeting();
		meeting.create();
	}

}

