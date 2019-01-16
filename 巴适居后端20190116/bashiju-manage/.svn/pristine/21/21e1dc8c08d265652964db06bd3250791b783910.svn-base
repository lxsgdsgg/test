<%@ page language="java" contentType="text/html; charset=utf-8"
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
		<div class="layui-form job-form" style="padding-top: 10px;">
			<input type="hidden" name="id" class="layui-input id">
		    <div class="layui-inline ">
		       <label class="layui-form-label">任务名称</label>
		       <input type="text" name="jobName"  value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		       <label class="layui-form-label">表达式</label>
		       <input type="text" name="cronExpression"  value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		       <label class="layui-form-label">任务描述</label>
		       <input type="text" name="JobDescription"  value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		       <label class="layui-form-label">任务组</label>
		       <input type="text" name="jobGroup"  value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		       <label class="layui-form-label">路由</label>
		       <input type="text" name="routingKey"  value="" class="layui-input">
		    </div>
		   <div class="layui-inline ">
		       <label class="layui-form-label">参数</label>
		       <input type="text" name="targetParam"  value="" class="layui-input">
		    </div>
		    
		    <div class="layui-inline ">
		    	<label class="layui-form-label"></label>
		     	<button type="button" class="layui-btn saveData" name="">保存</button>
		    </div>
		    <div class="layui-inline ">
		    	<label class="layui-form-label"></label>
		     	<button type="button" class="layui-btn" name="">取消</button>
		    </div>
		</div>
		
		<div>
			<table id="jobList" lay-filter="jobList" ></table>
		</div>
		
		<script>
		
		
		layui.use(['element','form','table','layer','laydate'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			 
			  
			  
			  cols = [
			  		 {field:"jobName",title:"任务名称",width:'180'},
			  		 {field:"isValid",title:"状态",width:'160'},
			 		 {field:"cronExpression",title:"表达式",width:'180'},
			 		 {field:"JobDescription",title:"任务描述"},
			 		 {field:"jobGroup",title:"任务组"},
			 		 {field:"routingKey",title:"路由",width:'100'},
			 		 {field:"targetParam",title:"参数",width:'160'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
			  table.render({
			    elem: '#jobList'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/manage/getJobConf" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,cellMinWidth:100
		  		,done: function(res, curr, count){
		  			
		  		}
			  });
			  
			
			 
			 //监听操作
			 table.on('tool(jobList)', function(obj){
				 	var data = obj.data;
				 	var id = data.id;
				    if(obj.event === 'del'){
				    	layer.confirm('要删除吗?', {icon: 3, title:'提示'}, function(index){
				    		  $.post("<%=request.getContextPath()%>/manage/delJobConf",{id:id},function(d){
				    			  if(d.success){
					            		alert("成功！");
					            		
					            	}else{
					            		alert(d.msg);
					            	}
				    		  })
				    		  
				    		  layer.close(index);
				    	});
				    }else if(obj.event === 'update'){
				    	for(var key in data){
				    		//alert(key);
				    		$("input[name="+key+"]").val(data[key]);
				    	}
				    }
			});
			 $(".saveData").click(function(){
				 //alert(111);
				 var inputs = $(".job-form").find("input");
				 var data = {};
				 for(var i=0;i<inputs.length;i++){
					 var name = $(inputs[i]).attr("name");
					 var value = $(inputs[i]).val();
					 data[name] = value;
				 }
				 
				 $.ajax({
			            type: "POST",//方法类型
			            dataType: "json",//预期服务器返回的数据类型
			            contentType: 'application/json',
			            data:JSON.stringify(data),
			            url: "<%=request.getContextPath()%>/manage/saveJobConf" ,//url	                
			            success: function (result) {
			            	if(result.success){
			            		alert("成功！");
			            		table.render({
			        			    elem: '#jobList'
			        			    ,height: 500
			        			    ,url:"<%=request.getContextPath()%>/manage/getJobConf" //数据接口
			        			    ,page: true //开启分页
			        			    ,cols: [cols]
			        		  		,method:'post'
			        		  		,cellMinWidth:100
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