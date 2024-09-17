package com.kh.mybatis.member.model.dao;

import java.sql.PreparedStatement;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	
	//회원가입 메소드
	public int insertMember(SqlSession sqlSession, Member m) {
		
		/*
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("키");
		
		pstmt = conn.~~~
		*/
		
		/*
		 * sqlSession 에서 제공하는 메소드를 통해 sql문을 찾아 실행하고 결과를 받아볼 수 있다.
		 * 해당 sql문이 미완성 상태가 아니라면 sql문을 완성시킬 객체는 생략가능
		 * */
//		int result = sqlSession.insert("memberMapper.insertMember", m);
//		
//		return result;
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	//로그인용 메소드
	public Member loginMember(SqlSession sqlSession, Member m) {

		return sqlSession.selectOne("memberMapper.selectMember", m);
	}
	
	
	
	
	

}
