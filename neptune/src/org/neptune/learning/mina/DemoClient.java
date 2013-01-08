package org.neptune.learning.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class DemoClient {
	private static String host = "127.0.0.1";
	private static String port = "3005";
	
	public static void main(String[] args){
		IoConnector connector = new NioSocketConnector();
		
		connector.setConnectTimeout(30000);
		
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(
				Charset.forName("UTF-8"),
				LineDelimiter.WINDOWS.getValue(),
				LineDelimiter.WINDOWS.getValue())));
	}
	
}
