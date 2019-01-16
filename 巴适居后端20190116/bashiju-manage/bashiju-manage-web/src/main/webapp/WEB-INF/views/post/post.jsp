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
		职位名称：
	  	<div class="layui-inline">
	    	<input class="layui-input" name="queryPostName" placeholder="请输入职位名称" id="queryPostName" autocomplete="off">
	  	</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.postAdd!='' && onlyBtnMenus.postAdd!=null}">
	  		<button class="layui-btn"  type="button" onclick="post.add(this)" name="postAdd">新增</button>
	  	</c:if>
	</div>
<table class="layui-table" id="table_post" lay-filter="table_post"></table>

<!--编辑的窗体-->
	 	<form class="toolBar" id="post" style="display:none;margin-top: 20px;" >
	 		<div class="layui-form-item">
	 			<label class="layui-form-label"><span style="color:red;">*</span>职位名称</label>
			    <div class="layui-input-inline">
			    	<input class="layui-input" name="name" placeholder="请输入部门名称" id="postName" autocomplete="off">
			    </div>
			  </div>
		</form>	
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.postEdit !='' && d.postEdit !=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="postEdit">编辑</a>
  {{# } }}
  {{# if(d.postDel !='' && d.postDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="postDel">删除</a>
  {{# } }}	
</script>
<script type="text/javascript">
//添加
var post={
		add:function(){
			$("#postName").val("");
		var index=layer.open({
		    	 title:'新增职位',
				  type: 1,
				  skin: 'layui-layer-demo', //样式类名
				  area: ['350px', '180px'],
				  content: $('#post'),
				  btn:['确定','取消'],
				  yes: function(index){
	                $.ajax({
	  	    		  type:"post",
	  	    		  url:"saveOrUpdatePost",
	  	    		  dataType:"json",
	  	    		  data:{"postName":$("#postName").val()},
	  	    		  success:function(data){
	  	    			
	  	    			  if(data.success){
		 					layer.msg(data.msg);
		 					layer.closeAll();
		  	    			location.reload()
	  	    				  
	  	    			  }else{
	  	    				  layer.alert(data.msg,{time:2000});
	  	    			  }
	  	    		  },
	  	    		  error:function(a,b,c){
	  	    			  layer.alert("操作异常，请稍后重试");
	  	    		  }
	  	    	  });
	              },
	              cancel: function(){
	                  //右上角关闭回调
	              }
				}); 
		}
}

layui.use('table', function(){
	  var table = layui.table;
	  //第一个实例
	  table.render({
	    elem: '#table_post'
	    ,method:"post"
//	     ,height:'full-200'
	    ,url: 'getPost'
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
	      ,{field: 'name', title: '角色名称', sort: true}
	      ,{field: 'addTime', title: '新增时间', sort: true ,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
	      ,{field: 'updateTime', title: '跟新时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
	      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
	    ]]
	  });
	   var $ = layui.$, active = {
			    reload: function(){
					
			     //执行重载
			      table.reload('table_post', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	postName: $("#queryPostName").val()
			        }
			      });
			    }
			  };
			  
 	//监听工具条
	  table.on('tool(table_post)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var postId = data.id;
	    var postName=data.name
	    if(layEvent === 'del'){ //删除
	      layer.confirm('确定要删除该职位吗？', function(index){
	    	  $.ajax({
	    		  type:"post",
	    		  url:"deletePost",
	    		  dataType:"json",
	    		  data:{postId:postId},
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
	    	//给编辑框赋值
	    	$("#postName").val(postName)
	    	//页面层
	     	layer.open({
	    	 title:'编辑职位',
			  type: 1,
			  skin: 'layui-layer-demo', //样式类名
			  area: ['350px', '180px'],
			  content: $('#post'),
			  btn:['确定','取消'],
			  yes: function(index){
                var inputName=$("#postName").val();
                $.ajax({
  	    		  type:"post",
  	    		  url:"saveOrUpdatePost",
  	    		  dataType:"json",
  	    		  data:{"postName":inputName,"postId":postId},
	    		  success:function(data){
  	    			  if(data.success){
  	    				  layer.alert(data.msg, {icon: 1,time:1000});
  	    				  //刷新页面
  	    				  location.reload()
  	    			  }else{
  	    				  layer.alert(data.msg);
  	    			  }
  	    		  },
  	    		  error:function(a,b,c){
  	    			  layer.alert("操作异常，请稍后重试");
  	    		  }
  	    	  });
              },
              cancel: function(){
                  //右上角关闭回调
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