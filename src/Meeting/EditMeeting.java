package Meeting;
import java.util.ArrayList;

import notification.CreateNotification;
import mysql.sqlExecute;
import mysql.sqlRetrieve;
public class EditMeeting {
	
	
	
	
	
		CreateNotification cn = new CreateNotification();
		sqlExecute sql = new sqlExecute();
		
		private int moteid;
		private String str;
		
		
		public EditMeeting(int moteid) {
			this.moteid = moteid;
		}
		
		public void DeleteMeeting(){
			
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]), info.getQuery()[0][2] + " den " + info.getQuery()[0][5] + " har blitt avlyst");
			}
			sql.execute("DELETE FROM mote WHERE moteid ='"+this.moteid+ "'");


			
			
		}
		public void endreBeskrivelse(String Beskrivelse) {
			
			
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]), info.getQuery()[0][2] + " den " + info.getQuery()[0][5] + " har fått en oppdatert beskrivelse");
			}
			sql.execute("UPDATE mote SET beskrivelse =" + "\"" + Beskrivelse + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreStarttid(String starttid){
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]), info.getQuery()[0][2] + " den " + info.getQuery()[0][5] + " har har endret starttidspunkt fra " + info.getQuery()[0][3]
						+ " til " + starttid);
			}
			
			sql.execute("UPDATE mote SET starttidspunkt =" + "\"" + starttid + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreSluttid(String sluttid){
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]), info.getQuery()[0][2] + " den " + info.getQuery()[0][5] + " har har endret sluttidspunkt fra " + info.getQuery()[0][4]
						+ " til " + sluttid);
			}
			sql.execute("UPDATE mote SET sluttidspunkt =" + "\"" + sluttid + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void leggtilbruker(int brukerid){
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			
			
				cn.create(brukerid, "Du har blitt invitert til " + info.getQuery()[0][2] + " den " + info.getQuery()[0][5] 
						+ " fra " + info.getQuery()[0][3] + " til " + info.getQuery()[0][4]);
						
			
		
			
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
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]), "Du har blitt slettet fra " + info.getQuery()[0][2] + " den " + info.getQuery()[0][5]);
		}
			sql.execute("DELETE FROM mote_has_bruker WHERE mote_moteid =" + "'" + moteid +  "'" + " AND bruker_brukerid =" + "'" + brukerid + "'");
		}
		public void endreDato(String dato){
			
			sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info.getQuery()[i][0]),  info.getQuery()[0][2] + " har endret dato fra " + info.getQuery()[0][5] + " til " + dato);
		}
			
			sql.execute("UPDATE mote SET dato =" + "\"" + dato + "\"" + "WHERE moteid =" + moteid);
			
		}
		
		public void endreRom(String nyttRom) {
			
			sql.execute("UPDATE mote_has_rom SET rom_romnavn =" + "\"" + nyttRom + "\"" + "WHERE mote_moteid ='" + moteid + "'");
			sqlRetrieve info1 = new sqlRetrieve("SELECT rom_romnavn, mote_moteid FROM mote_has_rom WHERE mote_moteid =" + this.moteid);
			sqlRetrieve info2 = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, dato"
					+ " FROM mote_has_bruker, mote"
					+ " WHERE mote_moteid = moteid"
					+ " AND mote_moteid = " + this.moteid);
			
			sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + this.moteid);
			for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
				cn.create(Integer.parseInt(info2.getQuery()[i][0]),  info2.getQuery()[0][2] + " den " + info2.getQuery()[0][3] + " har byttet rom til "
						+ info1.getQuery()[0][0]);
		}
			
		}
		
		//public void endreBeskrivelse(String nyBeskrivelse) {
			
		//	sql.execute("UPDATE rom SET Beskrivelse =" + "\"" + nyBeskrivelse + "\"" + "WHERE romnavn ='" + romNavn + "'");
		//}
		
		public static void main(String[] args) {

			EditMeeting meeting = new EditMeeting(11);
			meeting.endreSluttid("23:59:59");
			
		}
	}

