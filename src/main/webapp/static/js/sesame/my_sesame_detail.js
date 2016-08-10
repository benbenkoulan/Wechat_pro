(function(w,j,r,e,h,d,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '',
		accId = param_obj.accId || '',
		tenderDetailUrl;
	r.postLayer('/wechat/sesame/ajax/mysesame/' + pid,param_obj,function(data){
		if(data.status){
			var info = data.info;
			j('#title').append(info.title);
			j('#curPaidinRate').append(info.curPaidinRate);
			j('#hadPaidInterest').append((info.hadPaidInterest || 0) + '元');
			j('#keepDays').append(info.keepDays);
			j('#promoteRate').append(info.promoteRate);
			j('#amount').append((info.amount || 0) + '元');
			var closedPeriod = info.closedPeriod || 0;
			j('#closedPeriod').append(closedPeriod);
			j('#closedPeriod2').append(closedPeriod + '天');
			j('#interstTimeStr').append(info.interstTimeStr);
			j('#paidInterestRate').append((info.paidInterestRate || 0) + '%');
			j('#interestModeStr').append(info.interestModeStr);
			j('#payInterstTimeStr').append(info.payInterstTimeStr);
			j('#exitTimeStr').append(info.exitTimeStr || '');
			j('#expirePaidInRate').append((info.expirePaidInRate || 0) + '%');
			j('#expirePaidInterest').append((info.expirePaidInterest || 0) + '元');
			j('#server_url').attr('href',(info.protocolUrl || {}).desc || '');
			var incomeTemplate = et['static/tpls/sesame/sesame_income.handlebars'],
				paidTemplate = et['static/tpls/sesame/sesame_paid.handlebars'];
			j('#htime').append(incomeTemplate({incomeTable:info.incomeTable,curRatePos:info.curRatePos}));
			j('#fxjl_xq').append(paidTemplate(info.paidTable || []));
			tenderDetailUrl = info.tenderDetailUrl;
			var class_text = 'butt',
				style_text = '',
				hadHoldDay_text = '';
			switch(info.status){
				case 0:{
					class_text += ' transfer';
				};break;
				case 5:{
					hadHoldDay_text = '审核中';
					style_text = 'style="background:#cccccc;"';
				};break;
				case 1:
				case 6:{
					style_text = 'style="background:#cccccc;"';
				};break;
				case 2:{
					class_text += ' cancelTransfer';
				};break;
			}
			j('#butt_area').append('<div class="' + class_text + '" ' + style_text + '>' + info.statusCNNew + '</div>');
			j('#hadHoldDays').append(hadHoldDay_text || ((info.hadHoldDays || 0) + '天'));
			j('.transfer').click(transfer_fun);
			j('.cancelTransfer').click(cancel_transfer_fun);
			d.iScroll.refresh();
		};
	});
	var transfer_fun = function(){
		w.location.href = '/wechat/html/transfer/transfer.html?id=' + id + '&platform=' + platform + '&accId=' + accId + '&productId=' + pid;
	};
	var cancel_transfer_fun = function(){
		e.alert1('确定要取消转让吗?');
	};
	var tabs = j('.tab'),
		info = j('.info'),
		cpxq = j('#cpxq'),
		zqlb = j('#zqlb'),
		fxjl = j('#fxjl');
	e.setOkAction(function(){
		e.hideLayer();
		r.postLayer('/wechat/transfer/ajax/cancel_transfer', {id:id,platform:platform,accId:accId,productId:pid},function(data){
			e.alert2(data.info);
			data.status && e.setKnowRedirectUrl(data.url);
		},'取消转让中..');
	});
	j('#cp').click(function(){
		tabs.removeClass('jd');
		info.addClass('none');
		j(this).addClass('jd');
		cpxq.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('');
	});
	j('#zq').click(function(){
		tabs.removeClass('jd');
		info.addClass('none');
		j(this).addClass('jd');
		zqlb.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('zqlb');
		if(!d.getListener('zqlb')){
			var content = j('#zqlb_content'),
				pullDown = content.siblings('.pulldown'),
				zqlbTemplate = et['static/tpls/sesame/sesame_zqlb.handlebars'];
			d.addListener('zqlb',{url:'/wechat/sesame/ajax/mytenders/' + pid,data:{id:id,platform:platform,accId:accId},content:content,pullDown:pullDown,template:zqlbTemplate});
			d.getData();
		}
	});
	j('#fx').click(function(){
		tabs.removeClass('jd');
		info.addClass('none');
		j(this).addClass('jd');
		fxjl.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('');
	});
})(window,$,requestUtil,eloancnUtil,Handlebars,dataPage,eloancnTemplate);