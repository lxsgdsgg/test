<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<body>
<div class="layui-container">
	<form class="layui-form"  id="Form" style="margin-top: 20px;">
				 <input name="id" type="hidden" value="${houseAticle.id}">
			    <div class="layui-form-item"  id="area_select">
				    <label class="layui-form-label"><span style="color:red;">*</span>所属城市</label>
				    <div class="layui-input-inline">
				     	<input type="text" id="cityName" lay-verify="required"  value="${houseAticle.cityName}" class="layui-input" placeholder="请选择">
					</div>
					<!--提交的区域代码  -->
					 <input name="cityCode"  type="hidden" id="cityCode" value="${houseAticle.cityCode}">
	 	  		</div>
	
	  <div class="layui-form-item">
	  	   <label class="layui-form-label"><span style="color:red;">*</span>等级</label>
		    <div class="layui-input-inline">

					<select  name="level"  id = "level" lay-filter="level_select">
					
			    		<c:forEach var="level" items="${level}">
			    			<c:if test="${houseAticle.level==level.value}">
			    				<option value="${level.value}" selected="selected">${level.name}</option>
			    			</c:if>
		    				<c:if test="${houseAticle.level!=level.value}">
			    				<option value="${level.value}">${level.name}</option>
			    			</c:if>
			    		</c:forEach>
			    	</select>
		    </div>
	  </div>
	    <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>类别名称</label>
		    <div class="layui-input-inline">
		         <input type="text" name="name" required lay-verify="required" placeholder="名称"  autocomplete="off" class="layui-input"  value="${houseAticle.name}">
   		    	 <div style="color:red;" id="ValidateMsg"></div>
		    </div>
	  </div>
  	  <div class="layui-form-item">
		    <label class="layui-form-label">上级类别</label>
		    <div class="layui-input-inline">
		        <select name="parentId" id="parentId">
		        		<c:if test="${houseAticle.parentName!=null}">
    		  	    			<option  value="${houseAticle.parentId}">${houseAticle.parentName}</option>
		        		</c:if>
			    </select>
		    </div>
	  </div>
	    
	  <div class="layui-form-item">
		    <label class="layui-form-label">类型</label>
		    <div class="layui-input-inline">
		    	<select id="type" name="type">
		    			<c:forEach var="houseArticleType" items="${houseArticleType}">
			    			<c:if test="${houseArticleType.value==houseAticle.type}">
			    				<option value="${houseArticleType.value}" selected="selected">${houseArticleType.name}</option>
			    			</c:if>
			    			<c:if test="${houseArticleType.value!=houseAticle.type}">
			    				<option value="${houseArticleType.value}">${houseArticleType.name}</option>
			    			</c:if>
		    					
		    			</c:forEach>
			    </select>
		    </div>
	  </div>
    <c:if test="${roleGroup==ADMIN}">
  	   <label class="layui-form-label">是否全局显示</label>
	    <div class="layui-input-inline">
	      <input type="checkbox" lay-filter="isOpen" lay-skin="switch" ${houseAticle.isOpen==1?'checked':null} lay-text="是|否">
	    	<input type="hidden" name="isOpen" id="isOpen" value="${houseAticle!=null?houseAticle.isOpen:0}"/>
	     </div>
	  </c:if>
	  
	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="housePropertyArticleClassesAdd">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/javascript">
var areaList=JSON.parse('${areaList}');
var a= new AreaObject(areaList,selectArea,2,$("#area_select"));
function selectArea(code,name){
		$("#cityName").val(name);
		$("#cityCode").val(code);
}
layui.use('form', function(){
	  var form = layui.form;
	  var $ = layui.$ //重点处
	  ,layer = layui.layer;
	  //监听提交
	  form.on('submit(housePropertyArticleClassesAdd)', function(data){
		  var parentId=  $("#parentId").val();
		  if(parentId==null)
			  data.field.parentId=0
	    $.ajax({
	    	type:"post",
	    	url:"saveOrUpdateHousePropertyArticleClasses",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(data.field)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	  })
	  	//选着等级时触发上级类别下拉框
	   	form.on('select(level_select)', function(data){
	   	//获取城市编码
	   	var cityCode =	$("#cityCode").val();
	   	if(data.value!=1){
	   	   $.ajax({
		    	type:"post",
		    	url:"queryHousePropertyArticleClassesNameByLevel",
		    	dataType:"json",
		    	data:{level:data.value-1,cityCode:cityCode},
		    	success:function(test){
		    		var value = data.value-1//当前等级减一 ，3  3-1=2
		   			var level = test;//等级
			   		var parentLevel_select = "";
// 			   		for(var i=1;i<=value;i++){
			   				for(var j=0;j<level.length;j++){
			   					parentLevel_select+='<option value="'+level[j].id+'">'+level[j].name+'</option>';
			   				}
// 			   		}
			   		$("#parentId").html(parentLevel_select);
					form.render('select');//渲染
		    	},
		    });
	   	   return false;
	   	}else{
	   		parentLevel_select="";
	   		$("#parentId").html(parentLevel_select);
			form.render('select');//渲染
	   		}  
		}); 
	  
	  
	   if("${houseAticle.type}"!=""){
		   $("#type").val('${houseAticle.type}');
		   form.render('select');
	   };
	   if("${houseAticle.parentId}"!=""){
		   $("#parentId").val('${houseAticle.parentId}');
		   form.render('select');
	   };
	   //监听数据权限标识操作
	   form.on('switch(isOpen)', function(data){
	 	  if(this.checked==true)
	 		  $("#isOpen").val("1");
	 	  else 
	 		  $("#isOpen").val("0");
	 	});
	});

</script>
</body>
</html>