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
<title>房源带看记录</title>
</head>
<body>

	<div class="toolBar layui-form">
	
	  	<div class="layui-inline">
				<select name="transactionTypeId" id="transactionTypeId">
					<option value="">客源类型</option>
					<option value="3">求租</option>
					<option value="2">求购</option>
					<option value="1">一手</option>
					<option value="">全部</option>
				</select>
		</div>
			<div class="layui-inline">
					<select name="leaderId" id="leaderId">
						<option value="">带看人</option>
						<option value="13320">超级管理员2</option>	
					</select>
		</div>
		  <div class="layui-inline">
			        <input type="text" class="layui-input" id="beginTime" placeholder="开始日期">
		    </div>
		    <div class="layui-inline">
			        <input type="text" class="layui-input" id="endTime" placeholder="结束日期">
		    </div>
		 <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}">
			<button class="layui-btn"  type="button"  name="clear">清空</button>
	  	</c:if>
	</div>

<table class="layui-table" id="table_DemandShowedRecord" lay-filter="table_DemandShowedRecord"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.CustShowedRecordEditor!='' && d.CustShowedRecordEditor!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="Editor" name="CustShowedRecordEditor">编辑</a>
  {{# } }}
 {{# if(d.CustShowedRecordDel!='' && d.CustShowedRecordDel!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="Del" name="CustShowedRecordDel">删除</a>
  {{# } }}
</script>
<script type="text/javascript">
layui.use(['table','form','laydate'], function(){
	var laydate = layui.laydate;//时间控件
	var form = layui.form;
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
	  var $ = layui.$ //jquery的使用
	  var table = layui.table;
	  var form = layui.form;
	  table.render({
		    elem: '#table_DemandShowedRecord'
		    ,method:"post"
		    ,height:"full-68"
		    ,url: 'queryDemandShowedRecordData'
		    ,page: true //开启分页
		    ,cols: [[ //表头
/* 		      {field: 'shhId',width: 150,title: '带盘量', sort: true,templet: '<div>{{ layui.laytpl.toShhIdStr(d.shhId) }}</div>'} */
		      ,{field: 'custName', width: 130,title: '客户新名', sort: true}
		      ,{field: 'transactionType', width: 180,title: '交易类型', sort: true} 
		      ,{field: 'demandId',width: 150, title: '客源编号', sort: true}
		      ,{field: 'leader', width: 130,title: '带看人', sort: true}
		      ,{field: 'deptName',width: 180, title: '部门', sort: true}
		      ,{field: 'content', width: 300,title: '客户评价', sort: true}
		      ,{field: 'leadTime', width: 300,title: '带看时间', sort: true,templet: '<div>{{ common.toDateString(d.leadTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{fixed: 'right', title: '操作', width: 200, align:'center', toolbar: '#operationBar'}
		    ]]
		  })
	   var $ = layui.$, active = {
				 
			    reload: function(){
			    //执行重载
			      table.reload('table_DemandShowedRecord', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	transactionTypeId: $("#transactionTypeId").val(),
			        	leaderId:$("#leaderId").val(),
			        	beginTime:$("#beginTime").val(),
			        	endTime:$("#endTime").val()
			        }
			      });
			    }
			  };
		  layui.laytpl={
					 //转换菜单等级描述
					toShhIdStr : function(shhId){
						
						if(shhId!=""){
							var arr=shhId.split(",");//逗号分组	
							var test=arr.length;//数组长度
							return test
						}else
							return 0
					}
			  }
	 	//监听工具条
	  table.on('tool(table_DemandShowedRecord)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var houseId = data.houseId;
	    var id=data.id;
	    if(layEvent === 'Editor'){ //编辑
	    	var title = '查看当前客源源跟进详细页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">客源跟进编号：【'+data.houseId+'】</span>';
	    	//页面层
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"table_DemandShowedRecordFrame"
		        ,title: title
		        ,area: ['800px', '600px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterDemandShowedRecordDetailPage?id='+id		
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	    }else if(layEvent === 'Del'){
	    	layer.confirm('确定要删除该客源等级条件吗？', function(index){
		    	  $.ajax({
		    		  type:"post",
		    		  url:"delDemandShowedRecord",
		    		  dataType:"json",
		    		  data:{id:id},
		    		  success:function(data){
		    			  if(data.success){
		    				  layer.msg('删除成功', {icon: 1,time:500});
		    				  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
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
});
</script>
</body>
</html>