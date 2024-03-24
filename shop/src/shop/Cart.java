package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;
	private ItemManager item = ItemManager.getInstance();
	
	public Cart() {
		list = new ArrayList<>();
	}
	
	// searchItemName
	public int searchItemName(String name) {
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			if(item.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	// printCart
	public void printCart() {
		if(list.size() == 0) {
			System.err.println("장바구니가 비었습니다.");
			return;
		}
		for(int i = 0; i < list.size(); i ++) {
			Item item = list.get(i);
			System.out.printf("%d)%s(%s개)\n", i+1, item.getName(), item.getCnt()); 
		}
	}
	// total
	public int total() {
		int total = 0;
		for(int i = 0; i < list.size(); i ++) {
			Item basket = list.get(i);
			for(int j = 0; j < item.size(); j ++) {
				Item info = item.getItem(j);
				if(basket.getName().equals(info.getName())) {
					total += info.getPrice()*basket.getCnt();
				}
			}
		}
		return total;
	}
	// C.
	public void addItem(String name, int cnt) {
		int idx = searchItemName(name);
		
		if(idx == -1) {
			Item item = new Item();
			item.setName(name);
			item.setCnt(cnt);
			
			list.add(item);
			return;
		}
		
		int piece = list.get(idx).getCnt();
		list.get(idx).setCnt(piece+cnt);
		return;
	}
	// R.
	public Item getCart(int idx) {
		Item item = list.get(idx);
		return item;
	}
	// U.
	public void setCart(String name, int cnt) {
		int idx = searchItemName(name);
		
		if(idx < 0 || idx >= list.size()) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		list.get(idx).setCnt(cnt);
	}
	// D.
	public void removeItem(String name) {
		int idx = searchItemName(name);
		
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		list.remove(idx);
	}
	public void removeCart() {
		list.clear();
	}
	// size
	public int cartSize() {
		return list.size();
	}
}
