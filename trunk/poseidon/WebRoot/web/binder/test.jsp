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
	<body style="margin:6px;">
		<form name="theForm" action="${pageContext.request.contextPath}/testAction.do?action=test" method="post">
			<table border="0">
				<tr>
					<td>testString:<input type="text" name="testString"/></td>
				</tr>
				<tr>
					<td>testDate:<input type="text" name="testDate"/></td>
				</tr>
				<tr>
					<td>testLong:<input type="text" name="testLong"/></td>
				</tr>
				<tr>
					<td>testBoo:<input type="text" name="testBoo"/></td>
				</tr>
				<tr>
					<td>
						testStringArray:
						<input type="checkbox" name="testStringArray" value="a">
						<input type="checkbox" name="testStringArray" value="b">
						<input type="checkbox" name="testStringArray" value="c">
					</td>
				</tr>
				<tr>
					<td>
						testLongArray:
						<input type="checkbox" name="testLongArray" value="1">
						<input type="checkbox" name="testLongArray" value="2">
						<input type="checkbox" name="testLongArray" value="3">
					</td>
				</tr>
			</table>
			<input type="submit" value="submit">
		</form>
	</body>
</html>