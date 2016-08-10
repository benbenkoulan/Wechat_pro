Handlebars.registerHelper('compareProductType',function(type,option){
	if(type == 1){
		return option.fn(this);
	};
	return option.inverse(this);
});
Handlebars.registerHelper('checkVip',function(type,vip_rate,option){
	console.log();
	if(type == 1 && vip_rate){
		return option.fn(this);
	};
	return option.inverse(this);
})