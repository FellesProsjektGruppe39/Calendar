package User;

import mysql.sqlExecute;
import mysql.encryption;

public class User {
	
	private String fornavn;
	private String etternavn;
	private int TlfNr;
	private String stilling;
	private String passord;
	private int admin;
	private String brukernavn;
	sqlExecute create = new sqlExecute();
	
	User(String fornavn, String etternavn, int TlfNr,
			String brukernavn, String passord, String stilling, int admin) {
		this.fornavn=fornavn;
		this.etternavn=etternavn;
		this.TlfNr=TlfNr;
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
		try {
			if(this.brukernavn==null||this.passord==null){
				throw new IllegalArgumentException("Missing username and password");
		}
			
		create.execute("INSERT INTO bruker (fornavn,etternavn,tlfnr,"
				+ " brukernavn, passord, stilling, admin) VALUES ('" + this.fornavn + "','" + this.etternavn + "','" 
	            + this.TlfNr + "','" + this.brukernavn + "','" + encryption.md5(this.passord) + "','"  +
				this.stilling + "','" + this.admin + "')");
		
		}
		finally{}
		
	}
	public void DeleteUser(){
			
		create.execute("DELETE FROM bruker WHERE brukernavn ='" + this.brukernavn + "';");
	}
	
	
	
	public static void main(String[] args) {
		
		User User_1 = new User("kiiiiiim", "biiiiiiim");
		User_1.CreateUser();
		//User_1.DeleteUser();
	
	}

}
