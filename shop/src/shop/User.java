package shop;

public class User {
	private Cart cart;
	private String id, pw;
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
		this.cart = new Cart();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	public void addItem(Item item) {
		cart.addItem(item);
	}
	public void setCart(Cart cart) {
		this.cart = cart;
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
