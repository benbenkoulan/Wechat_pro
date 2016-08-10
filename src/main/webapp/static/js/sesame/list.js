(function(w,z,e,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '';
	w.id = id;
	w.platform = platform;	
	var content = z('#sesame_content'),
		pullDown = content.siblings('.pulldown'),
		template = et['static/tpls/sesame/list.handlebars'];
	scrollUtil.addPageScroll('sesame',content,pullDown,template,{
		url:'/wechat/sesame/ajax/list',
		data : {id:id,platform:platform},
		callback : function(){
			e.pauseCountDown();
    		e.refreshEnd();
    		e.countDown();
		}
	});
	scrollUtil.setCurrentPageScroll('sesame');
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
})(window,$,eloancnUtil,eloancnTemplate);