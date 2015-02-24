package logIn;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

import mysql.sqlRetrieve;

public class LogIn {
	
	private String username;
	private String password;
	private String CorrectPassword;
	
	public LogIn()  {
		toString2();
		
		sqlRetrieve passord = new sqlRetrieve(("SELECT passord FROM bruker WHERE brukernavn =" + "\"" + username +"\""));		
		CorrectPassword = (passord.getQuery()[0][0]);
		
		
		if (CorrectPassword.equals((password))){
			System.out.println(toString1());
			
		}else
			System.out.println("Du feilet");
		
	}
	
	
	private String toString1() {
		return "Gratulerer du har naa logget inn!";
		
	}


	public void toString2() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Username: ");
		username = scanner.nextLine();
		System.out.println("Enter your Password: ");
		this.password = scanner.nextLine();
		scanner.close();
		return;
	}
	
	public static void main(String[] args) throws SQLException {
		LogIn li = new LogIn();

		
	}
		
}
