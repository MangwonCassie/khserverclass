package com.kh.reply.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.reply.model.service.ReplyService;
import com.kh.reply.model.vo.Reply;

/**
 * Servlet implementation class selectReplyController
 */
@WebServlet("/select.rp")
public class selectReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList<Reply> list = new ArrayList<>(); 
		
		int refBno = Integer.parseInt(request.getParameter("currentBoardNo"));
		list = new ReplyService().selectReply(refBno);
		
		//콘솔에 출력이 안되는뎅......System.out.println("select컨트롤러 확인중"+list);
		
		//System.out.println("select컨트롤러 확인중"+list);
		
		//3가지 방식 다 정리하기(JSONArray - add메소드) 응답할 데이터 타입 지정 
		
		//JSONObject jObj = new JSONObject(); 그 다음 put메소드 jObj.put("list", list);
		
		response.setContentType("json/application; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
		
		//put, add 할 필요없이 Gson만 쓰면 간편하게 객체 그대로 들고갈 수 있음 코드 두줄만 있으면 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
