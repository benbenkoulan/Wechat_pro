<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>忘记密码</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">	
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
		<div class="form box">
			<form id="myform">
				<ul>
					<li>
						<span class="phone"></span>
						<input type="text" id="phone" placeholder="请输入您的手机号码">
					</li>
					<div class="errorTxt phoneError none"></div>
					<li class="codeLi">
						<span class="code"></span>
						<input type="text" id="verification_code" placeholder="图形验证码">
					</li>
					<div style="float:left;margin-top: 1rem;margin-left: .5rem;">
						<img class="reload" id="verCode" style="display:inline-block;" src="${baseUrl}/randCode/randCode.jsp" width="80">
						<a class="reload" href="javascript:void(0)" style="position:relative;top:-0.3rem;display:inline-block;color:#169ab4;font-size:14px;font-weight:normal;">看不清</a>
					</div>
					<div class="errorTxt verificationCodeError none"></div>
					<li class="codeLi" style="clear:both">
						<span class="code"></span>
						<input type="text" id="code" placeholder="短信验证码">
					</li>
					<span class="codeBtn">获取验证码</span>
					<span class="codeNum" style="display:none">60秒后重新获取</span>
					<div class="errorTxt codeError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password" placeholder="请设置新密码">
					</li>
					<div class="errorTxt passError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password1" placeholder="请确认新密码">
					</li>
					<div class="errorTxt passError1 none"></div>
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">确定</a></div>
				</ul>
			</form>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
(function(j,r,e){
	var baseUrl = '${baseUrl}',
		contextPath = '${ctx}',
		openId = '${openId}';
	eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('a 11=1j+\'/Z/Z.1d?\',18=J+\'/1a/1b/1r\';a f=j(\'#f\'),L=j(\'#L\'),o=j(\'#o\'),A=j(\'#A\'),G=j(\'.G\'),y=j(\'.y\');5 v(){S.M.1q(\'1s\',11+1o.1n()+\'&1g=\'+f.b())}G.B(\'E\',5(){y.D();a g=f.b();7(!i.H(g)){3}a q=L.b();7(!i.17(q)){3}a h=o.b(),m=A.b();7(!i.1c(h,m)){3}r.13(18,{f:g,1t:q,w:w,1p:h,1l:m},5(8){7(8.X){e.1m(J+8.1f);e.1e(8.F)}1i{j(8.T).c(8.F).d()}})});f.B(\'1h\',v);a S={N:j(\'#14\'),M:j(\'#M\'),l:j(\'.l\'),n:j(\'.n\'),K:j(\'.K\'),U:5(){a s=1k,2=4;2.l.D();2.n.d();2.I=1u(5(){s--;2.n.c(s+\'1E\');7(s<0){1G(2.I);2.l.d();1F 2.I;2.n.D().c(\'1H\')}},1I)},15:5(){a 2=4;2.K.B(\'E\',v);2.l.B(\'E\',5(){y.D();a g=f.b();7(!i.H(g)){3}a p=2.N.b();7(!i.19(p)){2.N.t();3}2.8[\'f\']=g;2.8[\'14\']=p;r.13(2.W,2.8,5(8){j(8.T).c(8.F).d();7(!8.X){v();3}2.U()})})},u:5(){4.W=J+\'/1a/1b/1D\';4.8={w:w};4.15()}};a i={R:j(\'.R\'),C:j(\'.C\'),Q:j(\'.Q\'),P:j(\'.P\'),O:j(\'.O\'),x:5(b){3(1x==1y b||1v==b||\'\'==b)},H:5(g){7(!4.Y.1w(g)){4.R.c(\'1B\');f.t();3 k}3 z},19:5(p){7(4.x(p)){4.O.c(\'16\').d();3 k}3 z},17:5(q){7(4.x(q)){4.P.c(\'16\').d();3 k}3 z},1c:5(h,m){7(4.x(h)){4.C.c(\'1C\').d();o.t();3 k}7(h.V<6||h.V>12){4.C.c(\'1z\').d();o.t();3 k}7(h!=m){4.Q.c(\'1A\').d();A.t();3 k}3 z},u:5(){4.Y=/^1[0-9]{10}$/}};S.u();i.u();',62,107,'||that|return|this|function||if|data||var|val|html|show||phone|phoneVal|passwordVal|validateUtil||false|codeBtn|password1Val|codeNum|password|verCodeVal|codeVal|||focus|init|refreshImageCode|openId|isEmpty|errorTxt|true|password1|bind|passError|hide|click|info|btn|valPhone|auto|contextPath|reload|code|verCode|verificationCode|verificationCodeError|codeError|passError1|phoneError|codeUtil|weizhi|countDown|length|getCodeUrl|status|phoneReg|randCode||randCodeUrl||post|verification_code|bindEvent|请输入图形验证码|valCode|findPwdUrl|valVerCode|index|ajax|valPassword|jsp|alert2|url|number|blur|else|baseUrl|60|pwd1|setKnowRedirectUrl|random|Math|pwd|attr|find_pwd_ajax|src|mcode|setInterval|null|test|undefined|typeof|登录密码要在6到12位之间|两次密码输入不一致|请填写正确的手机号|请设置登录密码|get_code|秒后重新获取|delete|clearInterval|60秒后重新获取|1000'.split('|'),0,{}))
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>