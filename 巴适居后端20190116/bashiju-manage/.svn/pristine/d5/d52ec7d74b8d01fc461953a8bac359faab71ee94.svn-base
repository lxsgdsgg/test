<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>

</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="menuForm" style="margin-top: 20px;">
		<input type="hidden" name="id" value="${menu.id}"/>
	  <div class="layui-form-item">
	    <label class="layui-form-label">菜单名称</label>
	    <div class="layui-input-inline">
	      <input type="text" name="name" required lay-verify="required" placeholder="请输入菜单名称" value="${menu.name}" autocomplete="off" class="layui-input">
	    </div>
	    
	    <label class="layui-form-label">菜单等级</label>
	    <div class="layui-input-inline">
	    	<select name="level" id="level" lay-verify="required" lay-filter="level">
	    	<c:forEach var="level" items="${MenuLevelEnum}">
	    		<c:if test="${menu.level==level.key}">
		        	<option value="${level.key}" selected="selected">${level.value}</option>
		        </c:if>
		        <c:if test="${menu.level!=level.key}">
		        	<option value="${level.key}">${level.value}</option>
		        </c:if>
	    	</c:forEach>
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">上级菜单</label>
	    <div class="layui-input-inline">
	      <select name="parentId" lay-verify="required" id="parentIdSelect">
	      </select>
	    </div>
	    
	    <label class="layui-form-label">菜单类型</label>
	    <div class="layui-input-inline">
	    	<select name="functionType" lay-verify="required">
	    	<c:forEach var="functionType" items="${MenuFunctionTypeEnum}">
	    		<c:if test="${menu.functionType==functionType.key}">
		        	<option value="${functionType.key}" selected="selected">${functionType.value}</option>
		        </c:if>
		        <c:if test="${menu.functionType!=functionType.key}">
		        	<option value="${functionType.key}">${functionType.value}</option>
		        </c:if>
	    	</c:forEach>
	      </select>
	    </div>
	  </div>
	  <div id="toggleDiv">
	  	<div class="layui-form-item" id="urlDiv">
		    <label class="layui-form-label">菜单路径</label>
		    <div class="layui-input-inline">
		      <input type="text" name="url" id="url" required lay-verify="required" placeholder="请输入菜单路径" value="${menu.url}" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">排序码</label>
		    <div class="layui-input-inline">
		    	<input type="number" name="sortNo" required lay-verify="required" placeholder="请输入排序码" value="${menu.sortNo}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item"id="btnDiv">
		    <label class="layui-form-label">按钮名称</label>
		    <div class="layui-input-inline">
		    	<input type="text" name="btnName" id="btnName" required lay-verify="required" placeholder="请输入按钮名称" value="${menu.btnName}" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">排序码</label>
		    <div class="layui-input-inline">
		    	<input type="number" name="sortNo" required lay-verify="required" placeholder="请输入排序码" value="${menu.sortNo}" autocomplete="off" class="layui-input">
		    </div>
		  </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">可视角色组</label>
	    <div class="layui-input-inline">
	    	<select xm-select="select" xm-select-type="2">
	    	<option value="">所有</option>
	    	<c:forEach var="roleGroup" items="${enumMap}">
	        	<option value="${roleGroup.key}">${roleGroup.value}</option>
	    	</c:forEach>
	      </select>
	    </div>
	    <label class="layui-form-label">数据权限标识</label>
	    <div class="layui-input-inline">
	      <input type="checkbox" lay-filter="dataPerFlagSw" lay-skin="switch" ${menu.dataPerFlag==1?'checked':null} lay-text="是|否">
	    	<input type="hidden" name="dataPerFlag" id="dataPerFlag" value="${menu!=null?menu.dataPerFlag:0}"/>
	    </div>
<!-- 	    <div class="layui-form-mid layui-word-aux">是表示需要数据权限的限制条件，否则不需要</div> -->
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="menuForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script>
var parents = ${parents};
var btnDiv = $("#btnDiv").clone();
var urlDiv = $("#urlDiv").clone();
//No.1 定义拓展模块的路径
layui.config({
	base: '${request.getContextPath()}/src/common/js/'
}).extend({
	formSelects: 'formSelects-v3'
});
layui.use(['form','element', 'formSelects'], function(){
  var form = layui.form;
  var element = layui.element;
  var formSelects = layui.formSelects;
  //监听提交
  form.on('submit(menuForm)', function(data){
	  data.field.roleGroup = formSelects.value("select",'val').join(",");
//     layer.alert(JSON.stringify(data.field));
//     return false;
    $.ajax({
    	type:"post",
    	url:"saveOrUpdateMenu",
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
  
  /**
  *获取父级菜单信息(根据菜单等级筛选父级菜单)
  *
  */
  function getParents(value,selectedId){
		if(parents!=null && value!=""){
			var html = "";
			for(var i=0;i<parents.length;i++){
				var obj = parents[i];
				if(obj.level==(value-1<0?value:(value-1))){
					if(obj.id==selectedId)
						html+='<option value="'+obj.id+'" selected="selected">'+obj.name+'</option>';
					else
						html+='<option value="'+obj.id+'">'+obj.name+'</option>';
				}
			}
			if(value==4){
				$("#toggleDiv").html(btnDiv);
			}else{
				$("#toggleDiv").html(urlDiv);
			}
			$("#parentIdSelect").html(html);
			form.render('select');
			
		}
	}
  
  getParents("${menu.level}","${menu.parentId}");
  //监听菜单等级选择操作
  form.on('select(level)', function(data){
	  getParents(data.value,"");
	}); 
  
 //监听数据权限标识操作
  form.on('switch(dataPerFlagSw)', function(data){
	  if(data.value=="on")
		  $("#dataPerFlag").val("1");
	  else
		  $("#dataPerFlag").val("0");
	}); 
  formSelects.value("select",'${menu.roleGroup}'.split(","));
});
</script> 
</body>
</html>