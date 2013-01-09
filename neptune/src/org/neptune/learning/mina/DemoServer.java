package org.neptune.learning.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class DemoServer {
	private static int port = 3005;
	
	public static void main(String[] args){
		IoAcceptor acceptor = null;
		try{
			acceptor = new NioSocketAcceptor();
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(
					Charset.forName("UTF-8"),
					LineDelimiter.WINDOWS.getValue(),
					LineDelimiter.WINDOWS.getValue())));
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			acceptor.setHandler(new DemoServerHandler());
			acceptor.bind(new InetSocketAddress(port));
			System.out.println("server started,port:" + port);
		}catch(Throwable t){
			System.out.println("server started error");
			t.printStackTrace();
		}
	}
}
