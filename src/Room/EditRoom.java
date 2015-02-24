package Room;

import mysql.sqlExecute;

public class EditRoom {
	
	sqlExecute sql = new sqlExecute();
	
	private static String romNavn;
	
	EditRoom(String romNavn) {
		this.romNavn = romNavn;
	}
	
	public void endreNavn(String nyttNavn) {
		
		sql.execute("UPDATE rom SET romnavn =" + "\"" + nyttNavn + "\"" + "WHERE romnavn =" + romNavn);
		
	}
	
	public void endreKapasitet(int nyKapasitet) {
		sql.execute("UPDATE rom SET Kapasitet =" + "\"" + nyKapasitet + "\"" + "WHERE romnavn ='" + romNavn + "'");

	}
	
	public void endreSted(String nyttSted) {
		sql.execute("UPDATE rom SET Sted =" + "\"" + nyttSted + "\"" + "WHERE romnavn ='" + romNavn + "'");

	}
	
	public void endreBeskrivelse(String nyBeskrivelse) {
		
		sql.execute("UPDATE rom SET Beskrivelse =" + "\"" + nyBeskrivelse + "\"" + "WHERE romnavn ='" + romNavn + "'");
	}
	
	public static void main(String[] args) {
		EditRoom rom = new EditRoom("Galtvort");
		
		rom.endreBeskrivelse("Morradiiiiiiii");
		rom.endreKapasitet(10);
		rom.endreSted("Hvoooor");
	}
}
