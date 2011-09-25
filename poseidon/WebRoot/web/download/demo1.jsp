<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<%@include file="/web/style.jsp" %>
	</head>
	<body style="margin:6px;">
		<form name="theForm" action="${pageContext.request.contextPath}/downloadAction.do?action=testDownload1" method="post">
			<input type="submit" value="submit">
		</form>
	</body>
</html>