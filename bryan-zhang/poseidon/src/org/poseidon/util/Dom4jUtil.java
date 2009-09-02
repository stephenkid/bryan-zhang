package org.poseidon.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jUtil {
	public static Document styleDocument(Document document, String stylePath)
			throws TransformerException, DocumentException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(
				stylePath));

		// 现在来样式化一个文档（Document）
		DocumentSource source = new DocumentSource(document);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);

		// 返回经过样式化的文档（Document）
		Document transformedDoc = result.getDocument();
		return transformedDoc;
	}

	public static String xmlToStirng(Document document) {
		return document.asXML();
	}

	public static Document stringToXml(String text) throws DocumentException {
		return DocumentHelper.parseText(text);
	}

	public static Document parse(String url) throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setEncoding("UTF-8");// 文件格式保存的文字编码
		Document document = reader.read(url);
		return document;
	}

	public static Document createDocument() {
		return DocumentHelper.createDocument();
	}

	public static XMLWriter write(Document document, OutputFormat outputFormat,
			OutputStream outputStream) throws IOException {
		if (outputStream == null)
			outputStream = System.out;
		outputFormat.setEncoding("UTF-8");// 输出格式的文字编码
		XMLWriter writer = new XMLWriter(outputStream, outputFormat);
		writer.write(document);
		writer.flush();
		return writer;
	}
	
	public static void writerXml(HttpServletResponse response,Document doc) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		OutputFormat outputFormat=OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("UTF-8");// 输出格式的文字编码
		XMLWriter writer = new XMLWriter(response.getOutputStream(), outputFormat);
		writer.write(doc);
		writer.flush();
		writer.close();
	}
	
	public static Document formList(List<Map<String, Object>> list){
		Document doc = Dom4jUtil.createDocument();
		if(list==null){
			return doc;
		}
		
		Element listElement = doc.addElement("OBJECTS");
		for (Map<String, Object> map : list) {
			Element mapElement = listElement.addElement("OBJECT");
			for (String key : map.keySet()) {
				Object value = map.get(key);
				mapElement.addElement(key).addText(value==null?"":value.toString());
			}
			
		}
		return doc;
	}
}
