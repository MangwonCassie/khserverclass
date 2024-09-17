package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{
	
	/*
	 * Connection conn = JDBCTemplate.getConnection();
	 * 
	 * 
	 * */
	
	
	//회원가입용 메소드 구현
	@Override
	public int insertMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new MemberDao().insertMember(sqlSession,m);
		
		if(result>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}

	//로그인용 메소드 구현
	@Override
	public Member loginMember(Member m) {

		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginUser = new MemberDao().loginMember(sqlSession,m);
		
		sqlSession.close();
		
		return loginUser;
	}

}
