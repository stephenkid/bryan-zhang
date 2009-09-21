package org.poseidon.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.poseidon.global.PoseidonSession;

public class SessionValidFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		List<String> escapeList = new ArrayList<String>();
		escapeList.add("/index.jsp");
		escapeList.add("/loginAction.do");
		
		if (!escapeList.contains(request.getServletPath())){
			HttpSession session = request.getSession();
			if (session == null){
				throw new RuntimeException("session is not valid");
			}
			PoseidonSession poseidonSession = (PoseidonSession)session.getAttribute("poseidonSession");
			if (poseidonSession == null){
				throw new RuntimeException("session is not valid");
			}
		}
		filterChain.doFilter(request, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
