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
		
		<title>Poseidon测试平台</title>
		<style type="text/css" media="screen">
			@import "${pageContext.request.contextPath}/css/style.css";
		</style>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script language="JavaScript">
			var win=null;
			
			function submitForm(){
				if($("#loginEmail").val()==""){
	    			alert("请输入您的帐号。");
	    			$("#loginEmail").focus();
				}else if($("#loginPassword").val()==""){
	    			alert("请输入您的密码。");
	    			$("#loginPassword").focus();
				}else{
					var sb=$("#submitButton");
					var sbValue=sb.val()
					sb.attr("disabled","true");
					sb.attr("value","验证中..");
					sb.css("cursor","wait");


					var urlStr="${pageContext.request.contextPath}/loginAction.do";
					dataStr="action=login&validate=validate&loginEmail="+$("#loginEmail").val()+"&loginPassword="+$("#loginPassword").val();
					var returnStr = $.ajax({
						type: "POST",
			    		url: urlStr,
			    		data:dataStr,
			    		cache: false,
			    		async: false
			        }).responseText;

					sb.attr("disabled","");
					sb.attr("value",sbValue);
					sb.css("cursor","default");

					if(returnStr == "wrongLogin"){
						alert("您输入的用户名或密码有误，请重新输入。");
					}else if(returnStr == "invalidLogin"){
						alert("您的账号目前不可用，请联系管理员并激活。");
					}else if (returnStr == "hasLogined"){
						if (window.confirm("您的账号正在登录中，要强制注销并登录吗？")){
							openWin();
						}
					}else if(returnStr == "success"){
						openWin();
					}
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
		<form action="${pageContext.request.contextPath}/loginAction.do?action=login" method="post" name="theForm" target="win" style="width: 808;height: 524;">
			<table cellspacing="0" cellpadding="0" style="margin-top:400px;" width="200px">
				<tr>
					<td style="font-size:13px;color:#000000;">帐&nbsp;号：</td>
					<td>
						<input type="text" id="loginEmail" name="loginEmail" class="inputtext" value="" onkeypress="checkButton();" onfocus="this.select();" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td style="font-size:13px;color:#000000;">密&nbsp;码：</td>
					<td>
						<input id="loginPassword" name="loginPassword" type="password" class="inputtext" value="" onkeypress="checkButton();" onfocus="this.select();" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;padding-top:5px;">
						<input type="button" id="submitButton" value="&nbsp;登&nbsp;录&nbsp;" onclick="submitForm();" class="buttonStyle" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
