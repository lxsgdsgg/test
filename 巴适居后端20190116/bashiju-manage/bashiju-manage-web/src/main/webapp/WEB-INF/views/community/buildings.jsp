<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座栋管理</title>
<%-- 
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all"> --%>

<style>
.input-group {
    position: relative;
    display: table;
    border-collapse: separate;
}
.input-group .form-control:not(:first-child):not(:last-child), .input-group-addon:not(:first-child):not(:last-child) {
    border-radius: 0;
}
.input-group .form-control,  .input-group .input-group-addon{
    width: auto;
    display: table-cell;
}
.input-group-addon:last-child {
    border-left: 0;
}
.input-group .form-control:last-child, .input-group-addon:last-child {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
}
.input-group-addon {
    padding: 1px 3px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1;
    color: #c2c2c2;
    text-align: center;
    border-bottom: 1px solid #D2D2D2;
    border-top:1px solid #D2D2D2;
    border-right:1px solid #D2D2D2;
    border-left:0;
    border-radius: 2px;
}
.form-control {
    /* padding: 6px 12px; */
    /* font-size: 14px; */
    line-height: 1.42857143;
    /* background-color: #fff; */
    background-image: none;
    border-bottom: 1px solid #D2D2D2;
    border-top:1px solid #D2D2D2;
    border-left:1px solid #D2D2D2;
    border-right:0;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    border-radius: 2px;
}
.hoseTable td,th{
	min-width:60px;
	 
	 padding:5px;
	 text-align: center;
}
.savebtn{
	    cursor: pointer;
}
</style>
</head>
<body>
	<div class="">  
		<div class="layui-row">
			<div class="layui-col-md9" >&nbsp;</div>
			<div class="layui-col-md3" >
				<button  onClick="showAddBuildingd()" class="layui-btn">增加座栋</button>
				<button class="layui-btn" onClick="showBatchAddBuildingd()">批量增加座栋</button>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md6" >
				<table class="layui-table" id="buildingTable" lay-filter="buildingTable"></table> 
			</div>
			<div class="layui-col-md6">
				<table class="layui-table" id="unitTable" lay-filter="unitTable"></table> 
			</div>
		</div>
		
		
	</div>
	
	<!-- 添加座栋表单 --> 	
<form class="layui-form layui-hide" id="addBuilding" style="margin:30px;">
<input type="hidden" name="id">
  <div class="layui-form-item">
  	<div class="layui-col-md6" >
	  	<label class="layui-form-label"><span style="color:red;">*</span>座栋名称</label>
	    <div class="layui-input-block">
	      <div class="layui-col-md6" >
	      	<input type="text" name="name" lay-verify="required|name" class="layui-input" style="width:100%;">
	      </div>
	      <div class="layui-col-md6" >
	      <select name="buildUnitAlias"  lay-verify="required" >
	      <option value=""></option>
	      	<option value="号楼">号楼</option>
	      	<option value="栋">栋</option>
	      	<option value="幢">幢</option>
	      	<option value="号">号</option>
	      	<option value="弄">弄</option>
	      	<option value="座">座</option>
	      	<option value="阁">阁</option>
	      </select>
	      </div>
	    </div>
  	</div>
  	<div class="layui-col-md5" >
  		<label class="layui-form-label"><span style="color:red;">*</span>房号起始值</label>
	    <div class="layui-input-block">
	      <input type="text" name="houseStartNumber" lay-verify="number" class="layui-input">
	    </div>
  	</div>
    
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">建筑年代</label>
    <div class="layui-input-inline">
      <div class="input-group">
	    	<input type="text" name="buildYear" lay-verify="number" class="form-control" style="width:80px;height:27px;">
	    	<div class="input-group-addon">年</div>
	    	</div>
    </div>
  </div>
  <div class="layui-form-item">
  <div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>单元数量</label>
    <div class="layui-input-block">
    <div class="layui-col-md5" >
      <input type="text" name="buildingUnitCount" lay-verify="number" class="layui-input">
      </div>
      <div class="layui-col-md6" >
      <select name="unitAlias"  lay-verify="required">
      <option value=""></option>
      	<option value="单元">单元</option>
      	<option value="号楼">号楼</option>
      	<option value="号">号</option>
      	<option value="门">门</option>
      	<option value="座">座</option>
      </select>
      </div>
    </div>
    
    </div>
    <div class="layui-form-mid layui-word-aux"><span style="color:red;">无单元时写0</span></div>
  </div>
  <div class="layui-form-item">
  <div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>起始楼层</label>
    <div class="layui-input-block">
      <div class="input-group">
	    	<input type="text" name="floorStartNumber" lay-verify="number" class="form-control" style="width:50px;height:27px;">
	    	<div class="input-group-addon">层</div>
	    	</div>
    </div>
   </div>
   <div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>总楼层</label>
    <div class="layui-input-block">
      <div class="input-group">
	    	<input type="text" name="floorTotal" lay-verify="number" class="form-control" style="width:50px;height:27px;">
	    	<div class="input-group-addon">层</div>
	    	</div>
    </div>
   </div>
  </div>

 <div class="layui-form-item">
 	<div class="layui-col-md6" >
    <label class="layui-form-label">单元信息</label>
    <div class="layui-input-block">
    <div class="layui-col-md5" >
       <div class="input-group">
	    	<input type="text" name="unitElevatorCount" lay-verify="number" class="form-control" style="width:45px;height:27px;">
	    	<div class="input-group-addon">梯</div>
	    	</div>
      </div>
      <div class="layui-col-md5" >
      <div class="input-group">
	    	<input type="text" name="unitHouseCount" lay-verify="number" class="form-control" style="width:40px;height:27px;">
	    	<div class="input-group-addon">户</div>
	    	</div>
      </div>
    </div>
    </div>
    <div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>单元起始值</label>
    <div class="layui-input-block">
    <div class="layui-col-md5" >
      <input type="text" name="unitStartNumber" lay-verify="number" class="layui-input">
      </div>
      <div class="layui-col-md6" >
      <select id="unitAlias"  lay-verify="required">
      <option value=""></option>
      	<option value="单元">单元</option>
      	<option value="号楼">号楼</option>
      	<option value="号">号</option>
      	<option value="门">门</option>
      	<option value="座">座</option>
      </select>
      </div>
    </div>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">跳楼层</label>
    <div class="layui-input-inline" style="width:120px;">
      <input type="text" name="skipFloor" lay-verify="skip" class="layui-input" style="width:120px;">
    </div>
    <div class="layui-form-mid layui-word-aux"><span style="color:red;">多个楼层时请用用英文逗号,隔离</span></div>
  </div>
  
  <div class="layui-form-item">
	<div class="layui-input-block">
    <button class="layui-btn" lay-submit lay-filter="buildingAdd">保存</button>
	</div>
  </div>
</form>

<!-- 批量添加座栋表单 --> 	
<form class="layui-form layui-hide" id="batchAddBuilding" style="margin:30px;">
  <div class="layui-form-item">
  	<div class="layui-col-md9" >
	  	<label class="layui-form-label"><span style="color:red;">*</span>座栋名称</label>
	    <div class="layui-input-block">
	    <div class="layui-col-md3" >
	    	<div class="input-group">
	    	<input type="text" name="prefix" lay-verify="length" class="form-control" style="width:50px;height:27px;">
	    	<div class="input-group-addon">前缀</div>
	    	&nbsp;加
	    	</div>
	      	 
	      </div>
	      <div class="layui-col-md2" >	      
	      <select name="nameType" id="nameType" lay-verify="required" >
	      	<option value="1">数字</option>
	      	<option value="2">字母</option>
	      </select>
	      </div>
	      <div class="layui-col-md2" >
	      	从
	      	<input type="text" name="begin" id="begin" lay-verify="required|begin" class="layui-input" style="display: inline-block;width:50px;">
	      </div>
	      <div class="layui-col-md2" >
	      	-
	      	<input type="text" name="end" lay-verify="required|end" class="layui-input" style="display: inline-block;width:50px;">
	      </div>
	      <div class="layui-col-md2" >
	      <select name="buildUnitAlias"  lay-verify="required" >
	      <option value=""></option>
	      	<option value="号楼">号楼</option>
	      	<option value="栋">栋</option>
	      	<option value="幢">幢</option>
	      	<option value="号">号</option>
	      	<option value="弄">弄</option>
	      	<option value="座">座</option>
	      	<option value="阁">阁</option>
	      </select>
	      </div>
	    </div>
  	</div>
  	<div class="layui-col-md3" >
  		<label class="layui-form-label"><span style="color:red;">*</span>总楼层</label>
    <div class="layui-input-block">    
      <div class="input-group">
	    	<input type="text" name="floorTotal" lay-verify="number" class="form-control" style="width:50px;height:27px;">
	    	<div class="input-group-addon">层</div>
	    	</div>
    </div>  
  	</div>
    
  </div>
<div class="layui-form-item">
	<div class="layui-col-md6" >
	    <label class="layui-form-label">建筑年代</label>
	    <div class="layui-input-inline">
	      <div class="input-group">
	    	<input type="text" name="buildYear" lay-verify="number" class="form-control" style="width:100px;height:27px;">
	    	<div class="input-group-addon">年</div>
	    	</div>
	    </div>
    </div>
    <div class="layui-col-md4" >
    <label class="layui-form-label"><span style="color:red;">*</span>单元数量</label>
    <div class="layui-input-block">
    <div class="layui-col-md5" >
      <input type="text" name="buildingUnitCount" lay-verify="number" class="layui-input">
      </div>
      <div class="layui-col-md7" >
      <select name="unitAlias"  lay-verify="required">
      <option value=""></option>
      	<option value="单元">单元</option>
      	<option value="号楼">号楼</option>
      	<option value="号">号</option>
      	<option value="门">门</option>
      	<option value="座">座</option>
      </select>
      </div>
    </div>   
    </div>
    <div class="layui-form-mid layui-word-aux"><span style="color:red;">无单元时写0</span></div>
  </div>
  <div class="layui-form-item">
  <div class="layui-col-md6" >
  		<label class="layui-form-label"><span style="color:red;">*</span>房号起始值</label>
	    <div class="layui-input-block">
	      <input type="text" name="houseStartNumber" lay-verify="number" class="layui-input">
	    </div>
  	</div>
    <div class="layui-col-md5" >
    <label class="layui-form-label"><span style="color:red;">*</span>单元信息</label>
    <div class="layui-input-block">
    <div class="layui-col-md4" >
  
      <div class="input-group">
	    	<input type="text" name="unitElevatorCount" lay-verify="number" class="form-control" style="width:45px;height:27px;">
	    	<div class="input-group-addon">梯</div>
	    	</div>
      </div>
      <div class="layui-col-md5" >
     
      <div class="input-group">
	    	<input type="text" name="unitHouseCount" lay-verify="number" class="form-control" style="width:40px;height:27px;">
	    	<div class="input-group-addon">户</div>
	    	</div>
      </div>
    </div>
    </div>
  </div>

 <div class="layui-form-item">
 	<div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>单元起始值</label>
    <div class="layui-input-block">
    <div class="layui-col-md3" >
      <input type="text" name="unitStartNumber" lay-verify="number" class="layui-input">
      </div>
      <div class="layui-col-md4" >
      <select  lay-verify="required">
      <option value=""></option>
      	<option value="单元">单元</option>
      	<option value="号楼">号楼</option>
      	<option value="号">号</option>
      	<option value="门">门</option>
      	<option value="座">座</option>
      </select>
      </div>
    </div>
    </div>
    <div class="layui-col-md5" >
    <label class="layui-form-label"><span style="color:red;">*</span>起始楼层</label>
    <div class="layui-input-block">
      <div class="input-group">
	    	<input type="text" name="floorStartNumber" lay-verify="number" class="form-control" style="width:60px;height:27px;">
	    	<div class="input-group-addon">层</div>
	    	</div>
    </div>
   </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">跳楼层</label>
    <div class="layui-input-inline" style="width:150px;">
      <input type="text" name="skipFloor" lay-verify="skip"  class="layui-input" style="width:150px;">
    </div>
    <div class="layui-form-mid layui-word-aux"><span style="color:red;">多个楼层时请用用英文逗号,隔离</span></div>
  </div>
  
  <div class="layui-form-item">
	<div class="layui-input-block">
    <button class="layui-btn" lay-submit lay-filter="buildingBatchAdd">保存</button>
	</div>
  </div>
</form>

	<!-- 添加座栋单元 --> 	
<form class="layui-form layui-hide" id="addBuildingUnit" style="margin:30px;">
<input type="hidden" name="id">
<input type="hidden" name="buildingId">
  <div class="layui-form-item">
  	<div class="layui-col-md6" >
	  	<label class="layui-form-label"><span style="color:red;">*</span>单元名称</label>
	    <div class="layui-input-block">
	    <div class="layui-col-md5" >
	      <input type="text" name="name" lay-verify="required|length" class="layui-input">
	      </div>
	      <div class="layui-col-md6" >
	      <select name="unitAlias"  lay-verify="required">
	      <option value=""></option>
	      	<option value="单元">单元</option>
	      	<option value="号楼">号楼</option>
	      	<option value="号">号</option>
	      	<option value="门">门</option>
	      	<option value="座">座</option>
	      </select>
	      </div>
	    </div>
  	</div>
  	<div class="layui-col-md6" >
	  	<label class="layui-form-label"><span style="color:red;">*</span>单元信息</label>
	    <div class="layui-input-block">
	    <div class="layui-col-md5" >
	       <div class="input-group">
		    	<input type="text" name="unitElevatorCount" lay-verify="number" class="form-control" style="width:45px;height:27px;">
		    	<div class="input-group-addon">梯</div>
		    	</div>
	      </div>
	      <div class="layui-col-md5" >
	      <div class="input-group">
		    	<input type="text" name="unitHouseCount" lay-verify="number" class="form-control" style="width:40px;height:27px;">
		    	<div class="input-group-addon">户</div>
		    	</div>
	      </div>
	    </div>
  	</div>
    
  </div>

  <div class="layui-form-item">
	  <div class="layui-col-md6" >
		    <label class="layui-form-label"><span style="color:red;">*</span>总楼层</label>
		    <div class="layui-input-block">
		      <div class="input-group">
			    	<input type="text" name="floorTotal" lay-verify="number" class="form-control" style="width:50px;height:27px;">
			    	<div class="input-group-addon">层</div>
			    	</div>
		    </div>
	   </div>
	  <div class="layui-col-md6" >
	   		<label class="layui-form-label"><span style="color:red;">*</span>房号起始值</label>
		    <div class="layui-input-block">
		      <input type="text" name="houseStartNumber" lay-verify="number" class="layui-input">
		    </div>
	    
	    </div>
  </div>
  <div class="layui-form-item">
  <div class="layui-col-md6" >
    <label class="layui-form-label"><span style="color:red;">*</span>起始楼层</label>
    <div class="layui-input-block">
      <div class="input-group">
	    	<input type="text" name="floorStartNumber" lay-verify="number" class="form-control" style="width:50px;height:27px;">
	    	<div class="input-group-addon">层</div>
	    	</div>
    </div>
   </div>
   
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">跳楼层</label>
    <div class="layui-input-inline" style="width:120px;">
      <input type="text" name="skipFloor" lay-verify="skip" class="layui-input" style="width:120px;">
    </div>
    <div class="layui-form-mid layui-word-aux"><span style="color:red;">多个楼层时请用用英文逗号,隔离</span></div>
  </div>
  
  <div class="layui-form-item">
	<div class="layui-input-block">
    <button class="layui-btn" lay-submit lay-filter="buildingUnitSave">保存</button>
	</div>
  </div>
</form>

<!-- 设置房号 --> 
<div id="setHouse" class="layui-hide">
	<table class="hoseTable">
	</table>
</div>

<!-- 查看座栋 --> 
<div id="showBuilding" class="layui-hide">
	<div class="layui-row">
	&nbsp;
	</div>
	<div class="layui-row">
		<div class="layui-col-md2 layui-elem-field">
			<table class="layui-table" id="buildingNameTable"  lay-filter="buildingNameTable"></table> 
		</div>
		<div class="layui-col-md2 layui-elem-field">
			<table class="layui-table" id="unitNameTable" lay-filter="unitNameTable"></table> 
		</div>
		<div class="layui-col-md8 layui-elem-field">
			<table id="showHouses" class=" layui-table"></table>
		</div>
	</div>
		
</div>

<%--    
    --%>

<script>

function showBuildingDetails(){
	layerId=layer.open({
		  title:'查看销控',
		  type:1,
		  area:['800px','600px'],
		  content: $('#showBuilding')
		}); 
  	$('#showBuilding').removeClass("layui-hide");
	table.render({
		  id:'buildingNameTable',
	    elem: '#buildingNameTable'
	    ,height: 'full-200'
	    ,limit:1000
	    ,url: '/manage/building/queryBuildingList' //数据接口
	    ,method:'post'
	    ,where:{cid:community.id}
	    ,cols: [[ //表头
	    	{field:'name', title:'座栋名称',width:125
	    		,templet:function(d){
	    			return '<span style="color:#01AAED;cursor:pointer;width:100%;display:block;" lay-event="lookUnits" >'+d.name+d.buildUnitAlias+'</span>';
	    		}
	    	}
	    ]]
	    ,done: function(res, curr, count){
	    	if(count>0){
	    		
	    		loadUnitNameTable(res.data[0].id);
	    	}else{
	    		loadUnitNameTable(0);
	    	}	    	
	      }
	  });
	
	table.on('tool(buildingNameTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'lookUnits'){
	    	loadUnitNameTable(data.id);
	    } 
	  }); 
}

function loadUnitNameTable(bid){
	$("#buildingNameTable").parent().find("tr[data-index=0]").addClass("layui-table-click");
	table.render({
		  id:'unitNameTable',
	    elem: '#unitNameTable'
	    ,height: 'full-200'
	    ,limit:50
	    //,width:'1000px'
	    //,cellMinWidth: '100px'
	    ,url: '/manage/building/queryBuildingUnitList' //数据接口
	    ,method:'post'
	    ,where:{bid:bid}
	    //,page: true //开启分页
	    ,cols: [[ //表头
	    	{field:'name', title:'单元名称',width:125
		    	,templet:function(d){
		    		if(d.name==""){
		    			return '<span lay-event="lookHouse" style="color:#01AAED;cursor:pointer;font-size:6px;width:100%;display:block;>无单元</span>'
		    		}
		    		 return '<span lay-event="lookHouse" style="color:#01AAED;cursor:pointer;font-size:6px;width:100%;display:block;">'+d.name+d.unitAlias+'</span>'
		    	}
	    	}
	    ]]
	    ,done: function(res, curr, count){
	    	if(count>0){
	    		loadHouseNO(res.data[0].id,res.data[0].unitHouseCount,res.data[0].houseStartNumber);
	    	}else{
	    		$("#showHouses").empty();
	    	}	    	
	      }
	  });
	table.on('tool(unitNameTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    
		var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值

	    if(layEvent === 'lookHouse'){
	    	loadHouseNO(data.id,data.unitHouseCount,data.houseStartNumber);
	    } 
	  }); 
}

function loadHouseNO(uid,unitHouseCount,houseStartNumber){
	$("#unitNameTable").parent().find("tr[data-index=0]").addClass("layui-table-click");
	$("#showHouses").empty();
	$.ajax({
	    type: "POST",//方法类型
	    dataType: "json",//预期服务器返回的数据类型
	    data: {buid:uid},
	    url: "<%=request.getContextPath()%>/manage/building/queryHouses" ,	                
	    success: function (result) {console.log(result);
	    	if(result.success){
	    		if(result.data!=null){	 
	    			$("#showHouses").append('<thead><tr></tr></thead>');
	    			for(var h=houseStartNumber;h<(unitHouseCount+houseStartNumber);h++){
	    				$("#showHouses>thead>tr").append('<th>第'+h+'户</th>');
	    			}
	    			$("#showHouses").append('<tbody></tbody>');
	    			var startNO=houseStartNumber;
	    			for(var i=0;i<result.data.length;i++){
	    				//判断楼层是否读完
	    				if(startNO>=(unitHouseCount+houseStartNumber)){
	    					startNO=houseStartNumber;
	    				}
	    				if(startNO==houseStartNumber){
	    					$("#showHouses tbody").append('<tr floor="'+result.data[i].floor+'"><td>'+result.data[i].houseNumber+'</td></tr>');
	    				}else{
	    					$("#showHouses tbody tr[floor="+result.data[i].floor+"]").append('<td>'+result.data[i].houseNumber+'</td>');
	    				}
	    				startNO++;
	    			}    			
	    		}
	  				
	    	}else{
	    		layer.msg(result.msg);
	    	}
	    },
	    error : function() {	            	
	        layer.alert("异常！");
	    }
	});
}
function showUnitTable(bid){
	table.render({
		  id:'unitTable',
	    elem: '#unitTable'
	    ,height: 'full-200'
	    ,limit:50
	    //,width:'1000px'
	    //,cellMinWidth: '100px'
	    ,url: '/manage/building/queryBuildingUnitList' //数据接口
	    ,method:'post'
	    ,where:{bid:bid}
	    //,page: true //开启分页
	    ,cols: [[ //表头
	    	{field:'name', title:'单元名称',width:100
		    	,templet:function(d){
		    		if(d.name==""){
		    			return "无单元";
		    		}
		    		return d.name+d.unitAlias;	
		    	}
	    	},
	    	{field: 'unitHouseCount',title:'梯户',width:100
	    		,templet:function(d){
		    		return d.unitElevatorCount+"梯"+d.unitHouseCount+"户";	
		    	}
	    	},
	    	{field: 'floorTotal',title:'总层数',width:100},
	    	{field: 'houseStartNumber',title:'房号起始值',width:100},
	    	
	        {field: 'id', title: '操作',width:150
	    	  ,templet:function(d){
	    		  return '<span lay-event="setHouse" style="color:#01AAED;cursor:pointer;font-size:6px;">设置房号</span>&nbsp;'
				  +'<span lay-event="edit" style="color:#01AAED;cursor:pointer;font-size:6px;">编辑</span>&nbsp;';
	    		  /* +'<span lay-event="del" style="color:#01AAED;cursor:pointer;font-size:6px;">删除</span>'; */
	    		  }}
	    ]]
	  });
	//监听工具条
	  table.on('tool(unitTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'setHouse'){
	    	$("#setHouse .hoseTable").empty();
	    	$.ajax({
	    	    type: "POST",//方法类型
	    	    dataType: "json",//预期服务器返回的数据类型
	    	    //contentType: 'application/json',
	    	    data: {buid:data.id},
	    	    url: "<%=request.getContextPath()%>/manage/building/queryHouses" ,	                
	    	    //data:data,
	    	    success: function (result) {
	    	    	if(result.success){
	    	    		if(result.data!=null){
	    	    			
	    	    			for(var i=0;i<result.data.length;i++){
	    	    				if($("#setHouse .hoseTable").find("tr[floor="+result.data[i].floor+"]").length>0){
	    	    					var el='<td><div class="input-group">'+
	    	    					'<input type="text" id="hid-'+result.data[i].id+'"   class="form-control" style="width:50px;height:27px;" house-name="'+result.data[i].houseNumber+'" value="'+result.data[i].houseNumber+'">'+
	    	    					'<div class="input-group-addon savebtn" onClick="saveHouseNO(\''+result.data[i].id+'\')" title="点击保存"><i class="layui-icon">✎</i></div>'+
	    	    					'</div></td>';
	    	    					$("#setHouse .hoseTable tr[floor="+result.data[i].floor+"]").append(el);
	    	    				}else{
	    	    					$("#setHouse .hoseTable").append('<tr floor="'+result.data[i].floor+'"><td>'+result.data[i].floor+'楼</td></tr>');
	    	    					var el='<td><div class="input-group">'+
	    	    					'<input type="text" id="hid-'+result.data[i].id+'"   class="form-control" style="width:50px;height:27px;" house-name="'+result.data[i].houseNumber+'"  value="'+result.data[i].houseNumber+'">'+
	    	    					'<div class="input-group-addon savebtn" onClick="saveHouseNO(\''+result.data[i].id+'\')" title="点击保存"><i class="layui-icon">✎</i></div>'+
	    	    					'</div></td>';
	    	    					$("#setHouse .hoseTable tr[floor="+result.data[i].floor+"]").append(el);
	    	    				}
	    	    			}
	    	    			layerId=layer.open({
	    	    		  		  title:'设置房号',
	    	    		  		  type:1,
	    	    		  		  area:['800px','500px'],
	    	    		  		  content: $('#setHouse')
	    	    		  		}); 
	    	    		    	$('#setHouse').removeClass("layui-hide");
	    	    		    	
	    	    		}
	    	  
	    				
	    	    	}else{
	    	    		layer.msg(result.msg);
	    	    	}
	    	    },
	    	    error : function() {	            	
	    	        layer.alert("异常！");
	    	    }
	    	});
	    } else if(layEvent === 'edit'){
	    	showUpdateBuildingUnit(data.id);	    	 	    	
	    } 
	  }); 
}

function saveHouseNO(hid){
	
		 if($("#hid-"+hid).val()!=$("#hid-"+hid).attr("house-name")){
			 /*if($("#hid-"+hid).val()==""){
				var content="您确定要删除房号:<span style='color:red;'>"+$("#hid-"+hid).attr("house-name")+"</span>吗？";
				layer.confirm(content, function(index){
					  //do something
					  layer.close(index);
					}, function(index){
						$("#hid-"+hid).val($("#hid-"+hid).attr("house-name"));
						  layer.close(index);
						}); 
				return 1;
			} */
		var content="您确定要修正房号:<span style='color:red;'>"+$("#hid-"+hid).attr("house-name")+"</span>为：<span style='color:red;'>"+$("#hid-"+hid).val()+"</span>吗？";
		layer.confirm(content, function(index){
			var house=new Object();
			house.id=hid;
			house.houseNumber=$("#hid-"+hid).val();
			$.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(house),},
	            url: "<%=request.getContextPath()%>/manage/building/saveHouseName" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		$("#hid-"+hid).attr("house-name",$("#hid-"+hid).val());
	            		layer.close(index);
	            		layer.msg("保存成功");
	            	}else{
	            		layer.alert(result.msg);
	            	}
	            },
	            error : function() {	            	
	                layer.alert("异常！");
	            }
	        });
			}, function(index){
				$("#hid-"+hid).val($("#hid-"+hid).attr("house-name"));
				  layer.close(index);
				}); 
	}
	
}

function showBatchAddBuildingd(){
	layerId=layer.open({
		  title:'批量新增座栋',
		  type:1,
		  area:'800px',
		  content: $('#batchAddBuilding')
		}); 
	$('#batchAddBuilding').removeClass("layui-hide");
	form.render();
	form.on('submit(buildingBatchAdd)', function(data){
		 
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(data.field),areaCode:community.areaCode,areaName:community.areaName,communityId:community.id},
	            url: "<%=request.getContextPath()%>/manage/building/batchAddBuilding" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
						table.reload("buildingTable");
	            		layer.close(layerId);
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
}

function showAddBuildingd(){
	$("#addBuilding input").each(function (index, domEle) { 
		 	$(this).val("");					 
		});
	 $("#addBuilding select").each(function (index, domEle) { 
			$(this).val("");		 
		});	
	 $("#addBuilding input").removeClass("layui-disabled");

	layerId=layer.open({
		  title:'新增座栋',
		  type:1,
		  area:'600px',
		  content: $('#addBuilding')
		}); 
	$('#addBuilding').removeClass("layui-hide");
	form.render();
	 form.on('submit(buildingAdd)', function(data){
		 
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(data.field),areaCode:community.areaCode,areaName:community.areaName,communityId:community.id},
	            url: "<%=request.getContextPath()%>/manage/building/addBuilding" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
						table.reload("buildingTable");
	            		layer.close(layerId);
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
}

function showUpdateBuildingd(bid){
	
	layerId=layer.open({
		  title:'编辑座栋',
		  type:1,
		  area:'600px',
		  content: $('#addBuilding')
		}); 
	$('#addBuilding').removeClass("layui-hide");
	form.render();
	 form.on('submit(buildingAdd)', function(data){
		 
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(data.field)},
	            url: "<%=request.getContextPath()%>/manage/building/updateBuilding" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
						table.reload("buildingTable");
	            		layer.close(layerId);
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
	 
	 $.ajax({
         type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         //contentType: 'application/json',
         data: {id:bid},
         url: "<%=request.getContextPath()%>/manage/building/queryBuilding" ,	                
         //data:data,
         success: function (result) {
         	if(result.success){
         		 $("#addBuilding input").each(function (index, domEle) { 
         		 	$(this).val("");
         			if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
         				$(this).val("0");
         		 	}			
         			var attr=$(this).attr("name");
         			  if(result.data[attr]!=null){		  
         				  $(this).val(result.data[attr]);
         			  }
         		 
         		});
         	 $("#addBuilding select").each(function (index, domEle) { 
         			$(this).val("");
         			if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
         				$(this).val("0");
         		 	}
         			var attr=$(this).attr("name");
         			  if(result.data[attr]!=null){		  
         				  $(this).val(result.data[attr]);
         			  }
         		 
         		});	
         	 $("#unitAlias").val($("#addBuilding select[name=unitAlias]").val());
         	 $("#addBuilding input[name=houseStartNumber]").addClass("layui-disabled");
         	$("#addBuilding input[name=buildingUnitCount]").addClass("layui-disabled");
         	$("#addBuilding input[name=floorStartNumber]").addClass("layui-disabled");
         	$("#addBuilding input[name=floorTotal]").addClass("layui-disabled");
         	$("#addBuilding input[name=unitHouseCount]").addClass("layui-disabled");
         	$("#addBuilding input[name=unitStartNumber]").addClass("layui-disabled");
         	$("#addBuilding input[name=skipFloor]").addClass("layui-disabled");
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
//显示添加座栋单元窗体
function showAddBuildingUnit(bid){
	$("#addBuildingUnit input").each(function (index, domEle) { 
		 	$(this).val("");					 
		});
	 $("#addBuildingUnit select").each(function (index, domEle) { 
			$(this).val("");		 
		});	
	 $("#addBuildingUnit input").removeClass("layui-disabled");
	 $("#addBuildingUnit input[name=buildingId]").val(bid);
	 
	layerId=layer.open({
		  title:'新增座栋单元',
		  type:1,
		  area:'600px',
		  content: $('#addBuildingUnit')
		}); 
	$('#addBuildingUnit').removeClass("layui-hide");
	form.render();
	 form.on('submit(buildingUnitSave)', function(data){
		 
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(data.field)},
	            url: "<%=request.getContextPath()%>/manage/building/addBuildingUnit" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
						table.reload("buildingTable");
						layer.msg("保存成功");
	            		layer.close(layerId);
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
}
//修改单元
function showUpdateBuildingUnit(buid){
	
	layerId=layer.open({
		  title:'修改单元',
		  type:1,
		  area:'600px',
		  content: $('#addBuildingUnit')
		}); 
	$('#addBuildingUnit').removeClass("layui-hide");
	form.render();
	 form.on('submit(buildingUnitSave)', function(data){
		 
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: {dataJson:JSON.stringify(data.field)},
	            url: "<%=request.getContextPath()%>/manage/building/updateBuildingUnit" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
						table.reload("unitTable");
						layer.msg("保存成功");
	            		layer.close(layerId);
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
	 
	 $.ajax({
         type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         //contentType: 'application/json',
         data: {id:buid},
         url: "<%=request.getContextPath()%>/manage/building/queryBuildingUnit" ,	                
         //data:data,
         success: function (result) {
         	if(result.success){
         		 $("#addBuildingUnit input").each(function (index, domEle) { 
         		 	$(this).val("");		
         			var attr=$(this).attr("name");
         			  if(result.data[attr]!=null){		  
         				  $(this).val(result.data[attr]);
         			  }
         		 
         		});
         	 $("#addBuildingUnit select").each(function (index, domEle) { 
         			$(this).val("");
         			var attr=$(this).attr("name");
         			  if(result.data[attr]!=null){		  
         				  $(this).val(result.data[attr]);         				  
         			  }        		 
         		});	
        	 $("#addBuildingUnit input[name=houseStartNumber]").addClass("layui-disabled");
        	$("#addBuildingUnit input[name=floorStartNumber]").addClass("layui-disabled");
        	$("#addBuildingUnit input[name=floorTotal]").addClass("layui-disabled");
        	$("#addBuildingUnit input[name=unitHouseCount]").addClass("layui-disabled");
        	$("#addBuildingUnit input[name=skipFloor]").addClass("layui-disabled");
         	form.render();
         	}
         }
	 });
}
var table ;


 layui.use(['table'], function(){
  table = layui.table;
	
  
  var upZimu="QWERTYUIOPASDFGHJKLZXCVBNM";
  form.verify({
		name:function(value, item){
			if(isNaN(value)){
				if(value.length>1||upZimu.indexOf(value)==-1)
				return "座栋名称只能是1位大写字母或数字";
			}
		}
  		,begin:function(value, item){
  			if($("#nameType").val()=="1"){
  				if(isNaN(value))
  					return "只能是数字";
  			}
  			if($("#nameType").val()=="2"){
  				if(value.length>1||upZimu.indexOf(value)==-1)
  					return "只能是1位大写字母";
  			}
  		}
  		,end:function(value, item){
  			if($("#nameType").val()=="1"){
  				if(isNaN(value)){
  					return "只能是数字";
  				}
  				if(value<$("#begin").val()){
  					return "不能小于开始座栋号";
  				}	
  			}
  			if($("#nameType").val()=="2"){
  				if(value.length>1||upZimu.indexOf(value)==-1)
  					return "只能是1位大写字母";
  				if(value<$("#begin").val()){
  					return "不能小于开始座栋号";
  				}	
  			}
  		}
  		,skip:function(value, item){
  			if(value!=""&&!/^[1-9][0-9,]*$/g.test(value)){
				return "只能输入数字及英文逗号";
			}
			if(value.length>100){
				return "长度不能大于100";
			}
		}
  		,length:function(value, item){
  			if(value.length>10){
				return "长度不能大于10";
			}
  		}
	});
  
 
  //第一个实例
  table.render({
	  id:'buildingTable',
    elem: '#buildingTable'
    ,height: 'full-200'
    //,width:'1000px'
    //,cellMinWidth: '100px'
    ,url: '/manage/building/queryBuildingList' //数据接口
    ,method:'post'
    ,where:{cid:community.id}
    ,page: true //开启分页
    ,cols: [[ //表头
    	{field:'name', title:'座栋名称',width:100
    		,templet:function(d){
    			return '<span style="color:#01AAED;cursor:pointer;" lay-event="detail" >'+d.name+d.buildUnitAlias+'</span>';
    		}
    	},
    	{field: 'floorTotal',title:'楼高',width:100},
    	{field: 'buildYear',title:'建成年代',width:100},
    	{field: 'buildingUnitCount',title:'单元数',width:100},
    	
        {field: 'id', title: '操作',width:200
    	  ,templet:function(d){
    		  return '<span lay-event="lookbuild" style="color:#01AAED;cursor:pointer;font-size:6px;">查看座栋</span>&nbsp;'
			  +'<span lay-event="edit" style="color:#01AAED;cursor:pointer;font-size:6px;">编辑</span>&nbsp;'
    		  +'<span lay-event="addUnit" style="color:#01AAED;cursor:pointer;font-size:6px;">新增单元</span>';
    		  }}
    ]]
    ,done: function(res, curr, count){
    	if(count>0){
    		showUnitTable(res.data[0].id);
    	}else{
    		showUnitTable(0);
    	}
    	
      }
  });
//监听工具条
  table.on('tool(buildingTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'detail'){
    	showUnitTable(data.id);
    } else if(layEvent === 'lookbuild'){
    	showBuildingDetails();  	
    } else if(layEvent === 'edit'){
    	showUpdateBuildingd(data.id);
    } else if(layEvent === 'addUnit'){
    	showAddBuildingUnit(data.id);
    }
  }); 

}); 
  
 

</script>

</body>

</html>