
package Meeting;

import java.util.ArrayList;
import java.util.Scanner;

import Room.CheckRoom;
import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class CreateMeeting {
	
	private String startTid;
	private String sluttTid;
	private String Beskrivelse;
	private String room; 
	private String date;
	private int userID;
	private int moteid;
	private int capacity;
	
	public CreateMeeting(int userID) {
		this.userID=userID;
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
	public void setMeeting(String startTid, String sluttTid,String beskrivelse, String date, String capacity){
		this.startTid = startTid;
		this.sluttTid = sluttTid;
		this.Beskrivelse = beskrivelse;
		this.date= date;
		this.capacity=Integer.parseInt(capacity);

	}
	
	public void ChooseRoomGUI(){
		//this.capacity=numUsers;
		CheckRoom roomOption=new CheckRoom();
		if(roomOption.check(this.date, this.startTid, this.sluttTid, this.capacity)){
			
			this.room=roomOption.allRoom[0][0];
			
		}//if
		else{
			System.out.println("Vil du velge et annet tidspunkt for motet?");
		}
		
	}
	public void ChooseRoom(){
		//this.capacity=numUsers;
		CheckRoom roomOption=new CheckRoom();
		if(roomOption.check(this.date, this.startTid, this.sluttTid, this.capacity)){
			int choice=10000;
			Scanner scanner = new Scanner(System.in);
			boolean run= false;
			while(choice>=roomOption.allRoom.length || choice<0){
				System.out.println("Valgnr - Romnavn           - Kapasitet - Sted             - Beskrivelse");
				for (int i = 0; i < roomOption.allRoom.length; i++) {
					//for (int j = 0; j < sqlret.getQuery()[0].length; j++) {
					 	System.out.print(i+"      -  ");
						System.out.print(String.format("%-16s",roomOption.allRoom[i][0]));
						System.out.print(" - ");
						System.out.print(String.format("%-9s",roomOption.allRoom[i][2]));
						System.out.print(" - ");
						System.out.print(String.format("%-16s",roomOption.allRoom[i][3]));
						System.out.print(" - ");
						System.out.print(String.format("%-16s",roomOption.allRoom[i][1]));
						System.out.println();
						//String.format("%40s",")
				}//for
				if(run){
					System.out.println("Du valgte et ugyldig rom.");
				}
				System.out.println("Velg et rom:");
				choice= Integer.parseInt(scanner.nextLine());//HER MAA DET LEGGES INN EN TRY CATCH HVIS DET SKRIVES INN NOE ANNET ENN TALL!
				run=true;
				
			}//while
			this.room=roomOption.allRoom[choice][0];
			
		}//if
		else{
			System.out.println("Vil du velge et annet tidspunkt for motet?");
		}
		
	}
	public int ChooseUsers(){
		int addedUsers=1;
		String choice="";
		Scanner scanner = new Scanner(System.in);
		boolean godkjent = false;
		while(godkjent==false) {
			System.out.println("Vil du legge til deltagere eller grupper til motet?");
			System.out.println("'N' : Nei");
			System.out.println("'D' : Legg til deltagere");
			System.out.println("'G' : Legg til gruppe");
			choice =scanner.nextLine();
			if (choice.equalsIgnoreCase("N")){
				godkjent=true;
			}
			else if(choice.equalsIgnoreCase("D")){
				godkjent=true;
			}
			else if(choice.equalsIgnoreCase("G")){
				godkjent=true;
			}
			else{
				System.out.println("Ugyldig valg.Vennligst velg igjen:");
			}
		}//while
		EditMeeting editMeeting = new EditMeeting(this.moteid);
		editMeeting.leggtilbruker(this.userID);
		if (choice.equalsIgnoreCase("N")){//
			
		}
		else if(choice.equalsIgnoreCase("G")){
			
		}else if (choice.equalsIgnoreCase("D")){
			ArrayList<Integer> chosenUsers=new ArrayList<Integer>();//List of chosen users
			
			chosenUsers.add(this.userID);
			String answer= "";
			//boolean go=true;
			while(!answer.equalsIgnoreCase("Q")){
				ArrayList<Integer> notChosenUsers=new ArrayList<Integer>();
				notChosenUsers=editMeeting.listUsers(chosenUsers);//makes a list with users that is not added to the list.
				if(notChosenUsers.size()==0){
					break;
				}
				System.out.println("BrukerID: Skriv inn deltagerens brukerID for a legge til brukeren i motet");
				System.out.println("Q: Du er ferdig med a legge til deltagere.");
				try{						
						answer=scanner.nextLine(); 
						if(answer.equalsIgnoreCase("Q")){
						}
						else{
							int cUser=Integer.parseInt(answer);
							if(notChosenUsers.contains(cUser)){//if answer is a valid userid that is not already chosen add this user to meeting 
								editMeeting.leggtilbruker(Integer.parseInt(answer));
								chosenUsers.add(Integer.parseInt(answer));
								addedUsers+=1;
							}//if
							else{
								System.out.println("Ugyldig brukerid");
							}
						}//else
				}//try
				catch(Exception e){
					System.out.println("Ugyldig input");
					//answer=scanner.nextLine(); 
				}//catch
				
				
			}//while
			
		}//D: Add users
			
		return addedUsers;	
	}//ChooseUsers
	
	public void create(){
		sqlExecute cre = new sqlExecute();
		cre.execute("INSERT INTO mote (starttidspunkt,sluttidspunkt,beskrivelse,dato,opprettet_av) "
				+ "VALUES ('" + startTid + "','" + sluttTid + "','" 
	            + Beskrivelse + "','"+date+"','"+userID+"')");
		sqlRetrieve retrieve=new sqlRetrieve("SELECT moteid FROM mote ORDER BY moteid");
		int lastmeeting=retrieve.getQuery().length;
		this.moteid=Integer.parseInt((retrieve.getQuery()[lastmeeting-1][0]));
		cre.execute("INSERT INTO mote_has_rom(mote_moteid,rom_romnavn) "
				+ "VALUES('" +this.moteid +"','"+this.room+"')");
		System.out.println(moteid);
		final EditMeeting meeting = new EditMeeting(moteid);
		meeting.leggtilbruker(userID);
		
	}
	public void CMeeting(){
		this.setMeeting();
		this.ChooseRoom();
		this.create();
		this.ChooseUsers();
	}
	public static void main(String[] args){
		CreateMeeting meeting=new CreateMeeting(4);
		meeting.CMeeting();
	}

}

