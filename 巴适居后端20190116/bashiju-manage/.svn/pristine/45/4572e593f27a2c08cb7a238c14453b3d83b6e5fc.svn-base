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
	<script type="text/javascript" src="${request.getContextPath()}/src/manage-platform/js/Mo2.js"></script>
	</head>
	<script type="text/javascript">
	
	var path = "<%=request.getContextPath()%>";
	
	var pageBox = "housepage-box";
	
	$(function(){
		
		
	})
	 
	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-form">
					<div class="layui-form-item">
					    <label class="layui-form-label">选择页面</label>
					    <div class="layui-input-block ">
					      <select name="select-form" lay-filter="selectform">
					        <option value="houseshow">房源展示</option>
					        <option value="othershow">其他页面</option>
					         
					      </select>
					    </div>
					  </div>
				 </div>
				<div class="layui-row">
					<div class="layui-col-md12 ">
						
						  <div class="housepage-box">
						  	<div class="layui-row housepage">
								<div class="layui-col-md8">
									<div class="layui-row" style="border:1px solid #f1f1f1;margin:20px;padding:10px;" tableid="1">
										<div class="layui-col-md8">
											<div class="layui-row general" tableid="1">
												<div class="layui-col-md6 col1">
													
												</div>
												<div class="layui-col-md6 col2">
													
												</div>
											
											</div>
											
										</div>
										<div class="layui-col-md4">
											二维码
										</div>
									
									</div>
									<div class="layui-row detail" style="border:1px solid #f1f1f1;margin:20px;padding:10px;" tableid="1">
										<div class="layui-col-md6 col1">
											
											
										</div>
										<div class="layui-col-md6 col2">
											
										</div>
									
									</div>
									<div class="layui-row other" style="border:1px solid #f1f1f1;margin:20px;padding:10px;" tableid="4">
										<div class="layui-col-md6">
											<div name="" class="move">其他信息一</div>
											<div name="" class="move">其他信息二</div>
										</div>
										<div class="layui-col-md6">
										
										
										</div>
									
									</div>
									
									<div style="height:200px;border:1px solid #f1f1f1;margin:20px;padding:10px;">跟进信息</div>
								</div>
								
								
								
								<div class="layui-col-md4">
									<div style="height:200px;border:1px solid #f1f1f1;margin:20px;padding:10px;">全景看房</div>
									
									<div class="layui-row houseowner" style="height:200px;border:1px solid #f1f1f1;margin:20px;padding:10px;" tableid="3">
										
										<div class="layui-col-md12 col">
											
										</div>
									
									</div>
									
									
									<div style="height:200px;border:1px solid #f1f1f1;margin:20px;padding:10px;">相关经济人</div>
								</div>
							</div>
						  
						  </div>
						
						<div class="otherpage-box">
							<div class="layui-row otherpage" style="display:none;">
								其他页面展示
								<div class="otherinfo-layout">
										
									
								</div>
							</div>
						</div>
						
						
					</div>
				</div> <!-- row结束 -->
				
				
			</div>
		
		<script>
		layui.use(['element','form','table'], function(){
			  var element = layui.element;
			  var form = layui.form;
			  var table = layui.table;
			  
			  
			  
		  var tableid = $(".general").attr("tableid");
			var sourceId = '${sourceid}';
			$.post("<%=request.getContextPath()%>/manage/getValue",{company:"11",tableid:tableid,sourceId:sourceId},function(d){
				console.log(d.data);
				for(var i=0;i<d.data.length;i++){
					if(d.data[i].general){
						var col1 = $(".general .col1");
						var col2 = $(".general .col2");
						var generalsize = d.data[i].general.length;
						for(var j=1;j<=generalsize;j++){
							if(j%2==1){
								var div1 = '<div><span>'+d.data[i].general[j-1].cnName+'55555555</span>：<span>'+d.data[i].general[j-1].value+'</span></div>';
								col1.append(div1);
							}
							if(j%2==0){
								var div2 = '<div><span>'+d.data[i].general[j-1].cnName+'</span>：<span>'+d.data[i].general[j-1].value+'</span></div>';
								col2.append(div2);
							}
							
						}
						
					}
					
					if(d.data[i].detail){
						var col1 = $(".detail .col1");
						var col2 = $(".detail .col2");
						var detailsize = d.data[i].detail.length;
						for(var j=1;j<=detailsize;j++){
							if(j%2==1){
								var div1 = '<div><span>'+d.data[i].detail[j-1].cnName+'</span>：<span>'+d.data[i].detail[j-1].value+'</span></div>';
								col1.append(div1);
							}
							
							if(j%2==0){
								var div2 = '<div><span>'+d.data[i].detail[j-1].cnName+'</span>：<span>'+d.data[i].detail[j-1].value+'</span></div>';
								col2.append(div2);
							}
							
						}
					}
					
					if(d.data[i].field){
						var tableid = d.data[i].tableid;
						var tableid1 = $(".houseowner").attr("tableid");
						if(tableid==tableid1){
							var col = $(".houseowner .col");
							var fieldsize = d.data[i].field.length;
							for(var j=1;j<=fieldsize;j++){
								if(j==fieldsize){
									var div = '<div><span>'+d.data[i].field[j-1].cnName+'</span>：<span>'+d.data[i].field[j-1].value+'</span></div>';
								}else{
									var div = '<div><span>'+d.data[i].field[j-1].cnName+'</span>：<span>'+d.data[i].field[j-1].value+'</span></div>';
								}
								
								col.append(div);
							}
							var edit = '<div><span class="editowner" style="color:red;" >编辑</span><span style="margin-left:10px;color:red;">新增</span></div>';
							col.append(edit);
							
						}
						
						editOwner();
					}
				}
				
			})		
			
			function editOwner(){
				$(".editowner").click(function(){
			    	//alert(111);
			    	popEditOwner(sourceId);
					$.post("<%=request.getContextPath()%>/manage/getOneForm",{sourceId:sourceId,formName:"houseowner",company:"11"},function(d){
						console.log(d.data);
						
						if(d.data.formName){
							var formName = "houseowner";
							if(d.data.formName==formName){
								var html = d.data.formHtml;
								//alert(html);
								//$("form[name='"+formName+"']").parent().html("");
								$(".editowner").html(html);
								$("form").find(".sourceId").val(sourceId);
								$("form").find(".move").removeAttr("flag");
								$("form").find(".formitem").removeClass("droppable");
								
								
								//加载单选、多选、下拉等数据项
								if(d.data.dataitem){
									for(var z=0;z<d.data.dataitem.length;z++){
										var iform = $("form[name='"+formName+"']");
										var element = d.data.dataitem[z].element;
										var datasource = d.data.dataitem[z].datasource;
										var elementDatasource = element+"-"+datasource;
										var divbox = iform.find(".move");
										for(var x=0;x<divbox.length;x++){
											var curelementDatasource = $(divbox[x]).attr("datasource");
											if(elementDatasource==curelementDatasource){
												var div = $(divbox[x]).find(".layui-input-block");
												div.empty();
												if(element=="select"){
													var el1 = $('<select name="'+d.data.dataitem[z].fieldname+'" lay-filter="'+d.data.dataitem[z].fieldname+'" class="value-box"></select>');
													var el2 = '<option class="blank_option"></option>';
													el1.append(el2);
													div.append(el1);
												}
												for(var y=0;y<d.data.dataitem[z].elementdataitem.length;y++){
													if(element=="radio"){
														var input = '<input type="radio" name="'+d.data.dataitem[z].fieldname+'" value="'+d.data.dataitem[z].elementdataitem[y].itemName+'" title="'+d.data.dataitem[z].elementdataitem[y].itemName+'" class="value-box">';
														div.append(input);
														
													}else if(element=="checkbox"){
														var input = '<input type="checkbox" name="'+d.data.dataitem[z].fieldname+'" value="'+d.data.dataitem[z].elementdataitem[y].itemName+'" title="'+d.data.dataitem[z].elementdataitem[y].itemName+'" class="value-box">';
														div.append(input);
													}else if(element=="select"){
														var el3 = '<option class="data_option" value="'+d.data.dataitem[z].elementdataitem[y].itemName+'">'+d.data.dataitem[z].elementdataitem[y].itemName+'</option>';
														el1.append(el3);
													}
												}
											}
										
											
											
										}
										
									}
									
								}
							}
							
							if(d.data.fieldvalue && d.data.fieldvalue.length>0){
								for(var x=0;x<d.data.fieldvalue.length;x++){
									//var formname = d.data.formUseName;
									var valuebox = $(".editHouse").find("form").find(".value-box");
									var curdata = d.data.fieldvalue[x];
									//赋值
									giveValue(valuebox,curdata);
								}
							}
							
							
							//表单更新
							form.render();
							//表单验证
							form.verify({
								
							})
					
							$('.form-item').find('*[lay-verify]').on('blur', blurV);
							
						}
						
						
					})
			    })
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
						
						/* var options = $(valuebox[j]).find("option");
						for(var n=0;n<options.length;n++){
							var optionvalue = $(options[n]).val();
							if(optionvalue==value){
								$(options[n]).attr("selected","true");
							}else{
								$(options[n]).removeAttr("selected");
							}
						} */
						alert(value);
						$(valuebox[j]).val(value);
						form.render('select');
					}
					if($(valuebox[j]).is("input[type='radio']")){
						var radiovalue = $(valuebox[j]).val();
						//alert(value);
						//alert(radiovalue);
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
		
		
		/*编辑弹出*/
			function popEditOwner(sourceid) {
				layer.open({
					type: 1,
					title: false,
					shade: [0],
					area: ['800px', '700px'],
					offset: 'auto', //弹出位置
					btnAlign: 'r',
					content: $(".editOwner"), //显示的url，no代表不显示滚动条
					btn: ['确定'],
					yes: function(index, layero) {
						saveFormValue(sourceid,"editOwner");
					},
					anim: 2,
					cancel: function(){
						$(".editHouse").css("display","none");
					}
				});
			}
		</script>
				
	</body>
	
	<!-- 编辑房东 -->
	 <div class="form-box editOwner" style="display:none;">
			
			
	</div>


</html>