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
	<div class="toolBar layui-form">
		部门名称：
	  	<div class="layui-inline">
	    	<input class="layui-input" name="queryDeptName" placeholder="请输入职位名称" id="queryDeptName" autocomplete="off">
	  	</div>
		部门类型：
	  	<div class="layui-inline">
					<select name="deptTypeId" id="deptTypeId">
							<option value="">-请选择-</option>
							<c:forEach var="deptType" items="${deptType}">
								<option value="${deptType.id}">${deptType.name}</option>
							</c:forEach>
					</select>
		</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.deptAdd!='' && onlyBtnMenus.deptAdd!=null}">
			<button class="layui-btn"  type="button" onclick="dept.add(this)" name="deptAdd">新增</button>
	  	</c:if>
	  	
	</div>
	
	
	
<table class="layui-table" id="table_dept" lay-filter="table_dept"></table>

<!--编码的窗体-->
	 	<form class="toolBar" id="dept" style="display:none;margin-top: 20px;" >
	 		<div class="layui-form-item">
	 			<label class="layui-form-label">职位名称</label>
			    <div class="layui-input-inline">
			    	<input class="layui-input" name="name" placeholder="请输入部门名称" id="queryDeptName" autocomplete="off">
			    </div>
			  </div>
		</form>	
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.deptEdit !='' && d.deptEdit !=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="deptEdit">编辑</a>
  {{# } }}
  {{# if(d.deptDel !='' && d.deptDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="deptDel">删除</a>
  {{# } }}	
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="acrossStorePermission" name="acrossStorePermission">跨店权限</a>
</script>
<script type="text/javascript">
//添加
var dept={
		add:function(){
			var title = '部门新增页面';
			common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"deptAddFrame"
		        ,title: title
		        ,area: ['700px', '400px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'deptDetailPage'
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
// 				,end: function(index, layero){ 
// 					layer.load();
// 	    			location.reload();
// 				}
		      });
		}
}

layui.use('table', function(){
	  var table = layui.table;
	  //第一个实例
	  table.render({
	    elem: '#table_dept'
	    ,method:"post"
	    ,url: 'getDeptData'
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
	      ,{field: 'name', title: '部门名称', sort: true}
	      ,{field: 'companyName', title: '公司名称', sort: true} 
	      ,{field: 'deptTypeName', title: '部门类型', sort: true} 
	      ,{field: 'parentName', title: '上级名称', sort: true} 
	      ,{field: 'sortNo', title: '排序码', sort: true} 
	      ,{field: 'areaCode', title: '区域编码', sort: true}
	      ,{field: 'operator', title: '操作人', sort: true} 
	      ,{field: 'addTime', title: '添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
	      ,{field: 'updateTime', title:'修改时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
	      ,{field: 'leaderName', title: '领导人', sort: true}
	      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
	    ]]
	  });
	   var $ = layui.$, active = {
			
			    reload: function(){
			   //执行重载
			      table.reload('table_dept', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	deptName: $("#queryDeptName").val(),
			        	deptTypeId:$("#deptTypeId").val()
			        	
			        }
			      });
			    }
			  };
			  
 	//监听工具条
	  table.on('tool(table_dept)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var deptId = data.id;
	    var deptName=data.name
	    if(layEvent === 'del'){ //删除
	      layer.confirm('确定要删除该部门吗？', function(index){
	    	  $.ajax({
	    		  type:"post",
	    		  url:"delDept",
	    		  dataType:"json",
	    		  data:{deptId:deptId},
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
	    	var title = '部门编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">部门管理：【'+data.name+'】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"deptEditFrame"
		        ,title: title
		        ,area: ['700px', '400px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'deptDetailPage?deptId='+deptId
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	    }else if(layEvent === 'acrossStorePermission'){
	    	var title = '跨店权限页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">部门管理：【'+data.name+'】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"deptAcrossStorePermissionFrame"
		        ,title: title
		        ,area: ['700px', '500px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterAcrossStorePermission?deptId='+deptId
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      });
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