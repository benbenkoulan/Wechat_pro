<%@ include file="/common.jsp"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>  
<!DOCTYPE HTML>
<html>
	<head>
	  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>我的账户</title>
	    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
		<meta name="format-detection" content="telephone=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-touch-fullscreen" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!-- 
			<meta http-equiv="Cache-Control" content="max-age=7200">
			<meta http-equiv="cache-control" content="no-cache">    
			<meta http-equiv="Expires" content="Sun, 31 Jan 2016 00:00:00 GMT"> 
		-->
		<link rel="stylesheet" href="${static_path}/static/css/style.css" />
		<script src="${static_path}/static/js/resize.js"></script>
		<link rel="stylesheet" type="text/css" href="${static_path}/static/css/layer.css" />
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
	<div class="mywealth">
		<dl class="mywealth_top">
			<dt class="box">
				<c:if test="${data.photo == null}">
				<img src="${static_path}/static/img/tou.png">
				</c:if>
				<c:if test="${data.photo != null}">
				<img src="http://img.eloancn.com/${data.photo}">
				</c:if>
			</dt>
			<dd>
				<b>${data.realname}
				<c:if test="${isvip != null && isvip.vip}">
					<img src="${static_path}/static/img/vip1.png" class="vipBtn" style="display:inline-block;width:1.25rem;margin-left:.2rem;" />
				</c:if>
				</b>
				<br />您好
			</dd>
			<a href="${ctx}/index/invested?openId=${openId}">我要投资</a>
		</dl> 
		<ul class="mywealth_list box">
			<li><span>${data.total2}</span><br/>总资产(元)</li>
			<li><span>${data.accountTotalMoney}</span><br/>账户余额(元)</li>
			<li><span>${data.freezingMoney}</span><br/>冻结余额(元)</li>
		</ul>
	</div>
		<ul class="mywealth_nav">
			<li><a href="${ctx}/index/ycb_show?openId=${openId}">翼农计划</a></li>
			<li><a href="${ctx}/index/my_hd?openId=${openId}">活动奖励</a></li>
			<li><a href="${ctx}/index/toubiao?openId=${openId}">我的投标</a></li>
			<li><a href="${ctx}/index/deal_log?openId=${openId}">交易记录</a></li>
			<li><a href="${ctx}/index/user_safe?openId=${openId}">帐户安全</a></li>
		</ul>
		<div class="tnd_form_btn" style="padding: 2rem 0;">
			<a data-href="${ctx}/index/czhi?openId=${openId}" data-viewname="czhi" style="color:#fff;" class="btn check_id">充值</a>
			<a data-href="${ctx}/index/tixian?openId=${openId}" data-viewname="tixian" style="color:#fff;" class="btn check_id">提现</a>
		</div>
	</div>
	<div class="layer none">
		<div class="layer_main position">
			<img src="${static_path}/static/img/close.png" class="close" style="position:absolute;right:.5rem;top:.5rem;width:0.7rem;">
			<div class="btn_with_layer vip_with_layer clearfix box">
			<span>VIP会员：</span>
			<p>${data.strTypeDesc}</p>
			<span>VIP专享福利：</span>
			<p>${data.strType}</p>
			<a href="${ctx}/index/invested?openId=${openId}">去翼农计划投资</a>
		</div>
	</div>
</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
	(function(w,j,r,e){
		var openId = '${openId}',
			contextPath = '${ctx}';
		var indexUrl = contextPath + '/index/index.html?openId=' + openId,
			checkBindUrl = contextPath + '/index/ajax/check_bind_ajax';
		var vipBtn = j('.vipBtn'),
			close = j('.close'),
			checkId = j('.check_id'),
			layer = j('.layer');
		vipBtn.on('click',function(){
			layer.show();
		});
		close.on('click',function(){
			layer.hide();
		});
		checkId.on('click',function(){
			var _href = j(this).data('href'),
				viewName = j(this).data('viewname');
			r.post(checkBindUrl,{openId:openId,viewName:viewName},function(data){
				if(data.status == 1){
					location.href = _href;
				} else {
					e.setKnowRedirectUrl(contextPath + data.url);
					e.alert2(data.info);
				}
			});
		});
		w.history.replaceState(null,'我的账户',indexUrl);
	})(window,$,requestUtil,eloancnUtil);
</script>
</body>
</html>
	