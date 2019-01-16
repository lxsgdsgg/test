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
<title>客户预约看房取消页面</title>
</head>
<body>
	<div class="layui-form-item">
		<form class="layui-form"  style="margin-top: 20px;">
		
			<div class="layui-form-item">
				 <div class="layui-inline">
			         <div class="layui-inline">
				        <label class="layui-form-label">选择时间</label>
				        <div class="layui-input-inline">
							<input class="layui-input" name="handelResult" placeholder="请输入。。。"  autocomplete="off">		     		</div>
		    		</div>
		    	</div>
		    </div>
		    	 <input class="layui-input" name="id" placeholder="请输入取消原因" type="hidden" value="${id}" autocomplete="off">
	    	 <div class="layui-inline">
			  			<button class="layui-btn" name="searchBtn"  lay-submit lay-filter="reservationWatchHouseCancel" data-type="reload" id="searchBtn">查询</button>
    		</div>
		</form>	
		  
	</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
layui.use(['form','table'], function(){	
	 var form = layui.form;
    form.on('submit(reservationWatchHouseCancel)', function(data){
    	data.field.status=2;
        $.ajax({
        	async:false,
        	type:"post",
        	url:"updateReservationsInfo",
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
      });
})
</script>
</body>
</html>