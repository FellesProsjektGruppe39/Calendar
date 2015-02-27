package User;

import mysql.encryption;
import mysql.sqlExecute;

public class EditUser {
	

	public void Editfornavn(String newfornavn, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET fornavn =" + "\"" + newfornavn + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public void Editetternavn(String newetternavn, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET etternavn =" + "\"" + newetternavn + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public void EditTlfnr(String tlfnr, int brukerid){
		if (tlfnr.length() == 8){
			sqlExecute create = new sqlExecute();
			create.execute("UPDATE bruker SET tlfnr =" + "\"" + tlfnr + "\"" + "WHERE `brukerid` =" + brukerid);
		}else
			System.out.println("Du maa ha et telefonnummer som paa 8 tall!!");
	}
	
	public void EditBrukernavn(String brukernavn, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET brukernavn =" + "\"" + brukernavn + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public void EditPassord(String passord, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET passord =" + "\"" + encryption.md5(passord) + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public void EditStilling(String stilling, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET stilling =" + "\"" + stilling + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public void EditAdmin(int admin, int brukerid){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET admin =" + "\"" + admin + "\"" + "WHERE `brukerid` =" + brukerid);
	}
	
	public static void main(String[] args) {
		EditUser user = new EditUser();
		user.EditAdmin(1, 3);
		
	}
}
