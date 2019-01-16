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
</head>
<body>
	<div class="layui-form-item">
		<form class="layui-form"  style="margin-top: 20px;">
		
		<div class="layui-form-item">
			 <div class="layui-inline">
		         <div class="layui-inline">
			        <label class="layui-form-label">选择时间</label>
			        <div class="layui-input-inline">
			        	<input type="text" class="layui-input" id="reservationDate" placeholder="预约时间" value="${reservationWatchHouseInfo.reservationDate}"  name="reservationDate">
		     		</div>
	    		</div>
	    	</div>
	    </div>
	    <input type="hidden" class="layui-input" id="reservationsId" name="id" value="${reservationWatchHouseInfo.id}"/>
     	<div class="layui-form-item">
		       <label class="layui-form-label">时间段</label>
		       <div class="layui-input-block">
		       <select  id="reservationTime" name="reservationTime">
					<option value="">请选择</option>
						<c:forEach var="reservationTimeEnum" items="${reservationTimeEnum}">
								<c:if test="${reservationWatchHouseInfo.reservationTime==reservationTimeEnum.value}">
									<option value="${reservationTimeEnum.value}" selected="selected">${reservationTimeEnum.name}</option>
								</c:if>
								<c:if test="${reservationWatchHouseInfo.reservationTime!=reservationTimeEnum.value}">
									<option value="${reservationTimeEnum.value}">${reservationTimeEnum.name}</option>
								</c:if>
						</c:forEach>
					</select>	
		      </div>
		</div>
			<div class="layui-inline">
			  		<button class="layui-btn" name="searchBtn"  lay-submit lay-filter="reservationWatchHouseUpdateTime" data-type="reload" id="searchBtn">查询</button>
		  	</div>
		</form>	
	</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
layui.use(['form','table','laydate'], function(){	
	var laydate = layui.laydate;//时间控件
	 var form = layui.form;
	//日期范围限制
		var endDate= laydate.render({
		        elem: '#reservationDate',//选择器结束时间
		        type: 'datetime',
		        min:"1970-1-1",//设置min默认最小值
		        done: function(value,date){
		        	date.month = date.month-1;
		        }
		    });
    form.on('submit(reservationWatchHouseUpdateTime)', function(data){
    	//状态为已处理
    	data.field.status=1
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