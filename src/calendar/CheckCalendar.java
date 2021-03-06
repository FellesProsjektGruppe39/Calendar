package calendar;
import java.util.Scanner;

import com.oracle.jrockit.jfr.TimedEvent;

import mysql.sqlRetrieve;;

/**
 * @author Brage
 *
 */

public class CheckCalendar {
	//sqlRetrieve info = new sqlRetrieve();
	/**
	 * @param brukerid
	 * @return
	 */
	public static String PrintDay(int brukerid){
		String str;
		sqlRetrieve info = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt, "
				+ " null as romnavn, beskrivelse, sted, attending"
				+ " FROM mote m1, mote_has_bruker mb1 WHERE m1.moteid NOT IN"
				+ " (SELECT moteid FROM mote m2, mote_has_bruker mb2, mote_has_rom mr2 "
				+ " WHERE mb2.bruker_brukerid= " + brukerid + " AND mr2.mote_moteid = m2.moteid "
				+ " AND mb2.mote_moteid = m2.moteid) AND m1.moteid = mb1.mote_moteid"
				+ " AND mb1.bruker_brukerid = " + brukerid 
				+ " AND mb1.attending = 1"
				+ " AND m1.dato >= CURDATE())"
				+ " AS temp1)"
				+ " UNION "
				+"	(SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn, m3.beskrivelse, m3.sted, mb3.attending"
				+ "	FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3 "
				+ "	WHERE mb3.bruker_brukerid= " + brukerid
				+ " AND mr3.mote_moteid = m3.moteid"
				+ "	AND mb3.mote_moteid = m3.moteid"
				+ " AND mb3.attending = 1"
				+ " AND m3.dato >= CURDATE() "
				+ "	ORDER BY dato, starttidspunkt ASC)"
				+ "	AS temp2)"
				+ " ORDER BY dato, starttidspunkt ASC");

		
		str = String.format("%-5s    %-10s   %-8s   %-8s   %-45s   %-20s","MoteId", "Date", "Start","End","Description","Room");

		sqlRetrieve moter = new sqlRetrieve (
				"SELECT COUNT(moteid)"
				+ " FROM mote m"
				+ " INNER JOIN mote_has_bruker mb "
				+ " ON m.moteid = mb.mote_moteid"
				+ " WHERE mb.bruker_brukerid = " + brukerid
				+ " AND m.dato >= CURDATE()"
				+ " AND mb.attending = 1");
		
		
		for( int i=0; i < Integer.parseInt(moter.getQuery()[0][0]); i++){
			str += String.format("\n %-5s %-10s - %-8s   %-8s   %-45s   %-20s",info.getQuery()[i][0], info.getQuery()[i][1], info.getQuery()[i][2], info.getQuery()[i][3], info.getQuery()[i][5], info.getQuery()[i][4]);
					
		}
		
		System.out.println(str);

		return str;
	}
	
	public static String PrintDay1(int gruppeid){
		String str;
		sqlRetrieve info = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt,null as romnavn, beskrivelse, sted"
				+" FROM mote m1, gruppe_has_mote gm1"
				+" WHERE moteid"
				+" NOT IN"
					+" (SELECT m2.moteid"
					+" FROM mote m2, gruppe_has_mote gm2 ,mote_has_rom mr2"
					+" WHERE gm2.gruppe_gruppeid ="+gruppeid
					+" AND mr2.mote_moteid = m2.moteid"
					+" AND gm2.mote_moteid = m2.moteid)"
				+" AND m1.moteid = gm1.mote_moteid"
				+" AND gm1.gruppe_gruppeid =" +gruppeid +")"
				+" AS temp1)"
				+" UNION"
				+" (SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn,  m3.beskrivelse, m3.sted"
				+" FROM mote m3,gruppe_has_mote gm3 ,mote_has_rom mr3" 
				+" WHERE gm3.gruppe_gruppeid ="+ gruppeid
				+" AND mr3.mote_moteid = m3.moteid"
				+" AND gm3.mote_moteid = m3.moteid"
				+" ORDER BY dato, starttidspunkt ASC)"
				+" AS temp2)"
				+" ORDER BY dato, starttidspunkt ASC");
		
		
		str = String.format("%-5s    %-10s   %-8s   %-8s   %-45s   %-20s","MoteId", "Date", "Start","End","Description","Room");
		
		sqlRetrieve moter = new sqlRetrieve (
				"SELECT COUNT(moteid)"
						+ " FROM mote m"
						+ " INNER JOIN gruppe_has_mote mb "
						+ " ON m.moteid = mb.mote_moteid"
						+ " WHERE mb.gruppe_gruppeid = " + gruppeid
						);
		
		
		for( int i=0; i < Integer.parseInt(moter.getQuery()[0][0]); i++){
			str += String.format("\n %-5s %-10s - %-8s   %-8s   %-45s   %-20s",info.getQuery()[i][0], info.getQuery()[i][1], info.getQuery()[i][2], info.getQuery()[i][3], info.getQuery()[i][5], info.getQuery()[i][4]);
			
		}
		
		System.out.println(str);
		
		return str;
	}
	public static String PrintWeek(int brukerid, int ukenr, int aar){
		String str;
		
		sqlRetrieve info = new sqlRetrieve(
				"(SELECT * FROM(SELECT moteid,dato,WEEKOFYEAR(dato) AS uke, starttidspunkt,sluttidspunkt, null AS romnavn, beskrivelse, sted, attending"
				+ " FROM mote m1, mote_has_bruker mb1"
				+ " WHERE m1.moteid NOT IN"
				+ " (SELECT moteid FROM mote m2, mote_has_bruker mb2, mote_has_rom mr2 "
				+ " WHERE mb2.bruker_brukerid= " + brukerid 
				+ " AND mr2.mote_moteid = m2.moteid "
				+ " AND mb2.mote_moteid = m2.moteid)"
				+ " AND m1.moteid = mb1.mote_moteid"
				+ " AND mb1.bruker_brukerid = " + brukerid 
				+ " AND WEEKOFYEAR(m1.dato) = " + ukenr
				+ " AND YEAR(m1.dato) = YEAR(CURDATE())"
				+ " AND mb1.attending = 1)"
				+ " AS temp1)"
				+ " UNION "
				+"	(SELECT * FROM(SELECT m3.moteid, m3.dato, WEEKOFYEAR(dato) AS uke, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn, m3.beskrivelse, m3.sted, mb3.attending"
				+ "	FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3 "
				+ "	WHERE mb3.bruker_brukerid= " + brukerid
				+ " AND mr3.mote_moteid = m3.moteid"
				+ "	AND mb3.mote_moteid = m3.moteid"
				+ " AND WEEKOFYEAR(m3.dato) = " + ukenr
				+ " AND YEAR(m3.dato) = " + aar
				+ " AND mb3.attending = 1"
				+ "	ORDER BY dato, starttidspunkt ASC)"
				+ "	AS temp2)"
				+ " ORDER BY dato, starttidspunkt ASC");
		
		
//		sqlRetrieve info2 = new sqlRetrieve(""); 
		str = String.format("%-120s", " -------------------------------- Uke " + ukenr + " - " + aar + "--------------------------------");
		str += String.format("%n %-5s   %-10s   %-8s   %-8s   %-30s   %-20s","MoteId", "Date", "Start","End","Description","Room");
		sqlRetrieve moter = new sqlRetrieve (
				"SELECT COUNT(moteid)"
				+ " FROM mote m"
				+ " INNER JOIN mote_has_bruker mb "
				+ " ON m.moteid = mb.mote_moteid"
				+ " WHERE mb.bruker_brukerid = " + brukerid
				+ " AND WEEKOFYEAR(m.dato) = " + ukenr
				+ " AND mb.attending = 1"
				+ " AND YEAR(m.dato) = YEAR(CURDATE())");

		
		for( int i=0; i < Integer.parseInt(moter.getQuery()[0][0]); i++){
			
			
			
			str += String.format("\n %-5s %-10s - %-8s   %-8s   %-30s   %-20s",info.getQuery()[i][0], info.getQuery()[i][1], info.getQuery()[i][3], info.getQuery()[i][4],info.getQuery()[i][6], info.getQuery()[i][5]);
					
		}
		
//		System.out.println(str);
		return str;
	}
	

public boolean checkinput(String string, String string2, String string3, String string4){
	String[] start = string.split(":");
	String[] slutt = string2.split(":");
	String[] dag = string3.split("-");
	
	if(start.length == 3 && slutt.length == 3 && dag.length == 3){
		
		try {
			int s1 = Integer.parseInt(start[0]);
			int sl1 = Integer.parseInt(slutt[0]);
			int s2 = Integer.parseInt(start[1]);
			int sl2 = Integer.parseInt(slutt[1]);
			int s3 = Integer.parseInt(start[2]);
			int sl3 = Integer.parseInt(slutt[2]);
			int d1 = Integer.parseInt(dag[0]);
			int d2 = Integer.parseInt(dag[1]);
			int d3 = Integer.parseInt(dag[2]);
			int an = Integer.parseInt(string4);
				
		if(an<=0){
			return false;
		}else{
		if((s1>=0 && s1<25)&&(s2>=0 && s2<61)&&(s3>=0 && s3<61)&&(sl1>=0 && sl1<25)&&(sl2>=0 && sl2<61)&&(sl3>=0 && sl3<61)){
			if(sl1<s1){
				System.out.println("False");
				return false;
			}if((sl2<s2)&&(sl1==s1)){
				System.out.println("False");
				return false;
			}if((sl3<s3)&&(sl1==s1)&&(sl1==s1)){
				System.out.println("False");
				return false;
			}
			return true;
		}
		if(d2>12){
			return false;
		}
		switch (d3) {
		case 1:
			if(d3<=31 && d3>=0){
				return true;
			}
		case 2:
			if(isLeapYear(d1)){
				if(d3<=29 && d3>=0){
					return true;
				}else
					if((d3<=28 && d3>=0)){
						return true;
					}
			}
		case 3:
			if(d3<=31 && d3>=0){
				return true;
			}
		case 4:
		if(d3<=30 && d3>=0){
			return true;
		}
		case 5:
		if(d3<=31 && d3>=0){
			return true;
		}
		case 6:
		if(d3<=30 && d3>=0){
			return true;
		}
		case 7:
		if(d3<=31 && d3>=0){
			return true;
		}
		case 8:
		if(d3<=31 && d3>=0){
			return true;
		}
		case 9:
		if(d3<=30 && d3>=0){
			return true;
		}
		case 10:
		if(d3<=31 && d3>=0){
			return true;
		}
		case 11:
		if(d3<=30 && d3>=0){
			return true;
		}
		case 12:
		if(d3<=31 && d3>=0){
			return true;
		}

		default:
			return false;
		}
		}
	}catch (Exception e) {
		return false;
	}
	}
	return false;
	
}
public static boolean isLeapYear(int year) {
    assert year >= 1583; // not valid before this date.
    return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
}

public static void main(String[] args) {
	CheckCalendar test = new CheckCalendar();

	System.out.println(test.PrintWeek(1, 12, 2015));
	//CheckCalendar test=new CheckCalendar();
	
//	System.out.println(test.PrintDay1(15));

	}
}

