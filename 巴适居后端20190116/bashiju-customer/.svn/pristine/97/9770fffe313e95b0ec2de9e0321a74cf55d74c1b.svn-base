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
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css" media="all">
<title>房源跟进</title>
</head>
<body>

<div class="layui-container">
	<form class="layui-form" action="" id="" style="margin-top: 20px;" name="res_house">	
	<input name ="id" value="${dsr.id}" style="display: none"/>
	   	<div class="layui-form-item">
		    <div class="layui-input-inline">
				<span style="color:red;">*</span>带看房源 &nbsp;<a href="#" id="selectSecondHouses">选择二手房源</a>
		    </div>
	 	</div>
	 	<table class="layui-table" id="queryHsSecondHandHouseById" lay-filter="queryHsSecondHandHouseById" name="table">
			
		</table>
		   <div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>客户评价</label>
			    <div class="layui-input-block">
					<textarea name="content" style="height: 100px;"  required lay-verify="required" class="layui-textarea"  id="content" required>${dsr.content}</textarea>
			    </div>
		   </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="hsSecondHandHouse">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary" lay-submit lay-filter="close">取消</button>
		    </div>
		  </div>
    </form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
	  <a class="layui-btn layui-btn-xs" lay-event="Del" name="secondHouseDel">删除</a>
</script>
<script type="text/javascript">
function removeEmptyArray(obj1){
	if(obj1.length==undefined){
		return obj1;
	}
}
layui.use(['table','form','element'], function(){
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var element = layui.element;
	  var form = layui.form;
	  var data = [];
		var dd = '${details}';
		if(dd!=""){
			data = JSON.parse(dd);
		}
	  $("#selectSecondHouses").click(function(){
	    	var title = '查看当前客源带看编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">客源跟进编号：【】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,title: title
		        ,area: ['700px', '500px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterHsSecondHandHouse'
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	  })
	var test = table.render({
		    elem: '#queryHsSecondHandHouseById'
		    ,method:"post"
		    ,height:"full-50"
		    ,data:data
 		    ,url: 'queryHsSecondHandHouseById?secondHouseId='+secondHouseId
		    ,page: false //开启分页
		    ,cols: [[ //表头
		      {field: 'id',width:100, title: '房源编号1111', sort: true}
		      ,{field: 'communityName',width:100	, title: '小区', sort: true}
		      ,{field: 'buildingsName', title: '座栋', width:100,sort: true} 
		      ,{field: 'buildingHouseName',width:100,title: '门牌号', sort: true}
		      ,{field: 'owner',width:100, title: '业主姓名', sort: true}
		      ,{field: 'houseType',width:100, title: '房型', sort: true}
		      ,{field: 'buildSpace', title: '面积', width:100,sort: true} 
		      ,{field: 'sellingPrice',width:100,title: '售价', sort: true}
		      ,{field: 'rentPrice',width:100, title: '租价', sort: true}
		      ,{field: 'maintainer',width:100, title: '维护人', sort: true}
		      ,{field: 'entrustCode', title: '委托人', width:100,sort: true} 
		      ,{field: 'mainterDept',width:100,title: '部门', sort: true}
		      ,{fixed: 'right', title: '操作', width: 200, align:'center', toolbar: '#operationBar'}
		    ]]
		  });
	  
	  top.document.back = function(obj){
// 		  alert("成功进入");
		  var oldData= layui.table.cache.queryHsSecondHandHouseById.filter(removeEmptyArray);
		  oldData.push(obj);

		var tt=[];
		  for(var i=0;i<table.cache.queryHsSecondHandHouseById.length;i++){
			  tt.push(table.cache.queryHsSecondHandHouseById[i].id)
			}
		  if(oldData.indexOf(obj.id)==-1){
			layer.msg("该房源已存在",{time:1000})
			return false
		  }
		test.reload({data:oldData});  
	  }
		  
	  form.on('submit(hsSecondHandHouse)', function(data){
		  //得到需求编号shhId
		  var arr=[];
		  for(var i=0;i<table.cache.queryHsSecondHandHouseById.length;i++){
			  //判断是否为undefined
			  if(typeof(table.cache.queryHsSecondHandHouseById[i].id)!='undefined'){
				  arr.push(table.cache.queryHsSecondHandHouseById[i].id)
			    }
			}
		  var id=arr.join(",")
		  var test=table.cache.queryHsSecondHandHouseById;
		  if(test==""){
			  layer.msg("带看房源不能为空")
		  	  return false
		  }	 
		  data.field.shhId=id//把数组分割为字符串
			$.ajax({
		    	type:"post",
		    	url:"saveOrUpdateDemandShowedRecord",
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
	  });
	  //操作表(删除带看房源操作)
	 	 table.on('tool(queryHsSecondHandHouseById)', function(obj){
	 		 var data = obj.data; //获得当前行数据
	 	     var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	 	     obj.del()
	 	 });
});
</script>
</body>
</html>