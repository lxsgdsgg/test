<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>费用类型新增页面</title>
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="financeCostTypeAdd" style="margin-top: 20px;" name="financeCostTypeAdd">
		  <div class="layui-form-item">
			 <label class="layui-form-label"><span style="color:red;">*</span>选择城市</label>
			    <div class="layui-input-inline">
					<input type="text" name="cityName" id="cityName"  required  lay-verify="required" placeholder="城市名称" autocomplete="off" class="layui-input" style="width:200px">
			   		<input type="text" name="cityCode" id="cityCode"  autocomplete="off" class="layui-input" style="width:200px;display: none">
			    </div>
			 </div>
		  <div class="layui-form-item">
	 		 	<label class="layui-form-label"><span style="color:red;">*</span>费用类型</label>
			    <div class="layui-input-inline">
					<input type="text"  name="name" required  lay-verify="required"  autocomplete="off" class="layui-input" style="width:200px">
			    </div>
	 	  </div>
	 	  <div class="layui-form-item">
	 		 	<label class="layui-form-label"><span style="color:red;">*</span>显示顺序</label>
			    <div class="layui-input-inline">
					<input type="text"  name="sortNo" required  lay-verify="required"  autocomplete="off" class="layui-input" style="width:200px">
			    </div>
	 	  </div>
		  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="financeCostTypeAdd">立即提交</button>
			      <button  class="layui-btn layui-btn-primary"  lay-filter="close">取消</button>
			    </div>
		  </div>
	  </form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/javascript">
var cityList=JSON.parse('${cityListJson}');
var b= new AreaObject(cityList,selectCity,2,$("#cityName"));
//赋值
function selectCity(code,name){
	$("#cityName").val(name);
	$("#cityCode").val(code);
}


 layui.use('form', function(){
	  var $ = layui.$ //重点处
	  var form = layui.form;
		//取消
		 form.on('close(filter)', function(data){
				parent.layer.closeAll();
		  })	
		  //监听提交
		  form.on('submit(financeCostTypeAdd)', function(data){
				 $.ajax({
		    	type:"post",
		    	url:"saveFinanceCostType",
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
		  })
 })
</script>
</body>
</html>