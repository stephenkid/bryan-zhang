package org.poseidon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.poseidon.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/downloadAction.do")
public class DownloadController extends BaseController {

	@RequestMapping(params = "action=testDownload1")
	public ModelAndView testDownload1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getRealPath("/");
		
		
		File file = new File(request.getRealPath("/") + "/web/download/demo.xls");
		FileInputStream fis = new FileInputStream(file);

		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=demo.xls");
		OutputStream out = response.getOutputStream();

		int n = 0;
		byte b[] = new byte[4*1024];
		while ((n = fis.read(b)) != -1) {
			out.write(b, 0, n);
		}
		out.flush();
		out.close();
		fis.close();
		return null;
	}
}
