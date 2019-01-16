<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/MultiSelection.css" >
	<link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/manage-platform/css/AreaSelect.css" media="all">
	
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/MultiSelection.js"></script>
	<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/manage-platform/js/AreaSelect.js"></script> 
	<title>经纪人详情管理</title>
</head>
<body>
	<div class="toolBar layui-form" id="searchArea">
		外网同步
		<div class="layui-inline">
			<div class="layui-input-inline">
				<select id="syncFlag" name="syncFlag">
					<option value="">--请选择--</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
		</div>
		热门经纪人
		<div class="layui-inline">
			<div class="layui-input-inline">
				<select id="hotAgentFlag" name="hotAgentFlag">
					<option value="">--请选择--</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
		</div>
		近30天带看记录
	   	<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input id="showedRdCnt" name="showedRdCnt" type="text" placeholder="请输入" class="layui-input" autocomplete="off">
	    	</div>
 		</div>
 		
 		<c:if test="${onlyBtnMenus.searchBtn != '' && onlyBtnMenus.searchBtn != null}">
		  	<button class="layui-btn" name="searchBtn" id="searchBtn" onclick="searchData()">查询</button>
	  	</c:if>
	  	<c:if test="${onlyBtnMenus.clearBtn!='' && onlyBtnMenus.clearBtn!=null}">
	  		<button class="layui-btn layui-btn-normal" onclick="clearData()">清空</button>
	  	</c:if>
	</div>
	<div class="excelTable">
		<table id="t_agent_detail_mgr" lay-filter="t_agent_detail_mgr" class="layui-table"></table>
	</div>
	
	<%@include file="/common/common.jsp" %>
	<script type="text/html" id="operationBar">
		{{# if(d.commenOnCommentsBtn !='' && d.commenOnCommentsBtn!=null){ }}
  			<a class="layui-btn layui-btn-xs" name="commenOnCommentsBtn" lay-event="commenOnCommentsBtn">查看留言评价</a>
		{{# } }}
	</script>
	
	<script type="text/javascript">
		var table;
		var form;
		var laydate;
		
		layui.use(['form','table'],function(){
			table = layui.table;
			form = layui.form;
			
			table.render({
				id: 't_agent_detail_mgr'
				,elem: '#t_agent_detail_mgr'
				,height: 'full-100'
				,url: 'queryAllAgentInfoPages'
				,method: 'post'
				,page: true
				,cols: [[
					{field : 'userId',width:100,title: '用户编号'}
					,{field : 'syncFlagName',width:100,title : '外网同步'}
					,{field : 'hotAgentFlagName',width:180,title: '热门经纪人'}
					,{field : 'showedRdCnt',width:140,title: '近30天带看记录'}
					,{field : 'histShowedRdCnt',width:140,title : '历史带看记录'}
					,{field : 'dealCnt',width:120,title : '近30天成交记录'}
					,{field : 'histDealCnt',width:100,title: '历史成交记录'}
					,{field : 'StarRating',width:120,title: '星级评分'}
					,{field : 'commentCnt',width:120,title: '客户历史评价记录'}
					,{field : 'seniority',width:100,title: '工龄'}
					,{field : 'addTime',width:120,title: '添加时间',templet: '<div>{{ common.toDateString(d.addTime,"yyyy-MM-dd")}}</div>'}
					,{fixed : 'right',title: '操作区域', width: 120,align:'center',toolbar: '#operationBar'}
				]]
			});
			
			table.on('tool(t_agent_detail_mgr)', function(obj) {
				//获得当前行数据
				var data = obj.data;
				//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				var layEvent = obj.event; 
				var depositId = data.id;
				if (layEvent === "commenOnCommentsBtn"){//显示成交明细列表
					var agentId = data.id;
					var title = ' <span style="color:#1E9FFF;font-size: 18px;">客户留言评价展示</span>';
					var option = {
				        type: 2 //此处以iframe举例
				        ,id:"leavingMsgAndcommentsIFrame"
				        ,title: title
				        ,area: ['90%', '90%']
				        ,shade: 0.5
				        ,resize:false//不允许拉伸
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: 'getLeavingMsgAndCommentsModel?agentId=' + agentId
				        ,zIndex: layer.zIndex //设置层级
				        ,success: function(layero){
				          layer.setTop(layero); //重点2
				        }
				    };
			    	common.windowLayerOpen(option);
				}
			});
		});
		
		//清空按钮事件
		function clearData(){
			$("#searchArea input").each(function (index, domEle) { 
				$(this).val("");
				if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
					$(this).val("0");
			 	}			
			});
			$("#searchArea select").each(function (index, domEle) { 
				$(this).val("");
				if($(this).attr("lay-verify")!=null&&"number"==$(this).attr("lay-verify")){
					$(this).val('');
			 	}
			});
		}
		/**
		 * 查询页面数据函数
		 */
		function searchData() {
			var showedRdCnt = $("#showedRdCnt").val();
			var hotAgentFlag = $("#hotAgentFlag").val();
			var syncFlag = $("#syncFlag").val();
			//执行重载
			table.reload('t_agent_detail_mgr', {
				page : {
					//重新从第 1 页开始
					curr : 1
				},
				where : {
					showedRdCnt : showedRdCnt,
					hotAgentFlag : hotAgentFlag,
					syncFlag : syncFlag
				}
			});
		}
	</script>
</body>
</html>