package calendar;
import java.util.Scanner;

import mysql.sqlRetrieve;;

public class CheckCalendar {
	//sqlRetrieve info = new sqlRetrieve();
	public static String PrintDay(int brukerid){
		String str;
		sqlRetrieve info = new sqlRetrieve("SELECT * from mote, mote_has_bruker,mote_has_rom "
				+ "WHERE mote_has_bruker.bruker_brukerid="+brukerid+
				" AND mote_has_bruker.mote_moteid=mote.moteid AND mote_has_rom.mote_moteid=mote.moteid ORDER BY dato, starttidspunkt ASC");
		
		
		str = ("Date       - Start    - End      - Description - Room");
		sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(* )FROM mote_has_bruker WHERE bruker_brukerid = " + "\"" + brukerid + "\"");
		
		
		
		for( int i=0; i < Integer.parseInt(moter.getQuery()[0][0]); i++){
			str += "\n" + (info.getQuery()[i][4] +" - "+info.getQuery()[i][1]+" - "
		+ info.getQuery()[i][2]+" - "+info.getQuery()[i][3]+" - "+info.getQuery()[i][10]);
		}
//		System.out.println(str);
		return str;
		
	}

public static void main(String[] args) {
	CheckCalendar test=new CheckCalendar();
	test.PrintDay(1);
	}
}

