<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
	<title>客户评价留言详细</title>
</head>
<body>
	<div class="">
		<div class="layui-row">
			<input type="hidden" id="agentId" name="agentId">
			<div class="layui-col-md6">
				<div class="toolBarOne layui-form">
					留言类型：
				  	<div class="layui-inline" style="width:150px;">
						<select id="messageType" name="messageType">
							<option value="">--请选择--</option>
							<option value="0">求租</option>
							<option value="1">求购</option>
							<option value="2">出租</option>
							<option value="3">出售</option>
							<option value="4">其他</option>
						</select>
					</div>
					是否已读：
				  	<div class="layui-inline" style="width:150px;">
						<select id="isRead" name="isRead">
							<option value="">--请选择--</option>
							<option value="0">未读</option>
							<option value="1">已读</option>
						</select>
					</div>
					客户电话：
				  	<div class="layui-inline" style="width:150px;">
						<input id="mobile" name="mobile" type="number" placeholder="请输入" class="layui-input" autocomplete="off">
					</div>
					<c:if test="${onlyBtnMenus.searchMsgBtn != '' && onlyBtnMenus.searchMsgBtn != null}">
					  	<button class="layui-btn" name="searchMsgBtn" id="searchMsgBtn" onclick="searchLeavingMsgData()">查询</button>
				  	</c:if>
				</div>
				<!-- 展示留言表格 -->
				<div class="layui-field-box">
					<table class="layui-table" id="t_leaving_msg_table" lay-filter="t_leaving_msg_table"></table>
				</div>
			</div>
			<div class="layui-col-md6">
				<div class="toolBarTwo layui-form">
					审核状态：
				  	<div class="layui-inline">
						<select id="checkStatus">
							<option value="">--请选择--</option>
							<option value="0">未审核</option>
							<option value="1">已审核</option>
						</select>
					</div>
					客户名称：
				  	<div class="layui-inline">
						<input id="custName" name="custName" type="text" placeholder="请输入" class="layui-input" autocomplete="off">
					</div>
					<c:if test="${onlyBtnMenus.searchCommentBtn != '' && onlyBtnMenus.searchCommentBtn != null}">
					  	<button class="layui-btn" name="searchCommentBtn" id="searchCommentBtn" onclick="searchCommentsData()">查询</button>
				  	</c:if>
				</div>
				<!--留言表格对象 -->
				<div class="layui-field-box">
					<table class="layui-table" id="t_comments_table" lay-filter="t_comments_table"></table>
				</div>
			</div>
		</div>
	</div>
		
	<%@include file="/common/common.jsp" %>
	<script type="text/html" id="operationBarOne">
		{{# if(d.examineStatus == 0){}}
			{{# if(d.checkBtn !='' && d.checkBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="checkBtn" lay-event="checkBtn">审核</a>
			{{# } }}
		{{# } }}
		{{# if(d.delComment !='' && d.delComment!=null){ }}
  			<a class="layui-btn layui-btn-xs layui-btn-normal" name="delComment" lay-event="delComment">删除</a>
		{{# } }}
	</script>
	<script type="text/html" id="operationBarTwo">
		{{# if(d.isRead == 0){ }}
			{{# if(d.readBtn !='' && d.readBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="readBtn" lay-event="readBtn">标记已读</a>
			{{# } }}
		{{# } }}
		
		{{# if(d.delMsgBtn !='' && d.delMsgBtn!=null){ }}
  			<a class="layui-btn layui-btn-xs layui-btn-normal" name="delMsgBtn" lay-event="delMsgBtn">删除</a>
		{{# } }}
	</script>
	
	<script type="text/javascript">
		var table;
		var form;
		
		layui.use(['form','table'],function(){
			table = layui.table;
			form = layui.form;
			
			table.render({
				id: 't_comments_table'
				,elem: '#t_comments_table'
				,height: 'full-100'
				,url: 'queryAllCustomerComments?agentId='+1
				,method: 'post'
				,page: true
				,cols: [[
					{field : 'userId',width:80,title: '用户编号'}
					,{field : 'checkName',width:100,title: '审核状态'}
					,{field : 'agentId',width:80,title : '经纪人详情编号'}
					,{field : 'star',width:80,title: '星级'}
					,{field : 'custId',width:80,title: '客户编号'}
					,{field : 'custName',width:100,title : '客户名称'}
					,{field : 'labels',width:140,title : '经纪人标签'}
					,{field : 'secondLabels',width:100,title: '二级标签'}
					,{field : 'auditor',width:100,title: '审核人'}
					,{field : 'reason',width:150,title: '驳回原因'}
					,{fixed : 'right',title: '操作区域', width: 180,align:'center',toolbar: '#operationBarOne'}
				]]
			});
			
			table.render({
				id: 't_leaving_msg_table'
				,elem: '#t_leaving_msg_table'
				,height: 'full-100'
				,url: 'queryAllCustomerLeavingMsg?agentId='+1
				,method: 'post'
				,page: true
				,cols: [[
					 {field : 'isReadName',width:80,title: '是否已读'}
					,{field : 'userId',width:80,title: '用户编号'}
					,{field : 'agentId',width:80,title : '详情编号'}
					,{field : 'messageType',width:80,title: '留言类型'}
					,{field : 'realName',width:100,title: '真实姓名'}
					,{field : 'mobile',width:100,title : '电话号码'}
					,{field : 'content',width:180,title : '留言内容'}
					,{fixed : 'right',title: '操作区域', width: 180,align:'center',toolbar: '#operationBarTwo'}
				]]
			});
			
			table.on('tool(t_comments_table)', function(obj) {
				//获得当前行数据
				var data = obj.data;
				//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var layEvent = obj.event; 
				var id = data.id;
				var agentId = data.agentId;
				if (layEvent === 'delComment') { //删除客户评价
					layer.confirm('确定要删除此客户评价吗？', function(index) {
						$.ajax({
							type:'post'
							,url:'deleteCustmerComments?id=' + id
							,success: function(result){
								if(result.success){
									layer.msg(result.msg);
									obj.del();
								}else{
									layer.msg(result.msg);
								}
							},
							error: function(a,b,c){
								layer.msg('系统操作异常!');
							}
						});
					});
				}else if(layEvent === "checkBtn"){//审核客户评价
					$.ajax({
						type:'post'
						,url:'checkCustmerComments?id=' + id
						,success: function(result){
							if(result.success){
								layer.msg(result.msg);
								location.reload();
							}else{
								layer.msg(result.msg);
							}
						},
						error: function(a,b,c){
							layer.msg('系统操作异常!');
						}
					});
				}
			});
			   
			table.on('tool(t_leaving_msg_table)', function(obj) {
				//获得当前行数据
				var data = obj.data;
				//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var layEvent = obj.event; 
				var id = data.id;
				var agentId = data.agentId;
				if (layEvent === 'delMsgBtn') { //确认定金
					layer.confirm('确定要删除此留言记录吗？', function(index) {
						$.ajax({
							type:'post'
							,url:'deleteCustmerLeavingMsg?id=' + id
							,success: function(result){
								if(result.success){
									layer.msg(result.msg);
									obj.del();
								}else{
									layer.msg(result.msg);
								}
							},
							error: function(a,b,c){
								layer.msg('系统操作异常!');
							}
						});
					});
				}else if(layEvent === "readBtn"){//显示成交明细列表
					layer.confirm('确定要标记为已读吗？', function(index) {
						$.ajax({
							type:'post'
							,url:'markCustomerLeavingMsgReaded?id=' + id
							,success: function(result){
								if(result.success){
									layer.msg(result.msg);
									location.reload();
								}else{
									layer.msg(result.msg);
								}
							},
							error: function(a,b,c){
								layer.msg('系统操作异常!');
							}
						});
					});
				}
			});
		});
		
		/**
		 * 查询留言页面数据函数
		 */
		function searchLeavingMsgData() {
			var messageType = $("#messageType").val();
			var isRead = $("#isRead").val();
			var mobile = $("#mobile").val();
			var agentId = $("#agentId").val();
			//执行重载
			table.reload('t_leaving_msg_table', {
				page : {
					//重新从第 1 页开始
					curr : 1
				},
				where : {
					messageType : messageType,
					isRead : isRead,
					agentId : agentId,
					mobile : mobile
				}
			});
		}
		/**
		 * 查询怕评价页面数据函数
		 */
		function searchCommentsData() {
			var checkStatus = $("#checkStatus").val();
			var custName = $("#custName").val();
			var agentId = $("#agentId").val();
			//执行重载
			table.reload('t_comments_table', {
				page : {
					//重新从第 1 页开始
					curr : 1
				},
				where : {
					checkStatus : checkStatus,
					custName : custName,
					agentId : agentId
				}
			});
		}
	</script>
</body>
</html>