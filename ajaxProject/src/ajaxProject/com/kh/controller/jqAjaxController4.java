package ajaxProject.com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ajaxProject.com.kh.model.vo.Member;

/**
 * Servlet implementation class jqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class jqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//데이터베이스에서 회원 목록을 조회해왔다는 가정하기 
		ArrayList<Member> list = new ArrayList<>();
		
		list.add(new Member(3,"김둘리", 20, "남자"));
		list.add(new Member(5, "도우너", 40, "남자"));
		list.add(new Member(11, "최또치", 15, "여자"));

		//list를 gson을 이용하여 편하게 전송하기
		
		response.setContentType("json/application; charset=UTF-8");
		
		new Gson().toJson(list, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
