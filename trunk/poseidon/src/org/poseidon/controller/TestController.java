package org.poseidon.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.controller.base.BaseController;
import org.poseidon.dto.TestDto;
import org.poseidon.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/testAction.do")
public class TestController extends BaseController {
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@RequestMapping(params="action=test")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response, TestDto dto) throws Exception{
		System.out.println("hello world");
		System.out.println(dto.getTestString());
		System.out.println(dto.getTestLong());
		System.out.println(dto.isTestBoo());
		System.out.println(dto.getTestDate());
		System.out.println(dto.getTestLongArray());
		System.out.println(dto.getTestStringArray());
		return null;
	}
}
