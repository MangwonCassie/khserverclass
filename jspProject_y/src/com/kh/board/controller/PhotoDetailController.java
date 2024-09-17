package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class PhotoDetailController
 */
@WebServlet("/detail.ph")
public class PhotoDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		
		//System.out.println("photoDetailCon에서 확인중"+bno);
		//내가들고온게 뭔지 확인하기
		
		//조회수증가메소드는 일반 게시판에서 작성했음 사용가능 (생각도...못했는데..다들 썻데)
		int result = new BoardService().increaseCount(bno); 
		
		
		if(result>0) {//조회수 증가 성공시 조회해오기
			//게시글 정보, 첨부파일 정보 상세조회
			
			Board b =new BoardService().selectBoard(bno);
			//Board b = new BoardService().detailPhotoBoard(bno);// 내가 한 거 개망함 null임
			ArrayList<Attachment> list = new BoardService().selectAttachmentList(bno);
		
			//메소드오버로딩
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			//jsp로 위임을 안했다....! set만 해도 되는게 아니네...!성이가 찾아줌
			System.out.println("컨트롤러에서 확인중 b"+b);
			System.out.println("컨트롤러에서 확인중 list"+list);
			request.getRequestDispatcher("views/board/photoDetailView.jsp").forward(request, response);
			
		} else {
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			//request.setAttribute("errorMsg", "실패했습니다");
			//에러 창 못 보내네...
		}
	
		//이것부터 틀림 여러개잖아...
		//Attachment at = service.detailPhotoAttachment(bno);
		
		
		
	
		//작성하기 폼에서 데이터 그대로 뽑아주면 된다.jsp
		request.getRequestDispatcher("views/board/photoDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
