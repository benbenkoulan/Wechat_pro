this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/transfer/income.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3=container.escapeExpression, alias4="function";

  return "<li class=\"clearfix "
    + alias3((helpers.comparePos || (depth0 && depth0.comparePos) || alias2).call(alias1,(depths[1] != null ? depths[1].curRatePos : depths[1]),(data && data.index),{"name":"comparePos","hash":{},"data":data}))
    + "\">\r\n	<div class=\"left\">"
    + alias3(((helper = (helper = helpers.ratePhase || (depth0 != null ? depth0.ratePhase : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"ratePhase","hash":{},"data":data}) : helper)))
    + "</div>\r\n	<div class=\"left\">"
    + alias3(((helper = (helper = helpers.curRate || (depth0 != null ? depth0.curRate : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"curRate","hash":{},"data":data}) : helper)))
    + "</div>\r\n</li>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.incomeTable : depth0),{"name":"each","hash":{},"fn":container.program(1, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true,"useDepths":true});;Handlebars.registerHelper('comparePos',function(pos,index){
	if(pos == (++index)){
		return 'profit_pos';
	};
	return '';

});