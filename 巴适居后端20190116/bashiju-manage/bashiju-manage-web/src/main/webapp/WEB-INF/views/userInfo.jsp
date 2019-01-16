<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/Mo.css">
	
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/manage-platform/js/Mo.js"></script>
	</head>
	<script type="text/javascript">
	
	/*  var tableinfo = $.parseJSON('${tableInfo}');
	for(var i=0;i<tableinfo.length;i++){
		var html = tableinfo[i].formHtml;
		$(".form-box1").html(html);
	}   */
	
	function saveFormValue(){
		var formbox = $(".mo-form .form-box");
		var data = [];
		for(var n=0;n<formbox.length;n++){
			
			var tableid = $(formbox[n]).find("form").find(".saveid").val();
			
			var divs = $(formbox[n]).find("form").find(".move");
			var tableInfo = {
					 
					
			 };
			tableInfo.tableid = tableid;
			if($(formbox[n]).find("form").attr("tabletype")=="father"){
				 tableInfo.tabletype="father";
			}
			
			for(var i=0;i<divs.length;i++){
				
				if($(divs[i]).find(".value-box").is("input[type='text']") || $(divs[i]).find(".value-box").is("textarea") || $(divs[i]).find(".value-box").is("select")){
					var value = $(divs[i]).find(".value-box").val();
					var name = $(divs[i]).find(".value-box").attr("name");
					tableInfo[name] = value;
				}
				
				if($(divs[i]).find(".value-box").is("input[type='radio']")){
					var radios = $(divs[i]).find(".value-box");
					for(var j=0;j<radios.length;j++){
						if($(radios[j]).is(":checked")){
							var value = $(radios[j]).val();
							var name = $(radios[j]).attr("name");
							tableInfo[name] = value;
						}
					}
				}
				
				if($(divs[i]).find(".value-box").is("input[type='checkbox']")){
					var checkboxs = $(divs[i]).find(".value-box");
					var checkboxvalue = "";
					for(var j=0;j<checkboxs.length;j++){
						if($(checkboxs[j]).is(":checked")){
							
							var value = $(checkboxs[j]).val();
							if(checkboxvalue==""){
								checkboxvalue = value;
							}else{
								checkboxvalue = checkboxvalue +","+value;
							}
						}
					}
					var name = checkboxs.attr("name");
					tableInfo[name] = checkboxvalue;
				}
				
			}
			
			data.push(tableInfo);
		 
		}
		
		
		 $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: 'application/json',
            data: JSON.stringify(data),
            url: "<%=request.getContextPath()%>/manage/saveRegisterFormValue" ,//url	                
            //data:data,
            success: function (result) {
            	if(result.success){
            		alert("保存成功！");
            	}else{
            		alert(result.errorMsg);
            	}
            },
            error : function() {
            	
                alert("异常！");
            },
            beforeSend : function() {
            	//表单验证
                //return $("#form").valid();
            }
        });
	 }
	
	
	function addField(){
		//required unique candel fieldtype="1 2" englishname="phone">
		
			var tableid = $(".form-item").find(".saveid").val();
			
			var divs = $(".form-item").find(".newfield");
			//var div2s = $(".move").not(".newfield");
			
			var fieldattributes = [];
			
			for(var i=0;i<divs.length;i++){
				
				var attribute = {};
				
				attribute.tableId = tableid
				var fieldname = $(divs[i]).find("label").text();
				attribute.fieldChinaName=fieldname;
				
				var englishname = $(divs[i]).attr("englishname");
				attribute.fileldDbName=englishname;
			
				var required = $(divs[i]).attr("required");
				if(required!=undefined){
					attribute.required="1";
				}
				
				var unique = $(divs[i]).attr("unique");
				if(unique!=undefined){
					attribute.dataUnique="1";
				}
				
				var candel = $(divs[i]).attr("candel");
				if(candel!=undefined){
					attribute.isCanDel="1";
				}
				
				var fieldtype = $(divs[i]).attr("fieldtype");
				attribute.fieldDbType=fieldtype;
				
				var defaultvalue = $(divs[i]).attr("defaultvalue");
				if(defaultvalue!=undefined){
					attribute.defaultvalue=defaultvalue;
				}
				
				
				fieldattributes.push(attribute);
				
			}
			
			var formHtml = $(".form-box").html();
			//console.log(fieldattributes);
			
			var data = {
					tableid:tableid,	
					fieldattributes:fieldattributes,
					formHtml:formHtml
					
			}
			
			
		
		
		//console.log(data);
		
		$.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: 'application/json',
            data: JSON.stringify(data),
            url: "<%=request.getContextPath()%>/manage/addField" ,//url	                
            //data:data,
            success: function (result) {
            	console.log(result);
            	console.log(result.msg);
            	console.log(result.success);
            	if(result.success){
            		alert("添加成功！");
            		$(".newfield").removeClass("newfield");
            	}else{
            		
            	}
            	
            },
            error : function() {
                alert("异常！");
            },
            beforeSend : function() {
            	//表单验证
                //return $("#form").valid();
            }
        }); 
	}
		
	
		
$(function(){
		var formwidth = $(".form-box").outerWidth(); 
		var formheight = $(".form-box").outerHeight();
		
		$( ".move" ).draggable();
		$( ".move" ).on( "dragstop", function( event, ui ) {
			
			var top = ui.position.top;
			var left = ui.position.left;
			if(left<-150 || left>formwidth){
				if($(this).attr("basefield")=="1"){
					alert("不能删除！");
				}else{
					$(this).remove();
				}
			}
			if(top<-50 || top>formheight){
				
				if($(this).attr("basefield")=="1"){
					alert("不能删除！");
				}else{
					$(this).remove();
				}
			}
		} );
		
	})
	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-row">
					<div class="col-md-2 mo-form-left">
						<div class="mo-form-left-head col-md-12">
							通用表单元素
						</div>
						<div style="font-weight:900;">预设项</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="1">
							<span class="glyphicon glyphicon-pencil"></span> 面积
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="2">
							<span class="glyphicon glyphicon-pencil"></span> 价格
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="3">
							<span class="glyphicon glyphicon-pencil"></span> 坐落
						</div>
						<div style="font-weight:900;">自定义项</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="4">
							<span class="glyphicon glyphicon-pencil"></span> 输入框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="5">
							<span class="glyphicon glyphicon-align-center"></span> 多文本
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="6">
							<span class="glyphicon glyphicon-check"></span> 多选框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="7">
							<span class="glyphicon glyphicon-record"></span> 单选框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="8">
							<span class="glyphicon glyphicon-collapse-down"></span> 下拉框
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="9">
							<span class="glyphicon glyphicon-list-alt"></span> 日期
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="10">
							<span class="glyphicon glyphicon-time"></span> 时间
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="11">
							<span class="glyphicon glyphicon-file"></span> 文件上传
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-form-left-item drag" flag="12">
							<span class="glyphicon glyphicon-edit"></span> 分隔符
						</div>
						<div class="col-md-6 mo-form-left-border mo-border-top mo-border-left mo-form-left-item drag" flag="13">
							
						</div>
					</div>
					<div class="col-md-2 mo-form-right">
						<div class="mo-form-right-head col-md-12">
							字段设置
						</div>
						<form class="layui-form">
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label >名称</label>
							    <input type="text" class="form-control fieldname_input"  placeholder="请输入名称">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label >英文名称</label>
							    <input type="text" class="form-control fieldenglishname_input"  placeholder="请输入名称">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<div class="form-group mo-form-right-group">
							    <label for="description">描述</label>
							    <textarea class="form-control description" rows="3"></textarea>
							</div>
						</div>
						
						<div class="layui-form-item">
						    <label class="layui-form-label">仅能读</label>
						    <div class="layui-input-block readonly">
						      <input type="radio" name="readonly" value="0" title="否" checked>
						      <input type="radio" name="readonly" value="1" title="是" >
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">必填项</label>
						    <div class="layui-input-block required">
						      <input type="radio" name="required" value="0" title="否" checked>
						      <input type="radio" name="required" value="1" title="是" >
						    </div>
						  </div>
						  <div class="layui-form-item">
						    <label class="layui-form-label">值唯一</label>
						    <div class="layui-input-block unique">
						      <input type="radio" name="unique" value="0" title="否" checked>
						      <input type="radio" name="unique" value="1" title="是" >
						    </div>
						  </div>
						   <div class="layui-form-item">
						    <label class="layui-form-label">可删除</label>
						    <div class="layui-input-block candel">
						      <input type="radio" name="candel" value="0" title="否" checked>
						      <input type="radio" name="candel" value="1" title="是" >
						    </div>
						  </div>
						  
						  <div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<label for="name">数据类型</label>
							<select class="fieldtype" lay-filter="fieldtype">
								<option></option>
								<option value="1">手机号</option>
								<option value="1">身份证</option>
								<option value="1">邮箱</option>
								<option value="3">整数</option>
								<option value="4">小数</option>
								<option value="1">简单文字</option>
								<option value="2">多文字</option>
								<option value="5">日期</option>
								<option value="6">时间</option>
								
							</select>
						</div>
						  
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom optionuse">
							<div class="form-group mo-form-right-group">
							    <label for="name">备选值（用于单选、复选、下拉等）</label>
							    <input type="text" class="form-control option-group"  placeholder="请输入">
							</div>
						</div>
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom optionuse">
							<div class="form-group mo-form-right-group">
							    <label for="name">默认选中值</label>
							    <input type="text" class="form-control default-option"  placeholder="请输入">
							</div>
						</div>
						
						<div class="col-md-12 mo-form-right-border mo-border-top mo-border-bottom">
							<label for="name">动态数据源</label>
							<select class="datasource" lay-filter="datasource">
								<option></option>
								<option value="1">部门</option>
								<option value="2">职位</option>
								<option value="3">其他</option>
							</select>
						</div>
						</form>
					</div>
					<div class="layui-col-md10 mo-form layui-col-md-offset1">
						<div class="form-box form-box1" aaa="1">
						<form class="layui-form form-item" tabletype="father"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
						  <input type="hidden" class="saveid" value="1">
						  <div class="layui-form-item move resizable" flag="1" basefield="1">
						    <label class="layui-form-label">用户名</label>
						    <div  class="layui-input-block">
						      <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input value-box">
						    </div>
						  </div>
						   <div class="layui-form-item move resizable" basefield="1">
						    <label class="layui-form-label">密码</label>
						    <div  class="layui-input-block">
						      <input type="text" name="password" placeholder="请输入" autocomplete="off" class="layui-input value-box">
						    </div>
						  </div>
						  <div class="layui-form-item move resizable" basefield="1">
						    <label class="layui-form-label">姓名</label>
						    <div class="layui-input-block">
						      <input type="text" name="fullname" placeholder="请输入" autocomplete="off" class="layui-input value-box">
						    </div>
						  </div>
						  <!-- <div class="layui-form-item move resizable">
						    <label class="layui-form-label">下拉选择框</label>
						    <div class="layui-input-block ">
						      <select name="interest" lay-filter="aihao">
						        <option value="0">写作</option>
						        <option value="1">阅读</option>
						      </select>
						    </div>
						  </div>
						  
						  <div class="layui-form-item move resizable">
						    <label class="layui-form-label">开关</label>
						    <div class="layui-input-block ">
						      <input type="checkbox" lay-skin="switch">
						    </div>
						  </div>
						  -->
						  <div class="layui-form-item move resizable">
						    <label class="layui-form-label">爱好</label>
						    <div class="layui-input-block ">
						      <input type="checkbox" name="interest" title="写作" value="0" class="value-box">
						      <input type="checkbox" name="interest" title="阅读" value="1" class="value-box">
						    </div>
						  </div>
						 
						  <div class="layui-form-item move resizable">
						    <label class="layui-form-label">性别</label>
						    <div class="layui-input-block ">
						      <input type="radio" name="sex" value="0" title="男" class="value-box">
						      <input type="radio" name="sex" value="1" title="女" checked class="value-box">
						    </div>
						  </div>
						  
						  <div class="layui-form-item layui-form-text move resizable">
						    <label class="layui-form-label">请填写描述</label>
						    <div class="layui-input-block ">
						      <textarea placeholder="请输入内容" name="description" class="layui-textarea value-box"></textarea>
						    </div>
						  </div>
						  
						  <!-- 模拟新增字段 -->
						  <!--
						  <div class="layui-form-item newfield" required unique candel fieldtype="1 2" englishname="phone" company="2"><label class="layui-form-label ">手机</label><div class="layui-input-block"><input type="text"  placeholder="请输入" autocomplete="off" class="layui-input"></div></div>
						  <div class="layui-form-item newfield" fieldtype="1 6" englishname="married" company="1"><label class="layui-form-label">婚否</label><div class="layui-input-block"><input type="radio" name="married" value="0" title="否"><input type="radio" name="married" value="1" title="是" checked></div></div>
						  -->
						  <div class="appendField"></div>
						  <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
						</form>	
						</div>
						<!-- <div class="form-box">
						<form class="layui-form form-item1">
							第二页数据
							<input type="hidden" class="saveid" value="3">
							<div class="layui-form-item move resizable" flag="1">
						    <label class="layui-form-label">详细信息1</label>
						    <div  class="layui-input-block">
						      <input type="text" name="detail1" placeholder="请输入" autocomplete="off" class="layui-input">
						    </div>
						  </div>
						   <div class="layui-form-item move resizable">
						    <label class="layui-form-label">详细信息2</label>
						    <div  class="layui-input-block">
						      <input type="text" name="detail2" placeholder="请输入" autocomplete="off" class="layui-input">
						    </div>
						  </div>
						  <div class="layui-form-item move resizable">
						    <label class="layui-form-label">详细信息3</label>
						    <div class="layui-input-block">
						      <input type="text" name="detail3" placeholder="请输入" autocomplete="off" class="layui-input">
						    </div>
						  </div>
						  <div class="appendField"></div>
						</form>
						</div>
						<div class="form-box">
						<form class="layui-form form-item2">
							<input type="hidden" class="saveid" value="4">
							第三页数据
							<div class="appendField"></div>
						</form>
						</div> -->
						<div>下一页数据</div>
						<div>再下一页数据</div>
						 <div class="layui-form-item save-btn move resizable">
						    <div class="layui-input-block">
						      <button type="button" class="layui-btn"  lay-filter="*" onclick="addField();">保存新增字段</button>
						      <button type="button" class="layui-btn"  lay-filter="*" onclick="saveFormValue();">保存表单值</button>
						      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
						    </div>
						  </div>
						
						
					</div>
				</div>
				
				
			</div>
		<script>
		
		</script>
				
	</body>

</html>