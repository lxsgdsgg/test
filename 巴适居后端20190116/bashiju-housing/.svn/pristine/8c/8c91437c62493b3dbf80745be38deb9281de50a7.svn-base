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
<style type="text/css">
.layui-form-select .layui-input {
    padding-right: 30px;
    cursor: pointer;
    width: 120px;
}

layui-input, .layui-textarea {
    display: block;
    width: 50%;
    padding-left: 10px;
}
</style>
<title>钥匙管理</title>
</head>
<body>
<div class="toolBar layui-form">
	
	  	<div class="layui-inline">
					<select name="houseStatus" id="houseStatus" layui>
						<option value="">房源状态</option>
						<c:forEach var="houseStatus" items="${houseStatus}">
								<option value="${houseStatus.name}">${houseStatus.name}</option>
						</c:forEach>
					</select>
		</div>
			<div class="layui-inline">
					<select name="keyStatus" id="keyStatus" layui>
						<option value="">钥匙状态</option>
						<c:forEach var="KeyStatus" items="${KeyStatus}">
							<option value="${KeyStatus.key}">${KeyStatus.value}</option>
						</c:forEach>
					
					</select>
		</div>
			<div class="layui-inline">
					<select name="reciver" id="reciver" layui>
						<option value="">得钥匙人</option>
						<option value="13148">呈贡一区一店一组组员1</option>
					</select>
		</div>
		
		  <div class="layui-inline">
			        <input type="text" class="layui-input" id="beginTime" placeholder="开始日期" layui>
		    </div>
		    <div class="layui-inline">
			        <input type="text" class="layui-input" id="endTime" placeholder="结束日期" layui>
		    </div>
		      <div class="layui-inline">
			        <input type="text" class="layui-input"  placeholder="行政区,片区,小区(可多选)" layui>
		    </div>
		    
		    <div class="layui-inline">
					<select name="buildingsName" id="buildingsName" layui>
						<option value="">座栋</option>
						<option value="19">1号楼</option>
						<option value="2">16栋</option>	
					</select>
			</div>
			  <div class="layui-inline">
			        <input type="text" class="layui-input"  placeholder="借出钥匙人,单元,房号">
		    </div>

		 <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}">
			<button class="layui-btn"  type="button"  id="clear" name="clear">清空</button>
	  	</c:if>
	</div>
<table class="layui-table" id="table_HsKey" lay-filter="table_HsKey"></table>

<!-- 借出 -->
<form class="toolBar" id="borrow" style="display:none;margin-top: 20px;" >
	 		<div class="layui-form-item">
	 			<label class="layui-form-label">借出人</label>
			    <div class="layui-input-inline">
			    	<input class="layui-input" name="borrower"  id="borrower" autocomplete="off">
			    </div>
			     <div class="layui-input-inline" style="margin-left: 100px">
			    	  <p style="color:red">
						<br>注意:如果不选可以直接填写公司名称
			  		  </p>
			    </div>
			  </div>
</form>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">

  {{# if(d.hsKeyLog!='' && d.hsKeyLog!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="log" name="hsKeyLog">日志</a>
  {{# } }}
  {{# if(d.hsKeyBorrow!='' && d.hsKeyBorrow!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="borrow" name="hsKeyBorrow">借出</a>
  {{# } }}
  {{# if(d.hsKeyBack!='' && d.hsKeyBack!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="back" name="hsKeyBack">退还</a>
  {{# } }}
  {{# if(d.hsKeySeal!='' && d.hsKeySeal!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="seal" name="hsKeySeal">封存</a>
  {{# } }}
</script>
<script type="text/javascript">
layui.use(['table','form','laydate'], function(){
	  var form = layui.form;
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var laydate = layui.laydate;//时间控件
	 layui.laytpl={
	
			 //转换描述
			toKeyStatussStr : function(keyStatus){
				var dd=${KeyStatuss};
				if('${KeyStatuss}'!='')
					return dd[keyStatus]
				else
					return keyStatus;
			}
	  }
	
	  var endDate= laydate.render({
	        elem: '#endTime',//选择器结束时间
	        type: 'datetime',
	        min:"1970-1-1",//设置min默认最小值
	        done: function(value,date){
	        	date.month = date.month-1;
	            startDate.config.max=date;
	        }
	    });
	    //日期范围
	    var startDate=laydate.render({
	        elem: '#beginTime',
	        type: 'datetime',
	        max:"2099-12-31",//设置一个默认最大值
	        done: function(value, date){
	        	date.month = date.month-1;
	            endDate.config.min =date;
	        }
	    });
	 
	    
	    
	  table.render({
		    elem: '#table_HsKey'
		    ,method:"post"
		    ,height:"full-68"
		    ,url: 'queryHsKeyData'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'keyStatus',title: '钥匙状态', sort: true,templet: '<div>{{ layui.laytpl.toKeyStatussStr(d.keyStatus) }}</div>'} 
		      ,{field: 'houseStatus',title: '房源状态', sort: true}
		      ,{field: 'areaName', title: '所在区域', sort: true}
		      ,{field: 'regionName',title: '片区', sort: true} 
		      ,{field: 'communityName', title: '小区', sort: true}
		      ,{field: 'buildingsName', title: '座栋名称', sort: true}
		      ,{field: 'buildingsUnitName', title: '单元名称', sort: true}
		      ,{field: 'buildingHouseName',title: '房号名称', sort: true}
		      ,{field: 'houseId',title: '房源编号', sort: true}
		      ,{field: 'reciveTime',title: '收匙时间', sort: true,templet: '<div>{{ common.toDateString(d.reciveTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'deptId',width: 130, title: '钥匙所在店', sort: true}
		      ,{field: 'reciver',title: '得钥匙人', sort: true}
		      ,{field: 'borrower', title:'借出钥匙人',sort: true}
		      ,{field: 'keyCode',title: '钥匙编号', sort: true}
		      ,{field: 'receipt',title: '收据', sort: true}
		      ,{fixed: 'right', title: '操作', width: 190, align:'center', toolbar: '#operationBar'}
		    ]]
		  });
	  //查询
	   var $ = layui.$, active = {
			    reload: function(){
			    //执行重载
			      table.reload('table_HsKey', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	houseStatus:$("#houseStatus").val(),
			        	reciver:$("#reciverId").val(),
			        	keyStatus:$("#keyStatus").val(),
			        	beginTime:$("#beginTime").val(),
			        	endTime:$("#endTime").val(),
			        	buildingsName:$("#buildingsName").val(),
			        	areaOrRegionOrCommunity:$("#areaOrRegionOrCommunity").val()
			        	
			        }
			      });
			    }
			  };
	  
	 	//监听工具条
		  table.on('tool(table_HsKey)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	var id = data.id;
			var keyStatus=data.keyStatus;
			var borrower=data.borrower;
		    if(layEvent === 'log'){ //删除
		      var title = '钥匙日志页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">钥匙日志：【'+data.id+'】</span>';
		    	common.windowLayerOpen({
			        type: 2 //此处以iframe举例
			        ,id:"table_HsKeyFramk"
			        ,title: title
			        ,area: ['1200px', '600px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterHsKeyLog?hsKeyId='+id
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
			      }); 
		    }else if(layEvent === 'borrow'){ 
		        var title = '借出<span style="margin-left: 24%;color: orange;font-size: 18px;">客源等级条件名称：【'+data.id+'】</span>';
		   		layer.open({
			    	 title:title,
					  type: 1,
					  skin: 'layui-layer-demo', //样式类名
					  area: ['500px', '250px'],
					  content: $('#borrow'),
					  btn:['确定','取消'],
					  yes: function(index){
						    var borrower=$("#borrower").val();
		                $.ajax({
		  	    		  type:"post",
		  	    		  url:"HsKeyBORROWED",
		  	    		  dataType:"json",
		  	    		  data:{borrower:borrower,id:id,keyStatus:keyStatus},
			    		  success:function(data){
		  	    			  if(data.success){
		  	    				  layer.alert(data.msg, {icon: 1,time:1000});
		  	    				  //刷新页面
		  	    				  location.reload()
		  	    			  }else{
		  	    				  layer.alert(data.msg);
		  	    			  }
		  	    		  },
		  	    		  error:function(a,b,c){
		  	    			  layer.alert("操作异常，请稍后重试");
		  	    		  }
		  	    	  });
		              }
					}); 
		    }else if(layEvent === 'back'){
		    	 layer.confirm('确定要退还钥匙吗？', function(index){
			    	  $.ajax({
			    		  type:"post",
			    		  url:"HsKeyRECIVED",
			    		  dataType:"json",
			    		  data:{id:id,keyStatus:keyStatus,borrower:borrower},
			    		  success:function(data){
			    			  if(data.success){
			    				  layer.msg('退还成功', {icon: 1,time:500});
			    				  location.reload();
			    			  }else{
			    				  layer.alert(data.msg);
			    			  }
			    		  },
			    		  error:function(a,b,c){
			    			  layer.alert("操作异常，请稍后重试");
			    		  }
			    	  });
			        layer.close(index);
			        //向服务端发送删除指令
			      });
		    }else if("seal"){
		  
		        layer.confirm('确定要封存钥匙吗？', function(index){
			    	  $.ajax({
			    		  type:"post",
			    		  url:"HsKeySeal",
			    		  dataType:"json",
			    		  data:{id:id,keyStatus:keyStatus},
			    		  success:function(data){
			    			  if(data.success){
			    				  layer.msg('封存成功', {icon: 1,time:500});
			    				  location.reload();
			    			  }else{
			    				  layer.alert(data.msg);
			    			  }
			    		  },
			    		  error:function(a,b,c){
			    			  layer.alert("操作异常，请稍后重试");
			    		  }
			    	  });
			        layer.close(index);
			        //向服务端发送删除指令
			      });
		    
		    }
		  });
	  
		  $('#searchBtn').on('click', function(){
			    var type = $(this).data('type');
			    active[type] ? active[type].call(this) : '';
			  });
})
</script>

</body>
</html>