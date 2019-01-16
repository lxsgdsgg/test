<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理编辑页面</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<style type="text/css">
/*  选择区域*/
.areaDiv {
    min-height: 300px;
    position: fixed;
    top: 0px;
    left: 50px;
    margin: auto;
    z-index: 99891010;
    background-color: #eeeeee;
    border-radius: 2px;
    box-shadow: 1px 1px 50px rgba(0,0,0,.3);
}
.ulData {
    height: 300px;
    overflow-y: scroll;
}
</style>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="deptForm" style="margin-top: 20px;">
		

	  <div class="layui-form-item">
	    <div class="layui-input-inline" style="display: none">
	      <input type="hidden" name="id"  value="${deptNow.id}" autocomplete="off" class="layui-input">
	    </div>
	  
	    <label class="layui-form-label"><span style="color:red;">*</span>部门名称</label>
	    <div class="layui-input-inline">
	      <input type="text" name="name" required lay-verify="required" placeholder="请输入菜单名称" value="${deptNow.name}" autocomplete="off" class="layui-input">
	    </div>
		
	    <label class="layui-form-label"><span style="color:red;">*</span>公司名称</label>
	    
	   <c:if test="${deptNow!=null}">
	   		 <div class="layui-input-inline">
    				 <input type="text"   value="${deptNow.companyName}" disabled="disabled" autocomplete="off" class="layui-input">
   					 <input type="text"   name="parentId" value="${deptNow.companyId}" disabled="disabled" style="display: none" autocomplete="off" class="layui-input">
   			</div>
	   </c:if>
	    <c:if test="${deptNow==null}">
	    	  <div class="layui-input-inline">
		   	<select name="companyId" id="companyId" lay-verify="required"  lay-filter="company">
		   			<option value="">请选择</option>
			        <c:forEach var="company" items="${companys}">
	  				 	<c:if test="${deptNow.companyId==company.id}">
	  						<option value="${company.id}" selected="selected">${company.name}</option>
	  					</c:if>
	  					<c:if test="${deptNow.companyId!=company.id && company.id!=deptNow.companyId}">
	  								<option value="${company.id}">${company.name}</option>
	  					</c:if> 
			        </c:forEach>
	        </select>
	    </div>
	    </c:if>
	  
	  </div>
	  <div class="layui-form-item">
	  
	    <label class="layui-form-label"><span style="color:red;">*</span>部门类型</label>
	    <c:if test="${deptNow!=null}">
	    	 <div class="layui-input-inline">
   				 <input type="text"   value="${deptNow.deptName}" disabled="disabled" autocomplete="off" class="layui-input">
				 <input type="text"   name="parentId" value="${deptNow.deptTypeId}" disabled="disabled" style="display: none" autocomplete="off" class="layui-input">
   			</div>
	    </c:if>
	    <c:if test="${deptNow==null}">
		     <div class="layui-input-inline">
		
			    	<input type="hidden" value="${deptNow.deptTypeId}" id ="deptTypeId" name="deptTypeId"/>
		    	 <select  lay-verify="required" lay-filter="deptType_select" id="deptType">
			    		<option value="">请选择</option> 
				    	<c:forEach var="deptTypes" items="${deptTypes}">
				    		<c:if test="${deptNow.deptTypeId==deptTypes.id}">
									<option value="${deptTypes.id}_${deptTypes.parentId}" selected="selected">${deptTypes.name}</option> 	    		
				    		</c:if>
				    		<c:if test="${deptNow.deptTypeId!=deptTypes.id}">
									<option value="${deptTypes.id}_${deptTypes.parentId}">${deptTypes.name}</option> 	    		
				    		</c:if>
				    		<!--当前部门类型的值拼接上部门类型父级的值-->
				     		
				    	</c:forEach>
			      </select>
		    </div>
	    </c:if>

	    <label class="layui-form-label"><span style="color:red;">*</span>上级名称</label>
	    <div class="layui-input-inline">
	    <c:if test="${deptNow!=null}">
   			 <div class="layui-input-inline">
    				 <input type="text"   value="${deptNow.parentName}" disabled="disabled" autocomplete="off" class="layui-input">
   					 <input type="text"   name="parentId" value="${deptNow.parentId}" disabled="disabled" style="display: none" autocomplete="off" class="layui-input">
   			</div>
     	</c:if>
     	
     	<c:if test="${deptNow==null}">
   			 <select name="parentId" id="parentId" required lay-verify="required" >
	    		<option value="">请选择</option>
	    		<c:forEach var="depts" items="${depts}">
	    			 <c:if test="${deptNow.parentId==depts.id}">
		    	 		<option value="${depts.id}" selected="selected">${depts.name}</option>
		    	 </c:if>
		    	 	<c:if test="${deptNow.parentId!=depts.id}">
		    	 		<option value="${depts.id}">${depts.name}</option>
		    	 </c:if>
	    		
	    		</c:forEach>
     		</select>
     	</c:if>
	    </div>
	  </div>
	  
	  	<div class="layui-form-item" id="urlDiv">
			    <label class="layui-form-label">领导人</label>
			    <div class="layui-input-inline">
					 <select name="leader" id="leader" lay-verify="required">
		     		 </select>
			    </div>
			    
		        <label class="layui-form-label"><span style="color:red;">*</span>排序码</label>
			    <div class="layui-input-inline">
			    	<input type="number" name="sortNo" required lay-verify="required" placeholder="请输入排序码" value="${deptNow.sortNo}" autocomplete="off" class="layui-input">
			    </div>
	  	  <!--区域-->
		  </div>
		<div id="toggleDiv">
		  	<div class="layui-form-item" id="urlDiv1">
			    <div class="layui-form-item"  id="area_select">
				    <label class="layui-form-label"><span style="color:red;">*</span>所属城市</label>
				    <div class="layui-input-inline">
				     	<input type="text" id="areaName" lay-verify="required" onClick="showAreaDiv()" value="${deptNow.oreaName}" class="layui-input" placeholder="请选择">
					</div>
					<!--提交的区域代码  -->
					 <input name="areaCode" type="hidden" id="areaCode" value="${deptNow.areaCode}">
   	        <!--区域  -->
	 	  		</div>
		  </div>
		</div>
	
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="deptForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script>

var urlDiv1=$("#urlDiv1").clone();
//后台区域数据

 var areaList=JSON.parse('${areaListJson}');
 var a= new AreaObject(areaList,selectArea,2);
 //点击显示选择区域控件
/*  function showAreaDiv(){	
		a.show();
	}
  */
 function selectArea(code,name){
		$("#areaName").val(name);
		$("#areaCode").val(code);
 }

 //后台数据
 var depts = '${depts}';
 debugger;
//  var depts = [];
//  if(dpt!=''){
// 	 depts = JSON.parse(dpt);
//  }
 var urs = '${users}';
 var users = [];
 if(urs!=''){
	 users = JSON.parse(urs);
 }
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(deptForm)', function(data){
      layer.confirm('确定要保存操作吗？', function(index){
    $.ajax({
    	type:"post",
    	url:"saveOrUpdateDept",
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
    
  });  
    return false;
  });
	/**
	*根据所选公司获取该公司下边的用户以及部门信息
	* value 所选公司id
	* selectedId 默认选中的用户id
	*/
	function getLeaderAndDeptByCompany(value,selectedId){
		//如果用户和选中公司不为空
	   if(users!=null && value!=""){
			var leader_select = "";
			//遍历users
			for(var i=0;i<users.length;i++){
				var obj = users[i];
				//如果当前用户的公司id等于选中的公司id
				if(obj.companyId==value){
					//如过当前用户的id被选中
					if(obj.id==selectedId)
						leader_select+='<option value="'+obj.id+'" selected="selected">'+obj.realName+'</option>';
					else
						leader_select+='<option value="'+obj.id+'">'+obj.realName+'</option>';
				}
			}
			//向id为leader的select追加option
			$("#leader").html(leader_select);
			form.render('select');
		}
	}
	
	/**
	*根据所选部门类型和公司获取部门信息
	* value 所选部门类型id与父级id的组合(部门类型id_父级类型id)
	* selectedId 默认选中的用户id
	*/
	function getDeptsByDeptTypeAndCompany(value,selectedId){
		//截取选中的值通过“_”;
		var val = value.split("_")[0];//当前部门类型的id
		var parentId = value.split("_")[1];//当前部门类型的父级id
		$("#deptTypeId").val(val); //把当前部门类型id赋值给id等于deptTypeId的输入框
		var companyId = $("#companyId").val();//得到公司的值
		//部门类型为市级显示区域代码
		if(val=="1")
			  $("#toggleDiv").html(urlDiv1);
		  else
			  $("#toggleDiv").html("");
		//部门信息和选中不为空
		if(depts!=null && value!=""){
			var dept_select = "";
			for(var i=0;i<depts.length;i++){
				var obj = depts[i];
				if(obj.deptTypeId==parentId && obj.companyId == companyId){//部门类型id=部门类型的上级id
					if(obj.id==selectedId)
						dept_select+='<option value="'+obj.id+'" selected="selected">'+obj.name+'</option>';
					else
						dept_select+='<option value="'+obj.id+'">'+obj.name+'</option>';
				}
				//职能
				else if(obj.deptTypeId==6 && val==obj.deptTypeId && obj.companyId == companyId){
					if(parentId=='' || val==6){
						dept_select += '<option value=""></option>';//初始化时“请选择”
						$("#parentId") .removeAttr("lay-verify");//移除必填属性
					}
					if(obj.id==selectedId)
						dept_select+='<option value="'+obj.id+'" selected="selected">'+obj.name+'</option>';
					else
						dept_select+='<option value="'+obj.id+'">'+obj.name+'</option>';
				}
			}
			$("#parentId").html(dept_select);
			form.render('select');//渲染
		}
	}
	 //初始化部门类型的值
	 getDeptsByDeptTypeAndCompany("${deptNow.deptTypeId}","${deptNow.parentId}");
	 getLeaderAndDeptByCompany("${deptNow.companyId}","${deptNow.leader}");
	//初始化编辑界面部门类型为‘市’级 
	if("${deptNow.deptTypeId}"=="1"){
		$("#toggleDiv").html(urlDiv1);
	}else{
		$("#toggleDiv").html("");
	}
	
  //监听公司 （根据部门和公司得到领导人信息）
   form.on('select(company)', function(data){
	   //data.value为选中公司的值
	   getLeaderAndDeptByCompany(data.value);
	}); 
  
  //监听部门类型选择操作
   form.on('select(deptType_select)', function(data){
	   getDeptsByDeptTypeAndCompany(data.value);
	   if(data.value=="1_"){
		   $("#areaName").on("click",function(event){
				 event.stopPropagation();
				 a.show();
			});
	   }
	});
  
   $("#areaName").on("click",function(event){
		 event.stopPropagation();
		 a.show();
		});
  
});
</script> 
</body>
</html>