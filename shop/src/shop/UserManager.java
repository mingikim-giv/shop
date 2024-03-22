package shop;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list;
	private static UserManager instance = new UserManager();
	
	public UserManager() {
		list = new ArrayList<>();
		User admin = new User("admin", "1234");
		list.add(admin);
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	// searchId
	public int searchId(String id) {
		for(int i = 0; i < list.size(); i ++) {
			User user = list.get(i);
			if(user.getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	// printUserId
	public void printUserId() {
		for(int i = 0; i < list.size(); i ++) {
			User user = list.get(i);
			System.out.printf("%s/%s\n", user.getId(), user.getPw());
		}
	}
	
	// addItem
	public void addItem(int idx, Item item) {
		User user = getUser(idx);
		user.getCart().addItem(item);
	}
	
	// C.
	public void addUser(String id, String pw) {
		User user = new User(id ,pw);
		list.add(user);
	}
	// R.
	public User getUser(int idx) {
		User user = list.get(idx);
		return user.clone();
	}
	// U.
	public User updateUser(int idx, User user) {
		return list.set(idx, user);
	}
	// D.
	public void removeUser(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		list.remove(idx);
	}
}
