package com.lec.sce.service;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.sce.dao.MemberDao;
import com.lec.sce.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;

public class MjoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	// 첨부한 파일 저장 -> 파라미터 받아db저장-> 첨부한 파일을 소스폴더로 복사
			int result = MemberDao.FAIL; //회원가입 결과를 저장할 변수
			// Request를 이용하여 파라미터 받아와서db에 저장하기
			String mid = request.getParameter("mid");
			String mpw = request.getParameter("mpw");
			String mname = request.getParameter("mname");
			String memail = request.getParameter("memail");
			String mtel = request.getParameter("mtel");
			String str_mbirth = request.getParameter("mbirth");
			Date mbirth = str_mbirth.equals("") ? null:Date.valueOf(str_mbirth); 
			String mgender = request.getParameter("mgender");
			String maddress = request.getParameter("maddress");
			MemberDao mDao = MemberDao.getInstance();
			// mid중복체크
			result = mDao.midConfirm(mid);
			if(result == MemberDao.NONEXISTENT) { //가입 가능한mid라서 회원가입진행
				MemberDto newMember = new MemberDto(mid, mpw, mname, memail, mtel, null, mgender, maddress);
				// 회원가입
				result = mDao.joinMember(newMember);
				if(result == MemberDao.SUCCESS) { //가입성공
					HttpSession session = request.getSession();
					session.setAttribute("mid", mid);
					request.setAttribute("joinResult", "회원가입이 완료되었습니다");
				}else { //가입실패
					request.setAttribute("joinErrorMsg", "정보가 너무 길어서 회원가입 실패");
				}
			}else { //가입불가한mid
				request.setAttribute("joinErrorMsg", "중복된 id는 회원가입이 불가합니다");
			}
			// Request를 이용하여 파라미터 받아와서db에 저장하기 로직은 위에서
	}

}
