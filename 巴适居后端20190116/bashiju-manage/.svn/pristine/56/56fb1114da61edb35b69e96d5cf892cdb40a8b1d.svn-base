<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户注册审核管理</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	</head>
	<body>
		<!-- 查询模块定义 -->
		<div class="toolBar layui-form" id="searchArea">
			选择部门：
		  	<div class="layui-inline">
	  			<div class="layui-input-inline" style="width:120px;"> 
					<input type="hidden" id="deptId" name="deptId">
					<input class="layui-input" id="deptName" name="deptName" readonly="readonly" reaplaceholder="请选择部门" autocomplete="off">
				</div>
			</div>
			主机类型
			<div class="layui-inline">
				<select id="userType" name="userType">
					<option value="">--请选择--</option>
					<option value="0">独立主机</option>
					<option value="1">主机</option>
					<option value="2">分机</option>
				</select>
			</div>
			审核状态
			<div class="layui-inline">
				<select id="checkStatus" name="checkStatus">
					<option value="">--请选择--</option>
					<option value="0">未审核</option>
					<option value="1">已审核</option>
				</select>
			</div>
			电脑位置：
		  	<div class="layui-inline">
				<input class="layui-input" name="computerInfo" placeholder="请输入电脑位置" id="computerInfo" autocomplete="off">
			</div>
			管理者：
		  	<div class="layui-inline">
				<input class="layui-input" name="manageName" placeholder="请输入管理者信息" id="manageName" autocomplete="off">
			</div>
			
		  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
		  		<button id="searchBtn" name="searchBtn" class="layui-btn" data-type="reload">查询</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.clearBtn!='' && onlyBtnMenus.clearBtn!=null}">
		  		<button class="layui-btn layui-btn-normal" onclick="clearData()">清空</button>
		  	</c:if>
		</div>
		
		<table class="layui-table" id="t_user_check_info" lay-filter="t_user_check_info"></table>
		
		<!--  用户信息审核弹窗代码 -->
		<form id="userCheckMgrForm" name="userCheckMgrForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" value="" name="id">
			<div class="layui-form-item">
				<label class="layui-form-label">管理者</label>
				<div class="layui-input-inline">
					<input type="hidden" id="id" name="id">
					<input type="hidden" id="operatorId" name="operatorId">
					<input type="hidden" id="manageId" name="manageId">
					<input id="userMgrName" name="userMgrName" type="text"
						class="layui-input" placeholder="请输选择管理者" readonly="readonly" autocomplete="off" style="width:240px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>主机类型</label>
				<div class="layui-input-inline" style="width:240px;">
					<select id="userTypeId" name="userTypeId" required lay-verify="required">
						<option value="">--请选择--</option>
						<option value="1">独立主机</option>
						<option value="2">主机</option>
						<option value="3">分机</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电脑位置</label>
				<div class="layui-input-inline" style="width:240px;">
					<input class="layui-input" id="computerInfo" name="computerInfo" placeholder="请输入电脑位置" autocomplete="off"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				 <div class="layui-input-block">
				 	<button id="saveBtn" class="layui-btn" lay-submit="" lay-filter="userCheckMgrForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		
		<script type="text/html" id="operationBar">
			{{# if(d.userCheckBtn !='' && d.userCheckBtn != null){ }}
  				<a class="layui-btn layui-btn-xs" name="userCheckBtn" lay-event="userCheckBtn">审核</a>
			{{# } }}
			{{# if(d.checkInfoDel !='' && d.checkInfoDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="checkInfoDel" lay-event="checkInfoDel">删除</a>
			{{# } }}
		</script>
		
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script>
			var form;
			var table;
			//部门选择控件
			var deptInfo = JSON.parse('${deptInfo}');
			var referInfo = JSON.parse('${referInfo}');
// 			var deptMaxLevel = '${deptMaxLevel}';
// 			var referMaxLevel = '${referMaxLevel}';
			var dept = new MultiSelection(deptInfo,function(code,name){
				$("#deptName").val(name);
				$("#deptId").val(code);
				dept.hidden();
			},null,$("#deptName"),true);
			$("#deptName").on("change",function(){
				if($(this).val()==""){
					$("#deptId").val("");
				}
			});
			//介绍人控件
			var refer = new MultiSelection(referInfo,function(code,name,level){
// 				if(level == referMaxLevel){
					$("#userMgrName").val(name);
					$("#manageId").val(code);
					refer.hidden();
// 				}
			},null,$("#userMgrName"),true);
			$("#userMgrName").on("change",function(){
				if($(this).val()==""){
					$("#manageId").val("");
				}
			});
			
			layui.use(['jquery','table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				//第一个实例
				table.render({
					id: 't_user_check_info'
					,elem : '#t_user_check_info'
					,url: 'queryUserRegisterAllInfoPage'
					,height: 'full-100'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'id'
						,title : '编号'
						,width : 80
						,sort  : true
						,fixed : 'left' 
					}, {
						field : 'userTypeName'
						,title : '主机类别'
						,sort  : true
					}, {
						field : 'addTime'
						,title : '注册时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'

					},{
						field : 'computerInfo'
						,title : '电脑位置'
						,sort  : true
					},{
						field : 'checkName'
						,title : '审核状态'
						,sort  : true
					},{
						field : 'deptName'
						,title : '所属部门'
						,sort  : true
					},{
						field : 'manageName'
						,title : '管理者'
						,sort  : true
					}, {
						field : 'checkTime'
						,title : '审核时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.checkTime,"yyyy-MM-dd HH:mm:ss") }}</div>'

					}, {
						field : 'operator'
						,title : '创建用户'
						,sort  : true
					},{
						field : 'ip'
						,title : '电脑IP'
						,sort  : true
					}, {
						field : 'lastLoginSuccessTime'
						,title : '最后登陆时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.lastLoginSuccessTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
 
					},{
						fixed: 'right'
						, title: '操作区域'
						, width: 150
						, align:'center'
						, toolbar: '#operationBar'
					}]]
				});
				// 注：tool是工具条事件名
				table.on('tool(t_user_check_info)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var id = data.id;
					//删除事件
					if (layEvent === 'checkInfoDel') {
						layer.confirm('您确定要删除吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "deleteUserRegisterInfo?id=" + id,
								success : function(data) {
									if (data.success) {
										layer.msg(data.msg);
					    				obj.del();
									} else {
										layer.alert(data.msg);
									}
								},
								error : function(a, b, c) {
									layer.msg("操作异常，请稍后重试");
								}
							});
						});
					} else if (layEvent === 'userCheckBtn') { //编辑事件
						checkedUserInfo('审核用户信息',data);
					} 
				});
				
				var $ = layui.$, active = {
					reload : function() {
						//执行重载
						table.reload('t_user_check_info', {
							page : {
								//重新从第 1 页开始
								curr : 1
							},
							where : {
								deptId : $("#deptId").val(),
								userType: $("#userType").val(),
								checkStatus: $("#checkStatus").val(),
								computerInfo: $("#computerInfo").val(),
								manageName: $("#manageName").val()
							}
						});
					}
				};
				
				//查询事件
				$('#searchBtn').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
			});
			
			/**
			 * 审核用户信息
			 */
			function checkedUserInfo(title,data){
				//将输入框值置为空
				$("#userCheckMgrForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
				$("#userCheckMgrForm select").each(function (index, domEle) { 
					$(this).val("");
					if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
						$(this).val('');
				 	}
				});
			 	//显示隐藏表单
			 	$('#userCheckMgrForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: title
				  	,type: 1
				  	,area:['420px','280px']
					,resize: false
				  	,content: $("#userCheckMgrForm")
				  	,end: function(index, layero){ 
						$('#userCheckMgrForm').addClass("layui-hide");
					}
				});
				$("#id").val(data.id);
				$("#operatorId").val(data.operatorId);
				$("#manageId").val(data.manageId);
				$("#userMgrName").val(data.manageName);
				//表单提交
				form.on('submit(userCheckMgrForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateUserRegisterInfo'
						,dataType: 'json'
						,data:{
							jsonData: JSON.stringify(data.field)
						},
						success: function(data){
							if(data.success){
								layer.alert(data.msg,function(index){
					    			layer.closeAll();
					    			location.reload();
			    				});
							}else{
								layer.msg(data.msg);
							}
						}
					});
					return false;
				});
			}
			//清空按钮事件
			function clearData(){
				$("#searchArea input").each(function (index, domEle) { 
					$(this).val("");
						if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
							$(this).val("0");
					 	}			
					});
				$("#searchArea select").each(function (index, domEle) { 
					$(this).val("");
					if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
						$(this).val('');
				 	}
				});
			}
		</script>
	</body>
</html>