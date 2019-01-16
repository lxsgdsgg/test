<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>门店支出配置</title>
</head>
<body>
	<div>
		<div class="layui-row">
			<div id="financeCostTypeDiv" class="layui-col-md6">
				<fieldset class="layui-elem-field">
					  <div class="layui-field-box">
					  	<div class="layui-row">
					  		<table id="financeCostType" lay-filter="financeCostType"></table>
					  	</div>
					  </div>
				</fieldset>
			</div>
			
			<div id="financeCostProjDiv" class="layui-col-md6">
				<fieldset class="layui-elem-field">
					  <div class="layui-field-box">
						<button id="financeCostTypeAdd" class="layui-btn" style="margin-left: 93%;margin-top: 10px" >新增费用类型</button>
					  	<div class="layui-row">
					  		<table id="financeCostProj"  lay-filter="financeCostProj"></table>
					  	</div>
					  </div>
				</fieldset>
			</div>
		</div>
	</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" id="operationBar">
	{{# if(d.financeCostProjAdd!='' && d.financeCostProjAdd!=null){ }}
		<a class="layui-btn layui-btn-xs" lay-event="add" name="financeCostProjAdd">新增费用项目</a>
	{{# } }}
</script>
<script type="text/javascript" id="operationBarProj">
		{{# if(d.financeCostProjDel!='' && d.financeCostProjDel!=null){ }}
			<a class="layui-btn layui-btn-xs" lay-event="del" name="financeCostProjDel">删除</a>
		{{# } }}
</script>
<script type="text/javascript">
	 layui.use(['table','layer','form'], function(){
		  table = layui.table;
		  layer=layui.layer;
		  form=layui.form;
		  var $ = layui.$ //重点处
		  $(function(){
			  $("#financeCostTypeAdd").click(function(){
				  var title = '费用类型新增页面';
					var option = {
					        type: 2 //此处以iframe举例
					        ,id:"financeCostTypeAddFrame"
					        ,title: title
					        ,area: ['500px', '330px']
					        ,shade: 0.5
					        ,resize:false//不允许拉伸
					        ,maxmin: false
					        ,offset: 'auto' 
					        ,content: 'enterFinanceCostTypeAdd'
					        ,zIndex: layer.zIndex //重点1
					        ,success: function(layero){
					          layer.setTop(layero); //重点2
					          parent.layer.closeAll();
					        }
					      };
					common.windowLayerOpen(option); 
			  })
		  })
		  table.render({
			    elem: '#financeCostType'
			    ,method:"post"
			    ,url: 'queryFinanceCostTypeData'
				,page: true //开启分页
			    ,cols: [[ //表头
			       {field: 'id',width: 130, title: 'ID'} 
			      ,{field: 'name',width: 130, title: '费用类型'} 
			      ,{field: 'sortNo',width: 130,title: '显示顺序', sort: true}
			      ,{fixed: 'right', title: '操作', width: 140, align:'center', toolbar: '#operationBar'}
			    ]],
			    //隐藏id列
			  done: function(res, curr, count){
			  $("[data-field='id']").css('display','none');
			 	 }
			  }); 
//双击行事件		  
		 	var $financeCostType = $("#financeCostType").next('.layui-table-view').find('table.layui-table');
		 	$financeCostType.dblclick(function(event){
		 		var tdArr = $($(event.target).closest("tr").get(0)).find('td');
		 		var obj = {};
		 		for(var i=0;i<tdArr.length;i++){
		 			obj[$(tdArr[i]).attr('data-field')]= $(tdArr[i]).find('div').text();
		 		}
	 			  var id=obj.id
		 		  table.render({
					    elem: '#financeCostProj'
					    ,method:"post"
						,page: true //开启分页
					    ,url: 'queryFinanceCostProjData?financeCostTypeId='+id
					    ,cols: [[ //表头
					       {field: 'financeCostTypeName',width: 130, title: '费用类型'} 
					      ,{field: 'name',width: 130, title: '费用项目'} 
					      ,{field: 'sortNo',width: 130,title: '显示顺序', sort: true}
					      ,{fixed: 'right', title: '操作', width: 140, align:'center', toolbar: '#operationBarProj'}
					    ]]
					  }); 
		 	});
		 	  table.on('tool(financeCostType)', function(obj){
		 		    var data = obj.data; //获得当前行数据
				    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				    var tr = obj.tr; //获得当前行 tr 的DOM对象
				   	var financeCostTypeId = data.id; 
				    if(layEvent=="add"){
				       	var title = '新增费用项目';
				    	//页面层
				    	common.windowLayerOpen({
					        type: 2 //此处以iframe举例
					        ,id:"financeCostProjFrame"
					        ,title: title
					        ,area: ['400px', '330px']
					        ,shade: 0.5
					        ,resize:false//不允许拉伸
					        ,maxmin: false
					        ,offset: 'auto' 
							,content: 'enterFinanceCostProjAddPage?financeCostTypeId='+financeCostTypeId
							,zIndex: layer.zIndex //重点1
					        ,success: function(layero){
					          layer.setTop(layero); //重点2
					        }
					      });
				    }
	 	   })
	 	    	  table.on('tool(financeCostProj)', function(obj){
		 		    var data = obj.data; //获得当前行数据
				    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				    var tr = obj.tr; //获得当前行 tr 的DOM对象
					var financeCostProjId=data.id;
				    if(layEvent=="del"){
				        layer.confirm('确定要删除该房源等级条件吗？', function(index){
					    	  $.ajax({
					    		  type:"post",
					    		  url:"delFinanceCostProj",
					    		  dataType:"json",
					    		  data:{financeCostProjId:financeCostProjId},
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
	 	   })
     });	
	</script>
</body>
</html>