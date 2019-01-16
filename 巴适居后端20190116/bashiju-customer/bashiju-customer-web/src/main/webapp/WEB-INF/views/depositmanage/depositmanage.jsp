<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
	
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
	<title>定金管理</title>
</head>
<body>
	<div class="toolBar layui-form" id="searchArea">
		客源类型
	   	<div class="layui-inline">
		    <div class="layui-input-inline">
				<select id="custType" name="custType">
					<option value="">--请选择--</option>
					<option value="">全部</option>
					<c:forEach var="cust" items="${custList}">
						<option value="${cust.value}">${cust.name}</option>
					</c:forEach>
				</select>
	    	</div>
 		</div>
		定金状态
	   	<div class="layui-inline">
		    <div class="layui-input-inline">
				<select id="depositStatus" name="depositStatus">
					<option value="">--请选择--</option>
					<c:forEach var="status" items="${depositStatus}">
						<option value="${status.value}">${status.name}</option>
					</c:forEach>
				</select>
	    	</div>
 		</div>
		经办人
	   	<div class="layui-inline">
		    <div class="layui-input-inline">
				<input id="trusteesId" type="hidden" class="layui-input" placeholder="请选择经办人" autocomplete="off"/>
				<input id=trustees name="trustees" class="layui-input" placeholder="请选择经办人" autocomplete="off"/>
	    	</div>
 		</div>
		日期范围
	   	<div class="layui-inline">
		    <div class="layui-input-inline">
				<input id="depositTime" name="depositTime" class="layui-input" placeholder="请选择下定时间段" autocomplete="off"/>
	    	</div>
 		</div>
 		
 		<c:if test="${onlyBtnMenus.searchBtn != '' && onlyBtnMenus.searchBtn != null}">
		  	<button class="layui-btn" name="searchBtn" id="searchBtn" onclick="searchData()">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clearBtn!='' && onlyBtnMenus.clearBtn!=null}">
	  		<button class="layui-btn layui-btn-normal" onclick="clearData()">清空</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.exportCurBtn!='' && onlyBtnMenus.exportCurBtn!=null}">
	  		<button class="layui-btn" onclick="exportTableToExcel()">导出当前页</button>
	  	</c:if>
	</div>
	<div class="excelTable">
		<table id="t_deposit_mgr" lay-filter="t_deposit_mgr" class="layui-table"></table>
	</div>
	<form id="payOffForm" name="payOffForm" class="layui-form layui-hide" action="" style="margin-top:10px;">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:120px;"><span style="color:red">*</span>支出协议数书编号</label>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input id="expenditureNo" name="expenditureNo" class="layui-input" placeholder="请输入支出协议数书编号" 
						autocomplete="off" required lay-verify="required">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label" style="width:120px;">支出备注</label>
			<div class="layui-inline">
				<div class="layui-input-inline" >
					<input id="expenditureRemark" name="expenditureRemark" class="layui-input" placeholder="请输入支出备注" 
						autocomplete="off" required lay-verify="required">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			 <div class="layui-input-block">
			 	<button id="saveDepositBtn" class="layui-btn" lay-submit="" lay-filter="payOffForm">提交</button>
		   		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			 </div>
		</div>
	</form>
	<!-- 下定金 -->
	<div class="depositModel" style="display:none;">
		<form id="depositForm" class="layui-form deposit-form" style="padding:10px;">
			<input type="hidden" id="id" name="id">
			<div class="layui-row">
				<div class="layui-col-md5">
					<div>客源编号：<span class="demandId"></span></div>
					<input type="hidden" class="deposit-demandId" name="demandId" value="">
					<div>客户姓名：
						<span class="custName"></span>
						<span class="relateTypeName"></span>
					</div>
					
					<div style="color:red;" class="selectHouse">选择房源</div>
					<input type="text" class="dshhId" name="shhId" value="">
					<input type="text" class="dhouseId" name="houseId" value="">
				</div>
				<div class="layui-col-md7">
					<div class="layui-form-item">
						<label class="layui-form-label">下定金额</label>
					    <div class="layui-input-block ">
					      <input type="number" placeholder="请输入下定金额" name="price" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">下定日期</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请选择下定日期" readonly="readonly" name="entrustTime" class="layui-input deposittime">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">经办人</label>
					    <div class="layui-input-block ">
					      <input type="hidden"  name="trusteesId" id="trusteesdepartmentcode" class="layui-input" value="">
					      <input type="text" placeholder="请选择经办人" readonly="readonly" name="trustees" id="trusteesdepartment" value="" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">票据编号</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入票据编号" name="billNo" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">预计成交金额</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请输入预计成交金额" name="maybeDealPrice" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">预计成交日期</label>
					    <div class="layui-input-block ">
					      <input type="text" placeholder="请选择预计成交日期" readonly="readonly" name="maybeDealDate" class="layui-input dealtime">
					    </div>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" id="remark" name="remark" class="layui-textarea dremark"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveDeposit();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			      
			    </div>
			</div>
		</form>
	</div>
	
	<!--选择房源-->
	<div class="selectHouseModel" style="display:none;">
		<div class="layui-form">
			<div class="layui-form-item show-line">
			    <div class="layui-input-block ">
			      <input type="hidden" id="mddepartmentcode" class="layui-input">
			      <input type="text" placeholder="门店" name="xwh" id="mddepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
				<label class="layui-form-label">面积区间</label>
			    <div class="layui-input-block ">
			      <select name="pictureType" lay-filter="">
			        <option value="1">200-500</option>
				  	<option value="2">500-1000</option>
				  	<option value="3">1000-1500</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block ">
			      <input type="hidden" id="selectAreacode" class="layui-input">
			      <input type="text" placeholder="选择区域" id="selectArea" name="" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <input type="text" placeholder="坐栋" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <input type="text" placeholder="房源编号，电话，门牌号，委托编号" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item show-line">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="">查询</button>
			      
			    </div>
			</div>
		</div>
		<table id="selectHouse" lay-filter="selectHouse" ></table>
	</div>
	
	<%@include file="/common/common.jsp" %>
	<script type="text/html" id="operationBar">
		{{# if(d.status != null && d.status != '' && d.status == 0){ }}
			{{# if(d.confirmBtn !='' && d.confirmBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="confirmBtn" lay-event="confirmBtn">确定</a>
			{{# } }}
			{{# if(d.depositEdit !='' && d.depositEdit!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="depositEdit" lay-event="depositEdit">修改</a>
			{{# } }}
 			{{# if(d.rebackBtn !='' && d.rebackBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-danger" name="rebackBtn" lay-event="rebackBtn">驳回</a>
			{{# } }}
		{{# }else if(d.status != null && d.status != '' && d.status == 1){ }}
			{{# if(d.retreatBtn !='' && d.retreatBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="retreatBtn" lay-event="retreatBtn">退定</a>
			{{# } }}
			{{# if(d.payOffBtn !='' && d.payOffBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs" name="payOffBtn" lay-event="payOffBtn">支出</a>
			{{# } }}
			{{# if(d.custDealBtn !='' && d.custDealBtn!=null){ }}
  				<a class="layui-btn layui-btn-xs layui-btn-normal" name="custDealBtn" lay-event="custDealBtn">成交</a>
			{{# } }}
		{{# } }}
	</script>
	
	<script type="text/javascript">
		var table;
		var form;
		var laydate;
		
		//选人员或部门
		var depDatas = JSON.parse('${depts}');
		//选定金经办人
		var ms = new MultiSelection (depDatas,function(code,name,level){
			if(code!="0"){
				$("#trusteesdepartment").val(name);
				$("#trusteesdepartmentcode").val(code);
			}	
			ms.hidden();
		},null,$("#trusteesdepartment"),true);
		
		var trustees = new MultiSelection (depDatas,function(code,name,level){
			if(code!="0"){
				$("#trustees").val(name);
				$("#trusteesId").val(code);
			}	
			trustees.hidden();
		},null,$("#trustees"),true); 
		
		layui.use(['laydate','form','table'],function(){
			table = layui.table;
			form = layui.form;
			laydate = layui.laydate;
			
			laydate.render({
			    elem: '#depositTime'
			    ,type: 'datetime'
		    	,range: true
		  	});
		 
			laydate.render({
				elem: '.deposittime' //指定元素
			});
			laydate.render({
				elem: '.dealtime' //指定元素
			});
			table.render({
				id: 't_deposit_mgr'
				,elem: '#t_deposit_mgr'
				,height: 'full-100'
				,url: 'getDepositManagePage'
				,method: 'post'
				,page: true
				,cols: [[
					{field : 'statusName',width:100,title: '状态'}
					,{field : 'custName',width:100,title : '客户姓名'}
					,{field : 'demandId',width:180,title: '客源编号',templet:function(d){
	    			  		return '<span lay-event="showCustomer" style="color:#01AAED;cursor:pointer;">'+d.demandId+'</span>';
		    			} 
					}
					,{field : 'houseId',width:140,title: '房源编号',templet:function(d){
	    			  		return '<span lay-event="showHouse" style="color:#01AAED;cursor:pointer;">'+d.houseId+'</span>';
		    			} 
					}
					,{field : 'dealId',width:140,title : '成交编号',
						templet:function(d){
							var dealId = d.dealId;
							if(dealId == null){dealId = "";}
	    			  		return '<span lay-event="showDeal" style="color:#01AAED;cursor:pointer;">'+dealId+'</span>';
		    			} 
					}
					,{field : 'expenditureNo',width:120,title : '支出协议编号'}
					,{field : 'price',width:100,title: '下定金额'}
					,{field : 'addTime',width:120,title: '下定时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd")}}</div>'}
					,{field : 'maybeDealPrice',width:120,title: '预计成交金额'}
					,{field : 'maybeDealDate',width:120,title: '预计成交时间',templet: '<div>{{ common.toDateString(d.maybeDealDate,"yyyy-MM-dd")}}</div>'}
					,{field : 'trustees',width:100,title: '经办人'}
					,{field : 'deptName',title: '区域'}
					,{field : 'buildingsName',title: '座栋'}
					,{field : 'buildingHouseName',title: '房号'}
					,{field : 'billNo',width:100,title : '票据编号'}
					,{field : 'remark',title : '备注'}
					,{field : 'expenditureRemark',width:100,title : '支出备注'}
					,{fixed : 'right',title: '操作区域', width: 150,align:'center',toolbar: '#operationBar'}
				]]
			});
			
			table.on('tool(t_deposit_mgr)', function(obj) {
				//获得当前行数据
				var data = obj.data;
				//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var layEvent = obj.event; 
				var depositId = data.id;
				if (layEvent === 'confirmBtn') { //确认定金
					layer.confirm('确定要确定此定金金额记录吗？', function(index) {
						confirmDepositRequest("确认定金",data);
					});
				}else if (layEvent === 'depositEdit') { //修改定金
					$(".givedeposit").attr("flag","1");
					$(".writelook-btn").removeAttr("flag");
					$(".deposit-demandId").val(data.demandId);//需求id
					$(".demandId").text(data["demandId"]);
					$(".custName").text(data["custName"]);
					$("#remark").val(data.remark);
					$("#depositForm input").each(function (index, domEle) { 
						$(this).val("");
						if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
							$(this).val("0");
					 	}			
					});
					$("#depositForm input").each(function (index, domEle) {
						$(this).val("");
						var attr=$(this).attr("name");
						if(data[attr]!=null){
							if(attr.indexOf("Time") != -1 || attr.indexOf("Date") != -1){
								$(this).val(common.toDateString(data[attr],"yyyy-MM-dd"));
							}else{
								$(this).val(data[attr]);
							}
						}		
					});
					openModel("定金修改",'600px','580px',$(".depositModel"))
				}else if (layEvent === 'rebackBtn') { //驳回
					layer.confirm('确定要驳回此定金金额记录吗？', function(index) {
						rebackDepositRequest("驳回定金请求",data);
					});
				}else if (layEvent === 'retreatBtn') { //退定
					layer.confirm('确定要退回此定金给客户吗？', function(index) {
						doReback("退还定金",data);
					});
				}else if (layEvent === 'payOffBtn') { //支出
					payOutMoney('定金支出',data);
				}else if (layEvent === 'custDealBtn') { //成交
					doBusinessDeal(title,data);
				}else if(layEvent === "showHouse"){//展示房源明细
					var houseId = data.shhId;
					var url = '${request.getContextPath()}/house/showHouseInfo?houseId=' 
							+ houseId +'&formName='+data.houseUsesId;
					window.open(url);
				}else if(layEvent === "showCustomer"){//展示客源详细
					var demandId = data.demandId;
					var url = '${request.getContextPath()}/customer/showCustomerInfo?demandId=' 
							+ demandId +'&formName='+data.formName;
					window.open(url);
				}else if(layEvent === "showDeal"){//显示成交明细列表
					var houseId = data.houseId;
					var url = '${request.getContextPath()}/house/showHouseInfo?houseId=' 
							+ houseId +'&formName='+data.houseUsesId;
					window.open(url);
				}
			});
			
			//勾选表格复选框赋值
			table.on('checkbox(selectHouse)', function(obj){
				  var shhId = obj.data.id;
				  var houseId = obj.data.houseId;
				  $(".dshhId").val(shhId);
				  $(".dhouseId").val(houseId);
			});
		});
		
		//选择房源点击事件
		$(".selectHouse").click(function(){
			var cols = [
				{checkbox: true},
		  		{field:"transactionType",title:"类型",width:'80'},
		 		{field:"status",title:"状态",width:'80'},
		 		{field:"houseId",title:"房源编号",width:'100'},
		 		{field:"communityName",title:"小区"},
		 		{field:"buildingsName",title:"坐栋"},
		 		{field:"buildingHouseName",title:"门牌号"},
		 		{field:"owner",title:"业主姓名"},
		 		{field:"room",title:"房型",templet: function(d){
		 	    	return  d.room +"室"+d.hall+"厅"
		 	    }},
		 		{field:"buildSpace",title:"面积"},
		 		{field:"sellingPrice",title:"售价"},
		 		{field:"rentPrice",title:"租价"},
		 		{field:"maintainer",title:"维护人"},
		 		{field:"entrustCode",title:"委托编号"},
		 		{field:"mainterDept",title:"部门"}
		  	];
	  	 	var conditions = {};
			table.render({
				elem: '#selectHouse'
			 	,url:"<%=request.getContextPath()%>/customer/customerFindHouseList" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,where:{conditions:JSON.stringify(conditions)}
			}); 
			openModel("选择房源",'650px','500px',$(".selectHouseModel"));
	  	});
		
		/*弹出框*/
		function openModel(title,width,height,divModel,param,myEvent) {
			layer.open({
				type: 1,
			  	title: '<span style="color: #1E9FFF;font-size:18px;font-weight:500;">' + title + '</span>',
				shade: [0],
				area: [width, height],
				offset: 'auto', //弹出位置
				btnAlign: 'r',
				content:divModel  //显示的url，no代表不显示滚动条
			});
		}
		
		//保存定金
		function saveDeposit(){
			var data = {}
			var inputs = $(".deposit-form").find("input");
			for(var i=0;i<inputs.length;i++){
				var name = $(inputs[i]).attr("name");
				var value = $(inputs[i]).val();
				data[name] = value;
			}
			var remark = $(".dremark").val();
			data.remark = remark;
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "updateDepositData",	                
	            success: function (result) {
	            	if(result.success){
	            		layer.msg("保存成功！");
						location.reload();
	            	}else{
	            		layer.msg(result.msg);
	            	}
	            },
	            error : function() {
	            	layer.msg("系统操作异常，请联系管理员");
	            }
	        });  
		}
		
		//确认定金处理
		function confirmDepositRequest(title,data){
			$.ajax({
				type: 'post'
				,url: 'comfirmDeposit?depositId=' + data.id + "&houseId=" 
						+ data.shhId + "&demandId=" + data.demandId
				,success: function(result){
					if(result.success){
						layer.msg(result.msg);
						location.reload();
					}else{
						layer.msg(result.msg);
					}
				}
				,error: function(a,b,c){
					layer.msg("系统操作异常！");
				}
			});
		}
		
		//驳回定金处理
		function rebackDepositRequest(title,data){
			$.ajax({
				type: 'post'
				,url: 'rebackDepositRequest?depositId=' + data.id
				,success: function(result){
					if(result.success){
						layer.msg(result.msg);
						location.reload();
					}else{
						layer.msg(result.msg);
					}
				}
				,error: function(a,b,c){
					layer.msg("系统操作异常！");
				}
			});
		}
		
		//退定处理
		function doReback(title,data){
			$.ajax({
				type: 'post'
				,url: 'retreatDeposit?depositId=' + data.id + "&houseId=" 
						+ data.shhId + "&demandId=" + data.demandId
				,success: function(result){
					if(result.success){
						layer.msg(result.msg);
						location.reload();
					}else{
						layer.msg(result.msg);
					}
				}
				,error: function(a,b,c){
					layer.msg("系统操作异常！");
				}
			});
		}
		
		//成交处理
		function doBusinessDeal(title,data){
			$.ajax({
				type: 'post'
				,url: 'retreatDeposit?depositId=' + data.id
				,success: function(result){
					if(result.success){
						layer.msg(result.msg);
						location.reload();
					}else{
						layer.msg(result.msg);
					}
				}
				,error: function(a,b,c){
					layer.msg("系统操作异常！");
				}
			});
		}
		
		//支出定金给业主
		function payOutMoney(title,data){
			$('#payOffForm').removeClass("layui-hide");
			var depositId = data.id;
			layerId = layer.open({
			  	title: '<span style="color: #1E9FFF;font-size:18px;font-weight:700;">' + title +'</span>'
			  	,type: 1
			  	,area:['400px','230px']
				,resize: false
			  	,content: $("#payOffForm")
			  	,end: function(index, layero){ 
					$('#payOffForm').addClass("layui-hide");
				}
			});
			//表单提交
			form.on('submit(payOffForm)',function(data){
				$.ajax({
					type: 'post'
					,url: 'savePayOffData'
					,dataType: 'json'
					,data:{
						jsonData: JSON.stringify(data.field)
						,depositId: depositId
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
		/**
		 * 查询页面数据函数
		 */
		function searchData() {
			var custType = $("#custType").val();
			var depositStatus = $("#depositStatus").val();
			var trusteesId = $("#trusteesId").val();
			var depositTime = $("#depositTime").val();
			//执行重载
			table.reload('t_deposit_mgr', {
				page : {
					//重新从第 1 页开始
					curr : 1
				},
				where : {
					custType : custType,
					depositStatus : depositStatus,
					trusteesId : trusteesId,
					depositTime : depositTime
				}
			});
		}
		//导出当前页
		function exportTableToExcel(){
			//.excelTable是需要导出table的class
			$(".excelTable").table2excel({
				// 需要导出的列
				columns:"statusName,custName,demandId,houseId,dealId,expenditureNo,price,addTime,maybeDealPrice,maybeDealDate,trustees,deptName,buildingsName,buildingHouseName,buildingHouseName,billNo,remark,expenditureRemark",
				// 不被导出的表格行的CSS class类
				exclude: ".noExcel",
				// 导出的Excel文档的名称
				name: "Excel",
				// Excel文件的名称
				filename: "epr-" +  new Date().getTime() + ".xls",
				// 是否导出图片
				exclude_img: true,
				// 是否导出超链接
				exclude_links: true,
				// 是否导出输入框中的内容
				exclude_inputs: true,
			});
		}
	</script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/src/customer-platform/js/jquery.table2excel.min.js"></script>
</body>
</html>