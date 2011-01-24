package org.poseidon.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.pojo.Login;
import org.poseidon.service.system.LoginService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Transactional
public class TestController extends BaseController {
	
	private LoginService loginService;
	
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Login loginItem = new Login();
		loginItem.setLoginEmail("weenyc31@163.com");
		loginItem.setLoginPassword("111111");
		loginItem.setLoginName("bryan31");
		loginItem.setMemo("这是备注");
		loginItem.setIsAvail(true);
		loginItem.setInputDate(new Date());
		loginService.addLogin(loginItem);
		modelAndView.setViewName(null);
		return modelAndView;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
