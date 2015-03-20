
package Room;

import java.util.Date;

import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class CheckRoom {
	public static String [][]allRoom;
	
	public static boolean check(String dato, String starttid, String slutttid, int kapasitet) {

		
		sqlRetrieve sqlret = new sqlRetrieve("SELECT romnavn, beskrivelse, kapasitet, sted FROM rom WHERE romnavn NOT IN (SELECT r.romnavn"
		+ " FROM mote m, mote_has_rom mr, rom r WHERE mr.mote_moteid = m.moteid "
		+ "AND m.sluttidspunkt > '" + starttid + "' AND m.starttidspunkt < '" + slutttid + "' "
		+ "AND m.dato = '" + dato + "' AND mr.rom_romnavn = r.romnavn) AND kapasitet >= " + kapasitet);
		allRoom=sqlret.getQuery();
		if (sqlret.getQuery().length == 0) {
			System.out.println("Ingen ledige rom");
			return false;
		}else{
		/*System.out.println("Valgnr - Romnavn   - Kapasitet - Sted             - Beskrivelse");
		for (int i = 0; i < sqlret.getQuery().length; i++) {
			//for (int j = 0; j < sqlret.getQuery()[0].length; j++) {
			 	System.out.print(i+"      -  ");
				System.out.print(String.format("%-8s",sqlret.getQuery()[i][0]));
				System.out.print(" - ");
				System.out.print(String.format("%-9s",sqlret.getQuery()[i][2]));
				System.out.print(" - ");
				System.out.print(String.format("%-16s",sqlret.getQuery()[i][3]));
				System.out.print(" - ");
				System.out.print(String.format("%-16s",sqlret.getQuery()[i][1]));
				System.out.println();
				//String.format("%40s",")
		}*/
			
			return true;
		}
	}
		
		public static boolean checkChange(String dato, String starttid, String slutttid, int kapasitet, int moteid) {

			
			sqlRetrieve sqlret = new sqlRetrieve(
					 " SELECT romnavn, beskrivelse, kapasitet, sted"
					+" FROM rom"
					+" WHERE romnavn" 
					+" NOT IN (SELECT r.romnavn"
					+		" FROM mote m, mote_has_rom mr, rom r"
					+		" WHERE mr.mote_moteid = m.moteid" 
					+		" AND m.sluttidspunkt > '" + starttid
					+		"' AND m.starttidspunkt <  '" + slutttid
					+		"' AND m.dato = '" + dato
					+		"' AND mr.rom_romnavn = r.romnavn"
					+		" AND m.moteid != " + moteid + ")"
					+		" AND kapasitet >= " + kapasitet);
			allRoom=sqlret.getQuery();
			if (sqlret.getQuery().length == 0) {
//				System.out.println("Ingen ledige rom");
				return false;
			}else{
			/*System.out.println("Valgnr - Romnavn   - Kapasitet - Sted             - Beskrivelse");
			for (int i = 0; i < sqlret.getQuery().length; i++) {
				//for (int j = 0; j < sqlret.getQuery()[0].length; j++) {
				 	System.out.print(i+"      -  ");
					System.out.print(String.format("%-8s",sqlret.getQuery()[i][0]));
					System.out.print(" - ");
					System.out.print(String.format("%-9s",sqlret.getQuery()[i][2]));
					System.out.print(" - ");
					System.out.print(String.format("%-16s",sqlret.getQuery()[i][3]));
					System.out.print(" - ");
					System.out.print(String.format("%-16s",sqlret.getQuery()[i][1]));
					System.out.println();
					//String.format("%40s",")
			}*/
				
				return true;
			}
		
	}
	
	
	
	public static void main(String[] args) {
		CheckRoom rom = new CheckRoom();
//		rom.check("2015-03-18", "10:00:00", "11:30:00", 5);
	
	}
	
}