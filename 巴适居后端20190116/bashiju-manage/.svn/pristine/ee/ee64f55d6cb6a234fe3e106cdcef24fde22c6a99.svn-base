<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经纪人能力认定新增页面</title>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" style="margin-top: 20px;">
	 <input type="hidden" name="id"  value="${agentAbilityidentifyInfo.id}" autocomplete="off" class="layui-input">
	  <div class="layui-form-item">
	  	   <label class="layui-form-label"><span style="color:red;">*</span>考核项目名称</label>
		    <div class="layui-input-inline">
   		         <input type="text" name="name" required lay-verify="required" placeholder="考核项目名称" value="${agentAbilityidentifyInfo.name} autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  
	    <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>经纪人名称:</label>
		    <div class="layui-input-inline">
<!-- 		         <input type="text" name="agentId" required lay-verify="required" placeholder="操作人"  autocomplete="off" class="layui-input"> -->
		  		<select  id="agentId">
		  			<c:forEach var="agentInfo" items="${agentInfo}">
		  					<c:if test="${agentAbilityidentifyInfo.agentId==agentInfo.agentId}">
				  					<option value="${agentInfo.agentId}_${agentInfo.userId}_${agentInfo.userName}" selected="selected">${agentInfo.userName}</option>
		  					</c:if>
	  						<c:if test="${agentAbilityidentifyInfo.agentId!=agentInfo.agentId}">
				  					<option value="${agentInfo.agentId}_${agentInfo.userId}_${agentInfo.userName}">${agentInfo.userName}</option>
		  					</c:if>
		  			</c:forEach>
		  			
		  		</select>
		    </div>
	  </div>
	  <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>考核成绩:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="achievement" required lay-verify="required" placeholder="考核成绩" value="${agentAbilityidentifyInfo.achievement}"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>考考核项目简介:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="introduction" required lay-verify="required" placeholder="考核项目简介" value="${agentAbilityidentifyInfo.introduction}"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="blackListForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
layui.use('form', function(){
	  var form = layui.form;
	  var $ = layui.$ //重点处
	  ,layer = layui.layer;
	  //监听提交
	  form.on('submit(blackListForm)', function(data){
	
		var agentInfo =  $("#agentId").val()
		var agentId = agentInfo.split("_")[0];
		var userId = agentInfo.split("_")[1];
		var userName = agentInfo.split("_")[2];
		data.field.agentId=agentId;
		data.field.userId=userId;
		data.field.userName=userName;
		alert(JSON.stringify(data.field))
	    $.ajax({
	    	type:"post",
	    	url:"saveOrUpdateAgentAbilityidentify",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(data.field)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	  });
});
</script>
</body>
</html>