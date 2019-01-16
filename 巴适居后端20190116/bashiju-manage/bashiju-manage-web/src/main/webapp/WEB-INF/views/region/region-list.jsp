<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>片区管理</title>

<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/css/animate.css" media="all">
<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
</head>
<body>
<input id="READING_MENU_ID" type="hidden" value="${menu_id}"/>
	<div class="">  
		<div class="layui-row">
			<div id="areaDiv" class="layui-col-md6">
				<fieldset class="layui-elem-field">
				  <div class="layui-field-box">
				  	<div class="layui-row">
				  		<div class="layui-inline">
					    <div class="layui-input-inline">
					    	<input type="hidden" id="codeHid">
					      <input type="text" id="queryArea" placeholder="行政区域" class="layui-input">
					    </div>
					    <button id="searchAreaBtn" class="layui-btn">查询</button>
					  </div>
				  	</div>
				  	<div class="layui-row">
				  		<table id="area" lay-filter="area"></table>
				  	</div>
				  </div>
				</fieldset>
				
			</div>
			<div id="regionDiv" class="layui-col-md6">
				<fieldset class="layui-elem-field">
				  <div class="layui-field-box">
				  	<div class="layui-row">
				  		<div class="layui-inline">
					    <div class="layui-input-inline">
					      <input type="text" id="regionKey" placeholder="片区名称" class="layui-input">
					    </div>
					    <button id="regionSearchBtn" class="layui-btn">查询</button>
					  </div>
				  	</div>
				  	<div class="layui-row">
				  		<table id="regionTable" ></table>
				  	</div>
				  </div>
				</fieldset>
			</div>
		</div>
		
	</div>
	 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=Tg1lQwb6XKbE8wgAflqsNimxI3myaCzD"></script>
	
 
 
 
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
<%@include file="/common/common.jsp" %>
<script>
var areaList=JSON.parse('${AreaListJson}');
var a= new AreaObject(areaList,function(code,name){
	$("#queryArea").val(name);
	if(code.length==6){
		code=code.substring(0,4);
	}
	$("#codeHid").val(code);
},2,$("#queryArea"));

$("#queryArea").on("change",function(){
	if($(this).val()==""){
		$("#codeHid").val("");
	}
});

$("#searchAreaBtn").on("click",function(){
	table.reload('areaTable',{where:{code:$("#codeHid").val()}});
});

$("#regionSearchBtn").on("click",function(){
	table.reload('regionTable',{url:'/manage/region/getRegionListByName',where:{name:$("#regionKey").val()}});
});

function showRegion(code,name){
	//$("#"+el).parent().parent().parent().parent().find("tr").css("background","");
	//$("#"+el).parent().parent().parent().css("background","#e2e2e2");
	 table.render({
		  id:'regionTable',
		    elem: '#regionTable'
		    ,height: 500
		    ,url: '/manage/region/getRegionList?menu_id=${menu_id}' //数据接口
		    ,where: {code:code}
		    ,method:'post'
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'name', title: '片区', width:120}
		      ,{field: 'longitude', title: '经度', width:120}
		      ,{field: 'latitude', title: '纬度', width:120} 
		      ,{field: 'id', title: '操作', width: 160
		    	  ,templet:function(d){
			    		 return '<span name="setRegionPoint" onClick="setMap(\''+d.longitude+'\',\''+d.latitude+'\',\''+d.id+'\',2,\''+name+'\')" style="color:#01AAED;cursor:pointer;">设置坐标</span>';
		    		  }}
		    ]]
		  });
}

function addRegion(code,name,longitude,latitude){

	var content='<form id="addRegionForm" class="layui-form" style="width:300px;margin:50px;">'
		+'<input type="hidden" name="areaCode" value="'+code+'">'
		+'<input type="hidden" name="areaName" value="'+name+'">'
		+'<div class="layui-form-item">'
		+'<div >'
		+'<input type="text" id="regionName" name="regionName" required  lay-verify="required|regionName" placeholder="请输入片区名称" autocomplete="off" class="layui-input">'
		+'</div>'
		+'</div>'
		+'<div class="layui-form-item">'
		+'<div >'
		+'<button  id="regionSave" lay-filter="regionSave"  lay-submit=""  class="layui-btn">保存</btn>'
		+'</div>'
		+'</div>'
		+'</form>';
	layerId=layer.open({
		  title:'新增片区',
		  type:1,
		  area:'400px',
		  content: content
		}); 
	

	//监听提交
	 form.on('submit(regionSave)', function(data){
		 $.ajax({
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            //contentType: 'application/json',
	            data: data.field,
	            url: "<%=request.getContextPath()%>/manage/region/addRegion" ,//url	                
	            //data:data,
	            success: function (result) {
	            	if(result.success){
	            		showRegion(data.field.areaCode,data.field.areaName);
	            		//layer.alert("保存成功！");
	            		layer.close(layerId);
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
var table ;
var layer;
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
	  id:'areaTable',
    elem: '#area'
    ,height: 500
    ,url: '/manage/region/getAreaList?menu_id=${menu_id}' //数据接口
    ,method:'post'
   //,data:data
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'name', title: '行政区', width:120
    	   ,templet:function(d){
    		  if(d.level>2){
    			  areaRowIndex++;
    			  return '<span  lay-event="detail" '+
    			  //onClick="showRegion(\''+d.code+'\',\''+d.name+'\',\'areaName_'+areaRowIndex+'\')" 
    			  'style="color:#01AAED;cursor:pointer;">'+d.name+'</span>';
    		  }
    		 	return d.name;
    		  } 
    }
      ,{field: 'longitude', title: '经度', width:120}
      ,{field: 'latitude', title: '纬度', width:120} 
      ,{field: 'code', title: '操作', width: 160
    	  ,templet:function(d){
    		  var menu=""
    		  if(d.level>2){  			  
    			  menu+='<span name="addRegion" lay-event="add" style="color:#01AAED;cursor:pointer;">新增片区</span>&nbsp;';
    			  menu+='<span name="setAreaPoint" lay-event="show" style="color:#01AAED;cursor:pointer;">设置坐标</span>';
    			  return menu;
    			  
    		  }
    		  //menu='<span>新增片区</span>&nbsp;';
    		   menu+='<span name="setAreaPoint" lay-event="show" style="color:#01AAED;cursor:pointer;">设置坐标</span>';
    		  return menu;
    		  //onClick="setMap(\''+d.longitude+'\',\''+d.latitude+'\',\''+d.code+'\',1,\''+d.name+'\')" 
    		  }}
    ]]
    ,done: function(res, curr, count){
    	if(count>0){
    		showRegion(res.data[0].code,res.data[0].name);
		}else{
			showRegion("","");
		}	    	
  }
  });
 
//监听工具条
  table.on('tool(area)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'detail'){
    	showRegion(data.code,data.name);
    } else if(layEvent === 'add'){
    	addRegion(data.code,data.name,data.longitude,data.latitude);
    } else if(layEvent === 'show'){
    	setMap(data.longitude,data.latitude,data.code,1,data.name);
    }
  });

}); 
  
 var layerId;//
 
function setMap(longitude,latitude,id,type,name){
	var contentMap='<div style="margin-left:5px;">'	
	+'<div class="layui-row">'
	+'<div id="container1" style="height:500px;width:590px;"></div>'
	+'</div>'
	+'<div class="layui-row">'
	+'<div class="layui-col-md2">'
	+'<button id="savePointBtn" onClick="savePoint(\''+id+'\','+type+')" class="layui-btn layui-disabled">保存</button>'
	+'</div>'
	+'</div>'
	+'</div>';
	//打开地图窗体
	layerId=layer.open({
	  title:'坐标设置',
	  type:1,
	  area:['600px','600px'],
	  content: contentMap
	}); 
	//地图显示配置
	showMap(longitude,latitude,name,type);
}
</script>
<!-- 百度地图相关js -->
<script type="text/javascript"> 
function showMap(jd,wd,name,type){
	//地图工具
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	
	var map = new BMap.Map("container1");// 创建地图实例  
	if(jd&&wd&&jd!='undefined'&&wd!='undefined'){
		var point = new BMap.Point(jd, wd);// 创建点坐标  	
		map.centerAndZoom(point, 12);// 初始化地图，设置中心点坐标和地图级别  
		var marker = new BMap.Marker(point); // 创建点
		map.addOverlay(marker); //添加点
		lng=jd;
		lat=wd;
	}else{
		if(type==2){
			map.centerAndZoom(name,12); 
		}
		if(type==1){
			map.centerAndZoom("中国",5); 
		}
	}
	
	map.addControl(top_left_control);        
	map.addControl(top_left_navigation);     
	map.addControl(top_right_navigation); 

	map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放

	
	
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		//alert("经度："+e.point.lng+",纬度："+e.point.lat);
		if(marker){
			map.removeOverlay(marker); 
		}		
		lng=e.point.lng;
		lat=e.point.lat;
		marker = new BMap.Marker(e.point);
		map.addOverlay(marker); //添加点
		$("#savePointBtn").removeClass("layui-disabled");
	});
}


var lng,lat;

function savePoint(id,type){
	if(type==1){
		saveAreaPoint(id);
	}
	if(type==2){
		saveRegionPoint(id);
	}
}

//修改片区坐标
function saveRegionPoint(id){
	//alert("经度："+lng+",纬度："+lat+",id:"+layerId);
	 $.ajax({
         type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         //contentType: 'application/json',
         data: {id:id,longitude:lng,latitude:lat},
         url: "<%=request.getContextPath()%>/manage/region/updateRegionPoint" ,//url	                
         //data:data,
         success: function (result) {
         	if(result.success){
         		table.reload('regionTable');
         		layer.close(layerId);
         		layer.msg("保存成功");
         	}else{
         		layer.alert(result.msg);
         	}
         },
         error : function() {	            	
             layer.alert("异常！");
         }
     });
}
//修改行政区划坐标
function saveAreaPoint(code){
	//alert("经度："+lng+",纬度："+lat+",id:"+layerId);
	 $.ajax({
         type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         //contentType: 'application/json',
         data: {code:code,longitude:lng,latitude:lat},
         url: "<%=request.getContextPath()%>/manage/region/updateAreaPoint" ,//url	                
         //data:data,
         success: function (result) {
         	if(result.success){
         		table.reload('areaTable');
         		layer.close(layerId);
         		layer.msg("保存成功");
         	}else{
         		layer.alert(result.msg);
         	}
         },
         error : function() {	            	
             layer.alert("异常！");
         }
     });
}
</script> 
</body>

</html>