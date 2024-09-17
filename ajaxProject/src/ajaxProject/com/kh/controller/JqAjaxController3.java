package ajaxProject.com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import ajaxProject.com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터로 받는 건 똑같네?
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		//번호로 회원조회를 했다고 가정하고 데이터 임의로 넣기
		
		Member m = new Member(5, "고길동", 50, "남자");
		
		//객체를 이클립스에서 넘겨서 인터넷 창에 콘솔로 넘기는 과정 
		
		//response.getWriter().print(m);
		
		/*
		JSONObject jobj = new JSONObject();
		jobj.put("memberNo", m.getMemberNo());
		jobj.put("memberName", m.getMemberName());
		jobj.put("gender", m.getGender());
		jobj.put("age", m.getAge());
		*/
		
		//response.setContentType("json/application; charset=UTF-8");
		//response.getWriter().print(jobj);
		/*getWriter() 메소드를 통해 응답으로 내보낼 출력 스트림을 얻어낸 후 out.print(HTML 태그) 형태로 작성하여 스트림에 텍스트를 기록*/
		
		//GSON: Google JSON
		//GSON: 라이브러리 사용
		response.setContentType("json/application; charset=UTF-8"); 
		Gson gson = new Gson();
		//gson.toJson(응답객체, 스트림);
		//gson열어줄 스트림은 getWriter임
		//변환 시 전달되는 키값은 VO의 필드명으로 지정한다.
		gson.toJson(m, response.getWriter()); //별도로 프린트 사용안해도된다고함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
