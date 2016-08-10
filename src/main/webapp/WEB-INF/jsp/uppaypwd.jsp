<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改支付密码</title>
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
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password2" name="password2" placeholder="请输入原支付密码">
					</li>
					<div class="errorTxt passError2 none"></div>
					<li class="codeLi">
						<span class="code"></span>
						<input type="text" id="verification_code" placeholder="图形验证码">
					</li>
					<div style="float:left;margin-top: 1rem;margin-left: .5rem;">
						<img class="reload" id="verCode" src="${baseUrl}/randCode/randCode.jsp?number=${data.mobile}" style="display:inline-block;" width="80">
						<a class="reload" href="javascript:void(0)" style="position:relative;top:-0.3rem;display:inline-block;color:#169ab4;font-size:14px;font-weight:normal;">看不清</a>
					</div>
					<div class="errorTxt verificationCodeError none"></div>
					<li class="codeLi" style="clear:both">
						<span class="code"></span>
						<input type="text" id="code" name="code" placeholder="短信验证码">
					</li>
					<span class="codeBtn">获取验证码</span>
					<span class="codeNum" style="display:none">60秒后重新获取</span>
					<div class="errorTxt codeError none"></div>
					<div class="errorTxt">验证码将发送到${data.strMobile}手机号</div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password" name="password" placeholder="请设置支付密码">
					</li>
					<div class="errorTxt passError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password1" name="password1" placeholder="请确认支付密码">
					</li>
					<div class="errorTxt passError1 none"></div>
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">确定</a></div>
					<p class="tnd_formp"><a href="${ctx}/index/find_pay_pwd?openId=${openId}">忘记支付密码</a></p>
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
	var phone = '${data.mobile}',
		openId = '${openId}',
		contextPath = '${ctx}',
		baseUrl = '${baseUrl}';
	eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('4 L=11+\'/U/U.13?\',K=J+\'/T/S/17\',P=J+\'/T/S/16\';4 l=j(\'#l\'),f=j(\'#f\'),p=j(\'#p\'),D=j(\'.D\'),H=j(\'.H\'),m=j(\'.m\'),C=j(\'.C\'),y=j(\'.y\'),t=j(\'#t\'),R=j(\'#Q\'),A=j(\'#A\'),h=j(\'.h\'),d=j(\'.d\'),z=j(\'.z\'),v=j(\'.v\'),B=j(\'.B\');9 w(){A.18(\'Z\',L+15.14()+\'&19=\'+x)}z.F(\'E\',w);h.F(\'E\',9(){v.u();4 i=R.c();5(n==o i||k==i||\'\'==i){y.3(\'Y\').1();8}r.V(P,{x:x,q:q,Q:i},9(2){j(2.W).3(2.G).1();5(!2.N){w();8}4 s=1k;h.u();d.1();4 O=1l(9(){s--;d.3(s+\'1m\');5(s<0){1n(O);h.1();d.u().3(\'1o\')}},1d)})});B.F(\'E\',9(){v.u();4 a=l.c();5(n==o a||k==a||\'\'==a){D.3(\'1i\').1();l.b();8}4 g=t.c();5(n==o g||k==g||\'\'==g){C.3(\'1j\').1();t.b();8}4 7=f.c();5(n==o 7||k==7||\'\'==7){m.3(\'1g\').1();f.b();8}5(7.M<6||7.M>12){m.3(\'1f\').1();f.b();8}4 I=p.c();5(7!=I){H.3(\'1h\').1();p.b();8}r.V(K,{q:q,1b:a,1a:g,1c:7,1e:I},9(2){5(2.N){e.1p(J+2.10)}e.X(2.G);j(2.W).1().3(2.G)})});',62,88,'|show|data|html|var|if||passwordVal|return|function|password2Val|focus|val|codeNum||password|codeVal|codeBtn|verificationCodeVal||null|password2|passError|undefined|typeof|password1|openId|||code|hide|errorTxt|refreshImageCode|phone|verificationCodeError|reload|verCode|btn|codeError|passError2|click|bind|info|passError1|password1Val|contextPath|upPayPwdUrl|randCodeUrl|length|status|auto|getCodeUrl|verification_code|verificationCode|ajax|index|randCode|post|weizhi|alert2|请输入图形验证码|src|url|baseUrl||jsp|random|Math|get_code|upPayPwd_ajax|attr|number|mcode|pwd|paypwd|1000|paypwd1|支付密码要在6到12位之间|请输入支付密码|两次密码输入不一致|请输入原支付密码|请输入短信验证码|60|setInterval|秒后重新获取|clearInterval|60秒后重新获取|setKnowRedirectUrl'.split('|'),0,{}))
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>