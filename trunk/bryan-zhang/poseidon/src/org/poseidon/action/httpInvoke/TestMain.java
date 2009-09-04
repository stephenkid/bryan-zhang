package org.poseidon.action.httpInvoke;

import org.apache.commons.httpclient.NameValuePair;
import org.poseidon.util.HttpClientUtil;

public class TestMain {
	public static void main(String[] args) throws Exception{
    	NameValuePair[] data = {new NameValuePair("method", "returnXmlMethod")}; 

    	String returnText = HttpClientUtil.sendPost("http://localhost:8085/poseidon/httpInvokeAction.do", data, 6000);
    	System.out.println(returnText);
    }
}
