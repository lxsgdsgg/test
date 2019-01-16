<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房源采集</title>
<style type="text/css">
.layui-form-select .layui-input {
    padding-right: 30px;
    cursor: pointer;
    width: 120px;
}

.range{
	display:inline-block !important;
	width:50px !important;
}
</style>
</head>
<body>
<form>
	<div class="toolBar layui-form payQuery">
	  	<div class="layui-inline">
			<select name="status" id="transactionTypeId">
			<option value="1">出售</option>
			<option value="2">出租</option>
			</select>
		</div>
		<div class="layui-inline">
				<div class="layui-unselect layui-form-select">
					<div class="layui-select-title">
					<input type="hidden" id="keyword">
					<input type="hidden" id="keywordType">
					<input type="text" placeholder="行政区小区" id=communityName  value=""  class="layui-input">
					</div>
					<dl id="keyDl" class="layui-anim layui-anim-upbit">
						<dd lay-value="1" class="">行政区:<span class="data"></span> </dd>
						<dd lay-value="2" class="">小区:<span class="data"></span></dd>
					</dl>
				</div>
			</div>
	  		<div class="layui-inline">
	  		    <lable>价格</lable>
	  		    <input type="text"  id="minPrice"  value=""  class="layui-input range">
	  		    -
				<input type="text"  id="manPrice"  value=""  class="layui-input range">
			</div>
			<div class="layui-inline">
				<lable>面积</lable>
	  		    <input type="text"  id="minSpace"  value=""  class="layui-input range">
	  		    -
				<input type="text"  id="maxSpace"  value=""  class="layui-input range">
			</div>
		  	<div class="layui-inline">
				<select name="lookStatus" id="lookStatus">
					<option value="">查看状态</option>
					<option value="1">已查看<option>
					<option value="0">未查看<option>
				</select>
			</div>
			<div class="layui-inline">
				<select name="inputStatus" id="inputStatus">
					<option value="">录入状态</option>
					<option value="1">已录入<option>
					<option value="0">未录入<option>
				</select>
			</div>
			<div class="layui-inline">
				<select name="checkStatus" id="checkStatus">
					<option value="">检查无效</option>
					<option value="1">是<option>
					<option value="0">否<option>
				</select>
			</div>

			
		 <%-- <c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}"> --%>
	  		<button class="layui-btn" type="button" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	<%-- </c:if> --%>
	  	<%-- <c:if test="${onlyBtnMenus.clear!='' && onlyBtnMenus.clear!=null}"> --%>
			<button class="layui-btn"  type="reset"  name="clear">清空</button>
	  	<%-- </c:if> --%>
	</div>
</form>
	<table class="layui-table" id="table_crawler" lay-filter="table_payRecord"></table>
	
<%@include file="/common/common.jsp" %>
<script>
layui.use(['table','laydate','form'], function(){
  var table = layui.table;
  var $=layui.$;
	var laydate = layui.laydate;//时间控件
	var form = layui.form;
	var endDate= laydate.render({
        elem: '#endTime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        	date.month = date.month-1;
            startDate.config.max=date;
        }
    });
    //日期范围
    var startDate=laydate.render({
        elem: '#beginTime',
        type: 'datetime',
        max:"2099-12-31",//设置一个默认最大值
        done: function(value, date){
        	date.month = date.month-1;
            endDate.config.min =date;
        }
    });
  
  
  table.render({
    elem: '#table_crawler'
    ,height:'full-100'
    ,url: '/house/queryCrawlerList'
    ,where: {condition:'{"transactionTypeId":'+$("#transactionTypeId").val()+'}'}
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'sourceName', title: '来源'}
      ,{field: 'recordTime', title: '采集时间',templet: '<div>{{ common.toDateString(d.recordTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{field: 'title', title: '房源标题'}
      ,{field: 'lookStatus', title: '查看状态',
    	  templet:function(d){
    		  if(d.lookStatus=='0') return '未查看';
    		  if(d.lookStatus=='1') return '已查看';
    		  return '';
    	  }}
      ,{field: 'inputStatus', title: '录入状态',
    	  templet:function(d){
    		  if(d.inputStatus=='0') return '未录入';
    		  if(d.inputStatus=='1') return '已录入';
    		  return '';
    	  }}
      ,{field: 'checkStatus', title: '检查无效',
    	  templet:function(d){
    		  if(d.checkStatus=='0') return '否';
    		  if(d.checkStatus=='1') return '是';
    		  return '';
    	  }}
      ,{field: 'communityName', title: '小区信息'}
      ,{field: 'price', title: '价格',templet:function(d){
    	  	if(d.transactionTypeId==1)return d.price/1000000+"万元";
    	  	if(d.transactionTypeId==2)return d.price/100+"元";
    	  	return "";
		}}
      ,{field: 'buildSpace', title: '面积'}
      ,{field: 'room', title: '户型'}
      ,{field: 'houseType', title: '房屋性质'}
      ,{field: 'orientation', title: '朝向'}
      ,{field: 'decoration', title: '装修'}
      ,{field: 'floor', title: '楼层'}
      ,{field: 'id', title: '操作',templet:function(d){ 	  
		 return '<a href="'+d.houseUrl+'" target="_blank" lay-event="info">详情</a>';			
		}} 
    ]]
  });
  
//监听工具条
  table.on('tool(table_crawler)', function(obj){
	  var data = obj.data //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	   if(layEvent === 'tongGuo'){
		   layer.confirm('您确认要审核吗?', function(index){
			   $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            data: {id:data.id,status:1},
		            url: "/fin/updatePayStatus" ,	                
		            //data:data,
		            success: function (result) {
		            	if(result.success){
		            		layer.close(index); 
		            		layer.msg("已审核");
		            		active.reload();
		            	}else{
		            		layer.alert(result.msg);
		            	}
		            },
		            error : function() {	            	
		                layer.alert("异常！");
		            }
		        });
			 });
	   }
	 
	  if(layEvent=='info'){
		 /*  layer.open({
			  title:'代收付款支出详情',
			  type: 1,
			  area: ['700px','500px'],
			  content: $('#detailDiv') 
			});
		  $("#detailDiv").removeClass("layui-hide"); */
	  }
  });
  
    var $ = layui.$, active = {
    reload: function(){
    var condition={};
    condition.transactionTypeId=1;
    var unit=1000000;
    if($("#transactionTypeId").val()=='2'){
    	condition.transactionTypeId=2;
    	unit=100;
    }
    if($("#minPrice").val()){
    	condition.minPrice=parseFloat($("#minPrice").val())*unit;
    }
    if($("#maxPrice").val()){
    	condition.maxPrice=parseFloat($("#maxPrice").val())*unit;
    }
    if($("#minSpace").val()){
    	condition.minSpace=parseFloat($("#minSpace").val());
    }
    if($("#maxSpace").val()){
    	condition.maxSpace=parseFloat($("#maxSpace").val());
    }
    if($("#communityName").val()){
    	condition.communityName=$("#communityName").val();
    }
    if($("#lookStatus").val()){
    	condition.lookStatus=0;
    	if($("#lookStatus").val()=='1'){
    		condition.lookStatus=1;
    	}
    }
    if($("#inputStatus").val()){
    	condition.lookStatus=0;
    	if($("#inputStatus").val()=='1')
    	condition.lookStatus=1;
    }
    if($("#checkStatus").val()){
    	condition.lookStatus=0;
    	if($("#checkStatus").val()=='1')
    	condition.lookStatus=1;
    }
     //执行重载
      table.reload('table_crawler', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {condition:JSON.stringify(condition)}
      });
    }
  };
    
    table.render({
        elem: '#table_detailRecord'
        //,height:'full-100'
        //,url: '/fin/queryPayRecordList'
        //,where: {transactionType:0}
       	//,method:'post'
       	,data:[]
       	,width:700
        ,page: true //开启分页
        ,cols: [[ //表头
          {field: 'tradeTime', title: '支出时间',templet: '<div>{{ common.toDateString(d.recordTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
          ,{field: 'recorderName', title: '支出金额'}
          ,{field: 'examineStatus', title: '状态'}        
          ,{field: 'auditorName', title: '录入人'}
          ,{field: 'buildingsName', title: '所在部门'}
          ,{field: 'reason', title: '驳回原因'}   
          ,{field: 'remark', title: '备注'}   
          ,{field: 'id', title: '操作',templet:function(d){
    		  return '';			
    		}} 
        ]]
      });
    
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  $('#keywordQuery').on('keyup', function(){
	    $(this).parent().next().css("display","block");
	    var val=$(this).val();
	    val=val.substring(val.indexOf(":")+1);
	    $(this).parent().next().find("span.data").text(val);
	  });
	  
	  $('#keyDl').find("dd").on('click', function(){
		    $(this).parent().css("display","none");
		    $('#keyword').val($(this).find("span.data").text());
		    $('#keywordType').val($(this).attr("lay-value"));
		    $('#keywordQuery').val($(this).text());
		  });
});
</script> 
</body>
</html>