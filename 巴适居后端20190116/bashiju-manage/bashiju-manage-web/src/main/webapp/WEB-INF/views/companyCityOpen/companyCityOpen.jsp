<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公司城市开通</title>
</head>
<body>
<%@include file="/common/common.jsp" %>
<div class="layui-container" style="margin-top: 20px;">
<div class="toolBar layui-form">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">省级编码</label>
			<div class="layui-input-block">
				<input type="text" name="provinceCode_query" id="provinceCode_query" placeholder="请选择开通省份" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">城市编码</label>
			<div class="layui-input-block">
				<input type="text" name="cityCode_query" id="cityCode_query" placeholder="请选择开通城市" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">公司名称</label>
			<div class="layui-input-block">
				<input type="text" name="companyName_query" id="companyName_query" placeholder="模糊查询" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
<%-- 			<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"> --%>
		  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
<%-- 		  	</c:if> --%>
<%-- 		  	<c:if test="${onlyBtnMenus.addMenu!='' && onlyBtnMenus.addMenu!=null}"> --%>
		  		<button class="layui-btn" name="addMenu" onclick="companyCityOpen.add()">新增</button>
<%-- 		  	</c:if> --%>
	  	</div>
	</div>
</div>
	<table class="layui-table" id="table_companyCityOpen" lay-filter="table_companyCityOpen"></table>
</div>
<div style="display: none;" id="companyCityOpenAddWindow">
	<form action="" class="layui-form">
		<div class="layui-form-item">
	      <label class="layui-form-label">城市</label>
	      <div class="layui-input-inline">
	      	<input lay-verify="required" type="text" name="cityCode" id="cityCode" placeholder="请选择城市编码" class="layui-input"/>
	      	<input lay-verify="required" type="text" name="cityName" id="cityName" placeholder="通过选择城市获取名称" class="layui-input"/>
	      	<input lay-verify="required" type="text" name="provinceCode" id="provinceCode" placeholder="通过选择城市获取所属省" class="layui-input"/>
	      	<input lay-verify="required" type="text" name="provinceName" id="provinceName" placeholder="通过选择城市获取所属省名称" class="layui-input"/>
	      </div>
		</div>
		<div class="layui-form-item">
	      <label class="layui-form-label">公司</label>
	      <div class="layui-input-block">
	      	<input lay-verify="required" type="text" name="companyId" id="companyId" placeholder="请选择公司" class="layui-input"/>
	      	<input lay-verify="required" type="text" name="companyName" id="companyName" placeholder="通过选择公司获取公司名称" class="layui-input"/>
	      </div>
		</div>
		<div class="layui-form-item">
	      <label class="layui-form-label">是否开通</label>
	      <div class="layui-input-block">
	      	<select name="isOpen" >
	      		<option value="0">否</option>
	      		<option value="1">是</option>
	      	</select>
	      </div>
		</div>
		<div class="layui-form-item">
	      <label class="layui-form-label">开通时间</label>
	      <div class="layui-input-block">
	      	<input lay-verify="required" type="text" name="beginTime" id="beginTime" placeholder="选择起始时间" class="layui-input"/>
	      	<input lay-verify="required" type="text" name="endTime" id="endTime" placeholder="选择截止时间" class="layui-input"/>
	      </div>
		</div>
		<div class="layui-form-item">
	      <button class="layui-btn" lay-submit lay-filter="cityOpenForm">立即提交</button>
		</div>
	</form>
</div>
<script type="text/html" id="operationBar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
var companyCityOpen = {
	add:function(){
		var title = '公司城市开通页面 ';
    	var option = {
   	        type: 1 
   	        ,id:"companyCityOpenIFrame"
   	        ,title: title
   	        ,area: ['400px', '400px']
   	        ,shade: 0.5
   	        ,resize:false//不允许拉伸
   	        ,maxmin: false
   	        ,offset: 'auto' 
   	        ,content: $("#companyCityOpenAddWindow")
   	        ,zIndex: layer.zIndex //重点1
   	        ,success: function(layero){
   	        }
   	      };
    	layer.open(option);
	}
}


  layui.use(['form','element','table'],function(){
	  var form = layui.form,
      formSelects = layui.formSelects,
      element = layui.element,
	  table = layui.table;
	
	  table.render({
		    elem: '#table_companyCityOpen'
		    ,height:'full-100'
		    ,url: 'getData'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
		      ,{field: 'provinceCode', title: '省级编码',width: "10%", sort: true}
		      ,{field: 'provinceName', title: '省级名称',width: "10%"}
		      ,{field: 'cityCode', title: '城市编码',width: "10%", sort: true}
		      ,{field: 'cityName', title: '城市名称',width: "10%"}
		      ,{field: 'companyName', title: '公司名称',width: "10%"}
		      ,{field: 'isOpen', title: '开通状态',width: "10%"}
		      ,{field: 'beginTime', title: '开通开始时间',width: "10%"}
		      ,{field: 'beginTime', title: '开通截止时间',width: "10%"}
		      ,{field: 'addTime', title: '开通时间',width: "10%"}
		      ,{ title: '操作',width: "10%", align:'center', toolbar: '#operationBar'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
		  
		    var $ = layui.$, active = {
		    reload: function(){
				var provinceCode = $("#provinceCode_query").val();
				var cityCode = $("#cityCode_query").val();
				var companyName = $("#companyName_query").val();
		     //执行重载
		      table.reload('table_companyCityOpen', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		        	provinceCode: provinceCode,
		        	cityCode: cityCode,
		        	companyName: companyName
		        }
		      });
		    }
		  };
		  
		//监听工具条
		  table.on('tool(table_companyCityOpen)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	var id = data.id;
		    if(layEvent === 'del'){ //删除
		      layer.confirm('确定要取消开通该城市吗？', function(index){
		    	  $.ajax({
		    		  type:"post",
		    		  url:"cancelCityOpen",
		    		  dataType:"json",
		    		  data:{id:id},
		    		  success:function(data){
		    			  if(data.success){
		    				  layer.tips(data.msg);
		    				  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
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
		  });
		  
		  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	  	
	  //监听提交
	  form.on('submit(cityOpenForm)', function(data){
	    $.ajax({
	    	type:"post",
	    	url:"saveOrUpdateCityOpen",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(data.field)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	  });
  });
</script> 
</body>
</html>