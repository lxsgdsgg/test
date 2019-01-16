<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	<div class="toolBar">
		角色名称：
	  	<div class="layui-inline">
	    	<input class="layui-input" name="queryRoleName" placeholder="请输入角色名称" id="queryRoleName" autocomplete="off">
	  	</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.roleAdd!='' && onlyBtnMenus.roleAdd!=null}">
	  		<button class="layui-btn" name="roleAdd" onclick="role.add()">新增</button>
	  	</c:if>
	</div>
	<table class="layui-table" id="table_role" lay-filter="table_role"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
 {{# if(d.roleEdit !='' && d.roleEdit!=null){ }}
  	<a class="layui-btn layui-btn-xs" name="roleEdit" lay-event="edit">编辑</a>
 {{# } }}
  {{# if(d.authorization !='' && d.authorization!=null){ }}
  	<a class="layui-btn layui-btn-xs" name="authorization" lay-event="rolePermission">角色授权</a>
  {{# } }}
  {{# if(d.adminAuthorization !='' && d.adminAuthorization!=null){ }}
  	<a class="layui-btn layui-btn-xs" name="adminAuthorization" lay-event="adminPermission">平台授权</a>
  {{# } }}
  {{# if(d.roleDel !='' && d.roleDel!=null){ }}
  	<a class="layui-btn layui-btn-danger layui-btn-xs" name="roleDel" lay-event="del">删除</a>
  {{# } }}
</script>
<script>
               
role = {
	add:function(){
		var title = '角色新增页面 ';
    	//页面层
    	common.windowLayerOpen({
	        type: 2 //此处以iframe举例
	        ,id:"deptEditFrame"
	        ,title: title
	        ,area: ['500px', '300px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: 'detailPage'
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	        }
// 			,end: function(index, layero){ 
// 				layer.load();
//     			location.reload();
// 			}
	      });  
	}
}
layui.use('table', function(){
  var table = layui.table;
  //第一个实例
  table.render({
    elem: '#table_role'
    ,height:'full-100'
    ,url: 'getRoleData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
      ,{field: 'name', title: '角色名称', sort: true}
      ,{field: 'operator', title: '操作人', sort: true}
      ,{field: 'addTime', title: '新增时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
      ,{field: 'sortNo', title: '排序码',width: 120, sort: true}
      ,{fixed: 'right', title: '操作', width: 200, align:'center', toolbar: '#operationBar'}
    ]]
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var queryRoleName = $("#queryRoleName").val();
     //执行重载
      table.reload('table_role', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	roleName: queryRoleName
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_role)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var roleId = data.id;
    if(layEvent === 'del'){ //删除
      layer.confirm('确定要删除该角色吗？', function(index){
    	  $.ajax({
    		  type:"post",
    		  url:"delRole",
    		  dataType:"json",
    		  data:{roleId:roleId},
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
    } else if(layEvent === 'edit'){ //编辑
    	var title = '角色编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">角色名称：【'+data.name+'】</span>';
    	//页面层
    	common.windowLayerOpen({
	        type: 2 //此处以iframe举例
	        ,id:"deptEditFrame"
	        ,title: title
	        ,area: ['500px', '300px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: 'detailPage?roleId='+roleId
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	        }
// 			,end: function(index, layero){ 
// 				layer.load();
//     			location.reload();
// 			}
	      });  
    	
    }else if(layEvent === 'rolePermission'){//授权
    	var title = '角色授权管理 <span style="margin-left: 24%;color: orange;font-size: 18px;">角色名称：【'+data.name+'】</span>';
		var option = {
		        type: 2 //此处以iframe举例
		        ,id:"rolePermissionIFrame"
		        ,title: title
		        ,area: ['90%', '90%']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
//	         	,content: 'http://www.baidu.com'
		        ,content: '/manage/permission/permission?roleId='+roleId
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      };
    	common.windowLayerOpen(option);
    }else if(layEvent === 'adminPermission'){
    	var title = '超管员授权管理 <span style="margin-left: 24%;color: orange;font-size: 18px;">角色名称：【'+data.name+'】</span>';
		var option = {
		        type: 2 //此处以iframe举例
		        ,id:"adminPermissionIFrame"
		        ,title: title
		        ,area: ['90%', '90%']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
//	         	,content: 'http://www.baidu.com'
		        ,content: '/manage/adminPermission/permissionPage?roleId='+roleId
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      };
    	common.windowLayerOpen(option);
    }
  });
  
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
});
</script> 
</body>
</html>