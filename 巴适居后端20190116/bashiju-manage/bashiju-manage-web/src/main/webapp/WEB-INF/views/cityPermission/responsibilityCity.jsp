<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市管理员授权页</title>
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<style type="text/css">
	.city{
		padding: 5px;
	    background: #DDDD;
	    border-radius: 5px;
	    margin: 3px;
	}
	.city>i{
		cursor: pointer;
		color:red;
	}
</style>
</head>
<body>
<div class="toolBar layui-form ">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
				<select name="roleId_search" id = "roleId_search" lay-verify="" lay-search>
					<option value="">请选择</option>
					<c:forEach var="roles" items="${roles}">
					  <option value="${roles.id}">${roles.name}</option>
					</c:forEach>
				</select>   
			</div>
		</div>
		<div class="layui-inline">
<%-- 				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}"> --%>
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
<%-- 			  	</c:if> --%>
<%-- 			  	<c:if test="${onlyBtnMenus.addMenu!='' && onlyBtnMenus.addMenu!=null}"> --%>
			  		<button class="layui-btn" name="addMenu" onclick="responsibilityCity.add()">新增</button>
<%-- 			  	</c:if> --%>
		  	</div>
	</div>
</div>
<table class="layui-table" id="table_responsibility" lay-filter="table_responsibility"></table>
<div class="layui-form" id="editResponsibilityCity" style="display: none;">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block">
				<select name="roleId_edit" id="roleId_edit" lay-filter="select" lay-verify="select" lay-search>
					<option value="">请选择</option>
					<c:forEach var="roles" items="${roles}">
					  <option value="${roles.id}">${roles.name}</option>
					</c:forEach>
				</select>   
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">请选择城市</label>
			<div class="layui-input-block">
				<div id="selectCity" style="width: 521px;height: 36px;border: 1px solid #DDDD;line-height: 36px;">
				</div>
			</div>
		</div>
	</div>
	<div class="layui-form-item">
	   <div class="layui-input-block">
	      <button class="layui-btn" id="saveResiposibilityCity" lay-submit lay-filter="saveResiposibilityCity">立即提交</button>
	      <button class="layui-btn layui-btn-primary"id="cancel">取消</button>
	    </div>
	 </div>
</div>

<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script>
<script  type="text/html" id="operationBar">
	<a class="layui-btn layui-btn-xs" name="menuEdit" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" name="menuDel" lay-event="del">删除</a>
</script>
<script>
var arr = [];
var roles=[];
var responsibilityCity = {
	add:function(){
		var title = '菜单新增页面';
		var option = {
	        type: 1 
	        ,id:"responsibilityCityIFrame"
	        ,title: title
	        ,area: ['700px', '300px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $('#editResponsibilityCity')
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	        }
			,end : function(){
				$("#roleId_edit").val("");
				form.render("select");
				$("#selectCity").html("");
				arr = [];
			}
	      };
		layer.open(option);
	}
	
}
layui.use(['table','form'], function(){
	var table = layui.table;
	form = layui.form;
	
	layui.laytpl = {
			//将角色id存到数组中用于判断新增时是否重复
		getRoleArr : function(roleId){
			roles.push(roleId+"");
			return roleId;
		}
	};
	
	table.render({
	    elem: '#table_responsibility'
	    ,height:'full-100'
	    ,url: 'getResponsibilityData'
	   	,method:'post'
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'roleId', title: '角色Id', sort: true,templet:'<div>{{ layui.laytpl.getRoleArr(d.roleId) }}</div>'}
	      ,{field: 'roleName', title: '角色名称',width: "10%", sort: true}
	      ,{field: 'areaCode', title: '责任城市代码', sort: true}
	      ,{field: 'areaName', title: '责任城市',width: "80%", sort: true}
	      ,{ title: '操作',width: "10%", align:'center', toolbar: '#operationBar'}
	    ]]
	  	,done: function(res, curr, count){
	  		$("[data-field='roleId']").css('display','none');
	  		$("[data-field='areaCode']").css('display','none');
	  	}
	 });
	
	var $ = layui.$, active = {
	    reload: function(){
			var roleId_search = $("#roleId_search").val();
	     //执行重载
	      table.reload('table_responsibility', {
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	        ,where: {
	        	roleId: roleId_search
	        }
	      });
	   }
	};
	
	//监听工具条
	  table.on('tool(table_responsibility)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var roleId = data.roleId;
	    if(layEvent === 'del'){ //删除
	      layer.confirm('删除该信息后该角色的权限信息也将删除，您确定要这么做吗？', function(index){
	    	  $.ajax({
	    		  type:"post",
	    		  url:"delResponsibilityCity",
	    		  dataType:"json",
	    		  data:{roleId:roleId},
	    		  success:function(data){
	    			  if(data.success){
	    				  layer.alert("操作成功",function(){
							 layer.closeAll();
							 location.reload();
						 });
	    			  }else{
	    				  layer.alert(data.msg);
	    			  }
	    		  },
	    		  error:function(a,b,c){
	    			  layer.alert("操作异常，请稍后重试");
	    		  }
	    	  });
	      });
	    } else if(layEvent === 'edit'){ //编辑
	    	arr = data.areaCode.split(",");
	    	var city = data.areaName.split(",");
	    	var cityStr = "";
	    	for(var i=0;i<city.length;i++){
	    		cityStr +='<span class="city" data-value='+arr[i]+'>'+city[i]+'<i class="layui-icon">&#x1006;</i></span>'
	    	}
	    	$("#selectCity").html(cityStr);
	    	$("#roleId_edit").val(data.roleId);
	    	form.render();
	    	cityInit();
	    	var title = '菜单新增页面';
			var option = {
		        type: 1 
		        ,id:"responsibilityCityIFrame"
		        ,title: title
		        ,area: ['700px', '300px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $('#editResponsibilityCity')
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
				,end : function(){
					$("#roleId_edit").val("");
					form.render("select");
					$("#selectCity").html("");
					arr = [];
				}
		      };
			layer.open(option);
	    }
	    
	  });
	  
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  	//取消操作
  	$("#cancel").click(function(){
  		$("#roleId_edit").val("");
		form.render("select");
		$("#selectCity").html("");
		arr = [];
		layer.closeAll();
  	});
	 //保存并授权
	 $("#saveResiposibilityCity").click(function(){
		 var roleId = $("#roleId_edit").val();
		 if(roleId==""){
			 top.layer.alert("请选择角色");
			 return false;
		 }
// 		 if(roles.includes(roleId)){
// 			 top.layer.alert("该角色已经存在，不允许重复添加");
// 			 return false;
// 		 }
		 var dataStr = arr.join(",");
		 if(arr.length<=0){
			 top.layer.alert("请选择该城市管理员负责的城市");
			 return false;
		 }
		 top.layer.confirm('保存信息后该角色的管理权限将发生变化，您确定要保存吗？', function(index){
			 $.ajax({
				 type:"post",
				 url:"saveResiposibilityCity",
				 dataType:"json",
				 data:{dataStr:dataStr,roleId:roleId},
				 success:function(data){
					 if(data.success){
						 top.layer.alert("操作成功",function(){
							 top.layer.closeAll();
							 location.reload();
						 });
					 }else{
						 top.layer.alert(data.msg);
					 }
				 },
				 error:function(a,b,c){
					 top.layer.alert("操作异常，请稍后重试");
				 }
			 });
		 });
	 });


	 form.on('select(select)', function(data){
		 if(roles.includes(data.value)){
			 top.layer.alert("该角色已经存在，不允许重复添加",function(){
				 top.layer.closeAll();
				 layer.closeAll();
			 });
			 return false;
		 }
	 });
});




var areaList=JSON.parse('${areaListJson}');
//areaList,selectArea(回掉函数)
var a= new AreaObject(areaList,selectArea,2,$("#selectCity"));
function selectArea(code,name){
	if(arr.includes(code)){
		top.layer.msg("该城市已经存在");
		return false;
	}
	 arr.push(code);
	var html = '<span class="city" data-value='+code+'>'+name+'<i class="layui-icon">&#x1006;</i></span>';
	$("#selectCity").append(html);
	cityInit();
}

function cityInit(){
	$("#selectCity i").click(function(event){
		event.stopImmediatePropagation();
		 arr.removeObj($(this).parent().attr("data-value"));
		 $(this).parent().remove();
	});
	$("#selectCity span").click(function(event){
		event.stopImmediatePropagation();
	});
}
cityInit();
</script> 
</body>
</html>