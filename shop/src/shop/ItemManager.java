package shop;

import java.util.ArrayList;

public class ItemManager {
	private static ArrayList<Item> list;
	private static ItemManager instance = new ItemManager();
	
	public ItemManager() {
		list = new ArrayList<>();
	}
	
	public static ItemManager getInstance() {
		return instance;
	}
	
	// searchItem
	public boolean searchItem(String name) {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			if(item.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	// printItem
	public void printItem() {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			System.out.printf("%s: %d원\n", item.getName(), item.getPrice());
		}
	}
	
	// C.
	public void addItem(String name, int price) {
		if(searchItem(name)) {
			System.err.println("중복된 아이템 이름입니다.");
			return;
		}
		Item item = new Item(name, price);
		list.add(item);
	}
	// R.
	public Item getItem(int idx) {
		Item item = list.get(idx);
		return item.clone();
	}
	// U.
	public Item updateItem(int idx, Item item) {
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
