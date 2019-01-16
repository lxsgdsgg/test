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
		.edui-container{
			top: 86px;
    		left: 36px;
    		width:680px!important;
		}
	</style>
	
	<body class="">
		<div>
			<div class="layui-form" style="padding: 10px;">
				<div class="layui-inline ">
				   <label class="layui-form-label">标题</label>
			       <input type="text"  class="layui-input search-title" >
			    </div>
				<div class="layui-inline ">
				   <label class="layui-form-label">类型</label>
			       <select class="search-classesname">
			         <option value="">请选择</option>
			         <option value="0">关于我们</option>
				     <option value="1">用户协议</option>
			       </select>
			    </div>
			    
			    <div class="layui-inline ">
			    	<label class="layui-form-label"></label>
			    	<button type="button" class="layui-btn searchArticle"  lay-filter="*" >查找</button>
			    </div>
			    
			    <div class="layui-inline ">
			    	<label class="layui-form-label"></label>
			    	<button type="button" class="layui-btn layui-btn-danger openEdit"  lay-filter="*" >编辑文章</button>
			    </div>
			    
		    </div>
			
			<table id="articleList" lay-filter="articleList" ></table>
		</div>
		
		<script>
		
	    layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  var upload = layui.upload
			  
			  
			  cols = [
			  		 {field:"title",title:"文章标题",width:'180'},
			 		 {field:"typeName",title:"文章分类"},
			 		 {field:"browseCnt",title:"浏览次数",width:'180'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
			  var conditions = {type:"ourselves"};
			  table.render({
			    elem: '#articleList'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/manage/getArticleList" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,cellMinWidth:160
		  		,where:{conditions:JSON.stringify(conditions)}
		  		,done: function(res, curr, count){
		  			
		  		}
			  });
			  
			//查询
				 $(".searchArticle").click(function(){
					 var title = $(".search-title").val();
					 var classesname = $(".search-classesname").val(); 
					 var conditions = {
							 type:"ourselves",
							 title:title,
							 classesname:classesname	
					 };
					
					 table.render({
						    elem: '#articleList'
						    ,height: 500
						    ,url:"<%=request.getContextPath()%>/manage/getArticleList" //数据接口
						    ,page: true //开启分页
						    ,cols: [cols]
					  		,method:'post'
					  		,cellMinWidth:160
					  		,where:{conditions:JSON.stringify(conditions)}
					  		,done: function(res, curr, count){
					  			
					  		}
						  });
					 
				 });
			 
			 //监听操作
			 table.on('tool(articleList)', function(obj){
				 	var data = obj.data;
				 	var id = data.id;
				    if(obj.event === 'detail'){
				    	layer.confirm('要删除吗?', {icon: 3, title:'提示'}, function(index){
				    		  $.post("<%=request.getContextPath()%>/manage/delArticle",{id:id},function(d){
				    			  if(d.success){
					            		alert("成功！");
					            		
					            	}else{
					            		alert(d.msg);
					            	}
				    		  })
				    		  
				    		  layer.close(index);
				    	});
				    }else if(obj.event === 'edit'){
				    	for(var key in data){
				    		$(".article-form").find("input[name="+key+"]").val(data[key]);
				    	}
				    	UM.getEditor('myEditor').setContent(data.content);
				    	layer.open({
							type: 1,
							title:'文章内容' ,
							shade: [0],
							area: ['800px', '660px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.addModel')  //显示的url，no代表不显示滚动条
							
						});
				    }else if(obj.event === 'show'){
				    	$(".showcontent").html(data.content);
				    	layer.open({
							type: 1,
							title:'文章内容' ,
							shade: [0],
							area: ['700px', '660px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.showModel')  //显示的url，no代表不显示滚动条
							
						});
				    }
			});
			
			 $(".openEdit").click(function(){
				 layer.open({
						type: 1,
						title:'文章内容' ,
						shade: [0],
						area: ['800px', '660px'],
						offset: 'auto', //弹出位置
						btnAlign: 'r',
						content:$('.addModel')  //显示的url，no代表不显示滚动条
						
					});
				 
				//实例化编辑器
				    var um = UM.getEditor('myEditor');
			 })
			
			
		});
	    
	    function saveArticle(){
	    	var data  = {};
	    	data.type="ourselves";
	    	var inputs = $(".article-form").find(".articleitem");
	    	for(var i = 0;i<inputs.length;i++){
	    		var name = $(inputs[i]).attr("name");
	    		var val = $(inputs[i]).val();
	    		data[name] = val;
	    	}
	    	
		    var typeId = $(".classes").val();
		    data.typeId = typeId;
		    if(typeId==0){
		    	data.typeName = "关于我们";
		    }else if(typeId==1){
		    	data.typeName = "用户协议";
		    }
		    
		    var content = UM.getEditor('myEditor').getContent();
		    data.content = content;
		    
		    $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            contentType: 'application/json',
	            data:JSON.stringify(data),
	            url: "<%=request.getContextPath()%>/manage/saveArticle" ,//url	                
	            success: function (result) {
	            	if(result.success){
	            		alert("成功！");
	            		$(".lookId").val(result.d);
	            		
	            	}else{
	            		alert(result.msg);
	            	}
	            },
	            error : function() {
	            	
	            },
	            beforeSend : function() {
	            	
	            }
	       }); 
	    }
	    

		
		</script>
		
		<script type="text/html" id="barDemo" >
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="show">查看</a>
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="edit">编辑</a>
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="detail">删除</a>
        </script>
        
	</body>	
	<div class="addModel" style="display:none;">
		<div class="layui-form" style="padding: 10px;">
		    <div class="layui-inline ">
		       <button type="button" class="layui-btn"  lay-filter="*" onclick="saveArticle();">保存</button>
		    </div>
		</div>
		<div class="layui-form article-form">
		    <input type="hidden" placeholder="请输入内容" name="id" class="layui-input articleitem">
			<div class="layui-form-item">
				<label class="layui-form-label">文章标题</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="title" class="layui-input articleitem">
			    </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">文章分类</label>
			    <div class="layui-input-block ">
			      	<select name="classes" lay-filter="classes" class="classes">
			       		<option value="">选择标签</option>
				        <option value="0">关于我们</option>
				        <option value="1">用户协议</option>
			      </select>
			    </div>
			</div>
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">文章详情</label>
			    <script type="text/plain" class="myEditor" id="myEditor" style=""></script>
			</div>
			
		</div>
	</div>
	<!--查看详情-->
	<div class="showModel" style="display:none;">
		<div class="showcontent">
		
		</div>
	</div>
	
	
	
</html>