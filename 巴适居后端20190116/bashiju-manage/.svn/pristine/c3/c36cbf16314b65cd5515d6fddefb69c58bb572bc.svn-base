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
    	window.layer.close(load);
    },
    onload:function(xhr){
    	window.layer.close(load);
    },
    //拦截函数
    open:function(arg){
    	load= layer.load();
	 
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
		load= layer.load();
		url +=url.indexOf("?")>0?"&":"?"+"menu_id="+menu_id;
		window.location.href=url;
		window.event.returnValue=false;  
	},
	/**
	 * 使用top.location.href访问页面
	 */
	topLocationHref : function(url){
		load= layer.load();
		url +=url.indexOf("?")>0?"&":"?"+"menu_id="+menu_id;
		top.location.href=url;
	},
	/**
	 * 使用parent.location.href 访问页面
	 */
	parentLocationHref : function(url){
		load= layer.load();
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
/**
 * 删除数组中的对象
 * _obj 该值为要删除的对象，如果传入数字，则等价于arry.pop();
 */
Array.prototype.removeObj = function(_obj) {
	 //比较两个对象是否匹配
	 var compaire = function(x,y){
		// If both x and y are null or undefined and exactly the same 
		if (x === y) 
			return true;
		// If they are not strictly equal, they both need to be Objects 
		//They must have the exact same prototype chain,the closest we can do is
		//test the constructor. 
		if (x.constructor !== y.constructor) 
			return false;
		for ( var p in x) {
			//Inherited properties were tested using x.constructor === y.constructor
			if (x.hasOwnProperty(p)) {
				// Allows comparing x[ p ] and y[ p ] when set to undefined 
				if (!y.hasOwnProperty(p)) 
					return false;

				// If they have the same strict value or identity then they are equal 
				if (x[p] === y[p]) 
					continue;

				// Numbers, Strings, Functions, Booleans must be strictly equal 
				if (typeof (x[p]) !== "object") 
					return false;

				// Objects and Arrays must be tested recursively 
				if (!Object.equals(x[p], y[p])) 
					return false;
			}
		}

		for (p in y) {
			// allows x[ p ] to be set to undefined 
			if (y.hasOwnProperty(p) && !x.hasOwnProperty(p)) {
				return false;
			}
		}
		return true;
	}
	 
	var length = this.length;
	for (var i = 0; i < length; i++) {
		if (this[i] == _obj || compaire(this[i],_obj)) {
			if (i == 0) {
				this.shift(); //删除并返回数组的第一个元素
				return;
			} else if (i == length - 1) {
				this.pop(); //删除并返回数组的最后一个元素
				return;
			} else {
				this.splice(i, 1); //删除下标为i的元素
				return;
			}
		}
	}
	
}
</script>