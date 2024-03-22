package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;
	private String id;
	
	public Cart(String id) {
		this.id = id;
		list = new ArrayList<>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<Item> getList() {
		return this.list;
	}
	
	public void addItem(Item item) {
		this.list.add(item);
	}
}
