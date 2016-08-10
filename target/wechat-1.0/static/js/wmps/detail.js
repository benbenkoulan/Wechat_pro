document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
(function(w,j,h,r,d,m,e,et) {
	'use strict';
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '';
	var tzlbContent = j('#tzlb_content'),
		tzlbTemplate = et['static/tpls/wmps/wmps_tzxq.handlebars'],
		zqlbTemplate = et['static/tpls/wmps/wmps_zqlb.handlebars'],
		tzlb = j('#tzlb'),
		zqlb = j('#zqlb'),
		cpxq = j('#cpxq');
	r.postLayer('/wechat/wmps/ajax/detail/' + pid,{id:id,platform:platform},function(data){
		if(data.status){
			var info = data.info;
			var title = j('#title');
			if(info.icon){
				title.append('<img src="' + info.icon + '" style="width:1.1rem;vertical-align:sub;">');
			}
			title.append('翼农计划' + info.period);
			j('#strPhases').text(info.term + '天');
			j('#twoAmount').text(info.totalAmount + '/' + info.maxAmount + '万');
			j('#restAmount').append(info.restAmount + '万');
			j('#number').append(info.number);
			j('#percentage').append(info.percentage + '%');
			j('.line').css('width',info.percentage + '%');
			j('#strInterdate').append(info.strInterdate);
			j('#firstInterDate').append(info.firstInterDate);
			j('#outDate').append(info.outDate);
			if(info.status == 1){
				j('#butt_area').append('<a class="butt" href="/wechat/html/wmps/invest.html?pid=' + pid + '&id=' + id + '&platform=' + platform + '&v=' + Math.random() + '">马上投资</a>');
				j('.enddate').data('lasttime',info.lasttime);
				e.countDown();
			} else {
				j('#butt_area').append('<div class="butt" style="background: #c1c1c1">已结束</div>');
			}
			var investors = info.investors || [],
				interest_area = '<span style="font-size:1.4rem;color:#e93a3a;">' + info.interest1 + '</span><span style="font-size:0.8rem;color:#6e6e6e;">%</span>',
				interest2 = +info.interest2,
				strRatio = +info.strRatio;
			if(interest2){
				interest_area += '<sup style="color:#e93a3a;font-size:0.6rem;">+' + interest2 + '&nbsp;</sup>';
			}
			if(strRatio){
				interest_area += '<sup style="color:#e93a3a;font-size:0.6rem;">+' + strRatio + '</sup><img style="width:1rem;" src="/wechat/static/img/vip.png"/>';
			}
			j('#tzlb_content').append(tzlbTemplate(investors));
			var investors_len = investors.length;
			if(!investors_len){
				tzlb.append('<div style="width:100%;text-align:center;margin-top:30px;">暂无数据</div>');
			} else if(investors_len >= 10){
				tzlb.append('<div class="pulldown" data-listenername="tzlb">上拉或点击加载更多</div>');
			} else if(investors_len < 10){
				tzlb.find('.pulldown').remove();
			}
			j('#interests').append(interest_area);
			w.tenderDetailUrl = info.tenderDetailUrl || '';
		}
	});
	var tab = j('.tab'),
		tab_page = j('.tab_page');
	j('#cp').click(function(){
		tab.removeClass('jd');
		j(this).addClass('jd');
		tab_page.addClass('none');
		cpxq.removeClass('none');
		d.iScroll.refresh();
        d.setListenerName('')
	});
	j('#zq').click(function(){
		tab.removeClass('jd');
		j(this).addClass('jd');
		tab_page.addClass('none');
		zqlb.removeClass('none');
		d.iScroll.refresh();
        d.setListenerName('zqlb');
        if (!d.getListener('zqlb')) {
            var content = j('#zqlb_content'),
                pullDown = content.siblings('.pulldown');
            d.addListener('zqlb', {url:'/wechat/wmps/ajax/tenders/' + pid,page: 1,
                pullDown: pullDown,
                content: content,
                template: zqlbTemplate
            });
            d.getData();
        }
	});
	j('#tz').click(function(){
		tab.removeClass('jd');
		j(this).addClass('jd');
		tab_page.addClass('none');
		tzlb.removeClass('none');
		d.iScroll.refresh();
        d.setListenerName('tzlb');
        if (!d.getListener('tzlb')) {
            d.addListener('tzlb', {url:'/wechat/wmps/ajax/invest/list/' +pid,page: 2,
                data: {id: id,platform: platform},
                pullDown: tzlbContent.siblings('.pulldown'),
                content: tzlbContent,
                template: tzlbTemplate
            });
        }
	});
	j('.tab_page').delegate('.pulldown','click',function(){
		var listenerName = j(this).data('listenername');
		d.setListenerName(listenerName);
		d.getData();
	});
})(window, $, Handlebars, requestUtil, dataPage,Math,eloancnUtil,eloancnTemplate);