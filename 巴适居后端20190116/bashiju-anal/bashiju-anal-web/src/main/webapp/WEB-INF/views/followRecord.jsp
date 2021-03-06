<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
	<head>
	<title>跟进漏斗转化分析</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<jsp:include page="../common/common.jsp"></jsp:include> 
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
			<c:if test="${!empty companys}">
			    <div class="layui-input-inline">
			      <select id="companyId" name="companyId">
			      	<c:forEach items="${companys}" var="companys">
			      		<option value="${companys.id}">${companys.name}</option>
			      	</c:forEach>
			      </select>
			    </div>
		    </c:if>
			
		    <label class="layui-form-label">起始日期</label>
		    <div class="layui-input-inline">
		      <input type="text" id="beginDate" name="beginDate" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">截止日期</label>
		    <div class="layui-input-inline">
		      <input type="text" id="endDate" name="endDate" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">门店</label>
		    <div class="layui-inline">
	  			<div class="layui-input-inline" style="width:120px;"> 
					<input type="hidden" id="deptCode" name="deptCode">
					<input class="layui-input" id="deptName" name="deptName" placeholder="请选择门店" readonly="readonly" autocomplete="off">
				</div>
			</div>
		    <button class="layui-btn layui-btn-danger" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  </div>
	</div>
	<table id="test" class="layui-table" lay-size="sm" lay-filter="test"></table>
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
				elem : '#test',
				height : 'full-100',
				page : false,
				size:'sm',
				url : 'getData',
				cols : cols.rows[0].cells,
				toolbar: '<div>跟进漏斗转化分析<div>', 
				totalRow:true,
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
					merge(count,0,true,true);
				}
			});
			
			
			var $ = layui.$, active = {
		    reload: function(){
				var beginDate = $("#beginDate").val();
				var endDate = $("#endDate").val();
				var deptId=$("#deptCode").val();
				var companyId = '';
				if($("#companyId").val()!='undefined')
					companyId=$("#companyId").val();
		     //执行重载
		      table.reload('test', {
// 		        page: {
// 		          curr: 1 //重新从第 1 页开始
// 		        },
		        where: {
		        	beginDate: beginDate,
		        	endDate: endDate,
		        	deptId:deptId,
		        	companyId:companyId
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