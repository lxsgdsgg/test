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
			var form = $(formbox[n]).find("form");
			for(var m=0;m<form.length;m++){
				var tableid = $(form[m]).find(".saveid").val();
				
				var divs = $(form[m]).find(".move");
				
				if(tableid!=undefined){
					var tableInfo = {
							 
							
					 };
					tableInfo.tableid = tableid;
					
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
	
	
	function addHouseowner(){
		var form = $(".form-box2").html();
		$(".form-box2").append(form);
	}
	
	
	$(function(){
		
				
				
				
				
				
			})	
	

	</script>
	<body class="userinfo">
			<div class="layui-container">
				<div class="layui-row">
					
					<div class="layui-col-md12">
						<div class="layui-tab" lay-filter="myTab">
							<ul class="layui-tab-title">
								<li class="icon-ex layui-this">填写表单</li>
								<li class="icon-ex">查看表单</li>
							</ul>
							<div class="layui-tab-content">
							
								<div class="layui-tab-item layui-show mo-form">
									<div class="form-title1">房源信息表单</div>
									<div class="form-box form-box1">
										<form class="layui-form form-item form-item1" tabletype="father" name="houseinfo"> 
										</form>
									</div>
									<div class="form-title2">房东信息表单<span onclick="addHouseowner();" style="color:red;margin-left:10px;">新增</span></div>
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
								
								<div class="layui-tab-item">
									<div class="">
										<table id="datashow" lay-filter="houseinfo" ></table>
									</div>
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
			  
			 $.post("<%=request.getContextPath()%>/manage/getTableValue",{},function(data){
				 console.log(data);
				 if(data.data){
					 var cols = [];
					 for(var key in data.data[0]){
						 var col ={
								 field:key,
								 title:key,
								 width:100,
						 }
						  cols.push(col);
					 }
					var col = {fixed: 'right', width:150, align:'center', toolbar: '#barDemo'};
					 cols.push(col);
					 
					 /* for(var i=0;i<data.data.length;i++){
						 var div = '<div sourceid="'+data.data[i].sourceId+'" onclick="toShowForm(this);">'+data.data[i].houseName+'</div>';
						 $("#datashow").append(div);
						 
						  
						 
					 } */
					 
					 
					 
					    table.render({
					    elem: '#datashow'
					    ,height: 315
					    ,data: data.data //数据接口
					    ,page: true //开启分页
					    ,cols: [cols]
					  }); 
				 
				 }
				 
				 
				
			 })
			  
			
			 //监听工具条
			  table.on('tool(houseinfo)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'detail'){
			    	toShowForm(data.sourceId);
			      //layer.msg('ID：'+ data.id + ' 的查看操作');
			    } else if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			        obj.del();
			        layer.close(index);
			      });
			    } else if(obj.event === 'edit'){
			      //layer.alert('编辑行：<br>'+ JSON.stringify(data))
			    	toEditForm(data.sourceId);
			    }
			  });
			
			 
			 
			 $.post("<%=request.getContextPath()%>/manage/getFormGroup",{formName:"houseinfo",company:"11"},function(d){
					console.log(data);
					var data = d.data;
					for(var i=0;i<data.length;i++){
						var formbox = $(".form-box");
						
						for(var k=0;k<formbox.length;k++){
							var formname = $(formbox[k]).find("form").attr("name");
							
							if(data[i].formUseName==formname){
								var html = data[i].formHtml;
								$(formbox[k]).html(html);
								$("form").find(".move").removeAttr("flag");
								$("form").find(".formitem").removeClass("droppable");
								//表单更新
								form.render();
								//表单验证
								form.verify({
									
								})
						
								$('.form-item').find('*[lay-verify]').on('blur', blurV);
								
								/* if(data[i].tableId){
									$(formbox[k]).find("form").find(".saveid").val(data[i].tableId);
								}
								
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
									
								} */
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
			 
			 function toShowForm(sourceid){
				//var sourceid = $(obj).attr("sourceid");
				window.open("<%=request.getContextPath()%>/manage/showform?sourceid="+sourceid);
			}
			 
			 function toEditForm(sourceid){
					//var sourceid = $(obj).attr("sourceid");
					window.open("<%=request.getContextPath()%>/manage/editform?sourceid="+sourceid);
				}
				
		})
		
		
		</script>
		<script type="text/html" id="barDemo" >
          <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
          <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  
        </script>
				
	</body>

</html>