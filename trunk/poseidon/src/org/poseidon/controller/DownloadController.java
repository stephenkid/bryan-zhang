package org.poseidon.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.poseidon.component.dataExport.DataExport;
import org.poseidon.controller.base.BaseController;
import org.poseidon.pojo.Person;
import org.poseidon.service.DownloadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/downloadAction.do")
public class DownloadController extends BaseController {

	@Resource(name = "downloadService")
	private DownloadService downloadService;
	
	@Resource(name = "XlsExportImpl")
	private DataExport dataExport;
	
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
	
	@RequestMapping(params = "action=testDownload2")
	public ModelAndView testDownload2(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Person> pList = this.downloadService.findTotalPerson();

		LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("id", "序号");
		headMap.put("name", "姓名");
		headMap.put("age", "年龄");
		headMap.put("address", "地址");
		headMap.put("mobile", "手机");
		headMap.put("email", "电子邮件");
		headMap.put("company", "公司");
		headMap.put("title", "职位");
		headMap.put("createTime", "创建时间");
		HSSFWorkbook wb = dataExport.convertList(pList, headMap);
		wb.write(response.getOutputStream());
		return null;
	}
	
	@RequestMapping(params = "action=createTestData")
	public ModelAndView createTestData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.downloadService.createTestData();
		return null;
	}
	
	
}
