<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经纪人能力认定</title>
</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-inline">
			<label class="layui-form-label">考核项目名</label>
			<div class="layui-input-block">
				<input type="text" id="name" cityCode   placeholder="考核项目名" autocomplete="off" class="layui-input" >
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">经纪人名称</label>
			<div class="layui-input-block">
				<input type="text" id="userName" cityCode   placeholder="经纪人名称" autocomplete="off" class="layui-input" >
			</div>
		</div>
<%-- 	  	<c:if test="${onlyBtnMenus.blackListAdd!='' && onlyBtnMenus.blackListAdd!=null}"> --%>
			<button class="layui-btn"  type="button" onclick="agentAbilityIdentify.add(this)" name="agentAbilityIdentifyAdd" >新增</button>
<%-- 	  	</c:if> --%>

<%--   	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}"> --%>
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
<%-- 	  	</c:if> --%>
	</div>
	
<table class="layui-table" id="table_agentAbilityIdentify" lay-filter="table_agentAbilityIdentify"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.agentAbilityIdentifyEdit !='' && d.agentAbilityIdentifyEdit !=null){ }}
  {{# } }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="agentAbilityIdentifyEdit">编辑</a>

  {{# if(d.agentAbilityIdentifyDel !='' && d.agentAbilityIdentifyDel !=null){ }}
  {{# } }}	
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="agentAbilityIdentifyDel">删除</a>

</script>
<script type="text/javascript">
agentAbilityIdentify = {
		add:function(){
			  var title = '经纪人能力认定新增页面<span style="margin-left: 24%;color: orange;font-size: 18px;">房源等级条件名称：【】</span>';
			   	common.windowLayerOpen({
			        type: 2 //此处以iframe举例
			        ,id:"enterAgentAbilityidentifyAddPage"
			        ,title: title
			        ,area: ['500px', '550px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterAgentAbilityidentifyAddPage'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
			      }); 
			}
	}
layui.use('table', function(){
	  var table = layui.table;
	  table.render({
		    elem: '#table_agentAbilityIdentify'
		    ,method:"post"
		    ,url: 'queryAgentAbilityidentifyDate'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: '中介名称'}
		      ,{field: 'name', title:'考核项目名称', sort: true} 
		      ,{field: 'agentId', title: '经纪人详情编号', sort: true}
		      ,{field: 'userId', title: '用户编号', sort: true}
		      ,{field: 'userName', title: '经纪人名称', sort: true}
		      ,{field: 'achievement', title: '考核成绩', sort: true}
		      ,{field: 'introduction', title: '考核项目简介', sort: true}
		      ,{field: 'operator', title: '操作人', sort: true}
		      ,{field: 'operatorId', title: '操作人Id', sort: true}
		      ,{field: 'addTime', title: '添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'updateTime', title: '修改时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}		
		      ]]
		  });
	   var $ = layui.$, active = {
			    reload: function(){
			    //执行重载
			      table.reload('table_agentAbilityIdentify', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	name: $("#name").val(),
			        	userName:$("#userName").val()
			        }
			      });
			    }
			  };
   		  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
   		  
   	 	//监听工具条
   		  table.on('tool(table_agentAbilityIdentify)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
   		    var data = obj.data; //获得当前行数据
   		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
   		    var tr = obj.tr; //获得当前行 tr 的DOM对象
   		   	var id = data.id;
   		    if(layEvent === 'del'){ //删除
   		    	alert()
   		      layer.confirm('确定要删除吗？', function(index){
   		    	  $.ajax({
   		    		  type:"post",
   		    		  url:"delAgentAbilityidentify",
   		    		  dataType:"json",
   		    		  data:{id:id},
   		    		  success:function(data){
   		    			  if(data.success){
   		    				  layer.msg('删除成功', {icon: 1,time:500});
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
   		    } else if(layEvent === 'edit'){ //编辑
   			  var title = '经纪人能力认定新增页面<span style="margin-left: 24%;color: orange;font-size: 18px;">房源等级条件名称：【】</span>';
			   	common.windowLayerOpen({
			        type: 2 //此处以iframe举例
			        ,id:"enterAgentAbilityidentifyAddPage"
			        ,title: title
			        ,area: ['500px', '550px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterAgentAbilityidentifyAddPage?id='+id
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
			      });  
   		      }
   		  });
})	  
</script>
</body>
</html>