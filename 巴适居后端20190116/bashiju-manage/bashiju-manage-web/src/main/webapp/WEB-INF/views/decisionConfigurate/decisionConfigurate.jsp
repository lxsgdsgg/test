<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>决策配置</title>

</head>
<body>
	<div class="toolBar layui-form">
		所属城市：
	  	<div class="layui-inline">
	    	<input type="text" id="cityCode"/>
	  	</div>
		决策类型：
	  	<div class="layui-inline">
	    	<select name='type' id="queryType">
	    		<option value="">请选择</option>
	    		<c:forEach var = "decisionConfigurateType" items="${decisionConfigurateType}">
	    			<option value="${decisionConfigurateType.key}">${decisionConfigurateType.value}</option>
	    		</c:forEach>
	    	</select>
	  	</div>
		决策名称：
	  	<div class="layui-inline">
	    	<input class="layui-input" name="name" placeholder="请输入名称" id="queryName" autocomplete="off">
	  	</div>
<%-- 	  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"> --%>
	  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
<%-- 	  	</c:if> --%>
<%-- 	  	<c:if test="${onlyBtnMenus.roleAdd!='' && onlyBtnMenus.roleAdd!=null}"> --%>
<!-- 	  		<button class="layui-btn" name="roleAdd" onclick="role.add()">新增</button> -->
<%-- 	  	</c:if> --%>
	</div>
	<table class="layui-table" id="table_decisionConfigurate" lay-filter="table_decisionConfigurate"></table>
	
	<div style="display: none;" id="setUp_div">
		<input type="hidden" id="decision_id" name="decisionId"/>
		<input type="hidden" id="decisionDetail_id" name="id"/>
		<label id="decision_name"></label>
		<div id="decision_descs"></div>
		<button class="layui-btn" onclick="saveDesisionSetUp()">立即提交</button>
	</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.setUp !='' && d.setUp !=null){ }}
  	<a class="layui-btn layui-btn-xs" name="setUp" lay-event="setUp">设置</a>
  {{# } }}
</script>
<script>

function saveDesisionSetUp(){
	var decisionId = $("#decision_id").val();
	var val = $("#val").val();
	var id = $("#decisionDetail_id").val();
	var cityCode = $("#cityCode").val();
	if(val=="" || val.length<=0){
		layer.alert("设置值不允许为空");
		return false;
	}
	if(cityCode=="" || cityCode.length<=0){
		layer.alert("请选择所属城市");
		return false;
	}
	var data = {
		id:id,
		decisionId: decisionId,
		cityCode : cityCode,
		val:val
	}
	$.ajax({
		type:"post",
		url:"saveDecisisionCOnfigurate",
		data:{jsonData:JSON.stringify(data)},
		dataType:"json",
		success:function(data){
			if(data.success){
				layer.alert("操作成功",function(){
					location.reload();
				});
			}else{
				layer.alert(data.desc);
			}
		},
		error:function(a,b,c){
			layer.alert("操作异常，请联系管理员");
		}
	});
}

layui.use('table', function(){
  var table = layui.table;
  format = function(desc,val,jsonStr){
	  if(jsonStr == "" || jsonStr == undefined || jsonStr.length<=0)
		  return desc.replace("?",'<font color="red">'+val+'</font>');
	  var json = JSON.parse(jsonStr)
	  return desc.replace('?','<font color="red">'+json[val]+'</font>');
  }
  //第一个实例
  table.render({
    elem: '#table_decisionConfigurate'
    ,height:'full-100'
    ,url: 'getData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
      ,{field: 'type', title: '角色类型', sort: true}
      ,{field: 'name', title: '角色名称', sort: true}
      ,{field: 'descs', title: '说明', templet: '<div>{{ format(d.descs,d.val,d.jsonSelect) }}</div>'} 
      ,{fixed: 'right', title: '操作', width: 200, align:'center', toolbar: '#operationBar'}
    ]]
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var queryType = $("#queryType").val();
		var queryName = $("#queryName").val();
		var cityCode = $("#cityCode").val();
     //执行重载
      table.reload('table_decisionConfigurate', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	type : queryType,
        	name : queryName,
        	cityCode : cityCode
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_decisionConfigurate)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var roleId = data.id;
    if(layEvent === 'setUp'){ //编辑
    	var title = '设置决策参数';
    	//页面层
    	layer.open({
	        type: 1 //此处以iframe举例
	        ,id:"decisionSetUpFrame"
	        ,title: title
	        ,area: ['500px', '300px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $('#setUp_div')
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	        	$("#decision_name").text(data.name);
	        	$("#decisionDetail_id").text(data.id);
	        	$("#decision_id").val(data.decisionId);
	        	var jsonStr = data.jsonSelect;
	        	var replaceStr = "";
	        	if(jsonStr == "" || jsonStr == undefined || jsonStr.length<=0){
	        		replaceStr = '<input type="number" id="val" name="val" value="'+data.val+'"/>';
	        	}else{
	        		var json = JSON.parse(jsonStr);
	        		replaceStr +='<select id="val" name="val">'
	        		for(key in json){
	        			replaceStr +='<option value="'+key+'" '+(data.val==key?"selected":"")+'>'+json[key]+'</option>';
	        		}
	        		replaceStr +="</select>"
	        	}
        		var html = data.descs.replace("?",replaceStr);
        		$("#decision_descs").html(html);
	        }
	      });  
    }
  });
  
  $('#searchBtn').on('click', function(){
	  if($("#cityCode").val()==""){
		  layer.alert("请选择所属城市");
		  return false;
	  }
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
});
</script> 
</body>
</html>