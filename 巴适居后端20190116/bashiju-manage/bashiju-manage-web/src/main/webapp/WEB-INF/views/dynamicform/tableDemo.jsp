<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% request.setAttribute("staticfile_url", "http://src.bashiju.com/svn/source/src");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格实例</title>
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/bootstrap-3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="${staticfile_url}/common/frame/datatables/media/css/dataTables.bootstrap.min.css" >

<c:set var="menu_id" value="${menu_id}"/>
<style type="text/css">
.checkboxDiv{
	margin:20px;
}
.checkboxDiv ul{
	min-height:200px;
}
.checkboxDiv li{
	min-width:100px;
	
}
</style>
</head>
<body>
<input id="READING_MENU_ID" type="hidden" value="${menu_id}"/>
	<div >  
		<div  style="margin-top:20px;">
			<div class="col-md-6">
				<form class="form-inline">
				  <div class="form-group">
				    <label for="key">小区名称</label>
				    <input type="text" class="form-control" id="key" placeholder="小区名称">
				  </div>
				  <button id="searchBtn" type="button" class="btn btn-default">查询</button>
				</form>
			</div>
			<div class="col-md-6">
			<a href="#" id="hidColBtn" class="pull-right" style="margin-right:20px;">隐藏设置</a>
			</div>
		</div>
		<div>
			
			<div  class="col-md-12">
				<table id="example" class="table table-striped table-bordered table-hover" cellspacing="0" >
					<thead>
			            <tr class="bg-primary">
			                <th>名称</th>
			                <th>拼音检索</th>
			                <th>所属片区</th>
			                <th>所属行政区</th>
			                <th>均价</th>
			                <th>地址</th>
			              
			                <th></th>
			            </tr>
			        </thead>
				</table>
			</div>
		</div>
		
	</div>


<div class="checkboxDiv hidden">
 <ul class="list-inline">
	<li><input type="checkbox" value="name">名称</li>
	<li><input type="checkbox" value="pinyin">拼音检索</li>
	<li><input type="checkbox" value="reginName">所属片区</li>
	<li><input type="checkbox" value="areaName">所属行政区</li>
	<li><input type="checkbox" value="salePrice">均价</li>
	<li><input type="checkbox" value="address">地址</li>
	<li><input type="checkbox" value="id">id</li>
 </ul>		
 <button id="saveBtn" class="btn btn-primary">保存</button>	 
</div>
	
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/layer/layer.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" charset="utf-8" src="${staticfile_url}/common/frame/datatables/media/js/dataTables.bootstrap.min.js"></script> 

<script>
$(function () {
	 var language= {//国际语言转化
	            "oAria": {
	                "sSortAscending": " - click/return to sort ascending",
	                "sSortDescending": " - click/return to sort descending"
	            },
	            "sLengthMenu": "显示 _MENU_ 记录",
	            "sZeroRecords": "对不起，查询不到任何相关数据",
	            "sEmptyTable": "未有相关数据",
	            "sLoadingRecords": "正在加载数据-请等待...",
	            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
	            "sInfoEmpty": "当前显示0到0条，共0条记录",
	            "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
	            //"sProcessing": "<img src='../resources/user_share/row_details/select2-spinner.gif'/> 正在加载数据...",
	           // "sSearch": "模糊查询：",
	            //"sUrl": "",
	            //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
	            "oPaginate": {
	                "sFirst": "首页",
	                "sPrevious": " 上一页 ",
	                "sNext": " 下一页 ",
	                "sLast": " 尾页 "
	            }
	        };
    var table = $('#example').DataTable({

        "autoWidth": true,//自动宽度
        "lengthChange": false,
        "pageLength": 10,
        "searching": false,
        "ordering": false,
        "oLanguage":language,
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pagingType": "full_numbers",
        "ajax": {
        	"url":"<%=request.getContextPath()%>/manage/community/searchCommunityListDemo",
        	"type": "POST",
        	"data": function ( d ) {
        	      return $.extend( {}, d, {
        	        "name": $("#key").val()
        	      } );
        	    }
        },
        "columnDefs": [ {
            "targets": [0,1,2,3,4,5,6],
            "render": function ( data, type, full, meta ) {
            	if(!data){
            		return "";
            	}
                return data;
              }
          } ],
        "columns": [
            { "data": "name","name": "name" },
            { "data": "pinyin","name": "pinyin" },
            { "data": "reginName","name": "reginName" },
            { "data": "areaName","name": "areaName" },
            { "data": "salePrice","name": "salePrice" },
            { "data": "address","name": "address" },

            { "data": "id","name": "id","title":"主键" }
		]

    });

   

  
    //查询按钮事件
  $("#searchBtn").on("click",function(){
	  table.ajax.reload();
  });
   //复选框单机事件
  $(".checkboxDiv :checkbox").on("click",function(){
	  var col=table.column($(this).val()+':name');
	  col.visible(!col.visible());
  });
   //页面加载时初始化隐藏列
  $.ajax({
      type: "POST",//方法类型
      dataType: "json",//预期服务器返回的数据类型
      //contentType: 'application/json',
      data: {formName:"test"},
      url: "<%=request.getContextPath()%>/manage/getTableColHiddenList" ,//url	                
      //data:data,
      success: function (result) {
      	if(result.success){
      		$(".checkboxDiv :checkbox").attr("checked","true");
      		if(result.data!=null&&result.data.columns!=null){
      			var cols=result.data.columns;
      			
      			for(var i=0;i<cols.length;i++){
      				$(".checkboxDiv input[value="+cols[i]+"]").removeAttr("checked");
      				if(table.column(cols[i])!=null){
      					table.column(cols[i]+':name').visible(false);
      				}     			  
      		  	}
      		}
      	}
      },
      error : function() {	            	

      }
  });
   
   //保存自定义列表设置
  $("#saveBtn").on("click",function (){
	  var showCol=[];
	  var hidCol=[];
	  $(".checkboxDiv :checkbox").each(function(){
		  if($(this).is(':checked')) {
			  showCol.push($(this).val());
		    }else{
		    hidCol.push($(this).val());
		    }
	  });
	   for(var i=0;i<showCol.length;i++){
		  table.column(showCol[i]+':name').visible(true);
	  }
	  
	  for(var i=0;i<hidCol.length;i++){
		  table.column(hidCol[i]+':name').visible(false);
	  } 
	  var columns="";
	  if(hidCol.length>0){
		  columns=hidCol.join(",");
	  } 
	  layer.close(layerId);
	  $.ajax({
	      type: "POST",//方法类型
	      dataType: "json",//预期服务器返回的数据类型
	      //contentType: 'application/json',
	      data: {formName:"test",columns:columns},
	      url: "<%=request.getContextPath()%>/manage/setTableColHidden" ,//url	                
	      //data:data,
	      success: function (result) {

	      },
	      error : function() {	            	

	      }
	  });
  });
   
   //显示自定义列表
  $("#hidColBtn").on("click",function(){
	  layerId=layer.open({
		  type: 1,
		  title: "自定义列表", //不显示标题
		  area:['500px','400px'],
		  content: $('.checkboxDiv'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		});
	  $('.checkboxDiv').removeClass("hidden");
	  
  });
  
});
 var layerId; 
 

</script>

</body>
</html>