package com.lec.sce.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
