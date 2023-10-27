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
		// request를 이용하여 파라미터 받아와서db에 저장하기
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
		Date mbirth = str_mbirth.equals("") ? null : Date.valueOf(str_mbirth);
		String mgender = request.getParameter("mgender");
		String maddress = request.getParameter("maddress");
		MemberDao mDao = MemberDao.getInstance();
		MemberDto newMember = new MemberDto(mid, mpw, mname, memail, mtel, null, mgender, maddress);
		int result = mDao.modifyMember(newMember);
		if (result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid);
			request.setAttribute("modifyResult", "회원정보수정이 완료되었습니다");
		} else { 
			request.setAttribute("modifyErrorMsg", "회원정보 수정 실패(정보가 길거나 mid check)");
		}
		// Request를 이용하여 파라미터 받아와서db에 저장하기 로직은 위에서
	}
}
