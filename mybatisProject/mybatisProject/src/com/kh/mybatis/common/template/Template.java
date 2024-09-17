package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	/*
	 * 기존 JDBC
	 * 
	 * public static Connection getConnection(){
	 * 		//driver.properties 파일 읽어서 DB접속 후 반환 
	 * }
	 * public static void close(JDBC용 객체){
	 * 		//반납구문
	 * }
	 *  
	 * public static void commit/rollback(Connection conn){
	 * 		//트랜잭션구문
	 * }
	 * */

	//마이바티스 
	
	public static SqlSession getSqlSession() {
		
		//mybatis-config.xml 파일을 읽어와서 sqlSession 객체 생성 후 반환
		
		SqlSession sqlSession = null;
		
		//SqlSession 객체를 생성하기 위해서는 SqlSessionFactory 객체가 필요
		//SqlSessionFactory 객체를 생성하기 위해선 SqlSessionFactoryBuilder 객체가 필요
		
		String resource = "/mybatis-config.xml";  // /는 최상위 소스폴더
		// 자바에서 클래스 패스를 기준으로 파일 경로를 지정할 때, "/"(슬래시)는 최상위 폴더(root folder)를 의미
		
		try {
			//자원으로부터 통로(stream)얻기 
			InputStream stream = Resources.getResourceAsStream(resource);
			
			//1단계: new SqlSessionFactoryBuilder() : 객체 생성
			//2단계 : .build(stream) : 통로로 부터 mybatis-config.xml 파일을 읽어오며 sqlSessionFactory 객체 생성
			//3단계 : .openSession(false) : sqlSession 객체 생성 및 앞으로 트랜잭션 처리시 자동 commit 여부 지정
			// - openSession() 처럼 매개변수를 주지않아도 기본값 false
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSession;
		
	}
	
	
}
