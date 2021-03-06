﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/deptSelect.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
	<script type="text/javascript" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/layui/layui.js"></script>
	<%-- <script type="text/javascript" src="${staticfile_url}/housing-platform/js/deptSelect.js"></script> --%>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/AreaSelect.js"></script>  
	</head>
	
	<style>
		
	</style>
	
	<body class="">
		<div class="layui-form address-form" style="padding-top: 10px;">
		   <input type="hidden" name="id" class="layui-input id">
		   <div class="layui-form-item">
				<label class="layui-form-label">城市区划</label>
			    <div class="layui-input-block ">
			      <input type="hidden" name="areaId" class="layui-input areaId">
			      <input type="text" placeholder="请输入内容" name="areaName" class="layui-input areaName">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">选模板</label>
			    <div class="layui-input-block ">
			      <input type="hidden" name="templateName" class="layui-input">
			      <select name="templateId" lay-filter="templateId" class="templateId">
			      	<option></option>
			      	<c:forEach items="${templetNames }" var="templet">
			      		<option value="${templet.id}">${templet.templateName}</option>
			      	</c:forEach>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">交易类型</label>
			    <div class="layui-input-block ">
			      <input type="hidden" name="transactionType" class="layui-input">
			      <select name="transactionTypeId" lay-filter="transactionTypeId" class="transactionTypeId">
			      		<option value="1">出售</option>
			      		<option value="2">出租</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">采集地址</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="houseListUrl" class="layui-input houseListUrl" >
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn saveData"  lay-filter="*" >保存</button>
			      
			    </div>
			</div>
		</div>
		
		<div>
			<table id="addressList" lay-filter="addressList" ></table>
		</div>
		
		<script>
		function selectArea(code,name){
			$(".areaName").val(name);
			$(".areaId").val(code);
		}
		
		$(function(){
			   //选区域
			   var areaList=JSON.parse('${areaListJson}');
			   var a= new AreaObject(areaList,selectArea,3,$(".areaName"));	
				
		});
		
		layui.use(['element','form','table','layer','laydate'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			 
			  
			  
			  cols = [
			  		 {field:"cityCode",title:"城市代码",width:'180'},
			 		 {field:"templateName",title:"解析模板名称",width:'80'},
			 		 {field:"areaName",title:"区划名称"},
			 		 {field:"regionName",title:"片区名称"},
			 		 {field:"transactionType",title:"房源交易类型"},
			 		 {field:"houseListUrl",title:"采集地址",width:'180'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
			  table.render({
			    elem: '#addressList'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/manage/getAddressConf" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,cellMinWidth:160
		  		,done: function(res, curr, count){
		  			
		  		}
			  });
			  
			
			 
			 //监听操作
			 table.on('tool(addressList)', function(obj){
				 	var data = obj.data;
				 	var id = data.id;
				    if(obj.event === 'del'){
				    	layer.confirm('要确认吗?', {icon: 3, title:'提示'}, function(index){
				    		  $.post("<%=request.getContextPath()%>/manage/delAddressConf",{id:id},function(d){
				    			  if(d.success){
					            		alert("成功！");
					            		
					            	}else{
					            		alert(d.msg);
					            	}
				    		  })
				    		  
				    		  layer.close(index);
				    	});
				    }else if(obj.event === 'update'){
				    	$(".id").val(id);
				    	$(".areaId").val(data.areaCode);
						$(".areaName").val(data.areaName);
						$(".templateId").val(data.templateId);
						$(".transactionTypeId").val(data.transactionTypeId);
						$(".houseListUrl").val(data.houseListUrl);
				    }
			});
			 $(".saveData").click(function(){
				 var data = {};
				 var id = $(".id").val();
				 data.id = id;
				 var areaCode = $(".areaId").val();
				 var areaName = $(".areaName").val();
				 
				 data.areaCode = areaCode;
				 data.areaName = areaName;
				 var templateId = $("select[name='templateId']").val();
				  var options = $("select[name='templateId']").find("option");
				  for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(templateId == val)
						 var templateName = $(options[i]).text();
				  }
				  data.templateId = templateId;
				  data.templateName = templateName;
				  var transactionTypeId = $("select[name='transactionTypeId']").val();
				  var options = $("select[name='transactionTypeId']").find("option");
				  for(var i=0;i<options.length;i++){
					 var val = $(options[i]).attr("value");
					 if(transactionTypeId == val)
						 var transactionType = $(options[i]).text();
				  }
				  data.transactionTypeId = transactionTypeId;
				  data.transactionType = transactionType;
				  var address = $(".houseListUrl").val();
				  data.houseListUrl = address;
				 
				 $.ajax({
			            type: "POST",//方法类型
			            dataType: "json",//预期服务器返回的数据类型
			            contentType: 'application/json',
			            data:JSON.stringify(data),
			            url: "<%=request.getContextPath()%>/manage/saveAddressConf" ,//url	                
			            success: function (result) {
			            	if(result.success){
			            		alert("成功！");
			            		table.render({
			        			    elem: '#addressList'
			        			    ,height: 500
			        			    ,url:"<%=request.getContextPath()%>/manage/getAddressConf" //数据接口
			        			    ,page: true //开启分页
			        			    ,cols: [cols]
			        		  		,method:'post'
			        		  		,cellMinWidth:160
			        		  		,done: function(res, curr, count){
			        		  			
			        		  		}
			        			 });
			            		
			            	}else{
			            		alert(result.msg);
			            	}
			            },
			            error : function() {
			            	
			            },
			            beforeSend : function() {
			            	
			            }
			       }); 
			 })
			 
			
		});
		
		
		
		</script>
		
		<script type="text/html" id="barDemo" >
			<a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="del">删除</a>
		    <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="update">修改</a>
         
        </script>
        
	</body>	
	
</html>