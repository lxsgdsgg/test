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

<table class="layui-table" id="table_HsFollowRecordDetaiil" lay-filter="table_HsFollowRecordDetaiil"></table>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
var houseId='${houseId}'
layui.use(['table','form'], function(){
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var form = layui.form;
	  table.render({
		    elem: '#table_HsFollowRecordDetaiil'
		    ,method:"post"
		    ,height:"full-50"
		    ,url: 'queryAllHsFollowRecordByHouseId?houseId='+houseId
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'addTime', title: '跟进时间', width:180,sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'followTypeName',width:100,title: '跟进方式', sort: true}
		      ,{field: 'transactionType',width:100,title: '类型', sort: true}
		      ,{field: 'houseId',title: '房源编号', width:150,sort: true} 
		      ,{field: 'communityName',width:110, title: '小区', sort: true}
		      ,{field: 'status', width:80,title: '状态', sort: true}
		      ,{field: 'operator',width:100, title: '跟进人', sort: true}
		      ,{field: 'deptId',width:100, title: '部门', sort: true}
		      ,{field: 'content', title: '跟进内内容', sort: true}
		    ]]
		  });
});
</script>
</body>
</html>