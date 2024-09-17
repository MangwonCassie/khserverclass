package com.kh.reply.model.vo;

import java.sql.Date;

public class Reply {

	
	int replyNo;
	String replyContent;
	int refBno;
	int replyWriter;
	Date createDate;
	String status;
	String replyWriterS; //아이디 띄우는 용
	int userId; //userId용
	


	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(int replyNo, String replyContent, int refBno, int replyWriter, Date createDate, String status, int userId) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
		this.userId = userId;
	}
	
	
	
	

	public Reply(int replyNo, String replyContent,  int userId, String replyWriterS, Date createDate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.userId = userId;
		this.replyWriterS = replyWriterS;
		this.createDate = createDate;

	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public int getReplyWriter() {
		return replyWriter;
	}


	
	//selectReply
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	

	//Reply용
	public String getReplyWriterS() {
		return replyWriterS;
	}

	public void setReplyWriterS(String replyWriterS) {
		this.replyWriterS = replyWriterS;
	}
	
	
	
	
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

	

	public void setReplyWriter1(String replyWriterS) {
		this.replyWriterS = replyWriterS;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBno=" + refBno + ", replyWriter="
				+ replyWriter + ", createDate=" + createDate + ", status=" + status + ", replyWriterS=" + replyWriterS
				+ ", userId=" + userId + "]";
	}


	
	
	
	
	
	
}
