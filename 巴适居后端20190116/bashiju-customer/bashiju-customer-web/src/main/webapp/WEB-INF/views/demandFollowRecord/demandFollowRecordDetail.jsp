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
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css" media="all">
<title>房源跟进</title>
</head>
<body>

<table class="layui-table" id="table_DemandFollowRecordDetail" lay-filter="table_DemandFollowRecordDetail"></table>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
var demandId='${demandId}'
layui.use(['table','form'], function(){
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var form = layui.form;
	  table.render({
		    elem: '#table_DemandFollowRecordDetail'
		    ,method:"post"
		    ,height:"full-50"
		    ,url: 'queryDemandFollowRecordByDemandId?demandId='+demandId
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'addTime', title: '跟进时间', width:180,sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'followTypeName',width:100,title: '跟进方式', sort: true}
		      ,{field: 'follower',width:110, title: '小区', sort: true}
		      ,{field: 'deptId',width:100, title: '部门', sort: true}
		      ,{field: 'content', title: '跟进内内容', sort: true}
		    ]]
		  });
});
</script>
</body>
</html>