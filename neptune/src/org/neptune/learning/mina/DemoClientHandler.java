package org.neptune.learning.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class DemoClientHandler extends IoHandlerAdapter {
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		System.out.println("客户端收到的信息为:" + msg);
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("客户端发生异常...");
		cause.printStackTrace();
	}
}
