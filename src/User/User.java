package User;

import mysql.sqlExecute;
import mysql.sqlRetrieve;
public class User {
	
	static int brukerid;
	static String fornavn;
	static String etternavn;
	static int TlfNr;
	static String stilling;
	static String passord;
	static int admin;
	static String brukernavn;
	sqlExecute create = new sqlExecute();
	
	User(int brukerid, String fornavn, String etternavn, int TlfNr,
			String brukernavn, String passord, String stilling, int admin){
		this.brukerid=brukerid;
		this.fornavn=fornavn;
		this.etternavn=etternavn;
		this.TlfNr=TlfNr;
		this.brukernavn=brukernavn;
		this.passord=passord;
		this.stilling=stilling;
		this.admin=admin;
		
	}
	User(int brukerid){
		this.brukerid=brukerid;
	}
	User(int brukerid, String brukernavn, String passord){
		this.brukerid=brukerid;
		this.brukernavn=brukernavn;
		this.passord=passord;
	}
		 public void CreateUser(){
		try{
			if(this.brukernavn==null||this.passord==null){
				throw new IllegalArgumentException("Missing username and password");
				
			}
		create.execute("INSERT INTO bruker (brukerid,fornavn,etternavn,tlfnr,"
				+ " brukernavn, passord, stilling, admin) VALUES ('" + this.brukerid +
				"','" + this.fornavn + "','" + this.etternavn + "','" 
	            + this.TlfNr + "','" + this.brukernavn + "','" + this.passord + "','"  +
				this.stilling + "','" + this.admin + "')");
		
		}
		finally{}
		
	}
	public void DeleteUser(){
			
		create.execute("DELETE FROM bruker WHERE brukerid =" + this.brukerid);
	}
	
	
	
	public static void main(String[] args) {
		
		User User_1 = new User(103);
		User_1.DeleteUser();
	
		//DeleteUser UserDel=new DeleteUser(100);
		//UserDel.delete();
	}

}
