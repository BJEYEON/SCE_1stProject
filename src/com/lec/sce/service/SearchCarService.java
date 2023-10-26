package com.lec.sce.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.CarDao;
import com.lec.sce.dto.CarDto;

public class SearchCarService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 String cname = request.getParameter("cname");
	        CarDao cdao = CarDao.getInstance();
	        CarDto carList = cdao.getCar(cname);
	        request.setAttribute("carList", carList);
	        
	}

}
