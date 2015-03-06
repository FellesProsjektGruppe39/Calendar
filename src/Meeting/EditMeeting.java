package Meeting;
import java.util.ArrayList;

import mysql.sqlExecute;
import mysql.sqlRetrieve;
public class EditMeeting {
	
	
		
		sqlExecute sql = new sqlExecute();
		
		private int moteid;
		
		public EditMeeting(int moteid) {
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
			
			sql.execute("INSERT INTO mote_has_bruker (mote_moteid,bruker_brukerid) VALUES ('" + moteid + "','" + brukerid + "')");
			
		}
		
		public void listUsers() {
			
			sqlRetrieve sqlret = new sqlRetrieve("SELECT brukerid, fornavn, etternavn FROM bruker;");
			System.out.println("BrukerID - Fornavn   - Etternavn");
			for (int i = 0; i < sqlret.getQuery().length; i++) {
				System.out.println(sqlret.getQuery()[i][0] + "        -  "+
						String.format("%-8s",sqlret.getQuery()[i][1])+" - " + String.format("%-16s",sqlret.getQuery()[i][2]));
			} 
			
		}
		public ArrayList<Integer> listUsers(ArrayList<Integer> alreadyChosen) {
			String query ="SELECT brukerid, fornavn, etternavn FROM bruker";
			for (int i =0;i<alreadyChosen.size();i++){
				if (i==0){
					query +=" WHERE ";
				}
				else{
					query+= " AND ";
				}
				query+= "brukerid != " + alreadyChosen.get(i);
			}
			sqlRetrieve sqlret = new sqlRetrieve(query);
			ArrayList<Integer> notChosen = new ArrayList<Integer>();
			if(sqlret.getQuery().length==0){
				
			}
			else{
				System.out.println("BrukerID - Fornavn   - Etternavn");
			
				for (int i = 0; i < sqlret.getQuery().length; i++) {
					System.out.println(sqlret.getQuery()[i][0] + "        -  "+
							String.format("%-8s",sqlret.getQuery()[i][1])+" - " + String.format("%-16s",sqlret.getQuery()[i][2]));
					notChosen.add(Integer.parseInt(sqlret.getQuery()[i][0]));
				
				}  
			}
			return notChosen;
			
			
		}
		
		public void addGroup(String name) {
			sqlRetrieve sqlret = new sqlRetrieve("SELECT gruppeid FROM gruppe WHERE gruppenavn = '"
					+ name + "';");

			sql.execute("INSERT INTO mote_has_bruker (mote_moteid,gruppe_gruppeid,attending) VALUES ('" + this.moteid + "','" + sqlret.getQuery()[0][0] + "','" + "0" + "')"); 
			
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

			EditMeeting meeting = new EditMeeting(16);
			meeting.listUsers();
			
			EditMeeting meeting1 = new EditMeeting(14);
			//meeting.DeleteMeeting();
			meeting1.addGroup("Konsulenter");

			//meeting.fjernbruker(3);
			
		}
	}

