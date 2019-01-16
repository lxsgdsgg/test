<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
 <div class="toolBar">
			部门类型名称：
			<div class="layui-inline">
	    		<input class="layui-input" name="name" id="deptTypeName" placeholder="请输入关键字"  autocomplete="off">
		  	</div>
		  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
	  			<button class="layui-btn" layer-submit data-type="reload" id="searchBtn">查询</button>
	  		</c:if>
	  	 	<c:if test="${onlyBtnMenus.deptTypeAdd!='' && onlyBtnMenus.deptTypeAdd!=null}">
	  			<button class="layui-btn " type="button" name="deptTypeAdd" onclick="deptType.add()">新增</button>
	  		</c:if>
		  	
	</div>
 
<table class="layui-table" id="table_deptType" lay-filter="table_deptType"></table>
<!-- 加载js文件 -->
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.deptTypeEdit !='' && d.deptTypeEdit !=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="deptTypeEdit">编辑</a>
  {{# } }}
  {{# if(d.deptTypeDel !='' && d.deptTypeDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="deptTypeDel">删除</a>
  {{# } }}	
</script>
<script type="text/javascript">
deptType={
		add:function(){
			var title = '部门类型新增页面';
			var option = {
			        type: 2 //此处以iframe举例
			        ,id:"deptTypeEditIFrame"
			        ,title: title
			        ,area: ['400px', '300px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'deptTypeDetail'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
// 					,end: function(index, layero){ 
// 						layer.load();
// 		    			location.reload();
// 					}
			      };
			common.windowLayerOpen(option);
		}	
}


layui.use('table', function(){	
	var table = layui.table;
	  table.render({
		    elem: '#table_deptType'
		    ,method:"post"
		    ,url: 'deptTypeListPage'
		   /*  ,page: true //开启分页 */
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'name', title: '部门类型名称', sort: true}
		      ,{field: 'parentName', title: '上级名称', sort: true} 
		      ,{field: 'operator', title: '操作人', sort: true} 
		      ,{field: 'addTime', title: '添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'updateTime', title:'修改时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
		    ]]
		  });
	   var $ = layui.$, active = {
				
			    reload: function(){
			   //执行重载
			      table.reload('table_deptType', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	deptTypeName: $("#deptTypeName").val(),
			        }
			      });
			    }
			  };
	 //监听工具条
	   table.on('tool(table_deptType)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	     var data = obj.data; //获得当前行数据
	     var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	     var tr = obj.tr; //获得当前行 tr 的DOM对象
	    	var deptTypeId = data.id;
	     if(layEvent === 'del'){ //删除
	       layer.confirm('确定要删除该部门类型吗？', function(index){
	     	  $.ajax({
	     		  type:"post",
	     		  url:"delDeptType",
	     		  dataType:"json",
	     		  data:{deptTypeId:deptTypeId},
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
	     	var title = '部门类型编辑页面 <span style="margin-left: 15%;color: orange;font-size: 15px;">部门管理：【'+data.name+'】</span>';
	     	//页面层
	     	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"deptEditFrame"
		        ,title: title
		        ,area: ['400px', '350px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'deptTypeDetail?deptTypeId='+deptTypeId
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
	   });
	 
	   $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
	
});	

</script> 
</body>
</html>