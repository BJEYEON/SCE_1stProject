package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.CarDao;
import com.lec.sce.dao.TestCarApplyDao;
import com.lec.sce.dto.TestCarApplyDto;

public class TestCarAppService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int result = TestCarApplyDao.FAIL;
		int cnum = Integer.parseInt(request.getParameter("carname"));
		String tname =  request.getParameter("tname");
		String ttel =  request.getParameter("ttel");
		String tgender =  request.getParameter("tgender");
		String tarea =  request.getParameter("tarea");
		String thall =  request.getParameter("thall");
		String tselmind =  request.getParameter("tselmind");
		String temail =  request.getParameter("temail");
		TestCarApplyDao tdao = TestCarApplyDao.getInstance();
		TestCarApplyDto newApply = new TestCarApplyDto(0, cnum, tname, ttel, tgender, tarea, thall, tselmind, temail, null);
		result = tdao.apply(newApply);
		if(result == TestCarApplyDao.SUCCESS) {
			request.setAttribute("applyResult", "시승신청 완료");
		}else {
			request.setAttribute("applyErrorMsg", "시승신청이 실패되었습니다. 입력 정보를 확인해 주세요.");
		}
	}

}
