<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>	
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
		<div class="tnd_account">
			<form id="myform">
				<ul class="mywealth_nav tnd_add_bank">
					<li class="mywealth_nav_ban">
						<span>姓名</span><input type="text" name="name" id="name" class="tnd_add_txt" readonly value="${info.realname}">
					</li>
					<div class="errorTxt nameError none"></div>
					<li><a id="check_bank" data-href="${ctx}/index/card_list?openId=${openId}">
					<span>所属银行</span>	
					<b id="b_1">
						<c:choose>
							<c:when test="${bank == null}">
								请点击选择
							</c:when>
							<c:otherwise>
								${bank}
							</c:otherwise>
						</c:choose>
						</b></a></li>
					<div class="errorTxt seleError1 none"></div>
					<li class="mywealth_nav_ban">
						<span>银行卡号</span><input type="tel" name="card" id="card" value="${add_card.card}" class="tnd_add_txt" placeholder="请输入银行卡号">
					</li>
					<div class="errorTxt cardError1 none"></div>
					<li>
						<a id="check_kaihu" data-href="${ctx}/index/province?openId=${openId}">
							<span>开户行</span>
							<b id="b_2">
								<c:choose>
									<c:when test="${add_card == null || add_card.bank == null}">
										请点击选择
									</c:when>
									<c:otherwise>
										${add_card.bank}
									</c:otherwise>
								</c:choose>
							</b>
						</a>
					</li>
					<div class="errorTxt seleError2 none"></div>
				</ul>
				<ul class="mywealth_nav">
					<li class="mywealth_nav_ban">
						<span>手机号</span><input type="text" name="phone" id="phone" value="${cover_phone}" readonly class="tnd_add_txt" placeholder="请输入手机号">
					</li>
					<div class="errorTxt phoneError none"></div>
					<li class="mywealth_nav_ban">
						<span>图形验证码</span>
						<img id="imgCode" src="" style="display:inline-block;position:relative;top: 0.28rem;" width="70">
						<input type="text" id="verification_code" class="tnd_add_txt tnd_add_codetxt" style="width: 6rem" placeholder="输入图形验证码">
					</li>
					<div class="errorTxt verificationCodeError none"></div>
					<li class="mywealth_nav_ban">
						<span>验证码</span>
						<span class="codeBtn">获取验证码</span>
						<span class="codeNum" style="display:none">60秒后重新获取</span>
						<input type="text" value="${add_card.code}" name="code" id="code" class="tnd_add_txt tnd_add_codetxt" placeholder="输入验证码">
						<input type="hidden" value="${add_card.code}" name="mcode" id="mcode">
						<div style="clear:both;"></div>
					</li>
					<div class="errorTxt codeError none"></div>
				</ul>
				<input type="hidden" name="openId" id="openId" value="${openId}" />
				<div class="form_btn" style="padding-top:2rem;"><a href="javascript:;" class="btn">确定</a></div>
			</form>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
	/**
	单例模式，2016-02-04，liben
	**/
	(function(w,d,jq,r,e){
		var phone='${info.mobile}',
			openId='${openId}',
			contextPath = '${ctx}';
		var saveInfoUrl = contextPath + '/index/ajax/save_info',
			getCodeUrl = contextPath + '/index/ajax/get_code',
			addCardAjaxUrl = contextPath + '/index/ajax/add_card_ajax';
			checkSessionBankUrl = contextPath + '/index/ajax/check_session_bank';
		var card_info = {
		    bank: jq('#check_bank'),
		    kaihu: jq('#check_kaihu'),
		    form: jq('#myform'),
		    saveInfo: function(data, href) {
		        r.post(saveInfoUrl, data, function(data) {
		            w.location.href = href;
		        })
		    },
		    bindEvent: function() {
		        var form = this.form,
		            that = this;
		        that.bank.on('click', function() {
		            var href = jq(this).data('href'),
		                data = form.serialize();
		            that.saveInfo(data, href)
		        });
		        that.kaihu.on('click', function() {
		            var href = jq(this).data('href'),
		                data = {openId: openId},
		                form_data = form.serialize();
		            r.post(checkSessionBankUrl, data, function(data) {
		                if (data.code) {
		                	r.setFlag(false);
		                    that.saveInfo(form_data, href);
		                } else {
		                    e.alert2('请选择所属银行');
		                    return;
		                }
		            })
		        })
		    }
		};
		card_info.bindEvent();
		var code_info = {
		    codeBtn: jq('.codeBtn'),
		    codeNum: jq('.codeNum'),
		    imgCode: jq('#imgCode'),
		    verCode: jq('#verification_code'),
		    verCodeError: jq('.verificationCodeError'),
		    codeError: jq('.codeError'),
		    reloadImgCode: function() {
		        this.imgCode.attr('src', '${baseUrl}/randCode/randCode.jsp?' + Math.random() + '&&number=' + phone)
		    },
		    bindEvent: function() {
		        var that = this;
		        this.imgCode.on('click', that.reloadImgCode);
		        this.codeBtn.on('click', function() {
		            that.verCodeError.hide();
		            var data = {
		                openId: openId,
		                phone: phone,
		                verification_code: that.verCode.val()
		            };
		            r.post(getCodeUrl, data, function(data) {
		                jq(data.weizhi).html(data.info).show();
		                if (data.status) {
		                    that.codeBtn.hide();
		                    that.codeNum.show();
		                    var s = 60,
		                        auto = w.setInterval(function() {
		                            s--;
		                            that.codeNum.html(s + '秒后重新获取');
		                            if (s < 0) {
		                                w.clearInterval(auto);
		                                that.codeError.hide();
		                                that.codeNum.hide().html('60秒后重新获取');
		                                that.codeBtn.show()
		                            }
		                        }, 1000)
		                } else {
		                    that.reloadImgCode()
		                }
		            })
		        })
		    }
		};
		code_info.bindEvent();
		code_info.reloadImgCode();
		var add_card = {
		    btn: jq('.btn'),
		    bank: jq('#b_1'),
		    bankError: jq('.seleError1'),
		    card: jq('#card'),
		    cardError: jq('.cardError1'),
		    kaihu: jq('#b_2'),
		    kaihuError: jq('.seleError2'),
		    code: jq('#code'),
		    codeError: jq('.codeError'),
		    bindEvent: function() {
		        var that = this;
		        this.btn.on('click', function() {
		            if (that.bank.text().trim() == '请点击选择') {
		                that.bankError.html('请选择所属银行').show();
		                return
		            }
		            that.bankError.hide();
		            if (!/^[0-9]{10,23}$/.test(that.card.val())) {
		                that.cardError.html('请输入正确的银行卡号').show();
		                return
		            }
		            that.cardError.hide();
		            if (that.kaihu.text().trim() == '请点击选择') {
		                that.kaihuError.html('请选择开户行').show();
		                return
		            }
		            that.kaihuError.hide();
		            if (that.code.val() == '') {
		                that.codeError.html('验证码不正确').show();
		                that.code.focus();
		                return
		            }
		            that.codeError.hide();
		            var data = {
		                openId: openId,
		                card: that.card.val(),
		                mcode: that.code.val()
		            };
		            r.post(addCardAjaxUrl, data, function(data) {
		                e.alert2(data.info);
		                if (data.status) {
		                	e.setKnowRedirectUrl(contextPath + data.url);
		                } else {
		                    jq(data.weizhi).show().html(data.info)
		                }
		            })
		        })
		    }
		};
		add_card.bindEvent();
	}(window,document,$,requestUtil,eloancnUtil));
</script>
</body>
</html>