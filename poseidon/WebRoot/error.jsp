<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
	</head>
	<body>
		<%
			Exception e = (Exception)request.getAttribute("exception");
			e.printStackTrace();
		%>
		<p>这是error页面</p>
		<p>exception描述:</p>
		<c:out value="${exception}"/>
		<c:forEach var="item" items="${exception.stackTrace}">
			<c:out value="${item}"/>
			<br/>
		</c:forEach>
	</body>
</html>