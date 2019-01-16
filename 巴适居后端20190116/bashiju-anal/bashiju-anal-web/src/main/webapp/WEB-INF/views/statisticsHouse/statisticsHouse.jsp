<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title>房源统计</title>
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
		.layui-form-select dl dd.layui-this {
		    background-color: #e8532b;
		    color: #fff;
		}
	</style>
	</head>
	<body>
	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-input-inline">
		      <select id="cityInfo" name="cityInfo">
		      	<c:forEach items="${cityInfo}" var="cityInfo">
		      		<option value="${cityInfo.cityCode}">${cityInfo.cityName}</option>
		      	</c:forEach>
		      </select>
		    </div>
		    <c:if test="${!empty companys}">
			    <div class="layui-input-inline">
			      <select id="companyId" name="companyId">
			      	<c:forEach items="${companys}" var="companys">
			      		<option value="${companys.id}">${companys.name}</option>
			      	</c:forEach>
			      </select>
			    </div>
		    </c:if>
		    <div class="layui-input-inline">
			    <select id="dateType">
			    	<option value="recordTime">录入日期</option>
			    	<option value="mainterFllowTime">维护日期</option>
			    </select>
		    </div>
		    <div class="layui-input-inline">
		      <input type="text" id="beginDate" name="beginDate" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
<!-- 		    <label class="layui-form-label">截止日期</label> -->
		    <div class="layui-input-inline">
		      <input type="text" id="endDate" name="endDate" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
<!-- 		    <label class="layui-form-label">门店</label> -->
		    <div class="layui-inline">
	  			<div class="layui-input-inline" style="width:120px;"> 
					<input type="hidden" id="deptCode" name="deptCode">
					<input class="layui-input" id="deptName" name="deptName" placeholder="请选择门店" readonly="readonly" autocomplete="off">
				</div>
			</div>
<!-- 			<label class="layui-form-label">横向维度</label> -->
		    <div class="layui-input-inline">
		      <select name="transverse" id="transverse">
		      	<c:forEach var="transverse" items="${transverse}">
		      		<option value="${transverse.key}">${transverse.value}</option>
		      	</c:forEach>
		      </select>
		    </div>
<!-- 		    <label class="layui-form-label">纵向维度</label> -->
		    <div class="layui-input-inline">
		      <select name="longitudinal" id="longitudinal">
		      	<c:forEach var="longitudinal" items="${longitudinal}">
		      		<option value="${longitudinal.key}">${longitudinal.value}</option>
		      	</c:forEach>
		      </select>
		    </div>
		    <button class="layui-btn layui-btn-danger" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  </div>
	</div>
	<div id="table_div"></div>
<!-- 	<table id="test" class="layui-table" lay-size="sm" lay-filter="test"></table> -->
	<script type="text/javascript">
	
		var deptInfo = JSON.parse('${deptInfo}');
		//部门选择控件
		var dept = new MultiSelection(deptInfo,function(code,name){
			$("#deptName").val(name);
			$("#deptCode").val(code);
			dept.hidden();
		},null,$("#deptName"),true);
		
		
		layui.use(['table','laydate'], function() {
			table = layui.table;
			laydate = layui.laydate;
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
			getReportDetailInfo = function(){
				var data = {
					cityCode:$("#cityInfo").val(),
					transverse:$("#transverse").val(),
					longitudinal:$("#longitudinal").val()	
				}
				if($("#companyId").val()!='undefined')
					data.companyId=$("#companyId").val();
				var dateType = $("#dateType").val();
				if(dateType=='recordTime'){
					data.beginRecordTime = $("#beginDate").val();
					data.endRecordTime = $("#endDate").val();
				}else if(dateType=='mainterFllowTime'){
					data.beginMainterFllowTime = $("#beginDate").val();
					data.endMainterFllowTime = $("#endDate").val();
				}
				$.ajax({
					type:"post",
					url:"getReportDetailInfo",
					data:data,
					dataType:"html",
					success:function(html){
						$("#table_div").html(html);
					},
					error:function(a,b,c){
						layer.alert("操作异常，请联系管理员");
					}
				});
			}
			var today = new Date();
			$("#beginDate").val(today.format('yyyy-MM-dd'));
			$("#endDate").val(today.format('yyyy-MM-dd'));
			getReportDetailInfo();
			
			$('#searchBtn').on('click', function(){
				getReportDetailInfo();
			  });
		});
	</script>
	</body>	
</html>