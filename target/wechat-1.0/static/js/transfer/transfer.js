(function(w,z){
	'use strict'
	var eloancnUtil = w.eloancnUtil,
		requestUtil = w.requestUtil;
	var param_obj = eloancnUtil.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		productId = param_obj.productId || '',
		accId = param_obj.accId || '',
		realGetTip,key,
		passInput = z('input[type="password"]'),
		transferBtn = z('.transfer_btn');
	passInput.bind('input propertychange',function(){
		if(z(this).val().length >= 6) {
			transferBtn.addClass('transfer_btn_active');
		} else {
			transferBtn.removeClass('transfer_btn_active');
		}
	});
	transferBtn.bind('click',function(){
		if(z(this).hasClass('transfer_btn_active')){
			var keyHex = CryptoJS.enc.Utf8.parse(key);
			var encrypted = CryptoJS.DES.encrypt(passInput.val(), keyHex, {
			    mode: CryptoJS.mode.ECB,
			    padding: CryptoJS.pad.Pkcs7
			});
			param_obj.pass = encrypted.toString();
			requestUtil.postLayer({
			url:'/wechat/transfer/ajax/transfer',
			data : param_obj,
			success : function(data){
				eloancnUtil.alert2(data.info);
				data.status && eloancnUtil.setKnowRedirectUrl(data.url);
			},
			content:'转让中..'
		});
		}
	});
	z('#realGetTip').bind('touchstart',function(){
		realGetTip && eloancnUtil.alert2(realGetTip);
	});
	requestUtil.postLayer({
		url:'/wechat/transfer/ajax/transfer_init',
		data : param_obj,
		success : function(data){
			if(data.status){
				var info = data.info || {},
					protocol = info.protocol;
				z('#curRate').text(info.curRate || 0);
				z('#amount').text(info.amount || 0);
				z('#realGetAmount').text(info.realGetAmount || 0);
				z('#keepDays').text(info.keepDays || 0);
				z('#remoteRate').text(info.remoteRate || 0);
				realGetTip = info.realGetTip || '';
				key = info.key || '';
				if(protocol.code == 0){
					z('#protocol').attr('href',protocol.desc);
				}
			} else {
				eloancnUtil.alert2(data.info);
			}
		}
	});
})(window,$);