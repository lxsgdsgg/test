<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司管理</title>

</head>
<body>
	<div class="toolBar layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">公司名称</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">法人证件号码</label>
				<div class="layui-input-block">
					<input type="text" name="doccode" id="doccode" placeholder="法人证件号码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">法代名称</label>
				<div class="layui-input-block">
					<input type="text" name="legrepname" id="legrepname" placeholder="模糊查询" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">法代手机号</label>
				<div class="layui-input-block">
					<input type="text" name="legdocmobile" id="legdocmobile" placeholder="法人代表手机号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<c:if test="${onlyBtnMenus.searchBtn!='' && onlyBtnMenus.searchBtn!=null}">
			  		<button class="layui-btn" name="searchBtn" data-type="reload" id="searchBtn">查询</button>
			  	</c:if>
			  	<c:if test="${onlyBtnMenus.addCompany!='' && onlyBtnMenus.addCompany!=null}">
			  		<button class="layui-btn" name="addCompany" onclick="company.add()">新增</button>
			  	</c:if>
		  	</div>
		</div>
	</div>
	<table class="layui-table" id="table_company" lay-filter="table_company"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
{{# if(d.companyEdit !='' && d.companyEdit!=null){ }}
  <a class="layui-btn layui-btn-xs" name="companyEdit" lay-event="edit">编辑</a>
{{# } }}
{{# if(d.companyDel !='' && d.companyDel!=null){ }}
  <a class="layui-btn layui-btn-danger layui-btn-xs" name="companyDel" lay-event="del">删除</a>
{{# } }}
</script>
<script>

company = {
	add:function(){
		var title = '公司新增页面';
		var option = {
		        type: 2 //此处以iframe举例
		        ,id:"companyEditIFrame"
		        ,title: title
		        ,area: ['700px', '650px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: 'companyDetailPage'
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
// 				,end: function(index, layero){ 
// 					layer.load();
// 	    			location.reload();
// 				}
		      };
		common.windowLayerOpen(option);
	}
}

layui.use('table', function(){
  var table = layui.table;
  
  layui.laytpl={
		 //转换菜单等级描述
		todoctypeStr : function(doctype){
			debugger;
		  var dd = ${doctypeJson};
			if('${doctypeJson}'!='')
				return dd[doctype];
			else
				return doctype;
		}
  		//转换功能类型描述
  		,toLegdoctypeStr : function(legdoctype){
  			var dd = ${legdoctypeJson};
			if('${legdoctypeJson}'!='')
				return dd[legdoctype];
			else
				return legdoctype;
  		}
		
  }
  
  table.render({
    elem: '#table_company'
    ,height:'full-100'
    ,url: 'getCompanyData'
   	,method:'post'
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
      ,{field: 'name', title: '公司名称', sort: true}
      ,{field: 'Doctype', title: '证件类型', sort: true,templet: '<div>{{ layui.laytpl.todoctypeStr(d.Doctype) }}</div>'}
      ,{field: 'doccode', title: '证件号码'}
      ,{field: 'legrepname', title: '法代名称'}
      ,{field: 'contname', title: '联系人'}
      ,{field: 'contphone', title: '联系电话'}
      ,{field: 'status', title: '状态'}
      ,{field: 'operator', title: '操作人'}
      ,{field: 'addTime', title: '新增时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
      ,{ title: '操作',width: "10%", align:'center', toolbar: '#operationBar'}
    ]]
  	,done: function(res, curr, count){
  		$("[data-field='id']").css('display','none');
  	}
  });
  
    var $ = layui.$, active = {
    reload: function(){
		var name = $("#name").val();
		var doccode = $("#doccode").val();
		var legrepname = $("#legrepname").val();
		var legdocmobile = $("#legdocmobile").val();
     //执行重载
      table.reload('table_company', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
        	name: name,
        	doccode: doccode,
        	legrepname: legrepname,
        	legdocmobile: legdocmobile
        }
      });
    }
  };
  
//监听工具条
  table.on('tool(table_company)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
   	var companyId = data.id;
    var regaddrcity=data.regaddrcity;
 
    if(layEvent === 'del'){ //删除
      layer.confirm('确定要删除该公司吗？', function(index){
    	  $.ajax({
    		  type:"post",
    		  url:"delCompany",
    		  dataType:"json",
    		  data:{companyId:companyId},
    		  success:function(data){
    			  if(data.success){
    				  layer.msg('删除成功', {icon: 1,time:500});
    				  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
    			  }else{
    				  layer.alert(data.msg);
    			  }
    		  },
    		  error:function(a,b,c){
    			  layer.alert("操作异常，请稍后重试");
    		  }
    	  });
        layer.close(index);
        //向服务端发送删除指令
      });
    } else if(layEvent === 'edit'){ //编辑
    	var title = '公司编辑页面 <span style="margin-left: 24%;color: orange;font-size: 18px;">公司名称：【'+data.name+'】</span>';
    	var option = {
    	        type: 2 //此处以iframe举例
    	        ,id:"companyEditIFrame"
    	        ,title: title
    	        ,area: ['700px', '650px']
    	        ,shade: 0.5
    	        ,resize:false//不允许拉伸
    	        ,maxmin: false
    	        ,offset: 'auto' 
    	        ,content: 'companyDetailPage?regaddrcity='+regaddrcity+'&companyId='+companyId
    	        ,zIndex: layer.zIndex //重点1
    	        ,success: function(layero){
    	          layer.setTop(layero); //重点2
    	        }
//     			,end: function(index, layero){ 
//     				layer.load();
//         			location.reload();
//     			}
    	      };
    	common.windowLayerOpen(option);
    }
  });
  
  $('#searchBtn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
});
</script> 
</body>
</html>