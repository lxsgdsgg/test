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
<title>房源回访周期设置</title>
</head>
<body>
<button class="layui-btn"  type="button" id="resHouseReturnUpdate"  style="margin-left:93%;margin-top: 10px">设置</button>
	<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>房源等级</th>
      <th>类型</th>
      <th>私盘回访周期</th>
      <th>公盘回访周期</th>
      <th>私盘带看周期</th>
      <th>公盘带看周期</th>
    </tr> 
  </thead>
  <tbody>
    <c:forEach var="map" items="${map}">
	  	<tr>
	      <td>${levelTypes[map.levelType]}</td>
	      <td>${transactionTypes[map.transactionType]}</td>
	      <td>全员：${map.privateAllBack} 天;维护人：${map.privateAdminBack}天 </td>
	      <td>全员：${map.pubicAllBack} 天;维护人：${map.publicAdminBack}天</td>
	      <td>全员：${map.privateAllLook} 天;维护人：${map.privateAdminLook}天</td>
	      <td>全员：${map.publicAllLook} 天;维护人：${map.publicAdminLook}天</td>
	    </tr>
  </c:forEach>
  
   
  </tbody>
</table>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
layui.use(['table','form'], function(){
	 var $ = layui.$ //重点处
	  var table = layui.table;
	  var form = layui.form;
	  
	  
$(function(){
	$("#resHouseReturnUpdate").click(function(){
			var title = '房源回访周期设置编辑页面';
			var option = {
			        type: 2 //此处以iframe举例
			        ,id:"ResHouseReturnCycleFrame"
			        ,title: title
			        ,area: ['900px', '600px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterResHouseReturnCycleEditorPage'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			          parent.layer.closeAll();
			        }
			      };
			common.windowLayerOpen(option);
		})
	})
});
</script>
</body>
</html>