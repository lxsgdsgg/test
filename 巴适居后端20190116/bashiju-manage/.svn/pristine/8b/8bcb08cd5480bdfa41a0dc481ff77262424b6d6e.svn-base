<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="companyForm" style="margin-top: 20px;">
	  <div class="layui-form-item">
	  	   <label class="layui-form-label"><span style="color:red;">*</span>中介名称</label>
		    <div class="layui-input-inline">
				<input type="text" name="name" required lay-verify="required" placeholder="备注信息"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	    <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>中介电话:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="mobile" required lay-verify="required|number|phone" placeholder="操作人"  autocomplete="off" class="layui-input"  onchange="query.queryMobile(this.value)">
   		    	 <div style="color:red;" id="ValidateMsg"></div>
		    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
		    <label class="layui-form-label">备注信息:</label>
		    <div class="layui-input-inline">
		         <input type="text" name="remark" placeholder="备注信息"  autocomplete="off" class="layui-input">
		    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="phoneBlackListAdd">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
var query={
		queryMobile:function(obj){//查询公司编号是否已存在
			$.ajax({
				url:"queryMobileIsExist",
				dataType:"json",
				data:{"mobile":obj},	
				success:function(data){
					if(data.success){
						$("#ValidateMsg").html("");
					}else{
						$("#ValidateMsg").text(data.msg)
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
	  form.on('submit(phoneBlackListAdd)', function(data){
	    $.ajax({
	    	type:"post",
	    	url:"savePhoneBlackList",
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
	  form.verify({
		  //我们既支持上述函数式的方式，也支持下述数组的形式
		  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		  //联系人电话
		 mobile: [
			 /^1(3|4|5|7|8)\d{9}$/
		    ,'法人必须为11位，且不能出现空格'
		  ]
		}); 
	  
	});

</script>
</body>
</html>