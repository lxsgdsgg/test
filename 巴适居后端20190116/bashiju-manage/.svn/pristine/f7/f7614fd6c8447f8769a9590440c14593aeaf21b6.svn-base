<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分成配置管理</title>

</head>
	<body>
		<!-- 加载source.common资源 -->
		<%@include file="/common/common.jsp" %>
		<div class="layui-container">
			<form id="divideintoAddOrUpdateForm" class="layui-form" action="" style="margin-top: 20px;">
				<input type="hidden" name="configId" value="${divideInfo.id}">
				<div class="layui-form-item" >
					<label class="layui-form-label"><span style="color:red;">*</span>配置名称</label>
					<div class="layui-input-inline" style="width:150px;">
						<input class="layui-input" type="text" name="configName" required lay-verify="required"
							 placeholder="请输入配置名称" value="${divideInfo.configName}" autocomplete="off"/>
					</div>
					
					<label class="layui-form-label"><span style="color:red;">*</span>业务类型</label>
					<div class="layui-input-inline" style="width:150px;">
					    <select name="configType" required lay-verify="required">
					      <option value="">请选择...</option>
					       <c:forEach var="configtype" items="${configtype}">
						       	<c:if test="${null != divideInfo && divideInfo.configType == configtype.key}">
									<option value="${divideInfo.configType}" selected="selected">${configtype.value}</option>      	
								</c:if>
								<!-- 这里处理【新增】的时候 -->
						      	<c:if test="${divideInfo.configType == null || divideInfo.configType != configtype.key}">
									<option value="${configtype.key}">${configtype.value}</option>     
								</c:if> 		
					        </c:forEach>
					    </select> 
					</div>
					
				</div>
				<div class="layui-form-item">	
					<label class="layui-form-label"><span style="color:red;">*</span>处理类型</label>
					<div class="layui-input-inline" style="width:150px;">
					    <select name="dealType" required lay-verify="required">
					       <option value="">请选择...</option>
					       <c:forEach var="dealtype" items="${dealtype}">
						       	<c:if test="${null != divideInfo && divideInfo.dealType == dealtype.key}">
									<option value="${divideInfo.dealType}" selected="selected">${dealtype.value}</option>      	
								</c:if>
								<!-- 这里处理【新增】的时候 -->
						      	<c:if test="${divideInfo.dealType == null || divideInfo.dealType != dealtype.key}">
									<option value="${dealtype.key}">${dealtype.value}</option>     
								</c:if> 		
					        </c:forEach>
					    </select> 
					</div>
					<label class="layui-form-label"><span style="color:red;">*</span>选择城市</label>
					<div class="layui-input-inline" style="width:150px;">
					    <select name="cityId" lay-verify="cityId" required lay-verify="required">
					      	<option value="">请选择...</option>
					       	<c:forEach var="cityInfo" items="${cityInfo}">
						       	<c:if test="${null != divideInfo && divideInfo.cityId == cityInfo.key}">
									<option value="${cityInfo.key}" selected="selected">${cityInfo.value}</option>      	
								</c:if>
						      	<c:if test="${divideInfo.cityId == null || divideInfo.cityId != cityInfo.key}">
									<option value="${cityInfo.key}">${cityInfo.value}</option>     
								</c:if> 		
					        </c:forEach>
					    </select> 
					</div>
				</div>
				<!-- 按钮区域 -->
				<div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit lay-filter="divideintoAddOrUpdateForm">立即提交</button>
				      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				    </div>
				</div>
			</form>
		</div>
		<!-- script 脚本 -->
		<script>
		layui.use(['jquery','form'], function(){
			  var form = layui.form;
			  var $ = layui.$;
			  //监听提交
			  form.on('submit(divideintoAddOrUpdateForm)', function(data){
				  var url = "saveOrUpdateDivideInto";
			      $.ajax({
			    	type: "post",
			    	url: url,
			    	dataType:"json",
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