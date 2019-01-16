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
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/ueditor/themes/default/css/umeditor.css">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/deptSelect.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
	<script type="text/javascript" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/layui/layui.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/ueditor/umeditor.config.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/ueditor/umeditor.min.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/AreaSelect.js"></script>  
	</head>
	
	<style>
		
	</style>
	
	<body class="">
		<div>
			<div class="layui-form" style="padding: 10px;">
				<div class="layui-inline ">
				   <label class="layui-form-label">状态</label>
			       <select class="status">
			       		<option value="">请选择</option>
			       		<option value="0">未处理</option>
			       		<option value="1">已处理</option>
			       </select>
			    </div>
				
			    <div class="layui-inline ">
			    	<label class="layui-form-label"></label>
			    	<button type="button" class="layui-btn searchTipoff"  lay-filter="*" >查找</button>
			    </div>
			    
			    
			    
		    </div>
			
			<table id="tipoffList" lay-filter="tipoffList" ></table>
		</div>
		
		<script>
		
	    layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  var upload = layui.upload
			  
			  
			  var cols = [
			  		 {field:"sourceType",title:"资源类型",width:'120'},
			  		 {field:"tipOffType",title:"举报类型",width:'120'},
			 		 {field:"content",title:"举报内容",width:'120'},
			 		 {field:"status",title:"处理状态",width:'200'},
			 		 {field:"realName",title:"处理人",width:'200'},
			 		 {field:"handleTime",title:"处理时间",width:'200'},
			 		 {field:"addTime",title:"举报时间",width:'120'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
		  	  var condition = {}
			  table.render({
			    elem: '#tipoffList'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/manage/getCustTipoff" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,where:{condition:JSON.stringify(condition)}
		  		,done: function(res, curr, count){
		  			
		  		}
			  }); 
			  
			//查询
				 $(".searchTipoff").click(function(){
					 var status = $(".status").val();
					 var condition = {
							 status:status
					 };
					
					 table.render({
						    elem: '#tipoffList'
						    ,height: 500
						    ,url:"<%=request.getContextPath()%>/manage/getCustTipoff" //数据接口
						    ,page: true //开启分页
						    ,cols: [cols]
					  		,method:'post'
					  		,cellMinWidth:160
					  		,where:{conditions:JSON.stringify(condition)}
					  		,done: function(res, curr, count){
					  			
					  		}
						  });
					 
				 });
			 
			 //监听操作
			 table.on('tool(tipoffList)', function(obj){
				 	var data = obj.data;
				 	var id = data.id;
				    if(obj.event === 'handle'){
				    	$(".handle-id").val(id);
				    	layer.open({
							type: 1,
							title:'处理结果' ,
							shade: [0],
							area: ['300px', '260px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.handleModel')  //显示的url，no代表不显示滚动条
							
						});
				    }else if(obj.event === 'show'){
				    	$(".showcontent").html(data.content)
				    	layer.open({
							type: 1,
							title:'反馈内容' ,
							shade: [0],
							area: ['800px', '660px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.showModel')  //显示的url，no代表不显示滚动条
							
						});
				    }
			});
			
			
			
			 
			
			 
		});
	    
	    function saveHandleResult(){
	    	var id = $(".handle-id").val();
	    	var handleResult = $(".handleResult").val();
	    	var type = "custTipoff";
	    	$.post("<%=request.getContextPath()%>/manage/saveHandleResult",{id:id,handleResult:handleResult,type:type},function(d){
	    		 if(d.success){
	            		alert("已保存！");
	            		
	            	}else{
	            		alert(d.msg);
	            	}
	    	})
	    	
		    
		    
	    }
		
		</script>
		
		<script type="text/html" id="barDemo" >
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="show">查看</a>
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="handle">处理</a>
        </script>
        
	</body>	
	<div class="showModel" style="display:none;">
		<div class="showcontent">
		
		</div>
	</div>
	
	<div class="handleModel" style="display:none;">
		<div class="layui-form" style="padding: 10px;">
			<input type="hidden"  name="id" class="layui-input handle-id">
			<div class="layui-form-item">
				<label class="layui-form-label">处理结果</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="handleResult" class="layui-input handleResult">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block ">
			      <button type="button" class="layui-btn"  lay-filter="*" onclick="saveHandleResult();">保存</button>
			    </div>
			</div>
		</div>
			
	</div>
</html>