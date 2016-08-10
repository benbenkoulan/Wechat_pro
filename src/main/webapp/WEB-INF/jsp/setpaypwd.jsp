<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置支付密码</title>
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
						<input type="password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" name="login_pwd" id="login_pwd" placeholder="请输入登录密码">
					</li>
					<div class="errorTxt passError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" name="pay_pwd" id="pay_pwd" placeholder="请设置支付密码">
					</li>
					<div class="errorTxt passError1 none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" name="pay_pwd1" id="pay_pwd1" placeholder="请确认支付密码">
					</li>
					<div class="errorTxt passError2 none"></div>
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
		viewName = '${viewName}',
		contextPath = '${ctx}';
	var setPayPwdUrl = contextPath + '/index/ajax/setPayPwd_ajax';
	var login_pwd,pay_pwd,pay_pwd1,passError,passError1,passError2,
		errorTxt = j('.errorTxt'),
		btn = j('.btn');
	btn.bind('click',function(){
		errorTxt.hide();
		login_pwd = login_pwd || j('#login_pwd');
		var loginPwdVal = login_pwd.val();
		if(undefined == loginPwdVal || null == loginPwdVal || '' == loginPwdVal){
			passError = passError || j('.passError');
			passError.html('请输入登录密码').show();
			login_pwd.focus();
			return;
		}
		pay_pwd = pay_pwd || j('#pay_pwd');
		var payPwdVal = pay_pwd.val();
		if(undefined == payPwdVal || null == payPwdVal || '' == payPwdVal){
			passError1 = passError1 || j('.passError1');
			passError1.html('请设置支付密码').show();
			pay_pwd.focus();
			return;
		}
		if(payPwdVal.length < 6 || payPwdVal.length > 12){
			passError1 = passError1 || j('.passError1');
			passError1.html('密码要在6到12位之间').show();
			pay_pwd.focus();
			return;
		}
		pay_pwd1 = pay_pwd1 || j('#pay_pwd1');
		var payPwd1Val = pay_pwd1.val();
		if(payPwdVal != payPwd1Val){
			passError2 = passError2 || j('.passError2');
			passError2.html('两次密码输入不一致').show();
			pay_pwd1.focus();
			return;
		}
		r.post(setPayPwdUrl,{openId:openId,viewName:viewName,login_pwd:loginPwdVal,pay_pwd:payPwdVal,pay_pwd1:payPwd1Val},function(data){
			if(data.status){
				e.alert2(data.info);
				e.setKnowRedirectUrl(contextPath + data.url);
			} else {
				j(data.weizhi).html(data.info).show();
			}
		});
	});
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>