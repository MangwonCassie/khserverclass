package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {

	//회원가입용 메소드
	public abstract int insertMember(Member m);	
	//로그인용 메소드
	Member loginMember(Member m);
	//정보수정
	
	//회원탈퇴
	
	//로그아웃
	
}
