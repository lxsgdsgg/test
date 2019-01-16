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
<title>Insert title here</title>
</head>
<body>
<table class="layui-table" id="table_HsKeyLog" lay-filter="table_HsKeyLog"></table>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
var hsKeyId='${hsKeyId}'
layui.use('table', function(){
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  
	  table.render({
		    elem: '#table_HsKeyLog'
		    ,method:"post"
		    ,height:"full-68"
		    ,url: 'queryHsKeyLog?hsKeyId='+hsKeyId
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'deptId',title: '部门', sort: true} 
		      ,{field: 'operator',title: '员工', sort: true}
		      ,{field: 'addTime', title: '操作时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'operatType',title: '操作类型', sort: true}
		      ,{field: 'content', title:'日志内容',sort: true}
		    ]]
		  });
})

</script>
</body>
</html>