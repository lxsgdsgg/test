<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>业绩类型管理</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
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
			    width: 100px;
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
		<div class="toolBar">
			业绩类型名称：
		  	<div class="layui-inline">
			  	<div class="layui-input-inline"> 
					<input class="layui-input" name="pfmTypeName" placeholder="请输入业绩类型名称" id="pfmTypeName" autocomplete="off">
				</div>
			</div>
			所属城市：
		  	<div class="layui-inline">
	  			<div class="layui-input-inline"> 
					<input type="hidden" id="codeHid">
					<input class="layui-input" name="cityId" placeholder="请选择城市" id="cityId" autocomplete="off">
				</div>
			</div>
		  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
		  		<button class="layui-btn" name="searchBtn" id="searchBtn" onClick="searchData()">查询</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.pfmTypeAdd!='' && onlyBtnMenus.pfmTypeAdd!=null}">
		  		<button class="layui-btn layui-btn-normal" onclick="showAddOrModify('添加业绩类型','')">新增</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.pfmTypeSet!='' && onlyBtnMenus.pfmTypeSet!=null}">
		  		<button class="layui-btn" onclick="selectCity();">佣金标准设置</button>
		  	</c:if>
		</div>
		<!-- 放置配置数据显示表格对象 -->
		
		<table class="layui-table" id="t_pfm_type_mgr" lay-filter="t_pfm_type_mgr"></table>
		<p style="color:red">
			<br>注意：
			<br>1、每种业绩类型（住宅出售;商业出售;住宅出租;商业出租;一手成交;金融服务;）只能被配置一次；如已配置则不能再被选择！
			<br>2、每种业绩类型修改【计算方式】后，必须将【相关方案的提成比例】重新检查并保存一遍，否则仍按原计算方式计算提成造成数据不准确。尤指【合同直提计算】提成比例的设置和其它计算方式区别较大。
		</p>
		
		
		<!--  以下是弹窗代码 -->
		<form id="addOrModifyForm" name="addOrModifyForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" id="id" value="" name="id">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>业绩名称</label>
				<div class="layui-input-inline">
					<input id="pfmTypeName" name="pfmTypeName" type="text" required lay-verify="required"
						class="layui-input" placeholder="请输入业绩类型名称" autocomplete="off" style="width:400px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>计算方式</label>
				<div class="layui-input-inline" style="width:400px;">
					<select name="caculateMethod" id="caculateMethod" required lay-verify="required"></select>
					
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>适用业务类型</label>
				<div class="layui-input-inline" style="width:400px;">
					<input id="" type="hidden" value = "" name ="usePfmTypeName">
					<select name="usePfmTypeValue" id="usePfmTypeValue" xm-select-type="2" xm-select="select"
							required lay-verify="required"></select> 
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>选择城市</label>
				<input type="hidden" id="rgCode1" name="rgCode1"> 
				<input class="layui-input" id="rgName1" name="rgName1"
					placeholder="请选择城市" autocomplete="off" required lay-verify="required" style="width:400px;">
			</div>
			<div class="layui-form-item">
				 <div class="layui-input-block">
				 	<button id="savePfmBtn" class="layui-btn" lay-submit="" lay-filter="savePfmBtn">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		
		
		<!-- 佣金设置表单定义 -->
		<form id="commisionSetForm" class="layui-form layui-hide" action="" style="margin-top:20px;">
			<input type="hidden" id="companyId" value="" name="companyId">
			<input type="hidden" id="rgCode" value="" name="rgCode">
			<div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">住宅出售</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group" >
							<input id="residenceSale" type="number" min="1" name="residenceSale" class="form-control"
								required lay-verify="required|outerZero" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<label class="layui-form-label" style="width:90px;">的成交价比例</label>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">商业出售</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group">
							<input id="bussinessSale" type="number" name="bussinessSale" class="form-control"
								onclick="jurgeComIsBlank('businessceSaleUnit')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<label class="layui-form-label" style="width:170px;">成交价的比例或者固定额度</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group">
							<input id="businessceSaleUnit" type="number" name="businessceSaleUnit" class="form-control" 
								onclick="jurgeComIsBlank('bussinessSale')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">元</div>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">住宅出租</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group">
							<input id="residenceRent" type="number" min="1" name="residenceRent" class="form-control"
								 required lay-verify="required|outerZero" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div> 
					<label class="layui-form-label" style="width:90px;">的成交价比例</label>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">商业出租</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group" style="width:100px;">
							<input id="bussinessRent" type="number" name="bussinessRent" class="form-control"
								onclick="jurgeComIsBlank('bussinessRentUnit')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<label class="layui-form-label" style="width:170px;">成交价的比例或者固定额度</label>
					<div class="layui-input-inline" style="width:80px;">
						<div class="input-group">
							<input id="bussinessRentUnit" type="number" name="bussinessRentUnit" class="form-control" 
								onclick="jurgeComIsBlank('bussinessRent')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">元</div>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:60px;">一手成交</label>
					<div class="layui-input-inline" style="width:100px;">
						<div class="input-group" >
							<input id="houseSaleFirst" type="number" name="houseSaleFirst" class="form-control" 
								onclick="jurgeComIsBlank('houseSaleFirstUnit')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">%</div>
						</div>
					</div>
					<label class="layui-form-label" style="width:170px;">成交价的比例或者固定额度</label>
					<div class="layui-input-inline" style="width:80px;">
						<div class="input-group">
							<input id="houseSaleFirstUnit" type="number" name="houseSaleFirstUnit" class="form-control" 
								onclick="jurgeComIsBlank('houseSaleFirst')" autocomplete="off" style="width:80px;"/>
							<div class="input-group-addon">元</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
				      	<button class="layui-btn" id="commisionSetSave" lay-filter="commisionSetForm" lay-submit>保存</button>
				      	<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				    </div>
				</div>
			</div>
		</form>

		<div id="selectCityDiv" class="layui-form layui-hide" style="margin-top: 20px;">
			<div class="layui-form-item">
			<label class="layui-form-label"><span style="color: red;">*</span>选择城市</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="hidden" id="areaCode" name="areaCode"> 
				<input class="layui-input" id="areaName" name="areaName"
					placeholder="请选择城市" autocomplete="off" required lay-verify="required">
			</div>
			</div>
			<div class="layui-form-item">
			<div class="layui-input-block" style="margin-top: 20px;">
				<button id="selectCityBtn" class="layui-btn">确定</button>
				<button id="cancelBtn" class="layui-btn">取消</button>
			</div>
			</div>
		</div>


	<script type="text/html" id="operationBar">
			{{# if(d.pfmTypeEdit !='' && d.pfmTypeEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="pfmTypeEdit" lay-event="pfmTypeEdit">编辑</a>
			{{# } }}
			{{# if(d.pfmTypeDel !='' && d.pfmTypeDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="pfmTypeDel" lay-event="pfmTypeDel">删除</a>
			{{# } }}
		</script>
		
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script>
			var path = "<%=request.getContextPath()%>";
			var cityList = JSON.parse('${cityList}');
			//适用城市控件--查询区域
			var a = new MultiSelection (cityList,function(code,name,level){
				$("#cityId").val(name);
				$("#codeHid").val(code);
				a.hidden();
			},3,$("#cityId"),false);
			
			var cityList = JSON.parse('${cityList}');
			//适用城市控件--编辑区域
			var a = new MultiSelection (cityList,function(code,name,level){
				$("#rgName1").val(name);
				$("#rgCode1").val(code);
				a.hidden();
			},3,$("#rgName1"),false);
			
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#areaCode").val(code);
				$("#areaName").val(name);
				rgCode.hidden();
			},3,$("#areaName"),false);
			
			///定义拓展模块的路径
			layui.config({
				base: '${request.getContextPath()}/src/common/js/'
			}).extend({
				formSelects: 'formSelects-v3'
			});
			var form;
			var table;
			var formSelects;
			var usePfmType = "${usePfmType}";
			layui.use(['table','form','formSelects','layer'], function() {
				var $ = layui.$;
				table = layui.table;
				form = layui.form;
				formSelects = layui.formSelects;
				table.render({
					id: 't_pfm_type_mgr'
					,elem : '#t_pfm_type_mgr'
// 					,height: 460//'full-100'
					,url  : 'getPerformanceTypePageObj'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'id'
						,title : 'ID'
						,width : 80
						,sort  : true
						,fixed : 'left' 
					}, {
						field : 'pfmTypeName'
						,title : '方案名称'
						,sort  : true
					}, {
						field : 'caculateName'
						,title : '计算方式'
						,sort  : true
					}, {
						field : 'cityName'
						,title : '所属城市'
						,sort  : true
					}, {
						field : 'usePfm'
						,title : '适用类型名称'
						,sort  : true
					}, {
						field : 'operator'
						,title : '创建用户'
						,sort  : true
					}, {
						field : 'addTime'
						,title : '新增时间'
						,sort  : false
						,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
					}, {
						field : 'updateTime'
						,title : '更新时间'
						,sort  : true
						,templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'
					}, {
						fixed: 'right'
						, title: '操作区域'
						, width: 150
						, align:'center'
						, toolbar: '#operationBar'
					}]]
				});
	
				// 监听工具条
				// 注：tool是工具条事件名，t_transfer_proc_mgr是table原始容器的属性 lay-filter="对应的值"
				table.on('tool(t_pfm_type_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var pfmTypeId = data.id;
					//删除事件
					if (layEvent === 'pfmTypeDel') {
						layer.confirm('确定要删除该配置信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "delPfmTypeData",
								dataType : "json",
								data : {
									pfmTypeId : pfmTypeId
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
					} else if (layEvent === 'pfmTypeEdit') { //编辑事件
						showAddOrModify('修改业绩类型',data);
					} 
				});
			});
			/**
			 * 添加或修改页面
			 */
			function showAddOrModify(title,data){
				//将输入框值置为空
				$("#addOrModifyForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
			 	//显示隐藏表单
			 	$('#addOrModifyForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: title
				  	,type: 1
				  	,area:['620px','320px']
					,resize: false
				  	,content: $("#addOrModifyForm")
				  	,end: function(index, layero){ 
						$('#addOrModifyForm').addClass("layui-hide");
					}
				});
				//表单提交
				form.on('submit(savePfmBtn)',function(data){
					//得到条件多选下拉框中文
				  	var usePfmTypeNameTemp = formSelects.value('select', 'nameStr');
					//得到多选下拉的值   
					var usePfmTypeValueTemp = formSelects.value('select', 'val').join(",");
					data.field.usePfmTypeName = usePfmTypeNameTemp;
					data.field.usePfmTypeValue = usePfmTypeValueTemp;
					$.ajax({
						type: 'post'
						,url: 'saveOrModifyPfmTypeData'
						,dataType: 'json'
						,data:{
							jsonData: JSON.stringify(data.field)
						},
						success: function(data){
							if(data.success){
								layer.msg(data.msg);
								layer.close(layerId);
								location.reload();
							}else{
								layer.msg(data.msg);
							}
						},
						error:function(a,b,c){
				    		layer.msg("系统操作异常，请稍后重试");
				    	}
					});
					return false;
				});
				//设置表单内容值
				$("#addOrModifyForm input").each(
					function(index, domEle) {
						$(this).val("");
						if ($(this).attr("lay-verify") != null
								&& "number" == $(this).attr("lay-verify")) {
							$(this).val("0");
						}
						var attr = $(this).attr("name");
						if (data[attr] != null) {
							$(this).val(data[attr]);
						}
					}
				);
				$("#rgCode1").val(data.cityId);
				$("#rgName1").val(data.cityName);
				showCombobox(data);
			}
			
			//展示下拉
			function showCombobox(data){
				var caculateMethodStr = "";
				var usePfmTypeNameArr = [];
				var typeValueTemp = data.usePfmTypeValue;
				if(data != ''){
					caculateMethodStr = data.caculateMethod;
					usePfmTypeNameArr = typeValueTemp.split(",");
				}
				var usePfmTypeValue = JSON.parse('${usePfmTypeValue}');
				var arrLen = usePfmTypeNameArr.length;
				var html = '<option value="">请选择...</option>';
				for(var i = 0;i < usePfmTypeValue.length;i++){ 
					var oneObj = usePfmTypeValue[i];
					if( data != '' && typeValueTemp != undefined){
						for(var m = 0;m < arrLen;m++){
							if(oneObj.id == usePfmTypeNameArr[m]){
								html +='<option value="'+oneObj.id + '" selected>'+oneObj.fieldValue+'</option>';
								break;
							}
							if(oneObj.id != usePfmTypeNameArr[m] && m == arrLen-1){
								html +='<option value="' + oneObj.id + '">' + oneObj.fieldValue +'</option>';
							}
						}
					}else{
						html +='<option value="' + oneObj.id + '">' + oneObj.fieldValue +'</option>';
					}
				}
				
				var caculateMethod = JSON.parse('${caculateMethod}');
				var htmlObj = '<option value="">请选择</option>';
				for(var i = 0;i < caculateMethod.length;i++){
					var obj = caculateMethod[i];
					if( data != '' && caculateMethodStr != undefined){
						if(obj.id == caculateMethodStr){
							htmlObj += '<option value="' + obj.id + '" selected>' + obj.fieldValue+'</option>';
						}else{
							htmlObj += '<option value="' + obj.id+'">' + obj.fieldValue + '</option>';
						}
					}else{
						htmlObj += '<option value="' + obj.id+'">' + obj.fieldValue + '</option>';
					}
				}
				//城市选择器
// 				var htmlCity = '<option value="">请选择...</option>';
// 				var cityInfo = JSON.parse('${cityMap}');
// 				for(var i = 0;i < cityInfo.length;i++){ 
// 					var oneObj = cityInfo[i];
// 					if(data != '' && oneObj.id == data.cityId){
// 						htmlCity +='<option value="' + oneObj.id + '" selected>'+oneObj.name+'</option>';
// 					}else{
// 						htmlCity +='<option value="' + oneObj.id + '">' + oneObj.name +'</option>';
// 					}
// 				}
// 				$("#cityIdEdit").html(htmlCity);
				$("#usePfmTypeValue").html(html);
				$("#caculateMethod").html(htmlObj);
				form.render('select');
				//动态赋值给“条件(usePfmTypeName)”
				formSelects.value('select',usePfmTypeNameArr);
			}
			
			//########################################添加或修改业绩类型方法结束###################################
			/**
			 * 查询页面数据函数
			 */
			function searchData() {
				var pfmTypeName = $("#pfmTypeName").val();
				var cityId = "";
				var cityName = $("#cityId").val();
				if(cityName == ""){
					$("#codeHid").val('')
				}
				//执行重载
				table.reload('t_pfm_type_mgr', {
					page : {
						//重新从第 1 页开始
						curr : 1
					},
					where : {
						pfmTypeName : pfmTypeName,
						areaCode : $("#codeHid").val()
					}
				});
			}
			//########################################查询页面数据方法结束###################################
			$("#selectCityBtn").click(function(){
				var rgCode = $("#areaCode").val();
				if(rgCode == ''){
					layer.msg("请先选择城市!");
					return;
				}
				$('#selectCityDiv').addClass("layui-hide");
				layer.closeAll();
				commsionAddOrEdit($("#areaCode").val());
			});
			$("#cancelBtn").click(function(){
				layer.closeAll();
			});
			//先选择城市
			function selectCity(){
				//显示隐藏表单
				$('#selectCityDiv').removeClass("layui-hide");
				$("#rgCode").val('');
				$("#areaCode").val('');
				layerId = layer.open({
				  	title: "选择城市"
				  	,type: 1
				  	,area:['400px','200px']
					,resize: false
				  	,content: $("#selectCityDiv")
				  	,end: function(index, layero){ 
						$('#selectCityDiv').addClass("layui-hide");
					}
				});
			}
			
			//佣金配置方法
			function commsionAddOrEdit(rgCode){
				var title = '<span style="font-size=20px;color: #1E9FFF;font-weight:700">佣金标准设置</span>';
				$.ajax({
					type: 'post'
					,url: 'getPfmTypeDetail?rgCode=' + rgCode
					,success : function(data) {
						data.rgCode = rgCode;
						commisionAddOrModify(title,data);
					},
					error : function(a, b, c) {
						layer.msg("操作异常，请稍后重试");
					}
				});
			}
			/**
			 * 佣金设置
			 */
			function commisionAddOrModify(title,data){
				$("#rgCode").val("");
				$("#areaName").val("");
				//将输入框值置为空
				$("#commisionSetForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
				//设置表单内容值
				$("#commisionSetForm input").each(
					function(index, domEle) {
						$(this).val("");
						if ($(this).attr("lay-verify") != null
								&& "number" == $(this).attr("lay-verify")) {
							$(this).val("");
						}
						var attr = $(this).attr("name");
						if (data[attr] != null) {
							var val = data[attr];
							if(val == "0"){
								val = "";
							}
							$(this).val(val);
						}
					}
				);
			 	//显示隐藏表单
			 	$('#commisionSetForm').removeClass("layui-hide");
			 	//弹出编辑窗口
			 	var title = '<span style="font-size=20px;color: #1E9FFF;font-weight:700">佣金标准设置</span>';
				layerId = layer.open({
				  	title: title
				  	,type: 1
				  	,area:['560px','400px']
					,resize: false
				  	,content: $("#commisionSetForm")
				  	,end: function(index, layero){ 
						$('#commisionSetForm').addClass("layui-hide");
					}
				});
				//表单提交
				form.on('submit(commisionSetForm)',function(data){
					var bussinessSale = data.field.bussinessSale;
					var businessceSaleUnit = data.field.businessceSaleUnit;
					var bussinessRent = data.field.bussinessRent;
					var bussinessRentUnit = data.field.bussinessRentUnit;
					var houseSaleFirst = data.field.houseSaleFirst;
					var houseSaleFirstUnit = data.field.houseSaleFirstUnit;
					
					if(bussinessSale == "" && businessceSaleUnit == "") {
						layer.msg("商业出售不能都为空！");
						return false;
					}
					if(bussinessRent == "" && bussinessRentUnit == "") {
						layer.msg("商业出租不能都为空！");
						return false;					
					}
					if(houseSaleFirst == "" && houseSaleFirstUnit == "") {
						layer.msg("一手成交不能都为空！");
						return false;
					}
					
					if(bussinessSale != "" && businessceSaleUnit != "") {
						layer.msg("商业出售的比例和固定额度只能二选一！");
						return false;
					}
					if(bussinessRent != "" && bussinessRentUnit != "") {
						layer.msg("商业出租的比例和固定额度只能二选一！");
						return false;					
					}
					if(houseSaleFirst != "" && houseSaleFirstUnit != "") {
						layer.msg("一手成交的比例和固定额度只能二选一！");
						return false;
					}
					
					$.ajax({
						type: 'post'
						,url: 'saveOrEditPfmTypeDetail'
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
			/**
			 * 判断级联控件是否为空
			 */
			function jurgeComIsBlank(comName){
				if($("#" + comName).val() != null && $("#" + comName).val() != "" && $("#" + comName).val() != '0'){
					layer.msg("佣金比例和固定额度只能二选一！");
				}
			}
		</script>
	</body>
</html>