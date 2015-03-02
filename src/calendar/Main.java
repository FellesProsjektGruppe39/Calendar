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
	
	public static void printCalendar(int brid){
		sqlRetrieve bruker = new sqlRetrieve("SELECT brukernavn FROM bruker WHERE brukerid = \"" + brid + "\"");
		System.out.println("Calendar for: " + bruker.getQuery()[0][0]);
		CheckCalendar brukercalendar = new CheckCalendar();
		brukercalendar.PrintDay(brid);
	}
	public void chooseaction(){
		System.out.println("Hva vil du gjøre?");
		System.out.println("'C' : For å se sin kalender.");
		System.out.println("'S' : Slette en avtale i din kalender. (Ikke implementert)");
		System.out.println("'E' : Endre din kalender. (Ikke implementert)");
		System.out.println("'L' : Logg ut. (Ikke implementert)");
		Scanner scanner = new Scanner(System.in);
		boolean Godkjent = false;
		while (Godkjent == false){
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("C")){
				printCalendar(brukerid);
				Godkjent = true;
			}
			else{
				System.out.println("Not a valid function, try again.");
			}
		}
		scanner.close();
	}
	
	public static void main(String[] args){
		
		brukerid = -1;
		while (brukerid == -1){
			LogIn bruker = new LogIn();
			brukerid = bruker.LogIn();
			if (brukerid == -1){
				System.out.println("Feil brukernavn eller passord. Start paa nytt");
			}
		}
		Main bruker = new Main();
		bruker.chooseaction();
		
		
		
	}
	
	

}
