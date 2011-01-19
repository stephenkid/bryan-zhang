package org.poseidon.action.demo.test;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.poseidon.action.BaseDispatchAction;
import org.poseidon.pojo.Login;
import org.poseidon.service.system.LoginService;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestAction extends BaseDispatchAction {
	
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	public ActionForward test(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Login loginItem = new Login();
		loginItem.setLoginEmail("weenyc31@163.com");
		loginItem.setLoginPassword("111111");
		loginItem.setLoginName("bryan31");
		loginItem.setMemo("这是备注");
		loginItem.setIsAvail(true);
		loginItem.setInputDate(new Date());
		loginService.addLogin(loginItem);
		return null;
	}
}
