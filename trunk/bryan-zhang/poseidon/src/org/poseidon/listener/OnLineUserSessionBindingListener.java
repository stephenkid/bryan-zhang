package org.poseidon.listener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

@Component("onLineUserSessionBindingListener")
public class OnLineUserSessionBindingListener implements
		HttpSessionBindingListener,Serializable {
	private static final long serialVersionUID = 495516839139233126L;
	private static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

	public void valueBound(HttpSessionBindingEvent sessionBindingEvent) {
		String loginNo = sessionBindingEvent.getName();
		loginOn(loginNo, sessionBindingEvent.getSession());
	}

	public void valueUnbound(HttpSessionBindingEvent sessionBindingEvent) {
		String loginNo = sessionBindingEvent.getName();
		loginOff(loginNo);
	}

	private void loginOff(String loginNo) {
		if (sessionMap.containsKey(loginNo)) {
			try {
				sessionMap.get(loginNo).invalidate();
			} catch (Exception e) {
			} finally {
				sessionMap.remove(loginNo);
			}
		}
	}

	private void loginOn(String loginNo, HttpSession session) {
		loginOff(loginNo);
		sessionMap.put(loginNo, session);
	}
	
	public static boolean isLogin(String loginNo){
		return sessionMap.containsKey(loginNo);
	}

	public static Map<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	public static void setSessionMap(Map<String, HttpSession> sessionMap) {
		OnLineUserSessionBindingListener.sessionMap = sessionMap;
	}
}
