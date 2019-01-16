<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>过户进度查看</title>

</head>
<body>
	<div class="toolBar layui-form">
		<button class="layui-btn" onclick="transfer.add()">新增进度</button>
		<button class="layui-btn" onclick="transfer.cancel()">取消过户</button>
		<button class="layui-btn" onclick="transfer.updateTime()">调整时间</button>
		<table class="layui-table" lay-size="sm" id="table_transfer">
			<thead>
				<tr>
					<th>进度状态</th>
					<th>进度名称</th>
					<th>预计时间</th>
					<th>完成时间</th>
					<th>操作人</th>
					<th>操作时间</th>
					<th>备注</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transfer" items="${transferList}">
					<tr>
						<td>
							${transfer.status}
							<input type="hidden" name="status" value="${transfer.status}"/>
							<input type="hidden" name="id" value="${transfer.id}"/>
						</td>
						<td>${transfer.scheduleName}<input type="hidden" name="scheduleName" value="${transfer.scheduleName}"/></td>
						<td>${transfer.estimateFinishTime}
							<input type="hidden" name="estimateFinishTime" value="${transfer.estimateFinishTime}"/>
						</td>
						<td>${transfer.actualFinishTime}</td>
						<td>${transfer.transfer}</td>
						<td>
							<c:if test="${transfer.status !=0}">
								${transfer.updateTime}
							</c:if>
						</td>
						<td>${transfer.remark}</td>
						<td>
							<button onclick="transfer.update('${transfer.id}','${transfer.status}',
							'${transfer.remark}','${transfer.estimateFinishTime}','${transfer.actualFinishTime}')">修改</button>
							<button onclick="transfer.del('${transfer.id}')">删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="display: none;"  class="layui-form" id="transferUpdate" style="margin-top: 20px;">
		<input type="hidden" name="id"/>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>进度状态</label>
		      <div class="layui-input-inline">
		      	<select name="status" lay-ignore>
		      		<c:forEach var="status" items="${transferScheduleStatus}">
				        <option value="${status.key}">${status.value}</option>
		        	</c:forEach>
		      	</select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">备注</label>
		      <div class="layui-input-inline">
		      	<textarea rows="5" cols="20" name="remark"></textarea>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>预计时间</label>
		      <div class="layui-input-inline">
		      	<input type="text" name="estimateFinishTime"/>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">完成时间</label>
		      <div class="layui-input-inline">
		      	<input type="text" name="actualFinishTime"/>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="transferUpdateForm">立即提交</button>
		    </div>
		  </div>
	</div>
	<div style="display: none;"  class="layui-form" id="transferAdd" style="margin-top: 20px;">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>进度名称</label>
		      <div class="layui-input-inline">
		      	<select name="scheduleName" lay-ignore>
		      		<option>从动态配置里获取1</option>
		      		<option>从动态配置里获取2</option>
		      	</select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>预计时间</label>
		      <div class="layui-input-inline">
		      	<input type="text" name="estimateFinishTime"/>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="transferAddForm">立即提交</button>
		    </div>
		</div>
	</div>
	<div style="display: none;"  class="layui-form" id="transferUpdateTime" style="margin-top: 20px;">
		<table class="layui-table" id="transferUpdateTime_table">
			<thead>
				<tr>
					<th>进度名称</th>
					<th>预计时间</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<button class="layui-btn" lay-submit lay-filter="transferUpdateTimeForm">确定</button>
	</div>
<script>
var transfer = {
	add : function(){
		var title = "新增过户进度";
		var option = {
	        type: 1 //此处以iframe举例
	        ,id:"transferAddwindow"
	        ,title: title
	        ,area: ['400px', '245px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $("#transferAdd")
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	        }
	      };
		layer.open(option);
	},
	update : function(id,status,remark,estimateFinishTime,actualFinishTime){
		var title = "调整进度";
		var option = {
	        type: 1 //此处以iframe举例
	        ,id:"transferUpdatewindow"
	        ,title: title
	        ,area: ['400px', '450px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $("#transferUpdate")
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	          $("#transferUpdate").find("[name='id']").val(id);
	          $("#transferUpdate").find("[name='status']").val(status);
	          $("#transferUpdate").find("[name='remark']").text(remark);
	          $("#transferUpdate").find("[name='estimateFinishTime']").val(estimateFinishTime);
	          $("#transferUpdate").find("[name='actualFinishTime']").val(actualFinishTime);
	        }
	      };
	layer.open(option);
	},
	del : function(id){
		layer.confirm("您确定要删除？",function(){
			$.ajax({
				type:"post",
				url:"deleteTransferSchedule",
				dataType:"json",
				data:{id:id},
				success:function(data){
					if(data.success){
		    			layer.alert("删除成功",function(){
		    				location.reload();
			     			layer.closeAll();
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
	},
	cancel : function(){
		var dealId = $("#dealId").val();
		if(dealId=="" || dealId == undefined){
			layer.alert("没有找到成交编号，请刷新重试");
			return false;
		}
		$.ajax({
			type:"post",
			url:"cancelTransferSchedule",
			dataType:"json",
			data:{dealId:dealId},
			success:function(data){
				if(data.success){
	    			layer.alert("取消过户成功",function(){
	    				location.reload();
		     			layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
		});
	},
	updateTime : function(){
		var tb = $("#table_transfer").get(0);
		  var rows = tb.rows;
		  var arr = [];
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input");
			  var obj = {};
			  for(var j=0;j<inputs.length;j++){
				  obj[inputs[j].name]=inputs[j].value;
			  }
			  arr.push(obj);
		  }
		  var html ="";
		  for(var i=0;i<arr.length;i++){
			  var obj = arr[i];
			  if(obj.status !='1'){//驳回或者未完成的状态下允许修改
				  html +="<tr><td>"+obj.scheduleName;
				  html +="</td><td><input type='hidden' name='id' value='"+obj.id+"'/>";
				  html += "<input type='text' name='estimateFinishTime' value='"+obj.estimateFinishTime+"'/></td></tr>";
			  }
		  }
		  $("#transferUpdateTime_table").find("tbody").html(html);
		  var title = "调整进度";
			var option = {
		        type: 1 //此处以iframe举例
		        ,id:"transferUpdateTimewindow"
		        ,title: title
		        ,area: ['400px', '450px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $("#transferUpdateTime")
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      };
		layer.open(option);
	}
};
layui.use(['table','form'], function(){
  var table = layui.table;
  var form = layui.form;
  
  form.on('submit(transferAddForm)', function(data){
    $.ajax({
    	type:"post",
    	url:"addTransferSchedule",
    	dataType:"json",
    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(data.field)},
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
  
  form.on('submit(transferUpdateForm)', function(data){
    $.ajax({
    	type:"post",
    	url:"updateTransferSchedule",
    	dataType:"json",
    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(data.field)},
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
  form.on('submit(transferUpdateTimeForm)', function(data){
	  var tb = $("#transferUpdateTime_table").get(0);
	  var rows = tb.rows;
	  var arr = [];
	  for(var i=1;i<rows.length;i++){
		  var row = rows[i];
		  var inputs = $(row).find("input");
		  var obj = {};
		  for(var j=0;j<inputs.length;j++){
			  obj[inputs[j].name]=inputs[j].value;
		  }
		  arr.push(obj);
	  }
	  alert(JSON.stringify(arr));
    $.ajax({
    	type:"post",
    	url:"batchUpdateTransferScheduleTime",
    	dataType:"json",
    	data:{jsonData:JSON.stringify(arr)},
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
  
});
</script> 
</body>
</html>