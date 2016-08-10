Handlebars.registerHelper('compareStatus',function(status,option){
	if(status == 0 || status == 2){
		return option.fn(this);
	}
	return option.inverse(this);
});