package User;

public class User {

	private String Name;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setName("Andre");
		System.out.println(user.getName());
	}

}
