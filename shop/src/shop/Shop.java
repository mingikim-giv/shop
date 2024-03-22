package shop;

import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager userManager = new UserManager();
	private ItemManager itemManager = new ItemManager();
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOP = 5;
	private final int MY_PAGE = 6;
	private final int FILE = 7;
	private final int ADMIN = 8;
	
	private final int MYBASKET = 1;
	private final int DELETE = 2;
	private final int ITEM_COUNT_CHANGE = 3;
	private final int PAYMENT = 4;
	
	private final int ITEM_ENROLL = 1;
	private final int ITEM_DELETE = 2;
	private final int ITEM_CHANGE = 3;
	private final int SALES_VIEW = 4;
	
	public static int log = -1;
	private String brand;

	public Shop(String brand) {
		this.brand = brand;
	}
	
	// print
	private void print() {
		userManager.printUserId();	// 검수용
		itemManager.printItem();	// 검수용
		System.out.println("===" + brand + "===");
		System.out.println("[1]회원가입");
		System.out.println("[2]회원탈퇴");
		System.out.println("[3]로그인");
		System.out.println("[4]로그아웃");
		System.out.println("[5]쇼핑");
		System.out.println("[6]마이 페이지");
		System.out.println("[7]파일");
		System.out.println("[8]관리자 모드");
	}
	// runMenu
	private void runMenu(int sel) {
		if(sel == JOIN && !isLogin()) {
			join();
		}
		else if(sel == LEAVE && isLogin()) {
			
		}
		else if(sel == LOGIN && !isLogin()) {
			login();
		}
		else if(sel == LOGOUT) {
			logout();
		}
		else if(sel == SHOP && isLogin()) {
			
		}	
		else if(sel == MY_PAGE && isLogin()) {
			myPageSubMenu();
			myPageRunMenu(inputNumber("메뉴"));
		}
		else if(sel == FILE) {
			
		}
		else if(sel == ADMIN && checkAdmin()) {
			adminSubMenu();
			adminRunMenu(inputNumber("메뉴"));
		}
	}
	
	// isLogin
	private boolean isLogin() {
		return log == -1 ? false : true;
	}
	
	// checkAdmin
	private boolean checkAdmin() {
		if(log != 0) {
			System.err.println("관리자만 이용 가능합니다.");
			return false;
		}
		return true;
	}
	
	// join
	private void join() {
		String id = inputString("ID");
		int check = userManager.searchId(id);
		
		if(check != -1) {
			System.err.println("중복된 아이디입니다.");
			return;
		}
		else {
			String pw = inputString("PW");
			userManager.addUser(id, pw);
			System.out.println("회원 가입 완료");
		}
	}
	
	// login
	private void login() {
		String id = inputString("ID");
		int idx = userManager.searchId(id);
		
		if(idx == -1) {
			System.err.println("회원 정보를 다시 입력해주세요.");
			return;
		}
		
		String pw = inputString("PW");
		User user = userManager.getUser(idx);
		if(pw.equals(user.getPw())) {
			log = idx;
			System.out.println("로그인 성공");
		}
	}
	
	// logout
	private void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	// myPageSubMenu
	private void myPageSubMenu() {
		System.out.println("[1]장바구니");
		System.out.println("[2]항목 삭제");
		System.out.println("[3]수량 수정");
		System.out.println("[4]결제");
	}
	

	// myPageRunMenu
	private void myPageRunMenu(int sel) {
		if(sel == MYBASKET) {
			
		}
		else if(sel == DELETE) {
			
		}
		else if(sel == ITEM_COUNT_CHANGE) {
			
		}
		else if(sel == PAYMENT) {
			
		}
	}
	// adminSubMenu
	private void adminSubMenu() {
		System.out.println("[1]아이템 등록");
		System.out.println("[2]아이템 삭제");
		System.out.println("[3]아이템 수정");
		System.out.println("[4]총매출 조회");
	}

	// adminRunMenu
	private void adminRunMenu(int sel) {
		if(sel == ITEM_ENROLL) {
			itemEnroll();
		}
		else if(sel == ITEM_DELETE) {
			deleteItem();
		}
		else if(sel == ITEM_CHANGE) {
			
		}
		else if(sel == SALES_VIEW) {
			
		}
	}
	
	// itemEnroll
	private void itemEnroll() {
		String name = inputString("아이템명");
		int price = inputNumber("아이템 가격");
		
		itemManager.addItem(name, price);
	}
	
	// printItem
	private void printItem() {
		for(int i = 0; i < itemManager.size(); i ++) {
			System.out.printf("%d)%s\n", i+1, itemManager.getItem(i));
		}
	}
	
	// deleteItem
	private void deleteItem() {
		printItem();
		int idx = inputNumber("삭제할 번호")-1;
		
		if(idx < 0 || idx >= itemManager.size()) {
			return;
		}
		
		itemManager.removeItem(idx);
	}
	
	// input
	private int inputNumber(String message) {
		int number = -1;
		
		try {
			System.out.println(message + ":");
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자를 입력하세요.");
		}
		
		return number;
	}
	
	private String inputString(String message) {
		System.out.println(message + ":");
		return scan.next();
	}
	
	public void run() {
		while(true) {
			print();
			runMenu(inputNumber("메뉴 선택"));
		}
	}
}
