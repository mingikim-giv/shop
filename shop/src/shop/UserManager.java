package shop;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	
	// searchId
	public int searchId(String id) {
		for(int i = 0; i < list.size(); i ++) {
			User user = list.get(i);
			if(id.equals(user.getId())) {
				return i;
			}
		}
		return -1;
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
	// D.
	public void removeUser(int idx) {
		if(idx < 0 || idx >= list.size()) {
			System.err.println("유효하지 않은 범위입니다.");
			return;
		}
		list.remove(idx);
	}
}
