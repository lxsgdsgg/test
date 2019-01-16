<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- css load  -->  
 <link rel="stylesheet" type="text/css" href="${request.getContextPath()}/src/common/frame/layui/css/layui.css" media="all">
<!-- 该文件要在layui后引用 -->
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/frame/layui/layui.js"></script> 
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${request.getContextPath()}/src/common/js/ajaxhook.min.js"></script>
<script type="text/javascript">
var menu_id = '${menu_id}';
layui.use('layer', function(){
  layer = layui.layer;

hookAjax({
    //拦截回调
    onreadystatechange:function(xhr){
    	window.layer.close(dd);
    },
    onload:function(xhr){
    	window.layer.close(dd);
    },
    //拦截函数
    open:function(arg){
    	dd= layer.load();
	 
     arg[1]+=(arg[1].indexOf("?")>0?"&":"?")+"menu_id="+menu_id;
    },
    send:function(arg){
//      console.log("send called: %O",arg[0])
    }
});
/**
 * 公用方法
 */
common = {
	/**
	 * 使用window.location.href 访问页面
	 */
	windowLocationHref : function(url){
		layer.load();
		url +=url.indexOf("?")>0?"&":"?"+"menu_id="+menu_id;
		window.location.href=url;
		window.event.returnValue=false;  
	},
	/**
	 * 使用top.location.href访问页面
	 */
	topLocationHref : function(url){
		layer.load();
		url +=url.indexOf("?")>0?"&":"?"+"menu_id="+menu_id;
		top.location.href=url;
	},
	/**
	 * 使用parent.location.href 访问页面
	 */
	parentLocationHref : function(url){
		layer.load();
		url +=url.indexOf("?")>0?"&":"?"+"menu_id="+menu_id;
		parent.location.href=url;
	},
	/**
	*top.layer.open 弹窗
	*/
	topLayerOpen : function(option){
		option.content = option.content+(option.content.indexOf("?")>0?"&":"?")+"menu_id="+menu_id;
		top.layer.open(option);
	},
	/**
	*parent.layer.open 弹窗
	*/
	parentLayerOpen:function(option){
		option.content = option.content+(option.content.indexOf("?")>0?"&":"?")+"menu_id="+menu_id;
		parent.layer.open(option);
	},
	/**
	*window.layer.open 弹窗
	*/
	windowLayerOpen:function(option){
		option.content = option.content+(option.content.indexOf("?")>0?"&":"?")+"menu_id="+menu_id;
		window.layer.open(option);
	},
	/**
	*时间格式化 d表示要转换的时间，format要转换的格式
	*/
	toDateString : function(d,format){
		if(d !="" && d!=undefined){
			var date = new Date(d)
	// 		var date = new Date(d || new Date());
		  ,ymd = [
		    this.digit(date.getFullYear(), 4)
		    ,this.digit(date.getMonth() + 1)
		    ,this.digit(date.getDate())
		  ]
		  ,hms = [
		    this.digit(date.getHours())
		    ,this.digit(date.getMinutes())
		    ,this.digit(date.getSeconds())
		  ];
	
		  format = format || 'yyyy-MM-dd HH:mm:ss';
	
		  return format.replace(/yyyy/g, ymd[0])
		  .replace(/MM/g, ymd[1])
		  .replace(/dd/g, ymd[2])
		  .replace(/HH/g, hms[0])
		  .replace(/mm/g, hms[1])
		  .replace(/ss/g, hms[2]);
		}else{
			return "";
		}
	},
	digit : function(num, length, end){
	  var str = '';
	  num = String(num);
	  length = length || 2;
	  for(var i = num.length; i < length; i++){
	    str += '0';
	  }
	  return num < Math.pow(10, length) ? str + (num|0) : num;
	}
};
});

</script>