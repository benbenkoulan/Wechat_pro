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
		<c:if test="${data != null}">
			<c:choose>
				<c:when test="${type == 2}">
					<dl class="tnd_with_top clearfix">
						<dt><img src="${static_path}/static/img/bank/${card_type}.png" ><span>${data.bankname}</span></dt>
						<dd>尾号:${data.cardno_end}<img src="${static_path}/static/img/with_ico1.png" ></dd>
		            </dl>
		            <div class="tnd_removebd none">解除绑定银行卡</div>
				</c:when>
				<c:otherwise>
					<dl class="tnd_with_top clearfix">
						<dt><img src="${static_path}/static/img/bank/${card_type}.png" ><span>${data.agreement_list[0].bank_name}</span></dt>
						<dd>尾号:${data.agreement_list[0].card_no}<img src="${static_path}/static/img/with_ico1.png" ></dd>
					</dl>
					<div class="tnd_removebd none">解除绑定银行卡</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<div class="tnd_account">
			<form id="czForm">
				<ul class="mywealth_nav tnd_add_bank" style="margin-top:0;">
					<li class="mywealth_nav_ban">
						<span>账户余额</span><b class="b_color b_color_red">￥${info.accountTotalMoney}</b>
					</li>
					<li class="mywealth_nav_ban">
						<span>充值金额</span>
						<input type="tel" onKeyPress="if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" name="money" id="money" class="tnd_add_txt">
					</li>
					<div class="errorTxt cardError1 none"></div>
				</ul>
				<input type="hidden" name="openId" value="${openId}" />
				<c:if test="${data != null}">
					<input type="hidden" name="cardtype" value="${data.cardtype}">
					<input type="hidden" name="bankname" value="${data.bankname}">
					<input type="hidden" name="id" value="${data.id}">
					<input type="hidden" name="uid" value="${data.uid}">
					<input type="hidden" name="cardno" value="${data.cardno}">
					<input type="hidden" name="orderid" value="${data.orderid}">
					<input type="hidden" name="no_agree" id="no_agree" value="${data.agreement_list[0].no_agree}" />
				</c:if>
			</form>
			<c:choose>
				<c:when test="${data != null}">
					<div class="form_btn" style="padding-top:2rem;"><a data-type="1" data-href="${ctx}/index/ajax/save_czhi2" class="btn">确定</a></div>
				</c:when>
				<c:otherwise>
					<div class="form_btn" style="padding-top:2rem;"><a data-type="2" data-href="${ctx}/index/ajax/save_czhi" class="btn">确定</a></div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 充值帮助 -->
	<div style="clear:both;padding-bottom:20px;">
		<p class="chargehelp">充值帮助<i class="icon iconfont icon-xiajiantou" data-val="1"></i></p>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="cardhelptable" style="display:none;">
			<tr>
				<th scope="col">支持银行</th>
				<th scope="col">单笔限额(元)</th>
				<th scope="col">单日限额(元)</th>
				<th scope="col">备注</th>
			</tr>
			<tr>
				<td>工商银行</td>
				<td>5万</td>
				<td>5万</td>
				<td>支付额度不能低于1元</td>
			</tr>
			<tr>
				<td>农业银行</td>
				<td>5万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>建设银行</td>
				<td>5万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>中国银行</td>
				<td>5万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>浦发银行</td>
				<td>5万</td>
				<td>5万</td>
				<td>支付额度不能低于1元</td>
			</tr>
			<tr>
				<td>民生银行</td>
				<td>10万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>广发银行</td>
				<td>10万</td>
				<td>10万</td>
				<td>支付额度不能低于1元</td>
			</tr>
			<tr>
				<td>中信银行</td>
				<td>10万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>兴业银行</td>
				<td>5万</td>
				<td>5万</td>
				<td></td>
			</tr>
			<tr>
				<td>光大银行</td>
				<td>10万</td>
				<td>10万</td>
				<td>支付额度不能低于1元</td>
			</tr>
			<tr>
				<td>平安银行</td>
				<td>10万</td>
				<td>10万</td>
				<td></td>
			</tr>
			<tr>
				<td>招商银行</td>
				<td>0.1万</td>
				<td>1万</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4">
				手机充值偶尔会有延时,一般30分钟内到账.如有更多疑问,请咨询客服热线:400-080-5055
				</td>
			</tr>
		</table>
	</div>
	<!-- 充值帮助 -->
	<!--弹出层-->
	<div class="layer none">
		<div class="layer_main position">
			<div class="certification clearfix box">
				<h2 style="padding-bottom:2rem;">是否解绑银行卡？</h2>
				<a href="javascript:;" class="close">取消</a>
				<a id="unbind" href="javascript:;">确认</a>
			</div>
		</div>
	</div>
	<!--弹出层-->
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.tool.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	$(function(){
		var type = '${type}',
			openId = '${openId}';
		$('.main').delegate('.tnd_with_top' , 'click', function(){
			if($('.tnd_removebd').hasClass('none')){
				$('.tnd_removebd').removeClass('none');
			}else{
				$('.tnd_removebd').addClass('none');
			}
		});
		$('.main').delegate('.tnd_removebd' , 'click', function(){
			$('.layer').show();
		});
		$('.close').bind('click', function(){
			$('.layer').hide();
		});
		$('#unbind').bind('click' , function(){
			var url,
				id = $("input[name='id']").val(),
				uid = $("input[name='uid']").val(),
				cardno = $("input[name='cardno']").val(),
				orderid = $("input[name='orderid']").val(),
				data = {openId:openId};
			if(type == 2){
				//联动优势
				url = '${ctx}/index/ajax/unbind_card_ld';
				data['orderid'] = orderid;
				data['id'] = id;
				data['uid'] = uid;
				data['cardno'] = cardno;
			} else {
				//易宝或者连连
				url = '${ctx}/index/ajax/unbind_llpay';
				data['no_agree'] = $("#no_agree").val();
			}
			jqueryTool.ajaxRequest(url , 'POST' ,data , function(data){
				alert(data.info);
				$('.layer').hide();
				if(data.status){
					location.reload();
				}
			},null);
		});
		$(".btn").bind("click", function(e){
			if(!Number($('#money').val())){
				$('.cardError1').show().html('充值金额不正确！');
				$('#money').focus();
				e.preventDefault();
				return;
			}else{
				$('.cardError1').hide();
			}
			var href = $(this).data('href'),
				type = $(this).data('type');	//有卡还是无卡
			jqueryTool.ajaxRequest(href , 'POST' , $('#czForm').serialize(), function(data){
				if(data.status == 1){
					if(data.info){
						location.href = data.url;
					} else {
						location.href = '${ctx}' + data.url;
					}
				}else if(data.status == 2){
					alert(data.info);
					location.href = '${ctx}' + data.url;
				}else{
					$(data.weizhi).show().html(data.info);
					alert(data.info);
				}
			},null);
		});
		$(".chargehelp").bind("click", function(){
			var v = $('.iconfont').attr('data-val');
			if (!v || v=='1') {
				$(".cardhelptable").show();
				$('.iconfont').addClass("icon-shangjiantou");
				$('.iconfont').removeClass("icon-xiajiantou");
				$('.iconfont').attr('data-val', '2');
			} else {
				$(".cardhelptable").hide();
				$('.iconfont').addClass("icon-xiajiantou");
				$('.iconfont').removeClass("icon-shangjiantou");
				$('.iconfont').attr('data-val', '1');
			}
		});
	});
</script>
</body>
</html>