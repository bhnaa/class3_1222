package com.itwillbs.spring_mvc_board_bhn.mapper;

import org.apache.ibatis.annotations.Param;

import com.itwillbs.spring_mvc_board_bhn.vo.MemberVO;

public interface MemberMapper {

	int insertMember(MemberVO member);

	String selectPasswd(String id);

	MemberVO selectMemberInfo(String id);

//	int updateMemberInfo(MemberVO member, String newPasswd);
	int updateMemberInfo(@Param("member") MemberVO member, @Param("newPasswd") String newPasswd);

	int deleteMember(String id);

}
