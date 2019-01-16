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
		var path = "<%=request.getContextPath()%>";
		
		$(function(){
			//加载城市自定义字段
			$.post(path+"/manage/getCustomField",{formName:"zhuzhai"},function(d){
				for(var i=0;i<d.data.length;i++){
					var div =$('<div></div>');
					var lable = '<lable>'+d.data[i].title+'</lable>';
					div.append(lable);
					if(d.data[i].fieldType=="3"){
						var select = '<select name="'+d.data[i].field+'" class="valuebox"><option>'+d.data[i].value+'</option></select>';
						div.append(select);
					}else{
						var input = '<input name="'+d.data[i].field+'" type="text" class="valuebox"/>';
						div.append(input);
					}
					
					$(".custom").append(div);
				}
				
				//获取表单数据值
				$.post(path+"/manage/getFormValue",{formName:"zhuzhai",sourceId:"FY-201804261738"},function(d){
					
					 console.log(d.data);
					 loadFormValue("zhuzhai",d.data);
					
				}); 
			});
			
			//加载单个下拉值
			/* $.post(path+"/manage/getSelectValue/one",{param:"8"},function(d){
				console.log(d.data);
			}); */
			
			//加载表单下全部下拉值
			$.post(path+"/manage/getSelectValue/many",{param:"zhuzhai"},function(d){
				//console.log(d.data);
				
				for(var i=0;i<d.data.length;i++){
					for(var key in d.data[i]){
						var select = $("select[name='"+key+"']");
						var options = d.data[i][key];
						for(var j=0;j<options.length;j++){
							var op = '<option value="'+options[j].id+'">'+options[j].fieldValue+'</option>';
							select.find(".space_option").after(op);
						}
					}
				}
				
				
			});
			
			
			
			//获取区间数据值
			 $.post(path+"/manage/getRegionValue/many",{tag:"1",param:"zhuzhai"},function(d){
				 //console.log(d.data);
				 for(var i=0;i<d.data.length;i++){
					for(var key in d.data[i]){
						var select = $("select[name='"+key+"']");
						var options = d.data[i][key];
						for(var j=options.length-1;j>=0;j--){
							var op = '<option value="'+options[j].min+'">'+options[j].min+'——'+(options[j].max==""?"无限大":options[j].max)+'</option>';
							select.find(".space_option").after(op);
						}
					}
				};
				
				
			});
			
		})
		
		function loadFormValue(formName,data){
			var box = $("form[name='"+formName+"']").find(".valuebox");
			for(var i=0;i<box.length;i++){
				var key =  $(box[i]).attr("name");
				if($(box[i]).is("input[type='text']") || $(box[i]).is("textarea") ){
					$(".valuebox[name='"+key+"']").val(data[key]);
				}
				if($(box[i]).is("select")){
					alert(1111);
					var options = $(box[i]).find("option");
					for(var n=0;n<options.length;n++){
						var optionvalue = $(options[n]).val();
						if(optionvalue==data[key]){
							$(options[n]).attr("selected","true");
						}else{
							$(options[n]).removeAttr("selected");
						}
					}
				}
				if($(box[i]).is("input[type='radio']")){
					var radiovalue = $(box[i]).val();
					//alert(value);
					if(radiovalue==data[key]){
						$(box[i]).prop("checked","true");
					}else{
						$(box[i]).removeAttr("checked");
					}
					
				}
				if($(box[i]).is("input[type='checkbox']")){
					var checkboxvalue = $(box[i]).val();
					var values = data[key].split(",");
					for(var v=0;v<values.length;v++){
						if(values[v]==checkboxvalue){
							$(box[i]).prop("checked","true");
						}else{
							$(box[i]).removeAttr("checked");
						}
					}
				}
			}
			
			
		}
		
	</script>
	<body class="">
			<div class="layui-container">
				<div class="layui-row">
					<form name="zhuzhai">
						<div class="common" style="border:1px solid #eee;height:300px;">
							公共字段
							<div>
								<label>朝向</label>
								<select name="directionId" class="valuebox">
									<option class="space_option"></option>
								</select>
							</div>
							<div>
								<label>配套</label>
								<select name="infrastructure" class="valuebox">
									<option class="space_option"></option>
								</select>
							</div>
							<div>
								<label>面积区间</label>
								<select name="areaRegion" class="valuebox">
									<option class="space_option"></option>
								</select>
							</div>
							<div>
								<label>价格区间</label>
								<select name="priceRegion" class="valuebox">
									<option class="space_option"></option>
								</select>
							</div>
						</div>
						<div class="custom" style="border:1px solid #eee;height:300px;">
						
						</div>
					</form>
				</div>	
			</div>		
	</body>
</html>