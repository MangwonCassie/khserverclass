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
 * Servlet implementation class PhotoListController
 */
@WebServlet("/list.ph")
public class PhotoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//페이징처리 직접 해보기
		
		

		
		//selectAttachmentList 게시글번호, 제목, 조회수, 파일경로 + 변경이름 조회해와서 사용자에게 보여주기 
		
		//목록 화면엔 대표이미지가 보여지게 작성할 것 
		
		ArrayList<Board> list = new BoardService().selectPhotoBoardList();
		
		//이게 필요가 없음 ArrayList<Attachment> aList = new BoardService().selectPhotoAttachmentList();
		
		System.out.println(list);
		//System.out.println(aList);
		
		request.setAttribute("list", list);
		//request.setAttribute("aList", aList);
		
		//count가 왜 0이야?
		
		//[Attachment [fileNo=1, refBno=108, originName=토끼.png, changeName=토끼.png, filePath=resources/board_files, uploadDate=2023-04-06, fileLevel=0, status=Y]
		//[Board [boardNo=134, boardType=2, category=null, boardTitle=제발 단축키 쓰세요, boardContent=제에발, boardWriter=1, count=0, createDate=2023-04-12, status=null], 
		request.getRequestDispatcher("views/board/photoListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
