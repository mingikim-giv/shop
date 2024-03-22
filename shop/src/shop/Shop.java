package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	private Scanner scan = new Scanner(System.in);
	private UserManager userManager = new UserManager();
	private ItemManager itemManager = new ItemManager();
	
	private static ArrayList<User> list = new ArrayList<>();
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int SHOP = 5;
	private final int MY_PAGE = 6;
	private final int FILE = 7;
	private final int ADMIN = 8;
	
	public static int log = -1;
	private String brand;

	public Shop(String brand) {
		this.brand = brand;
	}
	
	// print
	private void print() {
		userManager.printUserId();	// 검수용
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
		if(sel == JOIN) {
			join();
		}
		else if(sel == LEAVE) {
			
		}
		else if(sel == LOGIN) {
			login();
		}
		else if(sel == LOGOUT) {
			logout();
		}
		else if(sel == SHOP) {
			
		}	
		else if(sel == MY_PAGE) {
			
		}
		else if(sel == FILE) {
			
		}
		else if(sel == ADMIN) {
			
		}
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
			runMenu(inputNumber("menu"));
		}
	}
}
