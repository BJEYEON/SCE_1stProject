package com.lec.sce.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.sce.dao.MemberDao;
import com.lec.sce.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 첨부한 파일 저장 -> 파라미터 받아db저장-> 첨부한 파일을 소스폴더로 복사
					int result = MemberDao.FAIL; //회원가입 결과를 저장할 변수
					// Request를 이용하여 파라미터 받아와서db에 저장하기
					String mid = request.getParameter("mid");
					String oldMpw = request.getParameter("oldMpw");
					String mpw = request.getParameter("mpw");
					if (mpw.equals("")) {// 정보수정시 새비밀번호를 입력하지 않은 겨우 현비밀번호
						mpw = oldMpw;
					}
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
							request.setAttribute("modifyResult", "회원정보수정이 완료되었습니다");
						}else { //가입실패
							request.setAttribute("modifyErrorMsg", "정보가 너무 길어서 수정가입 실패");
						}
					}else { //가입불가한mid
						request.setAttribute("modifyErrorMsg", "중복된 id는 정보수정이 불가합니다");
					}
					// Request를 이용하여 파라미터 받아와서db에 저장하기 로직은 위에서

	}

}
