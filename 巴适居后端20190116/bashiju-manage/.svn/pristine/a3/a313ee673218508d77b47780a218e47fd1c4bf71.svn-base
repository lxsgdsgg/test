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
<title>简历详情页面</title>
</head>
<body>
<div>
	
<table class="layui-table" id="table_curriculumVitae">
		  <thead>
		    <tr>
		      <th>名称</th>
		      <th>投机岗位</th>
		      <th>性别</th>
		      <th>出生日期</th>
		      <th>电子邮件</th>
		      <th>电话</th>
		      <th>证件类型</th>
		      <th>证件号码</th>
		      <th>民族</th>
		      <th>政治面貌</th>
		      <th>户籍所在地</th>
		      <th>毕业日期</th>
		      <th>参加工作时间</th>
		      <th>家庭地址</th>
		      <th>婚否</th>
		    </tr> 
		  </thead>
		  <tbody>
		    <c:forEach var="map" items="${map}">
		    	<tr>
			      <td>${map.name}</td>
			      <td>${map.recruitName}</td>
			      <td>${map.sex}</td>
			      <td>${map.birthdate}</td>
			      <td>${map.email}</td>
			      <td>${map.mobile}</td>
			      <td>${map.docType}</td>
			      <td>${map.docCode}</td>
			      <td>${map.nationalities}</td>
			      <td>${map.politicalOrientation}</td>
			      <td>${map.householdRegister}</td>
			      <td>${map.graduatDate}</td>
			      <td>${map.workDate}</td>
			      <td>${map.address}</td>
			      <td>${map.isMarry}</td>
			    </tr>
		    </c:forEach>
		  </tbody>
</table>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
// var id= ${id};
// layui.use(['form','table'],function(){
// 	  var form = layui.form,
// 	  table = layui.table;
// 	  table.render({
// 		    elem: '#table_curriculumVitae'
// 		    ,height:'full-100'
// 		    ,url: 'queryCurriculumVitaeDetailById?id='+id
// 		   	,method:'post'
// 		    ,cols: [[ //表头
// 		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
// 		      ,{field: 'name', title: '名称',width: "10%", sort: true}
// 		      ,{field: 'sex', title: '性别',width: "10%"}
// 		      ,{field: 'birthdate', title: '出生日期',width: "10%"}
// 		      ,{field: 'workDate', title: 'workDate',width: "10%"}
// 		      ,{field: 'addTime', title: '投递时间',width: "10%"}
// 		      ,{field: 'mobile', title: '手机号码',width: "10%"}
// 		      ,{ title: '操作',width: "10%", align:'center', toolbar: '#operationBar'}
// 		    ]]
// 		  });
// });
</script>
</body>
</html>