<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form class="layui-form" action="" id="roleForm">
	<input type="hidden" id="roleId" name="id" value="${role.id}"/>
  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>角色名称</label>
    <div class="layui-input-inline">
      <input type="text" name="name" value="${role.name}"  lay-verify="name" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <c:if test="${!empty roleGroup}">
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>角色组</label>
	    <div class="layui-input-inline">
	      <select name="groups" lay-verify="groups">
	        <c:forEach var="groups" items="${roleGroup}">
	        	<c:if test="${groups.key == role.groups}">
	        		<option value="${groups.key}" selected="selected">${groups.value}</option>
	        	</c:if>
	        	<c:if test="${groups.key != role.groups}">
	        		<option value="${groups.key}">${groups.value}</option>
	        	</c:if>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
  </c:if>
  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>所属公司</label>
    <div class="layui-input-inline">
      <select name="companyId" lay-verify="companyId">
        <c:forEach var="company" items="${companys}">
        	<c:if test="${company.id == role.companyId}">
        		<option value="${company.id}" selected="selected">${company.name}</option>
        	</c:if>
        	<c:if test="${company.id != role.companyId}">
        		<option value="${company.id}">${company.name}</option>
        	</c:if>
        </c:forEach>
      </select>
    </div>
  </div>
  <div class="layui-form-item" class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>排序码</label>
    <div class="layui-input-inline">
      <input type="number" name="sortNo" value="${role.sortNo}"  lay-verify="sortNo" placeholder="请输入排序码" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="roleForm">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
<%@include file="/common/common.jsp" %>
<script>
//Demo

layui.use('form', function(){
  var form = layui.form;
  
  form.verify({
	  name: function(value, item){ //value：表单的值、item：表单的DOM对象
		  if(value=='' || value.trim()==''){
		  		return "角色名称不允许为空";
		  	}
	  }
  	  ,companyId: function(value, item){ 
	  	if(value==''){
	  		return "请选择所属公司";
	  	}
  	  }
  	,sortNo: function(value, item){ 
	  	if(value==''){
	  		return "排序码不能为空";
	  	}
  	  }
	}); 
  
  //监听提交
  form.on('submit(roleForm)', function(data){
    $.ajax({
    	type:"post",
    	url:"saveOrUpdateRole",
    	dataType:"json",
    	data:{jsonData:JSON.stringify(data.field)},
    	success:function(dt){
    		if(dt.success){
    			layer.alert(dt.msg,function(){
    				parent.location.reload();
	     			parent.layer.closeAll();
     			});
    		}else{
    			layer.alert(dt.msg);
    		}
    	},
    	error:function(a,b,c){
    		layer.alert("操作异常，请稍后重试");
    	}
    });
    return false;
  });
  
  form.on('switch(isvalid)', function(data){
	  $("#isvalid").val(this.checked ? '1' : '0');
	});  
});
</script>
<script>
</script> 
</body>
</html>