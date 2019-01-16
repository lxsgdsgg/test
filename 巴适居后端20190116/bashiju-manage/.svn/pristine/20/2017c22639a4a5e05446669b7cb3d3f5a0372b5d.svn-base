<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>工资方案配置</title>
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
			    height: 35px;
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
			<div class="toolBar">
				选择城市
			  	<div class="layui-inline">
			  		<input type="hidden" id="cityId" name=""cityId"">
					<input class="layui-input" id="cityName" name="cityName" placeholder="请选择城市" autocomplete="off">
				</div>
				方案名称
				<div class="layui-inline">
					<input class="layui-input" name="extractName" placeholder="请输入方案名称" id="extractName" autocomplete="off">
				</div>
			  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" id="searchBtn" onClick="searchData()">查询</button>
			  	</c:if>
			  	<c:if test="${onlyBtnMenus.wageAdd!='' && onlyBtnMenus.wageAdd!=null}">
			  		<button class="layui-btn layui-btn-normal" onclick="addOrEditWageSchemaInfo('新增工资方案','')">新增</button>
			  	</c:if>
			</div>
			
			<!--工资方案列表对象 -->
			<table class="layui-table" id="t_wage_mgr" lay-filter="t_wage_mgr"></table>
		</div>
		<!--工资方案信息编辑弹窗代码 -->
		<form id="wageMgrForm" name="wageMgrForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" id="id" name="id" value="">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>选择城市</label>
				<div class="layui-input-inline">
					<input type="hidden" id="areaCode" value="" name="areaCode">
					<input id="areaName" type="text" required lay-verify="required|moreThanZero"
						class="layui-input" placeholder="请选择城市" autocomplete="off" style="width: 300px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>方案名称</label>
				<div class="layui-input-inline">
					<input id="extractName" name="extractName" type="text" required lay-verify="required|moreThanZero"
						class="layui-input" placeholder="请输入方案名称" autocomplete="off" style="width: 300px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>基本工资</label>
				<div class="layui-input-inline">
					<div class="input-group" >
						<input id="baseSalary" name="baseSalary" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off" value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>养老保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="endowmentInsurance" name="endowmentInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>医疗保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="medicalInsurance" name="medicalInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>失业保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="unemploymentInsurance" name="unemploymentInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>工伤保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="employmentInjuryInsurance" name="employmentInjuryInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>生育保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="maternityInsurance" name="maternityInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>其他保险</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="otherInsurance" name="otherInsurance" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red">*</span>公积金</label>
				<div class="layui-input-inline" >
					<div class="input-group" >
						<input id="accumulationaFund" name="accumulationaFund" type="number" min="1" max="100" class="form-control"
							required lay-verify="required|moreThanZero" autocomplete="off"  value="" style="width:100px;"/>
						<div class="input-group-addon">元</div>[公司承担]
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;">方案备注</label>
				<div class="layui-input-inline" >
					<input id="remark" name="remark" type="text" class="layui-input"
						lay-verify="remark" autocomplete="off"  value="" style="width:300px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:15px;">
				 <div class="layui-input-block">
				 	<button id="wageMgrForm" class="layui-btn" lay-submit="" lay-filter="wageMgrForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		<script type="text/html" id="operationBar">
			{{# if(d.wageEdit !='' && d.wageEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="wageEdit" lay-event="wageEdit">修改</a>
			{{# } }}
			{{# if(d.wageDel !='' && d.wageDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="wageDel" lay-event="wageDel">删除</a>
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
			var city = new MultiSelection (cityList,function(code,name,level){
				$("#cityId").val(code);
				$("#cityName").val(name);
				rgCode.hidden();
			},3,$("#cityName"),false);
			
			//新增或修改的城市选择 
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#areaCode").val(code);
				$("#areaName").val(name);
				rgCode.hidden();
			},3,$("#areaName"),false);
			
			layui.use(['table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				//第一个实例
				table.render({
					id: 't_wage_mgr'
					,elem : '#t_wage_mgr'
// 					,height: 'full-100'
					,url: 'queryWageSchemaSetAllDataList'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'extractName'
						,title : '方案名称'
					}, {
						field : 'areaName'
						,title : '所属城市'
					}, {
						field : 'baseSalary'
						,title : '基本工资（元）'
					}, {
						field : 'endowmentInsurance'
						,title : '养老保险[公司承担]'
					}, {
						field : 'medicalInsurance'
						,title : '医疗保险[公司承担]'
					}, {
						field : 'unemploymentInsurance'
						,title : '失业保险[公司承担]'
					}, {
						field : 'employmentInjuryInsurance'
						,title : '工伤保险[公司承担]'
					}, {
						field : 'maternityInsurance'
						,title : '生育保险[公司承担]'
					}, {
						field : 'otherInsurance'
						,title : '其他保险[公司承担]'
					}, {
						field : 'accumulationaFund'
						,title : '公积金[公司承担]'
					}, {
						field : 'remark'
						,title : '备注'
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
				table.on('tool(t_wage_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					wageId = data.id;
					//删除事件
					if (layEvent === 'wageDel') {
						layer.confirm('确定要删除该工资方案信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "deleteWageSchemaInfo",
								dataType : "json",
								data : {
									wageId : wageId
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
					} else if (layEvent === 'wageEdit') { //编辑事件
						addOrEditWageSchemaInfo('修改工资方案',data);
					}
				});
				form.verify({  
			    	moreThanZero: function(value){
			        	if(value < 0 ){
			        		return '数字必须大于0';
			        	}
			        }
			  	});
			});
			/**
			 * 添加或修改工资方案信息
			 */
			function addOrEditWageSchemaInfo(title,data){
				//将输入框值置为空
				$("#wageMgrForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
			 	//显示隐藏表单
			 	$('#wageMgrForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: '<span style="font-size=16px;color: #1E9FFF;font-weight:700">'+ title + '</span>'
				  	,type: 1
				  	,area:['450px','700px']
					,resize: false
				  	,content: $("#wageMgrForm")
				  	,end: function(index, layero){ 
						$('#wageMgrForm').addClass("layui-hide");
					}
				});
				//设置表单内容值
				$("#wageMgrForm input").each(
					function(index, domEle) {
						var attr = $(this).attr("name");
						if (data[attr] != null) {
							$(this).val(data[attr]);
						}
					}
				);
				$("#areaCode").val(data.areaCode);
				$("#areaName").val(data.areaName);
				//表单提交
				form.on('submit(wageMgrForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateWageSchemaInfo'
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
			 * 查询页面数据函数
			 */
			function searchData() {
				var extractName = $("#extractName").val();
				var areaCode = $("#cityId").val();
				var areaName = $("#cityName").val();
				if(areaName == ""){
					areaCode = "";
				}
				//执行重载
				table.reload('t_wage_mgr', {
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