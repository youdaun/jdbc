package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

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
			query += " select  author_id id, author_name, author_desc ";
			query += " from    author ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);

			// select문은 update가 아니라 query!!
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				/*
				 int author_id = rs.getInt("id"); //컴럼명이 별명일때는 별명으로 써줘여함			 
				 String author_name = rs.getString("author_name");
				 String author_desc = rs.getString("author_desc");
				 System.out.println(author_id + ", " + author_name + ", " + author_desc);
				 */

				int author_id = rs.getInt(1);
				String author_name = rs.getString(2);
				String author_desc = rs.getString(3);
				
				AuthorVo vo = new AuthorVo(author_id, author_name, author_desc);

				authorList.add(vo);
			}

			for (int i = 0; i < authorList.size(); i++) {
				AuthorVo authorVo = authorList.get(i);
				System.out.println(
						authorVo.getAuthorId() + ", " + authorVo.getAthorName() + ", " + authorVo.getAthorDesc());
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
