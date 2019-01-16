<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二手房源租赁成交详情</title>
<style type="text/css">
	.border_div{
		border: 1px solid #DDD;
	    padding: 11px;
	    margin-top: 7px;
	    margin-right: 10px;
        overflow-y: auto;
	}
	.table_div{
/* 	    padding: 11px; */
	    margin-top: 7px;
	    margin-right: 10px;
	}
	.border_div div span{
	    color: #aeb3a3f2;
    	margin-right: 8px;
	}
</style>
</head>
<body>
	<div class="layui-tab layui-tab-card "lay-filter="deal_tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">基本信息</li>
	    <li>佣金记录</li>
	    <li>文件扫描件</li>
	    <li>跟进</li>
	    <li>日志</li>
	    <li>代收付款</li>
	  </ul>
	  <input type="hidden" id="dealId" value="${deal.id}"/>
	  <div class="layui-tab-content layui-container">
		<!--基本信息-->
	    <div class="layui-tab-item layui-show">
	    	<div>
	    		<span>房源地址：${deal.areaName}-${deal.regionName}-${deal.communityName}${deal.buildingsName}
	    		-${deal.buildingsUnitName}-${deal.buildingHouseName}</span>
	    		<button>修改成交信息</button>
	    	</div>
	    	<div class="layui-row">
	    		<div class="layui-col-xs8">
	    			<div class="border_div">
	    				<div>
    						<div class="layui-col-xs6"><span>户型  </span>${deal.room}室${deal.hall}厅${deal.toilet}卫</div>
    						<div class="layui-col-xs6"><span>面积  </span>${deal.buildSpace}平米</div>
    					</div>
    					<div>
    						<div class="layui-col-xs6"><span>楼层  </span>${deal.floorCount}/${deal.totalLayers}</div>
    						<div class="layui-col-xs6"><span>朝向  </span>${deal.orientation}</div>
    					</div>
    					<div>
    						<div class="layui-col-xs6"><span>用途  </span>${deal.houseUses}</div>
    						<div class="layui-col-xs6"><span>产权性质  </span>${deal.propertyType}</div>
    					</div>
    					<div>
    						<div class="layui-col-xs12"><span>建筑年代  </span>${deal.buildDates}</div>
    					</div>
    					<div>
    						<div class="layui-col-xs12"><span>补充条款  </span>${deal.supplementInfo}</div>
    					</div>
    					<hr>
    					<div>
    						<div class="layui-col-xs6"><span>成交价  </span>${deal.price/1000000}万元</div>
    						<div class="layui-col-xs6"><span>中介类应收佣金  </span>${deal.actualCommission/100} 元</div>
    					</div>
    					<div>
    						<div class="layui-col-xs6"><span>应收佣金  </span>${deal.commissionPrice/100} 元</div>
    						<div class="layui-col-xs6"><span>金融类应收佣金  </span>0 元</div>
    					</div>
	    			</div>
	    			<div class="table_div">
   						<table class="layui-table">
						  <thead>
						    <tr>
						      <th colspan="5">中介类分成信息        <button onclick="updateDivideInfos()">修改分成</button></th>
						    </tr> 
						  </thead>
						  <tbody>
						    <tr>
						      <td>分成人</td>
						      <td>分成缘由</td>
						      <td>分成比例</td>
						      <td>业绩</td>
						      <td>实应收</td>
						    </tr>
						    <c:forEach var="divid" items="${divids}">
							    <tr>
							      <td>${divid.dividerDeptName}-${divid.dividerName}</td>
							      <td>${divid.dividReason}</td>
							      <td>${divid.dividRate}</td>
							      <td>${divid.estimateProfit/100}</td>
							      <td>${divid.actualProfit/100}</td>
							    </tr>
						    </c:forEach>
						  </tbody>
						</table>
   					</div>
   					<div class="border_div">
   						跟进内容……
   					</div>
	    		</div>
	    		<div class="layui-col-xs4">
	    			<div class="border_div">
	    				<div><span>合同号 </span>${deal.agreementId}</div>
	    				<div><span>成交编号 </span>${deal.id}</div>
	    				<div><span>房源编号 </span>${deal.houseId}</div>
	    				<div><span>客源编号 </span>${deal.demandId}</div>
	    				<div><span>交易类型 </span>${dealType[deal.dealType]}</div>
	    				<div><span>成交人 </span>${deal.traderId}</div>
	    				<div><span>成交时间 </span>${deal.dealTime}</div>
	    				<div><span>权证人 </span>${deal.reference}</div>
	    			</div>
	    			<div class="border_div">
		    			<div>
		    				<div><span>甲方 </span>从合同中获取</div>
		    				<div><span>证件号码 </span>从合同中获取</div>
		    				<div><span>联系方式 </span>从合同中获取</div>
		    				<div><span>联系地址 </span>从合同中获取</div>
		    			</div>
		    			<hr>
		    			<div>
		    				<div><span>乙方 </span>从合同中获取</div>
		    				<div><span>证件号码 </span>从合同中获取</div>
		    				<div><span>联系方式 </span>从合同中获取</div>
		    				<div><span>联系地址 </span>从合同中获取</div>
		    			</div>
		    			<div><button>查看电话</button></div>
	    			</div>
	    			<div class="table_div">
	    				<table class="layui-table">
	    				<colgroup>
					    <col width="30%">
					    <col width="70%">
					    <col>
					  </colgroup>
						  <thead>
						    <tr>
						      <th colspan='2'>状态信息</th>
						    </tr> 
						  </thead>
						  <tbody>
						    <tr>
						      <td>分成</td>
						      <td>${deal.isDivide==1?"已分成":"未分成"}  <button onclick="updateDivideInfos()">修改分成</button></td>
						    </tr>
						     <tr>
						      <td>中介类佣金</td>
						      <td>${(deal.receivedPrice/deal.actualCommission)*100}%
						      (${deal.receivedPrice/100}元/${deal.actualCommission/100}元)</td>
						     </tr>
						     <tr>
						      <td>金融类佣金</td>
						      <td>还没做</td>
						     </tr>
						     <tr>
						      <td>结案</td>
						      <td>${deal.isFinishCase==1?"已结案":"未结案"}
<%-- 						      	<c:if test="${deal.isFinishCase==0}"> --%>
<!-- 						      		<button>结案</button> -->
<%-- 						      	</c:if> --%>
						      </td>
						     </tr>
						     <tr>
						      <td>违约</td>
						      <td>${deal.isBreach==0?"":"已违约"} 
						      	<c:if test="${deal.isBreach==0}">
						      		<button onclick="dealBreach('${deal.id}')">违约</button>
						      	</c:if>
						      	<c:if test="${deal.isBreach==1}">
						      		<button onclick="cancelDealBreach('${deal.id}')">取消违约</button>
						      	</c:if>
						      </td>
						     </tr>
						  </tbody>
						</table>
	    			</div>
	    		</div>
	    	</div>
	    </div>
	    <!--佣金记录-->
	    <div class="layui-tab-item">
			<label>收佣信息<button onclick="updateCommissionInfos()">调整计划</button></label>
			<table class="layui-table" lay-size="sm">
			  <thead>
			    <tr>
			      <th>收取状态</th>
			      <th>票据编号</th>
			      <th>费用类型</th>
			      <th>费用项目</th>
			      <th>费用金额</th>
			      <th>交费人</th>
			      <th>计划日期</th>
			      <th>收取时间</th>
			      <th>收取人</th>
			      <th>结算方式</th>
			      <th>银行卡号</th>
			      <th>备注</th>
			      <th></th>
			    </tr> 
			  </thead>
			  <tbody>
			    <c:forEach var="commission" items="${commissions}">
			    	<tr>
			    		<td>${commissionStatus[commission.status]}</td>
			    		<td>${commission.billNo}</td>
			    		<td>${commission.moneyType==0?'中介类费用':'金融类费用'}</td>
			    		<td>${commissionProj[commission.moneyProj]}</td>
			    		<td>${commission.price/100}</td>
			    		<td>${commission.payerType==0?'业主':'客户'}</td>
			    		<td>${commission.estimatePayTime}</td>
			    		<td>${commission.actualPayTime}</td>
			    		<td>${commission.payeeName}</td>
			    		<td>${settlementType[commission.settlementType]}</td>
			    		<td>${commission.bankCardNo}</td>
			    		<td>${commission.remark}</td>
			    		<td>
			    			<c:if test="${commission.status=='00'}">
			    				<button onclick="reciveCommission('${commission.id}','${commission.billNo}','${commission.moneyType}','${commission.moneyProj}','${commission.price}'
			    				,'${commission.payerType}','${commission.actualPayTime}','${commission.settlementType}','${commission.remark}')">收取佣金</button>
			    			</c:if>
			    			<c:if test="${commission.status=='01' || commission.status=='03'}">
			    				<button onclick="reciveCommission('${commission.id}','${commission.billNo}','${commission.moneyType}','${commission.moneyProj}','${commission.price}'
			    				,'${commission.payerType}','${commission.actualPayTime}','${commission.settlementType}','${commission.remark}')">修改</button>
			    				<button  onclick="cancelCommission('${commission.id}')">取消</button>
			    			</c:if>
			    		</td>
			    	</tr>
			    </c:forEach>
			  </tbody>
			</table>
		</div>
	    <!--文件扫描件-->
	    <div class="layui-tab-item" id="fileManage_div">
			<div>
				<span>类型</span>&nbsp;&nbsp;&nbsp; 
				<input type="hidden" id="search_attachType"/>
				<a href="javascript:void(0)" onclick="queryAttach()">不限</a>
				<a href="javascript:void(0)" onclick="queryAttach('00')">合同扫描件</a>
				<a href="javascript:void(0)" onclick="queryAttach('01')">业主材料扫描件</a>
				<a href="javascript:void(0)" onclick="queryAttach('02')">客户材料扫描件</a>
			</div>
			<div>
				<button class="layui-btn layui-btn-sm" onclick="openUploadImgWindow()"><i class="layui-icon">&#xe67c;</i>上传图片</button>
				<div class="layui-btn-group">
				  <button class="layui-btn layui-btn-sm">全选</button>
<!-- 				  <button class="layui-btn layui-btn-sm">单张下载</button> -->
				  <button class="layui-btn layui-btn-sm" onclick="downloadAttach()">下载</button>
				  <button class="layui-btn layui-btn-sm" onclick="deleteAttach()">删除</button>
				</div>
			</div>
			<table class="layui-table" id="attach_table"></table>
		</div>
	    <!--跟进-->
	    <div class="layui-tab-item" id="followRecord_div">
	    	<button onclick="addFollowRecord()">新增跟进记录</button>
			<table class="layui-table" lay-size="sm">
			  <thead>
			    <tr>
			      <th>跟进时间</th>
			      <th>跟进方式</th>
			      <th>跟进人</th>
			      <th>所在部门</th>
			      <th>跟进内容</th>
			      <th></th>
		      	</tr>	
			  </thead>      
			  <tbody>
			  	<c:forEach var="followRecord" items="${followRecords}">
			  		<tr>
			  			<td>${followRecord.followTime}</td>
			  			<td>${followRecord.followType}</td>
			  			<td>${followRecord.followerName}</td>
			  			<td>${followRecord.followerDeptName}</td>
			  			<td>${followRecord.content}</td>
			  			<td>
			  				<button onclick="delFollowRecord('${followRecord.id}')">删除</button>
			  			</td>
			  		</tr>
			  	</c:forEach>
			  </tbody>
	      	</table>
		</div>
	    <!--日志-->
	    <div class="layui-tab-item" id="log_div">6</div>
	    <!--代收付款-->
	    <div class="layui-tab-item" id="payRecord_div">
			<button onclick="addPayRecord()">新增</button>
			<table class="layui-table" lay-size="sm">
			  <thead>
			    <tr>
			      <th>状态</th>
			      <th>票据号</th>
			      <th>支付方</th>
			      <th>支付时间</th>
			      <th>代收付款类型</th>
			      <th>费用类型</th>
			      <th>金额</th>
			      <th>结算方式</th>
			      <th>银行卡号</th>
		      	</tr>	
			  </thead>      
			  <tbody>
			  	<c:forEach var="payRecord" items="${payRecords}">
			  		<tr>
			  			<td>${payRecord.examineStatus}</td>
			  			<td>${payRecord.billNo}</td>
			  			<td>${payRecord.payerType}</td>
			  			<td>${payRecord.tradeTime}</td>
			  			<td>${payRecord.transactionType}</td>
			  			<td>${payRecord.moneyTypeName}</td>
			  			<td>${payRecord.price/100}</td>
			  			<td>${payRecord.settlementTypeName}</td>
			  			<td>${payRecord.remark}</td>
			  		</tr>
			  	</c:forEach>
			  </tbody>
	      	</table>
		</div>
	  </div>
	</div>
	<div style="display: none;" class="layui-form" id="dealCommissionForm" style="margin-top: 20px;">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">票据号</label>
		      <div class="layui-input-inline">
		      	<input type="hidden" name="id" id="commision_id"/>
		        <input type="text" name="billNo" id="commision_billNo" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">费用类型</label>
		      <div class="layui-input-inline">
		        <select name="moneyType" id="commision_moneyType">
			        <option value="0">中介类费用</option>
			        <option value="1">金融类费用</option>
			      </select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">费用项目</label>
		      <div class="layui-input-inline">
		      	<input type="text" name="moneyProjName" value="从动态表单获取"/>
		        <select name="moneyProjId" id="commision_moneyProjId">
		        	<c:forEach var="commissionProj" items="${commissionProj}">
				        <option value="${commissionProj.key}">${commissionProj.value}</option>
		        	</c:forEach>
			      </select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">费用金额</label>
		      <div class="layui-input-inline">
		        <input type="number" name="price" id="commision_price"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>实际收取金额</label>
		      <div class="layui-input-inline">
		        <input type="number" name="actualPrice" id="commision_actualPrice" lay-verify="required" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>交佣方</label>
		      <div class="layui-input-inline">
		        <select name="payerType" id="commision_payerType" lay-verify="required">
			        <option value="0">业主</option>
			        <option value="1">客户</option>
			      </select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>收费时间</label>
		      <div class="layui-input-inline">
		        <input type="datetime" name="actualPayTime" id="commision_actualPayTime" lay-verify="required" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label"><font color="red">*</font>结算方式</label>
		      <div class="layui-input-inline">
		      	<input name = "settlementTypeName" value="动态表单配置"/>
		        <select name="settlementTypeId" id="commision_settlementTypeId">
		        	<c:forEach var="settlementType" items="${settlementType}">
				        <option value="${settlementType.key}">${settlementType.value}</option>
		        	</c:forEach>
			      </select>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">备注</label>
		      <div class="layui-input-inline">
		      	<textarea rows="5" cols="20" name="remark"  id="commision_remark"></textarea>
		      </div>
		    </div>
		</div>
		<div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="dealCommissionForm">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</div>
	
	<!-- 过户流程信息 -->
	<div style="display: none;" id="transferList">
		<c:forEach var="transfer" items="${transfers}">
			<button onclick="startUpTransfer('${transfer.id}')">${transfer.programmeName}</button>
		</c:forEach>
	</div>
	<!-- 过户流程详情信息 -->
	<div style="display: none;" id="transferDetails" class="layui-form">
		<table class="layui-table" id="transferDetails_table">
			<thead>
				<tr>
					<th>进度名称</th>
					<th>预计时间</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		 <button class="layui-btn" lay-submit lay-filter="transferDetailsForm">立即提交</button>
	</div>
	<!-- 修改分成信息 -->
	<div style="display: none;" id="divideUpdate" class="layui-form">
		<table class="layui-table" id="divideUpdate_table">
			<thead>
				<tr>
					<th>经纪人</th>
					<th>门店</th>
					<th>分成理由</th>
					<th>分成比例</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<button class="layui-btn" lay-submit lay-filter="divideUpdateForm">立即提交</button>
	</div>
	
	<!-- 调整佣金计划 -->
	<div style="display: none;" id="commissionUpdate" class="layui-form">
		<table class="layui-table" id="commissionUpdate_table">
			<thead>
				<tr>
					<th>费用类型</th>
					<th>费用项目</th>
					<th>缴费人</th>
					<th>金额</th>
					<th>预计交费日期</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<button class="layui-btn" lay-submit lay-filter="commissionUpdateForm">立即提交</button>
	</div>
	
	<!-- 上传图片窗口 -->
	<div style="display: none;" id="uploadImgWindow">
		<div class="layui-upload">
		  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button> 
		  <label>图片类型</label>
		  <select id="imageType">
		  	<option value="00">合同扫描件</option>
		  	<option value="01">业主材料扫描件</option>
		  	<option value="02">客户材料扫描件</option>
		  </select> 
		  <div class="layui-upload-list">
		    <table class="layui-table">
		      <thead>
		        <tr><th>文件名</th>
		        <th>大小</th>
		        <th>状态</th>
		        <th>操作</th>
		      </tr></thead>
		      <tbody id="demoList"></tbody>
		    </table>
		  </div>
		  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
		</div> 
	</div>
	
	<!-- 新增跟进记录 -->
	<div style="display: none;" id="followRecordWindow" class="layui-form">
		跟进方式id：<input type="text" name="followTypeId"/></br>
		跟进方式：<input type="text" name="followType"/></br>
		跟进内容：<input type="text" name="content"/></br>
		提醒人id：<input type="text" name="reminderId"/></br>
		提醒人：<input type="text" name="reminder"/></br>
		提醒时间：<input type="text" name="remindTime"/></br>
		提醒内容：<input type="text" name="remindContent"/></br>
		<button class="layui-btn" lay-submit lay-filter="followRecordForm">立即提交</button>
	</div>
	
	<!-- 新增代收款记录 -->
	<div style="display: none;" id="payRecordWindow" class="layui-form">
		票据号：<input type="text" name="billNo"/></br>
		代收付款：<input type="text" name="transactionType"/></br>
		费用类型Id：<input type="text" name="moneyTypeId"/></br>
		费用类型：<input type="text" name="moneyTypeName"/></br>
		金额：<input type="number" name="price"/></br>
		支付方：<input type="text" name="payerType"/></br>
		支付时间：<input type="text" name="tradeTime"/></br>
		结算方式Id：<input type="text" name="settlementTypeId"/></br>
		结算方式：<input type="text" name="settlementTypeName"/></br>
		备注：<input type="text" name="remark"/></br>
		<button class="layui-btn" lay-submit lay-filter="payRecordWindowForm">立即提交</button>
	</div>
<%@include file="/common/common.jsp" %>
<script>
function dealBreach(dealId){
	 var confirm = layer.confirm("您确定要违约吗？",function(){
		  $.ajax({
			  type:"post",
			  url:"dealBreach",
			  dataType:"json",
			  data:{dealId:dealId},
			  success:function(data){
				  if(data.success){
					  layer.alert("操作成功",function(){
						  location.reload();
					  });
				  }else
					  layer.alert("操作失败");
			  },
			  error:function(a,b,c){
				  layer.alert("操作异常，请刷新重试");
			  }
		  });
	  });
}
function cancelDealBreach(dealId){
	 var confirm = layer.confirm("您确定要取消违约吗？",function(){
		  $.ajax({
			  type:"post",
			  url:"cancelDealBreach",
			  dataType:"json",
			  data:{dealId:dealId},
			  success:function(data){
				  if(data.success){
					  layer.alert("操作成功",function(){
						  location.reload();
					  });
				  }else
					  layer.alert("操作失败");
			  },
			  error:function(a,b,c){
				  layer.alert("操作异常，请刷新重试");
			  }
		  });
	  });
}

function reciveCommission(id,billNo,moneyType,moneyProj,price,payerType,actualPayTime,settlementType,remark){
	var title = "收取佣金";
	var option = {
	        type: 1 //此处以iframe举例
	        ,id:"addReciveCommission"
	        ,title: title
	        ,area: ['600px', '450px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $("#dealCommissionForm")
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          $("#commision_id").val(id);
	          $("#commision_billNo").val(billNo);
	          $("#commision_moneyType").val(moneyType);
	          $("#commision_moneyProjId").val(moneyProj);
	          $("#commision_price").val(price/100);
	          $("#commision_actualPrice").val(price/100);
	          $("#commision_payerType").val(payerType);
	          $("#commision_actualPayTime").val(actualPayTime);
	          $("#commision_settlementTypeId").val(settlementType);
	          $("#commision_remark").val(remark);
// 	          form.render();
	        }
// 			,end: function(index, layero){ 
// 				layer.load();
//     			location.reload();
// 			}
	      };
	layer.open(option);
}

function cancelCommission(id){
	var confirm = layer.confirm("您确定要取消？",function(){
		$.ajax({
			type:'post',
			url:'cancelCommission',
			dataType:'json',
			data:{dealId:$("#dealId").val(),id:id},
			success:function(data){
				if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
			}, 
			error:function(a,b,c){
				layer.alert("操作异常，请联系管理员");
			}
		});
	});
}

function openTransferWindow(){
	var title = "启动过户";
	var option = {
	        type: 1 //此处以iframe举例
	        ,id:"openTransferWindow"
	        ,title: title
	        ,area: ['200px', '250px']
	        ,shade: 0.5
	        ,resize:false//不允许拉伸
	        ,maxmin: false
	        ,offset: 'auto' 
	        ,content: $("#transferList")
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
// 	          layer.setTop(layero); //重点2
	        }
	      };
	layer.open(option);
}

function startUpTransfer(confId){
	var confirm = layer.confirm("您确定要这么干？",function(){
// 		alert("可以的："+dealId);
		layer.close(confirm);
		$.ajax({
			type:'post',
			url:'getTransferProcessDetails',
			dataType:'json',
			data:{confId:confId},
			success:function(data){
				var html ='';
				for(var i=0;i<data.length;i++){
					var obj = data[i];
					html +='<tr>';
					html +='<td>'+obj.detailName+'</td>';
					html +='<td>';
					html +='<input type="hidden" name="scheduleName" value="'+obj.detailName+'"/>';
					html +='<input type="datetime" lay-verify="required" class="layui-input" name="estimateFinishTime"/>';
					html +='</td>';
					html +='</tr>';
				}
				$("#transferDetails_table").find("tbody").html(html);
				var title = "启动过户";
				var option = {
				        type: 1 //此处以iframe举例
				        ,id:"openTransferWindow1"
				        ,title: title
				        ,area: ['600px', '450px']
				        ,shade: 0.5
				        ,resize:false//不允许拉伸
				        ,maxmin: false
				        ,offset: 'auto' 
				        ,content: $("#transferDetails")
				        ,zIndex: layer.zIndex //重点1
				        ,success: function(layero){
//			 	          layer.setTop(layero); //重点2
				        }
				      };
				layer.open(option);
			}, 
			error:function(a,b,c){
				layer.alert("操作异常，请联系管理员");
			}
		});
	});
}

function updateDivideInfos(){
	$.ajax({
		type:"post",
		url : "queryDivideInfos",
		data : {dealId:$("#dealId").val()},
		dataType:"json",
		success : function(data){
			var html = "";
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				html +="<tr>";
				html +='<td><input type="hidden" name="id" value="'+obj.id+'"/><input type="text" name="dividerId" value="'+obj.dividerId+'"/></td>';
				html +='<td>'+obj.dividerDeptName+'</td>';
				html +='<td><input type="text" name ="dividReason" value="'+obj.dividReason+'"></td>';
				html +='<td><input type="number" name ="dividRate" value="'+obj.dividRate+'">';
				html +='</td>';
				html +="</tr>";
			}
			$("#divideUpdate_table").find("tbody").html(html);
			var title = "设置分成";
			var option = {
		        type: 1 //此处以iframe举例
		        ,id:"openTransferWindow1"
		        ,title: title
		        ,area: ['700px', '500px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $("#divideUpdate")
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		        }
		      };
			layer.open(option);
		},
		error : function(a,b,c){
			layer.alert("操作异常，请联系管理员");
		}
	});
	
}


function updateCommissionInfos(){
	$.ajax({
		type:"post",
		url : "queryCommissionInfos",
		data : {dealId:$("#dealId").val()},
		dataType:"json",
		success : function(data){
			var html = "";
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				html +="<tr>";
				html +='<td><input type="hidden" name="id" value="'+obj.id+'"/><input type="text" name="moneyType" value="'+obj.moneyType+'"/></td>';
				html +='<td><input type="hidden" name="moneyProjId" value="'+obj.moneyProjId+'"/><input type="text" name="moneyProjName" value="'+obj.moneyProjName+'"/></td>';
				html +='<td><input type="text" name ="payerType" value="'+obj.payerType+'"></td>';
				html +='<td><input type="number" name ="price" value="'+(obj.price/100)+'">';
				html +='</td>';
				html +='<td><input type="text" name ="estimatePayTime" value="'+obj.estimatePayTime+'"></td>';
				html +='<td><input type="text" name ="remark" value="'+obj.remark+'"></td>';
				html +="</tr>";
			}
			$("#commissionUpdate_table").find("tbody").html(html);
			var title = "设置分成";
			var option = {
		        type: 1 
		        ,id:"openCommissionWindow"
		        ,title: title
		        ,area: ['700px', '500px']
		        ,shade: 0.5
		        ,resize:false//不允许拉伸
		        ,maxmin: false
		        ,offset: 'auto' 
		        ,content: $("#commissionUpdate")
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		        }
		      };
			layer.open(option);
		},
		error : function(a,b,c){
			layer.alert("操作异常，请联系管理员");
		}
	});
	
}

function openUploadImgWindow(){
	var title = "上传文件图片";
	var option = {
        type: 1 
        ,id:"openUploadWindow"
        ,title: title
        ,area: ['700px', '500px']
        ,shade: 0.5
        ,resize:false//不允许拉伸
        ,maxmin: false
        ,offset: 'auto' 
        ,content: $("#uploadImgWindow")
        ,zIndex: layer.zIndex //重点1
        ,success: function(layero){
        }
      };
	layer.open(option);
}

function addFollowRecord(){
	var title = "新增跟进";
	var option = {
        type: 1 
        ,id:"openFollowRecordWindow"
        ,title: title
        ,area: ['700px', '500px']
        ,shade: 0.5
        ,resize:false//不允许拉伸
        ,maxmin: false
        ,offset: 'auto' 
        ,content: $("#followRecordWindow")
        ,zIndex: layer.zIndex //重点1
        ,success: function(layero){
        }
      };
	layer.open(option);
}

function addPayRecord(){
	var title = "新增代收付款";
	var option = {
        type: 1 
        ,id:"openPayRecordWindow"
        ,title: title
        ,area: ['700px', '500px']
        ,shade: 0.5
        ,resize:false//不允许拉伸
        ,maxmin: false
        ,offset: 'auto' 
        ,content: $("#payRecordWindow")
        ,zIndex: layer.zIndex //重点1
        ,success: function(layero){
        }
      };
	layer.open(option);
}

layui.use(['form','element','upload','table'], function(){
	  var $ = layui.jquery
	  ,element = layui.element //Tab的切换功能，切换事件监听等，需要依赖element模块
	  ,form = layui.form
	  ,upload = layui.upload
	  ,table = layui.table;
	  element.on('tab(deal_tab)', function(data){
// 	    alert(data.index);
		var ifame = "";
		var url = "";
		if(data.index==0 || data.index==1 || data.index==3)
			return false;
		
// 		$.ajax({
// 			type:"post",
// 			url:url,
// 			dataType:"html",
// 			data:{dealId:$("#dealId").val()},
// 			success:function(html){
// 				$("#"+ifame).html(html);
// 			},
// 			error:function(a,b,c){
// 				layer.alert("操作异常，请联系管理员");
// 			}
// 		});
	  });
	  
	//监听提交
	  form.on('submit(dealCommissionForm)', function(data){
	    $.ajax({
	    	type:"post",
	    	url:"reciverDealCommission",
	    	dataType:"json",
	    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(data.field)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	  });
	
	form.on('submit(transferDetailsForm)', function(data){
	    var tb = $("#transferDetails_table").get(0);
		  var rows = tb.rows;
		  var arr = [];
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input");
			  var obj = {};
			  for(var j=0;j<inputs.length;j++){
				  obj[inputs[j].name]=inputs[j].value;
			  }
			  arr.push(obj);
		  }
		  $.ajax({
	    	type:"post",
	    	url:"saveTransferProcess",
	    	dataType:"json",
	    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(arr)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	});
	
	form.on('submit(divideUpdateForm)', function(data){
	    var tb = $("#divideUpdate_table").get(0);
		var rateArr = $("#divideUpdate_table").find('input[name="dividRate"]');
		var rate100 = 0;
		for(var i=0;i<rateArr.length;i++){
			rate100 +=parseFloat(rateArr[i].value);
		}
		if(rate100!=100){
			layer.alert("分成比例不为100%");
			return false;
		}
		  var rows = tb.rows;
		  var arr = [];
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input");
			  var obj = {};
			  for(var j=0;j<inputs.length;j++){
				  obj[inputs[j].name]=inputs[j].value;
			  }
			  arr.push(obj);
		  }
		  $.ajax({
	    	type:"post",
	    	url:"saveDivideInfos",
	    	dataType:"json",
	    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(arr)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	});
	
	
	form.on('submit(commissionUpdateForm)', function(data){
	      var tb = $("#commissionUpdate_table").get(0);
		  var rows = tb.rows;
		  var arr = [];
		  for(var i=1;i<rows.length;i++){
			  var row = rows[i];
			  var inputs = $(row).find("input");
			  var obj = {};
			  for(var j=0;j<inputs.length;j++){
				  obj[inputs[j].name]=inputs[j].value;
			  }
			  arr.push(obj);
		  }
		  $.ajax({
	    	type:"post",
	    	url:"saveCommissionnfos",
	    	dataType:"json",
	    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(arr)},
	    	success:function(data){
	    		if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
	    	},
	    	error:function(a,b,c){
	    		layer.alert("操作异常，请稍后重试");
	    	}
	    });
	    return false;
	});
	
	form.on('submit(followRecordForm)', function(data){
// 		alert(JSON.stringify(data.field));
// 		return false;
	  $.ajax({
    	type:"post",
    	url:"savefollowRecord",
    	dataType:"json",
    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(data.field)},
    	success:function(data){
    		if(data.success){
    			layer.alert(data.msg,function(){
    				parent.location.reload();
	     			parent.layer.closeAll();
     			});
    		}else{
    			layer.alert(data.msg);
    		}
    	},
    	error:function(a,b,c){
    		layer.alert("操作异常，请稍后重试");
    	}
    });
    return false;
  });
	
	form.on('submit(payRecordWindowForm)', function(data){
// 		alert(JSON.stringify(data.field));
// 		return false;
	  $.ajax({
    	type:"post",
    	url:"savePayRecordd",
    	dataType:"json",
    	data:{dealId:$("#dealId").val(),jsonData:JSON.stringify(data.field)},
    	success:function(data){
    		if(data.success){
    			layer.alert(data.msg,function(){
    				parent.location.reload();
	     			parent.layer.closeAll();
     			});
    		}else{
    			layer.alert(data.msg);
    		}
    	},
    	error:function(a,b,c){
    		layer.alert("操作异常，请稍后重试");
    	}
    });
    return false;
  });
	   
	//多文件列表示例
  var demoListView = $('#demoList')
  ,uploadListIns = upload.render({
    elem: '#testList'
    ,url: 'uploadImgs'
    ,accept: 'file'
//     ,exts: 'png,jpg,gif,jpeg'
    ,multiple: true
    ,data:{dealId:$("#dealId").val(),imageType:$("#imageType").val()}
    ,auto: false
    ,bindAction: '#testListAction'
    ,choose: function(obj){   
      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
      //读取本地文件
      obj.preview(function(index, file, result){
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
          ,'<td>等待上传</td>'
          ,'<td>'
            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
          ,'</td>'
        ,'</tr>'].join(''));
        
        //单个重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        
        //删除
        tr.find('.demo-delete').on('click', function(){
          delete files[index]; //删除对应的文件
          tr.remove();
          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
        });
        
        demoListView.append(tr);
      });
    }
    ,done: function(res, index, upload){
      if(res.success){ //上传成功
        var tr = demoListView.find('tr#upload-'+ index)
        ,tds = tr.children();
        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
        tds.eq(3).html(''); //清空操作
        return delete this.files[index]; //删除文件队列已经上传成功的文件
      }
      this.error(index, upload);
    }
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
  });
	
  layui.laytpl = {
	  toImage : function(path){
		  return '<img src="'+path+'"/>';
	  }  
  };
	
  table.render({
    elem: '#attach_table'
    ,height:'full-100'
    ,url: 'getAttachData?dealId='+$("#dealId").val()
   	,method:'post'
    ,page: false //开启分页
    ,cols: [[ //表头
    	{type: 'checkbox'}
      ,{field: 'id', title: '主键',  sort: true, fixed: 'left'}
      ,{field: 'operator', title: '操作人'}
      ,{field: 'addTime', title: '新增日期', sort: true}
      ,{field: 'path', title: '文件', sort: true,templet: '<div>{{ layui.laytpl.toImage(d.path) }}</div>'}
    ]]
  	,done: function(res, curr, count){
   		$("[data-field='id']").css('display','none');
  	}
  });
  
  var active = {
    reload: function(){
		var attachType = $("#search_attachType").val();
     //执行重载
      table.reload('attach_table', {
       where: {
    	   attachType: attachType
        }
      });
    }
  };
  
  queryAttach = function(type){
	  $("#search_attachType").val(type);
	  active.reload();
  }
  
  downloadAttach = function(){
	  var checkStatus = table.checkStatus('attach_table');
	  var data = checkStatus.data;
	  if(data.length<=0){
		  layer.alert("请选择要下载的数据");
		  return false;
	  }
	  location.href = "downloadImage?jsonData="+JSON.stringify(data);
	  return false;
  }
  
  deleteAttach = function(){
	  var checkStatus = table.checkStatus('attach_table');
	  var data = checkStatus.data;
	  if(data.length<=0){
		  layer.alert("请选择要删除的数据");
		  return false;
	  }
	  layer.comfirm("您确定要删除？",function(){
		  $.ajax({
			 type:"post",
			 url:"deleteAttach",
			 data:{jsonData:JSON.stringify(data)},
			 dataType:"json",
			 success:function(data){
				 if(data.success){
		    			layer.alert(data.msg,function(){
		    				parent.location.reload();
			     			parent.layer.closeAll();
		     			});
		    		}else{
		    			layer.alert(data.msg);
		    		}
			 },
			 error:function(a,b,c){
				 layer.alert("操作异常，请联系管理员");
			 }
		  });
	  });
  }
  
  delFollowRecord = function(id){
	  layer.comfirm("您确定要删除？",function(){
		  $.ajax({
			  type:"post",
			  url:"delFollowRecord",
			  dataType:"json",
			  data:{id:id},
			  success:function(data){
				if(data.success){
	    			layer.alert(data.msg,function(){
	    				parent.location.reload();
		     			parent.layer.closeAll();
	     			});
	    		}else{
	    			layer.alert(data.msg);
	    		}
			  },
			  error:function(a,b,c){
				  layer.alert("操作异常，请联系管理员");
			  }
		  });
	  });
  }
	
});
</script> 
</body>
</html>