(function(w,j,r,e){
	'use strict';
	r.postLayer('/wechat/public/ajax/myaccount',e.getParams(),function(data){
	if(data.status){
		var info = data.info;
		var accountInfo = info.accountInfo || {},
			vipInfo = info.vipInfo || {},
			linkInfo = info.linkInfo || {};
		j('#name').append(accountInfo.realname || '');
		j('#today_profit').html(accountInfo.inter || '0.0');
		j('#total_amount').append('<b>' + (accountInfo.total2 || '') + '</b>');
		j('#available_amount').append('<b>' + (accountInfo.balance || '') + '</b>');
		j('#redbag_number').html(accountInfo.useableRedPacks || '');
		if(vipInfo.strRatio){
			j('#headImage').attr('src','/wechat/static/img/v2/vip_01.png');
		}else{
			j('#headImage').attr('src','/wechat/static/img/v2/vip_02.png');
		};
		j('.link').bind('click',function(){
			var type = j(this).data('type');
			w.location.href = linkInfo[type] || '#';
		});
		var account_page = j('#account_page'),
			account_page_height = account_page.height();
		account_page.height(account_page_height + 55 + 'px');
		};
	});
})(window,$,requestUtil,eloancnUtil);