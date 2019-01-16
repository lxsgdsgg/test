<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户日志</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">操作类型</label>
				<div class="layui-input-block">
					<input type="text" name="operationType" id="operationType" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">资源类型</label>
				<div class="layui-input-block">
					<input type="text" name="sourceType" id="sourceType" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
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
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
			  	</c:if>
			  	<c:if test="${onlyBtnMenus.addUser_log!='' && onlyBtnMenus.addUser_log!=null}">
			  		<button class="layui-btn" name="addUser_log" onclick="company.add()">新增</button>
			  	</c:if>
		  	</div>
			
		</div>
	</div>


	
	<table class="layui-table" id="table_user_log" lay-filter="table_user_log"></table>
<%@include file="/common/common.jsp" %>

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
	var table=layui.table;
	//加载数据
	 table.render({
		    elem: '#table_user_log'
		    ,height:'full-100'
		    ,url: 'queryUserLogData'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
		      ,{field: 'loginUser', title: '登陆人', sort: true}
		      ,{field: 'deptId', title: '部门编号', sort: true}
		      ,{field: 'deptName', title: '部门名称'}
		      ,{field: 'companyId', title: '公司编号'}
		      ,{field: 'companyName', title: '公司名称'}
		      ,{field: 'loginIp', title: '登陆ip'}
		      ,{field: 'sourceType', title: '资源类型'}
		      ,{field: 'operationType', title: '操作类型'}
		      ,{field: 'actionTime', title: '执行时间'}
		      ,{field: 'excuteTime', title: '执行日期',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'sourceId', title: '资源id'}
		      ,{field: 'excuteContext', title: '执行内容'}
		      ,{field: 'addTime', title: '添加时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'updateTime', title: '更新时间',templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'operator', title: '操作人'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
	
	  var $ = layui.$, active = {
			    reload: function(){
					var operationType = $("#operationType").val();
					var sourceType=$("#sourceType").val()
					var beginAddTime=$("#beginAddTime").val();
					var endAddTime=$("#endAddTime").val();
			     //执行重载
			      table.reload('table_user_log', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	operationType: operationType,
			        	sourceType:sourceType,
			        	endAddTime:endAddTime,
			        	beginAddTime:beginAddTime
			        }
			      });
			    }
			  };
	  
	  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	
});
</script>
</body>
</html>