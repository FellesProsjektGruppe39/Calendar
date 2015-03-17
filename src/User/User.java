package User;

import mysql.sqlExecute;
import mysql.encryption;
import mysql.sqlRetrieve;

public class User {
	
	private String fornavn;
	private String etternavn;
	private String TlfNr;
	private String stilling;
	private String passord;
	private int admin;
	private String brukernavn;
	sqlExecute create = new sqlExecute();
	
	public User(String fornavn, String etternavn, String string,
			String brukernavn, String passord, String stilling, int admin) {
		this.fornavn=fornavn;
		this.etternavn=etternavn;
		this.TlfNr=string;
		this.brukernavn=brukernavn;
		this.passord=passord;
		this.stilling=stilling;
		this.admin=admin;
	}

	User(String brukernavn, String passord) {
		this.brukernavn=brukernavn;
		this.passord=passord;
	}
	
	public void CreateUser(){
		
		//boolean godkjent = false;
		
		try {
			
			//while (godkjent == false) {
			
				if(this.brukernavn==null||this.passord==null){
					throw new IllegalArgumentException();
				}
		
				sqlRetrieve check = new sqlRetrieve("SELECT * FROM bruker WHERE brukernavn ='" + this.brukernavn + "';");
		
				if (check.getQuery().length == 1) {
					throw new IllegalArgumentException();
				}
				
				create.execute("INSERT INTO bruker (fornavn,etternavn,tlfnr,"
						+ " brukernavn, passord, stilling, admin) VALUES ('" + this.fornavn + "','" + this.etternavn + "','" 
						+ this.TlfNr + "','" + this.brukernavn + "','" + encryption.md5(this.passord) + "','"  +
						this.stilling + "','" + this.admin + "')");
				
				System.out.println("Bruker " + this.brukernavn + " ble opprettet!");
		//	} 
		}
		catch (Exception i){
			System.out.println("Du har skrevet inn feil input.");
			
		}
		
		
	}
	public void DeleteUser(){
			
		create.execute("DELETE FROM bruker WHERE brukernavn ='" + this.brukernavn + "';");
	}
	
	
	public static void main(String[] args) {
		
		User User_1 = new User("Petter", "Northug", 90909090, "PettN", "Brage", "Insatt", 0);
		User_1.CreateUser();
		//User_1.DeleteUser();
	}

}
