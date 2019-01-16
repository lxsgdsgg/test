<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历管理页面</title>
</head>
<body>
<div>
	<table class="layui-table" id="table_curriculumVitae" lay-filter="table_curriculumVitae"></table>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="detail">查看详情</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="download">下载附件</a>
</script>
<script type="text/javascript">
layui.use(['form','table'],function(){
	  var form = layui.form,
	  table = layui.table;
	  table.render({
		    elem: '#table_curriculumVitae'
		    ,height:'full-100'
		    ,url: 'queryCurriculumVitae'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
		      ,{field: 'name', title: '名称',width: "10%", sort: true}
		      ,{field: 'sex', title: '性别',width: "10%"}
		      ,{field: 'birthdate', title: '出生日期',width: "10%"}
		      ,{field: 'workDate', title: 'workDate',width: "10%"}
		      ,{field: 'addTime', title: '投递时间',width: "10%"}
		      ,{field: 'mobile', title: '手机号码',width: "10%"}
		      ,{ title: '操作',width: "10%", align:'center', toolbar: '#operationBar'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
		//监听工具条
	  table.on('tool(table_curriculumVitae)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var id = data.id;
	    var path = data.attachment;
	    if(layEvent === 'detail'){ //删除
	    	alert(id)
	    	var title = '简历详情页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房产文章分类编辑页面：【】</span>';
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"curriculumVitaeFrame"
		        ,title: title
		        ,area: ['900px', '650px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterCurriculumVitaeDetailPage?id='+id
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	    }else if(layEvent === 'download'){
	    	alert(path)
	    	window.location=path
	    }
	  });
});
</script>
</body>
</html>