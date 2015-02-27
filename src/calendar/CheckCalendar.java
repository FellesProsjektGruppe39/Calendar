package calendar;
import java.util.Scanner;

import mysql.sqlRetrieve;;

public class CheckCalendar {
	//sqlRetrieve info = new sqlRetrieve();
	public void PrintDay(int brukerid){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please select day:");
		String day= scanner.nextLine();
		sqlRetrieve info = new sqlRetrieve("SELECT * from mote, mote_has_bruker,mote_has_rom "
				+ "WHERE mote_has_bruker.bruker_brukerid="+brukerid+
				" AND mote_has_bruker.mote_moteid=mote.moteid AND mote_has_rom.mote_moteid=mote.moteid "
				+ "AND mote.dato='"+day+"'");
		
		
		System.out.println("Date      - Start     - End      - Description - Room");
		for( int i=0; i <1;i++){
			System.out.println(info.getQuery()[0][4] +" - "+info.getQuery()[0][1]+" - "
		+ info.getQuery()[0][2]+" - "+info.getQuery()[0][3]+" - "+info.getQuery()[0][9]);
		}
		scanner.close();
		//System.out.println("Test2");
	}

public static void main(String[] args) {
	CheckCalendar test=new CheckCalendar();
	test.PrintDay(1);
	}
}

