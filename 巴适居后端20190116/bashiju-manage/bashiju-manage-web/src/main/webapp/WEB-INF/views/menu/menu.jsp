<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-block">
					<input type="text" name="menuName" id="menuName" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">菜单类型</label>
				<div class="layui-input-block">
					<select name="functionType" id="functionType">
						<option value="">-请选择-</option>
						<c:forEach var="functionType" items="${MenuFunctionTypeEnum}">
							<option value="${functionType.key}">${functionType.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">菜单等级</label>
				<div class="layui-input-block">
					<select name="level" id="level">
						<option value="">-请选择-</option>
						<c:forEach var="level" items="${MenuLevelEnum}">
							<option value="${level.key}">${level.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
			  	</c:if>
			  	<c:if test="${onlyBtnMenus.addMenu!='' && onlyBtnMenus.addMenu!=null}">
			  		<button class="layui-btn" name="addMenu" onclick="menu.add()">新增</button>
			  	</c:if>
		  	</div>
		</div>
	</div>
	<table class="layui-table" id="table_menu" lay-filter="table_menu"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
{{# if(d.menuEdit !='' && d.menuEdit!=null){ }}
  <a class="layui-btn layui-btn-xs" name="menuEdit" lay-event="edit">编辑</a>
{{# } }}
{{# if(d.combinationEdit !='' && d.combinationEdit!=null){ }}
	<a class="layui-btn layui-btn-xs" name="combinationEdit" lay-event="combination">数据组合</a>
{{# } }}
{{# if(d.menuDel !='' && d.menuDel!=null){ }}
  <a class="layui-btn layui-btn-danger layui-btn-xs" name="menuDel" lay-event="del">删除</a>
{{# } }}
</script>
<script>

menu = {
	add:function(){
		var title = '菜单新增页面';
		var option = {
		        type: 2 //此处以iframe举例
		        ,id:"menuPermissionIFrame"
		        ,title: title
		        ,area: ['700px', '400px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'menuDetailPage'
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
// 				,end: function(index, layero){ 
// 					layer.load();
// 	    			location.reload();
// 				}
		      };
		common.windowLayerOpen(option);
	}
}

layui.use('table', function(){
  var table = layui.table;
  
  layui.laytpl={
		 //转换菜单等级描述
		toLevelStr : function(level){
		  var dd = ${MenuLevelEnumJson};
			if('${MenuLevelEnumJson}'!='')
				return dd[level];
			else
				return level;
		}
  		//转换功能类型描述
  		,toFunctionTypeStr : function(functionType){
  			var dd = ${MenuFunctionTypeEnumJson};
			if('${MenuFunctionTypeEnumJson}'!='')
				return dd[functionType];
			else
				return functionType;
  		}
		,toDataPerFlagStr : function(dataPerFlag){
			if(dataPerFlag=="1")
				return "是";
			else
				return "否";
		}
  }
  
  table.render({
    elem: '#table_menu'
    ,height:'full-100'
    ,url: 'getMemuData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
      ,{field: 'name', title: '菜单名称',width: "11%", sort: true}
      ,{field: 'parentName', title: '上级菜单',width: "11%", sort: true}
      ,{field: 'level', title: '菜单等级',width: "9%", sort: true,templet: '<div>{{ layui.laytpl.toLevelStr(d.level) }}</div>'}
      ,{field: 'functionType', title: '菜单类型',width: "9%", sort: true,templet: '<div>{{ layui.laytpl.toFunctionTypeStr(d.functionType) }}</div>'}
      ,{field: 'url', title: '菜单路径',width: "21%"}
      ,{field: 'btnName', title: '按钮名称',width: "10%"}
      ,{field: 'dataPerFlag', title: '数据权限标识',width: "6%", sort: true,templet: '<div>{{ layui.laytpl.toDataPerFlagStr(d.dataPerFlag) }}</div>'}
      ,{field: 'sortNo', title: '排序码',width: "6%", sort: true}
      ,{ title: '操作',width: "14%", align:'center', toolbar: '#operationBar'}
    ]]
  	,done: function(res, curr, count){
  		$("[data-field='id']").css('display','none');
  	}
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var menuName = $("#menuName").val();
		var functionType = $("#functionType").val();
		var level = $("#level").val();
     //执行重载
      table.reload('table_menu', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	menuName: menuName,
        	functionType: functionType,
        	level: level
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_menu)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var menuId = data.id;
    if(layEvent === 'del'){ //删除
      layer.confirm('确定要删除该菜单吗？', function(index){
    	  $.ajax({
    		  type:"post",
    		  url:"delMenu",
    		  dataType:"json",
    		  data:{menuId:menuId},
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
    	var title = '菜单编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">菜单名称：【'+data.name+'】</span>';
    	var option = {
    	        type: 2 //此处以iframe举例
    	        ,id:"menuPermissionIFrame"
    	        ,title: title
    	        ,area: ['780px', '400px']
    	        ,shade: 0.5
    	        ,resize:false//不允许拉伸
    	        ,maxmin: false
    	        ,offset: 'auto' 
    	        ,content: 'menuDetailPage?menuId='+menuId
    	        ,zIndex: layer.zIndex //重点1
    	        ,success: function(layero){
    	          layer.setTop(layero); //重点2
    	        }
//     			,end: function(index, layero){ 
//     				layer.load();
//         			location.reload();
//     			}
    	      };
    	common.windowLayerOpen(option);
// 		window.layer.open();
    } else if(layEvent === 'combination'){ //数据权限组合编辑
    	var title = '数据权限条件组合编辑页面 <span style="margin-left: 20%;color: orange;font-size: 18px;">菜单名称：【'+data.name+'】</span>';
    	var option = {
    	        type: 2 //此处以iframe举例
    	        ,id:"combinationIFrame"
    	        ,title: title
    	        ,area: ['1000px', '460px']
    	        ,shade: 0.5
    	        ,resize:false//不允许拉伸
    	        ,maxmin: false
    	        ,offset: 'auto' 
    	        ,content: '/manage/combination/combinationPage?menuId='+menuId
    	        ,zIndex: layer.zIndex //重点1
    	        ,success: function(layero){
    	          layer.setTop(layero); //重点2
    	        }
//     			,end: function(index, layero){ 
//     				layer.load();
//         			location.reload();
//     			}
    	      };
    	common.windowLayerOpen(option);
// 		window.layer.open();
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