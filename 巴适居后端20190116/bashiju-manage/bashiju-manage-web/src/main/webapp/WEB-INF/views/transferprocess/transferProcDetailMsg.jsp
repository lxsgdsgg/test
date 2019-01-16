<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业主/客户明细材料维护页面</title>
</head>
<body>
	<%@include file="/common/common.jsp"%>
	<div class="layui-container">
		<form class="layui-form" id="detailMgrForm" action="" style="margin-top: 20px;">
			<input type="hidden" name="detail" value="${newMap}"/>
			<input type="hidden" name="procId" value="${transferProc.id}"/>
			<input type="hidden" name="confType" value="${transferProc.confType}"/>
			<div class="layui-form-item" style="text-align:center;margin-top:3%;">
				<label><span style="margin-bottom:1%;font-size:14px;font-weight:600;">明细名称</span></label>
			</div>
			
			<c:forEach varStatus="step" begin="1" end="30">
				<c:if test="${step.count == (step.count*3-2)}">
					<div class="layui-form-item"></div>
				</c:if>	
					
				<div class="layui-input-inline" style="margin-top:1%;"> 
					<!-- 新增 -->
					<c:if test="${null == newMap}">
						<input class="layui-input" type="text" id="${step.count}" name="${step.count}"
							value="" autocomplete="off">
					</c:if>
					
					<c:if test="${null != newMap}">
						<c:set var="rowNum" scope="session" value="${''}${step.count}${''}"/>
						<c:set var="detailName" scope="session" value="${newMap[rowNum].detailName}"/>
						<!-- 修改时名称不为空 -->
						<c:if test="${null != detailName}">
							<input class="layui-input" detailId="${newMap[rowNum].id}" type="text" name="${step.count}" 
								value="${detailName}" autocomplete="off">
						</c:if>
						<!-- 修改时名称为空 -->
						<c:if test="${null == detailName}">
							<input class="layui-input" type="text" name="${step.count}" value="" autocomplete="off">
						</c:if>
					</c:if>
				</div>
			</c:forEach>
			<!-- 按钮区域 -->
			<div class="layui-form-item" style="margin-top:2%;margin-left:100px;">
			    <div class="layui-input-block">
			      	<button class="layui-btn" lay-submit lay-filter="detailMgrForm">确定</button>
			      	<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
		</form>
	</div>
	<script>
		layui.use(['jquery','form'],function(){
			var form = layui.form;
			var $ = layui.$;
			form.on('submit(detailMgrForm)',function(data){
				$.ajax({
					type: 'post'
					,url: 'saveOrUpdateTransferProcDetail'
					,dataType: 'json'
					,data:{
						jsonData: JSON.stringify(data.field)
					}
					,success:function(data){
						if(data.success){
							layer.alert(data.msg,function(index){
								parent.layer.closeAll();
				    			parent.location.reload();
		    				});  
						}else{
							layer.msg(data.msg);
						}
					}
					,error:function(a,b,c){
						layer.msg("系统操作错误!");
					}
				});
				return false;
			});
		});
	</script>
</body>
</html>