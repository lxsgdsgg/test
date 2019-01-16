<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.alibaba.druid.sql.visitor.functions.If"%>

<%request.setAttribute("path", request.getContextPath()+"/src/sso-platform");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="${path}/css/login.css" type="text/css"></link>

</head>
<body class="body_login">
		<div class="login_logo"></div>
		<div class="login_panel">
			<div id="login_t"><span class="login_input"><input id="uphone" type="text" placeholder="手机" value="13577000337"/></span></div>
			<div id="login_t"><span class="login_input"><input id="upass" type="password" placeholder="密码" value="123456"/></span></div>
			<% if("1".equals(request.getAttribute("showVerify"))){ %>
				<div id="login_t"><span class="login_input">
				<input type="text" id="ucode"  placeholder="输入验证码" >
				<img src="http://sso.bashiju.com/sso/verifyCode" onclick="javascript:this.setAttribute('src', 'http://sso.bashiju.com/sso/verifyCode?' + Math.random())" style="cursor:pointer"/>
				</span>
				</div>
			<%}%>
			<div><input type="button" id="login_btn" class="login_btn" value="登录" onclick="login()"></div>
		</div>
</body>
<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8" charset="utf-8"></script>
<script type="text/javascript" src="${path}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	/* 敲回车提交 */
	window.onkeydown=function(e){
		if(e.keyCode==13){
			login();
		}}
	function login(){
		if($("#uphone").val()==""){
			$("#ucode").focus();
			return;
			}			
		else if($("#upass").val()=="") {
			$("#ucode").focus();
			return;
		}
		else if($("#ucode")){
			if($("#ucode").val()==""){
				$("#ucode").focus();
				return;
			}
		}
		/* var upass=md5($("#upass").val()); */
		$.ajax({
			type:"POST",
			url:"doLogin",
			data:{"userPhone":$("#uphone").val(),"userPass":$("#upass").val(),"userIp":returnCitySN["cip"]
		,"areaCode":returnCitySN["cid"],"areaName":returnCitySN["cname"],"code":$("#ucode").val()},
			success:function(result){
				if(result.success){
					window.location.href="main/index";
					}
				else{
					alert(result.msg)
					window.location.href="login";
				}
			},error:function(result){
				alert(result.success);
			}			
		});
	}	
</script>
</html>