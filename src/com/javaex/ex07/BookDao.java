package com.javaex.ex07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}

	}

	private void close() {
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

	public void BookInsert(BookVo bookvo) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookvo.getTitle());
			pstmt.setString(2, bookvo.getPubs());
			pstmt.setString(3, bookvo.getPub_date());
			pstmt.setInt(4, bookvo.getAuthor_id());

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 저장되었습니다(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

	}

	public void BookDelete(int index) {

		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, index);

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();

	}

	public List<BookVo> BookSelect() {

		getConnection();
		List<BookVo> bookList = new ArrayList<BookVo>();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bo.book_id, ";
			query += " 		   bo.title, ";
			query += " 		   bo.pubs, ";
			query += " 		   bo.pub_date, ";
			query += " 		   au.author_id, ";
			query += " 		   au.author_name, ";
			query += " 		   au.author_desc ";
			query += " from    author au, book bo ";
			query += " where   au.author_id = bo.author_id ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			// select문은 update가 아니라 query!!
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				int au_author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");

				BookVo bookVo = new BookVo(book_id, title, pubs, pub_date, au_author_id, author_name, author_desc);
				bookList.add(bookVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();

		return bookList;

	}

	public void BookUpdate(int index, BookVo bookvo) {

		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set    title = ?, ";
			query += "        pubs = ?, ";
			query += "        pub_date = ?, ";
			query += "        author_id = ? ";
			query += " where  author_id = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookvo.getTitle());
			pstmt.setString(2, bookvo.getPubs());
			pstmt.setString(3, bookvo.getPub_date());
			pstmt.setInt(4, bookvo.getAuthor_id());
			pstmt.setInt(5, index);

			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();

	}

}
