<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>登录日志</title>
</head>
<body>
		<div class="toolBar layui-form">
		<div class="layui-form-item">
			 <div class="layui-inline">
			      <label class="layui-form-label">开始时间</label>
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="beginAddTime" placeholder="选择日期">
			      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">结束时间</label>
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="endAddTime" placeholder="选择日期">
			      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">用户名称</label>
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="name" placeholder="请输入关键字">
			      </div>
		    </div>
			<div class="layui-inline">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
		  	</div>
		  	
			
		</div>
	</div>
	
	

	
<table class="layui-table" id="table_login_record" lay-filter="table_login_record"></table>

<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="error_logEdit">详情</a>
</script>
<script type="text/javascript">
layui.use(['table','laydate'], function(){	
	
	var laydate = layui.laydate;//时间控件
		  
			var endDate= laydate.render({
		        elem: '#endAddTime',//选择器结束时间
		        type: 'datetime',
		        min:"1970-1-1",//设置min默认最小值
		        done: function(value,date){
		        	date.month = date.month-1;
		            startDate.config.max=date;
		        }
		    });
		    //日期范围
		    var startDate=laydate.render({
		        elem: '#beginAddTime',
		        type: 'datetime',
		        max:"2099-12-31",//设置一个默认最大值
		        done: function(value, date){
		        	date.month = date.month-1;
		            endDate.config.min =date;
		        }
		    });
	var table = layui.table;
	  table.render({
		    elem: '#table_login_record'
		    ,method:"post"
		    ,url: 'queryLoginRecordData'
		     ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'phone', title: '电话', sort: true}
		      ,{field: 'ip', title: 'IP地址', sort: true} 
		      ,{field: 'loginTime', title: '登陆时间', sort: true} 
// 		      ,{field: 'stauts', title: '状态', sort: true}
		      ,{field: 'addTime', title:'添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'updateTime', title:'修改时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'operator', title: '操作人', sort: true}
		      ,{field: 'areaCode', title: '区域代码', sort: true}
		      ,{field: 'areaName', title: '区域名称', sort: true}
		      ,{field: 'remark', title: '备注信息', sort: true}
		      ,{field: 'operatorType', title: '操作人类型', sort: true}
		      ,{field: 'source', title: '资源', sort: true}
		      ,{field: 'name', title: '名称', sort: true}
		      ,{field: 'departmentId', title: '部门编号', sort: true}
		      ,{field: 'departmentName', title: '部门名称', sort: true}
		      ,{field: 'companyId', title: '公司编号', sort: true}
		      ,{field: 'companyName', title: '公司名称', sort: true}
	/* 	      ,{field: 'isValid', title: '状态', sort: true} */
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  		
		  	}
		  });
	  var $ = layui.$, active = {
			    reload: function(){
			    
					var beginAddTime = $("#beginAddTime").val();
					var endAddTime=$("#endAddTime").val();
					var name=$("#name").val();
			     //执行重载
			      table.reload('table_login_record', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	beginAddTime:beginAddTime,
			        	endAddTime:endAddTime,
			        	name:name
			        }
			      });
			    }
			  };
	  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	  
})
</script>