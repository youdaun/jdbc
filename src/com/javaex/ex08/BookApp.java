package com.javaex.ex08;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {

		List<BookVo> bookList;
		
		//작가 Dao
		AuthorDao authordao = new AuthorDao();	
		
		//작가 추가
		AuthorVo avo01 = new AuthorVo("박경리", "토지 작가");
		authordao.authorInsert(avo01);
		
		AuthorVo avo02 = new AuthorVo("이문열", "경북 영양");
		authordao.authorInsert(avo02);
		
		AuthorVo avo03 = new AuthorVo("이고잉", "고잉 고잉");
		authordao.authorInsert(avo03);
		
		AuthorVo avo04 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authordao.authorInsert(avo04);
		
		AuthorVo avo05 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authordao.authorInsert(avo05);
		
		AuthorVo avo06 = new AuthorVo("김영하", "알쓸신잡");
		authordao.authorInsert(avo06);
		
		//책 Dao
		BookDao bookdao = new BookDao();
		
		//책 추가
		BookVo bvo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 2);
		bookdao.BookInsert(bvo01);
		
		BookVo bvo02 = new BookVo("삼국지", "민음사", "2002-03-01", 2);
		bookdao.BookInsert(bvo02);
		
		BookVo bvo03 = new BookVo("토지", "마로니에북스", "2012-08-15", 1);
		bookdao.BookInsert(bvo03);
		
		BookVo bvo04 = new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 3);
		bookdao.BookInsert(bvo04);
		
		BookVo bvo05 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookdao.BookInsert(bvo05);
		
		BookVo bvo06 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookdao.BookInsert(bvo06);
		
		BookVo bvo07 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookdao.BookInsert(bvo07);
		
		BookVo bvo08 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookdao.BookInsert(bvo08);
		
		//검색어 입력 Scanner()
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색어를 입력하세요(한글자):");
		String keyword = sc.nextLine();
		
		//책 검색메소드 호출
		bookList = bookdao.Search(keyword);
		
		//출력
		System.out.println("-----------------------------------------------");

		for (int i = 0; i < bookList.size(); i++) {
			BookVo bo = bookList.get(i);
			System.out.println(bo.getBook_id() + ", " + bo.getTitle() + ", " + bo.getPubs() + ", " + bo.getPub_date()
					+ ", " + bo.getAuthor_name());

		}
		
		sc.close();

	}

}
