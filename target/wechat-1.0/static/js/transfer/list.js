(function(w,z,et){
	'use strict'
	var dataPageUtil = w.dataPageUtil,
		eloancnUtil = w.eloancnUtil,
		transferContent = z('#transfer_content'),
		transferPullDown = z('.pulldown'),
		transferTemplate = et['static/tpls/transfer/list.handlebars'];
	var param_obj = eloancnUtil.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		transfer_data = {id:id,platform:platform};
	dataPageUtil.addListener('transfer', {url:'/wechat/transfer/ajax/list',page: 1,data:transfer_data,
		pullDown: transferPullDown,
        content: transferContent,
        template: transferTemplate,
        callback : function(){
    		eloancnUtil.pauseCountDown();
    		eloancnUtil.refreshEnd();
    		eloancnUtil.countDown();
    	}
    });
	dataPageUtil.setListenerName('transfer');
	dataPageUtil.getData();
	var transfer_filter = z('.transfer_filter'),
		transfer_condition1s = z('.transfer_condition1'),
		transfer_condition2s = z('.transfer_condition2');
	z('.transfer_filter_btn').bind('click',transfer_filter.show.bind(transfer_filter));
	z('.filter_cancel').bind('click',transfer_filter.hide.bind(transfer_filter));
	z('.filter_ok').bind('click',function(){
		transferContent.html('');
		transferPullDown.html('');
		transferPullDown.addClass('loading');
		transferPullDown.removeClass('none');
		transfer_data.page = 1;
		transfer_data.timeType = z('.transfer_condition_active1').data('id') || 0;
		transfer_data.amountType = z('.transfer_condition_active2').data('id') || 0;
		dataPageUtil.refreshRequestData('transfer',transfer_data);
		dataPageUtil.getData();
		transfer_filter.hide();
	});
	transfer_condition1s.bind('touchstart',function(){
		transfer_condition1s.removeClass('transfer_condition_active1');
		z(this).addClass('transfer_condition_active1');
	});
	transfer_condition2s.bind('touchstart',function(){
		transfer_condition2s.removeClass('transfer_condition_active2');
		z(this).addClass('transfer_condition_active2');
	});
	transferContent.on('click','.transfer_info',function(){
		var transfer = z(this).parent();
		w.location.href = '/wechat/html/transfer/detail.html?id=' + id + '&platform=' + platform + '&productId=' + transfer.data('productid') + '&accId=' + transfer.data('accid') + '&userId=' + transfer.data('userid') + '&recordId=' + transfer.data('recordid');
	});
	eloancnUtil.setOkAction(function(){
		requestUtil.postLayer({
			url:'/wechat/transfer/ajax/cancel_transfer',
			data : param_obj,
			success : function(data){
				eloancnUtil.alert2(data.info);
				data.status && eloancnUtil.setKnowRedirectUrl(data.url);
			},
			content : '取消转让中..'
		});
	});
	transferContent.on('click','.invest_btn_active',function(){
		var transfer = z(this).parent();
		var productId = transfer.data('productid'),
			accId = transfer.data('accid'),
			userId = transfer.data('userid'),
			recordId = transfer.data('recordid');
		if(z(this).data('status') == 0){			
			w.location.href = '/wechat/html/transfer/invest.html?id=' + id + '&platform=' + platform + '&productId=' + productId + '&accId=' + accId + '&userId=' + userId + '&recordId=' + recordId + '&v=' + Math.random();
		} else {
			param_obj.productId = productId;
			param_obj.accId = accId;
			eloancnUtil.alert1('确定要取消转让吗?');
		}
	});
})(window,$,eloancnTemplate);