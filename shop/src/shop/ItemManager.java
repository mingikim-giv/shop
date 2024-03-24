package shop;

import java.util.ArrayList;

public class ItemManager {
	private  ArrayList<Item> list;
	private static ItemManager instance = new ItemManager();
	
	public ItemManager() {
		list = new ArrayList<>();
	}
	
	public static ItemManager getInstance() {
		return instance;
	}
	
	// searchItem
	public int searchItem(String name) {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			if(item.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	// findItem
	public Item findItem(String name) {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			if(item.getName().equals(name)) {
				return item;
			}
		}
		return new Item();
	}
	
	// isDuplItem
	public boolean isDuplItem(String name) {
		Item item = findItem(name);
		if(item.getName() == null) {
			return true;
		}
		return false;
	}
	
	// printItem
	public void printItem() {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			System.out.printf("%d)%s\n", i+1, item);
		}
	}
	
	// C.
	public Item addItem(String name, int price) {
		if(isDuplItem(name)) {
			Item item = new Item(name, price);
			list.add(item);
			return item;
		}
		return new Item();
	}
	// R.
	public Item getItem(int idx) {
		Item item = list.get(idx);
		return item;
	}
	// U.
	public Item setItem(int idx, Item item) {
		return list.set(idx, item);
	}
	// D.
	public void removeItem(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		list.remove(idx);
	}
	// size
	public int size() {
		return list.size();
	}
}
