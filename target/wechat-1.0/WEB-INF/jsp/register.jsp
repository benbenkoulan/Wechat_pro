<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">	
<title>${pageTitle}</title>
<link rel="stylesheet" href="${static_path}/static/css/style.css" />
<link rel="stylesheet" href="${static_path}/static/css/layer.css" />
<style>
.imgCodeArea{float:left;margin-top: 1rem;margin-left: .5rem;}
</style>
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
	<div class="form box">
		<form id="myform">
			<ul>
				<li>
					<span class="phone"></span>
					<input type="tel" id="phone" name="phone" placeholder="请输入您的手机号码">
				</li>
				<div class="errorTxt phoneError none"></div>
				<li class="codeLi">
					<span class="code"></span>
					<input type="text" id="verification_code" placeholder="图形验证码">
				</li>
				<div class="imgCodeArea">
					<img class="reload" id="imgCode" style="display:inline-block;" width="80">
					<a class="reload" href="javascript:void(0)" style="position:relative;top:-0.3rem;display:inline-block;color:#169ab4;font-size:14px;font-weight:normal;">看不清</a>
				</div>
				<div class="errorTxt verificationCodeError none"></div>
				<li class="codeLi" style="clear:both">
					<span class="code"></span>
					<input type="text" id="mcode" name="mcode" placeholder="短信验证码">
				</li>
				<span class="codeBtn">获取验证码</span>
				<span class="codeNum" style="display:none;">60秒后重新获取</span>
				<div class="errorTxt codeError none"></div>
				<li>
					<span class="password"></span>
					<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password" name="password" placeholder="请设置登录密码">
				</li>
				<div class="errorTxt passError none"></div>
				<li>
					<span class="password"></span>
					<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password1" name="password1" placeholder="请确认登录密码">
				</li>
				<div class="errorTxt passError1 none"></div>
				<li class="none" id="tnd_code">
					<span class="code"></span>
					<input type="text" id="inviteCode" name="inviteCode" placeholder="请输入邀请码（仅VIP用户填写）">
				</li>
				<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">注册</a></div>
				<p class="tnd_formp"><a href="${ctx}/index/bind?openId=${openId}">已有账户，直接登录</a></p>
				<div class="tnd_code">邀请码<i></i></div>
			</ul>
		</form>
	</div>
</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
   (function(w,jq,req,e){
        'use strict'
        w.openId = '${openId}';
        w.contextPath = '${ctx}';
        w.randCodeUrl = '${baseUrl}/randCode/randCode.jsp?';
        eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('w.7=3(\'#7\');w.h=3(\'.h\');w.F=3(\'#F\');w.i=3(\'#19\'),w.u=/^1[0-9]{10}$/;g 15={q:3(\'#q\'),n:3(\'#n\'),y:3(\'.y\'),D:3(\'.D\'),x:3(\'.x\'),k:3(\'#X\'),v:3(\'.v\'),11:3(\'.1g\'),G:4(){g 2=c;c.11.E(\'B\',4(){8(!w.u.N((w.7.5()||\'\'))){w.h.a(\'Q\').b();w.7.p();j}w.h.f();8(!w.i.5()){2.x.a(\'1i\').b();w.i.p();j}2.x.f();g k=2.k.5();8(!k){2.v.a(\'1d\').b();2.k.p();j}2.v.f();g l=2.q.5();8(!l||l.14<6||l.14>12){2.y.a(\'1f\').b();2.q.p();j}2.y.f();g R=2.n.5();8(R!=l){2.D.a(\'1h\').b();2.n.p();j}2.D.f();Z.18(2.U,{z:w.z,7:w.7.5(),q:l,n:R,X:k,i:w.i.5(),F:w.F.5()},4(d){e.1a(d.H);8(d.13){e.1c(w.K+d.1k)}t{3(d.17).a(d.H).b()}})})},C:4(){c.U=w.K+\'/S/1x\';c.G()}},P={M:3(\'#M\'),A:3(\'#V\'),Y:3(\'.V\'),I:3(\'.I\'),L:3(\'.L\'),r:3(\'.r\'),m:3(\'.m\'),G:4(){g 2=c;w.7.E(\'1y\',4(){8(w.u.N((w.7.5()||\'\'))){w.h.f();P.o()}t{w.h.a(\'Q\').b()}});2.Y.E(\'B\',4(){8(2.A.1b(\'O\')){2.A.1v(\'O\')}t{2.A.1B(\'O\')}});2.I.1C(\'.1z\',\'B\',4(){2.o()});2.r.E(\'B\',4(){2.L.f();8(w.u.N((w.7.5()||\'\'))){Z.18(2.W,{7:w.7.5(),z:w.z,19:w.i.5()},4(d){3(d.17).a(d.H).b();8(!d.13){2.o();j}2.16()})}t{w.h.a(\'Q\').b()}})},16:4(){g s=1s,2=c;2.r.f();2.m.b();2.J=w.1q(4(){s--;2.m.a(s+\'1r\');8(s<0){w.1u(2.J);2.r.b();1t 2.J;2.m.f().a(\'1m\')}},1l)},o:4(){g 2=c,T=w.7.5()||\'\';2.M.1n(\'1p\',w.1o+1A.1w()+"&1e="+T)},C:4(){c.W=w.K+\'/S/1j\';c.G();c.o()}};15.C();P.C()',62,101,'||that|jq|function|val||phone|if||html|show|this|data||hide|var|phoneError|verificationCode|return|mobileCode|passwordVal|codeNum|password1|refreshImageCode|focus|password|codeBtn||else|phoneReg|codeError||verificationCodeError|passError|openId|tndCodeArea|click|init|passError1|on|inviteCode|bindEvent|info|imgCodeArea|auto|contextPath|errorTxt|imgCode|test|none|codeHelper|请填写正确的手机号|password1Val|register|phoneNumber|registerUrl|tnd_code|codeUrl|mcode|tndCodeBtn|req||registerBtn||status|length|registerHelper|countDown|weizhi|post|verification_code|alert2|hasClass|setKnowRedirectUrl|验证码不正确|number|密码要在6位和12位之间|btn|两次密码输入不一致|图形验证码不正确|get_code|url|1000|60秒后重新获取|attr|randCodeUrl|src|setInterval|秒后重新获取|60|delete|clearInterval|removeClass|random|reg_ajax|blur|reload|Math|addClass|delegate'.split('|'),0,{}))
   })(window, $, requestUtil, eloancnUtil);
</script>
</body>
</html>
