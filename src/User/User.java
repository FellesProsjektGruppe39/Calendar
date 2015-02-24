package User;

public class User {

	private String Name;
	
	
	
	
	public String getName() {
		return this.Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	
	public static void main(String[] args) {
		
		CreateUser Per = new CreateUser(100, "Pake", "Persson", 78978978, "NRK", "perp", "Daglig leder", 0);
		
	}

}
