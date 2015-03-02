package logIn;

import java.sql.SQLException;
import java.util.Scanner;
import mysql.encryption;
import mysql.sqlRetrieve;

public class LogIn {
	
	
	private int bruker;
	private String username;
	private String password;
	private String CorrectPassword;
	
	public int LogIn()  {

		toString2();
		
		sqlRetrieve passord = new sqlRetrieve(("SELECT passord FROM bruker WHERE brukernavn =" + "\"" + username +"\""));		
		
		try{
			CorrectPassword = (passord.getQuery()[0][0]);
		}
		catch (Exception ie){
			return -1;
		}
		
		
		if (CorrectPassword.equals(encryption.md5(password))){
			System.out.println(toString1());
			sqlRetrieve brukerid = new sqlRetrieve(("SELECT brukerid FROM bruker WHERE brukernavn =" + "\"" + username +"\""));
			bruker = Integer.parseInt(brukerid.getQuery()[0][0]);
			return bruker;
			
			
		}else
			return -1;
		
	}
	
	
	private String toString1() {
		return "Gratulerer du har naa logget inn!";
		
	}


	public void toString2() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Log In:");
		System.out.println("Enter your Username: ");
		this.username = scanner.nextLine();
		System.out.println("Enter your Password: ");
		this.password = scanner.nextLine();
		return;
	}
	
	public static void main(String[] args) throws SQLException {
		LogIn li = new LogIn();

		
	}
		
}
