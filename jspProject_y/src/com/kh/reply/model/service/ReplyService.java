package com.kh.reply.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.reply.model.dao.ReplyDao;
import com.kh.reply.model.vo.Reply;

public class ReplyService {

	public int insertReply(Reply reply) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ReplyDao().insertReply(conn,reply);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.close(conn);
		}
		
		
		return result;
	}

	public ArrayList<Reply> selectReply(int refBno) {
	
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Reply> list = new ArrayList<>();
		
		list = new ReplyDao().selectReply(conn, refBno);
		
		
		JDBCTemplate.close(conn);
		
		return list;
	}

}
