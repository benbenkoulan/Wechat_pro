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
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.tool.js"></script>
</head>
<body>
	<div class="main">
		<div class="tnd_account" style="padding-top:0;">
		<c:choose>
			<c:when test="${type ==1}">
				<form id="llForm" action="${ctx}/index/llpay" method="post">
					<ul class="mywealth_nav tnd_add_bank" style="margin-top:0;">
						<div class="errorTxt">为了您的资金安全，银行卡持卡人需与所填姓名一致</div>
						<li class="mywealth_nav_ban">
							<span>姓名</span><b class="b_color">${data.username}</b>
						</li>
						<li class="mywealth_nav_ban">
							<span>身份证号</span><b class="b_color">${data.strIdcard}</b>
						</li>
					</ul>
					<input type="hidden" value="${openId}" name="openId">
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn" data-type="1">提交</a></div>
				</form>
			</c:when>
			<c:otherwise>
				<form id="ybForm" action="${ctx}/index/llpay" method="post">
					<ul class="mywealth_nav tnd_add_bank" style="margin-top:0;">
						<div class="errorTxt">为了您的资金安全，银行卡持卡人需与所填姓名一致</div>
						<li class="mywealth_nav_ban">
							<span>姓名</span><b class="b_color">${data.username}</b>
						</li>
						<li class="mywealth_nav_ban">
							<span>身份证号</span><b class="b_color">${data.strIdcard}</b>
						</li>
					</ul>
					<input type="hidden" name="amount" value="${money}">
					<input type="hidden" name="orderid" value="${no_order}">
					<input type="hidden" name="transtime" value="${transtime}">
					<input type="hidden" name="currency" value="156">
					<input type="hidden" name="productcatalog" value="30">
					<input type="hidden" name="productname" value="翼龙贷充值">
					<input type="hidden" name="productdesc" value="翼龙贷充值">
					<input type="hidden" name="identitytype" value="2">
					<input type="hidden" name="identityid" value="${identityid}">
					<input type="hidden" name="terminaltype" value="0">
					<input type="hidden" name="terminalid" value="44-45-53-54-00-00">
					<input type="hidden" name="userip" value="${userip}">
					<input type="hidden" name="userua" value="${userua}">
					<input type="hidden" name="paytypes" value=""> 
					<input type="hidden" name="orderexpdate" value="60">
					<input type="hidden" name="idcardtype" value="01">
					<input type="hidden" name="idcard" value="${data.idcard}">
					<input type="hidden" name="owner" value="${data.username}">
					<input type="hidden" name="openId" value="${openId}">
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">提交</a></div>
				</form>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<script>
		$(function(){ 
			$(".btn").bind("click", function(){
				var formType = $(this).data('type');
				if(formType){
					$('#llForm').submit();
				} else {
					var data = jqueryTool.getFormJson('#ybForm');
					jqueryTool.ajaxJSONRequest('${ctx}/index/ajax/yibao_czhi' , 'POST' , data , function(data){
						if(data.status){
							location.href = data.url;
						} else {
							alert(data.info)
						}
					} , null);
				}
			});
			
			
		});
	</script>
</body>
</html>