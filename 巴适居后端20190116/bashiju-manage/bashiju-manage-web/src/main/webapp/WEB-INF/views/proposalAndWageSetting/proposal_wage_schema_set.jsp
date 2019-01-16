<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>提成工资方案配置</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	</head>
	<body>
		<!-- 查询模块定义 -->
		<div class="" style="margin-top:10px;">
			<div class="toolBar layui-form" id="searchArea">
				选择城市
			  	<div class="layui-inline">
			  		<input type="hidden" id="cityId" name="cityId">
					<input class="layui-input" id="cityName" name="cityName" placeholder="请选择城市" autocomplete="off">
				</div>
				选择门店
			  	<div class="layui-inline">
			  		<input type="hidden" id="deptId" name="deptId">
					<input class="layui-input" id="deptName" name="deptName" placeholder="请选择门店" autocomplete="off">
				</div>
				提成方案名称
				<div class="layui-inline">
					<select id="proposalId" name="proposalId">
						<option value="">--请选择--</option>
						<c:forEach var="proposal" items="${proposalList}">
							<option value="${proposal.id}">${proposal.extractName}</option>
						</c:forEach>
					</select>
				</div>
				工资方案名称
				<div class="layui-inline">
					<select id="salaryId" name="salaryId" >
						<option value="">--请选择--</option>
						<c:forEach var="wage" items="${wageList}">
							<option value="${wage.id}">${wage.extractName}</option>
						</c:forEach>
					</select>
				</div>
				
				
			  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" id="searchBtn" onClick="searchData()">查询</button>
			  	</c:if>
			  	<c:if test="${onlyBtnMenus.batchSetSchema!='' && onlyBtnMenus.batchSetSchema!=null}">
			  		<button class="layui-btn layui-btn-normal" onclick="batchSetProposalAndWageInfo('批量分配方案')">批量分配方案</button>
			  	</c:if>
			</div>
			
			<!--提成工资方案列表对象 -->
			<table class="layui-table" id="t_proposal_wage_mgr" lay-filter="t_proposal_wage_mgr"></table>
		</div>
		<!--提成工资方案信息编辑弹窗代码 -->
		<form id="oneSetMgrForm" name="oneSetMgrForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" id="id" name="id" value="">
			<input type="hidden" id="userId" name="userId" value="">
			<label class="layui-form-label">经纪人</label>
			<label id="userNameEdit" class="layui-form-label" style="width:200px;text-align:left;"></label>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>选择城市</label>
				<div class="layui-input-inline">
					<input type="hidden" id="areaCode" value="" name="areaCode">
					<input id="areaName" type="text" required lay-verify="required|moreThanZero"
						class="layui-input" placeholder="请选择城市" autocomplete="off" style="width: 300px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>提成方案</label>
				<div class="layui-input-inline">
					<select id="extractId" name="extractId" required lay-verify="required">
						<option value="">--请选择--</option>
						<c:forEach var="proposal" items="${proposalList}">
							<option value="${proposal.id}">${proposal.extractName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><span style="color:red;">*</span>工资方案</label>
				<div class="layui-input-inline">
					<select id="salaryConfId" name="salaryConfId" required lay-verify="required">
						<option value="">--请选择--</option>
						<c:forEach var="wage" items="${wageList}">
							<option value="${wage.id}">${wage.extractName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:15px;">
				 <div class="layui-input-block">
				 	<button id="oneSetMgrForm" class="layui-btn" lay-submit="" lay-filter="oneSetMgrForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		
		<script type="text/html" id="operationBar">
			{{# if(d.setBtn !='' && d.setBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="setBtn" lay-event="setBtn">设置</a>
			{{# } }}
		</script>
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script>
			var form;
			var table;
			//查询选择城市控件
			var cityList = JSON.parse('${cityList}');
			var deptList = JSON.parse('${deptList}');
			var city = new MultiSelection (cityList,function(code,name,level){
				$("#cityId").val(code);
				$("#cityName").val(name);
				rgCode.hidden();
			},3,$("#cityName"),false);
			
			//单条设置的城市选择 
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#areaCode").val(code);
				$("#areaName").val(name);
				rgCode.hidden();
			},3,$("#areaName"),false);
			//选择部门
			var rgCode = new MultiSelection(deptList,function(code,name,level){
				$("#deptId").val(code);
				$("#deptName").val(name);
				rgCode.hidden();
			},null,$("#deptName"),false);
			
			layui.use(['table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				//第一个实例
				table.render({
					id: 't_proposal_wage_mgr'
					,elem : '#t_proposal_wage_mgr'
					,url: 'queryProposalAndWageSchemaInfoWithPage'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'deptName'
						,title : '门店名称'
					}, {
						field : 'areaName'
						,title : '所属城市'
					}, {
						field : 'realName'
						,title : '经纪人'
					}, {
						field : 'proposalName'
						,title : '提成方案'
					}, {
						field : 'salaryName'
						,title : '工资方案'
					}, {
						field : 'addTime'
						,title : '添加时间'
						,sort: true						
						,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
					}, {
						field : 'updateTime'
						,title : '更新时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
					},{
						fixed: 'right'
						, title: '操作区域'
						, width: 120
						, align:'center'
						, toolbar: '#operationBar'
					}]]
				});
				// 注：tool是工具条事件名
				table.on('tool(t_proposal_wage_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					wageId = data.id;
					//删除事件
					if (layEvent === 'setBtn') {
						setProposalAndWage('提成和工资方案设置',data);
					}
				});
			});
			/**
			 * 设置单个提成和工资方案信息
			 */
			function setProposalAndWage(title,data){
				//将输入框值置为空
				$("#oneSetMgrForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
			 	//显示隐藏表单
			 	$('#oneSetMgrForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: '<span style="font-size=16px;color: #1E9FFF;font-weight:700">'+ title + '</span>'
				  	,type: 1
				  	,area:['450px','320px']
					,resize: false
				  	,content: $("#oneSetMgrForm")
				  	,end: function(index, layero){ 
						$('#oneSetMgrForm').addClass("layui-hide");
					}
				});
				//设置表单内容值
				$("#id").val(data.id);
				$("#userId").val(data.userId);
				$("#areaCode").val(data.areaCode);
				$("#areaName").val(data.areaName);
				$("#userNameEdit").text(data.realName);
				
				//表单提交
				form.on('submit(oneSetMgrForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateProposalAndWageSchemaInfo'
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
						,error: function(a,b,c){
							layer.msg("系统操作异常!");
						}
					});
					return false;
				});
				$("select#extractId option[value='" + data.proposalId +"']").attr("selected","selected");
				$("select#salaryConfId option[value='" + data.salaryConfId +"']").attr("selected","selected");
				form.render('select');
			}
			/**
			 * 批量设置提成工资方案
			 */
			function batchSetProposalAndWageInfo(title){
				var dataParam = {};
				dataParam.areaCode = $("#cityId").val();
				dataParam.deptId = $("#deptId").val();
				dataParam.proposalId = $("#proposalId").val();
				dataParam.salaryCofId = $("#salaryId").val();
				
// 				$("#searchArea input").each(function (index, domEle) { 
// 					var name = $(this).attr("name");
// 					eval("dataParam." + name + "=\"" + $(this).val() + "\"") ; 
// 				});
// 				$("#searchArea select").each(function (index, domEle) { 
// 					var name = $(this).attr("name");
// 					eval("dataParam." + name + "=\"" + $(this).val() + "\"") ; 
// 				});
				
    			var jsonData = JSON.stringify(dataParam).replace(/\"/g,"'");  

    			var batchSet ={
			        type: 2
			        ,id: "batchSetIFrame"
					,title: '<span style="font-size=16px;color: #1E9FFF;font-weight:700">' + title + '</span>'
				  	,area:['630px','700px']
			      	,shade: 0.5
			        ,resize: false
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'getBatchSetModel?dataParam=' + jsonData
			        ,zIndex: layer.zIndex
			        ,success: function(layero,zIndex){
			        	layer.setTop(layero);
			        }
			    };
				common.windowLayerOpen(batchSet);
			}
			/**
			 * 查询页面数据函数
			 */
			function searchData() {
				var deptId = $("#deptId").val();
				var deptName = $("#deptName").val();
				var areaCode = $("#cityId").val();
				var areaName = $("#cityName").val();
				var proposalId = $("#proposalId").val();
				var salaryId = $("#salaryId").val();
				var userName = $("#userName").val();
				if(areaName == ""){
					areaCode = "";
				}
				if(deptName == ""){
					deptId = "";
				}
				//执行重载
				table.reload('t_proposal_wage_mgr', {
					page : {
						//重新从第 1 页开始
						curr : 1
					},
					where : {
						deptId : deptId,
						areaCode : areaCode,
						salaryId : salaryId,
						proposalId : proposalId,
						userName : userName
					}
				});
			}
		</script>
	</body>
</html>