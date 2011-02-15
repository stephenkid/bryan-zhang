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
				theForm.action = "fileEditor.jsp?type=read";
				theForm.submit();
			}
		</script>
	</head>
	<body style="margin:6px;">
		<%
			String type = request.getParameter("type");
			if (type == null || type.equals("read")){
				String readFileUrl = request.getParameter("readFileUrl");
				String content = null;
				String errorInfo = null;
				if (StringUtils.isNotEmpty(readFileUrl)){
					try{
						content = FileUtil.readFile2String(readFileUrl);
					}catch(IOException e){
						e.printStackTrace();
						errorInfo = e.getMessage();
					}
				}
				request.setAttribute("readFileUrl",readFileUrl == null?"":readFileUrl);
				request.setAttribute("content",content == null?"":content);
				request.setAttribute("errorInfo",errorInfo == null?"":content);
			}else if(type.equals("write")){
				
			}
		%>
	
		<form name="theForm" action="fileEditor.jsp" method="post">
			<table border="0">
				<tr>
					<td>文件路径：<input type="text" name="readFileUrl" value="<%=request.getAttribute("readFileUrl") %>" size="50">
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
					<td>
						保存为：<input type="text" name="writeFileUrl" value="<%=request.getAttribute("writeFileUrl") %>" size="50">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>