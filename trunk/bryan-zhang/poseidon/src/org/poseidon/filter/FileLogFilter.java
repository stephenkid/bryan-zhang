package org.poseidon.filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.poseidon.global.PoseidonSession;


public class FileLogFilter implements Filter {
	private File logFile;
	private BufferedWriter bw;
	public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d HH:mm:ss");
	public void destroy() {
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logFile=null;
		bw=null;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		
		PoseidonSession poseidonSession = (PoseidonSession) request.getSession().getAttribute("poseidonSession");
		StringBuffer url=request.getRequestURL();
		if(bw!=null){
			bw.write(sdf.format(new Date())+","+request.getRemoteAddr()+","+url.toString()+","+(poseidonSession==null?"":poseidonSession.getLogin().getLoginName())+"\r\n");
		}
		bw.flush();
		filterChain.doFilter(request, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("logPath")!=null){
			File logPath=new File(filterConfig.getInitParameter("logPath"));
			if(logPath!=null&&!logPath.exists()){
				logPath.mkdirs();
			}
			logFile = new File(logPath,new SimpleDateFormat("yyyy-MM-dd HH_mm_ss").format(new Date())+".csv");
			try {
				if(logFile!=null){
					bw = new BufferedWriter(new FileWriter(logFile));
					if(bw!=null){
						bw.write("时间,客户端IP,请求URL,客户Session\r\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
