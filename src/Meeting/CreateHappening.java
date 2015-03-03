package Meeting;
import java.util.Scanner;
import Room.CheckRoom;
import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class CreateHappening {
		
		private String startTid;
		private String sluttTid;
		private String Beskrivelse;
		private String Sted;
		private String date;
		private int userID;
		private int hendelseid;
		
		CreateHappening(int userID) {
			this.userID = userID;
		}
		
		public void setMeeting(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn start-tid for hendelse: hh:mm:ss");
		this.startTid = scanner.nextLine();
		System.out.println("Skriv inn slutt-tid for hendelse: hh:mm:ss");
		this.sluttTid = scanner.nextLine();
		System.out.println("Skriv inn dato for motet: yyyy-mm-dd");
		this.date= scanner.nextLine();
		System.out.println("Hvor skal hendelsen vaere:");
		this.Sted =scanner.nextLine();
		System.out.println("Skriv inn en beskrivelse for hendelse:");
		this.Beskrivelse = scanner.nextLine();
		

	}
	
	
	public void create(){
		sqlExecute cre = new sqlExecute();
		cre.execute("INSERT INTO hendelse (hendelsestart,hendelseslutt,hendelsested,hendelsebeskrivelse,hendelsedato,bruker_brukerid) "
				+ "VALUES ('" + startTid + "','" + sluttTid + "','" 
	            + Sted + "','"+ Beskrivelse +"','"+ date +"','" +userID+"')");
		//sqlRetrieve retrieve=new sqlRetrieve("SELECT hendelseid FROM hendelse ORDER BY hendelseid");
		//int lasthappening = retrieve.getQuery().length;
		//this.hendelseid=Integer.parseInt((retrieve.getQuery()[lasthappening-1][0]));
		//cre.execute("INSERT INTO hendelse(hendelseid) "
		//		+ "VALUES('" + this.hendelseid +"')");
		}
		
		public static void main(String[] args){
			CreateHappening meeting=new CreateHappening(5);
			meeting.setMeeting();
			meeting.create();
		}

	}
