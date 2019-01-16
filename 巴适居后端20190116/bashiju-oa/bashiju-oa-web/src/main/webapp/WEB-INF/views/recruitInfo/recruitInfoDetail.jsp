<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘编辑页面</title>
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" style="margin-top: 20px;" name="res_house">
		<input type="text" name="id" id="id" value="${recruitInfo.id}" autocomplete="off" class="layui-input" style="width:400px;display: none ">
	   	<div class="layui-form-item">
	 		<label class="layui-form-label"><span style="color:red;">*</span>工作城市：</label>
		    <div class="layui-input-inline">
				<input type="text" name="workCityCode"   value="${recruitInfo.workCityName}" required  lay-verify="required" placeholder="城市名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
		     <label class="layui-form-label"><span style="color:red;">*</span>岗位名称：</label>
		    <div class="layui-input-inline">
		   		 <input type="text"  name="name"   value="${recruitInfo.name}" required  lay-verify="required" placeholder="岗位名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	 	</div>
	   <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>招聘人数:</label>
		    <div class="layui-input-inline">
					<input type="text" name="count"  value="${recruitInfo.count}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
    	    <label class="layui-form-label" id="area_label">学历要求:</label>
		    <div class="layui-input-inline">
				<select name="education">
				<c:forEach var="educationsEnum" items="${educationsEnum}">
					<c:if test="${recruitInfo.workCityCode==educationsEnum.value}">
						<option value="${educationsEnum.value}" selected="selected">${educationsEnum.name}</option>
					</c:if>
					<c:if test="${recruitInfo.workCityCode!=educationsEnum.value}">
						<option value="${educationsEnum.value}">${educationsEnum.name}</option>
					</c:if>
				</c:forEach>
				</select>
		    </div>
	   </div>
	  <hr class="layui-bg-gray">
  	  <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>所需专业:</label>
		    <div class="layui-input-inline">
					<input type="text" name="majorSubject"  value="${recruitInfo.majorSubject}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
    	    <label class="layui-form-label" >最低工作年限:</label>
		    <div class="layui-input-inline">
   					<input type="text" name="minWorking"  value="${recruitInfo.minWorking}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	   </div>
	   <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>最高工作年限:</label>
		    <div class="layui-input-inline">
					<input type="text" name="maxWorking"  value="${recruitInfo.maxWorking}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
    	    <label class="layui-form-label">薪资:</label>
		    <div class="layui-input-inline">
   					<input type="text" name="wages"  value="${recruitInfo.wages}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	   </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">招聘电话：</label>
		    <div class="layui-input-inline">
   					<input type="text" name="mobile"  value="${recruitInfo.mobile}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
		    <label class="layui-form-label">招聘截止时间：</label>
		    <div class="layui-input-inline">
   					<input type="text" name="endTime" id="endTime" value="${recruitInfo.endTime}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	  </div>
	   <div class="layui-form-item">
	   	    <label class="layui-form-label">任职要求：</label>
		    <div class="layui-input-inline">
   					<input type="text" name="requirement"  value="${recruitInfo.requirement}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
		     <label class="layui-form-label">主要职责：</label>
		    <div class="layui-input-inline">
   					<input type="text" name="duty"  value="${recruitInfo.duty}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	   </div>
      	<div class="layui-form-item">
	 		<label class="layui-form-label"><span style="color:red;">*</span>职位发布人：</label>
		    <div class="layui-input-inline">
				<input type="text" name="publisher" value="${recruitInfo.publisher}" required  lay-verify="required" placeholder="城市名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
		     <label class="layui-form-label"><span style="color:red;">*</span>职位发布时间：</label>
		    <div class="layui-input-inline">
		   		 <input type="text"  name="publishTime" id="publishTime" value="${recruitInfo.publishTime}" required  lay-verify="required" placeholder="岗位名称" autocomplete="off" class="layui-input" style="width:200px">
		    </div>
	 	</div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="recruitInfoSaveOrUpdate">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" lay-submit lay-filter="close">取消</button>
	    </div>
	  </div>
	  </form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/javascript">
layui.use(['form','laydate'], function(){
   var form = layui.form;
	var laydate = layui.laydate;//时间控件
     laydate.render({
        elem: '#endTime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        	date.month = date.month-1;
        }
    });
     laydate.render({
        elem: '#publishTime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        	date.month = date.month-1;
        }
    });
   form.on('submit(recruitInfoSaveOrUpdate)', function(data){
// 	  alert(JSON.stringify(data.field))
// 	   return false;
		$.ajax({
	    	type:"post",
	    	url:"saveOrUpdateRecruitInfo",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(data.field)},
	    	success:function(dt){
	    		if(dt.success){
	    			layer.alert(dt.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(dt.msg);
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