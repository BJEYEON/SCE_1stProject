package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.MemberDao;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// mide
		String mid = request.getParameter("mid");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.midConfirm(mid);
		if (result == MemberDao.EXISTENT) {
			request.setAttribute("confirmResult", "<b>중복된 ID입니다</b>");
		} else {
			request.setAttribute("confirmResult", "사용가능한id입니다");
		}
	}

}
