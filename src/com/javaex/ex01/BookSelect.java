package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {
	
	public static void main(String[] args) {

		List<BookVo> bookList = new ArrayList<BookVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  book_id, ";
			query += " 		   title, ";
			query += " 		   pubs, ";
			query += " 		   pub_date, ";
			query += " 		   author_id ";
			query += " from    book ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			// select문은 update가 아니라 query!!
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int book_id = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pub_date = rs.getString(4);
				int author_id = rs.getInt(5);
				
				BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_id);
				
				bookList.add(vo);
			}
		
			for (int i = 0; i < bookList.size(); i++) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBook_id() + ", " + bookVo.getTitle() + ", " + bookVo.getPubs() + ", " + bookVo.getPub_date() + ", " + bookVo.getAuthor_id());
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}
}
