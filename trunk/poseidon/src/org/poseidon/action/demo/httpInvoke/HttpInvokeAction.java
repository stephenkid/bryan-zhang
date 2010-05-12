package org.poseidon.action.demo.httpInvoke;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.Element;
import org.poseidon.action.BaseDispatchAction;
import org.poseidon.util.Dom4jUtil;
import org.poseidon.util.ServletUtil;

public class HttpInvokeAction extends BaseDispatchAction {
	public ActionForward returnStrMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnStr;
		returnStr = "hello,world!!!!!! very 的 hello";
		ServletUtil.writerText(response,returnStr);
		return null;
	}
	
	public ActionForward returnXmlMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Document doc = Dom4jUtil.createDocument();
		Element e = doc.addElement("parent");
		e.addElement("child").setText("a");
		e.addElement("child").setText("b");
		e.addElement("child").setText("c");
		e.addElement("child").setText("d");
		ServletUtil.writerXml(response, Dom4jUtil.xmlToStirng(doc));
		return null;
	}
}
