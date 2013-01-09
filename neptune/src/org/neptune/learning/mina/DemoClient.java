package org.neptune.learning.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class DemoClient {
	private static String host = "127.0.0.1";
	private static int port = 3005;
	
	public static void main(String[] args){
		IoConnector connector = new NioSocketConnector();
		
		connector.setConnectTimeout(30000);
		
		//字符串发送
//		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(
//				Charset.forName("UTF-8"),
//				LineDelimiter.WINDOWS.getValue(),
//				LineDelimiter.WINDOWS.getValue())));
		
		//对象发送
		connector.getFilterChain().addLast("codec", 
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		connector.setHandler(new DemoClientHandler());
		IoSession session = null;
		try{
			ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
			future.awaitUninterruptibly();
			session = future.getSession();
			session.write(new Person("peter", 28));
		}catch(Throwable t){
			t.printStackTrace();
		}
		
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
	
}
