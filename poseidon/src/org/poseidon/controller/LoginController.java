package org.poseidon.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.controller.base.BaseController;
import org.poseidon.service.system.LoginService;
import org.poseidon.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/loginAction.do")
public class LoginController extends BaseController {
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@RequestMapping(params="action=login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = null;
		String validate = request.getParameter("validate");
		String loginEmail = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");
		
		if ("validate".equals(validate)) {
			ServletUtil.writerText(response,loginService.returnLoginMessage(loginEmail, password));
		}else{
			loginService.setSession(loginEmail,request,response);
			modelAndView = new ModelAndView("/main.jsp");
		}
		return modelAndView;
	}
	
	@RequestMapping(params="action=loginOff")
	public ModelAndView loginOff(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		return null;
	}
}
