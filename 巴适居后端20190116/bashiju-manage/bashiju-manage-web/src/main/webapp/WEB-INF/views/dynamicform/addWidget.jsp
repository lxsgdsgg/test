﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>

<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/common/css/bootstrap.min.css"> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/common/css/jquery-ui.min.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/jquery.tagsinput.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/Mo2.css">
	
	<script type="text/javascript" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>
	<%-- <script type="text/javascript" src="${staticfile_url}/common/js/bootstrap.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${staticfile_url}/common/js/jquery-ui.min.js"></script> --%>
	<script type="text/javascript" src="${staticfile_url}/common/frame/layui/layui.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/AreaSelect.js"></script> 
	<%-- <script type="text/javascript" src="${staticfile_url}/manage-platform/js/jquery.tagsinput.js"></script> --%>
	<%-- <script type="text/javascript" src="${staticfile_url}/manage-platform/js/Mo2.js"></script>  --%>
	</head>
	<style>
		.widget-form .layui-form-item{
			display: inline-block;
			width: 30%;
		}
	</style>
	<script type="text/javascript">
	
		var path = "<%=request.getContextPath()%>";
		
		
		
		function selectArea(code,name){
			$("#areaName").val(name);
			$(".cityCode").val(code);
		}
		
		widgetType = "select";
	
		$(function() {
			
			var areaList=JSON.parse('${areaListJson}');
			var a= new AreaObject(areaList,selectArea,2,$("#areaName"));	
			
			
			
			$(".widgetCode").blur(function(){
				var cityCode = $(".cityCode").val();
				var widgetCode = $(".widgetCode").val();
				$.post(path+"/manage/validateWidgetCode",{widgetCode:widgetCode,widgetType:widgetType,cityCode:cityCode},function(d){
					if(d.success){
						
					}else{
						alert(d.msg);
						$(".widgetCode").val("");
					}
				})
			})
		});
			
		layui.use(['element','form','table','layer'], function(){
			  var element = layui.element;
			  var form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  type = "下拉";
			  //加载控件类型
				  var cols = [
					 		 {field:"type",title:"类型",event:"loadtable"},
					 		 {fixed: 'right', width:80, align:'center', toolbar: '#barDemo1'}
					  		];
				  var data = [{"type":"下拉"},{"type":"区间"}];
				  table.render({
					    elem: '#datatype'
					    ,height: 315
					    ,data:data //数据接口
					    ,page: false //开启分页
					    ,cols: [cols]
					  }); 
				  
				  loadDataTable("select");
			 
			  
			  //监听工具条
			  table.on('tool(datatype)', function(obj){
			    var data = obj.data;
			    
			    if(obj.event === 'addSelect'){
			    	  var oldData =  table.cache["dataitem"]; 
					  var newData = {"fieldCode":"","fieldValue":"","sorting":"0"};
					  oldData.push(newData);
					  table.reload('dataitem', {
						  data:oldData
					 });
			    }else if(obj.event === 'addRegion'){
			    	  var oldData =  table.cache["dataitem"]; 
					  var newData = {"fieldValue":"","maxRegionValue":""};
					  oldData.push(newData);
					  table.reload('dataitem', {
						  data:oldData
					 });
			    }else if(obj.event === "loadtable"){
			    	if(data.type=='下拉'){
			    		loadDataTable("select");
			    		type = "下拉";
			    		widgetType = "select";
			    		$(".citycodeDiv").hide();
			    		$(".widgetsqlDiv").show();
			    	}else if(data.type=='区间'){
			    		loadDataTable("region");
			    		type = "区间";
			    		widgetType = "region";
			    		$(".citycodeDiv").show();
			    		$(".widgetsqlDiv").hide();
			    	}
			    	
			    }
			    
			  });
			  
			  
			  
			  //加载值
			  function loadDataTable(type){
				  var data1 = [{"fieldCode":"","fieldValue":"","sorting":"0"},{"fieldCode":"","fieldValue":"","sorting":"0"},{"fieldCode":"","fieldValue":"","sorting":"0"},{"fieldCode":"","fieldValue":"","sorting":"0"},{"fieldCode":"","fieldValue":"","sorting":"0"}]
				  var data2 = [{"fieldValue":"","maxRegionValue":""},{"fieldValue":"","maxRegionValue":""},{"fieldValue":"","maxRegionValue":""},{"fieldValue":"","maxRegionValue":""},{"fieldValue":"","maxRegionValue":""}]
				  if(type=="select")	{
					 var cols = [
						 {field:"fieldCode",edit:true,title:"编号"},
				 		 {field:"fieldValue",edit:true,title:"值"},
				 		 {field:"sorting",edit:true,title:"排序"}
				  		];
					 
					 table.render({
						    elem: '#dataitem'
						    ,height:500
						    ,data: data1 //数据接口
						    ,page: false //开启分页
						    ,cols: [cols]
						  }); 
				 }else if(type=="region"){
					 var cols = [
				 		 {field:"fieldValue",edit:true,title:"最小值"},
				 		 {field:"maxRegionValue",edit:true,title:"最大值"}
				  		];
					 
					 table.render({
						    elem: '#dataitem'
						    ,height: 500
						    ,data: data2 //数据接口
						    ,page: false //开启分页
						    ,cols: [cols]
						  }); 
				 }
						 
			  }
			  
			 
			  //保存值
			  
			  $(".save-widget-btn").click(function(){
				  widgetCode = $("input[name='widgetCode']").val();
				  var widgetValue = table.cache["dataitem"];
				  var value = [];
				  cansave = true;
				  var widgetInfo = $(".widget-form").serialize();
				  widgetInfo = decodeURIComponent(widgetInfo);
				  console.log(widgetInfo);
				  for(var i=0;i<widgetValue.length;i++){
					  
					  delete widgetValue[i]["LAY_TABLE_INDEX"];
					  widgetValue[i].widgetCode = widgetCode;
					  
					  if(type=="下拉"){
						  var v1 = widgetValue[i].fieldValue;
						  var v2 = widgetValue[i].sorting;
					  }else{
						  var v1 = widgetValue[i].fieldValue;
						  var v2 = widgetValue[i].maxRegionValue; 
					  }
					  
					  if(v1!=""){
						  value.push(widgetValue[i]);
					  }
					  
				  }
				  console.log(widgetValue);
				  var data = {
						  widgetCode:widgetCode,
						  widgetInfo:widgetInfo,
						  widgetValue:value
				  }
				  if(cansave){
					 $.ajax({
				            type: "POST",//方法类型
				            dataType: "json",//预期服务器返回的数据类型
				            contentType: 'application/json',
				            data:JSON.stringify(data),
				            url: path+"/manage/setWidget" ,//url	                
				            success: function (result) {
				            	if(result.success){
				            		alert("保存成功！");
				            	}else{
				            		alert(result.msg);
				            	}
				            },
				            error : function() {
				            	
				            },
				            beforeSend : function() {
				            	
				            }
				        });  
				  }else{
					  layer.alert("字段名和控件编号不能为空");
				  }
				  
				  
			  })
		});	  
		
		
	 
	</script>
	<body class="">
		<div class="layui-container">
			<div class="layui-row">
				<div class="layui-col-md4 setDataItem-box">
					
					<table id="datatype" lay-filter="datatype" >
						
					</table>
				</div>
				<div class="layui-col-md8">
					<form class="layui-form widget-form">
					    <input type="hidden" name="cityCode" class="cityCode">
						 <div class="layui-form-item" >
						    <label class="layui-form-label">控件名称</label>
						    <div  class="layui-input-block">
						      <input type="text" name="name" placeholder="请输入" autocomplete="off" class="layui-input">
						    </div>
						 </div>
						 <div class="layui-form-item">
						    <label class="layui-form-label">控件类型</label>
						    <div class="layui-input-block ">
						      <select name="type" lay-filter="type">
						        <option value="1">一般下拉</option>
						        <option value="2">sql下拉</option>
						        <option value="3">区间</option>
						      </select>
						    </div>
						  </div>
						 <div class="layui-form-item citycodeDiv" style="display:none;">
						    <label class="layui-form-label">城市</label>
						    <div class="layui-input-block ">
						    <input type="text" placeholder="请输入" autocomplete="off" id="areaName" class="layui-input">
						      <!-- <select name="cityCode" lay-filter="cityCode" class="cityCode">
						      	<option value=""></option>
						        <option value="11">11</option>
						        <option value="22">22</option>
						        <option value="33">33</option>
						      </select> -->
						    </div>
						  </div>
						 <div class="layui-form-item" >
						    <label class="layui-form-label">控件编号</label>
						    <div  class="layui-input-block">
						      <input type="text" name="widgetCode" placeholder="请输入" autocomplete="off" class="layui-input widgetCode">
						    </div>
						 </div>
					    <div class="layui-form-item widgetsqlDiv" style="display:block;width:100%;" >
						    <label class="layui-form-label">sql语句</label>
						    <div  class="layui-input-block">
						      <textarea type="text" name="widgetSql" placeholder="请输入" autocomplete="off" class="layui-input"></textarea>
						    </div>
						 </div>
					</form>
					<table id="dataitem" lay-filter="dataitem" >
							
					</table>
					<div class="layui-form">
						<div class="layui-form-item">
						    <div class="layui-input-block">
						        <button  type="button" class="layui-btn save-widget-btn"  lay-filter="saveWidget" >保存</button>
						    </div>
					  	</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/html" id="barDemo1" >
		  {{#  if(d.type == '下拉'){ }}
    			 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="addSelect">新增</a>
  		  {{#  } }}
         
  		  {{#  if(d.type == '区间'){ }}
    			 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="addRegion">新增</a>
  		  {{#  } }}
        </script>
        
				
	</body>
	
</html>