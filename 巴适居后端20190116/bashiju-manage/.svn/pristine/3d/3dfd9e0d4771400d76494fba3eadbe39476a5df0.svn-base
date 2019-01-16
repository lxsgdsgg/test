<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="">
 	<input type="hidden"  value="${deptId}" id="deptId" name="deptId" />
	<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>部门名称</th>
      <th>房源</th>
      <th>求租</th>
      <th>求购</th>
    </tr> 
  </thead>
  <tbody>
    <c:forEach var="dept" items="${depts}">
    	<tr>
    		<td>
    			${dept.name}
    			<input type="hidden" name="overDeptId" value="${dept.id}"/>
    		</td>
    		<td>
    			<input type="checkbox" name="${houseMenuId}" value="${houseMenuId}"/>
    			<input type="hidden" name="menuBtns_house" id="menuBtns_house_${dept.id}"/>
    			<a onclick="addOperatBtn('${dept.id}','house')">选择</a>
    		</td>
    		<td>
    			<input type="checkbox" name="${rentMenuId}" value="${rentMenuId}"/>
    			<input type="hidden" name="menuBtns_rent" id="menuBtns_rent_${dept.id}"/>
    			<a onclick="addOperatBtn('${dept.id}','rent')">选择</a>
    		</td>
    		<td>
    			<input type="checkbox" name="${buyMenuId}" value="${buyMenuId}"/>
    			<input type="hidden" name="menuBtns_buy" id="menuBtns_buy_${dept.id}"/>
    			<a onclick="addOperatBtn('${dept.id}','buy')">选择</a>
    		</td>
    	</tr>
    </c:forEach>
  </tbody>
</table>
<div class="layui-form-item" style="margin-left: 250px">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="acrossStorePermission">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
    </div>
 </div>
</form>
<!-- 房源操作按钮 -->
<div style="display: none;" id="operatBtn_house" class="layui-form">
	<input type="hidden" name="overDeptId"  id="overDeptId"/>
	<c:forEach var="houseBtn" items="${houseBtn}">
		<input type="checkbox" name="${houseBtn.id}" value="${houseBtn.id}" title="${houseBtn.name}"/>&nbsp;&nbsp;
	</c:forEach>
	<button class="layui-btn" lay-submit lay-filter="houseBtnSubmit">立即提交</button>
</div>
<!-- 租赁客源操作按钮 -->
<div style="display: none;" id="operatBtn_rent" class="layui-form">
	<input type="hidden" name="overDeptId"  id="overDeptId"/>
	<c:forEach var="rentBtn" items="${rentBtn}">
		<input type="checkbox" name="${rentBtn.id}" value="${rentBtn.id}" title="${rentBtn.name}"/>&nbsp;&nbsp;
	</c:forEach>
	<button class="layui-btn" lay-submit lay-filter="rentBtnSubmit">立即提交</button>
</div>
<!-- 买卖客源操作按钮 -->
<div style="display: none;" id="operatBtn_buy" class="layui-form">
	<input type="hidden" name="overDeptId" id="overDeptId"/>
	<c:forEach var="buyBtn" items="${buyBtn}">
		<input type="checkbox" name="${buyBtn.id}" value="${buyBtn.id}" title="${buyBtn.name}"/>&nbsp;&nbsp;
	</c:forEach>
	<button class="layui-btn" lay-submit lay-filter="buyBtnSubmit">立即提交</button>
</div>

<%@include file="/common/common.jsp" %>
<script type="text/javascript">

layui.use(['form','table'], function(){
	  var form = layui.form;
	   var $=layui.$;
	   
	   addOperatBtn = function(deptId,type){
		   var title = '跨店权限 ';
			var option = {
		        type: 1 
		        ,id:"acrossStoreFrame"+deptId+type
		        ,title: title
		        ,area: ['90%', '90%']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $("#operatBtn_"+type)
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
					$("#overDeptId").val(deptId);
					var arr = $("#menuBtns_"+type+"_"+deptId).val().split(",");
					for(var i=0;i<arr.length;i++){
						$("#operatBtn_"+type).find("input[name='"+arr[i]+"']").prop("checked",true)
					}
					form.render();
		        }
				,cancel : function(){
					$("#operatBtn_"+type).find("input[type='checkbox']").prop("checked",false);
					form.render();
				}
		      };
			addOperatorIndex = layer.open(option);
	   }
	  form.on('submit(acrossStorePermission)',function(data){
		  var tb = $("table").get(0);
		  var rows = tb.rows;
		  var arr = [];
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input[type=checkbox]:checked");
			  var house = $(row).find("input[name='menuBtns_house']").val();
			  var rent = $(row).find("input[name='menuBtns_rent']").val();
			  var buy = $(row).find("input[name='menuBtns_buy']").val();
			  var overDeptId = $(row).find("input[name='overDeptId']").val();
			  var deptId = $("#deptId").val();
			  if(inputs.length>0){
				  var menuId = "";
				  for(var j=0;j<inputs.length;j++){
					  if(j!=0)
						  menuId +=",";
					  menuId += inputs[j].value; 
				  }
				  if(house.length>0)
					  menuId =menuId+","+house;
				  if(rent.length>0)
					  menuId =menuId+","+rent;
				  if(buy.length>0)
					  menuId =menuId+","+buy;
				  var obj = {
					  deptId:deptId,
					  overDeptId:overDeptId,
					  menuId:menuId
				  };
				  arr.push(obj);
			  }
		  }
		$.ajax({
	    	type:"post",
	    	url:"saveAcrossStorePermission",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(arr),deptId:deptId},
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
	  });
	  
	  form.on('submit(houseBtnSubmit)',function(data){
		  var arr = $("#operatBtn_house").find("input[type='checkbox']:checked");
		  var btnStr = "";
		  for(var i=0;i<arr.length;i++){
			  if(i>0)
				  btnStr +=",";
			  btnStr+=arr[i].value;
		  }
		  addBtns($("#overDeptId").val(),"house",btnStr)
		  layer.close(addOperatorIndex);
		  return false;
	  });
	  
	  form.on('submit(rentBtnSubmit)',function(data){
		  var arr = $("#operatBtn_rent").find("input[type='checkbox']:checked");
		  var btnStr = "";
		  for(var i=0;i<arr.length;i++){
			  if(i>0)
				  btnStr +=",";
			  btnStr+=arr[i].value;
		  }
		  addBtns($("#overDeptId").val(),"rent",btnStr)
		  layer.close(addOperatorIndex);
		  return false;
	  });
	  
	  form.on('submit(buyBtnSubmit)',function(data){
		  var arr = $("#operatBtn_buy").find("input[type='checkbox']:checked");
		  var btnStr = "";
		  for(var i=0;i<arr.length;i++){
			  if(i>0)
				  btnStr +=",";
			  btnStr+=arr[i].value;
		  }
		  addBtns($("#overDeptId").val(),"buy",btnStr)
		  layer.close(addOperatorIndex);
		  return false;
	  });
	  
});

function addBtns(overDeptId,type,addStr){
	var vl = $("#menuBtns_"+type+"_"+overDeptId).val(addStr);
}
</script>
</body>
</html>