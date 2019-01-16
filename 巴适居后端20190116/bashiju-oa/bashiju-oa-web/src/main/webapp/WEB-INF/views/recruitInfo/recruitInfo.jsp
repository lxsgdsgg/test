<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息管理页面</title>
</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-inline">
			<label class="layui-form-label">招聘名称</label>
			<div class="layui-input-block">
				<input type="text" id="name" cityCode   placeholder="考核项目名" autocomplete="off" class="layui-input" >
			</div>
		</div>
	<%-- 	  	<c:if test="${onlyBtnMenus.blackListAdd!='' && onlyBtnMenus.blackListAdd!=null}"> --%>
			<button class="layui-btn"  type="button" onclick="recruitInfoAdd.add(this)" name="recruitInfoAdd" >新增</button>
<%-- 	  	</c:if> --%>

<%--   	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}"> --%>
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
<%-- 	  	</c:if> --%>
	</div>


<div>
	<table class="layui-table" id="table_recruitInfo" lay-filter="table_recruitInfo"></table>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
	   <a class="layui-btn layui-btn-xs" lay-event="edit" name="recruitInfoEdit">编辑</a>
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="recruitInfoDel">删除</a>
</script>
<script type="text/javascript">
var recruitInfoAdd = {
	add:function(){
	 	var title = '招聘信息新增页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">招聘信息新增页面：【】</span>';
    	common.windowLayerOpen({
	        type: 2 //此处以iframe举例
	        ,id:"curriculumVitaeFrame"
	        ,title: title
	        ,area: ['900px', '650px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: 'enterRecruitInfoDetailPage?'
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          layer.setTop(layero); //重点2
	        }
	      });
	}
}
layui.use(['form','table'],function(){
	  var form = layui.form,
	  table = layui.table;
	  table.render({
		    elem: '#table_recruitInfo'
		    ,height:'full-100'
		    ,url: 'queryRecruitInfo'
		   	,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
		      ,{field: 'name', title: '名称', sort: true}
		      ,{field: 'workCityName', title: '工作所在城市'}
		      ,{field: 'count', title: '招聘人数'}
		      ,{field: 'education', title: '学历要求',templet: '<div>{{ layui.laytpl.toEducationStr(d.education) }}</div>'}
		      ,{field: 'majorSubject', title: '所需专业'}
		      ,{field: 'minWorking', title: '最低工作年限'}
		      ,{field: 'maxWorking', title: '最高工作年限'}
		      ,{field: 'wages', title: '薪资'}
		      ,{field: 'mobile', title: '招聘电话'}
		      ,{field: 'endTime', title: '招聘截止时间', sort: true,templet: '<div>{{ common.toDateString(d.endTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'requirement', title: '任职要求'}
		      ,{field: 'duty', title: '主要职责'}
		      ,{field: 'publisher', title: '职位发布人'}
		      ,{field: 'publishTime', title: '职位发布时间', sort: true,templet: '<div>{{ common.toDateString(d.publishTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
		      ,{field: 'operator', title: '操作人'}
		      ,{ title: '操作', align:'center', toolbar: '#operationBar'}
		    ]]
		  	,done: function(res, curr, count){
		  		$("[data-field='id']").css('display','none');
		  	}
		  });
	  
	  layui.laytpl={
				 //转换学历描述
				toEducationStr : function(education){
				  var dd = ${educationEnum};
					if('${educationEnum}'!='')
						return dd[education]
					else
						return education;
				}
		  }  
		//监听工具条
	  table.on('tool(table_recruitInfo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data; //获得当前行数据
	    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	    var tr = obj.tr; //获得当前行 tr 的DOM对象
	   	var id = data.id;
	    if(layEvent === 'edit'){ //删除
	    	alert(id)
	    	var title = '简历详情页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房产文章分类编辑页面：【】</span>';
	    	common.windowLayerOpen({
		        type: 2 //此处以iframe举例
		        ,id:"recruitInfoFrame"
		        ,title: title
		        ,area: ['900px', '650px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'enterRecruitInfoDetailPage?id='+id
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		      }); 
	    }else if(layEvent === 'del'){
	        layer.confirm('确定要删除该招聘吗？', function(index){
		    	  $.ajax({
		    		  type:"post",
		    		  url:"delRecruitInfoById",
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
	  var $ = layui.$, active = {
			    reload: function(){
			    
			     //执行重载
			      table.reload('table_recruitInfo', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	name:$("#name").val()
			        }
			      });
			    }
			  };
	  $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
});
</script>
</body>
</html>