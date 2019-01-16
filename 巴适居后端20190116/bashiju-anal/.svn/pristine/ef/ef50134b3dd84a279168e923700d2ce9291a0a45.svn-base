<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
</head>
<body>
<table id="test" class="layui-table" lay-size="sm" lay-filter="test"></table>
<script type="text/javascript">
		
		var cols = ${thead};
		var data = ${data};
// 		var data = [];
		table.render({
			elem : '#test',
			height : 'full-100',
			page : false,
			size:'sm',
			cols : cols.rows[0].cells,
			data : data,
			toolbar: '<div>${titles}<div>', 
// 			totalRow:true,
			defaultToolbar: ['filter', 'print', 'exports'],
			done : function(res, curr, count) {
				$(".layui-table-body").find("td").each(function(index,element){
					if($(this).attr("data-field")>0)
						$(this).css("display","none");
				});
				$(".layui-table-total").find("td").each(function(index,element){
					if($(this).attr("data-field")>0)
						$(this).css("display","none");
				});
				merge(count,0,true,true);
			}
		});
		
		
// 		var $ = layui.$, active = {
// 	    reload: function(){
// 			var beginDate = $("#beginDate").val();
// 			var endDate = $("#endDate").val();
// 			var deptId=$("#deptCode").val();
// 	     //执行重载
// 	      table.reload('test', {
// 	        where: {
// 	        	beginDate: beginDate,
// 	        	endDate: endDate,
// 	        	deptId:deptId
// 	        }
// 	      });
// 	    }
// 	  };
		
</script>
</body>	
</html>