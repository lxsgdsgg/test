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
			top: 36px;
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
			    
			    <div class="layui-inline ">
			    	<label class="layui-form-label"></label>
			    	<button type="button" class="layui-btn layui-btn-danger openEdit"  lay-filter="*" >编辑文章</button>
			    </div>
			    
		    </div>
			
			<table id="articleList" lay-filter="articleList" ></table>
		</div>
		
		<script>
		$(function(){
			//选区域
			   articleClassList=JSON.parse('${articleClassList}');
		})
	    layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  var upload = layui.upload
			  
			  
			  cols = [
			  		 {field:"title",title:"文章标题",width:'180'},
			 		 {field:"abstracts",title:"文章摘要",width:'160'},
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
			 		 {field:"labels",title:"标签",width:'180'},
			 		 {field:"likedCnt",title:"点赞次数",width:'180'},
			 		 {field:"isSetTop",title:"是否置顶",width:'180'},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
			  var conditions = {type:"information"};
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
					 var author = $(".search-author").val();
					 var sources = $(".search-sources").val();
					 var conditions = {
							 type:"information",
							 title:title,
							 classesname:classesname,	
							 author:author,
							 sources:sources,
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
				    	if(data.isOpen==1){
			    			$(".isOpen").attr("checked",true);
			    		}else{
			    			$(".isOpen").attr("checked",false);
			    		}
			    		
			    		if(data.isSetTop==1){
			    			$(".isSetTop").attr("checked",true);
			    		}else{
			    			$(".isSetTop").attr("checked",false);
			    		}
			    		$(".labels").val(data.labelids);
			    		form.render();
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
			
			 $(".openUpload").click(function(){
				 layer.open({
						type: 1,
						title:'上传封面图',
						shade: [0],
						area: ['300px', '260px'],
						offset: 'auto', //弹出位置
						btnAlign: 'r',
						content:$('.uploadImgModel')  //显示的url，no代表不显示滚动条
						
					});
			 })
			 
			//上传图片
			  upload.render({
				  elem: '#uploadimg'
				  ,url: '<%=request.getContextPath()%>/manage/uploadImg/'
				  ,auto: true //选择文件后不自动上传
				  ,accept:'images'
				  ,multiple:false
				  ,data:{}
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    //lookId = $(".lookId").val();
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				    	
				    });
				  }
				  ,done: function(result){
				      //上传完毕回调
					  if(result.success){
		            		alert("成功！");
		            		$(".openUpload").val(result.data);
		            	}else{
		            		alert(result.msg);
		            	}
				   }
			});      
			 
		});
	    
	    function saveArticle(){
	    	
	    	var data  = {};
	    	data.type="information";
	    	var inputs = $(".article-form").find(".articleitem");
	    	for(var i = 0;i<inputs.length;i++){
	    		var name = $(inputs[i]).attr("name");
	    		var val = $(inputs[i]).val();
	    		data[name] = val;
	    	}
	    	/* var labelids = $("select[name='labels']").val();
		    var options = $("select[name='labels']").find("option");
		    for(var i=0;i<options.length;i++){
			 var val = $(options[i]).attr("value");
			 if(labelids == val)
				 var labels = $(options[i]).text();
		    }
		    data.labelids = labelids;
		    data.labels = labels; */
		    var content = UM.getEditor('myEditor').getContent();
		    data.content = content;
		    
		    if( $(".isOpen").is(":checked")){
				data.isOpen = 1;
			}
		    if( $(".isSetTop").is(":checked")){
				data.isSetTop = 1;
			}
		    
		    
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
	    
		function getCity(){
			<%-- var userId = '${userId}';
			$.post("<%=request.getContextPath()%>/manage/getCity",{userId:userId},function(d){
				$(".select-city").empty();
				for(var i=0;i<d.data.length;i++){
					var option = '<option value="'+d.data[i].cityCode+'">'+d.data[i].name+'</option>';
					$(".select-city").append(option);
				}
				form.render();
				
				var cityCode = $(".select-city").val();
				var type = 1;
				getClasses(cityCode,type);
				
			}) --%>
			
			classopen = layer.open({
				type: 1,
				title:'选择分类',
				shade: [0],
				area: ['400px', '360px'],
				offset: 'auto', //弹出位置
				btnAlign: 'r',
				content:$('.typeModel')  //显示的url，no代表不显示滚动条
				
			});
			form.on('select(select-city)', function(data){
				  var cityCode = data.value; //得到被选中的值
				  $("input[name='cityCode']").val(cityCode);
				  getClasses(cityCode);
			});
		}
		
		
		
		function getClasses(cityCode){
			
			for(var key in articleClassList){
				var d = articleClassList[key];
				$(".type-box").empty();
				if(cityCode==key){
					for(var i=0;i<d.length;i++ ){
						if(d[i].parentId==0){
							var pdiv = $('<div style="font-size:16px;" class="ptype" id="'+d[i].id+'" onclick="selectOneClass(this);">'+d[i].name
							         +'</div>' )
							$(".type-box").append(pdiv);
						}else{
							var cdiv = $('<div class="ctype" id="'+d[i].id+'" onclick="selectOneClass(this);">'+d[i].name
							         +'</div>' )
							$("#"+d[i].parentId).append(cdiv);
						}
						
						
					}
				}
				
			}
			
		}
		
		function selectOneClass(obj){
			var id = $(obj).attr("id");
			var name = $(obj).text();
			$(".typeId").val(id);
			$(".typeName").val(name);
			layer.close(classopen);
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
		       <input type="checkbox" name="" title="对所有城市开放" value="" class="isOpen" filter="">
		    </div>
		    <div class="layui-inline ">
		       <input type="checkbox" name="" title="置顶" value="" class="isSetTop" filter="">
		    </div>
		    
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
				<label class="layui-form-label">文章摘要</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="abstracts" class="layui-input articleitem">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章分类</label>
			    <div class="layui-input-block ">
			      <input type="hidden" name="classesId" class="layui-input typeId articleitem" value="">
			      <input type="text" placeholder="请输入内容" name="typeName" class="layui-input typeName" onclick="getCity();">
			    </div>
			</div>
			<div class="layui-form-item ">
		       <label class="layui-form-label">文章标签</label>
			   <div class="layui-input-block ">
			     <input type="text" placeholder="请输入内容" name="labels" class="layui-input articleitem">
			   </div>
		    </div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章作者</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="author" class="layui-input articleitem">
			    </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">文章来源</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="sources" class="layui-input articleitem">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">来源链接</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="sourcesLink" class="layui-input articleitem">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">所属城市</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" readonly="readonly" name="cityCode" class="layui-input articleitem">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章封面</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="cover" class="layui-input openUpload articleitem">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章详情</label>
			    <script type="text/plain" class="myEditor" id="myEditor" style=""></script>
			</div>
			
		</div>
	</div>
	<!--上传封面图片-->
	<div class="showModel" style="display:none;">
		<div class="showcontent">
		
		</div>
	</div>
	<!--上传封面图片-->
	<div class="uploadImgModel" style="display:none;">
		<div class="layui-form">
			
			<div>
				<button type="button" class="layui-btn" id="uploadimg">
				  <i class="layui-icon">&#xe67c;</i>选择图片
				</button>
				<input class="layui-upload-file" type="file" name="file" multiple="false">
				
			</div>
		</div>
		
	</div>
	<!--文章分类-->
	<div class="typeModel" style="display:none;">
		<div>
			<div class="layui-form">
				<div class="layui-form-item">
				<label class="layui-form-label">选择城市</label>
			    <div class="layui-input-block ">
			      <select class="select-city" lay-filter="select-city">
			      	<option value="">请选择</option>
			      	<c:forEach var="city" items="${cityList}">
			      		<option value="${city.cityCode}">${city.cityName}</option>
			      	</c:forEach>
			      </select>
			    </div>
			</div>
			</div>
			<div class="type-box">
			
			</div>
		</div>
		
	</div>
	
</html>