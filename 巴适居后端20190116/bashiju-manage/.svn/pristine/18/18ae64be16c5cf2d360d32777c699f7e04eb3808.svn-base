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
			       		<option value="0">未审核</option>
			       		<option value="1">已审核</option>
			       		<option value="2">驳回</option>
			       </select>
			    </div>
				<div class="layui-inline ">
				   <label class="layui-form-label">标题</label>
			       <input type="text"  class="layui-input search-title" >
			    </div>
				<div class="layui-inline ">
				   <label class="layui-form-label">类型</label>
			       <input type="text"  class="layui-input search-classesname" >
			    </div>
			    <div class="layui-inline ">
			       <label class="layui-form-label">作者</label>
			       <input type="text"  class="layui-input search-author" >
			    </div>
			    <div class="layui-inline ">
			       <label class="layui-form-label">来源</label>
			       <input type="text"  class="layui-input search-sources" >
			    </div>
			    
			    <div class="layui-inline ">
			    	<label class="layui-form-label"></label>
			    	<button type="button" class="layui-btn searchArticle"  lay-filter="*" >查找</button>
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
				     {field:"type",title:"类型",width:'80'},
			  		 {field:"title",title:"文章标题",width:'180'},
			 		 {field:"abstracts",title:"文章摘要",width:'80'},
			 		 {field:"typeName",title:"文章分类"},
			 		 {field:"author",title:"文章作者"},
			 		 {field:"sources",title:"文章来源"},
			 		 {field:"sourcesLink",title:"来源链接",width:'180'},
			 		 {field:"browseCnt",title:"浏览次数",width:'180'},
			 		 {field:"isOpen",title:"是否对所有城市开放",width:'180'},
			 		 {field:"isPublished",title:"是否发表",width:'180'},
			 		 {field:"examineStatus",title:"审核状态",width:'180'},
			 		 {field:"reason",title:"驳回原因",width:'180'},
			 		 {field:"auditor",title:"审核人",width:'180'},
			 		 {field:"auditTime",title:"审核时间",width:'180'},
			 		 {field:"labels",title:"标签",width:'180'},
			 		 {field:"likedCnt",title:"点赞次数",width:'180'},
			 		 {field:"isSetTop",title:"是否置顶",width:'180'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
			  var conditions = {};
			  table.render({
			    elem: '#articleList'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/manage/getExamineList" //数据接口
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
					 var status = $(".status").val();
					 var title = $(".search-title").val();
					 var classesname = $(".search-classesname").val(); 
					 var author = $(".search-author").val();
					 var sources = $(".search-sources").val();
					 var conditions = {
							 status:status,
							 title:title,
							 classesname:classesname,	
							 author:author,
							 sources:sources,
					 };
					
					 table.render({
						    elem: '#articleList'
						    ,height: 500
						    ,url:"<%=request.getContextPath()%>/manage/getExamineList" //数据接口
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
				    if(obj.event === 'sure'){
				    	layer.confirm('确认通过吗?', {icon: 3, title:'提示'}, function(index){
				    		  $.post("<%=request.getContextPath()%>/manage/sureArticle",{id:id},function(d){
				    			  if(d.success){
					            		alert("成功！");
					            		
					            	}else{
					            		alert(d.msg);
					            	}
				    		  })
				    		  
				    		  layer.close(index);
				    	});
				    }else if(obj.event === 'reject'){
				    	$(".reject-id").val(id);
				    	layer.open({
							type: 1,
							title:'驳回' ,
							shade: [0],
							area: ['300px', '260px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.rejectModel')  //显示的url，no代表不显示滚动条
							
						});
				    }else if(obj.event === 'show'){
				    	$(".showcontent").html(data.content)
				    	layer.open({
							type: 1,
							title:'文章内容' ,
							shade: [0],
							area: ['800px', '660px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.showModel')  //显示的url，no代表不显示滚动条
							
						});
				    }
			});
			
			
			
			 
			
			 
		});
	    
	    function rejectArticle(){
	    	var id = $(".reject-id").val();
	    	var reason = $(".reject-reason").val();
	    	$.post("<%=request.getContextPath()%>/manage/rejectArticle",{id:id,reason:reason},function(d){
	    		 if(d.success){
	            		alert("已驳回！");
	            		
	            	}else{
	            		alert(d.msg);
	            	}
	    	})
	    	
		    
		    
	    }
		
		</script>
		
		<script type="text/html" id="barDemo" >
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="show">查看</a>
		  <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="sure">确认</a>
          <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="reject">驳回</a>
        </script>
        
	</body>	
	<div class="showModel" style="display:none;">
		<div class="showcontent">
		
		</div>
	</div>
	
	<div class="rejectModel" style="display:none;">
		<div class="layui-form" style="padding: 10px;">
			<input type="hidden"  name="id" class="layui-input reject-id">
			<div class="layui-form-item">
				<label class="layui-form-label">驳回原因</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="reason" class="layui-input reject-reason">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block ">
			      <button type="button" class="layui-btn"  lay-filter="*" onclick="rejectArticle();">保存</button>
			    </div>
			</div>
		</div>
			
	</div>
</html>