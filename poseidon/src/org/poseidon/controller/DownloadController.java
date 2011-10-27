package org.poseidon.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.nutz.json.Json;
import org.poseidon.controller.base.BaseController;
import org.poseidon.pojo.DownloadFile;
import org.poseidon.pojo.Person;
import org.poseidon.service.DownloadService;
import org.poseidon.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.poseidon.component.dataExport.XlsBottomDeal;
import org.poseidon.component.dataExport.XlsExportor;
import org.poseidon.component.dataExport.XlsHeadDeal;
import org.poseidon.dto.DownloadFileDto;
import org.poseidon.dto.PersonDto;

@Controller
@RequestMapping("/downloadAction.do")
public class DownloadController extends BaseController {

	@Resource(name = "downloadService")
	private DownloadService downloadService;
	
	@Resource(name = "XlsExportor")
    private XlsExportor XlsExportor;
	
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
	    Person cond = new Person();
	    cond.setAge(28);
		List<Person> list = this.downloadService.findPerson(cond);
		System.out.println(list.size());
		
		LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("age", "年龄");
		headMap.put("createTime", "创建日期");
		headMap.put("title", "职称");
		headMap.put("name", "姓名");
		
		Map<Integer, Integer> colWidthMap = new HashMap<Integer, Integer>();
		colWidthMap.put(0, 6000);
		colWidthMap.put(1, 6000);
		colWidthMap.put(2, 6000);
		colWidthMap.put(3, 6000);
		
		HSSFWorkbook wb = this.XlsExportor.convertList(list, headMap, colWidthMap, new XlsHeadDeal() {
            public Integer dealHead(HSSFSheet sheet, Integer rowCnt) throws Exception {
                HSSFRow curRow = sheet.createRow(rowCnt++);
                curRow.createCell(0).setCellValue(new HSSFRichTextString("我是头部信息"));
                curRow = sheet.createRow(rowCnt++);
                curRow.createCell(0).setCellValue(new HSSFRichTextString("我是头部信息2"));
                return rowCnt;
            }
        }, new XlsBottomDeal() {
            public Integer dealBottom(HSSFSheet sheet, Integer rowCnt) throws Exception {
                HSSFRow curRow = sheet.createRow(rowCnt++);
                curRow.createCell(0).setCellValue(new HSSFRichTextString("我是尾部信息"));
                return rowCnt;
            }
        });
		response.setHeader("Content-Disposition","attachment;filename=demo.xls");
		wb.write(response.getOutputStream());
		return null;
	}
	
	@RequestMapping(params = "action=testDownload3")
    public ModelAndView testDownload3(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    String message = null;
	    try{
	        this.downloadService.generateBigDataFile();
	        message = "提交成功";
	    }catch(Throwable t){
	        t.printStackTrace();
	        message = "提交失败";
	    }
	    ServletUtil.writerText(response, message);
        return null;
	}
	
	
	@RequestMapping(params = "action=createTestData")
	public ModelAndView createTestData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		this.downloadService.createTestData();
		return null;
	}
	
	@RequestMapping(params = "action=listDownloadFile")
    public ModelAndView listDownloadFile(HttpServletRequest request,HttpServletResponse response, DownloadFileDto dto) throws Exception {
	    int page = Integer.parseInt(request.getParameter("page"));
	    int rows = Integer.parseInt(request.getParameter("rows"));
        Map<String, ?> dataMap = this.downloadService.findDownloadFileList(dto, page, rows);
        ServletUtil.writerJson(response, Json.toJson(dataMap));
        return null;
    }
}
