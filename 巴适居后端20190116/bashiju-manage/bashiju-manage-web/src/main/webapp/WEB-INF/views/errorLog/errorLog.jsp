<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
		<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">方法名称</label>
				<div class="layui-input-block">
					<input type="text" name="methodName" id="methodName" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			 <div class="layui-inline">
			       <div class="layui-inline">
			      <label class="layui-form-label">开始时间</label>
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="beginAddTime" placeholder="请选择开始时间">
			      </div>
		    </div>
		    <div class="layui-inline">
			      <label class="layui-form-label">结束时间</label>
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="endAddTime" placeholder="请选择结束时间">
			      </div>
		    </div>
		    </div>
			<div class="layui-inline">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
		  	</div>
			
		</div>
	</div>
	<div id="error_logDetails" class="layui-container layui-form" style="display: none; margin-top: 20px; width: 640px;margin-left: -23px;">
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">类名</label>
			    <div class="layui-input-block">
			      <input name="desc"   id="className_testArea" disabled class="layui-input"/>
			    </div>
		      </div>
			  <div class="layui-form-item">
				    <label class="layui-form-label">方法名</label>
				    <div class="layui-input-block">
				      <input type="text" name="title"autocomplete="off" disabled id="methodName_textArea" class="layui-input">
				    </div>
 				</div>
			
		
		   <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">错误信息</label>
			    <div class="layui-input-block">
			      <textarea name="desc" style="height: 200px;"  class="layui-textarea" disabled id="errorMessage_textArea"></textarea>
			    </div>
		   </div>
	</div>
	

	
<table class="layui-table" id="table_error_log" lay-filter="table_error_log"></table>

<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="error_logEdit">详情</a>
</script>
<script type="text/javascript">
layui.use(['table','laydate'], function(){	
	
	var laydate = layui.laydate;//时间控件
	//日期范围限制
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
		    elem: '#table_error_log'
		    ,method:"post"
		    ,url: 'getErrorLogData'
		     ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'loginUser', title: '登陆人名称', sort: true}
		      ,{field: 'deptId', title: '部门Id', sort: true} 
		      ,{field: 'deptName', title: '部门名称', sort: true} 
		      ,{field: 'loginIp', title: '登陆IP', sort: true}
		      ,{field: 'loginArea', title: '登录区域', sort: true}
// 		      ,{field: 'className', title: '类名', sort: true}
// 		      ,{field: 'methodName', title: '方法名称', sort: true}
// 		      ,{field: 'errorMessage', title: '错误信息', sort: true}
		      ,{field: 'addTime', title:'添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'updateTime', title:'修改时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'operator', title: '操作人', sort: true}
		      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
	  var $ = layui.$, active = {
			    reload: function(){
			    
					var methodName = $("#methodName").val();
					var endAddTime=$("#endAddTime").val()
					var beginAddTime=$("#beginAddTime").val()
			     //执行重载
			      table.reload('table_error_log', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	methodName: methodName,
			        	beginAddTime:beginAddTime,
			        	endAddTime:endAddTime
			        }
			      });
			    }
			  };
		//监听工具条
	  table.on('tool(table_error_log)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	if(layEvent === 'edit'){ //编辑
	   		$("#className_testArea").val(data.className)
			$("#methodName_textArea").val(data.methodName);
			$("#errorMessage_textArea").val(data.errorMessage)
	    	var title = '错误日志详情页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">登录人名称：【'+data.loginUser+'】</span>';
	    	//页面层
	    	window.layer.open({
		        type: 1 
		        ,id:"deptEditFrame"
		        ,title: title
		        ,area: ['640px', '400px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $("#error_logDetails")
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	    }
	  });
	  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
});		
</script>
</body>
</html>