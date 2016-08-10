<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />	
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/style.css" />
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/layer.css" />
</head>
<body>
<!--弹窗层---开始-->
	<div class="alert_layer"></div>
	<div class="alert_box alert_box_ok">
		<p class="alert_title">提示</p>
		<p class="alert_content alert_content_center"></p>
		<p class="alert_attention"></p>
		<div class="alert_btn_container clearfix">
			<div class="alert_btn alert_btn1 alert_btn_cancel">取消</div>
			<div class="alert_btn alert_btn1 alert_btn_ok">确定</div>
		</div>
	</div>
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
		<div class="tnd_account">
			<dl>	
				<dt>头像</dt>
				<dd>
					<c:choose>
						<c:when test="${userInfo.yld_head_photo == null }">
							<img src="${static_path}/static/img/tou.png">
						</c:when>
						<c:otherwise>
							<img src="http://img.eloancn.com/${userInfo.yld_head_photo}">
						</c:otherwise>
					</c:choose>					
				</dd>
			</dl>
			<ul class="mywealth_nav">
				<c:choose>
					<c:when test="${check.idcard == '已'}">
						<li class="mywealth_nav_ban">
							<a href="javascript:;">身份证验证<b>${data.strIdcard}</b></a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a id="authenticate" data-time="${check.idCardAuthCount}" data-href="${ctx}/index/authenticate?openId=${openId}">身份证验证<b>未设置</b></a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${check.mobile == '已'}">
						<li class="mywealth_nav_ban">
							<a href="javascript:;">手机验证<b>${data.strMobile}</b></a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${ctx}/index/mobile_auth?openId=${openId}">手机验证<b>未设置</b></a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
			<ul class="mywealth_nav">
				<li>
					<c:choose>
						<c:when test="${check.paypassword == '已'}">
							<a href="${ctx}/index/up_pay_pwd?openId=${openId}">支付密码<b>修改</b></a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/index/set_pay_pwd?openId=${openId}">支付密码<b>未设置</b></a>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<a href="${ctx}/index/up_pwd?openId=${openId}">登录密码<b>修改</b></a>
				</li>
			</ul>
			<ul class="mywealth_nav">
				<li><a style="background:none;" id="unbind" href="javascript:void(0);">退出登录</a></li>
			</ul>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
(function(j,r,e){
	var openId = '${openId}',
		contextPath = '${ctx}';
	eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('7 9=h+\'/z/l/4\';7 5=j(\'#5\'),4=j(\'#4\');5.i(\'a\',3(){7 6=j(g).1(\'6\'),2=j(g).1(\'2\');8(6==\'0\'){e.n(\'\');e.f(\'o\\p-m-k\')}d{c.2=2}});4.w(\'a\',3(){e.x(3(){r.y(9,{b:b},3(1){8(1.A){c.2=h+1.s}d{e.q();e.f(1.t)}})});e.v(\'u?\')});',37,37,'|data|href|function|unbind|authenticate|time|var|if|unbindUrl|click|openId|location|else||alert2|this|contextPath|on||5055|ajax|080|setKnowRedirectUrl|您已经没有认证机会请联系客服人员解决|n400|hideLayer||url|info|确定退出|alert1|bind|setOkAction|post|index|status'.split('|'),0,{}))
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>