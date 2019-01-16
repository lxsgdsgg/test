<%@page import="javax.activation.DataSource"%>
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
<div style="margin-right:20px;margin-top: 40px">
<form class="layui-form">
			<input type="hidden" name="id" value="${datas.id}"/>
		 <div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>部门名称</label>
			    <div class="layui-input-inline">
						<input class="layui-input" name="name"  value="${datas.name}">   		
			    </div>
		  </div>
	  <!--上级id（ParentId）对应着部门的名称，如果想选中的上级id(ParentId),那么可以通过id来得到-->
	  <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>上级部门</label>
		    <div class="layui-input-inline">
		      <select name="parentId" lay-verify="parentId">
		      <option value="">请选择</option>
		       <c:forEach var="parentType" items="${deptTypeName}">
		       		<!--如果当前的上级编号==部门中已有的编号，那么就显示在页面上-->
			       	<c:if test="${datas.parentId==parentType.id}">
						<option value="${parentType.id}" selected="selected">${parentType.name}</option>      	
					</c:if>	
					<!-- 如果当前上级编号不等于部门编号就显示,或者当前编号不等于部门的编号就显示)-->
			      	<c:if test="${datas.parentId!=parentType.id && datas.id !=parentType.id}">
						<option value="${parentType.id}">${parentType.name}</option>     
					</c:if> 		
		        </c:forEach>
		       
		      </select>
		    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="deptTypeForm">立即提交</button>
	      <button type="reset" class="layui-btn">重置</button>
	    </div>
	  </div>
</form>
</div>
<%@include file="/common/common.jsp" %>
<script>
layui.use('form', function(){
  var form = layui.form;
  form.verify({
	  name: function(value, item){ //value：表单的值、item：表单的DOM对象
		  if(value=='' || value.trim()==''){
		  		return "部门名称不允许为空";
		  	}
	  }
  	,
  
  	parentId: function(value, item){ 
	  	if(value==''||item.parentId==item.name){
	  		return "上级名称空不能为空";
	  	}
  	  }
	}); 
  
  //监听提交
  form.on('submit(deptTypeForm)', function(data){
	  
    $.ajax({
    	async:false,
    	type:"post",
    	url:"saveOrUpdateDeptType",
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

//执行一个laydate实例

</script>
<script>
</script> 
</body>
</html>