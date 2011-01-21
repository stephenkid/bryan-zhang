package org.poseidon.service.system.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.lang.StringUtils;
import org.poseidon.global.PoseidonSession;
import org.poseidon.listener.OnLineUserSessionBindingListener;
import org.poseidon.pojo.Login;
import org.poseidon.service.system.LoginService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "onLineUserSessionBindingListener")
	HttpSessionBindingListener onLineUserSessionBindingListener;

	public String returnLoginMessage(String loginNo, String password) throws Exception {
		String loginMessage = "success";
		List<Login> loginList = hibernateTemplate.find("from Login l where upper(l.loginEmail)=? and l.loginPassword=?", new String[] {
				loginNo.toUpperCase(), password });
		if (loginList.size() == 0) {
			loginMessage = "wrongLogin";
		} else if ("0".equals(loginList.get(0).getIsAvail())) {
			loginMessage = "invalidLogin";
		} else if (OnLineUserSessionBindingListener.isLogin(loginNo)) {
			loginMessage = "hasLogined";
		}
		return loginMessage;
	}

	public void setSession(String loginEmail,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Login login = this.getLogin(loginEmail);
		if(login==null){
			return;
		}
		String isSaveLoginInfo = request.getParameter("isSaveLoginInfo");
		if (StringUtils.isNotEmpty(isSaveLoginInfo)){
			Cookie c = null;
			c = new Cookie("loginEmail",login.getLoginEmail());
			c.setMaxAge(60000);
			response.addCookie(c);
			c = new Cookie("loginPassword",login.getLoginPassword());
			c.setMaxAge(60000);
			response.addCookie(c);
		}
		
		PoseidonSession poseidonSession = new PoseidonSession();
		poseidonSession.setLogin(login);
		HttpSession session = request.getSession();
		session.setAttribute("poseidonSession", poseidonSession);
		session.setAttribute(poseidonSession.getLogin().getLoginEmail(), onLineUserSessionBindingListener);
	}

	private Login getLogin(String loginEmail) {
		List<Login> loginList = hibernateTemplate.find("from Login l where upper(l.loginEmail)=?", new String[] { loginEmail.toUpperCase() });
		Login login = null;
		if (!(loginList.isEmpty())) {
			login = loginList.get(0);
		}
		return login;
	}
	
	public void addLogin(Login login) throws Exception{
		hibernateTemplate.save(login);
	}
}
