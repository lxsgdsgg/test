<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title>门店实收排行</title>
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
		    
		    <div class="layui-input-inline">
		      <input type="text" id="months" name="months" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		    </div>
		    
		    <button class="layui-btn layui-btn-danger" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  </div>
	</div>
	<table id="test" class="layui-table" lay-size="sm" lay-filter="test"></table> 
	<script type="text/javascript">
	
		
		
		
		layui.use(['table','laydate'], function() {
			var table = layui.table;
			var laydate = layui.laydate;
			laydate.render({
			    elem: '#months' //指定元素
		    	,theme: '#e8532b'
			    ,type:'month'
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
				toolbar: '<div>门店实收排行<div>', 
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
				var months=$("#months").val();
		     //执行重载
		      table.reload('test', {
// 		        page: {
// 		          curr: 1 //重新从第 1 页开始
// 		        },
		        where: {
		        	months:months
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