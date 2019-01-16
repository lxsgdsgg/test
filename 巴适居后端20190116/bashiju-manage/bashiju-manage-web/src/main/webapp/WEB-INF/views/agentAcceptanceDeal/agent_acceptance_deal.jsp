<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>经纪人受理页面</title>
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" media="all">	
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
	
	<style type="text/css">
		.lableBlock{
		    color: #666;
		    text-align: left; 
		    float: right;
		    display: block;
		    padding: 9px 15px;
		    min-width: 100px;
		    font-weight: 500;
		    line-height: 20px;
		}
		.layui-form-span {
		    float: left;
		    display: block;
		    padding: 9px 5px;
		    min-width: 80px;
		    width:auto;
		    font-weight: 400;
		    line-height: 20px;
		    text-align: right;
		}
		
		.layui-input, .layui-textarea {
		    width:70px !important;
		    height: 30px !important;
		}
	</style>
</head>
<body>
	<%@include file="/common/common.jsp" %>
	<%@include file="/WEB-INF/views/agentAcceptanceDeal/houseList.jsp" %>
	
	<div class="toolBar layui-form">
		客户名称
	  	<div class="layui-inline">
	    	<input class="layui-input"  placeholder="客户名称" id="clienteleName" autocomplete="off">
	  	</div>
	  	处理状态
	  	<div class="layui-inline">
	  		<select id="status">
	  			<option value="">--选择状态---</option>
	  			<option value="0">未处理</option>
	  			<option value="1">已处理</option>
	  			<option value="2">已失效</option>
	  		</select>
	  	</div>
	  	选择片区
	  	<div class="layui-inline">
	  		<input type="hidden" id="regionId" name="regionId">
	    	<input class="layui-input"  placeholder="选择片区" id="regionName" autocomplete="off">
	  	</div>
	  	选择小区
	  	<div class="layui-inline">
	  		<input type="hidden" id="communityId" name="communityId">
	    	<input class="layui-input"  placeholder="选择小区" id="communityName" autocomplete="off">
	  	</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	</div>
	<table id="t_agent_acceptance" class="lay-table" lay-filter="t_agent_acceptance"></table>
	
	<!--经纪人拒绝表单 -->
	<form id="refuseFrom" class="layui-form layui-hide" action="" style="margin-top:20px;">
		<div class="layui-form-item">
			<label class="layui-form-label"><span style="color:red;">*</span>拒绝原因</label>
			<div class="layui-input-inline">
				<input id="id" name="id" type="hidden"/>
				<textarea id="refuseReason" name="refuseReason" type="textarea" class="layui-textarea" 
					required lay-verify="required" placeholder="请输入拒绝理由" autocomplete="off"
					style="width:320px;"></textarea>
			</div>
		</div>
		<!-- 按钮区域 -->
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" id="saveBtn" lay-submit lay-filter="refuseFrom">保存</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<!--客户委托详细信息-->
	<form id="detailForm" class="layui-form layui-hide" action="" style="margin-top:20px">
		<div id="detailInfo" class="layui-tab-item layui-show">
			<div class="layui-col-md9">
		    	<div class="layui-row">
		    		<div class="layui-col-md6">
		    			<div class="layui-input-inline">
		    			<span class="layui-form-span">所在城市：</span>
		    			<span data-name="areaName" class="lableBlock"></span>
		    			</div>
		    		</div>
		    		<div class="layui-col-md5">
		    			<div class="layui-input-inline">
		    			<span class="layui-form-span">小区名称：</span>
		    			<span data-name="communityName" class="lableBlock"></span>
		    			</div>
		    		</div>
		    	</div>
		    	<div class="layui-row">
		    		<div class="layui-col-md6">
		    			<div class="layui-input-inline">
		    			<span class="layui-form-span">所属片区：</span>
		    			<span data-name="regionName" class="lableBlock"></span>
		    			</div>
		    		</div>
		    		<div class="layui-col-md6">
		    			<div class="layui-input-inline">
		    			<span class="layui-form-span">小区地址：</span>
		    			<span data-name="address" class="lableBlock"></span>
		    			</div>
		    		</div>
		    	</div>
	    	</div>
	    	<div class="layui-col-md3">
	    		<div class="layui-input-inline">
		    		<button onClick="newHouseAdd()" class="layui-btn">新增房源</button>	
		    	</div>
	    	</div>
	    	<hr>
		    <div class="layui-col-md4">
		    	<div class="layui-input-inline">
		    		<input id="transactionTypeId" name="transactionTypeId" type="hidden">
					<span class="layui-form-span">交易类型:</span>				    
				    <span class="lableBlock" data-name="transactionTypeName"></span>
				</div>
				<div class="layui-input-inline">
				   	<span class="layui-form-span">出租类型:</span>				    
				    <span data-name="leaseTypeName" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
					<span class="layui-form-span">出租方式:</span>				    
					<span data-name="joinRentStyleName" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
		    		<input id="buildingsId" name="buildingsId" type="hidden">
				    <span class="layui-form-span">座栋名称:</span>			    
					<span  class="lableBlock" data-name="buildingsName"></span>
				</div>
				<div class="layui-input-inline">
		    		<input id="bulidingUnitId" name="bulidingUnitId" type="hidden">
				    <span class="layui-form-span">所在单元:</span>				    
				    <span class="lableBlock" data-name="buildingsUnitName"></span>
				</div>
		     </div>
		     <div class="layui-col-md4">
		    	<div class="layui-input-inline">
		    		<input id="buildingHouseId" name="buildingHouseId" type="hidden">
					<span class="layui-form-span">房屋编号:</span>				    
				    <span class="lableBlock" data-name="buildingHouseName"></span>
				</div>
				<div class="layui-input-inline">
				   	<span class="layui-form-span">房屋户型:</span>				    
				    <span class="lableBlock" data-name="houseType"></span>
				</div>
				<div class="layui-input-inline">
					<span class="layui-form-span">建筑面积:</span>				    
					<span data-name="buildSpace" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
				    <span class="layui-form-span">楼层描述:</span>				    
					<span data-name="floorDesc" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
				    <span class="layui-form-span">出售价格:</span>				    
				    <span class="lableBlock" data-name="sellingPriceName"></span>
				</div>
		     </div>
		     <div class="layui-col-md4">
		    	<div class="layui-input-inline">
					<span class="layui-form-span">出租价格:</span>				    
				    <span class="lableBlock" data-name="rentPriceName"></span>
				</div>
				<div class="layui-input-inline">
					<input id="payTypeId" name="payTypeId" type="hidden">
				   	<span class="layui-form-span">支付方式:</span>				    
				    <span class="lableBlock" data-name="payType"></span>
				</div>
				<div class="layui-input-inline">
					<span class="layui-form-span">客户电话:</span>				    
					<span data-name="mobile" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
				    <span class="layui-form-span">客户称呼:</span>				    
					<span data-name="clienteleName" class="lableBlock"></span>
				</div>
				<div class="layui-input-inline">
				    <span class="layui-form-span">房屋描述:</span>				    
				    <span class="lableBlock" data-name="descs"></span>
				</div>
		     </div>
	    </div>
	</form>
	<script type="text/html" id="operationBar">
  		{{# if(d.showDetailBtn !='' && d.showDetailBtn !=null){ }}
	  		<a class="layui-btn layui-btn-xs" lay-event="showDetail" name="showDetailBtn">查看详情</a>
  		{{# } }}
		{{# if(d.status != null && d.status != '2'){ }}
			{{# if(d.refuseBtn !='' && d.refuseBtn !=null){ }}
				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="refuseAccept" name="refuseBtn">拒绝</a>
  			{{# } }}
		{{# } }}
	</script>
	<%@include file="/common/common.jsp" %>
	<script type="text/javascript">
		$("#addBtn").find("li.addLi").on("click", function() {
			var houseType = $(this).find("a").attr("data-value");
			var houseTypeName = $(this).find("a").text();
			$("#addForm input[name=houseUsesId]").val(houseType);
			$("#addForm input[name=houseUses]").val(houseTypeName);
			layerId = layer.open({
				type : 1,
				title : "新增房源", //不显示标题
				area : [ '550px', '450px' ],
				content : $('#addForm'),
			});
			$('#addForm').removeClass("hidden");
			$("#addForm select[name]").each(function() {
				$(this).prev().val($(this).find("option:selected").text());
			});
		});

		var regionInfo = JSON.parse('${regionList}');
		var region = new MultiSelection(regionInfo,
				function(code, name, level) {
					if (level == 2) {
						$("#regionName").val(name);
						$("#regionId").val(code);
						region.hidden();
					}
				}, 2, $("#regionName"));
		$("#regionName").on("change", function() {
			if ($(this).val() == "") {
				$("#regionId").val("");
			}
		});

		var community = new MultiSelection(regionInfo, function(code, name,
				level) {
			if (level == 3) {
				$("#communityName").val(name);
				$("#communityId").val(code);
				community.hidden();
			}
		}, 3, $("#communityName"));
		$("#communityName").on("change", function() {
			if ($(this).val() == "") {
				$("#communityId").val("");
			}
		});

		var form;
		var table;
		layui
				.use(
						[ 'table', 'form', 'layer' ],
						function() {
							form = layui.form;
							table = layui.table;
							var $ = layui.$;

							table
									.render({
										id : 't_agent_acceptance',
										elem : '#t_agent_acceptance',
										method : 'post',
										url : 'queryAgentAcceptanceList',
										page : true,
										cols : [ [
												{
													field : 'id',
													title : 'ID',
													width : 80,
													fixed : 'left'
												},
												{
													field : 'statusName',
													title : '处理状态',
													width : 90
												},
												{
													field : 'userName',
													title : '经纪人名称'
												},
												{
													field : 'clienteleName',
													title : '业主称呼'
												},
												{
													field : 'mobile',
													title : '业主电话'
												},
												{
													field : 'areaName',
													title : '城市名称'
												},
												{
													field : 'regionName',
													title : '片区名称'
												},
												{
													field : 'communityName',
													title : '小区名称'
												},
												{
													field : 'buildingsName',
													title : '座栋'
												},
												{
													field : 'buildingsUnitName',
													title : '房屋单元'
												},
												{
													field : 'houseType',
													title : '户型'
												},
												{
													field : 'sellingPriceName',
													title : '出售价格'
												},
												{
													field : 'rentPriceName',
													title : '出租价格'
												},
												{
													field : 'addTime',
													title : '受理时间',
													sort : true,
													templet : '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss")}}</div>'
												}, {
													fixed : 'right',
													title : '操作区域',
													width : 150,
													align : 'center',
													toolbar : '#operationBar'
												} ] ]
									});
							var $ = layui.$, active = {
								reload : function() {
									table.reload('t_agent_acceptance', {//执行重载
										//重新从第 1 页开始
										page : {
											curr : 1
										},
										where : {
											clienteleName : $("#clienteleName")
													.val(),
											status : $("#status").val(),
											regionId : $("#regionId").val(),
											communityId : $("#communityId")
													.val(),
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
							table.on('tool(t_agent_acceptance)', function(obj) {
								//获得当前行数据
								var data = obj.data;
								//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
								var layEvent = obj.event;
								var userId = data.id;
								//删除事件
								if (layEvent === 'showDetail') { //修改用户
									showEntrustDetail(data);
								} else if (layEvent === 'refuseAccept') { //锁定用户
									layer.confirm('确定要拒绝此客户委托吗？', function(
											index) {
										refuseCustEntrust(data);
									})
								}
							});
						});
		//显示委托详细信息
		function showEntrustDetail(data) {
			$('#detailForm').removeClass();
			layerId = layer
					.open({
						title : '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">委托详细页面</span>',
						type : 1,
						area : [ '800px', '400px' ],
						resize : false,
						content : $("#detailForm"),
						end : function(index, layero) {
							$('#detailForm').addClass("layui-hide");
							layer.closeAll();
						}
					});
			var htmlTx = "";
			// 			$("#commnuityName").html(community.name);
			$("#detailInfo span[data-name]").each(function(index, domEle) {
				$(this).html("");
				if ($(this).attr("data-name") == "reginName") {
					$(this).html(data.areaName + "-" + data.reginName);
				} else {
					debugger;
					var attr = $(this).attr("data-name");
					if (data[attr] != null) {
						htmlTx = htmlTx + data[attr];
						$(this).html(data[attr]);
					}
				}
			});
			//alert(htmlTx);
		}

		// 经纪人拒绝客户委托
		function refuseCustEntrust(data) {
			$('#refuseFrom').removeClass("layui-hide");
			$('#id').val(data.id);
			layerId = layer
					.open({
						title : '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">填写理由页面</span>',
						type : 1,
						area : [ '500px', '240px' ],
						resize : false,
						content : $("#refuseFrom"),
						end : function(index, layero) {
							$('#refuseFrom').addClass("layui-hide");
							layer.closeAll();
						}
					});
			form.on('submit(refuseFrom)', function(data) {
				$.ajax({
					type : 'post',
					url : 'refuseAgentAcceptanceEntrustById',
					dataType : 'json',
					data : {
						jsonData : JSON.stringify(data.field)
					},
					success : function(data) {
						if (data.success) {
							layer.close(layerId);
							layer.alert(data.msg, function() {
								location.reload();
							});
						} else {
							layer.msg(data.msg);
						}
					},
					error : function(a, b, c) {
						layer.msg("系统操作异常，请联系管理员！");
					}
				});
				return false;
			});
		}
	</script>
</body>
</html>