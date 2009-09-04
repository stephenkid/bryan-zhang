<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html:html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		
		<title>Poseidon测试平台</title>
		<style type="text/css" media="screen">
			@import "${pageContext.request.contextPath}/css/style.css";
		</style>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/prototype.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poseidon.js"></script>
		<script language="JavaScript">
			var win=null;
			
			Event.observe(window, 'load', function(){
				var loginEmail = getCookie("loginEmail");
				var loginPassword = getCookie("loginPassword");
				if (loginEmail != null && loginPassword != null){
					theForm.loginEmail.value = loginEmail;
					theForm.loginPassword.value = loginPassword;
					openWin();
				}
			});
			
			function submitForm(){
				if($F("loginEmail")==""){
	    			alert("请输入您的帐号。");
	    			$("loginEmail").focus();
				}else if($F("loginPassword")==""){
	    			alert("请输入您的密码。");
	    			$("loginPassword").focus();
				}else{
					var sb=$("submitButton");
					var sbValue=sb.value;
					sb.disabled="true";
					sb.value="验证中..";
					sb.style.cursor="wait";
					var url="${pageContext.request.contextPath}/loginAction.do";
					pars="method=loginMethod&validate=validate&loginEmail="+$F("loginEmail")+"&loginPassword="+$F("loginPassword");
					new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: function(originalRequest){
						sb.disabled="";
						sb.value=sbValue;
						sb.style.cursor="default";
						var text=originalRequest.responseText;
						if(text == "wrongLogin"){
							alert("您输入的用户名或密码有误，请重新输入。");
						}else if(text == "invalidLogin"){
							alert("您的账号目前不可用，请联系管理员并激活。");
						}else if (text == "hasLogined"){
							if (window.confirm("您的账号正在登录中，要强制注销并登录吗？")){
								openWin();
							}
						}else if(text == "success"){
							openWin();
						}
					}});
				}
			}
			
			function openWin(){
				if(win!=null&&!win.closed){
					win.close();
				}
				win=window.open('','win','resizable=yes,scrollbars=no,status=yes,toolbar=no,menubar=no,location=no');
				win.moveTo(0,0);
				win.resizeTo(screen.availWidth,screen.availHeight);
				theForm.submit();
				win.focus();
			}
			
			function checkButton() {
				if(event.keyCode==13){
					submitForm();
				}
			}
		</script>
	</head>
	<body style="text-align: center;">
		<form action="${pageContext.request.contextPath}/loginAction.do?method=loginMethod" method="post" name="theForm" target="win" style="width: 808;height: 524;">
			<table cellspacing="0" cellpadding="0" style="margin-top:400px;" width="200px">
				<tr>
					<td style="font-size:13px;color:#000000;">帐&nbsp;号：</td>
					<td>
						<input type="text" name="loginEmail" class="inputtext" value="" onkeypress="checkButton();" onfocus="this.select();" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td style="font-size:13px;color:#000000;">密&nbsp;码：</td>
					<td>
						<input name="loginPassword" type="password" class="inputtext" value="" onkeypress="checkButton();" onfocus="this.select();" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;padding-top:5px;">
						<input type="checkbox" name="isSaveLoginInfo" value="Y">保存登录信息
						<input type="button" id="submitButton" value="&nbsp;登&nbsp;录&nbsp;" onclick="submitForm();" class="buttonStyle" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html:html>
