<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
<title>过户方案配置管理</title>
</head>
<body>
	<%@include file="/common/common.jsp" %>
	<div class="layui-container">
		<form class="layui-form" id="transferProcConfForm" action="" style="margin-top: 20px;">
			<input type="hidden" name="procId" value="${transferProc.id}"/>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>选择城市</label>
				<input type="hidden" id="areaCode" name="areaCode" value="${transferProc.areaCode}"> 
				<input class="layui-input" id="areaName" name="areaName" value="${transferProc.areaName}"
					placeholder="请选择城市" autocomplete="off" required lay-verify="required" style="width:400px;">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>方案名称</label>
				<div class="layui-input-inline" style="width:400px;">
					<input class="layui-input" type="text" id="procName" name="procName" value="${transferProc.programmeName}"
						 equired lay-verify="required" autocomplete="off" placeholder="请输入方案名称"/>
				</div>
			</div>
			<!-- 按钮区域 -->
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="transferProcConfForm">立即提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
		</form>
	</div>
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
	<script>
		var cityList = JSON.parse('${cityList}');
		//适用城市控件--查询区域
		var a = new MultiSelection (cityList,function(code,name,level){
			$("#areaName").val(name);
			$("#areaCode").val(code);
			a.hidden();
		},3,$("#areaName"),false);
		
		layui.use(['jquery','form'],function(){
			var form = layui.form;
			var $ = layui.$;
			//监听提交
			form.on('submit(transferProcConfForm)', function(data){
				var url = "saveOrUpdateTranserProc";
			    $.ajax({
			   		type: "post",
			    	url: url,
			    	dataType: "json",
			    	data:{
			    		jsonData:JSON.stringify(data.field)
			    	},
			    	success:function(data){
			    		if(data.success){
			    			layer.alert(data.msg,function(index){
				    			parent.layer.closeAll();
				    			parent.location.reload();
		    				}); 
			    		}else{
			    			layer.msg(data.msg);
			    		}
			    	},
			    	error:function(a,b,c){
			    		layer.msg("系统操作异常，请稍后重试");
			    	}
			    });
			    return false;
			});
		})
	</script>
</body>
</html>