package Room;

import java.util.Date;

import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class CheckRoom {
	
	public boolean check(String dato, String starttid, String slutttid, int kapasitet) {
		
		sqlRetrieve sqlret = new sqlRetrieve("SELECT romnavn, beskrivelse, kapasitet, sted FROM rom WHERE romnavn NOT IN (SELECT r.romnavn"
		+ " FROM mote m, mote_has_rom mr, rom r WHERE mr.mote_moteid = m.moteid "
		+ "AND m.sluttidspunkt > '" + starttid + "' AND m.starttidspunkt < '" + slutttid + "' "
		+ "AND m.dato = '" + dato + "' AND mr.rom_romnavn = r.romnavn) AND kapasitet >= " + kapasitet);
		
		if (sqlret.getQuery().length == 0) {
			System.out.println("Ingen ledige rom");
			return false;
		}
		for (int i = 0; i < sqlret.getQuery().length; i++) {
			for (int j = 0; j < sqlret.getQuery()[0].length; j++) {
				System.out.println(sqlret.getQuery()[i][j]);
			}
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		CheckRoom rom = new CheckRoom();
		rom.check("2015-03-30", "08:00:00", "09:30:00", 5);
	
	}
	
}