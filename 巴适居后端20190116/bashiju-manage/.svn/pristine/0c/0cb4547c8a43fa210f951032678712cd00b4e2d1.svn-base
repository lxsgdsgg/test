<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="toolBar layui-form">
	  	<div class="layui-inline">
	    	<input class="layui-input"  placeholder="关键字" id="keyword" autocomplete="off">
	  	</div>
	  	<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
	  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.blackListAdd!='' && onlyBtnMenus.blackListAdd!=null}">
			<button class="layui-btn"  type="button" onclick="blackList.add(this)" name="blackListAdd" >新增</button>
	  	</c:if>
	  	
	</div>
<table class="layui-table" id="table_BlackList" lay-filter="table_BlackList"></table>
<%@include file="/common/common.jsp" %>
<script type="text/html" id="operationBar">
  {{# if(d.blackListEdit !='' && d.blackListEdit !=null){ }}
	  <a class="layui-btn layui-btn-xs" lay-event="edit" name="blackListEdit">编辑</a>
  {{# } }}
  {{# if(d.blackListDel !='' && d.blackListDel !=null){ }}
	   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" name="blackListDel">移除</a>
  {{# } }}	
</script>
<script type="text/javascript">
blackList = {
		add:function(){
			var title = '黑名单新增页面';
			var option = {
			        type: 2 //此处以iframe举例
			        ,id:"blackListAdd"
			        ,title: title
			        ,area: ['400px', '350px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterBlackListDetailPage'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			          parent.layer.closeAll();
			        }
			      };
			common.windowLayerOpen(option);
		}
	}
layui.use('table', function(){
	  var table = layui.table;
	  //第一个实例
	  table.render({
	    elem: '#table_BlackList'
	    ,method:"post"
	    ,url: 'queryBlackListData'
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'companyName', title: '中介名称'}
	      ,{field: 'legalPhone', title:'中介电话', sort: true} 
	      ,{field: 'note', title: '违规原因', sort: true}
	    ]]
	  });
	  
	  
	   var $ = layui.$, active = {
			    reload: function(){
			    //执行重载
			      table.reload('table_BlackList', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	keyword: $("#keyword").val()
			        }
			      });
			    }
	   
	  		
			  };
	  
	   $('#searchBtn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
})
</script>
</body>
</html>