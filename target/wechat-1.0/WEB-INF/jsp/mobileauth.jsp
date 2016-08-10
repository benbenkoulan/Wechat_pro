<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />	
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/style.css" />
</head>
<body>
	<div class="main">
		<div class="form box">
			<form id="myform" action="">
				<ul>
					<div class="errorTxt nameError none"></div>
					<li>
						<span class="phone"></span>
						<input type="text" id="phone" placeholder="请输入您的手机号码">
					</li>
					<div class="errorTxt phoneError none"></div>
					<li class="codeLi">
						<span class="code"></span>
						<input type="text" id="code" placeholder="短信验证码">
					</li>
					<span class="codeBtn">获取验证码</span>
					<span class="codeNum" style="display:none">60秒后重新获取</span>
					<div class="errorTxt codeError none"></div>
					<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">确定</a></div>
				</ul>
			</form>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	$(function(){
		$('.codeBtn').bind('click', function(){
			var phone = $('#phone').val();
			if(!/^1[3578][0-9]{9}$/.test(phone)){
				$('.phoneError').show().html('请填写正确的手机号！');
				$('#phone').focus();
				return;
			}else{
				$('.phoneError').hide();
			}
			$.ajax({
				type: "POST",
				url: "${ctx}/index/ajax/auth_mobile_code",
				data: {openId:'${openId}',phone:phone},
				dataType: "json",
				success: function(data){
					$(data.weizhi).show().html(data.info);
				}
			});
			var s = 60;
			$(this).hide();
			$('.codeNum').show();
			var auto = setInterval(function(){ 
				s--;
				$('.codeNum').html(s + '秒后重新获取');
				if(s < 0){
					s = 60;
					clearInterval(auto);
					$('.codeNum').hide().html('60秒后重新获取');
					$('.codeBtn').show();
				}
			}, 1000);
		});
		$(".btn").bind("click", function(){
			if(!/^1[3578][0-9]{9}$/.test($('#phone').val())){
				$('.phoneError').show().html('请填写正确的手机号！');
				$('#phone').focus();
				return;
			}else{ 
				$('.phoneError').hide();
			}
			if($('#code').val() == ''){
				$('.codeError').show().html('验证码不正确！');
				$('#code').focus();
				return;
			}else{ 
				$('.codeError').hide();
			}
			$.ajax({
				type: "POST",
				url: "${ctx}/index/ajax/auth_mobile",
				data: {openId:'${openId}',phone:$('#phone').val(),mcode:$('#code').val()},
				dataType: "json",
				success: function(data){
					$(data.weizhi).show().html(data.info);
					if(data.status == 1){
						location.href = '${ctx}' + data.url;
					}
				},
				error: function(jqXHR ,textStatus, errorThrown){
					if(textStatus == 'timeout'){
						alert("网络不给力，请稍后再试！");
					}
				}
			});
		});
	});
</script>
</body>
</html>