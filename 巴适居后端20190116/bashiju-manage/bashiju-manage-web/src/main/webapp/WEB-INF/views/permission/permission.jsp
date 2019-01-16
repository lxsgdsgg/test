<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/common.jsp" %>
<style type="text/css">
	body{
		background: #eee;
	}
	.menus{
		border:1px solid #DDDD;
	    height: 580px;
   		overflow-y: auto;	
	}
	.permissions{
		border:1px solid #DDDD;
		height: 580px;
		overflow-y: auto;	
		background: white;
	}
	.fieldset{
		margin: 10px 5px;
	    border: 1px solid #DDDD;
	    border-radius: 5px;
	}
	.menuPermission{
/* 	    margin-left: 29px; */
	}
	.prohibit{
		color:#C0C0C0;
	}
	.valid{
		color: red;
	}
	.layui-this{
		color: #f11515 !important;
	}
	.layui-nav-child>.layui-this{
		border-right: 4px solid !important;
	}
	.layui-nav-child>dd:hover{
		border-right: 4px solid #d5d5d7 !important;
	}
	.layui-this:after{
		border-bottom: 2px solid #f11515 !important;
	}
	.layui-nav-tree{
		width:auto !important;
	}
	.layui-nav{
		background-color: transparent !important;
	}
	.layui-nav-itemed>.layui-nav-child{
		background-color: transparent !important;
	}
	.layui-nav-tree .layui-nav-child a{
		color:black !important;
	}
	.layui-nav .layui-nav-item a{
		color:black !important;
	    font-weight: bolder !important;
	}
	.layui-nav-itemed>a, .layui-nav-tree .layui-nav-title a, .layui-nav-tree .layui-nav-title a:hover {
	    color: #f11515 !important;
	}
	.layui-nav-tree .layui-nav-child dd.layui-this, .layui-nav-tree .layui-nav-child dd.layui-this a, .layui-nav-tree .layui-this, .layui-nav-tree .layui-this>a, .layui-nav-tree .layui-this>a:hover {
	    background-color: #d5d5d7 !important; 
	    color: #f11515 !important; 
	}
	.layui-nav-tree .layui-nav-child, .layui-nav-tree .layui-nav-child a:hover {
	    background: #d5d5d759 !important;
	    color: #f11515 !important;
	}
	.layui-nav .layui-nav-mored, .layui-nav-itemed>a .layui-nav-more {
	    margin-top: -9px;
	    border-style: dashed dashed solid;
	    border-color: transparent transparent #f11515 !important;
	}
	.layui-nav .layui-nav-more{
		border-top-color:black !important;
	}
	.layui-nav-tree .layui-nav-bar{
		width: 5px;
	    height: 0;
	    background-color: #f11515 !important;
	}
	.menu3>a{
		color: #bf9534 !important;
		font-weight: bolder !important;
	}
	.menu3{
		margin-bottom: 30px;
	}
</style>
</head>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="permissionForm">
	<input type="hidden" id="roleId" value="${roleId}"/>
	<input type="hidden" id="userId" value="${userId}"/>
	<div class="layui-tab layui-tab-brief">
	  <ul class="layui-tab-title" style="font-weight: bolder">
	  	<c:forEach var="menu1" items="${list}"  varStatus="index">
	  		<c:if test="${index.count==1}">
	  			<li class="layui-this" id="${menu1.id}">${menu1.name}</li>
	  		</c:if>
	  		<c:if test="${index.count!=1}">
	  			<li id="${menu1.id}">${menu1.name}</li>
	  		</c:if>
	  	</c:forEach>
	  </ul>
	  <div class="layui-tab-content">
	  	<c:forEach var="menu1" items="${list}"  varStatus="index">
	  		<c:if test="${index.count==1}">
	  			<div class="layui-tab-item layui-show">
  			 </c:if>
  			 <c:if test="${index.count!=1}">
	  			<div class="layui-tab-item">
  			 </c:if>
  			 	<div class="layui-row">
  			 		<div class="layui-col-xs2 menus">
				      <div>
				      	<ul class="layui-nav layui-nav-tree" lay-filter="test">
				      		<c:forEach var="menu2" items="${menu1.datas}">
								<li class="layui-nav-item layui-nav-itemed">
									<a href="javascript:;">${menu2.name}</a>
								    <dl class="layui-nav-child">
								      <c:forEach var="menu3" items="${menu2.datas}">
									      <dd><a href="#titles_${menu3.id}" id="navigate_${menu3.id}">${menu3.name}</a></dd>
								      </c:forEach>
								    </dl>
								</li>
							</c:forEach>
						</ul>
					  </div>
				    </div>
				    <div class="layui-col-xs10 permissions">
				    	<c:forEach var="menu2" items="${menu1.datas}">
							<fieldset class="fieldset">
			    				<legend>${menu2.name}</legend>
			    				<c:forEach var="menu3" items="${menu2.datas}">
				    				<div class="menu3" id="title_${menu3.id}">
								    	<a name="titles_${menu3.id}">${menu3.name}</a>
								    	<div class="menuPermission">
								    		<table class="layui-table" lay-size="sm" >
								    			<tbody>
								    				<c:forEach items="${menu3.datas}" var="menu4" varStatus="index">
								    					<c:if test="${(index.count==1)||(index.count)%6==0}">
									    					<tr>
									    				</c:if>
									    					<c:if test="${index.count==1}">
									    					<td>
									    						<c:if test="${menu3.dataPerFlag==0}">
									    							<div>查看<input name="${menu1.id}_${menu2.id}_${menu3.id}" id="${menu1.id}_${menu2.id}_${menu3.id}" type="checkbox" lay-ignore/></div>
									    						</c:if>
									    						<c:if test="${menu3.dataPerFlag==1}">
									    							<div>
																		<label>查看</label>
																		<select name="${menu1.id}_${menu2.id}_${menu3.id}" id="${menu1.id}_${menu2.id}_${menu3.id}" lay-ignore onchange="changeMenuPermission('${menu1.id}_${menu2.id}_${menu3.id}')">
																			<option value="-1" class="prohibit">禁止</option>
																			<option value="" class="valid">所有</option>
																			<option value="1" class="valid">本市</option>
																			<option value="2" class="valid">本大区</option>
																			<option value="3" class="valid">本区</option>
																			<option value="4" class="valid">本店</option>
																			<option value="5" class="valid">本组</option>
																			<option value="7">本人</option>
																		</select>
																	</div>
									    						</c:if>
									    					</td>
									    					</c:if>
<%-- 										    				<c:forEach var="menu4" items="${menu3.datas}" varStatus="status"> --%>
										    					<c:if test="${menu4.functionType=='onlyButton'}">
										    						<td><div>${menu4.name}<input name="${menu1.id}_${menu2.id}_${menu3.id}_${menu4.id}" id="${menu1.id}_${menu2.id}_${menu3.id}_${menu4.id}" type="checkbox" lay-ignore/></div></td>
										    					</c:if>
										    					<c:if test="${menu4.functionType=='multipleButton'}">
										    						<td>
																		<div>
																			<label>${menu4.name}</label>
																			<select name="${menu1.id}_${menu2.id}_${menu3.id}_${menu4.id}" id="${menu1.id}_${menu2.id}_${menu3.id}_${menu4.id}" lay-ignore>
																				<option value="-1" class="prohibit">禁止</option>
																				<option value="" class="valid">所有</option>
																				<option value="1" class="valid">本市</option>
																				<option value="2" class="valid">本大区</option>
																				<option value="3" class="valid">本区</option>
																				<option value="4" class="valid">本店</option>
																				<option value="5" class="valid">本组</option>
																				<option value="7">本人</option>
																			</select>
																		</div>
																	</td>
										    					</c:if>
<%-- 										    				</c:forEach> --%>
									    				<c:if test="${(index.count==5)||(index.count)%11==0}">
									    					</tr>
									    				</c:if>
								    				</c:forEach>
								    				<c:if test="${menu3.datas.size()<=0}">
								    					<tr>
									    					<td>
									    						<c:if test="${menu3.dataPerFlag==0}">
									    							<div>查看<input name="${menu1.id}_${menu2.id}_${menu3.id}" id="${menu1.id}_${menu2.id}_${menu3.id}" type="checkbox" lay-ignore/></div>
									    						</c:if>
									    						<c:if test="${menu3.dataPerFlag==1}">
									    							<div>
																		<label>查看</label>
																		<select name="${menu1.id}_${menu2.id}_${menu3.id}" id="${menu1.id}_${menu2.id}_${menu3.id}" lay-ignore onchange="changeMenuPermission('${menu1.id}_${menu2.id}_${menu3.id}')">
																			<option value="-1" class="prohibit">禁止</option>
																			<option value="" class="valid">所有</option>
																			<option value="1" class="valid">本市</option>
																			<option value="2" class="valid">本大区</option>
																			<option value="3" class="valid">本区</option>
																			<option value="4" class="valid">本店</option>
																			<option value="5" class="valid">本组</option>
																			<option value="7">本人</option>
																		</select>
																	</div>
									    						</c:if>
									    					</td>
								    					</tr>
								    				</c:if>
								    			</tbody>
								    		</table>
<%-- 								    		<c:forEach var="combinationBtn" items="${combinationBtns[menu3.id]}"> --%>
								    		<c:if test="${combinationBtns[menu3.id].size()>0}">
									    		<table class="layui-table" lay-size="sm">
									    		  <colgroup>
												    <col width="80"><col>
												  </colgroup>
								    			<thead>
								    				<tr>
								    				<th></th>
								    				<c:forEach var="combination" items="${combinations[menu3.id]}">
									    				<th>
									    					<div style="margin-left: 4px;"><label>${combination.name}</label></div>
									    					<select lay-ignore style="border: 0px;background: #F2F2F2;width: 57px;" onchange="selected('${combination.id}',this)">
									    						<option value="-1" class="prohibit">禁止</option>
																<option value="" class="valid">所有</option>
																<option value="1" class="valid">本市</option>
																<option value="2" class="valid">本大区</option>
																<option value="3" class="valid">本区</option>
																<option value="4" class="valid">本店</option>
																<option value="5" class="valid">本组</option>
																<option value="7">本人</option>
								    						</select>
									    				</th>
								    				</c:forEach>
								    				</tr>
								    			</thead>
								    			<tbody>
								    				<c:forEach var="combinationBtn" items="${combinationBtns[menu3.id]}">
									    				<tr>
									    					<td>${combinationBtn.name}</td>
									    					<c:forEach var="combination" items="${combinations[menu3.id]}">
										    					<td>
										    						<c:if test="${!fn:contains(combination.relationBtn,combinationBtn.id)}">--</c:if>
										    						<c:if test="${fn:contains(combination.relationBtn,combinationBtn.id)}">
																	<div>
																		<select lay-ignore flag="${combination.id}" assignFlg="${menu1.id}_${menu2.id}_${menu3.id}_${combinationBtn.id}" name="${menu1.id}_${menu2.id}_${menu3.id}_${combinationBtn.id}@@${combination.id}" id="${menu1.id}_${menu2.id}_${menu3.id}_${combinationBtn.id}">
																			<option value="-1" class="prohibit">禁止</option>
																			<option value="" class="valid">所有</option>
																			<option value="1" class="valid">本市</option>
																			<option value="2" class="valid">本大区</option>
																			<option value="3" class="valid">本区</option>
																			<option value="4" class="valid">本店</option>
																			<option value="5" class="valid">本组</option>
																			<option value="7">本人</option>
																		</select>
																	</div>
																	</c:if>
																</td>
															</c:forEach>
									    				</tr>
								    				</c:forEach>
								    			</tbody>
								    		</table>
								    		</c:if>
<%-- 								    		</c:forEach> --%>
					    		
								    	</div>
							    	</div>
						    	</c:forEach>
		    				</fieldset>
						</c:forEach>
				    </div>
  			 	</div>
  			 </div>
		 </c:forEach>
	  </div>
	  <div class="layui-btn-group" style="padding-left: 51%;">
		  <button class="layui-btn" lay-submit lay-filter="permissionForm">立即提交</button>
	  </div>
	  </form>
	</div>
</div>
<script>
layui.use(['form','element','table', 'laydate','tree'], function(){
	var form = layui.form;
	var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
	var table = layui.table;
	//监听提交
	  form.on('submit(permissionForm)', function(data){
		var roleId = $("#roleId").val();
		var userId = $("#userId").val();
		if(roleId==""){
			layer.alert("请选择要授权的角色");
		}
		for(var obj in data.field){
			if(data.field[obj]=='-1'){
				delete data.field[obj];
			}
		}
		
	    $.ajax({
	    	type:"post",
	    	url:"savePermissionInfo",
	    	dataType:"json",
	    	data:{data:JSON.stringify(data.field),roleId:roleId,userId:userId},
	    	success:function(dt){
	    		if(dt.success){
	    			layer.alert('授权成功', function(index){
		    			parent.layer.closeAll();
    				});  
	    		}else
	    			layer.alert(dt.msg);
	    		
	    	},
	    	error:function(a,b,c){
	    		
	    	}
	    });
	    return false;
	  });
	
	});
	
	function changeMenuPermission(menu_id){
		if($("#"+menu_id).val()=="-1")
			$("#"+menu_id).parents("table").find("select").val("-1");
	}
	function selected(flagId,th){
		$("select[flag='"+flagId+"']").val($(th).val());
	}
	$(document).ready(function(){
		var per = ${per};
		debugger;
		Object.keys(per).forEach(function(key){
			$("select[name='"+key+"']").val(per[key]=="on"?"":per[key]);
// 			$("select[assignFlg='"+key+"']").val(per[key]=="on"?"":per[key]);
			if(per[key]=='on'){
				$("input[name='"+key+"']").attr("checked","checked");
			}
		});
	});
	var permission = {
		fixed: function(id){
// 			debugger;
// alert($("#title_"+id).position().top);
// 			$(".permissions").animate({scrollTop:$("#title_"+id).offset().top});
			$(".permissions").scrollTop(($(".permissions").offset().top)+($("#title_"+id).offset().top));
// 			$("#title_"+id).css("cssText", "color:red !important;");  
 		}
	};
	
</script> 
</body>
</html>