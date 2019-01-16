<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分成配置管理</title>

</head>
	<style>
		.input-group {
		    position: relative;
		    display: table;
		    border-collapse: separate;
		}
		.input-group .form-control:not(:first-child):not(:last-child), .input-group-addon:not(:first-child):not(:last-child) {
		    border-radius: 0;
		}
		.input-group .form-control,  .input-group .input-group-addon{
		    width: auto;
		    display: table-cell;
		}
		.input-group-addon:last-child {
		    border-left: 0;
		}
		.input-group .form-control:last-child, .input-group-addon:last-child {
		    border-top-left-radius: 0;
		    border-bottom-left-radius: 0;
		}
		.input-group-addon {
		    padding: 1px 3px;
		    font-size: 14px;
		    color: #c2c2c2;
		    text-align: center;
		    border-bottom: 1px solid #D2D2D2;
		    border-top:1px solid #D2D2D2;
		    border-right:1px solid #D2D2D2;
		    border-left:0;
		    border-radius: 2px;
		}
		.form-control {
		    height: 38px;
		    width: 200px;
		    background-image: none;
		    border-bottom: 1px solid #D2D2D2;
		    border-top:1px solid #D2D2D2;
		    border-left:1px solid #D2D2D2;
		    border-right:0;
		    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
		    border-radius: 2px;
		}
	</style>
	<body>
		<!-- 加载source.common资源 -->
		<%@include file="/common/common.jsp" %>
		<div class="layui-container">
			<form id="divAddOrEditForm" class="layui-form" action="" style="margin-top:20px;">
				<div class="layui-form-item">
					<label><span style="margin-left:5%;font-size:14px;font-weight:700;">分成缘由</span></label>
					<label><span style="margin-left:10%;font-size:14px;font-weight:700;">分成比例</span></label>
					<label><span style="margin-left:20%;font-size:14px;font-weight:700;">分成转移(分成人没有时转移)</span></label>
				</div>
				<input type="hidden" name="configId" value="${divideInfo.id}">
				<div class="layui-form-item" >
					<label class="layui-form-label">房源录入人</label>
					<div class=layui-input-block>
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actionRoleIptRate" 
								lay-verify="percent" value="${divideInfo.actionRoleIptRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">房源开盘人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleOpMgrRate" 
								lay-verify="percent" value="${divideInfo.actRoleOpMgrRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">房源维护人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleHouseMsgRate" 
								lay-verify="percent" value="${divideInfo.actRoleHouseMsgRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">客源录入人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleCusIptRate" 
								lay-verify="percent" value="${divideInfo.actRoleCusIptRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">客源维护人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleCusMgrRate" 
								lay-verify="percent" value="${divideInfo.actRoleCusMgrRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">合同成交人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleContractMgrRate" 
								lay-verify="percent" value="${divideInfo.actRoleContractMgrRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">房源委托人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actHouseEntrustMgrRate" 
								lay-verify="percent" value="${divideInfo.actHouseEntrustMgrRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<div class="layui-input-inline">
						<select name="houseEntrustTrans">
					      	<option value="">请选择...</option>
					       	<c:forEach var="actRoleHouseMsg" items="${actRoleHouseMsg}">
						       	<c:if test="${null != divideInfo && divideInfo.houseEntrustTrans == actRoleHouseMsg.key}">
									<option value="${actRoleHouseMsg.key}" selected="selected">${actRoleHouseMsg.value}</option>      	
								</c:if>
								 <!-- 这里处理【新增】的时候  -->
						      	<c:if test="${divideInfo.houseEntrustTrans == null || divideInfo.houseEntrustTrans != actRoleHouseMsg.key}">
						      	·	<c:if test="${actRoleHouseMsg.key == 7}">
						      			<option value="${actRoleHouseMsg.key}" disabled>${actRoleHouseMsg.value}</option>
						      		</c:if>
						      		<c:if test="${actRoleHouseMsg.key != 7}">
										<option value="${actRoleHouseMsg.key}">${actRoleHouseMsg.value}</option>     
						      		</c:if>
								</c:if> 		
					        </c:forEach>
					    </select>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">拿钥匙人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleKeyMgrRate" 
								lay-verify="percent" value="${divideInfo.actRoleKeyMgrRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<div class="layui-input-inline">
						<select name="keyMgrTrans">
							<option value="">请选择...</option>
					       	<c:forEach var="actRoleHouseMsg" items="${actRoleHouseMsg}">
						       	<c:if test="${null != divideInfo && divideInfo.keyMgrTrans == actRoleHouseMsg.key}">
									<option value="${actRoleHouseMsg.key}" selected="selected">${actRoleHouseMsg.value}</option>      	
								</c:if>
								 <!-- 这里处理【新增】的时候  -->
						      	<c:if test="${divideInfo.keyMgrTrans == null || divideInfo.keyMgrTrans != actRoleHouseMsg.key}">
									<c:if test="${actRoleHouseMsg.key == 8}">
						      			<option value="${actRoleHouseMsg.key}" disabled>${actRoleHouseMsg.value}</option>
						      		</c:if>
						      		<c:if test="${actRoleHouseMsg.key != 8}">
										<option value="${actRoleHouseMsg.key}">${actRoleHouseMsg.value}</option>     
						      		</c:if>
								</c:if> 		
					        </c:forEach>
					    </select>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">签独家人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRoleSignUkRate" 
								lay-verify="percent" value="${divideInfo.actRoleSignUkRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<div class="layui-input-inline">
						<select name="signUkTrans">
					      	<option value="">请选择...</option>
					       	<c:forEach var="actRoleHouseMsg" items="${actRoleHouseMsg}">
						       	<c:if test="${null != divideInfo && divideInfo.signUkTrans == actRoleHouseMsg.key}">
									<option value="${actRoleHouseMsg.key}" selected="selected">${actRoleHouseMsg.value}</option>      	
								</c:if>
								 <!-- 这里处理【新增】的时候  -->
						      	<c:if test="${divideInfo.signUkTrans == null || divideInfo.signUkTrans != actRoleHouseMsg.key}">
									<c:if test="${actRoleHouseMsg.key == 9}">
						      			<option value="${actRoleHouseMsg.key}" disabled>${actRoleHouseMsg.value}</option>
						      		</c:if>
						      		<c:if test="${actRoleHouseMsg.key != 9}">
										<option value="${actRoleHouseMsg.key}">${actRoleHouseMsg.value}</option>     
						      		</c:if>   
								</c:if> 		
					        </c:forEach>
					    </select>
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">传照片人</label>
					<div class="layui-input-inline">
						<div class="input-group">
							<input class="form-control" type="number" max="100" min="0" name="actRolePivUploadRate" 
								lay-verify="percent" value="${divideInfo.actRolePivUploadRate}" autocomplete="off" style="width:120px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<div class="layui-input-inline" >
						<select name="picUploadTrans">
							<option value="">请选择...</option>
					      	<c:forEach var="actRoleHouseMsg" items="${actRoleHouseMsg}">
						       	<c:if test="${null != divideInfo && divideInfo.picUploadTrans == actRoleHouseMsg.key}">
									<option value="${actRoleHouseMsg.key}" selected="selected">${actRoleHouseMsg.value}</option>      	
								</c:if>
								 <!-- 这里处理【新增】的时候  -->
						      	<c:if test="${divideInfo.picUploadTrans == null || divideInfo.picUploadTrans != actRoleHouseMsg.key}">
									<c:if test="${actRoleHouseMsg.key == 10}">
						      			<option value="${actRoleHouseMsg.key}" disabled>${actRoleHouseMsg.value}</option>
						      		</c:if>
						      		<c:if test="${actRoleHouseMsg.key != 10}">
										<option value="${actRoleHouseMsg.key}">${actRoleHouseMsg.value}</option>     
						      		</c:if>     
								</c:if> 		
					        </c:forEach>
					    </select>
					</div>
				</div>
				<!-- 按钮区域 -->
				<div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit lay-filter="divAddOrEditForm">立即提交</button>
				      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				    </div>
				</div>
			</form>
		</div>
		<!-- script 脚本 -->
		<script>
		layui.use('form', function(){
			  //监听提交
			  var form = layui.form;
			  form.on('submit(divAddOrEditForm)', function(data){
				  if((data.field.actionRoleIpt + data.field.actRoleOpMgr + data.field.actRoleHouseMsg
					  + data.field.actRoleCusIpt + data.field.actRoleCusMgr 
					  + data.field.actRoleContractMgr + data.field.actHouseEntrustMgr 
					  + data.field.actRoleKeyMgr + data.field.actRoleSignUk 
					  + data.field.actRolePivUpload) == ""){
					  layer.alert("没有输入任何数据！");
					  return false;
				  }
			      $.ajax({
			    	type: "post",
			    	url: "saveOrUpdateDivConfInfo?configId=" + data.field.configId,
			    	dataType:"json",
			    	data:{
			    		jsonData:JSON.stringify(data.field)
			    	},
			    	success:function(data){
			    		if(data.success){
			    			layer.alert(data.msg,function(index){
				    			parent.layer.closeAll();
				    			parent.location.reload();
		    				});  
			    		}else{
			    			layer.alert(data.msg);
			    		}
			    	},
			    	error:function(a,b,c){
			    		layer.alert("系统操作异常，请稍后重试");
			    	}
			    });
			    return false;
			});
			  
		  	form.verify({  
		    	percent: function(value){
		        	if(value < 0 || value > 100){
		        		return '数字必须是0-100之间';
		        	}
		        }
		  	});  
		})
		</script>
	</body>
</html>