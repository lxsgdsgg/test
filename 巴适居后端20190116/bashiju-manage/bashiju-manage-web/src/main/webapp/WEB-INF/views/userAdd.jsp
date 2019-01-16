<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- load css -->
	
    <link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all">
    
<title>Insert title here</title>
</head>
<body>
<div class="layui-form">
<c:forEach items="${formField}" var="formField" varStatus="index" step="1">
	<c:if test="${formField.fieldType eq 1}">
		 ${formField.title}:<input type="text" name="${formField.field}" lay-verify="${formField.fieldAttr}	" >
	</c:if>
	<c:if test="${formField.fieldType eq 2}">
		${formField.title}:<input type="checkbox" name="${formField.field}" lay-verify="${formField.fieldAttr}" >
	</c:if>
	<c:if test="${formField.fieldType eq 3}">
		${formField.title}:<select name="modules" lay-verify="required" lay-search="">
			<option value="">请选择</option>
			<c:forEach items="${formField.value}" var="dropDownValue">
				<option value="dropDownValue">${dropDownValue}</option>
			</c:forEach>
		</select>
	</c:if>
	<c:if test="${formField.fieldType eq 4}">
		${formField.title}:<select name="modules" id="${formField.field}" class="dr" data-arg0=${formField.tableName} data-arg1=${formField.field}  lay-verify="${formField.fieldAttr}" lay-search="">			
			<option value="">请选择</option>
		</select>
	</c:if>
</c:forEach>
<button class="layui-btn" lay-submit="" lay-filter="demo">跳转式提交</button>
</div>
<!-- 加载js文件 -->
 
<script type="text/javascript">
	layui.use(['jquery','form'],function(){
		var $=layui.jquery;
		var form=layui.form;
	$(function(){
		$(".dr").each(function(){			

			var Domid=this.id;
			$.post("getDropDownValue",{"arg0":$(this).data("arg0"),"arg1":$(this).data("arg1")},function(res){
				$.each(res, function (index, item) {  
	                  var id = res[index].id; 
	                  var text = res[index].name; 
	                  $("#"+Domid).append("<option value='"+id+"'>"+text+"</option>");
	              });
		      	form.render('select');
				});
		});

	});

	//监听提交
	  form.on('submit(demo)', function(data){
	    layer.alert(JSON.stringify(data.xfield), {
	      title: '最终的提交信息'
	    })
	    return false;
	  });
})
</script>
</body>
</html>