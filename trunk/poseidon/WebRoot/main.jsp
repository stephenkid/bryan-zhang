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
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript">
			window.onbeforeunload=function(){
				var urlStr="${pageContext.request.contextPath}/loginAction.do";
				var dataStr="action=loginOff";

				$.ajax({
		    		url: urlStr,
		    		data:dataStr,
		    		cache: false,
		    		async: false
		        });

				window.close();
			}
			
		</script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/web/binder/test.jsp" target="appFrame"><font size="2">数据Form绑定</font></a>
		&nbsp;
		<a href="${pageContext.request.contextPath}/web/fileOper/fileEditor.jsp" target="appFrame"><font size="2">文件修改</font></a>
		&nbsp;
		<a href="${pageContext.request.contextPath}/web/download/demo1.jsp" target="appFrame"><font size="2">文件下载</font></a>
		
		
		<hr>
		<iframe id="appFrame" name="appFrame" style="position:absolute;top:50pt;left:0px;height:100%;width:100%;background-color:#000;" frameborder="0"/>
	</body>
</html>