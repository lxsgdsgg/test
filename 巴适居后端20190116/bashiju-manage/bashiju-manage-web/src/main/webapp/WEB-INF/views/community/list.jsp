<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小区管理</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all">
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
<c:set var="menu_id" value="${menu_id}"/>
<style type="text/css">

</style>
</head>
<body>

	<div >  
		<div class="layui-row" style="margin-top:20px;">
			<form class="layui-form" >
			<input id="queryAreaCode" type="hidden">
			 <div class="layui-form-item">
			    <div class="layui-inline" style="width:120px;">
			        <%-- <select name="areaCode" id="areaCode"  lay-filter="area">
			          <option value="">行政区</option>
			          <c:forEach var="item" items="${areaList}" >
			          	<option value="${item.code}">${item.name}</option>
			          </c:forEach> 
			        </select>--%>
			        <input type="text" name="areaCode" id="areaCode"   placeholder="行政区" class="layui-input">
			    </div>
			    
			    <div class="layui-inline">
				      <input type="text" name="regionName" id="regionName"   placeholder="片区(录入可搜索)" class="layui-input">
			    </div>
			    
			    <div class="layui-inline" style="width:120px;">
			        <select name="hot" id="hot" >
			          <option value="">热门小区</option>
			          <option value="1">是</option>
			          <option value="0">不是</option>
			        </select>
			    </div>
			    
			    <div class="layui-inline">
				      <input type="text" name="pinyin" id="pinyin"  placeholder="小区名搜索" class="layui-input">
			    </div>
			    
			    <div class="layui-inline" style="width:100px;">
			        <select id="biaozhu" name="biaozhu">
			          <option value="">是否标记</option>
			          <option value="1">已标记</option>
			          <option value="2">未标记</option>
			        </select>
			    </div>
			    
			    <div class="layui-inline">
				      <span class="layui-btn" onClick="searchCommunity()">查询</span>
			    </div>
			    
			    <div class="layui-inline">
				      <span onClick="clearCondition()" class="layui-btn">清空</span>
			    </div>
			    
			    <div class="layui-inline" style="float: right;">
				      <span onClick="showAdd()" class="layui-btn">新增</span>
			    </div>
			    
			  </div>
			</form>
		    
		</div>
		<div class="layui-row">
			<div  class="layui-col-md12">
				<table class="layui-table" id="communityTable" lay-filter="communityTable">
				</table> 
			</div>
		</div>
		
	</div>
	 	
<form class="layui-form layui-hide" id="addCommunity" style="margin:30px;">
	<input name="id" type="hidden">
	<input name="areaCode" type="hidden">
  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>小区名称</label>
    <div class="layui-input-block">
      <input type="text" name="name" lay-verify="required|title" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>备案名称</label>
    <div class="layui-input-block">
      <input type="text" name="recordName" lay-verify="required|title" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>拼音检索</label>
    <div class="layui-input-block">
      <input type="text" name="pinyin" lay-verify="required|title" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>所属片区</label>
    <div class="layui-input-inline">
     <input type="text" id="areaName" lay-verify="required" onClick="showAreaDiv()" class="layui-input" placeholder="请选择">
      <%-- <select name="areaCode" lay-filter="area" lay-verify="required">
        <option value=""></option>
		<c:forEach var="item" items="${areaList}" >
		<option value="${item.code}">${item.name}</option>
	    </c:forEach>
      </select> --%>
    </div>
    <div class="layui-input-inline">
      <select name="regionId" id="region" lay-verify="required">
        <option value=""></option>
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">小区地址</label>
    <div class="layui-input-block">
      <input type="text" name="address" lay-verify="address" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">出售均价</label>
    <div class="layui-input-block">
      <input type="text" name="salePrice" lay-verify="number" placeholder="元/m3" class="layui-input">
    </div>
  </div>

 <div class="layui-form-item">
    <label class="layui-form-label">热门小区</label>
    <div class="layui-input-block">
      <input type="checkbox" name="isHot" value="1" lay-skin="switch" lay-text="ON|OFF">
    </div>
  </div>
  <div class="layui-form-item">
	<div class="layui-input-inline">
    <button class="layui-btn" lay-submit lay-filter="communityAdd">保存</button>
	</div>
  </div>
</form>



	

 
 <%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script>

var layerId;
var areaList=JSON.parse('${areaListJson}');


var a= new AreaObject(areaList,selectArea,3,$("#areaName"));	
function showAreaDiv(){	
	/* a.show();
	if($("#areaName").val()!=""){
		a.setValue($("#addCommunity input[name=areaCode]").val());
	} */
}
function selectArea(code,name){
	$("#areaName").val(name);
	$("#addCommunity input[name=areaCode]").val(code);
	 $("#region").children().remove();
	  $("#region").append('<option value=""></option>');
	  if(code!=""){
		  $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {code:code},
	            url: "<%=request.getContextPath()%>/manage/community/getRegionList" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		  for(var i=0;i<result.data.length;i++){
	            			  var regionId=result.data[i].id;
	            			  var regionName=result.data[i].name;
	            			  $("#region").append('<option value="'+regionId+'">'+regionName+'</option>');
	            		  }	            		  
	            		  form.render();
	            	}else{
	            		layer.alert(result.msg);
	            	}
	            },
	            error : function() {	            	
	                layer.alert("异常！");
	            }
	        });
	  }
}
function setCommunity(code,name,rId){
	$("#areaName").val(name);
	$("#addCommunity input[name=areaCode]").val(code);
	 $("#region").children().remove();
	  $("#region").append('<option value=""></option>');
	  if(code!=""){
		  $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {code:code},
	            url: "<%=request.getContextPath()%>/manage/community/getRegionList" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		  for(var i=0;i<result.data.length;i++){
	            			  var regionId=result.data[i].id;
	            			  var regionName=result.data[i].name;
	            			  $("#region").append('<option value="'+regionId+'">'+regionName+'</option>');
	            		  }
	            		  $("#region").val(rId);
	            		  form.render();
	            	}else{
	            		layer.alert(result.msg);
	            	}
	            },
	            error : function() {	            	
	                layer.alert("异常！");
	            }
	        });
	  }
}
function searchCommunity(){
	var param={};
	param.areaCode=$("#queryAreaCode").val();
	param.regionName=$("#regionName").val();
	param.pinyin=$("#pinyin").val();
	param.hot=$("#hot").val();
	param.biaozhu=$("#biaozhu").val();
	//alert(JSON.stringify(param));
	table.reload("communityTable",{where:param});
}
function clearCondition(){
	$("#areaCode").val("");
	$("#queryAreaCode").val("");
	$("#regionName").val("");
	$("#hot").val("");
	$("#biaozhu").val("");
	$("#pinyin").val("");
	form.render();
}

function showAdd(){
	layerId=layer.open({
		  title:'新增片区',
		  type:1,
		  area:'600px',
		  content: $('#addCommunity')
		}); 
	$("#addCommunity input[type=text]").val("");
	$("#addCommunity input[type=hidden]").val("");
	$("#region").children().remove();
	form.render();
	$('#addCommunity').removeClass("layui-hide");
	
}

var table ;
var layer;
var form;

 layui.use(['table','layer','form'], function(){
  table = layui.table;
  layer=layui.layer;
  form=layui.form;
  
  form.on('submit(communityAdd)', function(data){
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: data.field,
	            url: "<%=request.getContextPath()%>/manage/community/add" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		layer.close(layerId);
						table.reload("communityTable");
	            		layer.msg("小区信息保存成功");
	            	}else{
	            		layer.alert(result.msg);
	            	}
	            },
	            error : function() {	            	
	                layer.alert("异常！");
	            }
	        });
		  
		  
		  return false;
	 });
  
  form.verify({
		title:function(value, item){
			if(value.length>20){
				return "小区名称长度不能大于20";
			}
		}
	  ,address:function(value, item){
			if(value.length>200){
				return "小区地址长度不能大于200";
			}
		}
	});
  var searchArea=new AreaObject(areaList,function(value,name){
	  $("#queryAreaCode").val(value);
	  $("#areaCode").val(name);	 
	  
  },3,$("#areaCode"));
  //searchArea.show();
 /*  $("#areaCode").on("click",function(){
	 	  
  }) ; */
  $("#areaCode").on("change",function(){
	  if($("#areaCode").val()==""){
		  $("#queryAreaCode").val("");
	  }
  });
  //第一个实例
  table.render({
	  id:'communityTable',
    elem: '#communityTable'
    ,height: 'full-200'
    ,method:'post'
    //,width:'1000px'
    //,cellMinWidth: '100px'
    ,url: '<%=request.getContextPath()%>/manage/community/searchCommunityList?menu_id=${menu_id}' //数据接口
   //,data:data
    ,page: true //开启分页
    ,cols: [[ //表头
    	{field:'name', title:'小区名称',width:100
    		,templet:function(d){
    			return '<a style="color:#01AAED;cursor:pointer;font-size:6px;" href="<%=request.getContextPath()%>/manage/community/detail?id='+d.id+'">'+d.name+'</a>';
    		}
    	},
    	{field: 'recordName',title:'备案名',width:100},
    	{field: 'pinyin',title:'拼音检索',width:200},
    	{field: 'reginName',title:'所在片区',width:100},
    	{field: 'areaName',title:'所属行政区',width:100},
    	{field: 'salePrice',title:'出售均价',width:100
    		,templet:function(d){
    			return d.salePrice+'元/m<sup>3</sup>';
    		}},
    	{field: 'longitude',title:'地图坐标',width:100
    	,templet:function(d){
    		if(d.longitude){
    			return "已标注";
    		}
			return "未标注";
		}},
    	{field: 'address',title:'小区地址'},
        {field: 'id', title: '操作',width:100
    	  ,templet:function(d){
    		  return '<span lay-event="updateInfo" style="color:#01AAED;cursor:pointer;font-size:6px;">编辑</span>&nbsp;'
			  //+'<span lay-event="showPinyin" style="color:#01AAED;cursor:pointer;font-size:6px;">更正拼音</span>&nbsp;'
    		  +'<span lay-event="delete" style="color:#01AAED;cursor:pointer;font-size:6px;">删除</span>&nbsp;'
    		  ;
    		  }}
    ]]
  });
//监听工具条
  table.on('tool(communityTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'updateInfo'){
    	layerId=layer.open({
  		  title:'编辑小区',
  		  type:1,
  		  area:'600px',
  		  content: $('#addCommunity')
  		}); 
	  	$("#addCommunity input[type=text]").val("");
	  	$("#addCommunity input[type=hidden]").val("");
	  	$("#region").children().remove();
	  	$("#addCommunity input[name]").each(function(){
	  		if(data[$(this).attr("name")]!=null&&$(this).attr("type")!='checkbox'){
	  			$(this).val(data[$(this).attr("name")]);
	  		}  		
	  	});
	  	if(data.isHot){
	  		$("#addCommunity input[name=isHot]").attr("checked","true");
	  	}else{
	  		$("#addCommunity input[name=isHot]").attr("checked","false");
	  	}	  	
	  	form.render();
	  	setCommunity(data.areaCode,data.areaName,data.regionId);
	  	$('#addCommunity').removeClass("layui-hide");
    } else if(layEvent === 'showPinyin'){
    	//更正拼音
    	var pycontent=data.pinyin;
    	if(pycontent==null||pycontent=='undefined'){
    		pycontent="";
    	}
    	
    	layer.open({
    		  title:"更正拼音",
    		  content: '<input type="text"  id="pinyinEdit"  placeholder="拼音检索" class="layui-input" value="'+pycontent+'">',
    		  yes: function(index, layero){
    		    if($("#pinyinEdit").val().length>50){
    		    	layer.msg("长度不能大于50");
    		    }else{
    		    	 $.ajax({
    			            type: "POST",//方法类型
    			            dataType: "json",//预期服务器返回的数据类型
    			            data: {pinyin:$("#pinyinEdit").val(),id:data.id},
    			            url: "<%=request.getContextPath()%>/manage/community/update" ,	                
    			            //data:data,
    			            success: function (result) {
    			            	if(result.success){
    			            		layer.close(index); 
    			            		table.reload("communityTable");
    			            	}else{
    			            		layer.alert(result.msg);
    			            	}
    			            },
    			            error : function() {	            	
    			                layer.alert("异常！");
    			            }
    			        });
    		    }
    		    
    		  }
    		}); 
    	
    } else if(layEvent === 'delete'){
    	layer.confirm('您确认要删除吗?', function(index){
    		$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            data: {id:data.id},
	            url: "<%=request.getContextPath()%>/manage/community/deleteInfo" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		layer.close(index); 
	            		layer.msg("数据删除成功");
	            		table.reload("communityTable");
	            	}else{
	            		layer.alert(result.msg);
	            	}
	            },
	            error : function() {	            	
	                layer.alert("异常！");
	            }
	        });
    	});  	 
    } 
  }); 

}); 
  
 

</script>

</body>

</html>