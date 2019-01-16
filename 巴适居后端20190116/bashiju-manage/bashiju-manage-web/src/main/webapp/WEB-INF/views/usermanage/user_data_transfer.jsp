<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户数据转移</title>
</head>
<body>
	<div class="layui-container">
		<form id="dataTransferForm" class="layui-form" style="margin-top:20px;"action="">
			<div class="layui-form-item" >
			<div class="layui-row">
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;text-align:left;">
						<label class="layui-form-label" style="text-align:left;">资源类型</label>
					</div>
				</div>
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;text-align:left;">
						<label class="layui-form-label" style="text-align:left;">转移数量</label>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="layui-input-inline" style="width:100px;text-align:left;">
						<label class="layui-form-label" style="text-align:left;">转维护人</label>
					</div>
				</div>
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="margin-left:30px;width:100px;">
						<label class="layui-form-label" style="text-align:left;">进淘宝池</label>
					</div>
				</div>
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;">
						<label class="layui-form-label" style="text-align:left;">转入公盘</label>
					</div>
				</div>
			</div>
			</div>
			
			<div class="layui-form-item">
			<div class="layui-row">
<!-- 			资源类型 -->
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;">
						<label class="layui-form-label" style="text-align:left;">房源</label>
					</div>
				</div>
<!-- 				转移数量 -->
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;">
						<label class="layui-form-label" style="text-align:left;">453</label>
					</div>
				</div>
<!-- 				转维护人 -->
				<div class="layui-col-md4">
					<div class="layui-input-inline" style="margin-left:13px;width:120px;">
						<input id="com3" class="layui-input"/>
					</div>
				</div>
<!-- 				进淘宝池 -->
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="margin-left:13px;width:100px;">
						<input id="com4" type="checkbox" lay-skin="primary" title="进淘宝池" />
					</div>
				</div>
<!-- 				转入公盘 -->
				<div class="layui-col-md2">
					<div class="layui-input-inline" style="width:100px;">
						<input id="com5" type="checkbox" lay-skin="primary" title="进入公盘"/>
					</div>
				</div>
				</div>
			</div>
			<!-- 按钮区域 -->
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" id="saveBtn" lay-submit lay-filter="saveBtn">提交</button>
			      	<button class="layui-btn type="reset" layui-btn-primary">重置</button>
				</div>			
			</div>
		</form>
	</div>
	
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript">
		layui.use('form',function(){
			form = layui.form;
			form.on('submit(saveBtn)',function(obj){
				$.ajax({
					method: 'post'
					,url: 'saveUserTransferData'
					,dateType: 'json'
					,data: {
						jsonData : JSON.stringify(data.field)
					}
					,success: function(data){
						if(data.success){
							layer.msg(data.msg,function(){
								layer.close(layerId);
								location.reload();
							});
						}else{
							layer.msg(data.msg);
						}
					}
					,error:function(a,b,c){
						layer.msg("系统操作异常，请联系管理员！");
					}
				});
			});
		});
	</script>
</body>
</html>