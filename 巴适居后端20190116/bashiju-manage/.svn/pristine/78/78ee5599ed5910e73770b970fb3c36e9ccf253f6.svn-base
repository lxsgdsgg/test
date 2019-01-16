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
	
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script>
	</head>
	<script type="text/javascript">
	
	
	
	
	function saveHtml(){
		var tableId = $("select[name='tableId']").val();
		var defaultHtml = $(".form-box").html();
		var pageUseName = $("input[name='pageUseName']").val();
		var pageUsage = $("input[name='pageUsage']").val();
		
		var divs =  $(".form-box form div.move");
		var formItem = [];
		
		if(pageUseName=='houseinfo'){
			//遍历general下的
			var layrow = $(".form-item1").find(".general").find(".layui-row");
			
			for(var i=0;i<layrow.length;i++){
				var row = [];
				var laycol = $(layrow[i]).find(".formitem");
				for(var j=0;j<laycol.length;j++){
					var col = [];
					var layitem = $(laycol[j]).find(".move");
					for(var k=0;k<layitem.length;k++){
						var name = $(layitem[k]).attr("englishname");
						var cnName = $(layitem[k]).find("label").text();
						var item = {
								name:name,
								cnName:cnName,
								type:'general'
							}
						col.push(item);
					}
					row.push(col);
					
				}
				formItem.push(row);
			}
			
			//遍历 detail下的
			var layrow = $(".form-item").find(".detail").find(".layui-row");
			for(var i=0;i<layrow.length;i++){
				var row = [];
				var laycol = $(layrow[i]).find(".formitem");
				for(var j=0;j<laycol.length;j++){
					var col = [];
					var layitem = $(laycol[j]).find(".move");
					for(var k=0;k<layitem.length;k++){
						var name = $(layitem[k]).attr("englishname");
						var cnName = $(layitem[k]).find("label").text();
						var item = {
								name:name,
								cnName:cnName,
								type:'detail'
							}
						col.push(item);
					}
					row.push(col);
					
				}
				formItem.push(row);
			}
		}else{
			var layrow = $(".form-item").find(".layui-row");
			for(var i=0;i<layrow.length;i++){
				var row = [];
				var laycol = $(layrow[i]).find(".formitem");
				for(var j=0;j<laycol.length;j++){
					var col = [];
					var layitem = $(laycol[j]).find(".move");
					for(var k=0;k<layitem.length;k++){
						var name = $(layitem[k]).attr("englishname");
						var cnName = $(layitem[k]).find("label").text();
						var item = {
								name:name,
								cnName:cnName,
								type:''
							}
						col.push(item);
					}
					row.push(col);
					
				}
				formItem.push(row);
			}
		}
		
		
		//console.log(formItem);
		
		
		
		var data = {
				tableId:tableId,
				pageUseName:pageUseName,
				defaultHtml:defaultHtml,
				formItem:formItem
				
		}
		
		$.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: 'application/json',
            data: JSON.stringify(data),
            url: "<%=request.getContextPath()%>/manage/savePageInfo" ,//url	                
            //data:data,
            success: function (result) {
                
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
		

	</script>
	<body class="userinfo">
			<div class="layui-container">
				
				<div class="layui-row">
				
					<div class="layui-form">
						<div class="layui-form-item move resizable">
						    <label class="layui-form-label">选择对应表</label>
						    <div class="layui-input-block ">
						      <select name="tableId" lay-filter="biao">
						      <option value=""></option>
						        <option value="1">房源信息</option>
						        <option value="3">房东信息</option>
						        <option value="4">房源其他信息</option>
						      </select>
						    </div>
						  </div>
						
						  
					  <div class="layui-form-item move resizable">
						    <label class="layui-form-label">表单页面使用名称</label>
						    <div  class="layui-input-block">
						      <input type="text" name="pageUseName" placeholder="请输入" autocomplete="off" class="layui-input">
						    </div>
					  </div>
					  
					  
					  <div class="form-box" style="display:none;" >
					  
					  	<!--  放form表单 -->
					  		
								<form class="layui-form form-item form-item2" name="houseowner">
							<input type="hidden" class="delField"  value=""/>
							<input type="hidden" class="saveid" value="3">
							<div>
								<div class="layui-row">
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="fullName" flag="1">
										    <label class="layui-form-label">姓名</label>
										    <div  class="layui-input-block">
										      <input type="text" name="fullName" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										 </div>
									</div>
									<div class="layui-col-md6 formitem ">
										<div class="layui-form-item move resizable" englishname="phone">
										    <label class="layui-form-label">电话</label>
										    <div  class="layui-input-block">
										      <input type="text" name="phone" placeholder="请输入" autocomplete="off" class="layui-input value-box">
										    </div>
										</div>
									</div>
								</div>
								<p class="appendField"></p>
							</div>
							
						  	
						</form>
								
						<!--  放form表单结束 -->
						
					  </div>
					  
					  
					  
					   <div class="layui-form-item save-btn move resizable">
						    <div class="layui-input-block">
						      <button type="button" class="layui-btn"  lay-filter="*" onclick="saveHtml();">提交</button>
						    	<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						    </div>
					  </div>
					
					</div>
				  
				</div>
			</div>
		
		
		
		<script>
		layui.use(['element','form'], function(){
			  var element = layui.element;
			  var form = layui.form;
		})
		</script>
				
				
	</body>

</html>