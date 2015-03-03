package Meeting;
import mysql.sqlExecute;
import mysql.sqlRetrieve;
public class EditMeeting {
	
	
		
		sqlExecute sql = new sqlExecute();
		
		private int moteid;
		
		EditMeeting(int moteid) {
			this.moteid = moteid;
		}
		public void DeleteMeeting(){
			sql.execute("DELETE FROM mote WHERE moteid ='"+this.moteid+ "'");
		}
		public void endreBeskrivelse(String Beskrivelse) {
			
			sql.execute("UPDATE mote SET beskrivelse =" + "\"" + Beskrivelse + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreStarttid(String starttid){
			
			sql.execute("UPDATE mote SET starttidspunkt =" + "\"" + starttid + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreSluttid(String sluttid){
			
			sql.execute("UPDATE mote SET sluttidspunkt =" + "\"" + sluttid + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void leggtilbruker(int brukerid){
			
			sql.execute("INSERT INTO mote_has_bruker (mote_moteid,bruker_brukerid,attending) VALUES ('" + moteid + "','" + brukerid + "','" + "0" + "')");
			
		}
		
		public void listUsers() {
			
			sqlRetrieve sqlret = new sqlRetrieve("SELECT brukerid, fornavn, etternavn FROM bruker;");
			
			for (int i = 0; i < sqlret.getQuery().length; i++) {
				System.out.println(sqlret.getQuery()[i][0] + 
						sqlret.getQuery()[i][1] + sqlret.getQuery()[i][2]);
			} 
			
		}
		
		public void addGroup() {
			
		}
		
		public void fjernbruker(int brukerid){
			
			sql.execute("DELETE FROM mote_has_bruker WHERE mote_moteid =" + "'" + moteid +  "'" + " AND bruker_brukerid =" + "'" + brukerid + "'");
			
		}
		
		public void endreDato(String dato){
			
			sql.execute("UPDATE mote SET dato =" + "\"" + dato + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreRom(String nyttRom) {
			
			sql.execute("UPDATE mote_has_rom SET rom_romnavn =" + "\"" + nyttRom + "\"" + "WHERE mote_moteid ='" + moteid + "'");

		}
		
		//public void endreBeskrivelse(String nyBeskrivelse) {
			
		//	sql.execute("UPDATE rom SET Beskrivelse =" + "\"" + nyBeskrivelse + "\"" + "WHERE romnavn ='" + romNavn + "'");
		//}
		
		public static void main(String[] args) {
			EditMeeting meeting = new EditMeeting(17);
			meeting.DeleteMeeting();
			
			//meeting.fjernbruker(3);
			
		}
	}

