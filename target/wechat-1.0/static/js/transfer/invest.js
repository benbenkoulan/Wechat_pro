(function(w,z){
	'use strict';
	'use strict'
	var eloancnUtil = w.eloancnUtil,
		requestUtil = w.requestUtil;
	var param_obj = eloancnUtil.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		productId = param_obj.productId || '',
		accId = param_obj.accId || '',
		userId = param_obj.userId || '',
		recordId = param_obj.recordId || '',
		transferAmount,realPayAmount,realPayTip;
	var investBtn = z('.invest_btn_active');
	requestUtil.postLayer({
		url:'/wechat/transfer/ajax/invest_init',
		data : param_obj,
		success : function(data){
			if(data.status){
				var info = data.info || {},
				protocol = info.protocol || {};
				z('#transferTitle').text(info.title || '');
				z('#minRate').text(info.minRate || 0);
				z('#maxRate').text(info.maxRate || 0);
				z('#expirePayIn').text(info.expirePayIn || 0);
				z('#surplusDays').text(info.surplusDays || '');
				z('#closePeriod').text(info.closePeriod || '');
				z('#balance').text(info.balance || 0);
				transferAmount = info.amount || 0;
				z('#transferAmount').text(transferAmount);
				realPayAmount = info.realPayAmount || 0;
				z('#realPayAmount').text(realPayAmount);
				param_obj.amount = realPayAmount
				realPayTip = info.realPayTip;
				if(protocol.code == 0){
					z('#protocol').attr('href',protocol.desc);	
				}
				z('#endDate').data('lasttime',info.surplusSeconds || 0);
				eloancnUtil.countDown();
			} else {
				eloancnUtil.alert2(data.info);
			}
		}
	});
	z('#realPayTip').bind('touchstart',function(){
		realPayTip && eloancnUtil.alert2(realPayTip);
	});
	eloancnUtil.setOkAction(function(){
		investBtn.unbind('click');
		investBtn.removeClass('invest_btn_active');
		requestUtil.postLayer({
			url:'/wechat/transfer/ajax/invest',
			data : param_obj,
			success : function(data){
				eloancnUtil.alert2(data.info);
				data.status && eloancnUtil.setKnowRedirectUrl(data.url);
			},
			content : '投资中..'
		});
	});
	investBtn.bind('click',function(){
		eloancnUtil.alert1('您确定要投资' + transferAmount + '元?');
	});
})(window,$);