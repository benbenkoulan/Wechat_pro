Handlebars.registerHelper('compareStatus',function(status,pid,accId){
	if(status == 0 || status == 1 || status == 2 || status == 5 || status == 6){
		return '/wechat/html/sesame/my_sesame_detail.html?id=' + window.id + '&platform=' + window.platform + '&pid=' + pid + '&tranType=' + status + '&accId=' + accId;
	};
	return 'javascript:void(0)';
});
Handlebars.registerHelper('getStatus',function(status){
	if(status == 1){
		return '封闭期';
	} else if(status == 5){
		return '审核中';
	} else {
		return '';
	}
});
Handlebars.registerHelper('isSettled',function(status,option){
	if(status == 3 || status == 4){
		return option.fn(this);
	};
	return option.inverse(this);
});
Handlebars.registerHelper('isCountDown',function(status,option){
	if(status == 1 || status == 2){
		return option.fn(this);
	};
	return option.inverse(this);
})