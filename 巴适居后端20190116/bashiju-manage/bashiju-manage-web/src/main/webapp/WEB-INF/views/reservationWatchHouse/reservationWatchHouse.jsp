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
<title>经纪人预约看房</title>
</head>
<body>

<table class="layui-table" id="table_ReservationWatchHouseByAgentId" lay-filter="table_ReservationWatchHouseByAgentId"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
			<a class="layui-btn layui-btn-xs layui-btn-normal" name="updateReservationDate" lay-event="updateReservationDate">修改预约时间</a>
		{{# if(d.status != null && d.status == '0'){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" name="IsReservation" lay-event="IsReservation">接受预约</a>
		{{# } else if(d.status != null && d.status == '1'){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" name="IsReservation" lay-event="IsReservation">已预约</a>
		{{# } }}
			<a class="layui-btn layui-btn-xs layui-btn-normal" name="cancelReservation" lay-event="cancelReservation">取消预约</a>
</script>
<script type="text/javascript">
layui.use(['table','laydate'], function(){
	var laydate = layui.laydate;//时间控件
	var endDate= laydate.render({
        elem: '#endAddTime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        	date.month = date.month-1;
            startDate.config.max=date;
        }
    });
    //日期范围
    var startDate=laydate.render({
        elem: '#beginAddTime',
        type: 'datetime',
        max:"2099-12-31",//设置一个默认最大值
        done: function(value, date){
        	date.month = date.month-1;
            endDate.config.min =date;
        }
    });
	  layui.laytpl={
				 //转换菜单等级描述
				toReservationTimeStr : function(reservationTime){
				  var dd = ${reservationTimeEnum};
					if('${reservationTimeEnum}'!='')
						return dd[reservationTime]
					else
						return reservationTime;
				},
			  //转换菜单等级描述
				toStatusStr : function(status){
					var dd=${handleStatusEnum};
					if('${handleStatusEnum}'!='')
						return dd[status]
					else
						return status;
				}
		  }
	var table=layui.table;
	//加载数据
	 table.render({
		    elem: '#table_ReservationWatchHouseByAgentId'
		    ,height:'full-100'
		    ,url: 'queryReservationWatchHouseByAgentId'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
		      ,{field: 'custId', title: '客户编号', sort: true}
		      ,{field: 'name', title: '登记名称', sort: true}
		      ,{field: 'remark', title: '需区等级'}
		      ,{field: 'mobile', title: '手机号码'}
		      ,{field: 'shhId', title: '预约房源编号'}
// 		      ,{field: 'companyId', title: '房源维护人所在公司'}
		      ,{field: 'maintainId', title: '房源维护人id'}
		      ,{field: 'reservationTime', title: '预约时间段',templet: '<div>{{ layui.laytpl.toReservationTimeStr(d.reservationTime) }}</div>'}
		      ,{field: 'reservationDate', title: '预约日期',templet: '<div>{{ common.toDateString(d.reservationDate,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'status', title: '状态',templet: '<div>{{ layui.laytpl.toStatusStr(d.status) }}</div>'}
		      ,{field: 'handelTime', title: '处理时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'handelerId', title: '处理人编号'}
		      ,{field: 'operatorId', title: '操作人Id'}
		      ,{field: 'operator', title: '操作人'}
		      ,{field: 'handelResult', title: '处理结果'}
		      ,{field: 'addTime', title: '添加时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'updateTime', title: '更新时间',templet: '<div>{{ common.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'operator', title: '操作人'}
		      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
	  table.on('tool(table_ReservationWatchHouseByAgentId)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	var id = data.id;
		    if(layEvent === 'del'){ //删除
		      layer.confirm('确定要删除该客源等级条件吗？', function(index){
		    	  $.ajax({
		    		  type:"post",
		    		  url:"delResCustLevelCondition",
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
		    } else if(layEvent === 'cancelReservation'){ //编辑
				var title = '取消预约时间页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">取消预约时间页面：【】</span>';
			   	common.windowLayerOpen({
			        type: 2 //此处以iframe举例
			        ,id:"table_ReservationWatchHouseByAgentIdFrame"
			        ,title: title
			        ,area: ['350px', '200px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterReservationWatchHouseCancelPage?id='+id
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
			      }); 
		    }else if(layEvent==='IsReservation'){
		    	if(data.status == '0'){
					layer.confirm('确定要接受预约吗？', function(index) {
						$.ajax({
				        	async:false,
				        	type:"post",
				        	url:"updateReservationsInfo",
				         	dataType:"json",
				        	data:{jsonData:JSON.stringify({"id":id,"status":1})},
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
				}
		    }else if(layEvent === 'updateReservationDate'){
		    	  var title = '修改预约时间页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房源等级条件名称：【】</span>';
				   	common.windowLayerOpen({
				        type: 2 //此处以iframe举例
				        ,id:"table_ReservationWatchHouseByAgentIdFrame"
				        ,title: title
				        ,area: ['500px', '400px']
				        ,shade: 0.5
				        ,resize:false//不允许拉伸
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'enterReservationWatchHouseUpdateTimePage?id='+id
				        ,zIndex: layer.zIndex //重点1
				        ,success: function(layero){
				          layer.setTop(layero); //重点2
				        }
				      }); 
		    }
		  });
	
});

</script>
</body>
</html>