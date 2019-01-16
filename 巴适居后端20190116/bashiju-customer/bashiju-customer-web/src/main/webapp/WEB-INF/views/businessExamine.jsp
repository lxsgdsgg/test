<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客源业务审核管理</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">业务类型</label>
				<div class="layui-input-block">
					<select name="transactionTypeId" id="transactionTypeId">
						<option value="">-请选择-</option>
						<c:forEach var="transactionType" items="${transactionType}">
							<option value="${transactionType.key}">${transactionType.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">审核类型</label>
				<div class="layui-input-block">
					<select name="examineTypeId" id="examineTypeId">
						<option value="">-请选择-</option>
						<c:forEach var="examineType" items="${examineType}">
							<option value="${examineType.key}">${examineType.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">审核状态</label>
				<div class="layui-input-block">
					<select name="status" id="status">
						<option value="">-请选择-</option>
						<c:forEach var="examineStatus" items="${examineStatus}">
							<option value="${examineStatus.key}">${examineStatus.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">申请人</label>
				<div class="layui-input-block">
					<input type="hidden" id="applyerId">
					<input type="text" name="applyer" id="applyer" placeholder="申请人" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-block">
					<input type="text" name="beginApplyTime" id="beginApplyTime" placeholder="申请日期" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">至</label>
				<div class="layui-input-block">
					<input type="text" name="endApplyTime" id="endApplyTime" placeholder="申请日期" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"></c:if>
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
			  	
		  	</div>
		</div>
	</div>
	<table class="layui-table" id="table_examine" lay-filter="table_examine"></table>
<%@include file="/common/common.jsp" %>

<script>

layui.use(['table','laydate'], function(){
  var table = layui.table;
  var laydate = layui.laydate; 
  layui.laytpl={
		 //
		toTransactionTypeStr : function(transactionTypeId){
		  var dd = ${transactionTypeJson};
			if('${transactionTypeJson}'!='')
				return dd[transactionTypeId];
			else
				return transactionTypeId;
		}
  		//
  		,toExamineTypeStr : function(examineTypeId){
  			var dd = ${examineTypeJson};
			if('${examineTypeJson}'!='')
				return dd[examineTypeId];
			else
				return examineTypeId;
  		}
  		//
  		,toStatusStr : function(status){
  			var dd = ${examineStatusJson};
			if('${examineStatusJson}'!='')
				return dd[status];
			else
				return status;
  		}
		
  }
  
  laydate.render({
	    elem: '#beginApplyTime' //指定元素
	  });
  laydate.render({
	    elem: '#endApplyTime' //指定元素
	  });
  
  var conditions = {};
  var cols = [ //表头
      {field: 'transactionTypeId', title: '业务类型', sort: true,templet: '<div>{{ layui.laytpl.toTransactionTypeStr(d.transactionTypeId) }}</div>'}
      ,{field: 'examineTypeId', title: '审核类型', sort: true,templet: '<div>{{ layui.laytpl.toExamineTypeStr(d.examineTypeId) }}</div>'}
      ,{field: 'status', title: '审核状态', sort: true,templet: '<div>{{ layui.laytpl.toStatusStr(d.status) }}</div>'}
      ,{field: 'rejectReason', title: '驳回原因', sort: true}
      ,{field: 'applyer', title: '申请人', sort: true}
      ,{field: 'deptName', title: '申请部门', sort: true}
      ,{field: 'applyTime', title: '申请时间', sort: true}
      ,{field: 'content', title: '申请内容', sort: true}
      ,{field: 'demandId', title: '需求编号', sort: true}
      ,{field: 'auditorName', title: '审核人', sort: true}
      ,{field: 'auditTime', title: '审核时间', sort: true}
      ,{ title: '操作',width: "14%", align:'center', toolbar: '#operationBar'}
    ]
  table.render({
    elem: '#table_examine'
    ,height:'full-100'
    ,url: 'getBusinessExamineData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [cols]
    ,where:{conditions:JSON.stringify(conditions)}
  	,done: function(res, curr, count){
  		
  	}
  });
  
    
  
//监听工具条
  table.on('tool(table_examine)', function(obj){ 
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var id = data.id;
    if(layEvent === 'sure'){ //
    	layer.confirm('确定要确认该申请吗？', function(index){
	    	  $.ajax({
	    		  type:"post",
	    		  url:"examineApplication",
	    		  dataType:"json",
	    		  data:{id:id,status:"1"},
	    		  success:function(data){
	    			  if(data.success){
	    				  layer.alert("操作成功",function(){
		    				  location.reload();
	    				  });
	    			  }else{
	    				  layer.alert(data.msg);
	    			  }
	    		  },
	    		  error:function(a,b,c){
	    			  layer.alert("操作异常，请稍后重试");
	    		  }
	    	  });
	        layer.close(index);
	        
	      });
    } else if(layEvent === 'reject'){ //驳回
    	var rejectReason = "就是测试一把";//放入公盘
		layer.alert("模拟驳回操作，原因为："+rejectReason,function(){
			$.ajax({
	    		  type:"post",
	    		  url:"examineApplication",
	    		  dataType:"json",
	    		  data:{id:id,status:"2",rejectReason:rejectReason},
	    		  success:function(data){
	    			  if(data.success){
	    				  layer.alert("操作成功",function(){
		    				  location.reload();
	    				  });
	    			  }else{
	    				  layer.alert(data.msg);
	    			  }
	    		  },
	    		  error:function(a,b,c){
	    			  layer.alert("操作异常，请稍后重试");
	    		  }
	    	  });
	        layer.close(index);
		});
    }
    
  });
  
  $('#searchBtn').on('click', function(){
	  var conditions = {};
	  var transactionTypeId = $("#transactionTypeId").val();
	  var examineType = $("#examineType").val();
	  var examineStatus = $("#examineStatus").val();
	  var applyerId = $("#applyerId").val();
	  var beginApplyTime = $("#beginApplyTime").val();
	  var endApplyTime = $("#endApplyTime").val();
	  conditions.transactionTypeId = transactionTypeId;
	  conditions.examineType = examineType;
	  conditions.examineStatus = examineStatus;
	  conditions.applyerId = applyerId;
	  conditions.beginApplyTime = beginApplyTime;
	  conditions.endApplyTime = endApplyTime;
	  table.render({
		    elem: '#table_examine'
		    ,height:'full-100'
		    ,url: 'getBusinessExamineData'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [cols]
		    ,where:{conditions:JSON.stringify(conditions)}
		  	,done: function(res, curr, count){
		  		
		  	}
		  });
  });
  
});
</script> 
<script type="text/html" id="operationBar">

  {{#  if(d.status == '0'){ }}
     <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="sure">确认</a>
    <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="reject">驳回</a>
  {{#  } }}
</script>
</body>
</html>