(function(w,z,e,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '';
	var content = z('#wmps_content'),
		pullDown = z('.pulldown'),
		wmps = z('.wmps'),
		template = et['static/tpls/wmps/list.handlebars'];
	scrollUtil.addPageScroll('wmps',content,pullDown,template,{
		url:'/wechat/wmps/ajax/list',
		data : {id:id,platform:platform},
		callback : function(){
			e.pauseCountDown();
    		e.refreshEnd();
    		e.countDown();
		}
	});
	scrollUtil.setCurrentPageScroll('wmps');
	scrollUtil.fetchData();
	content.on('click','.content',function(){
		var detailurl = z(this).parent().data('detailurl');
		detailurl += '&v=' + Math.random();
		w.location.href = detailurl;
	});
	content.on('click','.invest_btn_active',function(){
		var investurl = z(this).parent().data('investurl');
		investurl += '&v=' + Math.random();
		w.location.href = investurl;
	});
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
})(window,$,eloancnUtil,eloancnTemplate);