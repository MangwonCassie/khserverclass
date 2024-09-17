package ajaxProject.com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxController1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String str = request.getParameter("input");
		//System.out.println(str);
		
		//사용자에게 보여줄 응답데이터
		String responseData = "입력된 값: "+str+",길이: "+str.length();
		
		//통로를 열어줘야함
		//getWriter: jsp와의 통신통로 열어주기 
		//응답데이터에 한글이포함되었다면 설정해주기 (아니면 ??로 콘솔에 뜸)
		response.setContentType("text/html; charset=UTF-8");// ; 으로 구분
		
		response.getWriter().print(responseData);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
