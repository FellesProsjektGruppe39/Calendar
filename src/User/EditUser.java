package User;

import mysql.encryption;
import mysql.sqlExecute;

public class EditUser {
	

	public void Editfornavn(String newfornavn, String brukernavn){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET fornavn =" + "\"" + newfornavn + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
	}
	
	public void Editetternavn(String newetternavn, String brukernavn){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET etternavn =" + "\"" + newetternavn + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
	}
	
	public void EditTlfnr(String tlfnr, String brukernavn){
		if (tlfnr.length() == 8){
			sqlExecute create = new sqlExecute();
			create.execute("UPDATE bruker SET tlfnr =" + "\"" + tlfnr + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
		}else
			System.out.println("Du maa ha et telefonnummer som paa 8 tall!!");
	}
	
	public void EditPassord(String passord, String brukernavn){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET passord =" + "\"" + encryption.md5(passord) + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
	}
	
	public void EditStilling(String stilling, String brukernavn){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET stilling =" + "\"" + stilling + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
	}
	
	public void EditAdmin(String admin, String brukernavn){
		sqlExecute create = new sqlExecute();
		create.execute("UPDATE bruker SET admin =" + "\"" + admin + "\"" + "WHERE brukernavn = '" + brukernavn + "';");
	}
	
	public static void main(String[] args) {
		EditUser user = new EditUser();
		//user.EditPassord("brage", "MartinRH");
		
	}
}
