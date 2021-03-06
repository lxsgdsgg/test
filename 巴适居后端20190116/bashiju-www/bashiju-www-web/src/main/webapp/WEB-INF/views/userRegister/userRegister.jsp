
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
.welcome {
    font-size: 30px;
    margin-bottom: 20px;
}


body .side {
    position: fixed;
    top: 0px;
    left: 0;
    height: 100%;
    width: 480px;
    float: left;
    overflow: hidden;
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
/*     background-image: url(http://4.url.cn/zc/v3/img/01-2.jpg); */
}

</style>
</head>
<body>

<div class="side" id="side" data-bind="style:{backgroundImage:currentSideUrl()}" style="background-image: url(http://4.url.cn/zc/v3/img/01-4.jpg);">

</div>

	
	<div style="margin: 278px auto;margin-left: 750;" class="layui-form">
	<div class="welcome">欢迎注册</div>
		<form class="#">
		<fieldset style="width: 400px;border-color: white;">
		  <div class="layui-form-item">
 		 	<label class="layui-form-label">昵称：</label>
		    <div class="layui-input-inline">
		   		<input type="text" name="username" id="username"  autocomplete="off" class="layui-input" lay-verify="required" >
		    </div>
	 	</div>
	 	<div  class="layui-form-item">
 		  	<label class="layui-form-label">电话：</label>
		    <div class="layui-input-inline">
		   		<input type="number" name="mobile" id="mobile"  autocomplete="off" class="layui-input" lay-verify="phone|number" onchange="queryIsExistMobile(this.value)" >
		    </div>	
		    <span style="color:red;" id="errorMsg"></span>
	 	</div>
 		<div  class="layui-form-item">
 			<label class="layui-form-label">短信验证码：</label>
		    <div class="layui-input-inline">
		   		<input type="text" name="solt" id="solt"  autocomplete="off" class="layui-input" style="width: 100px" lay-verify="required"><br>
		    </div>	
		     <span id="mobileTip" style="color:red"></span>
		      <div class="layui-input-inline">
	 			<input class="layui-btn" type="button" id="btnSendCode" value="获取验证码">
		    </div>	
	 	</div>
	 	<div  class="layui-form-item">
		  	<label class="layui-form-label">密码：</label>
		    <div class="layui-input-inline">
		   		<input type="password" name="password" id="password"  autocomplete="off" class="layui-input" lay-verify="required">
		    </div>	
	 	</div>
	 	
	   <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="register">立即注册</button>
	    </div>
	  </div>
	 	</fieldset>
		</form>
	</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
//验证码倒计时
//时间
var wait = 5;
 function time(btn,p){//btn 按钮,o为按钮的对象，p为可选，这里是60秒过后，提示文字的改变 
	if(wait == 0){
		btn.removeAttr("disabled");
		btn.val("获取验证码");//改变按钮的值
// 		p.text("如果您在1分钟内没有收到验证码，请检查您填写的手机号码是否正确或重新发送"); 
		wait = 5;
	}else{
		btn.attr("disabled",true);//倒计时过程中按钮禁止点击
		btn.val(wait+"秒");//改变按钮中的值
		wait--;
		setTimeout(function(){
			time(btn,p)
		},1000)
	}
}
 function queryIsExistMobile(obj){
	 $.ajax({
	    	type:"post",
	    	url:"queryIsExistMobile",
	    	dataType:"json",
	    	data:{mobile:obj},
	    	success:function(data){
	    		if(data.success){
	    			$("#errorMsg").text("");
	    		}else{
	    			$("#errorMsg").text(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
 }
layui.use(['form','carousel'], function(){
	  var form = layui.form;
	  var $ = layui.$;
	  var carousel = layui.carousel;
	  $("#btnSendCode").click(function(){
		  var btn = $("#btnSendCode");
		  var span = $("#mobileTip");
		  time(btn,span)
		  //手机号码
		 var mobile = $("#mobile").val();
		  $.ajax({
		    	type:"post",
		    	url:"sendMsg",
		    	data:{mobile:mobile},
		    	success:function(data){
		    		debugger;
		    		if(data.success){
		    			layer.msg('发送成功');
// 		    			layer.msg(data.msg);
		    		}else{
		    			layer.alert("该手机号码每天发送短息次数达到上限");
		    		}
		    	},
		    	error:function(a,b,c){
		    		layer.alert("操作异常，请稍后重试");
		    	}
		    })
		    return false;
	    })
	  form.on('submit(register)', function(data){
		  //手机验证码
			 var checkCode = $("#solt").val()
			 if(checkCode==""){
				 $("#mobileTip").text("请输入验证码")
				 return false;
			 }
		   $.ajax({
		    	type:"post",
		    	url:"regCustomer",
		    	dataType:"json",
		    	contentType: 'application/json',
		    	data:JSON.stringify(data.field),
		    	success:function(data){
		    		if(data.success){
		    			layer.alert(data.msg);
		    		}else{
		    			layer.alert(data.msg);
		    		}
		    	},
		    	error:function(a,b,c){
		    		layer.alert("操作异常，请稍后重试");
		    	}
		    });
			return false;
	  })
})
</script>
</body>
</html>