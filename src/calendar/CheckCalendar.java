package calendar;
import java.util.Scanner;

import mysql.sqlRetrieve;;

public class CheckCalendar {
	//sqlRetrieve info = new sqlRetrieve();
	public static String PrintDay(int brukerid){
		String str;
		sqlRetrieve info = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt,"
				+ " null as romnavn, beskrivelse, sted"
				+ " FROM mote m1, mote_has_bruker mb1 WHERE m1.moteid NOT IN"
				+ " (SELECT moteid FROM mote m2, mote_has_bruker mb2, mote_has_rom mr2 "
				+ " WHERE mb2.bruker_brukerid= " + brukerid + " AND mr2.mote_moteid = m2.moteid "
				+ " AND mb2.mote_moteid = m2.moteid) AND m1.moteid = mb1.mote_moteid"
				+ " AND mb1.bruker_brukerid = " + brukerid +") AS temp1)"
				+ " UNION "
				+"	(SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn, m3.beskrivelse, m3.sted"
				+ "	FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3 "
				+ "	WHERE mb3.bruker_brukerid= " + brukerid
				+ " AND mr3.mote_moteid = m3.moteid"
				+ "	AND mb3.mote_moteid = m3.moteid"
				+ "	ORDER BY dato, starttidspunkt ASC)"
				+ "	AS temp2)"
				+ " ORDER BY dato, starttidspunkt ASC");


			
		
		str = ("Date       - Start    - End      - Description - Room");
		sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(* )FROM mote_has_bruker WHERE bruker_brukerid = " + "\"" + brukerid + "\"");
		
		
		
		for( int i=0; i < Integer.parseInt(moter.getQuery()[0][0]); i++){
			str += "\n" + (info.getQuery()[i][1] +" - "+info.getQuery()[i][2]+" - "
		+ info.getQuery()[i][3]+" - "+info.getQuery()[i][5]+" - "+info.getQuery()[i][4] + " - " + info.getQuery()[i][6]);
		}
		System.out.println(str);
		return str;
		
	}

public static void main(String[] args) {
	CheckCalendar test=new CheckCalendar();
	test.PrintDay(5);
	}
}

