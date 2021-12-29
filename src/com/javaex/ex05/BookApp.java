package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		BookDao bookDao = new BookDao();
		List<BookVo> bookList;

		bookDao.BookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 2);
		bookDao.BookInsert("삼국지", "민음사", "2002-03-01", 2);
		bookDao.BookInsert("토지", "마로니에북스", "2012-08-15", 3);

		bookList = bookDao.BookSelect();

		System.out.println("-----------------------------------------------");

		for (int i = 0; i < bookList.size(); i++) {
			BookVo bo = bookList.get(i);
			System.out.println(bo.getBook_id() + ", " + bo.getTitle() + ", " + bo.getPubs() + ", " + bo.getPub_date()
					+ ", " + bo.getAuthor_id() + ", " + bo.getAuthor_name() + ", " + bo.getAuthor_desc());

		}

		bookDao.BookUpdate(3, "오직두사람", "문학동네", "2017-05-04", 4);
		bookList = bookDao.BookSelect();

		System.out.println("-----------------------------------------------");

		for (int i = 0; i < bookList.size(); i++) {
			BookVo bo = bookList.get(i);
			System.out.println(bo.getBook_id() + ", " + bo.getTitle() + ", " + bo.getPubs() + ", " + bo.getPub_date()
					+ ", " + bo.getAuthor_id() + ", " + bo.getAuthor_name() + ", " + bo.getAuthor_desc());

		}

		bookDao.BookDelete(1);
		bookList = bookDao.BookSelect();

		System.out.println("-----------------------------------------------");

		for (int i = 0; i < bookList.size(); i++) {
			BookVo bo = bookList.get(i);
			System.out.println(bo.getBook_id() + ", " + bo.getTitle() + ", " + bo.getPubs() + ", " + bo.getPub_date()
					+ ", " + bo.getAuthor_id() + ", " + bo.getAuthor_name() + ", " + bo.getAuthor_desc());

		}

	}

}
