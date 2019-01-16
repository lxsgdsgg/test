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
<title>房源登记条件</title>
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
<style type="text/css">
	.tipDiv{border:1px dashed #000; height:150px;width:250px;margin-left: 100px; color: red}
</style>
</head>
<body>
	<div class="toolBar layui-form">
		  	房屋交易类型:
	  	<div class="layui-inline">
				<select name="transactionType" id="transactionType">
				<option value="">请选择</option>
						<c:forEach var="transactionTypes" items="${transactionTypes}">
							<option value="${transactionTypes.value}">${transactionTypes.name}</option>
						</c:forEach>
				</select>
		</div>
	  	<div class="layui-inline">
				<label class="layui-form-label">房屋等级</label>
				<div class="layui-input-block">
					<select name="levelType" id="levelType">
						<option value="">请选择</option>
						<option value="A">A等级</option>
						<option value="B">B等级</option>
				    </select>
				</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">城市：</label>
			<div class="layui-input-block">
				<input type="text" id="cityName" cityCode   placeholder="请选择城市" autocomplete="off" class="layui-input" >
			</div>
		</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	
 <div class="layui-inline">
      <label class="layui-form-label">新增</label>
      <div class="layui-input-inline">
        <select  lay-filter="test" >
          <option value="">请选择</option>
          <option value="2">出租新增</option>
          <option value="1">出售新增</option>
        </select>
      </div>
    </div>
    </div>
<table class="layui-table" id="table_res_house_level_condition" lay-filter="table_res_house_level_condition"></table>
		<p style="color:red">
			<br>注意：A、B级-有效且符合设置条件,C级-有效且非A、B级,D级-非有效、待确认及暂缓。
		</p>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/html" id="operationBar">
  {{# if(d.res_house_level_conditionEdit !='' && d.res_house_level_conditionEdit!=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="res_house_level_conditionEdit">编辑</a>
  {{# } }}
  {{# if(d.res_house_level_conditionDel !='' && d.res_house_level_conditionDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="res_house_level_conditionDel">删除</a>
  {{# } }}	
</script>
<script type="text/javascript">
var areaList=JSON.parse('${areaListJson}');
var path = "<%=request.getContextPath()%>";
var a= new AreaObject(areaList,selectArea,2,$("#cityName"));
function selectArea(code,name){
	$("#cityName").val(name);
	$("#cityName").attr("cityCode",code);
}
res_house_level_condition = {
		add:function(){
			var title = '房源等级条件新增页面';
			var option = {
			        type: 2 //此处以iframe举例
			        ,id:"ResHouseLevelConditionEditIFrame"
			        ,title: title
			        ,area: ['700px', '650px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterResHouseLevelConditionDetailPage'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			          parent.layer.closeAll();
			        }
			      };
			common.windowLayerOpen(option);
		}
	}
layui.use(['table','form'], function(){
	 var $ = layui.$ //重点处
	  var table = layui.table;
	  var form = layui.form;
	  layui.laytpl={
				 //转换描述
				toTransactionTypeStr : function(transactionType){
				  var dd = ${transactionType};
					if('${transactionType}'!='')
						return dd[transactionType]
					else
						return transactionType;
				},
			  //转换菜单等级描述
				toLevelStr : function(levelType){
				  var dd = ${levelType};
					if('${levelType}'!='')
						return dd[levelType]
					else
						return levelType;
				}
		  }
	  
	  //第一个实例
	  table.render({
	    elem: '#table_res_house_level_condition'
	    ,method:"post"
	    ,url: 'getResHouseLevelConditionData'
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'transactionType', width: 100,title: '房源交易类型', sort: true,templet: '<div>{{ layui.laytpl.toTransactionTypeStr(d.transactionType) }}</div>'} 
	      ,{field: 'levelType', width: 100,title: '等级', sort: true,templet: '<div>{{ layui.laytpl.toLevelStr(d.levelType) }}</div>'}
	      ,{field: 'addTime', width: 200,title: '录入时间', sort: true,templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
	      ,{field: 'name',width: 150, title: '方案名称', sort: true} 
	      ,{field: 'remark', title: '条件', sort: true}
	      ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#operationBar'}
	    ]]
	  });
	   var $ = layui.$, active = {
			    reload: function(){
			   //执行重载
			      table.reload('table_res_house_level_condition', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	transactionType: $("#transactionType").val(),
			        	levelType:$("#levelType").val(),
			        	cityCode:$("#cityName").attr("cityCode")
			        }
			      });
			    }
	   
	  		
			  };
	 	//监听工具条
		  table.on('tool(table_res_house_level_condition)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   	var id = data.id;
		    var transactionType=data.transactionType;
		    if(layEvent === 'del'){ //删除
		      layer.confirm('确定要删除该房源等级条件吗？', function(index){
		    	  $.ajax({
		    		  type:"post",
		    		  url:"delResHouseLevelCondition",
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
		    } else if(layEvent === 'edit'){ //编辑
		    	
		    if(transactionType==1){
		    	var title = '房源出售等级条件编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房源等级条件名称：【'+data.name+'】</span>';
		    	//页面层
		    	common.windowLayerOpen({
			        type: 2 //此处以iframe举例
			        ,id:"res_house_level_conditionEditFrame"
			        ,title: title
			        ,area: ['700px', '530px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
					,content: 'enterResHouseSellLevelConditionPage?id='+id
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			        }
			      }); 
		    	}else{
		    	  	var title = '房源出租等级条件编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">房源等级条件名称：【'+data.name+'】</span>';
			    	//页面层
			    	common.windowLayerOpen({
				        type: 2 //此处以iframe举例
				        ,id:"res_house_level_conditionEditFrame"
				        ,title: title
				        ,area: ['700px', '530px']
				        ,shade: 0.5
				        ,resize:false//不允许拉伸
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'enterResHouseLeaseLevelConditionPage?id='+id
				        ,zIndex: layer.zIndex //重点1
				        ,success: function(layero){
				          layer.setTop(layero); //重点2
				        }
				      });
		    	}
		    }
		  });
	   $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });

	   form.on('submit(deptTypeForm)', function(data){
			  
		    $.ajax({
		    	async:false,
		    	type:"post",
		    	url:"saveOrUpdateDeptType",
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
});
</script>
</body>
</html>