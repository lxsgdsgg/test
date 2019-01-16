<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户数据更新</title>
</head>
<body>
	<div class="layui-container">
		<form class="layui-form" id="userDataUpdateForm" style="margin-top:20px;" action="">
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input id="userAll" name="userAll" type="checkbox" lay-filter="userAll" lay-skin="primary" title="全选"/>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyIptStore" name="fyIptStore" class="layui-input" type="checkbox" lay-skin="primary" title="房源录入店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyResStore" name="fyResStore" class="layui-input" type="checkbox" lay-skin="primary" title="房源责任店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyIptStore" name="kyIptStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源录入店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyResStore" name="kyResStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源责任店">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyEntrustStore" name="fyEntrustStore" class="layui-input" type="checkbox" lay-skin="primary" title="房源委托店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyKeyStore" name="fyKeyStore" class="layui-input" type="checkbox" lay-skin="primary" title="房源钥匙店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyUkStore" name="kyUkStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源独家店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyPhotoStore" name="kyPhotoStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源图片店">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyTrankStore" name="fyTrankStore" class="layui-input" type="checkbox" lay-skin="primary" title="房源跟进店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyTrankStore" name="kyTrankStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源跟进店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kySeeStore" name="kySeeStore" class="layui-input" type="checkbox" lay-skin="primary" title="客源带看店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="kyDoneStore" name="kyDoneStore" class="layui-input" type="checkbox" lay-skin="primary" title="成交店">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline" style="width:155px;">
						<input id="curMonthAttendance" name="curMonthAttendance" class="layui-input" type="checkbox" lay-skin="primary" title="本月考勤">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="fyOpenQuotation" name="fyOpenQuotation" class="layui-input" type="checkbox" lay-skin="primary" title="房源开盘">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="allDayBillMove" name="allDayBillMove" class="layui-input" type="checkbox" lay-skin="primary" title="全部日报到当前店">
					</div>
					<div class="layui-input-inline" style="width:155px;">
						<input id="allAttendanceMove" name="allAttendanceMove" class="layui-input" type="checkbox" lay-skin="primary" title="全部考勤到当前店">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" >
			      <button class="layui-btn" id="saveBtn" lay-submit lay-filter="saveBtn">提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
		</form>
		<!--表格区域--展示操作记录 -->
		<table class="layui-table" id="t_user_data_update_log" data-type="reload" lay-filter="t_user_data_update_log"></table>
	</div>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript">
		layui.use(['table','form','layer'],function(){
			var form = layui.form;
			var table = layui.table;
			var $ = layui.$;
			
			table.render({
				id:'t_user_data_update_log'
				,elem:'#t_user_data_update_log'
				,url: 'queryUserDataUpdateActionLog'
				,method: 'post'
				,page: true
				,cols : [[ 		//表格列定义
					{field  : 'id',title: 'ID',width:70,fixed: 'left'}
					,{field  : 'actionUser',title: '操作人'}
				 	,{field : 'actionType',title: '操作类型'}
					,{field : 'submitTime',title: '提交时间',templet: '<div>{{ common.toDateString(d.submitTime,"yyyy-MM-dd")}}</div>'}
					,{field : 'actionStatus',title: '执行状态'}
					,{field : 'actionTime',title: '执行时间',templet: '<div>{{ common.toDateString(d.actionTime,"yyyy-MM-dd HH:mm")}}</div>'}
				]]
			});
			//表单提交事件
			form.on('submit(saveBtn)',function(){
				$.ajax({
					method: 'post'
					,url: 'saveUserUpdateLog'
					,dateType: 'json'
					,data: {
						jsonData : JSON.stringify(data.field)
					}
					,success: function(data){
						if(data.success){
							layer.msg(data.msg,function(){
								layer.close(layerId);
								location.reload();
							});
						}else{
							layer.msg(data.msg);
						}
					}
					,error:function(a,b,c){
						layer.msg("系统操作异常，请联系管理员！");
					}
				});
			});
			
			form.on('checkbox(userAll)', function(data){
// 				if (data.elem.checked){    
// 	                $("input[name='storeCbx']:checkbox").each(function(){   
// 	                      $(this).attr('checked',true);
// 	                });  
// 	            } else {
// 	            	alert("stor");
// 	                $("input[name='storeCbx']:checkbox").each(function() {     
// 	                      $(this).attr("checked",false);    
// 	                });  
// 	            }
				if (data.elem.checked){    
	                $("input:checkbox").each(function(){   
	                      $(this).attr('checked',true);
	                });  
	            } else {
	                $("input:checkbox").each(function() {     
	                      $(this).attr("checked",false);    
	                });  
	            }
				form.render();
			});        
		});
	</script>
</body>
</html>