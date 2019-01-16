<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房源回访周期编辑页面</title>
</head>
<body>
	<form class="layui-form"  action="" id="menuForm" style="margin-top: 20px;">
	
		<table class="layui-table" lay-skin="line" id="resHouseReturnCycleTable" lay-filter="resHouseReturnCycleTable">
		  <colgroup>
	    <col width="150">
	    <col width="200">
	    <col>
	  </colgroup>
	  <thead>
	    <tr>
	      <th>房源等级</th>
	      <th>房源类型</th>
	      <th>私盘回访周期</th>
	      <th>公盘回访周期</th>
	      <th>私盘带看周期</th>
	      <th>公盘带看周期</th>
	    </tr> 
	  </thead>
	  <tbody>
	    <c:forEach var="maps" items="${maps}">
		  	<tr class="id">
		      <td>${levelType[maps.levelType]}</td>
		      <td>${transactionType[maps.transactionType]}</td>
		      <td>
		      	<input type="hidden" value="${maps.id}" name="id"/>
	      		全部员工:<input  type="text" value="${maps.privateAllBack}"  name="privateAllBack" style="width: 20px" lay-verify="check|number"/>天;<br>
		      	维护员工:<input   type="text" value="${maps.privateAdminBack}"  name="privateAdminBack" style="width: 20px" lay-verify="check|number"/>天
		      </td>
		      <td>
		      	全部员工:<input  type="text" value="${maps.pubicAllBack}"  name="pubicAllBack" style="width: 20px" lay-verify="check|number"/>天;<br>
		      	维护员工:<input   type="text" value="${maps.publicAdminBack}" name="publicAdminBack"  style="width: 20px" lay-verify="check|number"/>天</td>
	          <td>
	          	全部员工:<input  type="text" value="${maps.privateAllLook}" name="privateAllLook"  style="width: 20px" lay-verify="check|number"/>天;<br>
	          	维护员工:<input type="text" value="${maps.privateAdminLook}"  name="privateAdminLook" style="width: 20px" lay-verify="check|number"/>天</td>
		      <td>
		      	全部员工:<input  type="text" value="${maps.publicAllLook}"  name="publicAllLook" style="width: 20px" lay-verify="check|number"/>天;<br>
		      	维护员工:<input   type="text" value="${maps.publicAdminLook}" name="publicAdminLook" style="width: 20px" lay-verify="check|number"/>天</td>
		    </tr>
	    </c:forEach>
	  </tbody>
	</table>
		<div class="layui-form-item" style="margin-left: 250px">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="resHouseReturnCycleForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
	  </div>
</form>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
layui.use(['form','table'], function(){
	  var form = layui.form;
	   var $=layui.$;
	   form.verify({
			  check: function(value, item){ //value：表单的值、item：表单的DOM对象
				  if(value=='' || value.trim()==''){
				  		return "必填字段不能为空";
				  	}
				  if(value <=0){
				  		return "输入内容只能不小于或等于0的数字"
			 		 }
			  }
			});   
	   
	  form.on('submit(resHouseReturnCycleForm)',function(data){
		  var tb = $("table").get(0);//table
		  var rows = tb.rows;//rows
		  var arr = [];//创建数组
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input");
			  var obj = {};
			  for(var j=0;j<inputs.length;j++){
				  obj[inputs[j].name]=inputs[j].value;
			  }
			  arr.push(obj);
		  }
	$.ajax({
		    	type:"post",
		    	url:"saveOrUpdateResHouseReturnCycle",
		    	dataType:"json",
		    	data:{jsonData:JSON.stringify(arr)},
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
	  
	  
});
</script>
</body>
</html>