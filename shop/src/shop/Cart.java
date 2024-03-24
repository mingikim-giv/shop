package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> list;
	private ItemManager itemManager = ItemManager.getInstance();
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
			System.out.printf("%d) %s(%s원)\n", i+1, item.getName(), item.getPrice()); 
		}
		int total = total();
		System.out.printf("총 금액: %d원\n", total);
	}
	// total
	public int total() {
		int total = 0;
		
		for(int i = 0; i < list.size(); i ++) {
			Item basket = list.get(i);
			for(int j = 0; j < itemManager.size(); i ++) {
				Item item = itemManager.getItem(j);
				if(basket.getName().equals(item.getName())) {
					total += item.getPrice()*basket.getPrice();
				}
			}
		}
		return total;
	}
	// C.
	public void addItem(Item item) {
		list.add(item);
	}
	// R.
	public Item getItem(int idx) {
		Item item = list.get(idx);
		return item.clone();
	}
	// U.
	public void setCart(String name, int price) {
		int idx = searchItemName(name);
		
		if(idx < 0 || idx >= list.size()) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		list.get(idx).setPrice(price);
	}
	// D.
	public void removeCart(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		list.remove(idx);
	}
	// size
	public int cartSize() {
		return list.size();
	}
}
