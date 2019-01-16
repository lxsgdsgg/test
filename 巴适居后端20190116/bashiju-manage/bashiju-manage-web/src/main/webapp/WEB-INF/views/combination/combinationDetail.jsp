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
<div class="layui-container layui-form" id="combinationForm" style="margin-top: 20px;">
	<div class="layui-tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">基础描述</li>
	    <li>组合条件</li>
	  </ul>
	  <div class="layui-tab-content">
	    <div class="layui-tab-item layui-show layui-row">
	    	<div class="layui-col-xs8">
				<div class="layui-form-item">
					<div class="layui-inline">
				      <label class="layui-form-label">组合名称</label>
				      <div class="layui-input-inline">
				        <input type="text" name="name" id="combinationName" value="${combination.name}" lay-verify="required" placeholder="请输入组合名称" autocomplete="off" class="layui-input">
				        <input type="hidden" id="menuId" value="${menuId}"/>
				        <input type="hidden" id="id" value="${combination.id}"/>
				      </div>
				      <div class="layui-form-mid layui-word-aux">尽可能不超过4个字符</div>
				    </div>
				</div>
				<div class="layui-form-item">
			      <label class="layui-form-label">关联按钮</label>
			      <div class="layui-input-block">
			        <select name="relationBtn" id="relationBtn" xm-select="select" xm-select-search xm-select-type="2" lay-verify="required">
			        	<c:forEach var="btns" items="${btns}">
							<option value="${btns.id}">${btns.name}</option>
			        	</c:forEach>
					</select>
			      </div>
				</div>
				<div class="layui-form-item">
			      <label class="layui-form-label">备注</label>
			      <div class="layui-input-block">
			        <textarea name="remark" id="remark" placeholder="请输入该组合条件的备注信息"  class="layui-textarea">${combination.remark}</textarea>
			      </div>
				</div>
			</div>
			<div class="layui-col-xs3 remark">
				1.关联按钮为该组合条件限制的按钮范围</p>
				1.这个是个测试数据，注意添加数据哈</p>
				1.这个是个测试数据，注意添加数据哈</p>
				1.这个是个测试数据，注意添加数据哈</p>
				1.这个是个测试数据，注意添加数据哈</p>
				1.这个是个测试数据，注意添加数据哈</p>
			</div>
		</div>
	    <div class="layui-tab-item">
	    	<div class="layui-form-item">
	    		<div class="layui-inline">
			      <label class="layui-form-label">名称</label>
			      <div class="layui-input-inline">
			        <input type="text" id="fieldName" placeholder="sql语句中的条件名称" autocomplete="off" class="layui-input">
			      </div>
		      </div>
		      <div class="layui-inline">
			      <label class="layui-form-label">值</label>
			      <div class="layui-input-inline">
			        <input type="text" id="fieldVal" placeholder="条件属性对应的值" autocomplete="off" class="layui-input">
			      </div>
		      </div>
		      <div class="layui-inline">
		        <button class="layui-btn layui-btn-sm" id="addColumn">添加</button>
		      </div>
			</div>
			<table class="layui-table" id="table_combination" lay-filter="table_combination"></table>
	    </div>
	  </div>
	</div>
	<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="combinationForm">立即提交</button>
<!--       <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
    </div>
  </div>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>

function removeEmptyArray(obj){
	if(obj.length==undefined){
		return obj;
	}
}

	//No.1 定义拓展模块的路径
	layui.config({
		base: '${request.getContextPath()}/src/common/js/'
	}).extend({
		formSelects: 'formSelects-v3'
	});
  layui.use(['form','element', 'formSelects','table'],function(){
	  var form = layui.form,
      formSelects = layui.formSelects,
      element = layui.element,
	  table = layui.table;
		var data = [];
		var dd = '${details}';
		if(dd!=""){
			data = JSON.parse(dd);
		}
	  	var table_comination = table.render({
		    elem: '#table_combination'
		    ,data:data
		    ,height:'full-260'
		    ,cols: [[ //表头
		      {field: 'fieldName', title: '名称'}
		      ,{field: 'val', title: '值'}
		      ,{ title: '操作', align:'center', toolbar: '#operationBar'}
		    ]]
	  	 ,limit: 999999 //显示的数量
		  });
	  	
	  	table.on('tool(table_combination)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	  	    var data = obj.data; //获得当前行数据
	  	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	  	   	var menuId = data.id;
	  	    if(layEvent === 'del'){ //删除
	  	      layer.confirm('删除该行后将在保存后生效！', function(index){
	  	    	obj.del();
	  	    	layui.table.cache.table_combination.filter(removeEmptyArray);
	  	    	layer.close(index);
	  	      });
	  	    }
	  	});
	  	
	  	$("#addColumn").click(function(){  
	  		var fieldName = $("#fieldName").val();
	  		var fieldVal = $("#fieldVal").val();
	  		if(fieldName==""){
	  			layer.alert("名称不允许为空");
	  			return false;
	  		}
	  		if(fieldVal==""){
	  			layer.alert("值不允许为空");
	  			return false;
	  		}
          var oldData =  layui.table.cache.table_combination.filter(removeEmptyArray);
          var data1={"fieldName":fieldName,"val":fieldVal};  
          oldData.push(data1);  
          table_comination.reload({data:oldData});
          $("#fieldName").val("");
          $("#fieldVal").val("");
     });
  	form.on('submit(combinationForm)', function(data){
  		var name = $("#combinationName").val();
  		var relationBtns = formSelects.value('select', 'val');
  		if(relationBtns==undefined || relationBtns.length<=0){
  			layer.alert("关联按钮不允许为空");
  			return false;
  		}
  		var remark = $("#remark").val();
  		var groupInfo = layui.table.cache.table_combination.filter(removeEmptyArray);
  		if(groupInfo.length<=0){
  			layer.alert("组合条件不允许为空");
  			return false;
  		}
  		var newGroup = [];
  		for(var index in groupInfo){
			var obj = groupInfo[index];
			if(typeof(obj)=='object'){
				var d = {
					fieldName : obj.fieldName,
					val : obj.val
				}
				newGroup.push(d);
			}
		}
     $.ajax({
     	type:"post",
     	url:"saveOrUpdateCombination",
     	dataType:"json",
     	data:{name:name,relationBtns:relationBtns.join(","),remark:remark,
     		groupInfo:JSON.stringify(newGroup),id:$("#id").val(),menuId:$("#menuId").val()},
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
  	formSelects.value('select', '${combination.relationBtn}'.split(","));
  });
</script> 

</body>
</html>