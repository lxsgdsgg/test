<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html >
<html>
	<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	<%@include file="/common/common.jsp" %>
	<body>	
		<div class="toolBar layui-form" id="searchArea">
			员工状态：
			<div class="layui-inline">
				<div class="layui-input-inline" style="width:80px;">
					<select id="isLocked" name="isLocked">
						<option value="">--请选择--</option>
						<option value="01">正常</option>
						<option value="0">锁定</option>
					</select>
				</div>
			</div>
			选择部门：
		  	<div class="layui-inline">
	  			<div class="layui-input-inline" style="width:120px;"> 
					<input type="hidden" id="deptCode" name="deptCode">
					<input class="layui-input" id="deptName" name="deptName" placeholder="请选择部门" readonly="readonly" autocomplete="off">
				</div>
			</div>
			选择角色：
		  	<div class="layui-inline" style="width:120px;">
				<select id="roleCode" name="roleCode">
					<option value="">请选择角色</option>
					<c:forEach var="role" items="${roleInfo}">
						<option value="${role.id}">${role.name}</option>
					</c:forEach>
				</select>
			</div>
			员工名称：
		  	<div class="layui-inline">
			  	<div class="layui-input-inline"> 
					<input class="layui-input" name="userName" placeholder="请输入员工名称" id="userName" autocomplete="off">
				</div>
			</div>
			员工编码：
		  	<div class="layui-inline">
			  	<div class="layui-input-inline"> 
					<input class="layui-input" type="text" name="employeeNum" placeholder="请输入员工编码" id="employeeNum" autocomplete="off">
				</div>
			</div>
			员工电话：
		  	<div class="layui-inline">
			  	<div class="layui-input-inline"> 
					<input id="mobile" type="number" lay-verify="phone" class="layui-input" name="mobile" placeholder="请输入员工电话" autocomplete="off">
				</div>
			</div>
			
		  	<c:if test="${onlyBtnMenus.searchBtn != '' && onlyBtnMenus.searchBtn != null}">
		  		<button class="layui-btn" name="searchBtn" id="searchBtn" data-type="reload" >查询</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.clearBtn!='' && onlyBtnMenus.clearBtn!=null}">
		  		<button class="layui-btn layui-btn-normal" onclick="clearData()">清空</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.userAdd !='' && onlyBtnMenus.userAdd != null}">
		  		<button class="layui-btn" onclick="addOrEditUserInfo('添加用户','')">新增</button>
		  	</c:if>
		</div>
	  	<table class="layui-table" id="t_user_info" lay-filter="t_user_info"></table>
	  	
	  	
	  	<!-- 新增或修改用户表单 -->
	  	<form id="userAddOrEditForm" class="layui-form layui-hide" action="" style="margin-top:20px;">
	  		<input type="hidden" id="id" name="id"/>
	  		<input type="hidden" id="agentDetailId" name="agentDetailId"/>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>员工名称</label>
					<div class="layui-input-inline">
						<input name="realName" type="text" class="layui-input" placeholder="请输入员工名称" autocomplete="off" required lay-verify="required"/>
					</div>
	  			</div>
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>行政区划</label>
					<div class="layui-input-inline">
						<input id="areaCode" name="areaCode" type="hidden">
						<input id="areaName" name="areaName" type="text" class="layui-input" readonly="readonly" placeholder="请选择区划" autocomplete="off" required lay-verify="required"/>
					</div>
	  			</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>员工性别</label>
					<div class="layui-input-inline">
						<select id="sex" name="sex" required lay-verify="required">
							<option value="">--请选择--</option>
							<c:forEach var="sex" items="${sexInfo}">
								<option value="${sex.key}">${sex.value}</option>
							</c:forEach>
						</select>
					</div>
	  			</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>手机号码</label>
					<div class="layui-input-inline">
						<input id="mobile" name="mobile" required lay-verify="phone" autocomplete="off" class="layui-input" placeholder="请输入手机号码"/>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
		  			<label class="layui-form-label">员工编号</label>
					<div class="layui-input-inline">
						<input name="employee_num" type="text" class="layui-input" placeholder="请输入员工编号"/>
					</div>
		  		</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>所属部门</label>
					<div class="layui-input-inline">
						<div class="layui-input-inline"> 
							<input type="hidden" id="deptId" name="deptId">
							<input id="deptIpt" name="deptName" required lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input" placeholder="请选择所属部门"/>
						</div>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>用户角色</label>
					<div class="layui-input-inline">
						<input type="hidden" id="roleIptCode">
						<select id="roleId" name="roleId">
							<option value="">--请选择--</option>
							<c:forEach var="role" items="${roleInfo}">
								<option value="${role.id}">${role.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label">出生日期</label>
					<div class="layui-input-inline">
						<input id="birthDate" name="birthDate" type="text" readonly="readonly" lay-verify="birthDate" autocomplete="off" class="layui-input" placeholder="请选择出生日期"/>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
				<div class="layui-inline">
		  			<label class="layui-form-label">入职时间</label>
					<div class="layui-input-inline">
						<input id="entryDate" name="entryDate" type="text" lay-verify="entryDate" readonly="readonly" autocomplete="off" class="layui-input" placeholder="请选择入职时间"/>
					</div>
				</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label">身份证号</label>
					<div class="layui-input-inline">
						<input id="idCode" name="idCode" lay-verify="idCode" class="layui-input" placeholder="请输入身份证号"/>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
				<div class="layui-inline">
		  			<label class="layui-form-label">电子邮箱</label>
					<div class="layui-input-inline">
						<input id="email" name="email" class="layui-input" autocomplete="off" placeholder="请输入电子邮箱"/>
					</div>
				</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label">用户职位</label>
					<div class="layui-input-inline">
						<input type="hidden" id="postCode">
						<select id="postId" name="postId">
							<option value="">--请选择--</option>
							<c:forEach var="post" items="${positionInfo}">
								<option value="${post.id}">${post.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
				<div class="layui-inline">
		  			<label class="layui-form-label">介绍人</label>
					<div class="layui-input-inline">
						<input type="hidden" id="code" name="code">
						<input id="refereeName" name="refereeName" lay-verify="refereeName" readonly="readonly" class="layui-input" autocomplete="off" placeholder="请选择介绍人"/>
					</div>
				</div>
				<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>排序编号</label>
					<div class="layui-input-inline">
						<input id="sortNo" name="sortNo" lay-verify="sortNo" required lay-verify="required" class="layui-input" placeholder="请输入排序编号" autocomplete="off"/>
					</div>
				</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
		  			<label class="layui-form-label">生日弹出</label>
		  			<div class="layui-input-inline">
			  			<select id="birthWindowFlag" name="birthWindowFlag" lay-verify="birthWindowFlag">
							<option value="">--请选择--</option>
							<c:forEach var="birthdayOpen" items="${birthdayOpen}">
								<option value="${birthdayOpen.key}">${birthdayOpen.value}</option>
							</c:forEach>
						</select>
					</div>
		  		</div>
	  			<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>是否经纪人</label>
	  				<div class="layui-input-inline">
			  			<select id="isAgent" name="isAgent" lay-verify="isAgent" lay-filter="isAgent" required lay-verify="required">
							<option value="">--请选择--</option>
							<c:forEach var="isAgent" items="${isAgent}">
								<option value="${isAgent.key}">${isAgent.value}</option>
							</c:forEach>
						</select>
					</div>
		  		</div>
	  		</div>
	  		<div id="isAgentDiv" class="layui-form-item layui-hide">
		  		<div class="layui-form-item">
		  			<div class="layui-inline">
			  			<label class="layui-form-label"><span style="color:red;">*</span>外网同步</label>
						<div class="layui-input-inline">
							<select id="syncFlag" name="syncFlag">
								<option value="">--请选择--</option>
								<c:forEach var="syncFlag" items="${syncFlag}">
									<option value="${syncFlag.key}">${syncFlag.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>
		  			<div class="layui-inline">
			  			<label class="layui-form-label"><span style="color:red;">*</span>热门经纪人</label>
		  				<div class="layui-input-inline">
				  			<select id="hotAgentFlag" name="hotAgentFlag" lay-verify="hotAgentFlag">
								<option value="">--请选择--</option>
								<c:forEach var="isHotInfo" items="${isHotInfo}">
									<option value="${isHotInfo.key}">${isHotInfo.value}</option>
								</c:forEach>
							</select>
						</div>
			  		</div>
			  	</div>
			  	<div class="layui-form-item">
		  			<div class="layui-inline">
			  			<label class="layui-form-label"><span style="color:red;">*</span>主营片区</label>
		  				<div class="layui-input-inline">
				  			<input type="hidden" value ="" name="mainRegions">
							<select id="mainRegionsIds" name="mainRegionsIds" xm-select-type="2" 
								xm-select="select1" xm-select-max="5"></select> 
						</div>
			  		</div>
		  			<div class="layui-inline">
			  			<label class="layui-form-label"><span style="color:red;">*</span>主营小区</label>
						<div class="layui-input-inline">
							<input type="hidden" value="" name="mainCommunity">
							<select id="mainCommunityIds" name="mainCommunityIds" xm-select-type="2" 
								xm-select="select2" xm-select-max="5"></select> 
						</div>
					</div>
			  	</div>
		  	</div>
		  	<div class="layui-form-item">
	  			<div class="layui-inline">
		  			<label class="layui-form-label"><span style="color:red;">*</span>电话号码</label>
	  				<div class="layui-input-inline">
			  			<input type="number" value ="" id="telPhone" name="telPhone" required lay-verify="required"
			  				class="layui-input" placeholder="请输入电话号码" autocomplete="off">
					</div>
		  		</div>
	  		</div>
	  		<!-- 按钮区域 -->
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" id="saveBtn" lay-submit lay-filter="userAddOrEditForm">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
	  		
	  	</form>
	  	
	  	<!-- 重置密码表单 -->
	  	<form id="resetPwsForm" class="layui-form layui-hide" action="" style="margin-top:20px;">
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>员工姓名</label>
	  				<div class="layui-input-inline">
	  					<input type="hidden" id="userId" name="userId"/>
	  					<input id="realName" name="userName" type="text" class="layui-input" readonly="readonly"/>
	  				</div>
	  			</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>新密码</label>
	  				<div class="layui-input-inline">
	  					<input id="newPassword" name="newPassword" type="password" class="layui-input" required lay-verify="required" placeholder="请输入新密码" />
	  				</div>
	  			</div>
	  		</div>
	  		<div class="layui-form-item">
	  			<div class="layui-inline">
	  				<label class="layui-form-label"><span style="color:red;">*</span>确认密码</label>
	  				<div class="layui-input-inline">
	  					<input id="confirmPassword" name="confirmPassword" type="password" placeholder="请确认密码" autocomplete="off" required lay-verify="required" class="layui-input"/>
	  				</div>
	  			</div>
	  		</div>
	  		<!-- 按钮区域 -->
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" id="resetPwsbtn" lay-submit lay-filter="resetPwsForm">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
	  	</form>
	  	
	  	<!-- 添加表格中的操作栏 -->
	  	<script type="text/html" id="operationBar">
			{{# if(d.userEdit !='' && d.userEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="userEdit" lay-event="userEdit">编辑</a>
			{{# } }}
			{{# if(d.permissionSet !='' && d.permissionSet!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="permissionSet" lay-event="permissionSet">设置权限</a>
			{{# } }}
			{{# if(d.moveUserInfo !='' && d.moveUserInfo!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="moveUserInfo" lay-event="moveUserInfo">信息转移</a>
			{{# } }}
			{{# if(d.dataUpdate !='' && d.dataUpdate!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="dataUpdate" lay-event="dataUpdate">数据更新</a>
			{{# } }}
			{{# if(d.resetPsw !='' && d.resetPsw!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="resetPsw" lay-event="resetPsw">重置密码</a>
			{{# } }}
			{{# if(d.lockedUser !='' && d.lockedUser!=null){ }}
					{{# if(d.status != null && d.status != '' && d.status == '0'){ }}
 						 <a class="layui-btn layui-btn-danger layui-btn-xs" name="lockedUser" lay-event="lockedUser">解锁</a>
					{{# }else{ }}
						<a class="layui-btn layui-btn-danger layui-btn-xs" name="lockedUser" lay-event="lockedUser">锁定</a>
					{{# } }}
			{{# } }}
		</script>
		<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
		<script type="text/javascript">
			var path = "<%=request.getContextPath()%>";
			var areaList = JSON.parse('${areaList}');
			var deptInfo = JSON.parse('${deptInfo}');
			var referInfo = JSON.parse('${referInfo}');
			var regionInfo = JSON.parse('${regionList}');
			var communityInfo = JSON.parse('${communityList}');
			
			//部门选择控件
			var dept = new MultiSelection(deptInfo,function(code,name){
				$("#deptName").val(name);
				$("#deptCode").val(code);
				dept.hidden();
			},null,$("#deptName"),true);
			$("#deptName").on("change",function(){
				if($(this).val()==""){
					$("#deptCode").val("");
				}
			});
			
			var rgArea = new MultiSelection(areaList,function(code,name){
				$("#areaName").val(name);
				$("#areaCode").val(code);
				rgArea.hidden();
			},3,$("#areaName"));
			$("#areaName").on("change",function(){
				if($(this).val()==""){
					$("#areaCode").val("");
				}
			});
			
			var deptIpt = new MultiSelection(deptInfo,function(code,name){
				$("#deptIpt").val(name);
				$("#deptId").val(code);
				deptIpt.hidden();
			},null,$("#deptIpt"));
			$("#deptIpt").on("change",function(){
				if($(this).val()==""){
					$("#deptId").val("");
				}
			});
			//介绍人控件
			var refer = new MultiSelection(referInfo,function(code,name,level){
// 				if(level == referMaxLevel){
					$("#refereeName").val(name);
					$("#code").val(code);
					refer.hidden();
// 				}
			},null,$("#refereeName"),true);
			$("#refereeName").on("change",function(){
				if($(this).val()==""){
					$("#code").val("");
				}
			});
			
			///定义拓展模块的路径
			layui.config({
				base: '${request.getContextPath()}/src/common/js/'
			}).extend({
				formSelects: 'formSelects-v3'
			});
			
			var form;
			var table;
			var formSelects;
			layui.use(['table','form','laydate','formSelects'], function() {
				var $ = layui.$;
				table = layui.table;
				form = layui.form;
				var laydate = layui.laydate;
				formSelects = layui.formSelects;
				laydate.render({
					 elem: '#entryDate' //指定元素
				});
				laydate.render({
					 elem: '#birthDate' //指定元素
				});
				table.render({
					id: 't_user_info'
					,elem : '#t_user_info'
					,url  : 'getUserAllDataWithPage'
					,method : 'post'
					,page : true 	//开启分页
					,cols : [[ 		//表格列定义
					{field : 'id',title: 'ID',width:70,sort: true,fixed : 'left'}
					,{field : 'statusName',title: '状态',width: 70,sort: true}
					,{field : 'sortNo',title : '排序',width: 70,sort  : true}
					,{field : 'realName',title: '员工名称',sort: true}
					,{field : 'deptName',title: '所在部门',sort: true}
					,{field : 'sexName',title: '性别',width: 70,sort: true}
					,{field : 'roleName',title: '角色名称',sort: false}
					,{field : 'birthDate',title: '出生日期',sort: true,templet: '<div>{{ common.toDateString(d.birthDate,"yyyy-MM-dd")}}</div>'}
					,{field : 'employee_num',title: '员工编号',sort: true}
					,{field : 'mobile',title: '电话',sort: true}
					,{field : 'entryDate',title: '入职日期',sort: true,templet: '<div>{{ common.toDateString(d.entryDate,"yyyy-MM-dd")}}</div>'}
					,{field : 'postName',title: '职务',sort: true}
					,{fixed: 'right',title: '操作区域', width: 400,align:'center',toolbar: '#operationBar'}
					]]
				});
				var $ = layui.$, active = {
					reload : function() {
						table.reload('t_user_info', {//执行重载
							//重新从第 1 页开始
							page : {curr : 1 },
							where : {    
								deptId : $("#deptCode").val(),
								roleId: $("#roleCode").val(),
								userName: $("#userName").val(),
								mobile: $("#mobile").val(),
								employeeNum: $("#employeeNum").val(),
								isLocked: $("#isLocked").val()
							}
						});
					}
				};
				//查询事件
				$('#searchBtn').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
	
				// 监听工具条
				// 注：tool是工具条事件名，t_transfer_proc_mgr是table原始容器的属性 lay-filter="对应的值"
				table.on('tool(t_user_info)', function(obj) {
					//获得当前行数据
					var data = obj.data;
					//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
					var layEvent = obj.event; 
					var userId = data.id;
					//删除事件
					if (layEvent === 'userEdit') { //修改用户
						addOrEditUserInfo('修改用户',data);
					}else if (layEvent === 'permissionSet') { //权限设置
						if(data.roleId == ""){
							layer.msg("该用户未挂接角色信息！");
							return false;
						}
						permissionSet(data);
					}else if (layEvent === 'moveUserInfo') { //数据转移
						moveUserInfo('用户信息转移',data);
					}else if (layEvent === 'dataUpdate') { //数据更新
						dataUpdate('数据更新',data);
					}else if (layEvent === 'resetPsw') { //重置密码
						resetUserPsw('重置密码',data);
					}else if (layEvent === 'lockedUser') { //锁定用户
						if(data.status == '01'){
							layer.confirm('确定要锁定用户吗？', function(index) {
								lockedUser(data);
							});
						}else{
							layer.confirm('确定要解锁用户吗？', function(index) {
								lockedUser(data);
							});
						}
					}  
				});
				/* 是否经纪人监听事件 */
				form.on('select(isAgent)',function(data){
					if(data.value == 1){
						$("#isAgentDiv").removeClass("layui-hide");
					}else{
						$("#isAgentDiv").addClass("layui-hide");
					}
				});
			});
			
			//添加或修改用户
			function addOrEditUserInfo(title,oriData){
				$('#userAddOrEditForm').removeClass("layui-hide");
				if(null != oriData && oriData.isAgent == "1"){
					$("#isAgentDiv").removeClass("layui-hide");
				}else{
					$("#isAgentDiv").addClass("layui-hide");
				}
				//将输入框值置为空后重新赋值
				$("#userAddOrEditForm input").each(function (index, domEle) { 
					$(this).val("");
					if($(this).attr("lay-verify")!=null && "number" == $(this).attr("lay-verify")){
						$(this).val('');
				 	}			
					var attr=$(this).attr("name");
				  	if(oriData[attr]!=null){
				  		if(attr.indexOf('Date') != -1){
				  			$(this).val(common.toDateString(oriData[attr],"yyyy-MM-dd"));
				  		}else{
							$(this).val(oriData[attr]);
				  		}
				  	}
				});
				$("#userAddOrEditForm select").each(function (index, domEle) { 
					$(this).val('');
					if($(this).attr("lay-verify")!=null && "number" == $(this).attr("lay-verify")){
						$(this).val('');
				 	}
					var attr=$(this).attr("name");
					if(oriData[attr]!=null){
						$(this).val(oriData[attr]);
					}
				});
				form.render();
				layerId = layer.open({
				  	title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">' + title +'</span>'
				  	,type: 1
				  	,area:['670px','520px']
					,resize: false
				  	,content: $("#userAddOrEditForm")
				  	,end: function(index, layero){ 
						$('#userAddOrEditForm').addClass("layui-hide");
					}
				});
				form.on('submit(userAddOrEditForm)',function(data){
					//得到条件多选下拉框中文
				  	var mainRegionsNameTemp = formSelects.value('select1', 'nameStr');
					var mainRegionsValueTemp = formSelects.value('select1', 'val').join(",");
				  	var mainCommunityNameTemp = formSelects.value('select2', 'nameStr');
					var mainCommunityValueTemp = formSelects.value('select2', 'val').join(",");
					
					data.field.mainRegions = mainRegionsNameTemp;
					data.field.mainRegionsIds = mainRegionsValueTemp;
					data.field.mainCommunity = mainCommunityNameTemp;
					data.field.mainCommunityIds = mainCommunityValueTemp;
					$.ajax({
						type: 'post'
						,url: 'saveOrUpdateUserInfo'
						,dataType: 'json'
						,data: {
							jsonData : JSON.stringify(data.field)
						}
						,success: function(data){
							if(data.success){
								layer.alert(data.msg,function(){
									layer.close(layerId);
									location.reload();
								});
								layer.msg(data.msg);
								layer.close(layerId);
								location.reload();
							}else{
								layer.msg(data.msg);
							}
						}
						,error:function(a,b,c){
							layer.msg("系统操作异常，请联系管理员！");
						}
					});
					return false;
				});
				showCombobox(oriData);
			}
			//展示下拉
			function showCombobox(data){
				var mainCommunityArr = [];
				var mainRegionsArr = [];
				var mainCommunityTemp = data.mainCommunityIds;
				var mainRegionsTemp = data.mainRegionsIds;
				if(data != '' && mainCommunityTemp != undefined){
					mainCommunityArr = mainCommunityTemp.split(",");
				}
				if(data != '' && mainRegionsTemp != undefined){
					mainRegionsArr = mainRegionsTemp.split(",");
				}
				var mainCommunity = JSON.parse('${communityList}');
				var arrLen = mainCommunityArr.length;
				var html = '<option value="">请选择...</option>';
				for(var i = 0;i < mainCommunity.length;i++){ 
					var oneObj = mainCommunity[i];
					if( data != '' && mainCommunityTemp != undefined){
						for(var m = 0;m < arrLen;m++){
							if(oneObj.id == mainCommunityArr[m]){
								html +='<option value="'+oneObj.id + '" selected>'+oneObj.name+'</option>';
								break;
							}
							if(oneObj.id != mainCommunityArr[m] && m == arrLen-1){
								html +='<option value="' + oneObj.id + '">' + oneObj.name +'</option>';
							}
						}
					}else{
						html +='<option value="' + oneObj.id + '">' + oneObj.name +'</option>';
					}
				}
				var mainRegion = JSON.parse('${regionList}');
				var regionLen = mainRegionsArr.length;
				var htmlObj = '<option value="">请选择</option>';
				for(var i = 0;i < mainRegion.length;i++){ 
					var oneObj = mainRegion[i];
					if( data != '' && mainRegionsTemp != undefined){
						for(var m = 0;m < regionLen;m++){
							if(oneObj.id == mainRegionsArr[m]){
								htmlObj +='<option value="'+oneObj.id + '" selected>'+oneObj.name+'</option>';
								break;
							}
							if(oneObj.id != mainRegionsArr[m] && m == regionLen-1){
								htmlObj +='<option value="' + oneObj.id + '">' + oneObj.name +'</option>';
							}
						}
					}else{
						htmlObj +='<option value="' + oneObj.id + '">' + oneObj.name +'</option>';
					}
				}
// 				alert(html);
// 				alert(htmlObj);
				$("#mainCommunityIds").html(html);
				$("#mainRegionsIds").html(htmlObj);
				//动态赋值给“条件(mainCommunityArr,mainRegionsArr)”
				formSelects.value('select1',mainRegionsArr);
				formSelects.value('select2',mainCommunityArr);
				form.render('select');
			}
			//权限设置--这里直接调用【角色授权】功能
			//此处适用IFrame弹出
			function permissionSet(data){
				var title = '角色授权管理 <span style="margin-left: 24%;color: orange;font-size: 18px;">角色名称：【' 
						+ data.roleName + '】</span>';
				var option = {
				        type: 2 //此处以iframe举例
				        ,id:"rolePermissionIFrame"
				        ,title: title
				        ,area: ['90%', '90%']
				        ,shade: 0.5
				        ,resize:false//不允许拉伸
				        ,maxmin: false
				        ,offset: 'auto' 
// 				        ,content: '/manage/permission/permissionPage?roleId='+data.roleId+'&userId='+data.id
				        ,content: '/manage/permission/permission?roleId='+data.roleId+'&userId='+data.id
				        ,zIndex: layer.zIndex //重点1
				        ,success: function(layero){
				          layer.setTop(layero); //重点2
				        }
				      };
		    	common.windowLayerOpen(option);
			}
			
			//数据转移
			function moveUserInfo(title,data){
				var userDataTransfer = {
			        type: 2
			        ,id: "moveUserIFrame"
			        ,title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">' + title +'</span>'
			        ,area: ['700px','600px']
			      	,shade: 0.5
			        ,resize: false
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'getUserDataTransferModel?userId='+data.id
			        ,zIndex: layer.zIndex
			        ,success: function(layero,zIndex){
			        	layer.setTop(layero);
			        }
			    };
				common.windowLayerOpen(userDataTransfer);
			}
			
			//数据更新
			function dataUpdate(title,data){
				var userDataUpdate = {
			        type: 2
			        ,id: "userDataUpdateIFrame"
			        ,title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">' + title +'</span>'
			        	+ '<span style="color:red;font-size:18px;font-weight:700;">'
			        	+ '【此操作只适用于员工调店后的门店数据更新】</span>'
			        ,area: ['700px','600px']
			      	,shade: 0.5
			        ,resize: false
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'getUserDataUpdateModel?userId='+data.id
			        ,zIndex: layer.zIndex
			        ,success: function(layero,zIndex){
			        	layer.setTop(layero);
			        }
			    };
				common.windowLayerOpen(userDataUpdate);
			}
			//重置密码
			function resetUserPsw(title,oriData){
				$("#userId").val(oriData.id);
				$("#realName").val(oriData.realName);
				$('#resetPwsForm').removeClass("layui-hide");
				layerId = layer.open({
				  	title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">' + title +'</span>'
				  	,type: 1
				  	,area:['360px','300px']
					,resize: false
				  	,content: $("#resetPwsForm")
				  	,end: function(index, layero){ 
						$('#resetPwsForm').addClass("layui-hide");
					}
				});
				form.on('submit(resetPwsForm)',function(data){
					var newPassword = $("#newPassword").val();
					var confirmPassword = $("#confirmPassword").val();
					if(newPassword != confirmPassword){
						layer.msg("两次输入的密码不一致！");
						return false;
					}
					$.ajax({
						type: 'post'
						,url: 'resetUserPassword'
						,dataType: 'json'
						,data: {
							jsonData : JSON.stringify(data.field)
						}
						,success: function(data){
							if(data.success){
								layer.close(layerId);
								layer.alert(data.msg,function(){
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
					return false;
				});
			}
			
			/* 锁定/解锁用户 */
			function lockedUser(data){
				var url;
				if(data.status == '01'){
					url = "lockedUserInfo?userId=" + data.id;
				}else{
					url = "unLockedUserInfor?userId=" + data.id;
				}
				$.ajax({
					type : "post",
					url  : url,
					success : function(data) {
						if (data.success) {
							layer.msg(data.msg);
							location.reload();
						} else {
							layer.msg(data.msg);
						}
					},
					error : function(a, b, c) {
						layer.msg("操作异常，请稍后重试");
					}
				});
			}
			//清空按钮事件
			function clearData(){
				$("#searchArea input").each(function (index, domEle) { 
					$(this).val("");
						if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
							$(this).val("0");
					 	}			
					});
				$("#searchArea select").each(function (index, domEle) { 
					$(this).val("");
					if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
						$(this).val('');
				 	}
				});
			}
		</script>
	</body>
</html>