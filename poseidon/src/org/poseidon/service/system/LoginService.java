package org.poseidon.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {
	public void setSession(String loginEmail,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public String returnLoginMessage(String loginEmail, String password) throws Exception;
}
