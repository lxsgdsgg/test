<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司管理</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<style type="text/css">
.areaDiv {
    position: fixed;
    top: 200px;
    left: 0;
    z-index: 99891010;
    background-color: rgb(238, 238, 238);
    box-shadow: rgba(0, 0, 0, 0.3) 1px 1px 50px;
    margin: auto;
    border-radius: 2px;
}
</style>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="companyForm" style="margin-top: 20px;">
	  <div class="layui-form-item">
	  	   <label class="layui-form-label"><span style="color:red;">*</span>中介名称</label>
		    <div class="layui-input-inline">
		      <select name="companyId" id="levelType" lay-verify="required" >
		      
		      	   	 <option value="">请选择</option>
				        <c:forEach var="companys" items="${companys}">
				        	 <option value="${companys.id}">${companys.name}</option>	
				        </c:forEach> 
		    	</select>
		    </div>
	  </div>
	  
	    <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>中介电话:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="legalPhone" required lay-verify="required|number|phone" placeholder="操作人"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  
	    <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>违规次数:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="foulsNum" required lay-verify="required|number" placeholder="违规次数"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  
	  <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>备注信息:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="note" required lay-verify="required" placeholder="备注信息"  autocomplete="off" class="layui-input">
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
<script>
layui.use('form', function(){
  var form = layui.form;
  var $ = layui.$ //重点处
  ,layer = layui.layer;
  //监听提交
  form.on('submit(blackListForm)', function(data){
    $.ajax({
    	type:"post",
    	url:"saveOrUpdateBlackList",
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
  
  form.verify({
	  //我们既支持上述函数式的方式，也支持下述数组的形式
	  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
	  //联系人电话
	 legdocmobile: [
		 /^1(3|4|5|7|8)\d{9}$/
	    ,'法人必须为11位，且不能出现空格'
	  ],
	  cusemail: function(value, item){ //value：表单的值、item：表单的DOM对象
		if(value!=""){
			var regx = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
		    if(!regx.test(value)){
		      return '你输入的邮箱格式有误，请重新输入';
		    }
		}
	  }
	}); 
  
});
</script> 
</body>
</html>