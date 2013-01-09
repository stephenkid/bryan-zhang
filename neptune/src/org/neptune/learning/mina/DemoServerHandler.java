package org.neptune.learning.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class DemoServerHandler extends IoHandlerAdapter {
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("服务端与客户端创建连接...");
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("服务端与客户端连接打开...");
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		System.out.println("服务端收到的数据为:" + msg);
		if ("bye".equals(msg)){
			session.close();
		}
		Date date = new Date();
		session.write(date);
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("服务端发送信息成功...");
		//短连接
		session.close();
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("session关闭...");
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("服务端进入空闲状态...");
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("服务端发送异常...");
	}
}
