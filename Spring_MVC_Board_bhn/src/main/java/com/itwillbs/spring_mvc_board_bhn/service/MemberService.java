package com.itwillbs.spring_mvc_board_bhn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.spring_mvc_board_bhn.mapper.MemberMapper;
import com.itwillbs.spring_mvc_board_bhn.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberMapper mapper;

	public int registMember(MemberVO member) {
		return mapper.insertMember(member);
	}

	public String getpasswd(String id) {
		return mapper.selectPasswd(id);
	}

	public MemberVO getMemberInfo(String id) {
		return mapper.selectMemberInfo(id);
	}

	public int updateMemberInfo(MemberVO member, String newPasswd) {
		return mapper.updateMemberInfo(member, newPasswd);
	}

	public int quitMember(String id) {
		return mapper.deleteMember(id);
	}
}
