<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二手房源买卖成交管理</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">房源用途</label>
				<div class="layui-input-block">
					<select name="houseUses" id="houseUses">
						<option value="">-请选择-</option>
						<c:forEach var="houseUses" items="${houseUsesEnum}">
							<option value="${houseUses.key}">${houseUses.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">录入人</label>
				<div class="layui-input-block">
					<input type="text" name="operatorId" id="operatorId" placeholder="录入人" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">录入人部门</label>
				<div class="layui-input-block">
					<input type="text" name="operatorDeptId" id="operatorDeptId" placeholder="录入人" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-block">
					<input type="text" name="beginDealTime" id="beginDealTime" placeholder="成交日期" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">至</label>
				<div class="layui-input-block">
					<input type="text" name="endDealTime" id="endDealTime" placeholder="成交日期" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"></c:if>
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
		  	</div>
		</div>
	</div>
	<table class="layui-table" id="table_businessDeal" lay-filter="table_businessDeal"></table>
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
	toBusinessDealDetail : function(id,dealType){
		var url = "dealDetailPage?id="+id+"&dealType="+dealType;
		return "<a href='"+url+"'>"+id+"</a>";
	}
  }
  
  table.render({
    elem: '#table_businessDeal'
    ,height:'full-100'
    ,url: 'getData?dealType=00'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '系统编号',  sort: true, fixed: 'left',templet: '<div>{{ layui.laytpl.toBusinessDealDetail(d.id,d.dealType) }}</div>'}
      ,{field: 'agreementId', title: '合同编号', sort: true}
      ,{field: 'houseId', title: '房源编号', sort: true}
      ,{field: 'demandId', title: '客源编号', sort: true}
      ,{field: 'areaName', title: '区', sort: true}
      ,{field: 'regionName', title: '片区', sort: true}
      ,{field: 'communityName', title: '小区', sort: true}
      ,{field: 'buildingHouseName', title: '房号', sort: true}
      ,{field: 'owner', title: '业主', sort: true}
      ,{field: 'custName', title: '客户', sort: true}
      ,{field: 'traderName', title: '成交人', sort: true}
      ,{field: 'traderDept', title: '成交部门', sort: true}
      ,{ title: '操作',width: "14%", align:'center', toolbar: '#operationBar'}
    ]]
  	,done: function(res, curr, count){
//   		$("[data-field='id']").css('display','none');
  	}
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var houseUses = $("#houseUses").val();
		var operatorId = $("#operatorId").val();
		var beginDealTime = $("#beginDealTime").val();
		var endDealTime = $("#endDealTime").val();
		var operatorDeptId = $("#operatorDeptId").val();
     //执行重载
      table.reload('table_businessDeal', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	houseUses: houseUses,
        	operatorId: operatorId,
        	beginDealTime: beginDealTime,
        	endDealTime: endDealTime,
        	operatorDeptId : operatorDeptId
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_businessDeal)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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