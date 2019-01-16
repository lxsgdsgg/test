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
	<link rel="stylesheet" type="text/css" href="${staticfile_url}/housing-platform/css/houseshow.css">
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
		.housetag{
			margin-left:10px;
			padding:4px;
			border:1px solid #e400ff;
		}
		.achievement{
			margin-left:10px;
		}
	</style>
	<script type="text/javascript">
	
	
	
		
	
			
			
			
			
	</script>
	<body class="">
			
			<div class="openHouseimgUpload">上传户型图</div>
		<script>
		layui.use(['element','form','table','layer','laydate','upload'], function(){
			  var element = layui.element;
			  form = layui.form;
			  var table = layui.table;
			  layer = layui.layer;
			  laydate = layui.laydate; 
			  upload=layui.upload;
			 
			  
			  var pictureType = $("select[name='pictureType']").val();
			  var options = $("select[name='pictureType']").find("option");
			  for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(pictureType == val)
					 var pictureName = $(options[i]).text();
			  }
			  var shhId = '${houseId}';//房源主键ID
			  var houseId = info.houseInfo.houseId;//房源编号
			  var filedata1 = {
					  shhId:shhId,
					  houseId:houseId,
					  pictureType:pictureType,
					  pictureName:encodeURIComponent(pictureName),
					  attachType:"hsPicture"
					  
			  }
			  //上传房源图片
			 var uploadvideo =  upload.render({
				  elem: '#uploadimg'
				  ,url: '<%=request.getContextPath()%>/house/uploadFile/'
				  ,auto: false //选择文件后不自动上传
				  ,bindAction: '.houseimgSureUpload' //指向一个按钮触发上传
				  ,accept:'images'
				  ,multiple:false
				  ,data:filedata1
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				    	
				    });
				  }
				  ,done: function(result){
				      //上传完毕回调
					  if(result.success){
		            		alert("成功！");
		            		 
		            	}else{
		            		alert(result.msg);
		            	}
				   }
			}); 
			  
			  var filedata2 = {
					  shhId:shhId,
					  houseId:houseId,
					  attachType:"hsVideo"
					  
			  }
			 //上传房源视频
			 var uploadvideo = upload.render({
				  elem: '#uploadvideo'
				  ,url: '<%=request.getContextPath()%>/house/uploadFile/'
				  ,auto: false //选择文件后不自动上传
				  ,bindAction: '.housevideoSureUpload' //指向一个按钮触发上传
				  ,accept:'file'
				  ,multiple:false
				  ,data:filedata2
				  ,choose: function(obj){
				    //将每次选择的文件追加到文件队列
				    var files = obj.pushFile();
				    
				    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
				    obj.preview(function(index, file, result){
				    	
				    });
				  }
				  ,done: function(result){
				      //上传完毕回调
					  if(result.success){
		            		alert("成功！");
		            		 
		            	}else{
		            		alert(result.msg);
		            	}
				   }
			}); 
			  
			 //下载
			 $(".download").click(function(){
				 var houseId = '${houseId}';//房源主键ID
				 var imageIds = $(".houseimg").attr("code");
				 window.open("<%=request.getContextPath()%>/house/downloadImages?houseId="+houseId+"&imageIds="+imageIds);
			 })
			 
			  //显示图片
			 $(".showhouseimg").click(function(){
				 var shhId = '${houseId}';//房源主键ID
         		$.post("<%=request.getContextPath()%>/house/getFile",{shhId:shhId,attachType:"01"},function(d){
         			console.log(d);
         			$(".img-show").empty();
         			for(var i=0;i<d.data.length;i++){
         				var div = '<div style="margin:10px;">'
         						+'<div>'+d.data[i].pictureName+'</div>'
         						+'<div><img class="houseimg" id="'+d.data[i].id+'" code="'+d.data[i].code+'" src="'+d.data[i].path+'"></div>'
         						+'<div class="layui-form-item">'
         						+'<label class="layui-form-label">图片类型</label>'
         					    +'<div class="layui-input-block">'
         					    	+'<select name="myPictureType" lay-filter="">'
         					        	+'<option value="1">房型图</option>'
         						  		+'<option value="2">卧室</option>'
         						  		+'<option value="3">客厅</option>'
         						  		+'<option value="4">阳台</option>'
         					        +'</select>'
         					    +'</div>'
         					    +'</div>'
         				         +'</div>'
         				$(".img-show").append(div);
         			}
         		})
			 })
			 
			 //显示视频
			 
			 $(".showhousevideo").click(function(){
				 var shhId = '${houseId}';//房源主键ID
				 $.post("<%=request.getContextPath()%>/house/getFile",{shhId:shhId,attachType:"03"},function(d){
	         			console.log(d);
	         			$(".video-show").empty();
	         			for(var i=0;i<d.data.length;i++){
	         				var div = '<div style="margin:10px;">'
	         						   +'<div><video class="housevideo" controls="controls" id="'+d.data[i].id+'" code="'+d.data[i].code+'" src="'+d.data[i].path+'"></div>'
	         						
	         				           +'</div>'
	         				$(".video-show").append(div);
	         			}
	         		})
			 })
			  
			  $(".openHouseimgUpload").click(function(){
				  openModel("上传房源图片",'500px','460px',$(".uploadImgModel"))
			  })
			  
			   $(".openHousevideoUpload").click(function(){
				  openModel("上传房源图片",'500px','460px',$(".uploadVideoModel"))
			  })
			  
			
		
	
			  
		});
		
		
		
		/*弹出框*/
		function openModel(title,width,height,divModel,param,myEvent) {
			
			layer.open({
				type: 1,
				title:title ,
				shade: [0],
				area: [width, height],
				offset: 'auto', //弹出位置
				btnAlign: 'r',
				content:divModel  //显示的url，no代表不显示滚动条
				
			});
		}
		
		
		
		//更改房源图片信息
		function updateHouseImg(){
			var shhId = '${houseId}';
			var id = $(".houseimg").attr("id");
			var pictureType = $("select[name='myPictureType']").val();
			  var options = $("select[name='myPictureType']").find("option");
			  for(var i=0;i<options.length;i++){
				 var val = $(options[i]).attr("value");
				 if(pictureType == val)
					 var pictureName = $(options[i]).text();
			  }
			//选中的图片的信息
			var imgInfos = [];
			var imgInfo = {
					id:id,
					isCover:1,
					pictureType:pictureType,
					pictureName:pictureName
			}
			imgInfos.push(imgInfo);
			var data = {
					shhId:shhId,	
					imgInfos:imgInfos
			}
			 $.ajax({
		            type: "POST",//方法类型
		            dataType: "json",//预期服务器返回的数据类型
		            contentType: 'application/json',
		            data:JSON.stringify(data),
		            url: "<%=request.getContextPath()%>/house/updateAttachment" ,//url	                
		            success: function (result) {
		            	if(result.success){
		            		alert("成功！");
		            	}else{
		            		alert(result.msg);
		            	}
		            },
		            error : function() {
		            	
		            },
		            beforeSend : function() {
		            	
		            }
		       }); 
		} 
		
		
		
		
		


		
		//设置全景看房
		function setOverallview(){
			var houseId = '${houseId}';
			var address = $(".overallviewaddress").val();
			//alert(address);
			 $.post("<%=request.getContextPath()%>/house/setOverallview",{houseId:houseId,address:address},function(data){
				 if(data.success){
					 alert("设置成功！");
				 }else{
					 alert(data.msg); 
				 }
			 });
		}
	
		




		
		
		</script>
		
		
	</body>
	
	
	
	<!-- 房源开盘 -->
	<div class="houseOpenModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">租售类型</label>
			    <div class="layui-input-block ">
			      <select name="transactionType" lay-filter="transactionType">
			        <option value="1">出租</option>
			        <option value="2">出售</option>
			        <option value="3">租售</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">租价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="rentprice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">租价单位</label>
			    <div class="layui-input-block ">
			      <select name="rentpriceunit" lay-filter="rentpriceunit">
			        <option value="1">元/月</option>
			        <option value="2">元/季度</option>
			        <option value="3">元/年</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">售价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="sellprice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('houseopen');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	
	<!-- 变更状态 -->
	<div class="changestatusModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">原状态</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ystatus" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">现状态</label>
			    <div class="layui-input-block ">
			      <select name="xstatus" lay-filter="xstatus">
			        <option value="4">他租</option>
			        <option value="5">他售</option>
			        <option value="6">未知</option>
			         <option value="7">自住</option>
			        <option value="8">出租中</option>
			        <option value="9">空置</option>
			        <option value="10">撤单</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">变更理由</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="reason" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('changestatus');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 申请封盘 -->
	<div class="houseCloseModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">封盘人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="fpdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="fpdepartment" id="fpdepartment" class="layui-input">
			    </div>
			</div> -->
			<div class="layui-form-item">
				<label class="layui-form-label">封盘类型</label>
			    <div class="layui-input-block ">
			      <select name="fptype" lay-filter="fptype">
			        <option value="1">封路径</option>
			        <option value="2">封电话</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">到期日期</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="fptime" class="layui-input fptime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">封盘原因</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="fpreason" class="layui-textarea fpreason"></textarea>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('houseclose');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 改变用途 -->
	<div class="changeUsageModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">房屋用途</label>
			    <div class="layui-input-block ">
			      <select name="xusage" lay-filter="xusage">
			        <option value="house">住宅</option>
			        <option value="villa">别墅</option>
			        <option value="apartment">公寓</option>
			        <option value="land">土地</option>
			      </select>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeStatus('changeUsage');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 举报 -->
	<div class="tipoffModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">举报类型</label>
			    <div class="layui-input-block ">
			      <select name="tipoffType" lay-filter="tipoffType">
			        <option value="1">照片错误</option>
			        <option value="2">信息失败</option>
			      </select>
			    </div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">举报原因</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="tipoffreason" class="layui-textarea tipoffreason"></textarea>
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="changeExamine('tipoff');">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出填写跟进 -->
	<div class="followModel" style="display:none;">
		<div class="layui-row">
			<div class="layui-col-md8">
				<form class="layui-form">
					<div class="layui-row">
						<div class="layui-col-md6">
							<div class="layui-form-item" >
							    <label class="layui-form-label">跟进方式</label>
							    <div class="layui-input-block">
							      <select name="followtype" lay-filter="followtype">
							        <option value="1">实勘</option>
							         <option value="2">谈委托</option>
							         <option value="3">店面面谈</option>
							          <option value="4">派单</option>
							      </select>
							    </div>
						  	</div>
						</div>
						<div class="layui-col-md6">
							<div class="layui-form-item">
							    <div class="layui-input-block ">
							      <input type="checkbox" name="" title="通知撤单" value="" class="cancelorder" filter="cancelorder">
							    </div>
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">跟进内容</label>
					    <div class="layui-input-block ">
					      <textarea placeholder="请输入内容" name="followcontent" class="layui-textarea followcontent"></textarea>
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒人</label>
					    <div class="layui-input-block ">
					      <input type="hidden" name=""  id="tsdepartmentcode" value="" class="layui-input">
					      <input type="text" name=""  id="tsdepartment" value="" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒时间</label>
					    <div class="layui-input-block ">
					      <input type="text" name=""  value="" class="layui-input remindtime">
					    </div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">提醒内容</label>
					    <div class="layui-input-block ">
					      <textarea placeholder="请输入内容" name="" class="layui-textarea remindcontent"></textarea>
					    </div>
					</div>
					<div class="layui-form-item">
					    <div class="layui-input-block">
					      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveFollow();">保存</button>
					      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
					      
					    </div>
					 </div>
				</form>
			</div>
			<div class="layui-col-md4">
				二维码
			</div>
		</div>
		
	</div>
	<!-- 弹出设置全景 -->
	<div class="setOverallview" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">全景地址</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="" class="layui-textarea overallviewaddress"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="setOverallview();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出修改业主-->
	<div class="updateOwnerModel" style="display:none;">
		<div class="layui-form" style="padding:10px">
			<input type="hidden" name="ownerid">
			<div class="layui-form-item">
				<label class="layui-form-label">业主</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerName" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			      <select name="nameTitle" lay-filter="aihao">
			        <option value="1">先生</option>
				  	<option value="2">女士</option>
				  	<option value="3">公司</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateOwner();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出新增电话-->
	<div class="addphoneModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ownerremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="addPhone();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 弹出修改电话-->
	<div class="updatephoneModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="upownerphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="upownerremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updatePhone();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 变更业主-->
	<div class="changeOwnerModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<div class="layui-form-item">
				<label class="layui-form-label">业主姓名</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newowner" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">业主性别</label>
			    <div class="layui-input-block">
			      <select name="newnameTitle" lay-filter="">
			        <option value="1">先生</option>
				  	<option value="2">女士</option>
				  	<option value="3">公司</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newphone" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="newremark" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveNewOwner();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!-- 修改低价-->
	<div class="updatepriceModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">出售低价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="minSellingPrice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出租低价</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="minRentPrice" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updatePrice();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--变更维护人-->
	<div class="updateOperatorModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">原维护人</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ywh" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">现维护人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="whdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="xwh" id="whdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateWh();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置钥匙-->
	<div class="setysModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">拿钥匙时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ystime" class="layui-input ystime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">钥匙编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="yscode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">钥匙收据</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="ysreceipt" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">得钥匙人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="ysdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" id="ysdepartment" name="yspeople" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateYs();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	
	<!--设置图片人-->
	<div class="setimgModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">图片人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="imgdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="imgpeople" id="imgdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateImg();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置独家-->
	<div class="setdjModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">独家编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djcode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">到期时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djtime" class="layui-input djtime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">独家总额</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="djmoney" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">独家人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="djdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="djpeople" id="djdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateDj();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--设置委托-->
	<div class="setwtModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
		
			<div class="layui-form-item">
				<label class="layui-form-label">委托编号</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="wtcode" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">委托时间</label>
			    <div class="layui-input-block ">
			      <input type="text" placeholder="请输入内容" name="wttime" class="layui-input wttime">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">委托人</label>
			    <div class="layui-input-block ">
			      <input type="hidden" id="wtdepartmentcode" class="layui-input">
			      <input type="text" placeholder="请输入内容" name="wtpeople" id="wtdepartment" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="updateWt();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--改价历史-->
	<div class="priceDetailModel" style="display:none;">
		<table id="pricedetail" lay-filter="" ></table>
	</div>
	
	<!--经纪人反馈-->
	<div class="feedbackModel" style="display:none;">
		<div class="layui-form" style="padding:10px;">
			<input type="hidden" class="feedbackId"/>
			<div class="layui-form-item">
				<label class="layui-form-label">反馈内容</label>
			    <div class="layui-input-block ">
			      <textarea placeholder="请输入内容" name="" class="layui-textarea feedback-content"></textarea>
			    </div>
			</div>
			<div class="layui-form-item saveFeedback">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveFeedback();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--客户档案-->
	<div class="customerArchivesModel" style="display:none;">
		房源：
		<table id="customerHouse" lay-filter="" ></table>
		求租：
		<table id="customerDemandRent" lay-filter="" ></table>
		求购：
		<table id="customerDemandBuy" lay-filter="" ></table>
	</div>
	<!--上传房源图片-->
	<div class="uploadImgModel" style="display:none;">
		<div class="layui-form">
			
			<div>
				<button type="button" class="layui-btn" id="uploadimg">
				  <i class="layui-icon">&#xe67c;</i>选择图片
				</button>
				<input class="layui-upload-file" type="file" name="file" multiple="false">
				<button type="button" class="layui-btn houseimgSureUpload" >
				  <i class="layui-icon"></i>上传
				</button>
			</div>
		</div>
		
	</div>
	
	<!--上传房源视频-->
	<div class="uploadVideoModel" style="display:none;">
		<div class="layui-form">
			
			<div>
				<button type="button" class="layui-btn" id="uploadvideo">
				  <i class="layui-icon">&#xe67c;</i>选择视频
				</button>
				<input class="layui-upload-file" type="file" name="file" multiple="false">
				<button type="button" class="layui-btn housevideoSureUpload" >
				  <i class="layui-icon"></i>上传
				</button>
			</div>
		</div>
		
	</div>
	<!--房源描述-->
	<div class="descriptionModel" style="display:none;">
		<div class="layui-form description-form" style="padding:10px;">
		
			<div class="layui-form-item saveDescriptionBtn">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn "  lay-filter="*" onclick="saveHouseDescription();">保存</button>
			      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
			      
			    </div>
			</div>
		</div>
	</div>
	<!--查看销控-->
	<div class="houseSellModel" style="display:none;">
		<!-- <div style="display:none;" class="unitinfo"></div> -->
		<div class="layui-row">
			<div class="layui-col-md3">
				<div>坐栋名称</div>
				<ul class="buildlist">
				
				</ul>
			</div>
			<div class="layui-col-md2">
				<div>单元名称名称</div>
				<ul class="unitlist">
				
				</ul>
			</div>
			<div class="layui-col-md7">
				
			</div>
		</div>
	</div>
</html>