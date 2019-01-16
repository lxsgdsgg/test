<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房源图片审核管理</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
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
				<label class="layui-form-label">资源编号</label>
				<div class="layui-input-block">
					<input type="text" name="houseId" id="houseId" placeholder="资源编号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">驳回原因</label>
				<div class="layui-input-block">
					<input type="text" name="rejectReason" id="rejectReason" placeholder="驳回原因" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"></c:if>
		  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
		  		<button class="layui-btn" name="searchBtn" data-type="reload" id="batchExamine">批量审核</button>
		  	</div>
		</div>
	</div>
	<table class="layui-table" id="table_pictureExamine" lay-filter="table_pictureExamine"></table>
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
  		toStatusStr : function(status){
  			var dd = ${examineStatusJson};
			if('${examineStatusJson}'!='')
				return dd[status];
			else
				return status;
  		}
		,toImage : function(path){
			if(path==undefined || path=="")
				return "";
			return '<img src="'+path+'" width="120px" height="120px;">';
		}
  }
  
  table.render({
    elem: '#table_pictureExamine'
    ,height:'full-100'
    ,url: 'getHousePictureExamineData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
      ,{type: 'checkbox',  sort: true, fixed: 'left'}
      ,{field: 'examineStatus', title: '审核状态', sort: true,templet: '<div>{{ layui.laytpl.toStatusStr(d.examineStatus) }}</div>'}
      ,{field: 'operator', title: '申请人', sort: true}
      ,{field: 'deptName', title: '申请部门', sort: true}
      ,{field: 'addTime', title: '申请时间', sort: true}
      ,{field: 'houseId', title: '资源编号', sort: true}
      ,{field: 'auditorName', title: '审核人', sort: true}
      ,{field: 'auditTime', title: '审核时间', sort: true}
      ,{field: 'reason', title: '驳回原因', sort: true}
      ,{field: 'path', title: '图片', sort: true,templet: '<div>{{ layui.laytpl.toImage(d.path) }}</div>'}
      ,{ title: '操作',width: "14%", align:'center', toolbar: '#operationBar'}
    ]]
  	,done: function(res, curr, count){
  		$("[data-field='id']").css('display','none');
  	}
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var status = $("#status").val();
		var operatorId = $("#operatorId").val();
		var beginAddTime = $("#beginAddTime").val();
		var endAddTime = $("#endAddTime").val();
		var rejectReason = $("#rejectReason").val();
     //执行重载
      table.reload('table_pictureExamine', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	status: status,
        	operatorId: operatorId,
        	beginAddTime: beginAddTime,
        	endAddTime: endAddTime,
        	rejectReason: rejectReason
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_pictureExamine)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var id = data.id;
    if(layEvent === 'sure'){ //
      layer.confirm('确定要确认该申请吗？', function(index){
    	  var arr = [];
			var obj = {
				"id":id
				,"examineStatus":"1"//已审核
			}
			arr.push(obj);
			examine(arr);
      });
    } else if(layEvent === 'reject'){ //驳回
    	var rejectReason = "就是测试一把";//放入公盘
		layer.alert("模拟驳回操作，原因为："+rejectReason,function(){
			var arr = [];
			var obj = {
				"id":id
				,"examineStatus":"2"//已驳回
				,"reason":rejectReason
			}
			arr.push(obj);
			examine(arr);
		});
    }
    
  });
  
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  $("#batchExamine").click(function(){
	  var checkStatus = table.checkStatus('table_pictureExamine');
	  var reason = "老子就不通过，你要咋地！";
	  var arr = [];
	  for(var index in checkStatus.data){
		  if(index < checkStatus.data.length){
			  var obj = checkStatus.data[index];
			  var newObj = {
				"id":obj.id
				,"examineStatus":"2"//已驳回
				,"reason":reason
			  }
			  arr.push(newObj);
		  }
	  }
	  examine(arr);
  });
  
  function examine(arr){
	  $.ajax({
		  type:"post",
		  url:"examineApplication",
		  dataType:"json",
		  data:{dataStr:JSON.stringify(arr)},
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
  }
  
});
</script> 
</body>
</html>