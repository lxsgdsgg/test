<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("staticfile_url", "/src");%>
<!DOCTYPE html >
<html>
	<head>
	<title><%=application.getInitParameter("systemtitle")%></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/layui/css/layui.css">
	<%-- <link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/deptSelect.css"> --%>
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/manage-platform/css/AreaSelect.css" media="all">
	<script type="text/javascript" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${staticfile_url}/common/frame/layui/layui.js"></script>
	<%-- <script type="text/javascript" src="${staticfile_url}/housing-platform/js/deptSelect.js"></script> --%>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${staticfile_url}/manage-platform/js/AreaSelect.js"></script>  
	</head>
	
	<style>
		.btn{
			
		}
		.hasStore{
			color:red;
		}
		
		.show-line{
			display: inline-block;
		}
		.blurCondition-ul.hidden{
			display:none;
		}
	</style>
	
	<body class="">
		<div class="layui-form" style="padding-top: 10px;">
		    <div class="layui-inline ">
		      <select name="status" lay-filter="status" class="status">
		        <option value="">收取状态</option>
		        <option value="01">待确认</option>
			  	<option value="02">已确认</option>
			  	<option value="03">驳回</option>
		      </select>
		    </div>
		    <div class="layui-inline ">
		      <select name="" lay-filter="" class="dealType">
		        <option value="">交易类型</option>
		        <option value="00">买卖</option>
			  	<option value="01">租赁</option>
			  	<option value="02">一手</option>
		      </select>
		    </div>
		    <div class="layui-inline ">
		      <select name="" lay-filter="" class="timeType">
		        <option value="dealTime">成交时间</option>
			  	<option value="actualPayTime">收取时间</option>
			  	<option value="sureTime">确认时间</option>
		      </select>
		    </div>
		    <div class="layui-inline ">
		     	<input type="text" name=""  value="" class="layui-input starttime">
		    </div>
		    <div class="layui-inline ">
		     	<input type="text" name=""  value="" class="layui-input endtime">
		    </div>
		    <div class="layui-inline ">
		    	<input type="hidden" id="deptCode">
		     	<input type="text" id="deptName" name="" placeholder="成交人" value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		    	<input type="hidden" id="areaCode">
		     	<input type="text" id="areaName" name="" placeholder="小区" value="" class="layui-input">
		    </div>
		    <div class="layui-inline ">
		     	<input type="text" id="blurCondition" name="" placeholder="模糊" value="" class="layui-input">
		     	<ul class="blurCondition-ul hidden">
		     		<li><span>成交编号</span><span></span></li>
		     		<li><span>小区</span><span></span></li>
		     		<li><span>门牌号</span><span></span></li>
		     		<li><span>票据编号</span><span></span></li>
		     	</ul>
		    </div>
		    <div class="layui-inline ">
		     	<button type="button" class="layui-btn searchData" name="">查询</button>
		    </div>
		    <div class="layui-inline ">
		     	<button type="button" class="layui-btn" name="">取消</button>
		    </div>
		</div>
		
		<div>
			<table id="commissionTable" lay-filter="commissionTable" ></table>
		</div>
		
		<script>
		function selectArea(code,name){
			$("#areaName").val(name);
			$("#areaCode").val(code);
		}
		$(function(){
			   //选区域
			   var areaList=JSON.parse('${communitList}');
			   var a= new AreaObject(areaList,selectArea,3,$("#areaName"));	
				
				
			   //选人员或部门
			   var depDatas = JSON.parse('${departmentList}');
				
				//选封盘人
				var ms=new MultiSelection (depDatas,function(code,name,level){
					if(code!="0"){
						$("#deptName").val(name);
						$("#deptCode").val(code);
					}	
					ms.hidden();
				},null,$("#deptName"),true);
		});
		
		layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  laydate = layui.laydate; 
			  
			  laydate.render({
				    elem: '.starttime' //指定元素
				  });
			  laydate.render({
				    elem: '.endtime' //指定元素
				  });
			  laydate.render({
				    elem: '.updateSuretime' //指定元素
				  });
			  
			  
			  var cols = [
			  		 {field:"dealId",title:"成交编号",width:'180'},
			 		 {field:"areaName",title:"区",width:'80'},
			 		 {field:"regionName",title:"片区",width:'100'},
			 		 {field:"communityName",title:"小区"},
			 		 {field:"buildingsName",title:"坐栋"},
			 		 {field:"buildingHouseName",title:"房号"},
			 		 {field:"billNo",title:"票据编号"},
			 		 {field:"agreementId",title:"合同号"},
			 		 {field:"moneyProjName",title:"费用项目"},
			 		 {field:"actualPrice",title:"金额"},
			 		 {field:"settlementTypeName",title:"结算方式"},
			 		 {field:"bankCardNo",title:"银行卡号"},
			 		 {field:"payerType",title:"缴费人"},
			 		 {field:"traderId",title:"成交人"},
			 		 {field:"status",title:"收取状态"},
			 		 {field:"reason",title:"驳回原因"},
			 		 {field:"dealTime",title:"成交时间"},
			 		 {field:"actualPayTime",title:"收取时间"},
			 		 {field:"sureTime",title:"确认时间"},
			 		 {fixed: 'right', width:180, align:'center', toolbar: '#barDemo'}
			  		];
		  	 var conditions = {moneyType:0};
			  table.render({
			    elem: '#commissionTable'
			    ,height: 500
			    ,url:"<%=request.getContextPath()%>/fin/getCommissionList" //数据接口
			    ,page: true //开启分页
			    ,cols: [cols]
		  		,method:'post'
		  		,cellMinWidth:100
		  		,where:{conditions:JSON.stringify(conditions)}
		  		,done: function(res, curr, count){
		  			
		  		}
			  });
			  
			 //查询
			 $(".searchData").click(function(){
				 var status = $(".status").val();
				 var dealType = $(".dealType").val(); 
				 var timeType = $(".timeType").val();
				 var starttime = $(".starttime").val();
				 var endtime = $(".endtime").val();
				 var areaCode = $("#areaCode").val();
				 var conditions = {
						 moneyType:0,
						 status:status,	
						 dealType:dealType,
						 timeType:timeType,
						 starttime:starttime,
						 endtime:endtime,
						 communityId:areaCode
				 };
				 //成交人（可选部门，可选人）
				 var deptCode = $("#deptCode").val();
				 var deptType = "人";
				 if(deptType=='人'){
					 var peopleId = deptCode;
					 conditions.peopleId=peopleId;
				 }else if(deptType=='部门'){
					 var deptId = deptCode;
					 conditions.deptId = deptId;
				 }
				 
				 //模糊查询条件
				 
				 /* if(blurType=="成交编号"){
					 conditions.dealId = dealId;
				 }else if(blurType=="小区"){
					 conditions.communityName = communityName;
				 }else if(blurType=="门牌号"){
					 conditions.buildingHouseName = buildingHouseName;
				 }else if(blurType=="票据编号"){
					 conditions.billNo = billNo;
				 } */
				 
				 table.render({
					    elem: '#commissionTable'
					    ,height: 500
					    ,url:"<%=request.getContextPath()%>/fin/getCommissionList" //数据接口
					    ,page: true //开启分页
					    ,cols: [cols]
				  		,method:'post'
				  		,cellMinWidth:100
				  		,where:{conditions:JSON.stringify(conditions)}
				  		,done: function(res, curr, count){
				  			
				  		}
					  });
				 
			 });
			 
			 //监听操作
			 table.on('tool(commissionTable)', function(obj){
				 	var data = obj.data;
				 	var id = data.id;
				    if(obj.event === 'sure'){
				    	layer.confirm('要确认吗?', {icon: 3, title:'提示'}, function(index){
				    		  $.post("<%=request.getContextPath()%>/fin/updateInfo",{id:id,type:'sureStatus'},function(d){
				    			  if(d.success){
					            		alert("成功！");
					            	}else{
					            		alert(d.msg);
					            	}
				    		  })
				    		  
				    		  layer.close(index);
				    	});
				    }else if(obj.event === 'reject'){
				    	$(".rejectId").val(id);
				    	layer.open({
							type: 1,
							title:'驳回' ,
							shade: [0],
							area: ['300px', '260px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.rejectModel')  //显示的url，no代表不显示滚动条
							
						});
				    }else if(obj.event === 'updatetime'){
				    	$(".updateId").val(id);
				    	layer.open({
							type: 1,
							title:'修改确认时间' ,
							shade: [0],
							area: ['300px', '260px'],
							offset: 'auto', //弹出位置
							btnAlign: 'r',
							content:$('.updateSuretimeModel')  //显示的url，no代表不显示滚动条
							
						});
				    }
			});
			  
		});
		
		function reject(){
			var id = $(".rejectId").val();
			var reason = $(".rejectreason").val();
			$.post("<%=request.getContextPath()%>/fin/updateInfo",{id:id,type:'reject',reason:reason},function(d){
  			  if(d.success){
	            		alert("成功！");
	            	}else{
	            		alert(d.msg);
	            	}
  		  })
		}
		function updateSuretime(){
			var id = $(".updateId").val();
			var sureTime = $(".updateSuretime").val();
			$.post("<%=request.getContextPath()%>/fin/updateInfo",{id:id,type:'updateSuretime',sureTime:sureTime},function(d){
  			  if(d.success){
	            		alert("成功！");
	            	}else{
	            		alert(d.msg);
	            	}
  		  })
		}
		</script>
		
		<script type="text/html" id="barDemo" >
          {{#  if(d.status == '01'){ }}
    			 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="sure">确认</a>
				 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="reject">驳回</a>
				 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="looklog">查看日志</a>
  		  {{#  } }}
         
  		  {{#  if(d.status == "02"){ }}
    			<a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="reject">驳回</a>
				<a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="updatetime">修改确认时间</a>
                <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="looklog">查看日志</a>
  		  {{#  } }}

  		 {{#  if(d.status == "03"){ }}
      		 <a class="layui-btn layui-btn-xs" style="color:#fff;" lay-event="looklog">查看日志</a>
	  	 {{#  } }}
        </script>
        
	</body>	
	<!-- 弹出驳回 -->
	<div class="rejectModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<input type="hidden" class="rejectId">
			<div class="layui-form-item">
				<label class="layui-form-label">驳回理由</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="reason" class="layui-input rejectreason">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="reject();">确定</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!-- 弹出修改确认时间 -->
	<div class="updateSuretimeModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<input type="hidden" class="updateId">
			<div class="layui-form-item">
				<label class="layui-form-label">确认时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="" class="layui-input updateSuretime">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateSuretime();">确定</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
</html>