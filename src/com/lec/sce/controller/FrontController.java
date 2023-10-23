package com.lec.sce.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.service.ALoginService;
import com.lec.sce.service.BoardContentService;
import com.lec.sce.service.BoardListService;
import com.lec.sce.service.BoardModifyService;
import com.lec.sce.service.BoardModifyViewService;
import com.lec.sce.service.BoardWriteService;
import com.lec.sce.service.MLoginService;
import com.lec.sce.service.MLogoutService;
import com.lec.sce.service.MModifyService;
import com.lec.sce.service.MemailConfirmService;
import com.lec.sce.service.MidConfirmService;
import com.lec.sce.service.MjoinService;
import com.lec.sce.service.Service;
import com.lec.sce.service.MWithdrawalService;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		//main
		}else if(command.equals("/loginView.do")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}else if(command.equals("/login.do")) { // 로그인 확인
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) { // 화면가입 화면
			viewPage = "member/join.jsp";
		}else if(command.equals("/midConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
		}else if(command.equals("/memailConfirm.do")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/memailConfirm.jsp";
		}else if(command.equals("/join.do")) { // 회원가입 
			service = new MjoinService();
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/logout.do")) { // 로그아웃 
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/modifyView.do")) { // 정보 수정
			viewPage = "member/modify.jsp";
		}else if(command.equals("/modify.do")) {// 정보수정 DB처리후 세션도 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/withdrawal.do")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * * * admin 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 파일첨부 게시판 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		} else if(command.equals("/boardList.do")) {
			service = new BoardListService();
			service.execute(request, response);
			viewPage = "board/boardList.jsp";
		}else if(command.equals("/boardWriteView.do")) {
			viewPage = "board/boardWrite.jsp";
		}else if(command.equals("/boardWrite.do")) {
			service = new BoardWriteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(command.equals("/boardContent.do")) {
			service = new BoardContentService();
			service.execute(request, response);
			viewPage = "board/boardContent.jsp";
		}else if(command.equals("/boardModifyView.do")) {
			service = new BoardModifyViewService();
			service.execute(request, response);
			viewPage = "board/boardModify.jsp";
		}else if(command.equals("/boradModify.do")) {
			service = new BoardModifyService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
