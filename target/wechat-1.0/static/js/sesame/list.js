(function(w,z,e,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '';
	w.id = id;
	w.platform = platform;	
	var content = z('#sesame_content'),
		pullDown = content.siblings('.pulldown'),
		template = et['static/tpls/sesame/sesame_list.handlebars'];
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
})(window,$,eloancnUtil,eloancnTemplate);