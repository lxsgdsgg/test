<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title>全员业绩分析</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<jsp:include page="../../common/common.jsp"></jsp:include> 
	
	<style type="text/css">
		.layui-form-item .layui-input-inline {
		    float: left;
		    width: 109px;
		    margin-right: 10px;
		}
		.layui-input, .layui-textarea {
		    display: block;
		    width: 100%;
		    padding-left: 10px;
		    border: none;
		    border-bottom: 1px solid #DDDD;
		    /* width: 55%; */
		}
		.layui-table-tool-temp {
		    padding-right: 120px;
		    font-size: large;
		    font-weight: unset;
		}
	</style>
	</head>
	<body>
	<div class="layui-form">
		<div class="layui-form-item">
		    <label class="layui-form-label">门店</label>
  			<div class="layui-input-inline" style="width:120px;"> 
				<input type="hidden" id="deptCode" name="deptCode">
				<input class="layui-input" id="deptName" name="deptName" placeholder="请选择门店" readonly="readonly" autocomplete="off">
			</div>
		    <label class="layui-form-label">日期类型</label>
		    <div class="layui-input-inline">
		      <select id="confirmType" name="confirmType" required  lay-verify="required" placeholder="日期类型" autocomplete="off">
		      	<option value="0">佣金确认日期</option>
		      	<option value="1">佣金结案日期</option>
		      </select>
		    </div>
		    <label class="layui-form-label">开始日期</label>
		    <div class="layui-input-inline">
		      <input type="text" id="beginDate" name="beginDate" required  lay-verify="required" placeholder="开始日期" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">截止日期</label>
		    <div class="layui-input-inline">
		      <input type="text" id="endDate" name="endDate" required  lay-verify="required" placeholder="截止日期" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">部门类型</label>
		    <div class="layui-input-inline">
		      <select name="deptType" id="deptType">
		      	<c:forEach var="deptType" items="${deptType}">
		      		<option value="${deptType.key}">${deptType.value}</option>
		      	</c:forEach>
		      </select>
		    </div>
		    <button class="layui-btn layui-btn-danger" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  </div>
	</div>
	<table id="totalPerformanceAnalysis" class="layui-table" lay-size="sm" lay-filter="totalPerformanceAnalysis"></table>

	<script type="text/javascript">
		var deptInfo = JSON.parse('${deptInfo}');
		//部门选择控件
		var dept = new MultiSelection(deptInfo,function(code,name){
			$("#deptName").val(name);
			$("#deptCode").val(code);
			dept.hidden();
		},null,$("#deptName"),true);
		
		
		layui.use(['table','laydate'], function() {
			var table = layui.table;
			var laydate = layui.laydate;
			laydate.render({
			    elem: '#beginDate' //指定元素
		    	,theme: '#e8532b'
			    ,type:'date'
			    ,value :new Date()
			 });
			laydate.render({
			    elem: '#endDate' //指定元素
		    	,theme: '#e8532b'
		    	,type:'date'
	    		,value :new Date()
			 });
			
			var cols = ${thead};
			table.render({
				id: 'totalPerformanceAnalysis',
				elem : '#totalPerformanceAnalysis',
				height : 'full-100',
				page : false,
				size:'sm',
				url : 'queryReportData',
				cols : cols.rows[0].cells,
				toolbar: '<div>全员业绩分析<div>', 
// 				totalRow:true,
				defaultToolbar: ['filter', 'print', 'exports'],
				done : function(res, curr, count) {
					$(".layui-table-body").find("td").each(function(index,element){
						if($(this).attr("data-field")>0)
							$(this).css("display","none");
					});
					$(".layui-table-total").find("td").each(function(index,element){
						if($(this).attr("data-field")>0)
							$(this).css("display","none");
					});
// 					debugger;
					merge(count,0,true,false);
				}
			});
			
			
			
		var $ = layui.$, active = {
				reload : function() {
					var beginDate = $("#beginDate").val();
					var endDate = $("#endDate").val();
					var deptId = $("#deptCode").val();
					var deptType = $("#deptType").val();
					var confirmType = $("#confirmType").val();
					table.reload('totalPerformanceAnalysis', {
						where : {
							beginDate : beginDate,
							endDate : endDate,
							deptId : deptId,
							deptType : deptType,
							confirmType : confirmType
						}
					});
				}
			};

			$('#searchBtn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
	</body>	
</html>