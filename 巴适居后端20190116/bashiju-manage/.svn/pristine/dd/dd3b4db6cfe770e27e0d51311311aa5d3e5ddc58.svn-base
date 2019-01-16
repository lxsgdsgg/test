<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>提成工资方案批量配置</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	
	</head>
	<body>
		<!-- 批量设置提成工资方案 -->
		<%@include file="/common/common.jsp" %>
		<div class="layui-container">
			<form id="batchSetForm" name="batchSetForm" class="layui-form" action="" style="margin-top:10px;">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label" style="width:80px;"><span style="color:red;">*</span>选择城市</label>
						<div class="layui-input-inline"  style="width: 100px;">
							<input type="hidden" id="areaCode" value="" name="areaCode">
							<input id="areaName" type="text" required lay-verify="required|moreThanZero"
								class="layui-input" placeholder="请选择城市" autocomplete="off" style="width: 120px;"/>
						</div>
						<label class="layui-form-label" style="width: 50px;">经纪人</label>
						<div class="layui-input-inline" style="width: 120px;margin-left:10px">
							<select id="proposalOne" name="proposalOne" lay-filter="proposalOne">
								<option value=""></option>
								<c:forEach var="proposal" items="${proposalList}">
									<option value="${proposal.id}">${proposal.extractName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="layui-input-inline" style="width: 120px;">
							<select id="wageOne" name="wageOne" lay-filter="wageOne">
								<option value=""></option>
								<c:forEach var="wage" items="${wageList}">
									<option value="${wage.id}">${wage.extractName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<hr class="layui-bg-blue" style="font-weight:700;">
				<c:forEach var="user" items="${userList}">
					<div class="layui-form-item">
						<label id="${user.id}" name="${user.id}" class="layui-form-label" style="width:280px;">${user.deptName}${'**'}${user.realName}</label>
						<div class="layui-input-inline" style="width:120px;">
							<select id="proposal" name="${'proposal_'}${user.id}" lay-filter="proposalSel">
								<option value=""></option>
								<c:forEach var="proposal" items="${proposalList}">
									<option value="${proposal.id}">${proposal.extractName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="layui-input-inline" style="width:120px;">
							<select id="wage" name="${'wage_'}${user.id}" lay-filter="wageSel" >
								<option value=""></option>
								<c:forEach var="wage" items="${wageList}">
									<option value="${wage.id}">${wage.extractName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:forEach>
				<div class="layui-form-item" style="margin-left:280px;">
					 <div class="layui-input-block">
					 	<button id="batchSetForm" class="layui-btn" lay-submit="" lay-filter="batchSetForm">确定</button>
				   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					 </div>
				</div>
			</form>
		</div>
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		
		<script>
			var form;
			var table;

			var cityList = JSON.parse('${cityList}');
			//单条设置的城市选择 
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#areaCode").val(code);
				$("#areaName").val(name);
				rgCode.hidden();
			},3,$("#areaName"),false);
			
			layui.use(['table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				
				batchSetProposalAndWageInfo();
				
				form.on('select(proposalOne)',function(data){
					$("select#proposal option[value='" + data.value +"']").attr("selected","selected");
					form.render('select'); 
				});
				
				form.on('select(wageOne)',function(data){
					$("select#wage option[value='" + data.value +"']").attr("selected","selected");
					form.render('select'); 
				});
			});
			/**
			 * 批量设置提成工资方案
			 */
			function batchSetProposalAndWageInfo(){
				//将输入框值置为空
				$("#batchSetForm select").each(function (index, domEle) {
					$(this).val('');
					form.render('select'); 
				});
				//表单提交
				form.on('submit(batchSetForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'batchSaveProposalAndWageSchemaInfo'
						,dataType: 'json'
						,data:{
							jsonData: JSON.stringify(data.field),
						},
						success: function(data){
							if(data.success){
								layer.alert(data.msg,function(index){
					    			parent.layer.closeAll();
					    			parent.location.reload();
			    				});
							}else{
								layer.msg(data.msg);
							}
						}
						,error: function(a,b,c){
							layer.msg("系统操作异常!");
						}
					});
					return false;
				});
			}
		</script>
	</body>
</html>