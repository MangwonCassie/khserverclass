package com.kh.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.reply.model.service.ReplyService;
import com.kh.reply.model.vo.Reply;

/**
 * Servlet implementation class CommentListController
 */
@WebServlet("/insert.rp")
public class InsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		int currentBoardNo= Integer.parseInt(request.getParameter("currentBoardNo"));
		String replyContent= request.getParameter("replyContent");
		String replyWriter = request.getParameter("replyWriter");
		
		System.out.println("currentBoardNo용 확인"+currentBoardNo);
		System.out.println("replyContent용 확인"+replyContent);
		//System.out.println("숫자니..?"+replyWriter);//숫자니..?1 숫자는 출력됨
		Reply reply = new Reply();
		reply.setRefBno(currentBoardNo);
		reply.setReplyContent(replyContent);
		reply.setReplyWriterS(replyWriter);
		
		int result = new ReplyService().insertReply(reply);
		
		if(result>0) {
			
			response.setContentType("text/html; charset=UTF-8"); 
			
			response.getWriter().print("aa");
			
		}else {
			//이게 나오네..추가가안됐어...!ㅋㅋ
			response.getWriter().print("bb");
			
		}
		
		//일단테스트 
		//String responseData = "입력된 값: "+str+",길이: "+str1+str2;
		
		
		//창에 보내려고 테스트
		//response.setContentType("text/html; charset=UTF-8");// ; 으로 구분
		
		//response.getWriter().print(responseData);
		
		//select로 할 때....gson으로 배워서 객체배열..다 넣어서...다다다다 반복문으로 띄우게해야한데..
		
		
		//테스트
		
		
		//int currentBoardNo = Integer.parseInt(request.getParameter("currentBoardNo"));
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
