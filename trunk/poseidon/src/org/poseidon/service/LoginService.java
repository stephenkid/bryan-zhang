package org.poseidon.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.pojo.Login;

public interface LoginService {
	public void setSession(String loginEmail,HttpServletRequest request,HttpServletResponse response);
	
	public String returnLoginMessage(String loginEmail, String password);
	
	public void addLogin(Login login);
}
