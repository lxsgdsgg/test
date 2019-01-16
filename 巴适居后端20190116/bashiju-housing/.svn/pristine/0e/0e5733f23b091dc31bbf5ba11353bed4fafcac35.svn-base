<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房源业务审核管理</title>

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
					<input type="text" name="operatorId" id="operatorId" placeholder="申请人" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-block">
					<input type="text" name="beginAddTime" id="beginAddTime" placeholder="申请日期" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">至</label>
				<div class="layui-input-block">
					<input type="text" name="endAddTime" id="endAddTime" placeholder="申请日期" autocomplete="off" class="layui-input">
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
<script type="text/html" id="operationBar">
 {{# if(d.menuEdit!='' && d.menuEdit!=null){ }}
  <a class="layui-btn layui-btn-xs" name="menuEdit" lay-event="sure">确认</a>
 {{# } }}
 {{# if(d.combinationEdit!='' && d.combinationEdit!=null){ }}
  <a class="layui-btn layui-btn-xs" name="combinationEdit" lay-event="reject">驳回</a>
 {{# } }}
</script>
<script>

layui.use('table', function(){
  var table = layui.table;
  
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
		,toHandleTypeStr : function(handleType){
			if(handleType==undefined || handleType=="")
				return "";
			var dd = ${examineHandleTypeJson};
			if('${examineHandleTypeJson}'!='')
				return dd[handleType];
			else
				return handleType;
		}
  }
  
  table.render({
    elem: '#table_examine'
    ,height:'full-100'
    ,url: 'getBusinessExamineData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
      ,{field: 'transactionTypeId', title: '业务类型', sort: true,templet: '<div>{{ layui.laytpl.toTransactionTypeStr(d.transactionTypeId) }}</div>'}
      ,{field: 'examineTypeId', title: '审核类型', sort: true,templet: '<div>{{ layui.laytpl.toExamineTypeStr(d.examineTypeId) }}</div>'}
      ,{field: 'status', title: '审核状态', sort: true,templet: '<div>{{ layui.laytpl.toStatusStr(d.status) }}</div>'}
      ,{field: 'rejectReason', title: '驳回原因', sort: true}
      ,{field: 'handleType', title: '处理方式', sort: true,templet: '<div>{{ layui.laytpl.toHandleTypeStr(d.handleType) }}</div>'}
      ,{field: 'operator', title: '申请人', sort: true}
      ,{field: 'deptName', title: '申请部门', sort: true}
      ,{field: 'addTime', title: '申请时间', sort: true}
      ,{field: 'content', title: '申请内容', sort: true}
      ,{field: 'houseId', title: '资源编号', sort: true}
      ,{field: 'auditorName', title: '审核人', sort: true}
      ,{field: 'auditTime', title: '审核时间', sort: true}
      ,{ title: '操作',width: "14%", align:'center', toolbar: '#operationBar'}
    ]]
  	,done: function(res, curr, count){
  		$("[data-field='id']").css('display','none');
  	}
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var transactionTypeId = $("#transactionTypeId").val();
		var examineTypeId = $("#examineTypeId").val();
		var status = $("#status").val();
		var operatorId = $("#operatorId").val();
		var beginAddTime = $("#beginAddTime").val();
		var endAddTime = $("#endAddTime").val();
     //执行重载
      table.reload('table_examine', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	transactionTypeId: transactionTypeId,
        	examineTypeId: examineTypeId,
        	status: status,
        	operatorId: operatorId,
        	beginAddTime: beginAddTime,
        	endAddTime: endAddTime
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_examine)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var id = data.id;
    if(layEvent === 'sure'){ //删除
    	if(data.examineTypeId=='03'){//房源举报
    		var handleType = "02";//放入公盘
    		layer.alert("模拟处理方式为放入公盘",function(){
    			$.ajax({
  	    		  type:"post",
  	    		  url:"examineApplication",
  	    		  dataType:"json",
  	    		  data:{id:id,status:"1",handleType:handleType},
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
    	}else{
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
	        //向服务端发送删除指令
	      });
    	}
    } else if(layEvent === 'reject'){ //驳回
    	var rejectReason = "就是测试一把";//放入公盘
		layer.alert("模拟驳回操作，原因为："+rejectReason,function(){
			$.ajax({
	    		  type:"post",
	    		  url:"examineApplication",
	    		  dataType:"json",
	    		  data:{id:id,status:"2",handleType:handleType,rejectReason:rejectReason},
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
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
});
</script> 
</body>
</html>