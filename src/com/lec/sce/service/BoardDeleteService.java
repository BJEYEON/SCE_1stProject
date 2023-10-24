package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.BoardDao;

public class BoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 삭제하고자 하는 글만 삭제
//		int fid = Integer.parseInt(request.getParameter("fid"));
//		BoardDao boardDao = BoardDao.getInstance();
//		int result = boardDao.deleteBoard(fid);
//		if(result == BoardDao.SUCCESS) {
//			request.setAttribute("boaredResult", "글 삭제 성공");
//		}else {
//			request.setAttribute("boaredResult", "글삭제 실패");
//		}
		// 삭제하고자 하는 글과 답변글 모두 삭제 추가할 예정
		int bgroup = Integer.parseInt(request.getParameter("bgroup"));
		int bstep = Integer.parseInt(request.getParameter("bstep"));
		int bindent = Integer.parseInt(request.getParameter("bindent"));
		BoardDao boardDao = BoardDao.getInstance();
		int deleteCnt = boardDao.deleteBoard(bgroup, bstep, bindent);
		if(deleteCnt >= BoardDao.SUCCESS) {
			request.setAttribute("boaredResult", "글(답변글 포함) "+deleteCnt+"개 글 삭제 성공");
		}else {
			request.setAttribute("boaredResult", "글(답변글도 모두) 삭제 안 됨");
		}
	}

}
