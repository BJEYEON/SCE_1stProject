package com.lec.sce.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sce.dao.CarDao;
import com.lec.sce.dao.TestCarApplyDao;
import com.lec.sce.dto.CarDto;

public class TestCarAppService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CarDao cardao = CarDao.getInstance();
		ArrayList<CarDto> carList = cardao.carList();
		request.setAttribute("carList", carList);

	}

}
