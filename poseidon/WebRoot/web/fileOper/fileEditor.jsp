<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.poseidon.util.FileUtil"%>
<%@page import="java.io.IOException"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript">
			function doRead(){
				theForm.submit();
			}
		</script>
	</head>
	<body style="margin:6px;">
		<%
			String fileUrl = request.getParameter("fileUrl");
			String content = null;
			String errorInfo = null;
			if (StringUtils.isNotEmpty(fileUrl)){
				try{
					content = FileUtil.readFile2String(fileUrl);
				}catch(IOException e){
					e.printStackTrace();
					errorInfo = e.getMessage();
				}
			}
			request.setAttribute("fileUrl",fileUrl == null?"":fileUrl);
			request.setAttribute("content",content == null?"":content);
			request.setAttribute("errorInfo",errorInfo == null?"":content);
		%>
	
		<form name="theForm" action="fileEditor.jsp" method="post">
			<table border="0">
				<tr>
					<td>文件路径：<input type="text" name="fileUrl" value="<%=request.getAttribute("fileUrl") %>" size="50">
					<input type="button" value="读取" onclick="doRead()"/></td>
				</tr>
				<tr>
					<td><font color="red"><%=request.getAttribute("errorInfo") %></font></td>
				</tr>
				<tr>
					<tr>
						<td>文件内容：</td>
					</tr>
					<tr>
						<td><textarea rows="30" cols="100"><%=request.getAttribute("content") %></textarea></td>
					</tr>
				</tr>
				<tr>
					
				</tr>
			</table>
		</form>
	</body>
</html>