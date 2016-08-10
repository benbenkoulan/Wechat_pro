Handlebars.registerHelper('compareIndex',function(pos,index,option){
	if(pos == (index + 1)){
		return option.fn(this);
	};
	return option.inverse(this);
})