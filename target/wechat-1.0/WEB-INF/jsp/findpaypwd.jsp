<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记支付密码</title>
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
						<span class="idNum"></span>
						<input type="text" id="idNum" placeholder="请输入您的身份证号">
					</li>
					<div class="errorTxt idNumError none"></div>
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
						<input type="text" id="code" placeholder="短信验证码">
					</li>
					<span class="codeBtn">获取验证码</span>
					<span class="codeNum" style="display:none">60秒后重新获取</span>
					<div class="errorTxt codeError none"></div>
					<div class="errorTxt">验证码将发送到${data.strMobile}手机号</div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password" placeholder="请设置支付密码">
					</li>
					<div class="errorTxt passError none"></div>
					<li>
						<span class="password"></span>
						<input type="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))" id="password1" placeholder="请确认支付密码">
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
	var phone = '${data.mobile}',
		openId = '${openId}',
		contextPath = '${ctx}',
		baseUrl = '${baseUrl}';
	var getCodeUrl = contextPath + '/index/ajax/get_code',
		findPayPwdUrl = contextPath + '/index/ajax/findPayPwd_ajax',
		randCodeUrl = baseUrl + '/randCode/randCode.jsp?';
	var idNum = j('#idNum'),
		code = j('#code'),
		password = j('#password'),
		password1 = j('#password1'),
		verCode = j('#verCode'),
		verificationCode = j('#verification_code'),
		reload = j('.reload'),
		codeBtn = j('.codeBtn'),
		codeNum = j('.codeNum'),
		btn = j('.btn'),
		idNumError = j('.idNumError'),
		verificationCodeError = j('.verificationCodeError'),
		codeError = j('.codeError'),
		passError = j('.passError'),
		passError1  = j('.passError1'),
		errorTxt = j('.errorTxt');
	function refreshImageCode() {
	    verCode.attr('src', randCodeUrl + Math.random() + '&number=' + phone);
	}
	reload.bind('click',refreshImageCode);
	codeBtn.bind('click',function(){
		errorTxt.hide();
		var verificationCodeVal = verificationCode.val();
		if('undefined' == typeof verificationCodeVal || null == verificationCodeVal || '' == verificationCodeVal){
			verificationCodeError.html('请输入图形验证码').show();
			return;
		}
		r.post(getCodeUrl,{phone:phone,openId:openId,verification_code:verificationCodeVal},function(data){
			j(data.weizhi).html(data.info).show();
            if (!data.status) {
                refreshImageCode();
                return;
            }
            var s = 60;
            codeBtn.hide();
            codeNum.show();
            var auto = setInterval(function(){
            	s--;
            	codeNum.html(s + '秒后重新获取');
                if (s < 0) {
                    clearInterval(auto);
                    codeBtn.show();
                    codeNum.hide().html('60秒后重新获取');
                }
            },1000);
		});
	});
	btn.bind('click',function(){
		errorTxt.hide();
		var idNumVal = idNum.val();
		if(undefined == typeof idNumVal || null == idNumVal || '' == idNumVal){
			idNumError.html('请输入有效的身份证号').show();
			idNum.focus();
			return;
		}
		var codeVal = code.val();
		if(undefined == typeof codeVal || null == codeVal || '' == codeVal){
			codeError.html('请输入短信验证码').show();
			code.focus();
			return;
		}
		var passwordVal = password.val();
		if(undefined == typeof passwordVal || null == passwordVal || '' == passwordVal){
			passError.html('请输入支付密码').show();
			password.focus();
			return;
		}
		if(passwordVal.length < 6 || passwordVal.length > 12){
			passError.html('支付密码要在6到12位之间').show();
			password.focus();
			return;
		}
		var password1Val = password1.val();
		if(passwordVal != password1Val){
			passError1.html('两次密码输入不一致').show();
			password1.focus();
			return;
		}
		r.post(findPayPwdUrl,{openId:openId,idcard:idNumVal,mcode:codeVal,paypwd:passwordVal,paypwd1:password1Val,phone:phone},function(data){
			if (data.status) {
	            e.setKnowRedirectUrl(contextPath + data.url);
			}
			e.alert2(data.info);
			j(data.weizhi).show().html(data.info);
		});
	});
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>