package com.itwillbs.spring_mvc_board_bhn.vo;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

/* 
 * ------------------------------------
 글번호(board_num) : 정수, PK
 작성자(board_name) : 문자 20글자, NN
 패스워드(board_pass) : 문자 16글자, NN
 제목(board_subject) : 문자 50글자, NN
 내용(board_content) : 문자 2000글자, NN
 파일명(board_file) : 문자 50글자, NN
 업로드경로(board_file_path) : 문자 50글자, NN
 참조글번호(board_re_ref) : 정수, NN
 들여쓰기레벨(board_re_lev) : 정수, NN
 순서번호(board_re_seq) : 정수, NN
 조회수(board_readcount) : 정수, NN
 작성일(board_date) : 날짜 및 시각(DATETIME), NN
 --------------------------------------
 
 *  USE mvc_board3;
  
 	CREATE TABLE board (
		BOARD_NUM INT PRIMARY KEY,
		BOARD_NAME VARCHAR(20) NOT NULL,
		BOARD_PASS VARCHAR(16) NOT NULL, // 패스워드 삭제함
		BOARD_SUBJECT VARCHAR(50) NOT NULL,
		BOARD_CONTENT VARCHAR(2000) NOT NULL,
		BOARD_FILE VARCHAR(200) NOT NULL,
		board_file_path varchar(50) not null,
		BOARD_RE_REF INT NOT NULL,
		BOARD_RE_LEV INT NOT NULL,
		BOARD_RE_SEQ INT NOT NULL,
		BOARD_READCOUNT INT NOT NULL,
		BOARD_DATE DATETIME
	);

	DESC board;
 */


public class BoardVO {
	private int board_num;
	private String board_name;
	private String board_subject;
	private String board_content;
	private String board_file; // 파일 이름을 가져옴
	private String board_file_path; // 파일 업로드 경로
	private int board_re_ref;
	private int board_re_lev;
	private int board_re_seq;
	private int board_readcount;
	private Timestamp board_date;
	private MultipartFile file; // 파일을 가져옴
//	private MultipartFile[] files; // 복수개의 파일 업로드 시 배열로 선언
	
	


	public int getBoard_num() {
		return board_num;
	}


	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}




	public String getBoard_name() {
		return board_name;
	}




	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}



	public String getBoard_subject() {
		return board_subject;
	}




	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}




	public String getBoard_content() {
		return board_content;
	}




	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}




	public String getBoard_file() {
		return board_file;
	}




	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}




	public String getBoard_file_path() {
		return board_file_path;
	}


	public void setBoard_file_path(String board_file_path) {
		this.board_file_path = board_file_path;
	}


	public int getBoard_re_ref() {
		return board_re_ref;
	}




	public void setBoard_re_ref(int board_re_ref) {
		this.board_re_ref = board_re_ref;
	}




	public int getBoard_re_lev() {
		return board_re_lev;
	}




	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}




	public int getBoard_re_seq() {
		return board_re_seq;
	}




	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}




	public int getBoard_readcount() {
		return board_readcount;
	}




	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}




	public Timestamp getBoard_date() {
		return board_date;
	}




	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}

	
	
	
	public MultipartFile getFile() {
		return file;
	}




	public void setFile(MultipartFile file) {
		this.file = file;
	}


	@Override
	public String toString() {
		return "BoardVO [board_num=" + board_num + ", board_name=" + board_name + ", board_subject=" + board_subject
				+ ", board_content=" + board_content + ", board_file=" + board_file + ", board_file_path="
				+ board_file_path + ", board_re_ref=" + board_re_ref + ", board_re_lev=" + board_re_lev
				+ ", board_re_seq=" + board_re_seq + ", board_readcount=" + board_readcount + ", board_date="
				+ board_date + ", file=" + file + "]";
	}




	




	




	
	
	
	
	
	
	
}





















