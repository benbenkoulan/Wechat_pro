Handlebars.registerHelper('compare_btn',function(status,lasttime,option){
	if(status == 1 && lasttime > 0){
		return option.fn(this);
	}
	return option.inverse(this);
});
Handlebars.registerHelper('compareStatus',function(status,lasttime){
	if(status == 1){
		return lasttime;
	}
	return 0;
});