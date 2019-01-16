<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学区管理</title>
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
		<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	</head>
	<body>
		<!-- 查询模块定义 -->
		<div class="">
			<div class="layui-row">
				<div class="toolBar">
					学校名称：
				  	<div class="layui-inline">
						<input class="layui-input" name="schoolName" placeholder="请输入学校名称" id="schoolName" autocomplete="off">
					</div>
					学校地址：
				  	<div class="layui-inline">
						<input class="layui-input" name="schoolAddress" placeholder="请输入学校地址" id="schoolAddress" autocomplete="off">
					</div>
					所属城市：
					<div class="layui-inline">
			  			<div class="layui-input-inline"> 
							<input type="hidden" id="cityCode" name="cityCode">
							<input class="layui-input" id="cityName" name="cityName" placeholder="请选择城市" autocomplete="off">
						</div>
					</div>
					
					
				  	<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
				  		<button class="layui-btn" name="searchBtn" id="searchBtn" onClick="searchData()">查询</button>
				  	</c:if>
				  	<c:if test="${onlyBtnMenus.schoolAdd!='' && onlyBtnMenus.schoolAdd!=null}">
				  		<button class="layui-btn layui-btn-normal" onclick="addOrEditSchoolInfo('新增学校','')">新增学校</button>
				  	</c:if>
				</div>
				
				<div class="layui-col-md6">
					<div class="layui-field-box">
						<!--学校信息表格对象 -->
						<table class="layui-table" id="t_school_block_mgr" lay-filter="t_school_block_mgr"></table>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-field-box">
						<!--周边小区表格对象 -->
						<table class="layui-table" id="t_communit_mgr" lay-filter="t_communit_mgr"></table>
					</div>
				</div>
			</div>
		</div>
		<!--  学校信息编辑弹窗代码 -->
		<form id="schoolMgrForm" name="schoolMgrForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
			<input type="hidden" value="" name="id">
			<div class="layui-form-item">
			  	<div class="layui-inline">
			  		<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>所属城市</label>
		  			<div class="layui-input-inline" style="width:200px;"> 
						<input type="hidden" id="rgCode" name="rgCode">
						<input class="layui-input" placeholder="请选择城市" id="cityId" readonly="readonly" autocomplete="off" 
							required lay-verify="required">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>学校名称</label>
				<div class="layui-input-inline">
					<input id="schoolName" name="schoolName" type="text" required lay-verify="required"
						class="layui-input" placeholder="请输入学校名称" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;"><span style="color:red;">*</span>学校地址</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="schoolAddress" name="schoolAddress" type="text" required lay-verify="required"
						class="layui-input" placeholder="请输入学校地址" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;">对口中学</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="middleSchoolName" name="middleSchoolName" type="text"
						class="layui-input" placeholder="请输入对口中学" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:90px;">学校网址</label>
				<div class="layui-input-inline" style="width:200px;">
					<input id="schoolWebsite" name="schoolWebsite" type="url" lay-verify="url"
						class="layui-input" placeholder="请输入学校网址" autocomplete="off" style="width:200px;"/>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top:15px;">
				 <div class="layui-input-block">
				 	<button id="schoolMgrForm" class="layui-btn" lay-submit="" lay-filter="schoolMgrForm">保存</button>
			   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				 </div>
			</div>
		</form>
		
		
		<!-- 新增小区表单定义 -->
		<form id="communityForm" class="layui-form layui-hide" action="" style="margin-top:20px;">
			<input type="hidden" id="schoolId" value="" name="schoolId">
			<input type="hidden" id="comId" value="" name="comId">
			<div id="areaDiv">
				<div class="layui-form-item">
					<label class="layui-form-label" ><span style="color:red;">*</span>选择小区</label>
					<div class="layui-input-inline">
						<input type="hidden" id="communityId" name ="communityId">
						<input id="communityName" type="text" name="communityName" class="layui-input"
							readonly="readonly" lay-verify="required" autocomplete="off" style="width:230px"/>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">备注说明</label>
					<div class="layui-input-inline">
						<input id="communityRemark" type="text" name="communityRemark" class="layui-input"
							autocomplete="off" style="width:230px"/>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block">
				      	<button class="layui-btn" id="communityForm" lay-filter="communityForm" lay-submit>保存</button>
				      	<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				    </div>
				</div>
			</div>
		</form>
		
		<script type="text/html" id="operationBarOne">
			{{# if(d.communityAdd !='' && d.communityAdd!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="communityAdd" lay-event="communityAdd">新增周边小区</a>
			{{# } }}
			{{# if(d.schoolEdit !='' && d.schoolEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="schoolEdit" lay-event="schoolEdit">修改</a>
			{{# } }}
			{{# if(d.schoolDel !='' && d.schoolDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="schoolDel" lay-event="schoolDel">删除</a>
			{{# } }}
		</script>
		
		<script type="text/html" id="operationBarTwo">
			{{# if(d.communityEdit !='' && d.communityEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="communityEdit" lay-event="communityEdit">修改</a>
			{{# } }}
			{{# if(d.communitDel !='' && d.communitDel!=null){ }}
 				 <a class="layui-btn layui-btn-danger layui-btn-xs" name="communitDel" lay-event="communitDel">删除</a>
			{{# } }}
		</script>
		
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script>
			var form;
			var table;
			//行政区划数据对象
			var cityList = JSON.parse('${cityList}');
			var areaList=JSON.parse('${AreaListJson}');
			var a= new AreaObject(areaList,function(code,name){
				$("#communityName").val(name);
				if(code.length==6){
					code=code.substring(0,4);
				}
				$("#communityId").val(code);
			},3,$("#communityName"));
			$("#communityName").on("change",function(){
				if($(this).val()==""){
					$("#communityId").val("");
				}
			});
			
			var rgCode = new MultiSelection (cityList,function(code,name,level){
				$("#rgCode").val(code);
				$("#cityId").val(name);
				rgCode.hidden();
			},3,$("#cityId"),false);
			
			var city = new MultiSelection (cityList,function(code,name,level){
				$("#cityCode").val(code);
				$("#cityName").val(name);
				rgCode.hidden();
			},3,$("#cityName"),false);
			
			layui.use(['jquery','table','form'], function() {
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				//第一个实例
				table.render({
					id: 't_school_block_mgr'
					,elem : '#t_school_block_mgr'
					,height: 'full-100'
					,url: 'getSchoolPageObj'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'id'
						,title : 'ID'
						,width : 50
						,sort  : true
						,fixed : 'left' 
					}, {
						field : 'schoolName'
						,title : '学校名称'
						,sort  : true
						,templet:function(d){
		    			  	return '<span lay-event="showDetail" style="color:#01AAED;cursor:pointer;">'+d.schoolName+'</span>';
			    		} 
					}, {
						field : 'schoolAddress'
						,title : '学校地址'
						,sort  : true
					}, {
						field : 'rgName'
						,title : '所属城市'
						,sort  : true
					},{
						field : 'middleSchoolName'
						,title : '对口学校'
						,sort  : true
					}, {
						field : 'operator'
						,title : '创建用户'
						,sort  : true
					},{
						fixed: 'right'
						, title: '操作区域'
						, width: 200
						, align:'center'
						, toolbar: '#operationBarOne'
					}]],done: function(res, curr, count){
				    	if(count>0){
				    		showCommunitInfo(res.data[0].id);
						}else{
							showCommunitInfo("");
						}	    	
				  	}
				});
				// 注：tool是工具条事件名
				table.on('tool(t_school_block_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var schoolId = data.id;
					//删除事件
					if (layEvent === 'schoolDel') {
						layer.confirm('确定要删除该学校信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "delSchoolBlockData",
								dataType : "json",
								data : {
									schoolId : schoolId
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
					}  else if(layEvent === 'communityAdd'){//添加周边小区
						addOrEditCommunityInfo('新增周边小区信息',data,schoolId,'');
					} else if (layEvent === 'schoolEdit') { //编辑事件
						addOrEditSchoolInfo('修改学校信息',data);
					} else if(layEvent === 'showDetail'){//点击显示右侧列表数据
						showCommunitInfo(schoolId);
					}
				});
			});
			
			//展示右侧区域表格
			function showCommunitInfo(schoolId){
				//第二个实例
				table.render({
					id: 't_communit_mgr'
					,elem : '#t_communit_mgr'
					,height: 'full-100'
					,url  : 'getCommunityPageObj?schoolId='+schoolId
					,method : 'post'
					,page: true 	//开启分页
					,cols : [[ 		//表格列定义
					{
						field : 'id'
						,title : 'ID'
						,width : 50
						,sort  : true
						,fixed : 'left' 
					}, {
						field : 'communityName'
						,title : '小区名称'
						,sort  : true
					}, {
						field : 'communityRemark'
						,title : '备注说明'
						,sort  : true
					}, {
						field : 'operator'
						,title : '创建用户'
						,sort  : true
					}, {
						fixed: 'right'
						, title: '操作区域'
						, width: 200
						, align:'center'
						, toolbar: '#operationBarTwo'
					}]]
				});
				
				// 监听第二个表格工具条
				table.on('tool(t_communit_mgr)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					//获得当前行 tr的DOM对象
					var tr = obj.tr; 
					var id = data.id;
					//删除事件
					if (layEvent === 'communitDel') {
						layer.confirm('确定要删除该小区信息吗？', function(index) {
							$.ajax({
								type : "post",
								url  : "delSchoolBlockData",
								dataType : "json",
								data : {
									communityId : id
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
					}else if (layEvent === 'communityEdit') { //编辑事件
						addOrEditCommunityInfo('修改周边小区信息',data,data.schoolId,id);
					} 
				});
			}
			/**
			 * 添加或修改学校信息
			 */
			function addOrEditSchoolInfo(title,data){
				//将输入框值置为空
				$("#schoolMgrForm input").each(function (index, domEle) { 
				 	$(this).val("");					 
				});
			 	//显示隐藏表单
			 	$('#schoolMgrForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: title
				  	,type: 1
				  	,area:['400px','380px']
					,resize: false
				  	,content: $("#schoolMgrForm")
				  	,end: function(index, layero){ 
						$('#schoolMgrForm').addClass("layui-hide");
					}
				});
				//设置表单内容值
				$("#schoolMgrForm input").each(
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
				$("#cityId").val(data.rgName);
				//表单提交
				form.on('submit(schoolMgrForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateSchoolInfo?type=1'
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
			 * 添加或修改小区信息
			 */
			function addOrEditCommunityInfo(title,data,schoolId,id){
				//显示隐藏表单
			 	$('#communityForm').removeClass("layui-hide");
				//将输入框值置为空
				$("#communityForm input").each(function (index, domEle) { 
					$(this).val("");
					if ($(this).attr("lay-verify") != null
							&& "number" == $(this).attr("lay-verify")) {
						$(this).val("0");
					}
					var attr = $(this).attr("name");
					if (data[attr] != null) {
						$(this).val(data[attr]);
					}
				});
				layerId = layer.open({
				  	title: title
				  	,type: 1
				  	,area:['400px','240px']
					,resize: false
				  	,content: $("#communityForm")
				  	,end: function(index, layero){ 
						$('#communityForm').addClass("layui-hide");
					}
				});
				//表单提交
				form.on('submit(communityForm)',function(data){
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateSchoolInfo?type=2'
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
				
				//设置表单内容值
				$("#communityForm input").each(
					function(index, domEle) {
						$(this).val("");
						if ($(this).attr("lay-verify") != null
								&& "number" == $(this).attr("lay-verify")) {
							$(this).val("0");
						}
						var attr = $(this).attr("name");
						if (data[attr] != null && data[attr] != "") {
							$(this).val(data[attr]);
							form.render();
						}
					}
				);
				$("#schoolId").val(schoolId);
				$("#comId").val(id);
			}
			
			/**
			 * 查询页面数据函数
			 */
			function searchData() {
				var schoolName = $("#schoolName").val();
				var schoolAddress = $("#schoolAddress").val();
				var cityCode = $("#cityCode").val();
				var cityName = $("#cityName").val();
				if(cityName == ""){
					cityCode = "";
				}
				//执行重载
				table.reload('t_school_block_mgr', {
					page : {
						//重新从第 1 页开始
						curr : 1
					},
					where : {
						schoolName : schoolName,
						schoolAddress : schoolAddress,
						rgCode : cityCode
					}
				});
			}
		</script>
	</body>
</html>