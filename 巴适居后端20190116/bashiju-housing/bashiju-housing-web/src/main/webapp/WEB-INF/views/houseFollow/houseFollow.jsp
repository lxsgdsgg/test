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
<style type="text/css">
.layui-form-select .layui-input {
    padding-right: 30px;
    cursor: pointer;
    width: 120px;
}

layui-input, .layui-textarea {
    display: block;
    width: 50%;
    padding-left: 10px;
}
</style>
<title>房源跟进</title>
</head>
<body>

	<div class="toolBar layui-form">
	
	  	<div class="layui-inline">
				<select name="transactionType" id="transactionType">
					<option value="">房源类型</option>
					<c:forEach var="houseTransactionType" items="${houseTransactionType}">
						<option value="${houseTransactionType.value}">${houseTransactionType.name}</option>
					</c:forEach>
				</select>
		</div>
	  	<div class="layui-inline">
					<select name="houseStatus" id="houseStatus">
						<option value="">房源状态</option>
						<c:forEach var="houseStatus" items="${houseStatus}">
								<option value="${houseStatus.name}">${houseStatus.name}</option>
						</c:forEach>
					</select>
		</div>
			<div class="layui-inline">
					<select name="isValid" id="isValid">
						<option value="">跟进类型</option>
						<option value="1">正常</option>
						<option value="0">异常</option>
					
					</select>
		</div>
			<div class="layui-inline">
					<select name="followTypeName" id="followTypeName">
						<option value="">跟进方式</option>
						<option value="1">实勘</option>
						<option value="2">谈委托</option>	
						<option value="3">店面面谈</option>
						<option value="4">派单</option>	
					</select>
		</div>
			<div class="layui-inline">
					<select name="followerId" id="followerId">
						<option value="">跟进人</option>
						<option value="13220">超级管理员</option>
						<option value="测试人员">测试人员</option>	
					</select>
		</div>
			<div class="layui-inline">
					<select name="operatorId" id="operatorId">
						<option value="">维护人</option>
						<option value="13320">测试人员</option>
						<option value="11">普通角色代表</option>
						<option value="13320">超级管理员</option>	
					</select>
		</div>
		  <div class="layui-inline">
			        <input type="text" class="layui-input" id="beginTime" placeholder="开始日期">
		    </div>
		    <div class="layui-inline">
			        <input type="text" class="layui-input" id="endTime" placeholder="结束日期">
		    </div>
			<div class="layui-inline">
					<select name="communityId" id="communityId">
						<option value="">小区</option>
						<option value="12">玛卡切片<option>
					</select>
		</div>
			<div class="layui-inline">
				<input class="layui-input" name="houseIdOrFollowerId" placeholder="房源编号,跟进人" id="houseIdOrFollowerId" autocomplete="off">
			</div>
		 <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}">
			<button class="layui-btn"  type="button"  name="clear">清空</button>
	  	</c:if>
	</div>

<table class="layui-table" id="table_HsFollowRecord" lay-filter="table_HsFollowRecord"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.hsFollowRecordDetail!='' && d.hsFollowRecordDetail!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="Detail" name="hsFollowRecordDetail">查看所有跟进</a>
  {{# } }}
</script>
<script type="text/javascript">
layui.use(['table','form','laydate'], function(){
	var laydate = layui.laydate;//时间控件
	var form = layui.form;
	var endDate= laydate.render({
        elem: '#endTime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        	date.month = date.month-1;
            startDate.config.max=date;
        }
    });
    //日期范围
    var startDate=laydate.render({
        elem: '#beginTime',
        type: 'datetime',
        max:"2099-12-31",//设置一个默认最大值
        done: function(value, date){
        	date.month = date.month-1;
            endDate.config.min =date;
        }
    });
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var form = layui.form;
	  table.render({
		    elem: '#table_HsFollowRecord'
		    ,method:"post"
		    ,height:"full-68"
		    ,url: 'queryHsFollowRecordData'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'followerTime',width: 180, title: '跟进时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{field: 'followTypeName',width: 100,title: '跟进方式', sort: true}
		      ,{field: 'transactionType', width: 100,title: '类型', sort: true}
		      ,{field: 'houseId', width: 130,title: '房源编号', sort: true} 
		      ,{field: 'communityName',width: 130, title: '小区', sort: true}
		      ,{field: 'status', width: 100,title: '状态', sort: true}
		      ,{field: 'follower',width: 130, title: '跟进人', sort: true}
		      ,{field: 'deptId', width: 130,title: '部门', sort: true}
		      ,{field: 'content',title: '跟进内内容', sort: true}
		      ,{fixed: 'right', title: '操作', width: 140, align:'center', toolbar: '#operationBar'}
		    ]]
		  });
	   var $ = layui.$, active = {
				 
			    reload: function(){
			    //执行重载
			      table.reload('table_HsFollowRecord', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	transactionType: $("#transactionType").val(),
			        	followerId: $("#followerId").val(),
			        	status:$("#houseStatus").val(),
			        	followType:$("#followTypeName").val(),
			        	houseIdOrFollowerId:$("#houseIdOrFollowerId").val(),
			        	communityId:$("#communityId").val(),
			        	beginTime:$("#beginTime").val(),
			        	isValid:$("#isValid").val(),
			        	operatorId:$("#operatorId").val(),
			        	endTime:$("#endTime").val()
			        }
			      });
			    }
			  };
	  
	  
	  
	  
	 	//监听工具条
	  table.on('tool(table_HsFollowRecord)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var houseId = data.houseId;
	    if(layEvent === 'Detail'){ //编辑
	    	var title = '查看房前房源跟进详细页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房源跟进编号：【'+data.houseId+'】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"HsFollowRecordDetailFrame"
		        ,title: title
		        ,area: ['1200px', '600px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterHsFollowRecordDetail?houseId='+houseId
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