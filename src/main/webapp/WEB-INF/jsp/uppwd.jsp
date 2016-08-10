<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />	
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/style.css" />
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
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password2" name="password2" placeholder="请输入原密码">
					</li>
					<div class="errorTxt passError2 none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password" name="password" placeholder="请输入新密码">
					</li>
					<div class="errorTxt passError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password1" name="password1" placeholder="请确认新密码">
					</li>
					<div class="errorTxt passError1 none"></div>
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">提交</a></div>
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
	var openId = '${openId}',
		contextPath = '${ctx}';
	eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('3 u=v+\'/G/z/C\';3 i=j(\'#i\'),k=j(\'#k\'),h=j(\'#h\'),b=j(\'.b\'),1=j(\'.1\'),a=j(\'.a\'),f=j(\'.f\');d=j(\'.d\');d.D(\'F\',s(){f.E();3 5=i.g();4(o==q 5||n==5||\'\'==5){b.7(\'y\').8();b.c();9}3 0=k.g();4(o==q 0||n==0||\'\'==0){1.7(\'O\').8();1.c();9}4(0.p<6||0.p>P){1.7(\'Q\').8();1.c();9}3 l=h.g();4(0!=l){a.7(\'H\').8();a.c();9}r.L(u,{M:5,t:t,I:0,J:l},s(2){4(2.N){e.K(v+2.A);e.x(2.m)}w{j(2.B).8().7(2.m)}})});',53,53,'passwordVal|passError|data|var|if|password2Val||html|show|return|passError1|passError2|focus|btn||errorTxt|val|password1|password2||password|password1Val|info|null|undefined|length|typeof||function|openId|editPwdUrl|contextPath|else|alert2|请输入原登录密码|ajax|url|weizhi|edit_pwd|bind|hide|click|index|两次密码输入不一致|pwd1|pwd2|setKnowRedirectUrl|post|pwd|status|请输入新登录密码|12|登录密码要在6到12位之间'.split('|'),0,{}))
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>