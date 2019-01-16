<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<title>分成配置管理</title>
</head>
<body>
	<%@include file="/common/common.jsp" %>
	<!-- 查询模块定义 -->
	<div class="toolBar">
		配置名称：
	  	<div class="layui-inline">
	  		<div class="layui-input-inline">
				<input type="text" class="layui-input" name="configName" placeholder="请输入分成配置名称" id="configName" autocomplete="off">
			</div>
		</div>
		所在城市
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="hidden" id="codeHid">
				<input type="text" class="layui-input" id="cityId" name="cityName" placeholder="请选择城市" autocomplete="off">
			</div>
		</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.divideAdd!='' && onlyBtnMenus.divideAdd!=null}">
	  		<button class="layui-btn layui-btn-normal" onclick="divideinto.add()">新增</button>
	  	</c:if>
	</div>
	<!-- 放置配置数据显示表格对象 -->
	<table class="layui-table" id="t_divide_into_conf" lay-filter="t_divide_into_conf"></table>

	<script type="text/html" id="operationBar">
		{{# if(d.confAdd !='' && d.confAdd!=null){ }}
 			 <a class="layui-btn layui-btn-xs" name="confAdd" lay-event="confAdd">分成配置</a>
		{{# } }}
		{{# if(d.edit !='' && d.edit!=null){ }}
  			<a class="layui-btn layui-btn-xs layui-btn-normal" name="confModify" lay-event="confModify">编辑</a>
		{{# } }}
		{{# if(d.del !='' && d.del!=null){ }}
 			 <a class="layui-btn layui-btn-danger layui-btn-xs" name="confDel" lay-event="confDel">删除</a>
		{{# } }}
	</script>
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
	<script type="text/javascript">
		var areaList = JSON.parse('${AreaListJson}');
		//适用城市控件
		var a= new AreaObject(areaList,function(code,name){
			$("#cityId").val(name);
			$("#codeHid").val(code);
		},2,$("#cityId"));
		$("#cityId").on("change",function(){
			if($(this).val()==""){
				$("#codeHid").val("");
			}
		});
		
		divideinto = {
			add : function() {
				var addOper = {
			        type: 2
			        ,id:"divideIntoAddIFrame"
			        ,title: '分成配置新增页面'
			        ,area: ['600px', '240px']
			      	,shade: 0.5
			        ,resize: false
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'divideIntoAddOrUpdatePage'
			        ,zIndex: layer.zIndex 
			        ,success: function(layero){
			          layer.setTop(layero); 
			        }
			    };
				common.windowLayerOpen(addOper);
			}
		}
		
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem : '#t_divide_into_conf'
				,height:'full-100'
				,url  : 'queryAllDivideIntoConfigInfo'
				,method : 'post'
				,page : true 	//开启分页
				,cols : [[ 		//表格列定义
				{
					field : 'id'
					,title : 'ID'
					,width : 80
					,sort  : true
					,fixed : 'left'
				}, {
					field : 'configName'
					,title : '配置名称'
					,sort  : true
				}, {
					field : 'status'
					,title : '配置状态'
					,sort  : true
				},  {
					field : 'cityName'
					,title : '所在城市'
					,sort  : true
				},{
					field : 'operator'
					,title : '创建用户'
					,sort  : true
				}, {
					field : 'addTime'
					,title : '新增时间'
					,sort  : false
					,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
				}, {
					field : 'dealTypeName'
					,title : '处理类型'
					,sort  : true
				}, {
					field : 'configTypeName'
					,title : '配置类型'
					,sort  : true
				}, {
					fixed: 'right'
					, title: '操作区域'
					, width: 300
					, align:'center'
					, toolbar: '#operationBar'
				}]]
			});

			var $ = layui.$, active = {
				reload : function() {
					//执行重载
					table.reload('t_divide_into_conf', {
						page : {
							//重新从第 1 页开始
							curr : 1
						},
						where : {
							configName : $("#configName").val(),
							codeHid: $("#codeHid").val()
						}
					});
				}
			};

			// 监听工具条
			// 注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			table.on('tool(t_divide_into_conf)', function(obj) {
				//获得当前行数据
				var data = obj.data;
				//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var layEvent = obj.event; 
				//获得当前行 tr的DOM对象
				var tr = obj.tr; 
				var configId = data.id;
				//删除事件
				if (layEvent === 'confDel') {
					layer.confirm('确定要删除该配置信息吗？', function(index) {
						$.ajax({
							type : "post",
							url  : "deleteDivideIntoConfigInfo",
							dataType : "json",
							data : {
								configId : configId
							},
							success : function(data) {
								if (data.success) {
									layer.msg(data.msg);
				    				obj.del();
								} else {
									layer.alert(data.msg);
								}
							},
							error : function(a, b, c) {
								layer.alert("操作异常，请稍后重试");
							}
						});
						layer.close(index);
					});
				} else if (layEvent === 'confModify') { //编辑事件
					var modify ={
				        type: 2
				        ,id: "divideIntoUpdateIFrame"
				        ,title: '配置编辑页面 '
				        ,area: ['600px','240px']
				      	,shade: 0.5
				        ,resize: false
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'divideIntoAddOrUpdatePage?configId='+configId
				        ,zIndex: layer.zIndex
				        ,success: function(layero,zIndex){
				        	layer.setTop(layero);
				        }
				    };
					common.windowLayerOpen(modify);
				} else if( layEvent == 'confAdd'){
					var tilte = '<span style="font-size=16px;color: #1E9FFF;">【'+ data.configName + '】分成配置</span>';
					var addConf ={
						type: 2
				        ,id: "divConfAddIFrame"
				        ,title: tilte
				        ,area: ['600px','700px']
				      	,shade: 0.5
				        ,resize: false
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'confAddOrEditPage?configId='+configId
				        ,zIndex: layer.zIndex
				        ,success: function(layero){
				          layer.setTop(layero);
				        }
					};
					common.windowLayerOpen(addConf);
				}
			});
			//查询事件
			$('#searchBtn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
</body>
</html>