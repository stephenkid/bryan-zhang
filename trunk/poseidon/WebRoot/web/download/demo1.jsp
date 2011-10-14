<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<%@include file="/web/style.jsp" %>
		<script type="text/javascript">
			function deal1(){
				self.location.href="${pageContext.request.contextPath}/downloadAction.do?action=testDownload1";
			}

			function deal2(){
				self.location.href="${pageContext.request.contextPath}/downloadAction.do?action=testDownload2";
			}
		</script>
	</head>
	<body style="margin:6px;">
		<input type="button" value="下载http文件" onclick="deal1();">
		<br/>
		<input type="button" value="下载大容量文件" onclick="deal2();">
	</body>
</html>