<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>提成方案配置</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
		<style type="text/css">
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
	</head>
	<body>
		<!-- 查询模块定义 -->
		<div class="" style="margin-top:10px;">
			<div class="layui-row">
				<div class="toolBar">
					选择城市
				  	<div class="layui-inline">
				  		<input type="hidden" id="areaCode" name="areaCode">
						<input class="layui-input" name="areaName" placeholder="请选择城市" id="areaName" autocomplete="off">
					</div>
					方案名称
					<div class="layui-inline">
						<input class="layui-input" name="extractName" placeholder="请输入方案名称" id="extractName" autocomplete="off">
					</div>
				  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
				  		<button class="layui-btn" name="searchBtn" id="searchBtn" onClick="searchData()">查询</button>
				  	</c:if>
				  	<c:if test="${onlyBtnMenus.proposalAdd!='' && onlyBtnMenus.proposalAdd!=null}">
				  		<button class="layui-btn layui-btn-normal" onclick="addOrEditProposalInfo('新增提成方案','')">新增</button>
				  	</c:if>
				</div>
				
				<div class="layui-col-md6">
					<div class="layui-field-box">
						<!--提成方案对象 -->
						<table class="layui-table" id="t_proposal_mgr" lay-filter="t_proposal_mgr"></table>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-field-box">
						<!--业绩类型对象 -->
						<table class="layui-table" id="t_performance_mgr" lay-filter="t_performance_mgr"></table>
					</div>
				</div>
			</div>
		</div>
		<!--提成方案信息编辑弹窗代码 -->
		<form id="proposalMgrForm" name="proposalMgrForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" id="id" name="id" value="">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>选择城市</label>
				<div class="layui-input-inline">
					<input type="hidden" id="cityId" value="" name="areaCode">
					<input id="cityName" name="areaName" type="text" required lay-verify="required"
						class="layui-input" placeholder="请选择城市" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>方案名称</label>
				<div class="layui-input-inline">
					<input id="extractName" name="extractName" type="text" required lay-verify="required"
						class="layui-input" placeholder="请输入方案名称" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;">方案说明</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="extractRemark" name="extractRemark" type="text"
						class="layui-input" placeholder="请输入方案说明" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:15px;">
				 <div class="layui-input-block">
				 	<button id="proposalMgrForm" class="layui-btn" lay-submit="" lay-filter="proposalMgrForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		
		
		<!--方案详细配置表单定义 -->
		<form id="proposalDetailForm" class="layui-form layui-hide" action="" style="margin-top:20px;">
			<input type="hidden" id="proposalId" value="" name="proposalId">
			<input type="hidden" id="pfmTypeId" value="" name="pfmTypeId">
			<input type="hidden" id="rgCode" value="" name="areaCode">
			<div class="layui-form-item">
				<label><span style="margin-left:10%;font-size:14px;font-weight:700;">合同实收率</span></label>
				<label><span style="margin-left:20%;font-size:14px;font-weight:700;">提成比例</span></label>
			</div>
			<c:forEach varStatus="step" begin="1" end="10">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">实收率>=</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group" >
							<input id="${step.count}" name="${step.count}" type="number" min="1" max="100" class="form-control"
								lay-verify="percent" autocomplete="off" style="width:100px;" value=""/>
							<div class="input-group-addon">%</div>
							<input id="${step.count + 10}" name="${step.count + 10}" type="number" min="1" max="100" class="form-control"
								lay-verify="percent" autocomplete="off" style="width:100px;margin-left:10px;" value=""/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="layui-form-item" style="margin-top:15px;">
				 <div class="layui-input-block">
				 	<button id="proposalDetailForm" class="layui-btn" lay-submit="" lay-filter="proposalDetailForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		<script type="text/html" id="operationBarOne">
			{{# if(d.proposalEdit !='' && d.proposalEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="proposalEdit" lay-event="proposalEdit">修改</a>
			{{# } }}
			{{# if(d.proposalDel !='' && d.proposalDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="proposalDel" lay-event="proposalDel">删除</a>
			{{# } }}
		</script>
		
 		<script type="text/html" id="operationBarTwo"> 
			{{# if(d.proposalSet !='' && d.proposalSet!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="proposalSet" lay-event="proposalSet">设置</a>
			{{# } }}
		</script>
		
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script>
			var form;
			var table;
			var proposalId;
			//查询选择城市控件
			var cityList = JSON.parse('${cityList}');
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#areaCode").val(code);
				$("#areaName").val(name);
				rgCode.hidden();
			},3,$("#areaName"),false);
			
			//新增或修改的城市选择 
			var city = new MultiSelection (cityList,function(code,name,level){
				$("#cityId").val(code);
				$("#cityName").val(name);
				rgCode.hidden();
			},3,$("#cityName"),false);
			
			layui.use(['table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				//第一个实例
				table.render({
					id: 't_proposal_mgr'
					,elem : '#t_proposal_mgr'
// 					,height: 'full-100'
					,url: 'queryProposalSetAllDataList'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'extractName'
						,title : '方案名称'
						,templet:function(d){
		    			  	return '<span lay-event="showDetail" style="color:#01AAED;cursor:pointer;">'+d.extractName+'</span>';
			    		} 
					}, {
						field : 'extractRemark'
						,title : '方案说明'
					}, {
						field : 'areaCode'
						,title : '城市编码'
						,sort  : true
					}, {
						field : 'areaName'
						,title : '所属城市'
					}, {
						field : 'addTime'
						,title : '添加时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
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
						, toolbar: '#operationBarOne'
					}]],done: function(res, curr, count){
						proposalId = res.data[0].id;
				    	showPerformanceInfo(res.data[0].areaCode);
				  	}
				});
				// 注：tool是工具条事件名
				table.on('tool(t_proposal_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					proposalId = data.id;
					//删除事件
					if (layEvent === 'proposalDel') {
						layer.confirm('确定要删除该提成方案信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "deleteProposalSetInfo",
								dataType : "json",
								data : {
									proposalId : proposalId
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
					} else if (layEvent === 'proposalEdit') { //编辑事件
						addOrEditProposalInfo('修改提成方案',data);
					} else if(layEvent === 'showDetail'){//点击显示右侧列表数据
						showPerformanceInfo(data.areaCode);
					}
				});
				form.verify({  
			    	percent: function(value){
			        	if(value < 0 || value > 100){
			        		return '数字必须是0-100之间';
			        	}
			        }
			  	}); 
			});
			
			//展示右侧区域表格
			function showPerformanceInfo(areaCode){
				//第二个实例
				table.render({
					id: 't_performance_mgr'
					,elem : '#t_performance_mgr'
					,url  : 'getPerformanceTypePageObjForProposal?areaCode='+areaCode+"&proposalId="+proposalId
					,method : 'post'
					,page: true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'pfmTypeName'
						,title : '业绩名称'
					}, {
						field : 'usePfmTypeName'
						,title : '使用业绩类型'
					}, {
						field : 'caculateMethodName'
						,title : '计算方式'
					}, {
						field : 'isSetted'
						,title : '配置状态'
					}, {
						fixed: 'right'
						, title: '操作区域'
						, width: 100
						, align:'center'
						, toolbar: '#operationBarTwo'
					}]]
				});
				
				// 监听第二个表格工具条
				table.on('tool(t_performance_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var id = data.id;
					if (layEvent === 'proposalSet') { //编辑事件
						$.ajax({
							type: 'post'
							,url: 'queryAllProposalDetail'
							,dataType: 'json'
							,data:{confId: proposalId,pfmTypeId: id,areaCode: data.areaCode}
							,success: function(result){
								if(result.success){
									setProposalSetInfo('方案明细设置',result,data);
								}else{
									layer.msg(result.msg);
								}
							}
							,error: function(a,b,c){
								layer.msg("系统操作异常!");
							}
						});
					} 
				});
			}
			/**
			 * 添加或修改提成方案信息
			 */
			function addOrEditProposalInfo(title,data){
				//将输入框值置为空
				$("#proposalMgrForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
			 	//显示隐藏表单
			 	$('#proposalMgrForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: '<span style="font-size=16px;color: #1E9FFF;font-weight:700">'+ title + '</span>'
				  	,type: 1
				  	,area:['380px','280px']
					,resize: false
				  	,content: $("#proposalMgrForm")
				  	,end: function(index, layero){ 
						$('#proposalMgrForm').addClass("layui-hide");
					}
				});
				//设置表单内容值
				$("#proposalMgrForm input").each(
					function(index, domEle) {
						$(this).val("");
						if ($(this).attr("lay-verify") != null
								&& "number" == $(this).attr("lay-verify")) {
							$(this).val("0");
						}
						var attr = $(this).attr("name");
						if (data[attr] != null && data[attr] != "") {
							$(this).val(data[attr]);
						}
					}
				);
				$("#cityId").val(data.areaCode);
				$("#cityName").val(data.areaName);
				//表单提交
				form.on('submit(proposalMgrForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateProposalInfo'
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
			}
			
			/**
			 * 添加或修改提成方案详细信息
			 */
			function setProposalSetInfo(title,result,data){
				//显示隐藏表单
			 	$('#proposalDetailForm').removeClass("layui-hide");
				//将输入框值置为空
				$("#proposalDetailForm input").each(function (index, domEle) { 
					$(this).val("");
					if ($(this).attr("lay-verify") != null
							&& "number" == $(this).attr("lay-verify")) {
						$(this).val("0");
					}
				});
				layerId = layer.open({
				  	title: '<span style="font-size=16px;color: #1E9FFF;font-weight:700">'+ title + '</span>'
				  	,type: 1
				  	,area:['400px','700px']
					,resize: true
				  	,content: $("#proposalDetailForm")
				  	,end: function(index, layero){ 
						$('#proposalDetailForm').addClass("layui-hide");
					}
				});
				//表单提交
				form.on('submit(proposalDetailForm)',function(data){
					var result = "0";
					for(var i = 1;i < 10;i ++){
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
						layer.msg("请按顺序填写！");
						return false;
					}
					$.ajax({
						type: 'post'
						,url: 'saveProposalSetDetailInfo'
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
				
				var temp = result.data;
				if(temp != null && temp.length != 0){
					$("#proposalId").val(temp[0].confId);
					$("#pfmTypeId").val(temp[0].pfmTypeId);
					$("#rgCode").val(temp[0].areaCode);
					for(var i = 1;i <= temp.length;i++){
						var valueTemp = temp[i - 1];
						$("#" + (i + 10)).val(valueTemp.extractRate);
						$("#" + i).val(valueTemp.contractRate);
					}
				}else{
					$("#proposalId").val(proposalId);
					$("#pfmTypeId").val(data.id);
					$("#rgCode").val(data.areaCode);
				}
			}
			
			/**
			 * 查询页面数据函数
			 */
			function searchData() {
				var extractName = $("#extractName").val();
				var areaCode = $("#areaCode").val();
				var areaName = $("#areaName").val();
				if(areaName == ""){
					areaCode = "";
				}
				//执行重载
				table.reload('t_proposal_mgr', {
					page : {
						//重新从第 1 页开始
						curr : 1
					},
					where : {
						extractName : extractName,
						areaCode : areaCode
					}
				});
			}
		</script>
	</body>
</html>