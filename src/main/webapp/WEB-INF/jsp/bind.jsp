<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">	
<link rel="stylesheet" href="${static_path}/static/css/style.css" />
<style>
	body{position: absolute;}
	.code_none{display:none;}
</style>
</head>	
<body>
<div class="main">
	<div class="form box">
		<form id="myform">
			<ul>
				<li>
					<span class="user"></span>
					<input type="text" id="user" placeholder="手机号/邮箱">
				</li>
				<li>
					<span class="password"></span>
					<input type="password" id="password" placeholder="登录密码" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))">
				</li>
				<div class="code_area code_none">
					<li id="code" class="codeLi">
						<span class="code"></span>
						<input type="text" id="inputcode" placeholder="图形验证码">
					</li>
					<div style="float:left;margin-top: 1rem;margin-left: .5rem;">
						<img class="reload" id="imgCode" style="display:inline-block;" width="80" >
					    <a class="reload" href="javascript:void(0);" style='position:relative;top:-0.3rem;display:inline-block;color:#169ab4;font-size:14px;font-weight:normal;'>看不清</a>
					</div>					
				</div>
				<div class="errorTxt passError none"></div>
			</ul>
			<div class="form_btn"><a href="javascript:;" class="btn">登录</a></div>
			<p class="tnd_formp"><a href="${ctx}/bind/findpwd?openId=${openId}">忘记密码</a>
		</form>
	</div>
</div>
<a href="${ctx}/register/reg?openId=${openId}" class="tnd_reg_btn">注册</a>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script>
	//单例模式
	(function(w,jq,req){
		var bindHandler = {
			user : jq('#user'),//用户名
			password : jq('#password'),//密码
			passError : jq('.passError'),//错误提示
			codeArea : jq('.code_area'),//验证码区域
			inputcode : jq('#inputcode'),//图片验证码输入框
			imgCode : jq('#imgCode'),//图片验证码
			reloadBtn : jq('.reload'),//加载图片验证码按钮
			bindBtn : jq('.btn'),//登录按钮
			refreshCode : function(){
				this.imgCode.attr('src','${baseUrl}/randCode/randCode.jsp?' + Math.random() + '&&number=' + this.user.val());
			},
			bindEvent : function(){
				var that = this;
				that.bindBtn.on('click',function(){
					var username = that.user.val() || '',
						pwd = that.password.val() || '';
					var mobile = username.match(/^1[0-9]{10}$/),
						email = username.match(/\w+@\w+\.\w/),
						idCard = username.match(/^[0-9a-zA-Z]{18}/);
					if(mobile || email || idCard){
						that.passError.hide();
						that.request_data['phone'] = username;
						that.request_data['pwd'] = pwd;
						if(!that.codeArea.hasClass('code_none')){//如果验证码显示
							that.request_data['ranCode'] = that.inputcode.val();
						} else {
							that.request_data['ranCode'] = '';
						}
						req.post(that.bindUrl,that.request_data,function(data){
							if(data.status){//登录成功
								w.location.href = '${ctx}' + data.url;
							} else {
								that.passError.html(data.info).show();
								that.refreshCode();
								that.codeArea.removeClass('code_none');
							}
						});
					} else {
						that.passError.html('用户名或密码错误').show();
						that.refreshCode();
						that.codeArea.removeClass('code_none');
					}
				});
				that.reloadBtn.on('click',function(){
					that.refreshCode();
				});
				that.user.on('change',function(){
					that.codeArea.addClass('code_none');
				});
			},
			init : function(){
				this.request_data = {
					params : ('${params}' || ''),
					viewName : ('${viewName}' || ''),
					openId : ('${openId}' || '')
				};
				this.openId = '${openId}' || '';
				this.bindUrl = '${ctx}/bind/bind_ajax';
				this.bindEvent();
				var h = document.documentElement.clientHeight || document.body.clientHeight;
				jq('body').height(h);
			}
		};
		bindHandler.init();
	})(window,$,requestUtil);
</script>
</body>
</html>