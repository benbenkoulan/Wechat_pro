(function(w,j,r,h,e){
	'use strict'
	var param_obj = e.getParams();
	var amount = param_obj.amount || 0;
	var tenderNumber = j('#tender_number'),
		tenders = j('#tenders'),
		tenderTpl = j('#tender_tpl'),
		enddate = j('#enddate'),
		btn = j('#btn');
	var tenderTemplate = h.compile(tenderTpl.html());
	var refreshTender = function(){
		r.postLayer('/wechat/wmps/ajax/gettenders',param_obj,function(data){
			if(data.status){
				var info = data.info || {};
				var tenders_arr = info.TenderResult || [];
				tenderNumber.html(info.tenderCount);
				tenders.html(tenderTemplate(tenders_arr));
				enddate.data('lasttime',info.strongTime);
				btn.addClass('btn_active');
				e.countDown(2,function(){
					btn.removeClass('btn_active');
				});
			};
		},'刷新中..');	
	};
	refreshTender();
	btn.bind('touchstart',function(){
		if(!j(this).hasClass('btn_active'))return;
		e.setOkAction(function(){
			e.hideLayer();
			e.pauseCountDown();
			btn.removeClass('btn_active');
			r.postLayer('/wechat/wmps/ajax/invest',param_obj,function(data){
				e.alert2(data.info);
				if(data.status){
					e.setKnowRedirectUrl(data.url);	
				}
			},'投资中..');	
		});
		e.alert1('您确定要投资' + amount + '元?');
	});
	j('#invest_amount').append(amount);
	j('.header img,.header span').bind('touchstart',refreshTender);
})(window,$,requestUtil,Handlebars,eloancnUtil);