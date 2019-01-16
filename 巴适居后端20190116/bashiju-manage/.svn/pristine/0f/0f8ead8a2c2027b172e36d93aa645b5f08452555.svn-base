<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据权限条件组合管理</title>

<style type="text/css">
	.remark{
		margin-top: 5px;
	    margin-left: 23px;
	    padding-left: 23px;
	    height: 210px;
	    border-left: 1px solid #DDDD;
	    font-style: italic;
	}
</style>
</head>
<body>
<div class="layui-container" style="margin-top: 20px;">
	<button class="layui-btn layui-btn-sm" onclick="combination.add()">新增</button>
	<table class="layui-table" id="table_combination" lay-filter="table_combination"></table>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
var combination = {
	add:function(){
		var title = '组合条件编辑页面 ';
    	var option = {
   	        type: 2 //此处以iframe举例
   	        ,id:"combinationPermissionIFrame"
   	        ,title: title
   	        ,area: ['700px', '400px']
   	        ,shade: 0.5
   	        ,resize:false//不允许拉伸
   	        ,maxmin: false
   	        ,offset: 'auto' 
   	        ,content: 'combinationDetail?menuId=${menuId}'
   	        ,zIndex: layer.zIndex //重点1
   	        ,success: function(layero){
   	          layer.setTop(layero); //重点2
   	        }
//    			,end: function(index, layero){ 
//    				layer.load();
//        			location.reload();
//    			}
   	      };
    	common.windowLayerOpen(option);
	}
}


  layui.use(['form','element','table'],function(){
	  var form = layui.form,
      formSelects = layui.formSelects,
      element = layui.element,
	  table = layui.table;
		var data = [];
		var dd = '${list}';
		if(dd!=""){
			data = JSON.parse(dd);
		}
	  	var table_comination = table.render({
		    elem: '#table_combination'
		    ,data:data
		    ,height:'full-260'
		    ,cols: [[ //表头
		      {field: 'name', title: '组合名称'}
		      ,{field: 'remark', title: '备注'}
		      ,{ title: '操作', align:'center', toolbar: '#operationBar'}
		    ]]
	  	 ,limit: 999999 //显示的数量
		  });
	  	
	  	table.on('tool(table_combination)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  	    var data = obj.data; //获得当前行数据
	  	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	  	   	var combinationId = data.id;
	  	    if(layEvent === 'del'){ //删除
	  	      layer.confirm('该数据删除后不可恢复，您确定要删除吗？', function(index){
	  	    	  $.ajax({
	  	    		  type:"post",
	  	    		  url:"delCombination",
	  	    		  dataType:"json",
	  	    		  data:{combinationId:combinationId},
	  	    		  success:function(dt){
	  	    			  if(dt.success){
				  	    	obj.del();
	  	    			  }else{
	  	    				  layer.alert(dt.msg);
	  	    			  }
	  	    		  },
	  	    		  error:function(a,b,c){
	  	    			layer.alert("操作异常，请稍后重试");
	  	    		  }
	  	    	  });
	  	    	layer.close(index);
	  	      });
	  	    }else if(layEvent === 'edit'){//编辑
	  	    	var title = '组合条件编辑页面 ';
	  	    	var option = {
  	    	        type: 2 //此处以iframe举例
  	    	        ,id:"combinationPermissionIFrame"
  	    	        ,title: title
  	    	        ,area: ['100%', '100%']
  	    	        ,shade: 0.5
  	    	        ,resize:false//不允许拉伸
  	    	        ,maxmin: false
  	    	        ,offset: 'auto' 
  	    	        ,content: 'combinationDetail?menuId=${menuId}&combinationId='+combinationId
  	    	        ,zIndex: layer.zIndex //重点1
  	    	        ,success: function(layero){
  	    	          layer.setTop(layero); //重点2
  	    	        }
//   	    			,end: function(index, layero){ 
//   	    				layer.load();
//   	        			location.reload();
//   	    			}
  	    	      };
  	    		common.windowLayerOpen(option);
	  	    }
	  	});
  });
</script> 

</body>
</html>