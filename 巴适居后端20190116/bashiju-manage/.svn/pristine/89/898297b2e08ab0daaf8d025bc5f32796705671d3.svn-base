<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户管理</title>

	<link rel="Shortcut Icon" href="/favicon.ico" />
	<!-- load css -->
    <link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all">
    <style type="text/css">
    	button.layui-btn {
/* 		    display: none; */
		}
    </style>
</head>
<body>
<div class="layui-btn-group">
  <button class="layui-btn" name="dataAdd" layer-filter='dataAdd' onclick="user.add()">增加</button>
</div>

<table class="layui-table" id='user-table'></table>

 <%@include file="/common/common.jsp" %> 
<script type="text/javascript">
layui.use(['jquery','table','layer'],function(){
	  var table = layui.table;
	  var $=layui.jquery;
	  var layer=layui.layer;
	table.render({
		 elem: '#user-table'
			    ,height:'full-100'
			    ,url: 'getListShowBody'
			   	,method:'post'
			    ,page: true //开启分页
			    ,cols:[${formField}]			
	});
	user={
		add:function(){
			layer.open({
				type: 2
				,content:'formShow?pageName=userAdd'
				,title: '添加用户'
				,area: ['600px', '500px']
	        	,maxmin: true});
		}	
	}
	
	
})
</script>
</body>
</html>