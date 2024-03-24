package shop;

public class Item {
	private String name;
	private int price, cnt, total;
	
	public Item() {
		
	}
	
	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getCnt() {
		return this.cnt;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public Item clone() {
		Item item = new Item(this.name, this.price);
		return item;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%d원", this.name, this.price);
	}
}
