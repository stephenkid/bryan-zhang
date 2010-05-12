<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/ajaxanywhere.tld" prefix="aa"%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		
		<style type="text/css" media="screen">
			@import "${pageContext.request.contextPath}/css/style.css";
		</style>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
		<script type="text/javascript">
			window.onbeforeunload=function(){
				var url="${pageContext.request.contextPath}/loginAction.do";
				var pars="method=loginOffMethod";
				new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: function(originalRequest){
					window.close();
				}});
			}
		</script>
	</head>
	<body style="margin:6px;">
		<form name="theForm">
			这是主页面
		</form>
	</body>
</html>