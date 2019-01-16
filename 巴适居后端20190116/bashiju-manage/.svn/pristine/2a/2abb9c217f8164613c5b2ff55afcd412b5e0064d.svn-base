<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>过户流程管理</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
	</head>
	<body>
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
		<!-- 查询模块定义 -->
		<div class="toolBar">
			方案名称：
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input class="layui-input" name="transferProcName" placeholder="请输入过户方案名称" id="transferProcName" autocomplete="off">
				</div>
			</div>
			所属城市：
		  	<div class="layui-inline">
	  			<div class="layui-input-inline"> 
					<input type="hidden" id="areaCode">
					<input class="layui-input" name="areaName" placeholder="请选择城市" id="areaName" autocomplete="off">
				</div>
			</div>
			
		  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
		  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.transferProcAdd!='' && onlyBtnMenus.transferProcAdd!=null}">
		  		<button class="layui-btn layui-btn-normal" onclick="transferProc.add()">新增</button>
		  	</c:if>
		</div>
		<!-- 放置配置数据显示表格对象 -->
		<table class="layui-table" id="t_transfer_proc_mgr" lay-filter="t_transfer_proc_mgr"></table>
	
		<script type="text/html" id="operationBar">
			{{# if(d.procModify !='' && d.procModify!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="processModify" lay-event="procModify">编辑</a>
			{{# } }}
			{{# if(d.hsOwnerDetail !='' && d.hsOwnerDetail!=null){ }}
 				 <a class="layui-btn layui-btn-xs" name="hsOwnerDetail" lay-event="hsOwnerDetail">业主材料明细</a>
			{{# } }}
			{{# if(d.cusDetail !='' && d.cusDetail!=null){ }}
				<a class="layui-btn layui-btn-xs layui-btn-normal" name="cusDetail" lay-event="cusDetail">客户材料明细</a>
			{{# } }}
			{{# if(d.procConf !='' && d.procConf!=null){ }}
				<a class="layui-btn layui-btn-xs" name="procConf" lay-event="procConf">过户流程设置</a>
			{{# } }}
			{{# if(d.procDel !='' && d.procDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="procDel" lay-event="procDel">删除</a>
			{{# } }}
		</script>
	
		<script>
			var areaList = JSON.parse('${AreaListJson}');
			//适用城市控件
			var a= new AreaObject(areaList,function(code,name){
				$("#areaName").val(name);
				$("#areaCode").val(code);
			},2,$("#areaName"));
			$("#areaName").on("change",function(){
				if($(this).val()==""){
					$("#areaCode").val("");
				}
			});
			transferProc = {
				add : function() {
					var addOper = {
				        type: 2
				        ,id:"transferProcIFrame"
				        ,title: '过户流程新增页面'
				        ,area: ['580px', '230px']
				      	,shade: 0.5
				        ,resize: false
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'getAddOrModifyModel'
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
					elem : '#t_transfer_proc_mgr'
					,height:'full-100'
					,url  : 'queryTransferProcessList'
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
						field : 'programmeName'
						,title : '方案名称'
						,sort  : true
					}, {
						field : 'operator'
						,title : '创建用户'
						,sort  : true
					},{
						field : 'addTime'
						,title : '新增时间'
						,sort  : false
						,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
					}, {
						field : 'updateOperator'
						,title : '更新用户'
						,sort  : true
					}, {
						field : 'areaName'
						,title : '所属城市'
						,sort  : true
					}, {
						fixed: 'right'
						, title: '操作区域'
						, width: 400
						, align:'center'
						, toolbar: '#operationBar'
					}]]
				});
				var $ = layui.$, active = {
					reload : function() {
						var transferProcName = $("#transferProcName").val();
						//执行重载
						table.reload('t_transfer_proc_mgr', {
							page : {
								//重新从第 1 页开始
								curr : 1
							},
							where : {
								transferProcName : transferProcName,
								areaCode : $("#areaCode").val()
							}
						});
					}
				};
	
				// 监听工具条
				// 注：tool是工具条事件名，t_transfer_proc_mgr是table原始容器的属性 lay-filter="对应的值"
				table.on('tool(t_transfer_proc_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var procId = data.id;
					//删除事件
					if (layEvent === 'procDel') {
						layer.confirm('确定要删除该配置信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "delTransferProc",
								dataType : "json",
								data : {
									procId : procId
								},
								success : function(data) {
									if (data.success) {
										layer.msg(data.msg);
					    				obj.del();
									} else {
										layer.msg(data.msg);
									}
								},
								error : function(a, b, c) {
									layer.msg("操作异常，请稍后重试");
								}
							});
						});
					} else if (layEvent === 'procModify') { //编辑事件
						var modify ={
					        type: 2
					        ,id: "procModifyIFrame"
					        ,title: '配置编辑页面 '
					        ,area: ['580px', '230px']
					      	,shade: 0.5
					        ,resize: false
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'getAddOrModifyModel?procId='+procId
					        ,zIndex: layer.zIndex
					        ,success: function(layero,zIndex){
					        	layer.setTop(layero);
					        }
					    };
						common.windowLayerOpen(modify);
					} else if( layEvent == 'hsOwnerDetail'){//业主明细材料
						if(data.isValid == "0"){
							layer.msg("主配置信息不可用，不可设置!");
							return;
						}
						var tilte = '<span style="font-size=20px;color:#1E9FFF;font-weight:700">业主明细材料编辑</span>';
						var hsOwnerDetail ={
							type: 2
					        ,id: "hsOwnerDetailIFrame"
					        ,title: tilte
					        ,area: ['610px','600px']
					      	,shade: 0.5
					        ,resize: false
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'getDetailMgrModel?procId='+procId+'&confType=1'
					        ,zIndex: layer.zIndex
					        ,success: function(layero){
					          layer.setTop(layero);
					        }
						};
						common.windowLayerOpen(hsOwnerDetail);
					}else if( layEvent == 'cusDetail'){//客户明细材料
						if(data.isValid == "0"){
							layer.msg("主配置信息不可用，不可设置!");
							return;
						}
						var tilte = '<span style="font-size=20px;color:#1E9FFF;font-weight:700">客户明细材料</span>';
						var cusDetail={
							type: 2
					        ,id: "cusDetailIFrame"
					        ,title: tilte
					        ,area: ['610px','600px']
					      	,shade: 0.5
					        ,resize: false
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'getDetailMgrModel?&procId='+procId+'&confType=2'
					        ,zIndex: layer.zIndex
					        ,success: function(layero){
					          layer.setTop(layero);
					        }
						};
						common.windowLayerOpen(cusDetail);
					}else if( layEvent == 'procConf'){//过户流程设置
						if(data.isValid == "0"){
							layer.msg("主配置信息不可用，不可设置!");
							return;
						}
						var tilte = '<span style="font-size=20px;color: #1E9FFF;font-weight:700">过户流程设置</span>';
						var procConf={
							type: 2
					        ,id: "procConfIFrame"
					        ,title: tilte
					        ,area: ['320px','500px']
					      	,shade: 0.5
					        ,resize: false
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'getTransProcSettingModel?&procId='+procId+'&confType=3'
					        ,zIndex: layer.zIndex
					        ,success: function(layero){
					          layer.setTop(layero);
					        }
						};
						common.windowLayerOpen(procConf);
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