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
	var usetype = "showform";
	
	
	
		
	$(function(){
		
		
				
				
				
				
				
			})
			
			
			
			
	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-row">
					
					<div class="layui-col-md12">
						<div class="mo-form">
							<div class="form-box form-box1">
								<form class="layui-form form-item form-item1" tabletype="father" name="houseinfo"> 
								 </form>
							</div>
							
							<div class="form-box form-box2">
								<form class="layui-form form-item form-item2" name="houseowner">
								</form>
							</div>
							<div class="form-box form-box3">
								<form class="layui-form form-item form-item3" name="houseother">
								</form>
							</div>
							
						</div>
						
					</div>
				</div>
				
				
			</div>
			
		<script>
		layui.use(['element','form'], function(){
			  var element = layui.element;
			  var form = layui.form;
			  var sourceId = '${sourceid}';
			  //alert(sourceId);
			  $.post("<%=request.getContextPath()%>/manage/getForm",{sourceId:sourceId,formUseName:"houseinfo",company:"11"},function(data){
					console.log(data);
					for(var i=0;i<data.length;i++){
						var formbox = $(".form-box");
						
						for(var k=0;k<formbox.length;k++){
							var formname = $(formbox[k]).find("form").attr("name");
							if(data[i].formUseName==formname){
								var html = data[i].formHtml;
								$(formbox[k]).html(html);
								form.render();
								//赋表ID
								if(data[i].tableId){
									$(formbox[k]).find("form").find(".saveid").val(data[i].tableId);
								}
								
								//给字段赋值
								if(data[i].fieldvalue && !$.isEmptyObject(data[i].fieldvalue)){
									var formname = data[i].formUseName;
									
									var valuebox = $("form[name='"+formname+"']").find(".value-box");
									for(var j=0;j<valuebox.length;j++){
										var name = $(valuebox[j]).attr("name");
										if(data[i].fieldvalue[name]){
											var value = data[i].fieldvalue[name];
											if($(valuebox[j]).is("input[type='text']") || $(valuebox[j]).is("textarea") ){
												
												$(valuebox[j]).val(value);
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
								
								//给字段赋ID
								if(data[i].fieldRecord && data[i].fieldRecord.length>0){
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
									
								}
								
							}
						}
						
					}
				})
		})
		</script>
				
	</body>

</html>