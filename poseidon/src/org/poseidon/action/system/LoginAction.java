package org.poseidon.action.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.poseidon.action.BaseDispatchAction;
import org.poseidon.service.system.LoginService;
import org.poseidon.util.ServletUtil;

public class LoginAction extends BaseDispatchAction {
	@Resource(name = "loginService")
	private LoginService loginService;
	
	public ActionForward loginMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String validate = request.getParameter("validate");
		String loginEmail = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");
		
		ActionForward forward = null;
		if ("validate".equals(validate)) {
			ServletUtil.writerText(response,loginService.returnLoginMessage(loginEmail, password));
		}else{
			loginService.setSession(loginEmail,request,response);
			forward = mapping.findForward("loginSuccess");
		}
		return forward;
	}

	public ActionForward loginOffMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return null;
	}
}
