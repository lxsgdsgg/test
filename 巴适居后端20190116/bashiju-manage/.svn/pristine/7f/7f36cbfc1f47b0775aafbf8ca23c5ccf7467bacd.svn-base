<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司管理</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<style type="text/css">
.areaDiv {
    position: fixed;
    top: 200px;
    left: 0;
    z-index: 99891010;
    background-color: rgb(238, 238, 238);
    box-shadow: rgba(0, 0, 0, 0.3) 1px 1px 50px;
    margin: auto;
    border-radius: 2px;
}
</style>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="companyForm" style="margin-top: 20px;">
		<%-- <input type="hidden" name="id" value="${company.id}"/> --%>
	  <div class="layui-form-item">
	  	  <label class="layui-form-label"><span style="color:red;">*</span>公司编号</label>
	    <div class="layui-input-inline">
	      <input type="text" name="id" ${company!=null?"readonly":null} id="companyId"required lay-verify="required" placeholder="请输入公司编号" value="${company.id}" autocomplete="off" class="layui-input" onchange="query.queryCompanyId(this.value)">
	      <div style="color:red;" id="idValidateMsg"></div>
	    </div>
	  
	    <label class="layui-form-label"><span style="color:red;">*</span>公司名称</label>
	    <div class="layui-input-inline">
	      <input type="text" name="name" required lay-verify="required" placeholder="请输入公司名称" value="${company.name}" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	  
	  	    
	    <label class="layui-form-label"><span style="color:red;">*</span>法定代表人名称</label>
	    <div class="layui-input-inline">
	    	<input type="text" name="legrepname" required lay-verify="required" placeholder="请输入法定代表人" value="${company.legrepname}" autocomplete="off" class="layui-input">
	    </div>
	  
	    <label class="layui-form-label"><span style="color:red;">*</span>法人证件类型</label>
	    <div class="layui-input-inline">
	    	<select name="doctype" lay-verify="required">
		    	<c:forEach var="doctype" items="${doctype}">
		    		<c:if test="${company.doctype==doctype.key}">
			        	<option value="${doctype.key}" selected="selected">${doctype.value}</option>
			        </c:if>
			        <c:if test="${company.doctype!=doctype.key}">
			        	<option value="${doctype.key}">${doctype.value}</option>
			        </c:if>
		    	</c:forEach>
		      </select>
	    </div>
	    
	  
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>法人证件号码</label>
	    <div class="layui-input-inline">
	    	<input type="text" name="doccode" required lay-verify="required" placeholder="请输入法人证件号码" value="${company.doccode}" autocomplete="off" class="layui-input">
	    </div>
	  
	    <label class="layui-form-label"><span style="color:red;">*</span>法代证件类型</label>
	    <div class="layui-input-inline">

	    	<select name="legdoctype" lay-verify="required">
		    	<c:forEach var="legdoctype" items="${legdoctype}">
		    		<c:if test="${company.legdoctype==legdoctype.key}">
			        	<option value="${legdoctype.key}" selected="selected">${legdoctype.value}</option>
			        </c:if>
			        <c:if test="${company.legdoctype!=legdoctype.key}">
			        	<option value="${legdoctype.key}">${legdoctype.value}</option>
			        </c:if>
		    	</c:forEach>
		      </select>
	    </div>
	    
	 
	  </div>
	  <!--联系人  -->
		  <div class="layui-form-item">
		  		 <label class="layui-form-label"><span style="color:red;">*</span>法定代表人证件号码</label>
				    <div class="layui-input-inline">
				    	<input type="text" name="legdoccode" id="legdoccode" required lay-verify="required" placeholder="请输入法定代表人证件号码" value="${company.legdoccode}" autocomplete="off" class="layui-input">
				    </div>
		  
		  
		  
	  			   <label class="layui-form-label"><span style="color:red;">*</span>法人电话号码</label>
				    <div class="layui-input-inline">
				    	<input type="text" name="legdocmobile" id="legdocmobile"  lay-verify="required|phone|number" placeholder="请输入法人电话号码" value="${company.legdocmobile}" autocomplete="off" class="layui-input">
				    </div>
		  			
		  
		  		
	    			
		  </div>
		  <!--公司-->
		   <div class="layui-form-item">
		   		   <label class="layui-form-label"><span style="color:red;">*</span>联系人名称</label>
		  		   <div class="layui-input-inline">
	    				<input type="text" name="contname" required lay-verify="required" placeholder="请输入联系人名称" value="${company.contname}" autocomplete="off" class="layui-input">
	    			</div>
		   
		   
		   
		   
		   			<label class="layui-form-label"><span style="color:red;">*</span>联系人电话</label>
    			   <div class="layui-input-inline">
    					<input type="text" name="contphone" required lay-verify="phone|number" placeholder="请输入联系人电话" value="${company.contphone}" autocomplete="off" class="layui-input">
    			   </div>
		    	
    			 
		   </div>
		    <div class="layui-form-item">
		    	 <label class="layui-form-label"><span style="color:red;">*</span>所属城市</label>
				    <div class="layui-input-inline">
				     	<input type="text"  lay-verify="required"  value="${cityName.name}" id="regaddrcity" class="layui-input" placeholder="请选择">
					</div>
				     	<input type="text" name="regaddrcity" style="display: none" id="regaddrcityCode" lay-verify="required" onClick="showAreaDiv()" class="layui-input" value="${company.regaddrcity}" placeholder="请选择">
    			 
		    
		    
	    		 <label class="layui-form-label"><span style="color:red;">*</span>公司注册地址</label>
		   		 <div class="layui-input-inline">
    					<input type="text" name="regaddrdetail" required lay-verify="required" placeholder="请输入公司注册地址" value="${company.regaddrdetail}" autocomplete="off" class="layui-input">
    			 </div>
    			
		    </div>
		    
	     <div class="layui-form-item">
	     		 <label class="layui-form-label">公司邮箱</label>
		   		 <div class="layui-input-inline">
    					<input type="text" name="cusemail"  lay-verify="cusemail" placeholder="请输入公司邮箱" value="${company.cusemail}" autocomplete="off" class="layui-input">
    			 </div>
    			
	     
	     
	     
			    <label class="layui-form-label">公司网址</label>
			    <div class="layui-input-inline">
			      <input type="text" name="url"  placeholder="请输入公司网址" value="${company.url}" autocomplete="off" class="layui-input">
			    </div>
			    
			  
			   
			   
	     </div>
	        <div class="layui-form-item">
	        	
	     		   <label class="layui-form-label"><span style="color:red;">*</span>状态</label>
			    <div class="layui-input-inline">
			    	<input type="text" name="status" required lay-verify="required" placeholder="请输入状态" value="${company.status}" autocomplete="off" class="layui-input">
			    </div> 
			    
			    
		  	</div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="companyForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script>
var areaList=JSON.parse('${areaListJson}');
//areaList,selectArea(回掉函数)
var a= new AreaObject(areaList,selectArea,2);
//点击显示选择区域控件
function showAreaDiv(){	
		a.show();
	}
//选中区域以后
function selectArea(code,name){
		$("#regaddrcity").val(name);
		$("#regaddrcityCode").val(code)
		
}
var query={
		queryCompanyId:function(obj){//查询公司编号是否已存在
			$.ajax({
				url:"queryCompanyById",
				dataType:"json",
				data:{"companyId":obj},	
				success:function(data){
					if(data.success){
						$("#idValidateMsg").html("");
					}else{
						
						$("#idValidateMsg").text(data.msg)
						//清空
						$("#companyId").val("")
					}
					
				}
			})
		}
}



layui.use('form', function(){
  var form = layui.form;
  
  var $ = layui.$ //重点处
  ,layer = layui.layer;
  //监听提交
  form.on('submit(companyForm)', function(data){
    $.ajax({
    	type:"post",
    	url:"saveOrUpdateCompany",
    	dataType:"json",
    	data:{jsonData:JSON.stringify(data.field)},
    	success:function(data){
    		if(data.success){
    			layer.msg(data.msg);
    			parent.layer.closeAll();
    		}else{
    			layer.alert(data.msg);
    		}
    	},
    	error:function(a,b,c){
    		layer.alert("操作异常，请稍后重试");
    	}
    });
    return false;
  });
  
  form.verify({
	  //我们既支持上述函数式的方式，也支持下述数组的形式
	  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
	  //联系人电话
	 legdocmobile: [
		 /^1(3|4|5|7|8)\d{9}$/
	    ,'法人必须为11位，且不能出现空格'
	  ],
	  cusemail: function(value, item){ //value：表单的值、item：表单的DOM对象
		if(value!=""){
			var regx = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
		    if(!regx.test(value)){
		      return '你输入的邮箱格式有误，请重新输入';
		    }
		}
	  }
	}); 
  
  $("#regaddrcity").on("click",function(event){
		 event.stopPropagation();
		 a.show();
	});
});
</script> 
</body>
</html>