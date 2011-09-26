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
		</script>
	</head>
	<body style="margin:6px;">
		<input type="button" value="下载http文件" onclick="deal1();">
	</body>
</html>