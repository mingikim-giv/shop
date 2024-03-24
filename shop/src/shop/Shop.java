package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager userManager = UserManager.getInstance();
	private ItemManager itemManager = ItemManager.getInstance();
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOP = 5;
	private final int MY_PAGE = 6;
	private final int ADMIN = 7;
	private final int END = 8;
	
	private final int MYBASKET = 1;
	private final int DELETE = 2;
	private final int ITEM_COUNT_CHANGE = 3;
	private final int PAYMENT = 4;
	
	private final int ITEM_ENROLL = 1;
	private final int ITEM_DELETE = 2;
	private final int ITEM_CHANGE = 3;
	private final int SALES_VIEW = 4;
	
	private int log;
	private int sale;
	
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	private String fileName = "mega.txt";
	private File file = new File(fileName);
	
	private boolean isEnd;
	
	private String brand;
	
	public Shop(String brand) {
		this.brand = brand;
		log = -1;
	}
	
	// print
	private void print() {
		System.out.println("===" + brand + "===");
		System.out.println("[1]회원가입");
		System.out.println("[2]회원탈퇴");
		System.out.println("[3]로그인");
		System.out.println("[4]로그아웃");
		System.out.println("[5]쇼핑");
		System.out.println("[6]마이 페이지");
		System.out.println("[7]관리자 모드");
		System.out.println("[8]종료");
	}
	
	// runMenu
	private void runMenu(int sel) {
		if(sel == JOIN && !isLogin()) {
			join();
		}
		else if(sel == LEAVE && isLogin()) {
			leave();
		}
		else if(sel == LOGIN && !isLogin()) {
			login();
		}
		else if(sel == LOGOUT) {
			logout();
		}
		else if(sel == SHOP && isLogin()) {
			shop();
		}	
		else if(sel == MY_PAGE && isLogin()) {
			myPageSubMenu();
			myPageRunMenu(inputNumber("메뉴"));
		}
		else if(sel == ADMIN && checkAdmin()) {
			adminSubMenu();
			adminRunMenu(inputNumber("메뉴"));
		}
		else if(sel == END) {
			end();
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
	
	// leave
	private void leave() {
		if(log == 0) {
			System.err.println("관리사측 문의해주세요.");
			return;
		}
		
		String pw = inputString("PW");
		
		if(userManager.getUser(log).getPw().equals(pw)) {
			userManager.removeUser(log);
			log = -1;
			System.out.println("회원 탈퇴 완료");
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
			System.out.printf("%s님 로그인 성공\n", user.getId());
		}
	}
	
	// logout
	private void logout() {
		log = -1;
		System.out.println("로그아웃 완료");
	}
	
	// shop
	private void shop() {
		itemManager.printItem();
		
		String name = inputString("구매할 아이템 이름");
		int idx = itemManager.searchItem(name);
		
		if(idx == -1) {
			System.err.println("존재하지 않는 아이템입니다.");
			return;
		}
		
		int cnt = inputNumber("구매할 수량");
		
		if(cnt < 1) {
			System.err.println("1개 이상 부터 변경 가능합니다.");
			return;
		}
		
		Cart cart = userManager.getUser(log).getCart();
		cart.addItem(name, cnt);
		System.out.println("쇼핑 완료");
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
			myBasket();
		}
		else if(sel == DELETE) {
			delete();
		}
		else if(sel == ITEM_COUNT_CHANGE) {
			changeItemCount();
		}
		else if(sel == PAYMENT) {
			payment();
		}
	}
	
	// myBasket
	private void myBasket() {
		User user = userManager.getUser(log);
		
		if(user.isCartEmpty()) {
			System.err.println("장바구니가 비었습니다.");
			return;
		}
		
		user.getCart().printCart();
	}
	
	// delete
	private void delete() {
		myBasket();
		
		String name = inputString("아이템 이름");
		
		User user = userManager.getUser(log);
		user.getCart().removeItem(name);;
		System.out.println("항목 삭제 완료");
	}
	
	// changeItemCount
	private void changeItemCount() {
		myBasket();
		String name = inputString("아이템 이름");
		int cnt = inputNumber("아이템 개수");
		
		if(cnt < 1) {
			System.err.println("1개 이상 부터 변경 가능합니다.");
			return;
		}
		
		User user = userManager.getUser(log);
		user.getCart().setCart(name, cnt);
		System.out.println("수량 변경 완료");
	}
	
	// payment
	private void payment() {
		myBasket();
		
		int money = inputNumber("가격");
		User user = userManager.getUser(log);
		
		int total = user.getCart().total();
		
		if(money < total) {
			System.err.println("금액이 부족합니다.");
			return;
		}
		sale += total;
		user.getCart().removeCart();
		System.out.println("결제 완료");
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
			changeItem();
		}
		else if(sel == SALES_VIEW) {
			viewSale();
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
		int number = inputNumber("삭제할 번호")-1;
		if(number < 0 || number >= itemManager.size()) {
			System.err.println("번호를 다시 입력해주세요.");
			return;
		}
		
		itemManager.removeItem(number);
		System.out.println("삭제 완료");
	}
	
	// changeItem
	private void changeItem() {
		printItem();
		
		String name = inputString("수정할 아이템 이름");
		int idx = itemManager.searchItem(name);
		if(idx == -1) {
			System.err.println("존재하지 않는 상품입니다.");
			return;
		}
		
		int price = inputNumber("수정할 가격");
		if(price < 1) {
			System.err.println("수정할 가격은 1보다 큰 수 입니다.");
			return;
		}
		
		Item item = itemManager.getItem(idx);
		item.setPrice(price);
		
		itemManager.setItem(idx, item);
		System.out.println("수정 완료");
	}
	
	// viewSale
	private void viewSale() {
		System.out.printf("총 매출액: %d원\n", sale);
	}
	
	// save
	private void save() {
		String data = createData();
		
		try {
			fw = new FileWriter(file);
			
			fw.write(data);
			
			fw.close();
			System.out.println("파일 저장 성공");
		} catch (Exception e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	// createData
	private String createData() {
		/*
		 * 아이템/가격
		 * id/pw/아이템/개수
		 * id/pw
		 * 총 매출
		 */
		String data = "";
		
		// item
		for(int i = 0; i < itemManager.size(); i ++) {
			Item item = itemManager.getItem(i);
			String name = item.getName();
			int price = item.getPrice();
			data += name + "/" + price;
			
			if(i < itemManager.size()-1) {
				data += "/";
			}
		}
		data += "\n";
		
		// user
		for(int i = 0; i < userManager.size(); i ++) {
			User user = userManager.getUser(i);
			String id = user.getId();
			String pw = user.getPw();
			data += id + "/" + pw;
			
			Cart cart = user.getCart();
			if(cart.cartSize() > 0) {
				for(int j = 0; j < cart.cartSize(); j ++) {
					data += "/";
					String name = cart.getCart(j).getName();
					int price = cart.getCart(j).getPrice();
					data += name + "/" + price;
				}
			}
			data += "\n";
		}
		data += sale;
		return data;
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
	
	private void end() {
		isEnd = true;
		save();
	}
	
	private boolean isRun() {
		return !isEnd;
	}
	
	public void run() {
		while(isRun()) {
			userManager.printUserId();	// 검수용
			print();
			runMenu(inputNumber("메뉴 선택"));
		}
	}
}
