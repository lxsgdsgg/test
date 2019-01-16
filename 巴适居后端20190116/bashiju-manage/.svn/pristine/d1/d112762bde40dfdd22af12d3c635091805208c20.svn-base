<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>过户流程设置页面</title>
</head>
<body>
	<%@include file="/common/common.jsp"%>
	<div class="layui-container">
		<form class="layui-form" id="transProcSetForm" action="" style="margin-top: 20px;">
			<input type="hidden" name="procId" value="${transferProc.id}"/>
			<input type="hidden" name="confType" value="${confType}"/>
			<input type="hidden" name="detail" value="${procNameInfo}"/>
			<div class="layui-form-item">
				<label><span style="font-size:14px;font-weight:600;">流程节点名称</span></label>
			</div>
			<c:forEach varStatus="step" begin="1" end="${procNameCount}">
				<div class="layui-form-item">
					<div class="layui-inline">
						<select id="${step.count}" name="${step.count}" lay-filter="checkSel">
							<option value="">请选择...</option>
							<c:forEach var="procNames" items="${procNames}">
								<c:set var="rowNum" scope="session" value="${''}${step.count}${''}"/>
								<c:set var="detailCode" scope="session" value="${procNameInfo[rowNum].detailCode}"/>
								<c:if test="${null != procNameInfo and detailCode == procNames.key}">
									<option value="${detailCode}" selected="selected">${procNames.value}</option>
								</c:if>
								<c:if test="${null == procNameInfo or detailCode != procNames.key }">
									<option value="${procNames.key}">${procNames.value}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:forEach>
			<!-- 按钮区域 -->
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      	<button class="layui-btn" lay-submit lay-filter="transProcSetForm">确定</button>
			      	<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
		</form>
	</div>
	<script>
		layui.use(['jquery','form'],function(){
			var form = layui.form;
			var $ = layui.$;
			form.on('submit(transProcSetForm)',function(data){
				var result = "0";
				var cout = ${procNameCount};
				for(var i = 1;i < cout;i ++){
					var val = $("#" + i).val();
					if(val == "" && result == "0"){
						result = "1";//第一次为空时标记
						continue;
					}
					if(val != "" && result == "1"){
						result = "2";
						break;
					}
				}
				if(result == "2"){
					layer.msg("请按顺序选择流程节点！");
					return false;
				}
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
			form.on('select(checkSel)',function(data){
				var id = data.elem.getAttribute("id");
				var oneVal = $("#" + id).val();
				var result = "0";
				for(var i = 1; i <= id;i++){
					var value = $("#" + i).val();
					if(value == "" && i != id){
						result = "1";
						break;
					}
					if(oneVal == value && id != i){//第一个节点排除
						layer.msg("流程不能重复！");
						break;
					}
				}
				if(result == "1"){
					layer.msg("请按顺序填写流程！");
				}
			});
			
		});
	</script>
</body>
</html>