package User;
import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class Group {
	
	private String groupName;
	sqlExecute sqlexec = new sqlExecute();
	
	Group(String name) {
		this.groupName = name;
	}
	
	Group() {
		
	}
	
	public void setGroupName(String name) {
		this.groupName = name;
	}
	
	public boolean newGroup() {
		sqlRetrieve sqlret = new sqlRetrieve("SELECT gruppenavn FROM gruppe WHERE gruppenavn = '"
				+ this.groupName + "';");
		if (sqlret.getQuery().length == 1) {
			System.out.println("Gruppen eksisterer allerede!");
			return false;
		}
		sqlexec.execute("INSERT INTO gruppe (gruppenavn) VALUES ('" + this.groupName +  "')");
		System.out.println("Gruppen ble opprettet!");
		return true;
	}
	
	public boolean editGroupName(String name) {
		if (name.length() == 0) {
			System.out.println("Du maa velge et gyldig gruppenavn!");
			return false;
		}
		sqlexec.execute("UPDATE gruppe SET gruppenavn = '" + name + "' WHERE gruppenavn = '"
				+ this.groupName + "';");
		System.out.println("Gruppenavnet ble endret!");
		return true;
	}
	
	public boolean deleteGroup() {
		sqlRetrieve sqlret = new sqlRetrieve("SELECT gruppenavn FROM gruppe WHERE gruppenavn = '"
				+ this.groupName + "';");
		if (sqlret.getQuery().length == 0) {
			System.out.println("Gruppen eksisterer ikke!");
			return false;
		}
		sqlexec.execute("DELETE FROM gruppe WHERE gruppenavn ='" + this.groupName +  "';");
		System.out.println("Gruppen ble slettet!");
		return true;
	}
	
	public void listGroups() {
		sqlRetrieve sqlret = new sqlRetrieve("SELECT gruppenavn FROM gruppe;");
		
		for (int i = 0; i < sqlret.getQuery().length; i++) {
			System.out.println(sqlret.getQuery()[i][0]);
		} 
	}
	
	public void listUsers() {
		sqlRetrieve sqlret = new sqlRetrieve("SELECT bruker.fornavn, bruker.etternavn, bruker.brukernavn "
				+ "FROM bruker "
				+ "JOIN bruker_has_Gruppe ON bruker_has_Gruppe.bruker_brukerid = bruker.brukerid "
				+ "JOIN gruppe ON gruppe.gruppeid = bruker_has_Gruppe.Gruppe_gruppeid "
				+ "WHERE gruppenavn =  '" + this.groupName + "'");
		
		for (int i = 0; i < sqlret.getQuery().length; i++) {

				System.out.println(sqlret.getQuery()[i][0] + 
						sqlret.getQuery()[i][1] + sqlret.getQuery()[i][2]);
		}
		
	}
	
	public boolean addUser(String username) {
		
		sqlRetrieve sqlret1 = new sqlRetrieve("SELECT gruppeid FROM gruppe WHERE gruppenavn = '"
				+ this.groupName + "';");
		sqlRetrieve sqlret2 = new sqlRetrieve("SELECT brukerid FROM bruker WHERE brukernavn = '"
				+ username + "';");
		sqlRetrieve sqlret3 = new sqlRetrieve("SELECT * FROM bruker_has_Gruppe WHERE bruker_brukerid = "
				+ sqlret2.getQuery()[0][0] + " AND Gruppe_gruppeid = " + sqlret1.getQuery()[0][0]);
		
		if (sqlret3.getQuery().length == 1) {
			System.out.println("Brukeren ligger allerede i gruppen!");
			return false;
		}
		sqlexec.execute("INSERT INTO bruker_has_Gruppe (bruker_brukerid, Gruppe_gruppeid) VALUES ('"
				+ sqlret2.getQuery()[0][0] + "', '" + sqlret1.getQuery()[0][0] +  "');");
		System.out.println("Brukeren ble lagt til i gruppen!");
		return true;
	}
	
	public boolean removeUser(String username) {
		sqlRetrieve sqlret1 = new sqlRetrieve("SELECT gruppeid FROM gruppe WHERE gruppenavn = '"
				+ this.groupName + "';");
		sqlRetrieve sqlret2 = new sqlRetrieve("SELECT brukerid FROM bruker WHERE brukernavn = '"
				+ username + "';");
		sqlRetrieve sqlret3 = new sqlRetrieve("SELECT * FROM bruker_has_Gruppe WHERE bruker_brukerid = "
				+ sqlret2.getQuery()[0][0] + " AND Gruppe_gruppeid = " + sqlret1.getQuery()[0][0]);
		if (sqlret3.getQuery().length == 0) {
			System.out.println("Brukeren ligger ikke i gruppen!");
			return false;
		}
		sqlexec.execute("DELETE FROM bruker_has_Gruppe WHERE bruker_brukerid = '"
			+ sqlret2.getQuery()[0][0] + "' AND Gruppe_gruppeid = '" + sqlret1.getQuery()[0][0] + "';");
		System.out.println("Brukeren ble fjernet fra gruppen!");
		return true;
	}
	
	
	public static void main(String[] args) {
		Group group = new Group("Konsulenter");
		//group.newGroup();
		//group.setGroupName("blablalba");
		//group.newGroup();
		//group.editGroupName("den beste gruppen");
		//group.listUsers();
		//group.listGroups();
		group.addUser("KimHD");
		group.listUsers();
		//group.removeUser("braged");
		//group.deleteGroup();
		
	}
	
}
