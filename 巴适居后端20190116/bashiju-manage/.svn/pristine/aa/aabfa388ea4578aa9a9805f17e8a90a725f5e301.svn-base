<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<title>补充表单项</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all">
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
</head>
<body>
	<div>
		<div class="layui-row">
			<div class="layui-inline" style="margin:10px 100px;">
				<div class="layui-input-inline">
					<input type="hidden" id="codeHid">
					<input type="text" id="queryArea" placeholder="城市" class="layui-input">
				</div>
				<button id="searchFormBtn" class="layui-btn">查询</button>
			</div>
		</div>
		<div class="layui-row">
			<div  class="layui-col-md3">
				<fieldset class="layui-elem-field">
				  <div class="layui-field-box">
				  	<table id="dynamicform" lay-filter="dynamicform"></table>
				  </div>
				 </fieldset>
			</div>
			<div  class="layui-col-md8">
				<fieldset class="layui-elem-field">
				  <div class="layui-field-box">
				  	<table id="dynamicformfield" lay-filter="dynamicformfield"></table>
				  </div>
				 </fieldset>
			</div>
		</div>
		
	</div>



	<div>
		<form id="addFieldForm" class="layui-form layui-hide" style="margin:30px;">
		<input name="tableType" type="hidden" class="formData">
		<input name="customTableName" type="hidden" class="formData">
		<input name="tableName" type="hidden" class="formData">
		<input name="cityCode" type="hidden" class="formData">
		<input name="formId" type="hidden" class="formData">
			<div class="layui-form-item">
			    <label class="layui-form-label">类型</label>
			    <div class="layui-input-block">
			      <input type="text" id="formName" readonly="readonly"   class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>字段</label>
			    <div class="layui-input-block">
			      <input type="text" name="title"   lay-verify="required"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">必填</label>
			    <div class="layui-input-block">
			      <select name="fieldRequired"  >
			        <option value="">非必填</option>
			        <option value="required">必填</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">选择已有</label>
			    <div class="layui-input-block">
			      <select name="field" lay-filter="field" class="formData">
			        <option value=""></option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>填写方式</label>
			    <div class="layui-input-block">
			      <select name="fieldType" lay-filter="fieldType" lay-verify="required">
			        <option value="1">文本</option>
			        <option value="2">数字</option>
			        <option value="3">自定义下拉</option>
			      </select>
			    </div>
			</div>
			<div id="fieldUnit" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>单位</label>
			    <div class="layui-input-block">
			      <input type="text" name="fieldUnit"   class="layui-input formData">
			    </div>
			</div>
			<div id="fieldSelect" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>下拉选项</label>
			    <div class="layui-input-block">
			      <input type="text" name="value"    class="layui-input formData">
			    </div>
			    <div class="layui-input-block">
			     <div  style="color:red;">下拉选项使用【英文逗号】连接</div>
			    </div>		    
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>顺序</label>
			    <div class="layui-input-block">
			      <input type="text" name="sort"   lay-verify="number"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="saveFieldBtn">提交</button>
			    </div>
		  </div>
		</form>
	</div>
	
	<!-- 公用字段修改 -->
	<form id="updateFieldForm" class="layui-form layui-hide" style="margin:30px;">
		<input name="id" type="hidden" class="formData">
			<div class="layui-form-item">
			    <label class="layui-form-label">类型</label>
			    <div class="layui-input-block">
			      <input type="text" id="formName1" readonly="readonly"   class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>字段</label>
			    <div class="layui-input-block">
			      <input type="text" name="title"   lay-verify="required"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>填写方式</label>
			    <div class="layui-input-block">
			      <input type="text" id="fieldType" readonly="readonly"   class="layui-input">
			    </div>
			</div>
			<div id="fieldUnit1" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>单位</label>
			    <div class="layui-input-block">
			      <input type="text" name="fieldUnit"   class="layui-input formData">
			    </div>
			</div>
			<div id="fieldSelect1" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>下拉选项</label>
			    <div class="layui-input-block">
			      <input type="text" name="value"    class="layui-input formData">
			    </div>
			    <div class="layui-input-block">
			     <div  style="color:red;">下拉选项使用【英文逗号】连接</div>
			    </div>		    
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>顺序</label>
			    <div class="layui-input-block">
			      <input type="text" name="sort"   lay-verify="number"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="updateFieldBtn">提交</button>
			    </div>
		  </div>
		</form>
		
		<!-- 自定义字段修改 -->
		<form id="updateFieldForm1" class="layui-form layui-hide" style="margin:30px;">
		<input name="id" type="hidden" class="formData">
			<div class="layui-form-item">
			    <label class="layui-form-label">类型</label>
			    <div class="layui-input-block">
			      <input type="text" id="formName2" readonly="readonly"   class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>字段</label>
			    <div class="layui-input-block">
			      <input type="text" name="title"   lay-verify="required"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">必填</label>
			    <div class="layui-input-block">
			      <select name="fieldRequired"  >
			        <option value="">非必填</option>
			        <option value="required">必填</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>填写方式</label>
			    <div class="layui-input-block">
			      <select name="fieldType" lay-filter="fieldType1" lay-verify="required" class="formData">
			        <option value="1">文本</option>
			        <option value="2">数字</option>
			        <option value="3">自定义下拉</option>
			      </select>
			    </div>
			</div>
			<div id="fieldUnit2" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>单位</label>
			    <div class="layui-input-block">
			      <input type="text" name="fieldUnit"   class="layui-input formData">
			    </div>
			</div>
			<div id="fieldSelect2" class="layui-form-item layui-hide">
			    <label class="layui-form-label"><span style="color:red;">*</span>下拉选项</label>
			    <div class="layui-input-block">
			      <input type="text" name="value"    class="layui-input formData">
			    </div>
			    <div class="layui-input-block">
			     <div  style="color:red;">下拉选项使用【英文逗号】连接</div>
			    </div>		    
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label"><span style="color:red;">*</span>顺序</label>
			    <div class="layui-input-block">
			      <input type="text" name="sort"   lay-verify="number"  class="layui-input formData">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="updateFieldBtn">提交</button>
			    </div>
		  </div>
		</form>

  <%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 

<script>
var table ;
var layer;
var layerId;
var form;
var areaRowIndex=0;
layui.use(['table','layer','form'], function(){
  table = layui.table;
  layer=layui.layer;
  form=layui.form;
  
  
  form.verify({
		regionName:function(value, item){
			if(value.length>20){
				return "片区名称长度不能大于20";
			}
		}
	});
  
  //第一个实例
  table.render({
	  id:'dynamicform',
    elem: '#dynamicform'
    ,height: 'full-200'
    ,url: '/manage/dynamicConfigure/getDynamicFormList' //数据接口
    ,where:{cityCode:$("#codeHid").val(),formType:1}
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'tableChinaName', title: '类型', width:200
    	   ,templet:function(d){
    		   return '<span  lay-event="fieldDetail" '+
 			  'style="color:#01AAED;cursor:pointer;">'+d.tableChinaName+'</span>'; 
    }}
      ,{field: 'code', title: '操作', width: 100
    	  ,templet:function(d){
    		  var menu='<span  lay-event="addField" style="color:#01AAED;cursor:pointer;">新增字段</span>';
			  return menu;
    	}}
    ]]
    ,done: function(res, curr, count){
    	if(count>0){
    		tableTitle=res.data[0].tableChinaName;
    		showFieldTable(res.data[0].id);
    		
		}else{
			tableTitle="";
			showFieldTable(0);
		}	    	
  }
  });
 
//监听工具条
  table.on('tool(dynamicform)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'fieldDetail'){
    	tableTitle=data.tableChinaName;
    	showFieldTable(data.id);
    } else if(layEvent === 'addField'){
    	showAddField(data.tableChinaName,data.tableDbName,data.customTableName,data.id,data.tableType);
    } 
  });

	$("#searchFormBtn").on("click",function(){
		table.reload('dynamicform',{where:{cityCode:$("#codeHid").val()}});
	});
}); 
 
var areaList=JSON.parse('${AreaListJson}');
var a= new AreaObject(areaList,function(code,name){
	$("#queryArea").val(name);
	$("#codeHid").val(code);
},2,$("#queryArea"));

$("#queryArea").on("change",function(){
	if($(this).val()==""){
		$("#codeHid").val("");
	}
});

 function showFieldTable(formId){
	 table.render({
		  id:'dynamicformfield',
	    elem: '#dynamicformfield'
	    ,height: 'full-200'
	    ,url: '/manage/dynamicConfigure/getDynamicFormFieldList' //数据接口
	    ,where:{formId:formId,cityCode:$("#codeHid").val()}
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'title', title: '字段', width:200}
	      ,{field: 'fieldRequired', title: '必填', width: 100
	    	  ,templet:function(d){
	    		  var menu='';
	    		  if(d.fieldRequired=='required'){
	    			  menu='必填';
	    		  }	    		  
				  return menu;
	    	}}
	     ,{field: 'fieldType', title: '填写方式', width:100
	    	 ,templet:function(d){
	    		 
	    		  var result='自定义';
	    		  if(d.fieldType=='1'){
	    			  result='文本';
	    		  }
	    		  if(d.fieldType=='2'){
	    			  result='数字';
	    		  }
	    		  if(d.fieldType=='3'){
	    			  result='自定义下拉';
	    		  }
	    		  if(d.fieldType=='4'){
	    			  result='sql下拉';
	    		  }
	    		  if(d.fieldType=='5'){
	    			  result='公用下拉';
	    		  }
	    		  if(d.fieldType=='6'){
	    			  result='复选';
	    		  }
	    		  if(d.fieldType=='7'){
	    			  result='日期';
	    		  }
				  return result;
	    }}
	     ,{field: 'fieldUnit', title: '单位', width:100}
	     ,{field: 'sort', title: '顺序', width:100}
	      ,{field: 'id', title: '操作', width: 100
	    	  ,templet:function(d){
	    		  var menu='<span  lay-event="updateField" style="color:#01AAED;cursor:pointer;">编辑</span>&nbsp;&nbsp;';
	    		  menu+='<span  lay-event="delField" style="color:#01AAED;cursor:pointer;">删除</span>';
				  return menu;
	    	}}
	    ]]
	  });
	//监听工具条
	  table.on('tool(dynamicformfield)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'updateField'){
	    	showUpdateField(data);
	    } else if(layEvent === 'delField'){
	    	layer.confirm('您确定要删除字段【'+data.title+'】吗?', {icon: 3, title:'确认'}, function(index){
	    		 $.ajax({
	    	         type: "POST",//方法类型
	    	         dataType: "json",//预期服务器返回的数据类型
	    	         //contentType: 'application/json',
	    	         data: {id:data.id},
	    	         url: "<%=request.getContextPath()%>/manage/dynamicConfigure/delFormField" ,//url	                
	    	         //data:data,
	    	         success: function (result) {
	    	         	if(result.success){
	    	         		table.reload('dynamicformfield');
	    	         		layer.close(index);
	    	         		layer.msg("删除成功");
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
 }
 function showAddField(cName,tName,ctName,formId,tableType){
	 $.ajax({
         type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         //contentType: 'application/json',
         data: {formId:formId},
         url: "<%=request.getContextPath()%>/manage/dynamicConfigure/queryCityDynamicFormFieldSelect" ,//url	                
         //data:data,
         success: function (result) {
         	if(result.success){
         		setAddFieldForm(cName,tName,ctName,formId,result.data,tableType);
         	}else{
         		layer.alert(result.msg);
         	}
         },
         error : function() {	            	
             layer.alert("异常！");
         }
     });
 }
 function setAddFieldForm(cName,tName,ctName,formId,selectData,tableType){
	 
	 layerId=layer.open({
		  title:'新增补充信息',
		  type:1,
		  area:'600px',
		  content: $('#addFieldForm')
		}); 
	 $("#addFieldForm input.formData").val("");
	 $("#addFieldForm select[name=fieldRequired]").val("");
	 $("#addFieldForm input[name=customTableName]").val(ctName);
	 $("#addFieldForm input[name=tableType]").val(tableType);
	 $("#formName").val(cName);
	 $("#addFieldForm input[name=tableName]").val(tName);
	 $("#addFieldForm input[name=formId]").val(formId);
	 $("#addFieldForm input[name=cityCode]").val($("#codeHid").val());
	 $("#addFieldForm select[name=field]").children().remove();
	 $("#addFieldForm select[name=field]").append('<option value=""></option>');
	 if(selectData){
		 $.each(selectData,function(i,item){
			 $("#addFieldForm select[name=field]").append('<option value="'+item.field+'">'+item.title+'</option>');
		 });
	 }
	 form.render();
	 $('#addFieldForm').removeClass("layui-hide");
	 
	 form.on('select(field)', function(data){
		 if(data.value==""){
			 $("#addFieldForm select[name=fieldType]").val('1');
			 setSelectInfo(1);
		 }else{
			 $.each(selectData,function(i,item){
				 if(data.value==item.field){
					 if(item.fieldType==1){
						 $("#addFieldForm select[name=fieldType]").val(item.fieldType);
						 setSelectInfo(item.fieldType);
					 }
					 if(item.fieldType==2){
						 $("#addFieldForm select[name=fieldType]").val(item.fieldType);
						 setSelectInfo(item.fieldType);
						 $("#addFieldForm input[name=fieldUnit]").val(item.fieldUnit);
					 }
					 if(item.fieldType==3){
						 $("#addFieldForm select[name=fieldType]").val(item.fieldType);
						 setSelectInfo(item.fieldType);
						 $("#addFieldForm input[name=value]").val(item.value);
					 }
					 return;
				 }
			 });
		 }
		 form.render();
		});
	 form.on('select(fieldType)', function(data){
		 setSelectInfo(data.value);
		});
	 
	 form.on('submit(saveFieldBtn)', function(data){
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data:{dataJson:JSON.stringify(data.field)},
	            url: "<%=request.getContextPath()%>/manage/dynamicConfigure/saveFormField" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		layer.close(layerId);
						table.reload("dynamicformfield");
	            		layer.msg("补充信息新增成功");
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
 
 function setSelectInfo(value){
	  if(value==1){
		  $("#fieldUnit").addClass("layui-hide");
		  $("#fieldSelect").addClass("layui-hide");
		  $("#addFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
		  $("#addFieldForm input[name=value]").removeAttr("lay-verify");
	  }
	  if(value==2){
		  $("#fieldUnit").removeClass("layui-hide");
		  $("#fieldSelect").addClass("layui-hide");
		  $("#addFieldForm input[name=value]").removeAttr("lay-verify");
		  $("#addFieldForm input[name=fieldUnit]").attr("lay-verify","required")
	  }
	  if(value==3){
		  $("#fieldUnit").addClass("layui-hide");
		  $("#fieldSelect").removeClass("layui-hide");
		  $("#addFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
		  $("#addFieldForm input[name=value]").attr("lay-verify","required")
	  }
}
 var tableTitle;
 function showUpdateField(data){
	 	 
	if(data.isDynamic!=3){
		layerId=layer.open({
			  title:'编辑字段信息',
			  type:1,
			  area:'600px',
			  content: $('#updateFieldForm')
			}); 
		 $("#updateFieldForm input.formData").val("");
		 $("#updateFieldForm input.formData").each(function(){
			 if(data[$(this).attr("name")]){
				 $(this).val(data[$(this).attr("name")]);
			 }
		 });
		 $("#formName1").val(tableTitle);

		//根据字段类型显示
		switch(data.fieldType){
			 case '1':
			   	$("#fieldType").val("文本");
			   	$("#fieldUnit1").addClass("layui-hide");
				$("#fieldSelect1").addClass("layui-hide");
				 $("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
				 $("#updateFieldForm input[name=value]").removeAttr("lay-verify");
			   break;
			 case '2':
				 $("#fieldType").val("数字");
				 $("#fieldUnit1").removeClass("layui-hide");
				$("#fieldSelect1").addClass("layui-hide");
				$("#updateFieldForm input[name=fieldUnit]").attr("lay-verify","required")
				$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
			   break;
			 case '3':
				 $("#fieldType").val("自定义下拉");
				 $("#fieldUnit1").addClass("layui-hide");
				$("#fieldSelect1").removeClass("layui-hide");
				$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
				$("#updateFieldForm input[name=value]").attr("lay-verify","required");
			   break;
			 case '4':
				   	$("#fieldType").val("sql下拉");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				   break; 
			 case '5':
				   	$("#fieldType").val("公用下拉");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				  break; 
			 case '6':
				   	$("#fieldType").val("单选");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				  break; 
			 case '7':
				   	$("#fieldType").val("复选");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				  break; 
			 case '8':
				   	$("#fieldType").val("组合框");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				  break; 
			 case '9':
				   	$("#fieldType").val("自定义");
				   	$("#fieldUnit1").addClass("layui-hide");
					$("#fieldSelect1").addClass("layui-hide");
					$("#updateFieldForm input[name=fieldUnit]").removeAttr("lay-verify");
					$("#updateFieldForm input[name=value]").removeAttr("lay-verify");
				  break; 
		}
		$('#updateFieldForm').removeClass("layui-hide");
	}else{
		layerId=layer.open({
			  title:'编辑字段信息',
			  type:1,
			  area:'600px',
			  content: $('#updateFieldForm1')
			}); 
		 $("#updateFieldForm1 input.formData").val("");
		 $("#updateFieldForm1 input.formData").each(function(){
			 if(data[$(this).attr("name")]){
				 $(this).val(data[$(this).attr("name")]);
			 }
		 });
		 if(data.fieldRequired){
			 $("#updateFieldForm1 select[name=fieldRequired]").val(data.fieldRequired);
			 form.render();
		 }
		 
		 $("#formName2").val(tableTitle);
		 
		form.on('select(fieldType1)', function(data){
			showUpdateItem(data.value);
		});
		$("#updateFieldForm1 select[name=fieldType]").val(data.fieldType);
		showUpdateItem(data.fieldType);
		form.render();
		$('#updateFieldForm1').removeClass("layui-hide");
	} 
	
	 
	 
	 form.on('submit(updateFieldBtn)', function(data){
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data:{dataJson:JSON.stringify(data.field)},
	            url: "<%=request.getContextPath()%>/manage/dynamicConfigure/saveFormField" ,	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		layer.close(layerId);
						table.reload("dynamicformfield");
	            		layer.msg("字段信息更新成功");
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
 
 function showUpdateItem(value){
	 if(value=='1'){
		  $("#fieldUnit2").addClass("layui-hide");
		  $("#fieldSelect2").addClass("layui-hide");
		  $("#updateFieldForm1 input[name=fieldUnit]").removeAttr("lay-verify");
		  $("#updateFieldForm1 input[name=value]").removeAttr("lay-verify");
		  $("#updateFieldForm1 input[name=fieldUnit]").val("");
		  $("#updateFieldForm1 input[name=value]").val("");
	  }
	  if(value=='2'){
		  $("#fieldUnit2").removeClass("layui-hide");
		  $("#fieldSelect2").addClass("layui-hide");
		  $("#updateFieldForm1 input[name=value]").removeAttr("lay-verify");
		  $("#updateFieldForm1 input[name=fieldUnit]").attr("lay-verify","required")
		  $("#updateFieldForm1 input[name=value]").val("");
	  }
	  if(value=='3'){
		  $("#fieldUnit2").addClass("layui-hide");
		  $("#fieldSelect2").removeClass("layui-hide");
		  $("#updateFieldForm1 input[name=fieldUnit]").removeAttr("lay-verify");
		  $("#updateFieldForm1 input[name=value]").attr("lay-verify","required")
		  $("#updateFieldForm1 input[name=fieldUnit]").val("");
	  }
 }
</script>

</body>
</html>