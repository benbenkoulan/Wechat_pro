(function(w,j,h,d,e,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '';
	window.id = id;
	window.platform = platform;
	var cyz_content = j('#cyz_content'),
		cyz_pulldown = cyz_content.siblings('.pulldown'),
		mylistTemplate = et['static/tpls/sesame/sesame_mylist.handlebars'];
	d.addListener('cyz',{url:'/wechat/sesame/ajax/mylist',data:param_obj,
		content:cyz_content,
		pullDown:cyz_pulldown,
		template:mylistTemplate,
		callback : function(){
			e.pauseCountDown();
			e.refreshEnd();
			e.countDown(1);
		}
	});
	d.setListenerName('cyz');
	d.getData();
	var tabs = j('.tab'),
		infos = j('.info');
	tabs.click(function(){
		tabs.removeClass('active');
		infos.addClass('none');
		var that = j(this);
		that.addClass('active');
		var type = +that.data('type'),
			name = that.data('name');
		infos[type].classList.remove('none');
		d.iScroll.refresh();
		d.setListenerName(name);
		if(!d.getListener(name)){
			var contentName = '#' + name + '_content'
			var content = j(contentName),
				pullDown = content.siblings('.pulldown');
			d.addListener(name,{url:'/wechat/sesame/ajax/mylist',data:{id:id,platform:platform,tranType:type},
				content:content,
				pullDown:pullDown,
				template:mylistTemplate,
				callback : function(){
					e.pauseCountDown();
					e.refreshEnd();
					e.countDown(1);
				}
			});
			d.getData();
		};
	});
})(window,$,Handlebars,dataPage,eloancnUtil,eloancnTemplate);