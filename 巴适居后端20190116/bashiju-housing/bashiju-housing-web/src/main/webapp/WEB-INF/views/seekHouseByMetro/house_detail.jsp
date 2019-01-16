<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<meta charset="UTF-8">
	<title>房源明细数据</title>
	</head>
	<body>
		<div class="toolBar layui-form" id="searchArea" style="margin-top:10px;">
			<div class="layui-inline">
				<div class="layui-input-inline" style="width:150px;">
					<input type="hidden" id="areaCode" name="areaCode">
					<input id="area" name="area" class="layui-input" placeholder="请选择区域" readonly="readonly" autocomplete="off">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width:150px;">
					<input type="hidden" id="squareSectionId" name="squareSectionId">
					<input id="squareSection" name="squareSection" class="layui-input" placeholder="请选择面积区间">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width:150px;">
					<input type="hidden" id="areaCode" name="areaCode">
					<input id="area" name="area" class="layui-input" placeholder="请选择区域" readonly="readonly" autocomplete="off">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width:150px;">
					<input type="hidden" id="priceSectionId" name="priceSectionId">
					<input id="priceSection" name="priceSection" class="layui-input" placeholder="请选择价格区间">
				</div>
			</div>
	  		<button class="layui-btn" name="searchBtn" id="searchBtn" data-type="reload" >搜索</button>
	  		<button class="layui-btn layui-btn-normal" onclick="clearData()">清空</button>
		</div>
		<table class="layui-table" id="t_house_detail" lay-filter="t_house_detail"></table>
		<%@include file="/common/common.jsp" %>
		<script type="text/javascript">
			var table;
			var form;
			layui.use(['table','layer','form'],function(){
				table = layui.table;
				form = layui.form;
				var $ = layui.$;
				var communityId = JSON.parse('${communityId}');
				var dataParam = JSON.parse('${dataParam}');
				dataParam.metroName = "";
// 				var jsonData = JSON.stringify(dataParam).replace(/\"/g,"'");  
				table.render({
					id: 't_house_detail'
					,elem: '#t_house_detail'
					,height: 'full-100'
					,url: 'queryAllHouseSourceDataList?communityId=' + communityId + '&jsonData='
							+ JSON.stringify(dataParam)
					,method: 'post'
					,page: true
					,cols: [[
						{field : 'id',title: 'ID',width:70,sort: true,fixed : 'left',type:'hidden'}
						,{field : 'houseId',title: '房源编号',width:140,sort: true,templet:function(d){
		    			  	return '<span lay-event="showDetail" style="color:#01AAED;cursor:pointer;">'+d.houseId+'</span>';
			    		}}
						,{field : 'transactionType',title: '类型',width: 70,sort: true}
						,{field : 'status',title: '状态',sort: true}
						,{field : 'communityName',title: '小区名称',sort: true}
						,{field : 'buildingsName',title: '座栋',width: 70,sort: true}
						,{field : 'buildingsUnitName',title: '门牌号'}
						,{field : 'owner',title: '业主姓名'}
						,{field : 'houseType',title: '房型'}
						,{field : 'buildSpace',title: '面积'}
						,{field : 'sellingPrice',title: '售价'}
						,{field : 'rentPrice',title: '租价'}
						,{field : 'operator',title: '维护人'}
						,{field : 'entrustCode',title: '委托编号'}
						,{field : 'mainterDept',title: '部门名称'}
					]]
				});
				
				table.on('tool(t_house_detail)',function(obj){
					var data = obj.data;
					var layEvent = obj.event;
					var id = data.id;
					if(layEvent === 'showDetail'){
						var url = '/house/showHouseInfo?houseId=' + id +'&formName='+data.houseUsesId;
						window.open(url);
					}
				});
				
				var $ = layui.$, active = {
					reload : function() {
						table.reload('t_house_detail', {//执行重载
							//重新从第 1 页开始
							page : {curr : 1 },
							where : {
								deptId : $("#deptCode").val(),
								roleId: $("#buildSpace").val(),
								mobile: $("#buildingsId").val(),
								employeeNum: $("#employeeNum").val()
							}
						});
					}
				};
				//查询事件
				$('#searchBtn').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
			});
		</script>
	</body>
</html>
