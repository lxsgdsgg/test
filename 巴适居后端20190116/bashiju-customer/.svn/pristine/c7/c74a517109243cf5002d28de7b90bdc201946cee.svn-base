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
<title>二手房源</title>
</head>
<body>
<table class="layui-table" id="table_hsSecondHandHouse" lay-filter="table_hsSecondHandHouse" lay-filter="demoEvent"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
	  <a class="layui-btn layui-btn-xs" lay-event="add" name="hsSecondHandHouseAdd">添加</a>
</script>
<script type="text/javascript">
layui.use(['table','form'], function(){
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var form = layui.form;
	  table.render({
		    elem: '#table_hsSecondHandHouse'
		    ,method:"post"
		    ,height:"full-50"
		    ,url: 'queryHsSecondHandHouseData'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		        {field: 'id',width:100, title: '房源编号', sort: true}
			      ,{field: 'communityName',width:100	, title: '小区', sort: true}
			      ,{field: 'buildingsName', title: '座栋', width:100,sort: true} 
			      ,{field: 'buildingHouseName',width:100,title: '门牌号', sort: true}
			      ,{field: 'owner',width:100, title: '业主姓名', sort: true}
			      ,{field: 'houseType',width:100, title: '房型', sort: true}
			      ,{field: 'buildSpace', title: '面积', width:100,sort: true} 
			      ,{field: 'sellingPrice',width:100,title: '售价', sort: true}
			      ,{field: 'rentPrice',width:100, title: '租价', sort: true}
			      ,{field: 'maintainer',width:100, title: '维护人', sort: true}
			      ,{field: 'entrustCode', title: '委托人', width:100,sort: true} 
			      ,{field: 'mainterDept',width:100,title: '部门', sort: true}
		    ]]
		  });
	  
	  var $ = layui.$, active = {
			    getCheckData: function(){ //获取选中数据
			      var checkStatus = table.checkStatus('test')
			      ,data = checkStatus.data;
			      layer.alert(JSON.stringify(data));
			    }
	  }
	   	var arr="";
 	 table.on('tool(table_hsSecondHandHouse)', function(obj){
 	    var data = obj.data;
	 	var secondHouseId=data.id;
 	    if(obj.event === 'add'){
    	  $.ajax({
    		  type:"post",
    		  url:"delResCustLevelCondition",
    		  dataType:"json",
			 data:{secondHouseId:secondHouseId},
    		  success:function(data){
    			  if(data.success){
    				  layer.msg('删除成功', {icon: 1,time:500});
    				  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
    			  }else{
    				  layer.alert(data.msg);
    			  }
    		  },
    		  error:function(a,b,c){
    			  layer.alert("操作异常，请稍后重试");
    		  }
    	  });
//  	    	关闭当前的iframe
//  	    	index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//  	    	alert(index);
 	    }
 	  });
 	 
 	var $table_hsSecondHandHouse = $("#table_hsSecondHandHouse").next('.layui-table-view').find('table.layui-table');
 	$table_hsSecondHandHouse.dblclick(function(event){
 		var tdArr = $($(event.target).closest("tr").get(0)).find('td');
		var obj = {};
 		for(var i=0;i<tdArr.length;i++){
 			obj[$(tdArr[i]).attr('data-field')]= $(tdArr[i]).find('div').text();
 		}
 		top.document.back(obj);
 	 	index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
 		parent.layer.closeAll()
 	});
  		
});
</script>
</body>
</html>