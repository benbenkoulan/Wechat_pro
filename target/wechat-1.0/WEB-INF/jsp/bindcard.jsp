<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>	
<link rel="stylesheet" href="${static_path}/static/css/style.css" />
</head>
<body>
	<div class="main">
		<div class="tnd_account" style="padding-top:0;">
			<form id="CZForm" action="${czhiUrl}" method="post">
				<ul class="mywealth_nav tnd_add_bank" style="margin-top:0;">
					<div class="errorTxt">为了您的资金安全，银行卡持卡人需与所填姓名一致</div>
					<li class="mywealth_nav_ban">
						<span>姓名</span><b class="b_color">${data.username}</b>
					</li>
					<li class="mywealth_nav_ban">
						<span>身份证号</span><b class="b_color">${data.strIdcard}</b>
					</li>
					<li class="mywealth_nav_ban">
						<span>银行卡号</span><input type="tel" name="card" id="card" class="tnd_add_txt" placeholder="请输入银行卡号">
					</li>
					<div class="errorTxt cardError1 none"></div>
					<c:if test="${type == 2}">
					<li class="mywealth_nav_ban">
						<span>选择银行类型</span>
						<select name="bank_name" id="bank_name" style="width:200px;height:30px;border-radius:5px;">
							<option value="">---请选择---</option>
							<option value="CCB">建设银行</option>
							<option value="ABC">农业银行</option>
							<option value="BOC">中国银行</option>
							<option value="CITIC">中信银行</option>
							<option value="CEB">光大银行</option>
							<option value="CIB">兴业银行</option>
							<option value="SPDB">浦发银行</option>
							<option value="ICBC">工商银行</option>
							<!-- <option value="PSBC">邮储银行</option> -->
							<option value="CMBC">民生银行</option>
							<option value="GDB">广发银行</option>
							<option value="SPAB">平安银行</option>
						</select>
					</li>
					<div class="errorTxt bankErro1 none"></div>
				</c:if>
				</ul>
				<input type="hidden" name="amount" value="${money}">
				<input type="hidden" name="identityid" value="${identityid}">
				<input type="hidden" name="owner" value="${data.username}">
				<input type="hidden" name="idcard" value="${data.idcard}">
				<c:if test="${type == 3}">
					<input type="hidden" name="orderid" value="${no_order}">
					<input type="hidden" name="transtime" value="${transtime}">
					<input type="hidden" name="currency" value="156">
					<input type="hidden" name="productcatalog" value="30">
					<input type="hidden" name="productname" value="翼龙贷充值">
					<input type="hidden" name="productdesc" value="翼龙贷充值">
					<input type="hidden" name="identitytype" value="2">
					<input type="hidden" name="terminaltype" value="0">
					<input type="hidden" name="terminalid" value="44-45-53-54-00-00">
					<input type="hidden" name="userip" value="${userip}">
					<input type="hidden" name="userua" value="${userua}">
					<input type="hidden" name="paytypes" value=""> 
					<input type="hidden" name="orderexpdate" value="60">
					<input type="hidden" name="idcardtype" value="01">
				</c:if>
				<input type="hidden" name="openId" value="${openId}">
				<div class="form_btn" style="padding-top:2rem;">
					<a href="javascript:;" class="btn">提交</a>
				</div>
			</form>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.tool.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	$(function(){ 
		var czhiUrl = '${ctx}' + '${czhiUrl}',
			type = '${type}',
			openId = '${openId}';
		function yibao_success(data){
			if(data.status){
				location.href = data.url;
			} else {
				alert(data.info)
			}
		}
		$(document).keypress(function(e) {
            if (e.which == 13 || e.keyCode == 13){
               return false;
            }
        });
		function ldys_success(data){
			if(data.status){
				var tradeNo = data.info,//联动支付的订单号
					cardId = $("input[name='card']").val(), //银行卡号
					merCustId = $("input[name='identityid']").val(),//用户id
					cardHolder =$("input[name='owner']").val(),//持卡人姓名
					identityCode = $("input[name='idcard']").val();//身份证号
				var ldpay_data = {
						cardId:cardId,
						openId:openId,
						tradeNo:tradeNo,//商户API下单时，平台响应的trade_no
						merCustId:merCustId,//用户在商户端的唯一标识
						identityType:'IDENTITY_CARD',//仅支持身份证 IDENTITY_CARD
						identityCode:identityCode,//证件号
						cardHolder:cardHolder,//持卡人姓名
						payType:"DEBITCARD",//payType和gateId同传或不同传										
						gateId:$("#bank_name option:selected").val(),//payType和gateId同传或不同传,详见规范银行列表
						canModifyFlag:""//如传0，则不允许用户修改商户上传的支付要素
					}
				jqueryTool.ajaxRequest("${ctx}/index/ajax/liandong_pay" , 'POST' , ldpay_data , function(data){
					if(data.status == 1){
						location.href = data.url;
					}else{
						$(data.weizhi).show().html(data.info);
					}
				} , null);
			} else {
				$(data.weizhi).show().html(data.info);
			}
		}
		
		$(".btn").bind("click", function(){
			if(!/^[0-9]{10,23}$/.test($('#card').val())){
				$('.cardError1').show().html('请输入正确的银行卡号！');
				$('#card').focus();
				return;
			}else{ 
				$('.cardError1').hide();
			}
			var bank_name = $('#bank_name option:selected').val();
			if(!bank_name && (type == 2)){
				$('.bankErro1').show().html('请选择银行类型！');
				$('#bank_name').focus();
				return;
			} else {
				$('.bankErro1').hide();
			}
			if(type == 1){//连连支付
				$('#CZForm').submit();
			} else if(type == 3){//易宝支付
				var data = jqueryTool.getFormJson('#CZForm');
				jqueryTool.ajaxJSONRequest(czhiUrl , 'POST' , data , yibao_success , null);
			} else if(type == 2){//联动优势支付
				var amount = $("input[name='amount']").val(), 
					cardId = $("input[name='card']").val(), //银行卡号
					cardType = $("#bank_name option:selected").val();
				var data = {
					amount:amount, 
					cardType:cardType,//payType和gateId同传或不同传
					openId:openId,
					cardId:cardId
				};
				jqueryTool.ajaxRequest(czhiUrl , 'POST' , data , ldys_success , null);
			}
			
		});
	});
</script>
</body>
</html>