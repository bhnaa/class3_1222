package com.itwillbs.spring_mvc_board_bhn.vo;

import java.sql.Date;

public class MemberVO {
	private int idx;
	private String name;
	private String id;
	private String passwd;
	private String email;
	// xml에서 이메일을 concat 하도록 나눠놓음
	private String email1;
	private String email2;
	private String gender;
	private Date date;
	private String auth_status;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getGender() {
		return gender;
	}
	
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", name=" + name + ", id=" + id + ", passwd=" + passwd + ", email=" + email
				+ ", email1=" + email1 + ", email2=" + email2 + ", gender=" + gender + ", date=" + date
				+ ", auth_status=" + auth_status + "]";
	}
	
	
	
	
	
	
	
	
	
}













