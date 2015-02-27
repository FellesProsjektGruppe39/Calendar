package calendar;

import java.sql.SQLException;

import logIn.LogIn;
import mysql.sqlRetrieve;
public class Main {
	
	private static int brukerid;
	
	public static void printCalendar(int brid){
		sqlRetrieve bruker = new sqlRetrieve("SELECT brukernavn FROM bruker WHERE brukerid = \"" + brid + "\"");
		System.out.println("Calendar for: " + bruker.getQuery()[0][0]);
		CheckCalendar brukercalendar = new CheckCalendar();
		brukercalendar.PrintDay(brid);
	}
	
	public static void main(String[] args){
		
		LogIn bruker = new LogIn();
		brukerid = bruker.LogIn();
		System.out.println(brukerid);
		if (brukerid == -1){
			System.out.println("Start på nytt");
		}
		else{
			printCalendar(brukerid);
		}
		
		
		
	}
	
	

}
