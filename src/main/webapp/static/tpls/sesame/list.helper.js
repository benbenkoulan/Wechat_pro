Handlebars.registerHelper('compareStatus',function(productStatus,surplusSeconds,option){
	if(productStatus == 'pub' && surplusSeconds > 0){
		return option.fn(this);
	} else {
		return option.inverse(this);
	}
});