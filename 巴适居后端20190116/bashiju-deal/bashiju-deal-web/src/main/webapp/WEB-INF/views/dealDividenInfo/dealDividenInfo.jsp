<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分成明细查询</title>
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
	<div class="toolBar layui-form">
	  	<div class="layui-inline">
			<select name="dealType" id="dealType">
			<option value="">类型</option>
				<c:forEach var="DealType" items="${DealType}">
					<option value="${DealType.value}">${DealType.name}</option>
				</c:forEach>
			</select>
		</div>
		  	<div class="layui-inline">
			        <input type="text" class="layui-input" id="beginTime" placeholder="起始日期">
		    </div>
		    <div class="layui-inline">
			        <input type="text" class="layui-input" id="endTime" placeholder="结束日期">
		    </div>
			<div class="layui-inline">
				<select name="dividerId" id="dividerId">
					<option value="">分成人</option>
					<option value="13320">测试人员<option>
				</select>
			</div>
			<div class="layui-inline">
					<input class="layui-input" name="dealId" placeholder="成交编号" id="dealId" autocomplete="off">
			</div>
			<div class="layui-inline">
				<select name="isValid" id="isValid">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效<option>
				</select>
			</div>
		 <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}">
			<button class="layui-btn"  type="button"  name="clear">清空</button>
	  	</c:if>
	</div>
	<table class="layui-table" id="table_DealDividenInfo" lay-filter="table_DealDividenInfo"></table>
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
  
    layui.laytpl={
			 //转换描述
			toDealTypeStr : function(dealType){
			  var dd = ${dealType};
				if('${dealType}'!='')
					return dd[dealType]
				else
					return dealType;
			},
			toAddressStr : function(address){
				debugger;
			  var dd = ${dealType};
				if('${dealType}'!='')
					return dd[dealType]
				else
					return dealType;
			}
	  }
  table.render({
    elem: '#table_DealDividenInfo'
    ,height:'full-100'
    ,url: 'queryDealDividenInfoData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'address', title: '地址', sort: true, fixed: 'left'}
      ,{field: 'deadlId', title: '成交编号', sort: true}
      ,{field: 'addTime', title: '成交日期', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{field: 'dividerName', title: '分成人'}
      ,{field: 'dividerDeptName', title: '分成人门店'}
      ,{field: 'dealType', title: '类型',templet: '<div>{{ layui.laytpl.toDealTypeStr(d.dealType) }}</div>'}
      ,{field: 'dividReason', title: '分成缘由'}
      ,{field: 'estimateProfit', title: '合同业绩'}
      ,{field: 'actualProfit', title: '实收业绩'}
    ]]
  });
  
    var $ = layui.$, active = {
    reload: function(){
     //执行重载
      table.reload('table_DealDividenInfo', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	dealType:$("#dealType").val(),
        	beginTime:$("#beginTime").val(),
        	endTime:$("#endTime").val(),
        	dividerId:$("#dividerId").val(),
        	isValid:$("#isValid").val(),
        	dealId:$("#dealId").val()
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