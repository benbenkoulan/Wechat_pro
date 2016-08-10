<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>身份证认证</title>
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
		<div class="form box clearfix">
			<form id="myform">
				<ul>
					<li>
						<span class="name"></span>
						<input type="text" name="name" id="name" placeholder="请输入您的真实姓名" onpaste="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" >
					</li>
					<div class="errorTxt nameError none"></div>
					<li>
						<span class="idNum"></span>
						<input type="text" id="idNum" name="idNum" placeholder="请输入您的身份证号码">
					</li>
					<div class="errorTxt idNumError none"></div>
					<div class="errorTxt" style="text-align:center">温馨提示: 您还有${number}次免费认证机会</div>
				</ul>
				<div class="form_btn"><a href="javascript:;" class="btn">身份证认证</a></div>
			</form>
		</div>
		<ul class="tnd_certification clearfix">
			<li>1.身份证认证目前只支持 2 代身份证，如您的身份证信息属于敏感信息请联系客服人员。</li>
			<li>2.手机端目前有 3 次认证机会，如果 3 次机会用完，请联系客服人员。</li>
			<li>3.如您的账户有资金交易记录，则身份证姓名不可更改，请慎重填写。</li>
			<li>投资咨询：400-080-50555</li>
		</ul>
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
	var authUrl = contextPath + '/index/ajax/auth_ajax';
	var name = j('#name'),
		idNum = j('#idNum'),
		btn = j('.btn'),
		errorTxt = j('.errorTxt'),
		nameError = j('.nameError'),
		idNumError = j('.idNumError');
	btn.bind('click',function(){
		errorTxt.hide();
		var nameVal = name.val();
		if('undefined' == typeof nameVal || nameVal.match(/[^\u4E00-\u9FA5]/g)){
			nameError.html('请填写真实姓名').show();
			name.focus();
			return;
		}
		var idNumVal = idNum.val();
		if('undefined' == typeof idNumVal || idNumVal.length != 18){
			idNumError.html('请填写正确的身份证号').show();
			idNum.focus();
			return;
		}
		r.post(authUrl,{idNum:idNumVal,openId:openId,name:nameVal,viewName:viewName},function(data){
			if(data.info){//如果提示信息不为空则提示
				e.alert2(data.info);
			}
			if(data.status == 1){
				e.setKnowRedirectUrl(contextPath + data.url);
			}else{
				e.setKnowAction(function(){
					location.reload();
				});
			}
		});
	});
})($,requestUtil,eloancnUtil);
</script>
</body>
</html>