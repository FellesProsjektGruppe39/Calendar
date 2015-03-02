package calendar;

import java.sql.SQLException;
import java.util.Scanner;

import logIn.LogIn;
import mysql.sqlRetrieve;
public class Main {
	
	//constructor
	Main(){
		
	}
	
	private static int brukerid;
	private static boolean bruk;
	private static boolean loggedin;
	
	public static void printCalendar(int brid){
		sqlRetrieve bruker = new sqlRetrieve("SELECT brukernavn FROM bruker WHERE brukerid = \"" + brid + "\"");
		System.out.println("Calendar for: " + bruker.getQuery()[0][0]);
		CheckCalendar brukercalendar = new CheckCalendar();
		brukercalendar.PrintDay(brid);
	}
	public void chooseaction(){
		System.out.println("What do you want to do?");
		System.out.println("'C' : Look at your calendar.");
		System.out.println("'D' : Delete an element in your calender. (Ikke implementert)");
		System.out.println("'E' : Add/change calendar. (Ikke implementert)");
		System.out.println("'L' : Log out.");
		System.out.println("'Q' : Quit program.");
		Scanner scanner = new Scanner(System.in);
		boolean Godkjent = false;
		while (Godkjent == false){
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("C")){
				printCalendar(brukerid);
				Godkjent = true;
			}
			else if (input.equalsIgnoreCase("Q")){
				System.out.println("Lukker porgrammet...");
				loggedin = false;
				bruk = false;
				Godkjent = true;
			}
			else if (input.equalsIgnoreCase("L")){
				System.out.println("Logging out...");
				loggedin = false;
				Godkjent = true;
			}
			else if (input.equalsIgnoreCase("D")){
				
			}
			else if (input.equalsIgnoreCase("E")){
				
			}
			else{
				System.out.println("Not a valid function, try again.");
			}
		}
	}
	
	public static void main(String[] args){
		bruk = true;
		while (bruk == true){
			brukerid = -1;
			while (brukerid == -1){
				LogIn bruker = new LogIn();
				brukerid = bruker.LogIn();
				if (brukerid == -1){
					System.out.println("Invalid username or password. Try again.");
				}
			}
			loggedin = true;
			Main bruker = new Main();
			while (loggedin == true){
				bruker.chooseaction();
			}
		}
		
		
		
		
		
	}
	
	

}
