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

			function deal3(){
				self.location.href="${pageContext.request.contextPath}/downloadAction.do?action=testDownload3";
			}
		</script>
	</head>
	<body style="margin:6px;">
		<div id="dlDiv" class="easyui-accordion" style="width:800px;height:300px;">
			<div title="下载http文件">
				<input type="button" value="执行" onclick="deal1();">
			</div>
			<div title="普通下载">
				<input type="button" value="执行" onclick="deal2();">
			</div>
			<div title="下载大容量文件">
				<input type="button" value="执行" onclick="deal3();">
				<table id="bigDataTb"></table>
			</div>
		</div>
	</body>
</html>