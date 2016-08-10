Handlebars.registerHelper('comparePos',function(pos,index){
	if(pos == (++index)){
		return 'profit_pos';
	};
	return '';

});