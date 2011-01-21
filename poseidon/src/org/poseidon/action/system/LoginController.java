package org.poseidon.action.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.action.BaseController;
import org.poseidon.service.system.LoginService;
import org.poseidon.util.ServletUtil;
import org.springframework.web.servlet.ModelAndView;

public class LoginController extends BaseController {
	@Resource(name = "loginService")
	private LoginService loginService;
	
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
	
	public ModelAndView loginOff(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		return null;
	}
}
