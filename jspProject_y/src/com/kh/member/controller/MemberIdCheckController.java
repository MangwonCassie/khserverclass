package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberIdCheckController
 */
@WebServlet("/idCheck.me")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String checkId = request.getParameter("checkId");//data: 키값으로 가져온거임...
		
		//System.out.println(checkId);
		
		int count = new MemberService().checkId(checkId); 
		
		//비동기 통신은 따로 쓰는 메소드가 있다. 그게 response.getWriter.print or response.getWriter.println
		
		if(count>0) {//조회가 되었다(중복) NNNNN
			response.getWriter().print("NNNNN");
		}else {
			//조회가 되지않음(중복아님)  사용가능  NNNNY
			response.getWriter().print("NNNNY");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
