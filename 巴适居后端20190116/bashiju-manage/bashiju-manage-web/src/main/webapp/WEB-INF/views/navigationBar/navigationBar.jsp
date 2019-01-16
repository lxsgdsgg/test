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
	
	
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">导航名称</label>
				<div class="layui-input-block">
					<input type="text" id="name" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">导航类型</label>
				<div class="layui-input-block">
						<select  id="type">
							<option value="">请选择</option>
							<option value="0">头部导航栏</option>
							<option value="1">底部导航栏</option>
						</select>	
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">导航等级</label>
				<div class="layui-input-block">
					<select  id="level">
						<option value="">请选择</option>
						<option value="1">一级</option>
						<option value="2">二级</option>
					</select>	
				</div>
			</div>
			<div class="layui-inline">
			<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
			</c:if>
			<c:if test="${onlyBtnMenus.addBtn!=''&& onlyBtnMenus.addBtn!=null}"> 		
		  			<button class="layui-btn" name="addBtn"  type="button" id="add">新增</button>
		  	</c:if>		
		  	</div>
		</div>
	</div>

<table class="layui-table" id="table_navigationBar" lay-filter="table_navigationBar"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.table_navigationBarEdit !='' && d.table_navigationBarEdit!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="table_navigationBarEdit">编辑</a>
  {{# } }}
  {{# if(d.table_navigationBarDel !='' && d.table_navigationBarDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="table_navigationBarDel">删除</a>
  {{# } }}	
</script>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;
	  var $ = layui.$ //重点处
	  //新增
	  $(function(){
			$("#add").click(function(){
					var title = '导航条新增页面';
					var option = {
					        type: 2 //此处以iframe举例
					        ,id:"NavigationBarFrame"
					        ,title: title
					        ,area: ['500px', '350px']
					        ,shade: 0.5
					        ,resize:false//不允许拉伸
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'enterNavigationBarDetailPage'
					        ,zIndex: layer.zIndex //重点1
					        ,success: function(layero){
					          layer.setTop(layero); //重点2
					          parent.layer.closeAll();
					        }
					      };
					common.windowLayerOpen(option);
				})
			})
	  table.render({
		    elem: '#table_navigationBar'
		    ,method:"post"
		    ,url: 'queryNavigationBarData'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'navigateCode', title: '导航栏编号', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'type', title: '导航类型', sort: true,templet: '<div>{{ layui.laytpl.toTypeStr(d.type) }}</div>'}
		      ,{field: 'level', title: '导航等级', sort: true,templet: '<div>{{ layui.laytpl.toLevelStr(d.level) }}</div>'} 
		      ,{field: 'name', title: '导航名称', sort: true} 
		      ,{field: 'url', title: '导航地址', sort: true} 
		      ,{field: 'operator', title: '操作人', sort: true} 
		      ,{field: 'addTime', title: '添加时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 	
		      ,{field: 'updateTime', title: '更新时间', sort: true,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'} 
		      ,{fixed: 'right', title: '操作', width: 140, align:'center', toolbar: '#operationBar'}
		      ]]
		  });
	  
	  
	  layui.laytpl={
				 //转换类别描述
				toTypeStr : function(type){
					if(type==0)
						return '头部导航栏';
					else
						return '底部导航栏';
				},
				toLevelStr : function(level){
					if(level==1)
						return '一级'
					else
						return '二级';
				}
		  }
	  
	 	//监听工具条
	  table.on('tool(table_navigationBar)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var navigationBarId = data.id;
	    if(layEvent === 'del'){ //删除
	      layer.confirm('确定要删除该导航条吗？', function(index){
	    	  $.ajax({
	    		  type:"post",
	    		  url:"delNavigationBarById",
	    		  dataType:"json",
	    		  data:{navigationBarId:navigationBarId},
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
	    	var title = '导航条编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房产文章分类编辑页面：【】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"NavigationBarFrame"
		        ,title: title
		        ,area: ['500px', '350px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterNavigationBarDetailPage?navigationBarId='+navigationBarId
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	  	  }
	  });
	  
	  var $ = layui.$, active = {
			    reload: function(){
			     //执行重载
			      table.reload('table_navigationBar', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	name:$("#name").val(),
			        	level:$("#level").val(),
			        	type:$("#type").val()			        }
			      });
			    }
			  };
	  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
	  });
})
</script>

</body>
</html>