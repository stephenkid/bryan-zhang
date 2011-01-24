package org.poseidon.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.poseidon.global.PoseidonSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BaseController{
	public static Object getBean(ServletContext servletContext,String beanName) {
		return getApplicationContext(servletContext).getBean(beanName);
	}
	private static ApplicationContext getApplicationContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	protected static PoseidonSession getPoseidonSession(HttpServletRequest request) {
		PoseidonSession poseidonSession = (PoseidonSession) request.getSession().getAttribute("poseidonSession");
		if (poseidonSession == null) {
			throw new RuntimeException("会话不存在或过期！");
		}
		return poseidonSession;
	}
}
