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
  <button class="layui-btn" name="dataAdd" layer-filter='dataAdd'>增加</button>
</div>

<table class="layui-table" id="housing_log"> </table>
   
<!-- 加载js文件 -->
 
 
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;	  
	  table.render({
	    elem: '#housing_log'
	    ,url:'/demo/table/user/'
	    ,cols: [${tableTitle}]
	    ,page: true
	  });
	});
</script>
</body>
</html>