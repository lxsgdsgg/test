<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/jquery-ui.min.css">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css">
	
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script>
	
	</head>
	<script type="text/javascript">
	var usetype = "useform";
	
	function saveFormValue(){
		var formbox = $(".mo-form .form-box");
		var data = [];
		for(var n=0;n<formbox.length;n++){
			
			var tableid = $(formbox[n]).find("form").find(".saveid").val();
			var sourceid = $(formbox[n]).find("form").find(".sourceid").val();
			var divs = $(formbox[n]).find("form").find(".move");
			
			if(tableid!=undefined){
				var tableInfo = {
						 
						
				 };
				
				tableInfo.tableid = tableid;
				
				if(sourceid!=undefined){
					tableInfo.sourceId = sourceid;
				}
				
				tableInfo.company="11";
				
				/* if($(formbox[n]).find("form").attr("tabletype")=="father"){
					 tableInfo.tabletype="father";
				} */
				
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
			
		 
		}
		
		
		$.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: 'application/json',
            data: JSON.stringify(data),
            url: "<%=request.getContextPath()%>/manage/saveHouseFormValue" ,//url	                
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
	
	
	
	$(function(){
		
				
				
				
				
				
			})	
	

	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-row">
					
					<div class="layui-col-md12">
							
							
						<div class="layui-tab-item layui-show mo-form">
							<div class="form-title1">房源信息表单</div>
							<div class="form-box form-box1">
								<form class="layui-form form-item form-item1" tabletype="father" name="houseinfo"> 
								</form>
							</div>
							<div class="form-title2">房东信息表单</div>
							<div class="form-box form-box2">
								<form class="layui-form form-item form-item2" name="houseowner">
								</form>
							</div>
							<div class="form-title3">房源其他信息表单</div>
							<div class="form-box form-box3">
								<form class="layui-form form-item form-item3" name="houseother">
								</form>
							</div>
							<div class="layui-form-item save-btn move resizable">
							    <div class="layui-input-block">
							      <button type="button" class="layui-btn " lay-submit  lay-filter="*" onclick="saveFormValue();">保存</button>
							    </div>
							 </div>
						</div>
								
					</div>
				</div>
				
				
			</div>
			
		<script>
		
		layui.use(['element','form','table'], function(){
			  var element = layui.element;
			  var form = layui.form;
			  var table = layui.table;
			  var sourceId = '${sourceid}';
			  $.post("<%=request.getContextPath()%>/manage/getForm",{sourceId:sourceId,formUseName:"houseinfo",company:"11"},function(data){
					console.log(data);
					for(var i=0;i<data.length;i++){
						var formbox = $(".form-box");
						
						for(var k=0;k<formbox.length;k++){
							var formname = $(formbox[k]).find("form").attr("name");
							if(data[i].formUseName==formname){
								var html = data[i].formHtml;
								if(data[i].fieldvalue && data[i].fieldvalue.length>0){
									
									var houseownernum = 0;
									var fullname = "";
									for(var x=0;x<data[i].fieldvalue.length;x++){
										if(formname=="houseowner"){//处理房东信息
											var curfullname = data[i].fieldvalue[x].fullName;
											var curphone = data[i].fieldvalue[x].phone;
											if(fullname!=curfullname){
												$(formbox[k]).append(html);
												var sourceid = '<input type="hidden" class="sourceid"  value="'+sourceId+'"/>';
												$(formbox[k]).find("form").append(sourceid);
												form.render();
												var formname = data[i].formUseName;
												var valuebox = $("form[name='"+formname+"']").find(".value-box");
												var curdata = data[i].fieldvalue[x];
												//赋值
												giveValue(valuebox,curdata);
												houseownernum++;
												fullname = curfullname;
											}else{
												var name = $("form[name='"+formname+"']").find(".value-box[name='fullName']").val();
												if(name==curfullname){
													var phone = $("form[name='"+formname+"']").find(".value-box[name='fullName']").parent().parent().parent().next().find(".value-box").val();
													curphone = curphone + ',' + phone;
													$("form[name='"+formname+"']").find(".value-box[name='fullName']").parent().parent().parent().next().find(".value-box").val(curphone);
													
												}
											}
											
											
										}else{
											$(formbox[k]).append(html);
											var sourceid = '<input type="hidden" class="sourceid"  value="'+sourceId+'"/>';
											$(formbox[k]).find("form").append(sourceid);
											form.render();
											
											var formname = data[i].formUseName;
											var valuebox = $("form[name='"+formname+"']").find(".value-box");
											var curdata = data[i].fieldvalue[x];
											//赋值
											giveValue(valuebox,curdata);
										}
										
										
									}
									
								}
								
								
								
								
								//给字段赋ID
								/* if(data[i].fieldRecord && data[i].fieldRecord.length>0){
									for(var m = 0;m<data[i].fieldRecord.length;m++){
										var name = data[i].fieldRecord[m].fileldDbName;
										//alert(name); 
										var divs = $(formbox[k]).find("form").find(".move");
										for(var j=0;j<divs.length;j++){
											var enName = $(divs[j]).attr("englishname");
											if(name==enName){
												$(divs[j]).attr("fieldId",data[i].fieldRecord[m].id);
												
											}
										}
									}
									
								} */
								
							}
						}
						
					}
				})
				
			 
		})
		
			//表单验证
		    function blurV () {
		        var DANGER = 'layui-form-danger',
		                stop = null,
		                verify = form.config.verify,
		                device = layui.device()
		        var othis = $(this), ver = othis.attr('lay-verify'), tips = '';
		        var value = othis.val(), isFn = typeof verify[ver] === 'function';
		        othis.removeClass(DANGER);
		        ver = ver.split('|');
		        $.each(ver, function(index, thisVer) {
		        if(verify[thisVer] && (isFn ? tips = verify[thisVer](value, this) : !verify[thisVer][0].test(value)) ){
		            layer.msg(tips || verify[thisVer][1]);
		            /* //非移动设备自动定位焦点
		            if(!device.android && !device.ios){
		                this.focus();
		            } */
		            othis.addClass(DANGER);
		            return stop = true;
		        }
		        });
		    }
			 
		//赋值
		function giveValue(valuebox,curdata){

			for(var j=0;j<valuebox.length;j++){
				var name = $(valuebox[j]).attr("name");
				if(curdata[name]){
					var value = curdata[name];
					if($(valuebox[j]).is("input[type='text']") || $(valuebox[j]).is("textarea") ){
						var curvalue = $(valuebox[j]).val();
						if(curvalue==""){
							curvalue = value;
						}else{
							curvalue = curvalue + ','+value;
						}
						$(valuebox[j]).val(curvalue);
					}
					if($(valuebox[j]).is("select")){
						var options = $(valuebox[j]).find("option");
						for(var n=0;n<options.length;n++){
							var optionvalue = $(options[n]).val();
							if(optionvalue==value){
								$(options[n]).attr("selected","true");
							}else{
								$(options[n]).removeAttr("selected");
							}
						}
					}
					if($(valuebox[j]).is("input[type='radio']")){
						var radiovalue = $(valuebox[j]).val();
						//alert(value);
						if(radiovalue==value){
							$(valuebox[j]).prop("checked","true");
						}else{
							$(valuebox[j]).removeAttr("checked");
						}
						
					}
					if($(valuebox[j]).is("input[type='checkbox']")){
						var checkboxvalue = $(valuebox[j]).val();
						var values = value.split(",");
						for(var v=0;v<values.length;v++){
							if(values[v]==checkboxvalue){
								$(valuebox[j]).prop("checked","true");
							}else{
								$(valuebox[j]).removeAttr("checked");
							}
						}
					}
				}
			}
			
		}
		
		</script>
		
				
	</body>

</html>