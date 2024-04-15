package shop;

public class Main {

	public static void main(String[] args) {
		Shop shop = new Shop("Mega");
		shop.run();
		
		// Shop Project
		// class : Item, ItemManager, User, UserManager, Cart, Shop, FileManager
		
		// method :
		
		// 유저 -
		// ㄴ 회원가입/탈퇴
		// ㄴ 로그인/로그아웃
		// ㄴ 쇼핑하기
		// ㄴ 마이페이지(내 장바구니, 항목 삭제, 수령 수정, 결제)
		// ㄴ 자동 저장/ 자동 로드
		
		// 관리자 -
		// ㄴ 아이템 등록/삭제/수정
		// ㄴ 조회(총 매출)
	}

}

// 유저 - 
// ㄴ 회원가입					[O]
// ㄴ 탈퇴						[O]
// ㄴ 로그인					[O]
// ㄴ 로그아웃					[O]
// ㄴ 쇼핑하기					[O]
// ㄴ 마이페이지
//   ㄴ 내장바구니				[O]
//   ㄴ 항목삭제				[O]
//   ㄴ 수량수정				[O]
//   ㄴ 결제					[O]
// 파일 
// ㄴ 자동저장					[O]
// ㄴ 자동로드					[O]

// 관리자 -
// ㄴ 아이템		
//   ㄴ 등록					[O]
//   ㄴ 삭제					[O]
//   ㄴ 수정					[O]
// ㄴ 조회(총 매출)				[O]