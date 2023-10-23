package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.BoardDao;
import com.lec.sce.dto.BoardDto;

public class BoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = boardDao.getBoardNotHitUp(bnum);
		request.setAttribute("board", boardDto);
	}

}
