package com.kh.reply.model.dao;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.reply.model.vo.Reply;

public class ReplyDao {
	
	
	private Properties prop = new Properties();
	
	public ReplyDao() {
		String filePath = MemberDao.class.getResource("/sql/reply/reply-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	public int insertReply(Connection conn, Reply reply) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReply");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getRefBno());
			pstmt.setString(3, reply.getReplyWriterS());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		

	
		return result;
	}


	public ArrayList<Reply> selectReply(Connection conn, int refBno) {
	
		 ArrayList<Reply> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		String sql = prop.getProperty("selectReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			 pstmt.setInt(1, refBno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Reply (rset.getInt("REPLY_NO") 
						,rset.getString("REPLY_CONTENT")
						,rset.getInt("REPLY_WRITER")
						,rset.getString("USER_ID")
						,rset.getDate("CREATE_DATE")));
			};
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		System.out.println("dao에서 가져왔나요:"+list); //dao에서 가져왔나요:[]
		return list;
	}

}
