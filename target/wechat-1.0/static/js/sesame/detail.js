(function(w,j,r,e,d,h,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform,
		pid = param_obj.pid || '',
		tenderDetailUrl = '',
		syll = j('#syll'),
		jiant = j('#jiant'),
		jiant2 = j('#jiant2'),
		syTemplate = et['static/tpls/sesame/sesame_sy.handlebars'],
		tzlbTemplate = et['static/tpls/sesame/sesame_tzlb.handlebars'],
		zqlbTemplate = et['static/tpls/sesame/sesame_zqlb.handlebars'];
	r.postLayer('/wechat/sesame/ajax/detail/' + pid,param_obj,function(data){
		if(data.status){
			var info = data.info || {};
			var totalAmount = info.totalAmount || 0,
				maxAmount = info.maxAmount || 0,
				percentage = (info.percentage || '0') + '%';
			j('#twoAmount').append(totalAmount + '/' + maxAmount + '万');
			j('#title').append(info.title || '');
			j('#closePeriod').append(info.closePeriod || '');
			j('#rate').append(percentage);
			j('#chiyou_time').val(info.holdTime || 0);
			j('.line').css('width',percentage);
			j('#interests').append('<span style="font-size:22px;color:#e93a3a;">' + info.minRate + '</span>%<span style="font-size:22px;color:#e93a3a;">&nbsp;&nbsp;~&nbsp;&nbsp;' + info.maxRate + '</span> %');
			j('#holdTime').append(info.holdTime || '');
			j('#number').append(info.number || '');
			j('#expireTime').append(info.exitTimeStr || '');
			j('#tzsx').append(info.everyAmountStr || '');
			j('#fxsj').append(info.payInterstTimeStr || '');
			j('#endCloseDateStr').append(info.endCloseDateStr || '');
			j('#transferRule').append(info.transferRule || '');
			j('#transferAmount').append(info.transferAmount || '');
			j('#transferLimit').append(info.transferLimit || '');
			if(info.productStatus == 'pub'){
				j('#butt_area').append('<a class="butt" href="/wechat/html/sesame/invest.html?pid=' + pid + '&id=' + id + '&platform=' + platform + '&v=' + Math.random() + '">马上投资</a>');
			} else {
				j('#butt_area').append('<div class="butt" style="background: #c1c1c1">已结束</div>');
			};
			syll.append(syTemplate(info.incomeTable || []));
			tenderDetailUrl = info.tenderDetailUrl || '';
			j('.enddate').data('lasttime',info.lasttime);
			e.countDown();
		};
	});	
	var tabs = j('.tab'),
		tab_pages = j('.tab_page'),
		cpxq = j('#cpxq'),
		zqlb = j('#zqlb'),
		tzlb = j('#tzlb');
	j('#cp').click(function(){
		tabs.removeClass('jd');
		j(this).addClass('jd');
		tab_pages.addClass('none');
		cpxq.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('');
	});
	j('#zq').click(function(){
		tabs.removeClass('jd');
		j(this).addClass('jd');
		tab_pages.addClass('none');
		zqlb.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('zqlb');
		if (!d.getListener('zqlb')) {
			 var content = j('#zqlb_content'),
           	 pullDown = content.siblings('.pulldown');
           d.addListener('zqlb', {url:'/wechat/sesame/ajax/tenders/' + pid,
               pullDown: pullDown,
               content: content,
               template: zqlbTemplate
           });
           d.getData();
       }; 
	});
	j('#tz').click(function(){
		tabs.removeClass('jd');
		j(this).addClass('jd');
		tab_pages.addClass('none');
		tzlb.removeClass('none');
		d.iScroll.refresh();
		d.setListenerName('tzlb');
		if (!d.getListener('tzlb')) {
			 var content = j('#tzlb_content'),
            	 pullDown = content.siblings('.pulldown');
            d.addListener('tzlb', {url:'/wechat/sesame/ajax/investors/' + pid,page: 1,
                data: {id: id,platform: platform},
                pullDown: pullDown,
                content: content,
                template: tzlbTemplate
            });
            d.getData();
        }; 
	});
	j('.tip').bind('touchstart',function(){
		e.alert2();
	});
	j('#cksy').click(function(){
		if(syll.hasClass('none')){
			jiant2.addClass('none');
			jiant.removeClass('none');
			syll.removeClass('none');
		} else {
			jiant.addClass('none');
			jiant2.removeClass('none');
			syll.addClass('none');
		};
		d.iScroll.refresh();
	});
	j('.tab_page').delegate('.pulldown','click',function(){
		var listenerName = j(this).data('listenername');
		d.setListenerName(listenerName);
		d.getData();
	});
	var touziAmount = j('#touzi_amount'),
		chiyouTime = j('#chiyou_time'),
		caculateProfit = function(amount,interest,time){
			amount = amount || 0;
			time = time || 0;
			interest = interest || 0;
		var p1 = Math.ceil(((amount * interest) / 365) * 100) / 100;
		var profit = Math.ceil((p1 * time) * 10) / 10;
		};
})(window,$,requestUtil,eloancnUtil,dataPage,Handlebars,eloancnTemplate);