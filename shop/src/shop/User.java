package shop;

public class User {
	public final String ADMIN = "admin";
	public final String ADMIN_PW = "1234";
	private Cart cart;
	private String id, pw;
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public User clone() {
		User user = new User(this.id, this.pw);
		return user;
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s", this.id, this.pw);
	}
}
