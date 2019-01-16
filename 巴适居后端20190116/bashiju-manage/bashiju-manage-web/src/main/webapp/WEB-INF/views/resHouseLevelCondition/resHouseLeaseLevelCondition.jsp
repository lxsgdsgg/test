<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出租</title>
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" media="all">
<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
</head>
<style type="text/css">
.areaDiv {
    position: fixed;
    top: 20px;
    left: 50px;
    z-index: 99891010;
    background-color: rgb(238, 238, 238);
    box-shadow: rgba(0, 0, 0, 0.3) 1px 1px 50px;
    margin: auto;
    border-radius: 2px;
    overflow: hidden;
}
.areaIcon{
	background-color: #c0c4cc;
    color: #ffffff;
    margin-left: 8px;
    border-radius: 20px;
    font-size: 12px;
    cursor: initial;
    overflow: hidden;
}
.areaSpan{
	padding: 2px 5px;
    background: #f0f2f5;
    border-radius: 2px;
    color: #909399;
    display: block;
    line-height: 18px;
    height: 18px;
    margin: 2px 5px 2px 0px;
    float: left;
    cursor: initial;
    user-select: none;
    overflow: hidden;
}
</style>
<body>
<div class="layui-container">
	<form class="layui-form" action="" id="menuForm" style="margin-top: 20px;" name="res_house">
		<input type="text" name="transactionType" value="2" autocomplete="off" class="layui-input" style="width:400px;display: none ">
		<input type="text" name="id" id="id" value="${res_houses.id}" autocomplete="off" class="layui-input" style="width:400px;display: none ">
	   	<div class="layui-form-item">
	 		<label class="layui-form-label"><span style="color:red;">*</span>选择城市</label>
		    <div class="layui-input-inline">
				<input type="text"  id="cityName"   value="${res_houses.cityName}" required  lay-verify="required" placeholder="城市名称" autocomplete="off" class="layui-input" style="width:200px">
		   		<input type="text" name="cityCode" id="cityCode" value="${res_houses.cityCode}" autocomplete="off" class="layui-input" style="width:200px;display: none">
		    </div>
		     <label class="layui-form-label"><span style="color:red;">*</span>等级</label>
		    <div class="layui-input-inline">
			    	  <select name="levelType" id="levelTypes"  lay-verify="required" >
			    	 		    <option value="">请选择</option>
			    	  			<option value="A">A等级</option>
			    	  			<option value="B">B等级</option>
			    	  </select>
		    </div>
	 	</div>
	   <div class="layui-form-item">
		   
	  </div>
	   <div class="layui-form-item">
		    <label class="layui-form-label"><span style="color:red;">*</span>方案名称</label>
		    <div class="layui-input-inline">
					<input type="text" name="name" id="name" value="${res_houses.name}"  required  lay-verify="required" placeholder="方案名称" autocomplete="off" class="layui-input" style="width:400px">
		    </div>
	  </div>
	  <hr class="layui-bg-gray">
	   <div class="layui-form-item">
		    <label class="layui-form-label" id="area_label">需求区域</label>
		    <div class="layui-input-block">
			     <div type="text" name="area"  id="area"  class="layui-input" placeholder="请选择">
			     <c:forEach var="area" items="${res_houses.area}" varStatus="index">
				     	    <span class="areaCode areaSpan" data-value="${res_houses.areaCode.split(',')[index.count-1]}" data-name="${area}">
					     		<font>${area}</font>
					     	    <i class="layui-icon areaIcon">&#x1006;</i>
				     	    </span>
			     </c:forEach>
			     </div>
		    </div>
	  </div>
	  
	  	<div class="layui-form-item">
			      <label class="layui-form-label">条件</label>
			      <div class="layui-input-block">
		
			        <select  id="Conditions" xm-select="select" xm-select-type="2">

					</select>
			      </div>
				</div>
				
	  <div class="layui-form-item">
		    <label class="layui-form-label">产权性质</label>
		    <div class="layui-input-inline">
			    <select  id="propertyType"></select>
		    </div>
		    
		    <label class="layui-form-label">装修情况</label>
		    <div class="layui-input-inline">
			    <select  id="decoratingType"></select>
		    </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">价格区间(万元)</label>
		    <div class="layui-input-inline">
			    <select  id="Price"></select>
		    </div>
		    <label class="layui-form-label">面积区间(平米)</label>
		    <div class="layui-input-inline">
			    <select  id="Space"></select>
		    </div>
	  </div>
	  
	   <div class="layui-form-item">
	   	    <label class="layui-form-label">房屋用途</label>
		    <div class="layui-input-inline">
			    <select  id="houseUses" lay-filter="houseUses" ></select>
		    </div>
		     <label class="layui-form-label">付款方式</label>
		    <div class="layui-input-inline">
			    <select id="payTypeId" name="payTypeId">
			    		<option value="">请选着</option>
			    		<option value="1">按一年付</option>
			    		<option value="2">按一半年付</option>
			    		<option value="3">按一季度付</option>
			    		<option value="4">按一年付</option>
			    </select>
		    </div>
	   </div>
	     <div class="layui-form-item">
	        <label class="layui-form-label">配套</label>
	   	      <div class="layui-input-block">
			    <select id="matchings"  xm-select="selectPT" xm-select-type="2">
			   		   
			    </select>
		    </div>
  	    </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="res_housesForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" lay-submit lay-filter="close">取消</button>
	    </div>
	  </div>
	  </form>
</div>
<%@include file="/common/common.jsp" %>
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<script type="text/javascript">
var path = "<%=request.getContextPath()%>";
var resHouses=JSON.stringify(${res_houses});
var res_houses={};
if(resHouses!='' && resHouses!=undefined){
	res_houses = JSON.parse(resHouses);
}
//城市数据源
var cityList=JSON.parse('${cityListJson}');
var b= new AreaObject(cityList,selectCity,2,$("#cityName"));
//赋值
function selectCity(code,name){
	$("#cityName").val(name);
	$("#cityCode").val(code);
}

//No.1 定义拓展模块的路径
layui.config({
	base: '${staticfile_url}/common/js/'
}).extend({
	formSelects: 'formSelects-v3'
});
//区域数据源
var areaList=JSON.parse('${areaListJson}');
var areaArr=[];//区域代码
var areaName=[];//区域名称
var arr_d = '${res_houses.areaCode}';
if(arr_d!="")areaArr = arr_d.split(",");
var name_d = '${res_houses.area}';
if(name_d!="")areaName = name_d.split(",");
//选择区域
var m=new MultiSelection(areaList,function(code,name,level){
	var type = "";
	if(level==1)
		type="area-";//区域
	else if(level==2)
		type="reg-";//片区
	else 
		type="com-";//小区
	//判断是否已存在选择区域	
	if(areaArr.includes(type+code))
		return false;
	
	var item = '<span class="areaCode areaSpan" data-value="'+type+code+'" data-name="'+name+'" ><font>'+name+'</font>	<i class="layui-icon areaIcon">&#x1006;</i></span>';
	
	areaArr.push(type+code);
 	areaName.push(name);
 	$("#area").append(item);
 	$("#area .layui-icon").on("click",function(event){
 		areaArr.removeObj($(this).parent().attr("data-value"));
		areaName.removeObj($(this).parent().attr("data-name"));
		$(this).parent().remove();
	});
},3,$("#area"))
 layui.use(['form','formSelects'], function(){
	  var $ = layui.$ //重点处(jquery)
	  var form = layui.form;
	  formSelects = layui.formSelects//下拉多选框
	  //得到区域的值
	  $("#area .layui-icon").on("click",function(event){
		  areaArr.removeObj($(this).parent().attr("data-value"));
		  areaName.removeObj($(this).parent().attr("data-name"));
		$(this).parent().remove();
	  })
	//条件  
	  var list=${houseLevelCondition}
			var obj =list;//第i个控件
			var html = "";
			html+='<option value="">请选择</option>'
			for(var j=0;j<obj.length;j++){
				if(res_houses.conditions==obj[j].value){
					html +='<option value="'+obj[j].value+'" selected>'+obj[j].name+'</option>';
				}else{
					html +='<option value="'+obj[j].value+'">'+obj[j].name+'</option>';
				}	
			}
			$("#Conditions").html(html);
			form.render('select');
			formSelects.value('select', "${res_houses.conditions}".split(",")); 
			
	  //普通下拉
	  $.post(path+"/manage/getSelectValue/many",{param:"res_house"},function(d){
			var obj = d.data;//第i个控件
			for(var key in obj){//得到属性名   propertyType   
				var arr = obj[key];
				var html = "";
				html +='<option value="">请选择</option>'
				for(var j=0;j<arr.length;j++){
					if(arr[j].id==res_houses[key]){
						html +='<option value="'+arr[j].id+'" selected>'+arr[j].fieldValue+'</option>';
					}else{
						html +='<option value="'+arr[j].id+'">'+arr[j].fieldValue+'</option>';
					}
				$("#"+key).html(html);
			}
		}
		form.render('select');
	});
	//获取区间数据值
		 $.post(path+"/manage/getRegionValue/many",{tag:"1",param:"res_house"},function(d){
			 for(var i=0;i<d.data.length;i++){
					var obj = d.data[i];//第i个控件
					for(var key in obj){//得到属性名   propertyType   
						var arr = obj[key];//id?
						var html = "";
						html +='<option value="">请选择</option>'
						for(var j=0;j<arr.length;j++){
							if(arr[j].min==res_houses["min"+key] && arr[j].max==res_houses["max"+key]){
								html +='<option value="'+arr[j].min+"-"+arr[j].max+'"selected>'+arr[j].min+ "-"+arr[j].max+'</option>';
							}else{
								html +='<option value="'+arr[j].min+"-"+arr[j].max+'">'+arr[j].min+ "-"+arr[j].max+'</option>';
							}
						}
						$("#"+key).html(html);
					}
				}
				form.render('select');
		});
	
		  //取消
		  form.on('submit(close)', function(data){
				parent.layer.closeAll();
		  });
		  //监听提交
		  form.on('submit(res_housesForm)', function(data){
			  var area="";
			//区域代码  
				area=areaArr.join(",")
		   	//区域名称
				areaNames=areaName.join(",")
			data.field.area=areaNames;	
			data.field.areaCode=area;
			data.field.cityName=$("#cityName").val();
			data.field.cityCode=$("#cityCode").val();
			var space= $("#Space").val();
		    var price=$("#Price").val();
		    var area=$("#area").val();
		    //得到房源交易类型选中的中文
		    var transactionType=$("#transactionType :selected").text();
		    //下拉框的名称
		      var propertyTypeName=$("#propertyType :selected").text();
			  var decoratingTypeName=$("#decoratingType :selected").text();
			  var houseUsesName=$("#houseUses :selected").text();
			  var minSpace=0;
			  var maxSpace=0;
			  var minPrice=0;
			  var maxPrice=0;
			  if(space!=""){
				  //截取面积  
				   minSpace=space.split("-")[0];
				   maxSpace=space.split("-")[1];
			  }
			  if(price!=""){
				  //截取面积  
				   minPrice=price.split("-")[0];
				   maxPrice=price.split("-")[1];
			  } 
		    //条件下拉框的值
		    var propertyType=$("#propertyType :selected").val();
		    var decoratingType=$("#decoratingType :selected").val();
		    var houseUses=$("#houseUses :selected").val();
		    //得到条件多选下拉框文字
		    var conditionsName=formSelects.value('select', 'nameStr');
		    //得到多选下拉的值   
		    var result=formSelects.value('select', 'val');
		    var conditions="";
		    //用逗号分隔条件
		    conditions=result.join(",")
		   //配套下拉文字	 
			var matchingNames=formSelects.value('selectPT', 'nameStr');
			//配套下拉值
			var matchingResult=formSelects.value('selectPT', 'val');	 
			var matchingIds="";
			matchingIds=matchingResult.join(",")	 
			//支付方式	 
			 var payTypeName= $("#payTypeId :selected").text();
			 var payTypeId= $("#payTypeId :selected").val();
			 data.field.payTypeId=payTypeId;	
			 data.field.conditions=conditions;
			 data.field.propertyType=propertyType;
			 data.field.decoratingType=decoratingType;
			 data.field.matchingId=matchingIds;
			 data.field.matching=matchingNames;
			 data.field.houseUses=houseUses;
		     data.field.minSpace=minSpace; 
		     data.field.maxSpace=maxSpace;
		     data.field.minPrice=minPrice;
		     data.field.maxPrice=maxPrice;
		     //初始化备注
			 data.field.remark="";
			//备注
			 if(areaNames!=""&& areaNames!=undefined)
		    	data.field.remark="<font style='color:black; font-weight:bold'>需求区域:</font>:"+areaNames+";";
		    if(conditions!="")
				 data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>条件</font>:"+conditionsName+";"
			 if(propertyType!="")	 
				data.field.remark=data.field.remark+"<font style='color:black;font-weight:bold'>产权性质</font>:"+propertyTypeName+";";
			 if(decoratingType!="")
				data.field.remark=data.field.remark+"<font style='color:black;font-weight:bold'>装修情况</font>:"+decoratingTypeName
			 if(houseUses!="")
				 data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>房屋用途</font>:"+houseUsesName;
			 if(space!="")
			    	data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>面积</font>"+space+";";
		     if(price!="")	
		    	 data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>价格</font>"+price+";"; 
	    	 if(matchingResult!="")
				 data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>配套</font>:"+matchingNames+";"
			 if(payTypeId!="")
				 data.field.remark=data.field.remark+"<font style='color:black; font-weight:bold'>支付方式</font>:"+payTypeName+";"	 
			$.ajax({
	    	type:"post",
	    	url:"saveOrUpdateResHouseLevelCondition",
	    	dataType:"json",
	    	data:{jsonData:JSON.stringify(data.field)},
	    	success:function(dt){
	    		if(dt.success){
	    			layer.alert(dt.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(dt.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
		    return false;
		  });
		  //自定义验证
		  form.verify({
			  conditions: function(value, item){
				  if(formSelects.value('select', 'val').length<=0){
				  		return "条件不允许为空";
				  	}
			  },
			  //验证需求区域
			  area: function(value, item){
				  var values="";
				  $("#area span.areaCode").each(function(){
						values+=$(this).attr("data-value");
					});
				
				  if(values==''||values.trim()==''){
				  		return "需求区域不允许为空";
				  	}
			  },
		  })
//选择房屋用途后		  
	  	   form.on('select(houseUses)', function(data){
				if(data.value==197){
				  	var html="";
					html+='<option value="247">家电</option>'
					html+='<option value="248">家具</option>'
					html+='<option value="249">车库</option>'
					html+='<option value="250">小院</option>'
					html+='<option value="251">泳池</option>'
						$("#matchings").html(html)
				form.render('selectPT');
				}
				if(data.value==196){
					  var html="";
					html+='<option value="252">床</option>';
					html+='<option value="253">衣柜</option>'
					html+='<option value="254">书桌</option>';
					html+='<option value="256">空调</option>'
					html+='<option value="257">冰箱</option>';
					html+='<option value="258">电视</option>'
					html+='<option value="259">洗衣机</option>';
					html+='<option value="260">其他</option>'
						$("#matchings").html(html)
				form.render('selectPT');
				
				}
	   			  formSelects.value('selectPT', "${res_houses.matchingId}".split(",")); //初始化条件多选框
	  	   })
	  	  	
//初始化		   
		  if("${res_houses.levelType}"!=""){
			   $("#levelTypes").val('${res_houses.levelType}');
			   form.render('select');
		   };
		   if("${res_houses.payTypeId}"!=""){
			   $("#payTypeId").val('${res_houses.payTypeId}');
			   form.render('select');
		   };
		   
   });
   
</script>
</body>
</html>