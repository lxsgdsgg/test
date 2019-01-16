<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css">
	<%-- <link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/jquery.tagsinput.css"> --%>
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/Mo2.css">
	
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script>
	<%-- <script type="text/javascript" src="${request.getContextPath()}/src/manage-platform/js/jquery.tagsinput.js"></script> --%>
	<%-- <script type="text/javascript" src="${request.getContextPath()}/src/manage-platform/js/Mo2.js"></script>  --%>
	</head>
	<script type="text/javascript">
	
		var path = "<%=request.getContextPath()%>";
	
		$(function() {
			
		});
			
		layui.use(['element','form','table','layer'], function(){
			  var element = layui.element;
			  var form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  widgetid = 0;
			  
			  //加载下拉控件
			  var cols = [
				  		{field:"widgetCode",title:"编号"},
				 		 {field:"typeName",title:"类型"},
				 		 {field:"name",title:"名称",event:"loadValue"},
				 		 {fixed: 'right', width:80, align:'center', toolbar: '#barDemo1'}
				  		];
			  
			  table.render({
				    elem: '#datatype'
				    ,height: 500
				    ,url: path+"/manage/getSelectType" //数据接口
				    ,page: true //开启分页
				    ,cols: [cols]
			  		,method:'post'
			  		,done: function(res, curr, count){
			  			//加载值
			  			 var typeData = table.cache["datatype"]; 
						 widgetid = typeData[0].widgetCode;
						 if(typeData[0].type=='1'){
				    		getDataValue(widgetid,"1");
				    	 }else if(typeData[0].type=='2'){
				    		getDataValue(widgetid,"2");
				    	 }
			  		}
				  }); 
			  
			 
				
				
			  //监听工具条
			  table.on('tool(datatype)', function(obj){
			    var data = obj.data;
			    
			    if(obj.event === 'add'){
			    	  var oldData =  table.cache["dataitem"]; 
					  var newData = {"fieldCode":"","sorting":"0","fieldValue":"","id":""};
					  oldData.push(newData);
					  table.reload('dataitem', {
						  data:oldData
					 });
			    }else if(obj.event === "loadValue"){
			    	widgetid = data.widgetCode;
			    	//alert(widgetid);
			    	if(data.type=='1'){
			    		getDataValue(widgetid,"1");
			    	}else if(data.type=='2'){
			    		getDataValue(widgetid,"2");
			    	}
			    	
			    }else if(obj.event==="edit"){
			    	widgetid = data.widgetCode;
			    	
			    	//取出sql
			    	$.post(path+"/manage/getSelectItem",{widgetCode:widgetid,type:"1"},function(d){
			    		console.log(d.data);
			    		$(".showSql").find("textarea").val("");
			    		var sql = d.data[0].fieldValue;
			    		$(".showSql").find("textarea").val(sql);
			    	})
			    	layer.open({
						type: 1,
						title: "编辑sql",
						shade: [0],
						area: ['300px', '300px'],
						offset: 'auto', //弹出位置
						btnAlign: 'r',
						content: $(".showSql") ,//显示的url，no代表不显示滚动条
						btn: ['确定'],
						yes: function(index, layero) {
							var sql = $(".showSql").find("textarea").val();
							$.post(path+"/manage/saveSelectSql",{sql:sql,widgetCode:widgetid},function(d){
								if(d.success){
									layer.alert("成功");
								}
							})
						}
						
					});
			    }
			    
			  });
			  
			  table.on('tool(dataitem)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'detail'){
				    	var oldData =  table.cache["dataitem"];
				    	for(var i=0;i<oldData.length;i++){
				    		if(oldData[i].id == data.id){
				    			oldData.splice(i,1);
				    		}
				    	}
				    	 table.reload('dataitem', {
							  data:oldData
						 });
				    }
				  });
			  
			  //监听单元格编辑
			  table.on('edit(dataitem)', function(obj){ 
				  var value = obj.value;
				  var field = obj.field; //当前编辑的字段名
				  if(field=="sorting"){
					  var a = /^[0-9]*$/;
					  if(!a.test(value)){
						  layer.alert("只能输入数字");
					  }
				  }
				});
			  //加载值
			  function getDataValue(widgetid,type){
					$.post(path+"/manage/getSelectItem",{widgetCode:widgetid,type:type},function(d){
						
						 var cols = [
							 {field:"fieldCode",edit:true,title:"编号"},
					 		 {field:"fieldValue",edit:true,title:"值"},
					 		 {field:"sorting",edit:true,title:"排序"},
					 		 {fixed: 'right', width:80, align:'center', toolbar: '#barDemo2'}
					  		];
						 
						 table.render({
							    elem: '#dataitem'
							    ,height: 500
							    ,data: d.data //数据接口
							    ,page: false //开启分页
							    ,cols: [cols]
							  }); 
					});
			  }
			  
			 
			  //保存下拉值
			  
			  $(".save-dataitem-btn").click(function(){
				  var selectValue = table.cache["dataitem"];
				  cansave = true;
				  for(var i=0;i<selectValue.length;i++){
					  var sorting = selectValue[i].sorting;
					  var fieldValue = selectValue[i].fieldValue;
					  if(fieldValue==""){
						  selectValue.splice(i,1);
					  }
					  var a = /^[0-9]*$/;
					  if(!a.test(sorting)){
						  cansave = false;
					  }
				  }
				  console.log(selectValue);
				  var data = {
						  widgetCode:widgetid,
						  selectValue:selectValue
				  }
				  if(cansave){
					  $.ajax({
				            type: "POST",//方法类型
				            dataType: "json",//预期服务器返回的数据类型
				            contentType: 'application/json',
				            data:JSON.stringify(data),
				            url: path+"/manage/setSelectItem" ,//url	                
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
					  layer.alert("排序列只能填数字");
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
					<form class="layui-form">
					    <p><label>类型明细管理:</label></p>
					    <table id="dataitem" lay-filter="dataitem" >
							
						</table>
					</form>
					<div class="layui-form">
						<div class="layui-form-item">
						    <div class="layui-input-block">
						        <button  type="button" class="layui-btn save-dataitem-btn"  lay-filter="saveDataitem" >保存</button>
						    </div>
					  	</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/html" id="barDemo1" >
		  {{#  if(d.type == 1){ }}
    			 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="add">新增</a>
  		  {{#  } }}
         
  		  {{#  if(d.type == 2){ }}
    			 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="edit">编辑</a>
  		  {{#  } }}
        </script>
        <script type="text/html" id="barDemo2" >
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="detail">删除</a>
  
        </script>
				
	</body>
	<div class="showSql" style="display:none;">
		<textarea style="width: 90%;height: 180px;margin: 10px;">
		
		</textarea>
	</div>
</html>