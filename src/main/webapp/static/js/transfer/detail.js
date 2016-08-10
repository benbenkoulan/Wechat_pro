(function(w,z,et){
	'use strict'
	var dataPageUtil = w.dataPageUtil,
		eloancnUtil = w.eloancnUtil,
		requestUtil = w.requestUtil;
	var param_obj = eloancnUtil.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		productId = param_obj.productId || '',
		accId = param_obj.accId || '',
		userId = param_obj.userId || '',
		recordId = param_obj.recordId || '';
	var transfer_detail_tabs = z('.transfer_detail_tab'),
		transfer_detail_info1 = z('.transfer_detail_info1'),
		transfer_detail_info2 = z('.transfer_detail_info2'),
		tenderContent = z('.transfer_tenders'),
		tenderPullDown = z('.pulldown'),
		buttArea = z('#butt_area'),
		tenderTemplate = et['static/tpls/transfer/tender.handlebars'],
		incomeTemplate = et['static/tpls/transfer/income.handlebars'],
		jiant = z('#jiant'),
		jiant2 = z('#jiant2'),
		profits = z('.profits'),
		incomeObj = {},status;
	transfer_detail_tabs.bind('touchstart',function(){
		transfer_detail_tabs.removeClass('transfer_detail_tab_active');
		z(this).addClass('transfer_detail_tab_active');
		if(z(this).hasClass('transfer_detail_tab1')){
			transfer_detail_info1.removeClass('none');
			transfer_detail_info2.addClass('none');
		} else {
			transfer_detail_info2.removeClass('none');
			transfer_detail_info1.addClass('none');
		}
		if(!dataPageUtil.getListener('tender')){
			dataPageUtil.addListener('tender', {url:'/wechat/transfer/ajax/tenders',page: 1,data:param_obj,
				pullDown: tenderPullDown,
		        content: tenderContent,
		        template: tenderTemplate
		   	});
			dataPageUtil.setListenerName('tender');
			dataPageUtil.getData();
		}
		dataPageUtil.refreshIScroll();
	});
	z('.check_profit').bind('touchstart',function(){
		jiant2.toggleClass('none');
		jiant.toggleClass('none');
		profits.toggleClass('none');
		dataPageUtil.refreshIScroll();
	});
	z('#tip').bind('touchstart',function(){
		eloancnUtil.alert2('','每日付息=本金*(借款利率-服务费) / 12<br>每日计息=每月付息/当月天数<br>每月最后一日计息=每月付息-当月每日计息*(当月天数-1)<br>【以上金额均保留两位小数】')
	});
	tenderContent.on('click','.transfer_tender',function(){
		w.location.href = z(this).data('url') || '#';
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
	buttArea.on('click','.invest_btn_active',function(){
		if(status == 0){
			w.location.href = '/wechat/html/transfer/invest.html?id=' + id + '&platform=' + platform + '&productId=' + productId + '&accId=' + accId + '&userId=' + userId + '&recordId=' + recordId + '&v=' + Math.random();
		} else if(status ==2){
			eloancnUtil.alert1('确定要取消转让吗?');
		}
	});
	requestUtil.postLayer({
		url:'/wechat/transfer/ajax/detail_init',
		data : param_obj,
		success : function(data){
			if(data.status){
				var info = data.info || {},
					investDesc = info.investDesc || {},
					transferDesc = info.transferDesc || {},
					exitDesc = info.exitDesc || {};
				z('#transferTitle').text(info.title || '');
				z('#amount').text(info.amount || 0);
				z('#minRate').text(info.minRate || 0);
				z('#maxRate').text(info.maxRate || 0);
				z('#restTime').text(info.surplusDays || 0);
				z('#closePeriod').text(info.closePeriod || 0);
				z('#endDate').data('lasttime',info.surplusSeconds || '');
				z('#payInMode').text(investDesc.payInMode || '');
				z('#carryInTime').text(investDesc.carryInTime || '');
				z('#payInTime').text(investDesc.payInTime || '');
				z('#endCloseDate').text(transferDesc.endCloseDate || '');
				z('#transRule').text(transferDesc.transRule || '');
				z('#transAmount').text(transferDesc.transAmount || '');
				z('#transLimit').text(transferDesc.transLimit || '');
				z('#expireTime').text(exitDesc.expireTime || '');
				incomeObj.curRatePos = info.curRatePos;
				incomeObj.incomeTable = info.incomeTable;
				z('#incomeTable').append(incomeTemplate(incomeObj));
				var butt_class = 'invest_btn ';
				status = info.status;
				if(status == 0 || status == 2){
					butt_class += 'invest_btn_active';
				}
				buttArea.html('<div class=\"' + butt_class + '\">' + info.statusCN + '</div>');
				eloancnUtil.countDown();
			} else {
				eloancnUtil.alert2(data.info);
			}
		}
	});
})(window,$,eloancnTemplate);