package org.poseidon.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.dto.TestDto;
import org.poseidon.service.system.LoginService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Transactional
public class TestController extends BaseController {
	
	private LoginService loginService;
	
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response, TestDto dto) throws Exception{
		System.out.println("hello world");
		System.out.println(dto.getTestString());
		System.out.println(dto.getTestLong());
		System.out.println(dto.isTestBoo());
		System.out.println(dto.getTestDate());
		System.out.println(Arrays.asList(dto.getTestLongArray()));
		System.out.println(Arrays.asList(dto.getTestStringArray()));
		return null;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
