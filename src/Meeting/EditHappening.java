package Meeting;
import mysql.sqlExecute;

public class EditHappening {	
	sqlExecute sql = new sqlExecute();
	
	private int hendelseid;
	
	EditHappening(int hendelseid) {
		this.hendelseid = hendelseid;
	}
	
	public void endreBeskrivelse(String Beskrivelse) {
		
		sql.execute("UPDATE hendelse SET hendelsebeskrivelse =" + "\"" + Beskrivelse + "\"" + "WHERE hendelseid =" + hendelseid);
		
	}
	
	public void endreStarttid(String starttid){
		
		sql.execute("UPDATE hendelse SET hendelsestart =" + "\"" + starttid + "\"" + "WHERE hendelseid =" + hendelseid);
		
	}
	
	public void endreSluttid(String sluttid){
		
		sql.execute("UPDATE hendelse SET hendelseslutt =" + "\"" + sluttid + "\"" + "WHERE hendelseid =" + hendelseid);
		
	}

	public void endreDato(String dato){
		
		sql.execute("UPDATE hendelse SET hendelsedato =" + "\"" + dato + "\"" + "WHERE hendelseid =" + hendelseid);
		
	}
	
	public void endreSted(String nyttSted) {
		
		sql.execute("UPDATE hendelse SET hendelsested =" + "\"" + nyttSted + "\"" + "WHERE hendelseid ='" + hendelseid + "'");

	}
	
			
	public static void main(String[] args) {
		EditHappening happening = new EditHappening(3);
		
		happening.endreSted("Skolen");
		
		
	}
}
