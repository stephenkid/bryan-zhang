package org.poseidon.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.controller.base.BaseController;
import org.poseidon.service.DownloadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/downloadAction.do")
public class DownloadController extends BaseController {

	@Resource(name = "downloadService")
	private DownloadService downloadService;
	
	@RequestMapping(params = "action=testDownload1")
	public ModelAndView testDownload1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		URL url = new URL("http://www.baidu.com");
		InputStream is = url.openStream();
	
		response.setHeader("Content-Disposition","attachment;filename=demo.txt");
		OutputStream out = response.getOutputStream();

		int n = 0;
		byte b[] = new byte[2*1024];
		while ((n = is.read(b)) != -1) {
			out.write(b, 0, n);
		}
		out.flush();
		out.close();
		is.close();
		return null;
	}
	
	@RequestMapping(params = "action=createTestData")
	public ModelAndView createTestData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.downloadService.createTestData();
		return null;
	}
}
