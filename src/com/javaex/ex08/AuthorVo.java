package com.javaex.ex08;

public class AuthorVo {
	
	private int authorId;
	private String athorName;
	private String athorDesc;

	public AuthorVo(int authorId, String athorName, String athorDesc) {
		this.authorId = authorId;
		this.athorName = athorName;
		this.athorDesc = athorDesc;
	}
	
	public AuthorVo(String athorName, String athorDesc) {
		this.athorName = athorName;
		this.athorDesc = athorDesc;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAthorName() {
		return athorName;
	}

	public void setAthorName(String athorName) {
		this.athorName = athorName;
	}

	public String getAthorDesc() {
		return athorDesc;
	}

	public void setAthorDesc(String athorDesc) {
		this.athorDesc = athorDesc;
	}
}
