package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class PhotoInsertController
 */
@WebServlet("/insert.ph")
public class PhotoInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("views/board/photoEnrollForm.jsp").forward(request, response);
		
		//겟리퀘스트 디스패쳐
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//전송용량 제한
			int maxSize = 10* 1024 * 1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/photoBoard_files/"); 
			// board_files/ 폴더 안쪽까지 들어갈 수 있게끔 끝에 슬래쉬
			
			//전달된 파일명 수정작업 객체 포함시켜 업로드 작업
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			//DB에 저장할 데이터 추출하기
			Board b =  new Board();
			//b.setBoardWriter("userNo"); 아님
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
			
			//Attachment 에 들어가야할 데이터도 추출하기
			//여러개의 첨부파일이 있을 수 있으니 list에 담아서 가져가기
			//대표이미지는 필수입력사항이니 최소 1개는 담기게 된다. 
			//첨부파일을 여러개 가져올 수 있으니까 ArrayList
			
			ArrayList<Attachment> list = new ArrayList<>();     //왼쪽 오른쪽 ArrayList
			
			
			//객체생성해서 담아주기
			//반복문이용 키값접근 
			
			for(int i =1; i<=4; i++) {
				String key = "file"+i;
				
				//키값에 해당하는 파일이 넘어왔다면
				//첨부파일이 있다면 첨부파일 객체 생성 및 데이터 담기
				if(multiRequest.getOriginalFileName(key)!=null) {
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("/resources/photoBoard_files/");   //이번에 양쪽 끝 / / 붙임.뽑아줄 떄 채워줘도되고 경로쉽게 뽑으려고
					
					//대표이미지와 상세이미지를 구분할 file_level 컬럼값 세팅하기 
					if(i==1) {
						//대표이미지파일(file1을 키값으로 가진)
						at.setFileLevel(1);
					}else {
						//상세이미지 파일 (대표이미지가 아닌 경우)
						at.setFileLevel(2);
					}
					
					list.add(at);
					
					
				}
			}//for 닫는 
			
			//for문 바깥에서 요청해야함. 아니면 계속 요청함. - > 비슷하게 계속 오류나서 주말에 계속 찾았잖아			
			//추출한 게시글 정보와 첨부파일 정보 넘겨주기 
			int result = new BoardService().insertPhotoBoard(b,list);  //게시글 정보 b랑 첨부파일 리스트 정보, 
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "사진게시글 작성 성공");
				response.sendRedirect(request.getContextPath()+"/list.ph");
			}else {
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
			
		}
		
	}

}
