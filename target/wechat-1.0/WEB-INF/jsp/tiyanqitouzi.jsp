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
<link rel="stylesheet" href="${static_path}/static/css/layer.css" />
</head>
<body>
	<!--弹窗层---开始-->
	<div class="alert_layer"></div>
	<div class="alert_box alert_box_yes">
		<p class="alert_title">提示</p>
		<p class="alert_content alert_content_center"></p>
		<p class="alert_attention"></p>
		<div class="alert_btn_container">
			<div class="alert_btn alert_btn_know">知道了</div>
		</div>
	</div>
	<!--弹窗层---结束-->
	<div class="main">
		<div class="bank_table box">
			<dl class="tnd_bank clearfix box">
				<dt>翼农计划<b>${data.title}</b></dt>
				<dd>
					<span>预期年化收益：<b id="nhsy" style="color:#333;">${data.strInterestrate}</b>
					</span>
					<span>合约期：<b id="hyq">${data.strPhases}</b></span>
				</dd>
			</dl>
			<div class="buy_main">
				<form id="myform" action="">
					<fmt:parseNumber value="${info.balance}"  var="balance"  type="NUMBER"/>
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" style="width:4.8rem;">可投金额(元):</td>
							<td><b class="ktNum" style="font-size:1.05rem;">
								${data.maxAmount - data.amount}
							</b></td>
						</tr>
						<tr>
							<td align="right">账户余额(元):</td>
							<td><b class="yeNum" style="font-size:1.05rem;">${info.balance}</b><a href="${ctx}/index/czhi?openId=${openId}" class="buy_a">充值</a></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="tnd_buy">
									<span class="sub_bg">-</span>
									<input type="text" name="invest" readonly="readonly" class="invest box" id="invest" value="10000.00" placeholder="请输入购买金额(元)">
									<span class="plus_bg">+</span>
								</div>
								<a href="javascript:;" class="buy_amax">最大金额</a>
								<div class="errorTxt investError none"></div>
							</td>
						</tr>
						<tr>
							<td align="right">预计收益(元):</td>
							<td><span id="shouyi">0</span></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="form_btn tnd_bank_btn">
				<a href="javascript:;" class="btn">同意协议并投资</a>
			</div>
			<div class="tnd_with_zi clearfix"><a href="${licaiUrl}?wid=${data.id}">《翼龙贷在线居间服务协议》</a></div>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
	$(function(){
		$(document).keypress(function(e) {
            if (e.which == 13 || e.keyCode == 13){
               return false;
            }
        });
		var wid = '${data.id}',
			calculateUtil = {
			invest : $('#invest'),	//投资
			interestRate : 0,
			contractDays : 0,
			init : function(){
				var Rate = eval($('#nhsy').text().replace('%', '') + $('#vipsy').text().replace('%', ''));
				this.interestRate = parseFloat(Rate / 100);
				this.contractDays = parseInt($('#hyq').text());
				this.calculateRate();
			},
			
			calculateRate : function(){
				var that = this;
				requestUtil.post('${ctx}/index/ajax/calculated_interest',{openId:'${openId}',
					amount:that.invest.val(),
					contractDays:that.contractDays,
					interestRate:that.interestRate},function(data){
						if(data.status == 1){
							$(data.weizhi).show().html(data.info);		
						}
					});
			}
		}
		calculateUtil.init();
		$(".btn").bind("click", function(){
			if($(this).hasClass('btn_bg')){
				return;
			}
			requestUtil.post('${ctx}/index/ajax/touzi_ajax',{openId:'${openId}',
				wid:wid,
				amount:$('#invest').val(),
				type:"1"},function(data){
					eloancnUtil.alert2(data.info);
					if(data.status == 1){
						eloancnUtil.setKnowRedirectUrl(data.url);
					}
				});
		});
		document.addEventListener('touchmove', function(e){e.preventDefault();}, false);
	});
</script>
</body>
</html>