<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="format-detection" content="telephone=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-touch-fullscreen" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>提现</title>
	<link rel="stylesheet" type="text/css" href="${static_path}/static/css/layer.css" />
	<style>
		body{background-color: #ebebeb;color: #333333;margin: 0;padding:8px 12px;max-width: 100%;font-family: '微软雅黑';}
		.info{background-color: #ffffff;border:1px solid #dddddd;font-size: 15px;line-height: 2.5em;}
		.bank{background:url(${static_path}/static/img/sj.png) no-repeat 90% #ffffff;margin-bottom: 8px;text-decoration: none;display:block;}
		.info span{padding-right: 10px;color: #222222;display: inline-block;}
		.bank span{padding-left: 46px;display:inline-block;text-align: left;}
		.bank em{font-style: normal;color: #999999;font-size: 12px;}
		.bank b{background: url(${static_path}/static/img/td2.png) no-repeat 0 center;width:16px;height: 2.5em;float: right;}
		.jine span{text-align: right;width: 28%;}
		.jine input{border: 0;font-size: 12px;font-family: 'Microsoft Yahei';height: 24px;line-height: 24px;}
		.fee_info {font-size: 10px;padding: 5px 0 0 25px;}
		.fee_info span{color:#ff0000;}
		.dae_info{font-size:10px;padding:5px 0 0 25px;color:#ff0000;}
		.tishi{text-align: center;font-size: 11px;padding:20px 0 0 0;}
		.btn_invalid{background:#cccccc!important;}
		.btn{background: #f84d4d;color: #ffffff;border: 0;width: 100%;height: 40px;line-height: 40px;font-family: 'Microsoft Yahei';border-radius: 6px;margin-top: 10px;font-size: 1em;}
		.sm{font-size: 11px;}
		.sm b{display: block;font-size: 14px;height: 20px;padding-top: 30px;}
		.sm .sm1{padding-bottom: 15px;}
		#masklayer{position: fixed;background: #000000;opacity: 0.7;left: 0;top: 0;width: 100%;height: 100%;z-index: 10;}
		.tck{position: fixed;background: #ffffff;left: 0;top:12%;width: 90%;padding: 5%;z-index: 20;}
		.tck .close{position: absolute;right: 5%;top: 10px;width: 30px;height: 30px;background: url(${static_path}/static/img/xx.png) no-repeat center center;}
		.tck p{padding: 5px 0;margin: 0;line-height: 20px;font-size: 13px;}
		.tck .srmm{font-size: 16px;text-align: center;font-weight: bold;}
		.tck .dz{text-align: center;}
		.tck .dz span{color: #a83030}
		.tck .zfmm{height: 30px;}
		.tck .zfmm span{color: #333333;}
		.tck .zfmm input{height: 26px;line-height: 26px;padding: 0;padding-left: 6px;font-size: 1em;font-family: sans-serif;}
		.none{display: none;}
		.add{background: #f84d4d;margin-bottom: 8px;display: block;text-align: center;text-decoration: none;}
		.error{color:#ff0000;font-size: .5rem;}
		.amountError{display: none;padding-left: 30%;}
		.zfmmError{text-align:center;}
		input{width: 50%;}
		.txyz input{height: 26px;line-height: 26px;padding: 0;padding-left: 5px;}
		.codeBtn{float:left;margin:0 5px; width: 38%;height: 30px;border-radius:4px;line-height: 30px;text-align: center;color: #ffffff;}
		.activeBtn{background: #eb9f2d;display:block;}
		.inactiveBtn{background: #cccccc;}
		.clearfix:after{ content: "."; display: block; height: 0; clear: both; visibility: hidden; font-size:0; }
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
<c:choose>
<c:when test="${tx_info == null}">
	<div class="addcard">
		<a href="${ctx}/index/add_card?openId=${openId}" class="btn add">添加银行卡</a>
	</div>
</c:when>
<c:otherwise>
	<a class="info bank" href="${ctx}/index/check_card?openId=${openId}">
		<span style="background: url(${static_path}/static/img/bank/${tx_info.type}.png) no-repeat 10px 8px;background-size:23px;">${tx_info.strType}</span>
		<em>尾号：${tx_info.account}</em>
		<b></b>
	</a>
</c:otherwise>
</c:choose>
	<div class="info jine">
		<span>提现金额</span>
		<input id="amount" type="number" placeholder="本卡可提余额${tx_info.canWithDrawMoney1}元"/>
	</div>
	<p class="error amountError amountError1">请输入正确的提现金额</p>
	<p class="error amountError amountError2">提现金额必须小于本卡可提余额</p>
	<p class="error amountError amountError3">提现金额大于等于3</p>
	<div class="fee_info">提现费用<span class="feiy">0</span>元，本月还可免费提现<span class="rest_time">${3 - data.withDrawQuota.countWithDrawFee}</span>次。</div>
	<div class="dae_info none">提现金额大于10万，银行可能会分批处理到账，请注意查收.</div>
	<div class="tishi">预计1-2个工作日内到账</div>
	<input type="button" value="立即提现" class="btn btn_invalid tixian1">
	<div class="sm">
		<b>相关说明：</b>
		<p class="sm1">提现费用说明：<strong>当投资人提现金额 ≤总投资额+总收益-已提现总额时，每自然月前3笔免提现手续费。此外，提现手续费收取标准为工\农\建\招 ≤5万/2元、5万一个计费单位；其他银行 ≤2万/2元、2万一个计费单位。</strong></p>
		<p>该卡可提余额说明：因手机支付要求同卡进出，故要求用户使用网站绑定的银行卡提现时，要保留出手机充值金额，使手机绑定的卡可以提走相应的金额，即为该卡可提余额。</p>
	</div>
	<div id="masklayer" class="none"></div>
	<div class="tck none">
		<span class="close"></span>
		<p class="srmm">输入密码</p>
		<p class="dz">提现<span class="txje">***</span>元，实际到账<span class="sjdz">***</span>元</p>
		<p class="zfmm"><input type="password" id="zfmm" style="width: 97%;" placeholder="输入付款时的密码" name="zfmm" /></p>
		<span class="error zfmmError zfmmError1">支付密码不能为空</span>
		<span class="error zfmmError zfmmError2">密码要在6位和16位之间</span>
		<p class="txyz">
			<input type="text" id="randCode" placeholder="输入图片验证码"/>
			<img class="refreshCode" alt="图形验证码" src="" id="imgCode" style="vertical-align: middle;width: 20%;" />
			<a class="refreshCode" href="javascript:void(0);">看不清</a>
		</p>
		<span class="error txyzmError"></span>
		<span class="error tixianError" style="display: block"></span>
		<input type="button" class="btn btn_invalid tixian2" value="立即提现" />
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script>
	(function(w,jq,r,e){
		var tixian1 = jq('.tixian1'),//第一个提现按钮
			tixian2 = jq('.tixian2'),//第二个提现按钮
			feiy = jq('.feiy'),//提现费用
			zfmm = jq('#zfmm'),//支付密码
			amount = jq('#amount'),//提现金额
			txje = jq('.txje'),//提现金额显示
			masklayer = jq('#masklayer'),//阴影层
			tck = jq('.tck'),//输入支付密码
			close = jq('.close'),//关闭阴影层按钮
			sjdz = jq('.sjdz'),//实际到账金额
			randCode = jq('#randCode'),//输入图形验证码
			tixianError = jq('.tixianError'),//提现错误
			txyzmError = jq('.txyzmError'),//图形验证码错误
			error = jq('.error'),//所有错误提示信息
			imgCode = jq('#imgCode'),//图形验证码
			refreshCode = jq('.refreshCode'),//刷新图形验证码
			daeInfo = jq('.dae_info'),//大额提现提示
			personAccountId = '${tx_info.id}',
			bankname = '${tx_info.bankname}',
			province = '${tx_info.province}',
			city = '${tx_info.city}',
			openId = '${openId}',
			phone = '${info.mobile}';
		function refreshImageCode(){
			imgCode.attr('src','${baseUrl}/randCode/randCode.jsp?v=' + Math.random() + '&number=' + phone);
		}
		refreshCode.click(refreshImageCode);
		tixian1.click(function(){
			if(jq(this).hasClass('btn_invalid'))return;//按钮失效状态，返回
			var amountNumber = Number(amount.val());
			sjdz.text((amountNumber - Number(feiy.text())).toFixed(2));//计算到账费用
			txje.text(amountNumber.toFixed(2));
			//显示阴影层
			masklayer.removeClass('none');
			tck.removeClass('none');
		});
		var request_data = {openId:openId,bankname:bankname,province:province,city:city,personAccountId:personAccountId,mobile:phone};
		tixian2.click(function(){
			if(!jq(this).hasClass('btn_invalid')){//按钮有效，发送提现申请请求
				request_data['money'] = amount.val();
				request_data['paypassword'] = zfmm.val();
				request_data['randCode'] = randCode.val();//传入图片验证码
				request_data['feiy'] = feiy.text();
				request_data['txje'] = txje.text();
				request_data['sjdz'] = sjdz.text();
				error.hide();
				r.post('${ctx}/index/ajax/tixian_ajax',request_data,function(data){
					e.alert2(data.info);
					if(data.status == 1 || data.status == 2){
						e.setKnowRedirectUrl('${ctx}' + data.url);
					} else {
						refreshImageCode();
						tixianError.html(data.info).show();
					}
				});
			}
		});
		zfmm.keyup(function(){
			error.hide();
			var mm = jq(this).val();
			if(typeof mm == undefined || mm == '' || mm == null){//判断支付密码是否为空
				tixian2.addClass('btn_invalid');
				valHandler.zfmmError1.show();
				jq(this).focus();
				return;
			}
			if(mm.length < 6 || mm.length > 16){//判断支付密码位数是否正确
				tixian2.addClass('btn_invalid');
				valHandler.zfmmError2.show();
				jq(this).focus();
				return;
			}
			tixian2.removeClass('btn_invalid');
		});
		amount.keyup(function(){
			var val = jq(this).val();
			valHandler.valAmount(val);//判断输入金额是否正确
			valHandler.calculateFee(val);//计算手续费
		});
		close.click(function(){//关闭阴影层
			masklayer.addClass('none');
			tck.addClass('none');
		});
		//验证handler
		var valHandler = {
			amountError1 : jq('.amountError1'),//输入金额错误1
			amountError2 : jq('.amountError2'),//输入金额错误2
			amountError3 : jq('.amountError3'),//输入金额错误3
			zfmmError1 : jq('.zfmmError1'),//支付密码错误1
			zfmmError2 : jq('.zfmmError2'),//支付密码错误2
			rest_time : jq('.rest_time'),//免费提现次数
			valAmount : function(val){
				if(this._flag){//没有银行卡禁止提现
					return;
				}
				var amount_reg = /^[0-9]+(\.[0-9]{0,2})?$/,
					flag = false;
				error.hide();
				if(!amount_reg.test(val)){//验证是否是数字
					this.amountError1.show();
					flag = true;
				}
				if(val < 3 && !flag){//验证提现金额是否小于3
					this.amountError3.show();
					flag = true;
				}
				if(val > Number(this.canWithDrawMoney) && !flag){//验证提现金额是否大于可提金额
					this.amountError2.show();
					flag = true;
				}
				if(flag){//输入金额有问题
					tixian1.addClass('btn_invalid');
					amount.focus();
					return;
				}
				if(val >= 100000){
					daeInfo.removeClass('none');
				} else {
					daeInfo.addClass('none');
				}
				error.hide();
				tixian1.removeClass('btn_invalid');
			},
			calculateFee : function(val){
				var time = this.rest_time.text();
				if(time && Number(time) && (val <= this.qutoa)){//还有免费提现机会
					feiy.text('0');
				} else {
					if(tixian1.hasClass('btn_invalid')){//按钮失效状态，费用为0
						feiy.text('0');
					} else {
						//计算利息
						var feiyong = Math.ceil(val/this.danwei) * 2;
						feiy.text(feiyong);
					}
				}
			},
			init : function(){
				var addCard = jq('.addcard');
				if(addCard.length){//如果没有银行卡
					this._flag = true;//禁止提现标志
				}
				var type = '${tx_info.type}';
				//计算单位
				if([1,3,4,5].indexOf(type) >= 0){
					this.danwei = 50000;
				}else{
					this.danwei = 20000;
				}
				this.qutoa = Number('${data.withDrawQuota.quota}');
				this.canWithDrawMoney = '${tx_info.canWithDrawMoney1}';
			}
		}
		refreshImageCode();
		valHandler.init();
	}(window,$,requestUtil,eloancnUtil))
</script>
</body>
</html>