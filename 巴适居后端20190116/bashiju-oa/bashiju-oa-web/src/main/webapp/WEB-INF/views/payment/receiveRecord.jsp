<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代收款管理</title>
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
</head>
<body>
<form>
	<div class="toolBar layui-form payQuery">
	  	<div class="layui-inline">
			<select name="status" id="status">
			<option value="">状态</option>
			<option value="0">未审核</option>
			<option value="1">已审核</option>
			<option value="2">已驳回</option>
			</select>
		</div>
		<div class="layui-inline">
					<select name="timeType" id="timeType">
						<option value="">时间</option>
						<option value="1">录入时间</option>
						<option value="2">审核时间</option>
						<option value="3">付款时间</option>
					</select>
		</div>
		<div class="layui-inline">
			        <input type="text" class="layui-input" id="beginTime" placeholder="起始日期">
		    </div>
		    <div class="layui-inline">
			        <input type="text" class="layui-input" id="endTime" placeholder="结束日期">
		    </div>
	  	<div class="layui-inline">
			<select name="moneyTypeId" id="moneyTypeId">
				<option value="">费用类型</option>
				<c:forEach var="item" items="${moneyType}">
					<option value="${item.id}">${item.fieldValue}</option>
				</c:forEach>
			</select>
		</div>
		<div class="layui-inline">
			<select name="settlementTypeId" id="settlementTypeId">
				<option value="">结算方式</option>
				<c:forEach var="item" items="${settlementType}">
					<option value="${item.id}">${item.fieldValue}</option>
				</c:forEach>
			</select>
		</div>	
		  	
			<div class="layui-inline">
				<select name="payerType" id="payerType">
					<option value="">支付方</option>
					<option value="0">业主<option>
					<option value="1">客户<option>
				</select>
			</div>

			<div class="layui-inline">
				<div class="layui-unselect layui-form-select">
					<div class="layui-select-title">
					<input type="hidden" id="keyword">
					<input type="hidden" id="keywordType">
					<input type="text" placeholder="关键字" id="keywordQuery"  value=""  class="layui-input">
					</div>
					<dl id="keyDl" class="layui-anim layui-anim-upbit">
						<dd lay-value="billNo" class="">票据编号:<span class="data"></span> </dd>
						<dd lay-value="dealId" class="">成交编号 :<span class="data"></span></dd>
						<dd lay-value="remark" class="">备注:<span class="data"></span> </dd>
					</dl>
				</div>
			</div>
		 <%-- <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}"> --%>
	  		<button class="layui-btn" type="button" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	<%-- </c:if> --%>
	  	<%-- <c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}"> --%>
			<button class="layui-btn"  type="reset"  name="clear">清空</button>
	  	<%-- </c:if> --%>
	</div>
</form>
	<table class="layui-table" id="table_payRecord" lay-filter="table_payRecord"></table>
	<div id="detailDiv" class="layui-hide">
	<table class="layui-table" id="table_detailRecord" lay-filter="table_detailRecord"></table>
	</div>
<%@include file="/common/common.jsp" %>
<script>
layui.use(['table','laydate','form'], function(){
  var table = layui.table;
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
  
  
  table.render({
    elem: '#table_payRecord'
    ,height:'full-100'
    ,url: '/fin/queryPayRecordList'
    ,where: {transactionType:0}
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'recordTime', title: '录入时间',templet: '<div>{{ common.toDateString(d.recordTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{field: 'recorderName', title: '录入人'}
      ,{field: 'dealId', title: '成交编号',
    	  templet:function(d){
  			return '<a href="/deal/deal/dealDetailPage?id='+d.id+'" target="_blank">'+ d.dealId +'</a>';
  		}}
      ,{field: 'examineStatus', title: '状态',
    	  templet:function(d){
    		  if(d.examineStatus=='0') return '未审核';
    		  if(d.examineStatus=='1') return '已审核';
    		  if(d.examineStatus=='2') return '已驳回';
    		  return '';
    	  }}
      ,{field: 'auditTime', title: '审核时间',templet: '<div>{{ common.toDateString(d.auditTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{field: 'auditor', title: '审核人'}
      ,{field: 'reason', title: '驳回原因'}
      ,{field: 'billNo', title: '票据编号'}
      ,{field: 'moneyTypeName', title: '费用类型'}
      ,{field: 'price', title: '金额', templet:function(d){
			return d.price/100;
		}}
      ,{field: 'payerType', title: '收取方', templet:function(d){
    	  if(d.payerType=='0') return '业主';
		  if(d.payerType=='1') return '客户';
		  return '';
		}}
      ,{field: 'tradeTime', title: '收取时间',templet: '<div>{{ common.toDateString(d.tradeTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{field: 'settlementTypeName', title: '结算方式'}      
      ,{field: 'remark', title: '备注'}   
      ,{field: 'id', title: '操作',templet:function(d){
    	  if(d.examineStatus=='0') return '<a href="javascript:void(0);" lay-event="tongGuo">审核</a>&nbsp;<a href="javascript:void(0);" lay-event="boHui">驳回</a>';
		  if(d.examineStatus=='1') return '<a href="javascript:void(0);" lay-event="info">详情</a>';
		  return '';			
		}} 
    ]]
  });
  
//监听工具条
  table.on('tool(table_payRecord)', function(obj){
	  var data = obj.data //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	   if(layEvent === 'tongGuo'){
		   layer.confirm('您确认要审核吗?', function(index){
			   $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            data: {id:data.id,status:1},
		            url: "/fin/updatePayStatus" ,	                
		            //data:data,
		            success: function (result) {
		            	if(result.success){
		            		layer.close(index); 
		            		layer.msg("已审核");
		            		active.reload();
		            	}else{
		            		layer.alert(result.msg);
		            	}
		            },
		            error : function() {	            	
		                layer.alert("异常！");
		            }
		        });
			 });
	   }
	  if(layEvent=='boHui'){
		  layer.prompt({title:'驳回原因'},function(value, index, elem){
			  $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            data: {id:data.id,status:2,reason:value},
		            url: "/fin/updatePayStatus" ,	                
		            //data:data,
		            success: function (result) {
		            	if(result.success){
		            		layer.close(index); 
		            		layer.msg("已驳回");
		            		active.reload();
		            	}else{
		            		layer.alert(result.msg);
		            	}
		            },
		            error : function() {	            	
		                layer.alert("异常！");
		            }
		        });
			  layer.close(index);
			});
	  }
	  if(layEvent=='info'){
		  layer.open({
			  title:'代收付款支出详情',
			  type: 1,
			  area: ['700px','500px'],
			  content: $('#detailDiv') 
			});
		  $("#detailDiv").removeClass("layui-hide");
	  }
  });
  
    var $ = layui.$, active = {
    reload: function(){
     //执行重载
      table.reload('table_payRecord', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	transactionType:0,
        	status:$("#status").val(),
        	moneyTypeId:$("#moneyTypeId").val(),
        	timeType:$("#timeType").val(),
        	beginTime:$("#beginTime").val(),
        	endTime:$("#endTime").val(),
        	settlementTypeId:$("#settlementTypeId").val(),
        	payerType:$("#payerType").val(),
        	keyword:$("#keyword").val(),
        	keywordType:$("#keywordType").val()
        }
      });
    }
  };
    
    table.render({
        elem: '#table_detailRecord'
        //,height:'full-100'
        //,url: '/fin/queryPayRecordList'
        //,where: {transactionType:0}
       	//,method:'post'
       	,data:[]
       	,width:700
        ,page: true //开启分页
        ,cols: [[ //表头
          {field: 'tradeTime', title: '支出时间',templet: '<div>{{ common.toDateString(d.recordTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
          ,{field: 'recorderName', title: '支出金额'}
          ,{field: 'examineStatus', title: '状态'}        
          ,{field: 'auditorName', title: '录入人'}
          ,{field: 'buildingsName', title: '所在部门'}
          ,{field: 'reason', title: '驳回原因'}   
          ,{field: 'remark', title: '备注'}   
          ,{field: 'id', title: '操作',templet:function(d){
    		  return '';			
    		}} 
        ]]
      });
    
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  $('#keywordQuery').on('keyup', function(){
	    $(this).parent().next().css("display","block");
	    var val=$(this).val();
	    val=val.substring(val.indexOf(":")+1);
	    $(this).parent().next().find("span.data").text(val);
	  });
	  
	  $('#keyDl').find("dd").on('click', function(){
		    $(this).parent().css("display","none");
		    $('#keyword').val($(this).find("span.data").text());
		    $('#keywordType').val($(this).attr("lay-value"));
		    $('#keywordQuery').val($(this).text());
		  });
});
</script> 
</body>
</html>