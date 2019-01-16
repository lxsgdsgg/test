<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>登录日志</title>
</head>
<body>
		<div class="toolBar layui-form">
			<div class="layui-form-item">
			    <div class="layui-inline">
				      <label class="layui-form-label">关键字</label>
				      <div class="layui-input-inline">
				        <input type="text" class="layui-input" id="keyWord" placeholder="请输入关键字">
				      </div>
			    </div>
			<c:if test="${onlyBtnMenus.searchBtn!=''&& onlyBtnMenus.searchBtn!=null}">
		  		<button class="layui-btn" data-type="reload" id="searchBtn" name="searchBtn">查询</button>
		  	</c:if>
		  	<c:if test="${onlyBtnMenus.phoneBlacklistAdd!='' && onlyBtnMenus.phoneBlacklistAdd!=null}">
				<button class="layui-btn"  type="button" onclick="phoneBlacklist.add(this)" name="phoneBlacklistAdd">新增</button>
		  	</c:if>
			</div>
	</div>
	
	

	
<table class="layui-table" id="table_phoneBlacklist" lay-filter="table_phoneBlacklist"></table>

<%@include file="/common/common.jsp" %>
<script type="text/javascript">
phoneBlacklist = {
		add:function(){
			var title = '电话黑名单新增页面';
			var option = {
			        type: 2 //此处以iframe举例
			        ,id:"phoneBlacklistFrame"
			        ,title: title
			        ,area: ['450px', '320px']
			        ,shade: 0.5
			        ,resize:false//不允许拉伸
			        ,maxmin: false
			        ,offset: 'auto' 
			        ,content: 'enterphoneBlacklistAdd'
			        ,zIndex: layer.zIndex //重点1
			        ,success: function(layero){
			          layer.setTop(layero); //重点2
			          parent.layer.closeAll();
			        }
			      };
			common.windowLayerOpen(option);
		}
	}


layui.use(['table','laydate'], function(){	
	var table = layui.table;
	  table.render({
		    elem: '#table_phoneBlacklist'
		    ,method:"post"
		    ,url: 'queryPhoneBlacklistData'
		     ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'name', title: '姓名', width: 100,  sort: true, fixed: 'left'}
		      ,{field: 'mobile', title: '电话', sort: true}
		      ,{field: 'remark', title: 'IP地址', sort: true} 
		    ]]
		  });
	  var $ = layui.$, active = {
			    reload: function(){
			    
			     //执行重载
			      table.reload('table_phoneBlacklist', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			        	keyWord:$("#keyWord").val()
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