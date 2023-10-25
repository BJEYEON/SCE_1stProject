package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dto.TestCarApplyDto;

public class TestCarAppService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnum = Integer.parseInt(request.getParameter("carname"));
		String tname =  request.getParameter("tname");
		String ttel =  request.getParameter("ttel");
		String tgender =  request.getParameter("tgender");
		String tarea =  request.getParameter("tarea");
		String thall =  request.getParameter("thall");
		String tselmind =  request.getParameter("tselmind");
		String temail =  request.getParameter("temail");
		
		TestCarApplyDto newApply = new TestCarApplyDto(0, cnum, tname, ttel, tgender, tarea, thall, tselmind, temail, null);

	}

}
