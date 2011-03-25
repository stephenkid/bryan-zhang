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

			function doWrite(){
				theForm.action = "fileEditor.jsp?type=write";
				theForm.submit();
			}
		</script>
	</head>
	<body style="margin:6px;">
		<%
			String type = request.getParameter("type");
			String readFileUrl = request.getParameter("readFileUrl");
			String writeFileUrl = request.getParameter("writeFileUrl");
			String content = request.getParameter("content");
			
			if (type != null && type.equals("read")){
				if (StringUtils.isNotEmpty(readFileUrl)){
					content = FileUtil.readFile2String(readFileUrl);
				}
			}else if(type != null && type.equals("write")){
				if (StringUtils.isNotEmpty(writeFileUrl)){
					FileUtil.writeString2File(content,writeFileUrl);
				}
			}
			
			request.setAttribute("readFileUrl",readFileUrl == null?"":readFileUrl);
			request.setAttribute("writeFileUrl",writeFileUrl == null?"":writeFileUrl);
			request.setAttribute("content",content == null?"":content);
		%>
	
		<form name="theForm" action="fileEditor.jsp" method="post">
			<table border="0">
				<tr>
					<td>文件路径：<input type="text" name="readFileUrl" value="<%=request.getAttribute("readFileUrl") %>" size="50">
					<input type="button" value="读取" onclick="doRead();"/></td>
				</tr>
				<tr>
					<td>文件内容：</td>
				</tr>
				<tr>
					<td><textarea name="content" rows="30" cols="100"><%=request.getAttribute("content") %></textarea></td>
				</tr>
				<tr>
					<td>
						保存为：<input type="text" name="writeFileUrl" value="<%=request.getAttribute("writeFileUrl") %>" size="50">
						<input type="button" value="保存" onclick="doWrite();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>