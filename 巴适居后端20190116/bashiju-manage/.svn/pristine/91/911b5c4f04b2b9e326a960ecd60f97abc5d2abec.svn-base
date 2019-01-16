<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市地铁线路详情页</title>
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
</head>
<body>
<div class="layui-container">
	<form class="layui-form"   style="margin-top: 20%;margin-left: 20%" >
      <div class="layui-form-item">
		    <input name="id"  type="hidden"  value="${metroInfo.id}">
		    <label class="layui-form-label"><span style="color:red;">*</span>所属城市</label>
		    <div class="layui-input-inline">
		     	<input type="text" id="cityName" lay-verify="required" value="${metroInfo.cityName}" class="layui-input" placeholder="请选择">
			    <input name="cityCode"  type="hidden" id="cityCode" value="${metroInfo.cityCode}">
			</div>
  	  </div>	
  	  <div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>地铁线路名称</label>
			    <div class="layui-input-inline">
						<input class="layui-input" name="name"  value="${metroInfo.name}">   		
			    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="metroAdd">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/javascript">
var areaList=JSON.parse('${areaList}');
var a= new AreaObject(areaList,selectArea,2,$("#cityName"));
function selectArea(code,name){
	$("#cityName").val(name);
	$("#cityCode").val(code);
}
layui.use('form', function(){
	  var form = layui.form;
	  var $ = layui.$ //重点处
	  form.on('submit(metroAdd)', function(data){
	    $.ajax({
	    	type:"post",
	    	url:"saveOrUpdateMetro",
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
})
</script>
</body>
</html>