this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/transfer/list.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<div class=\"transfer_sesame\" data-productid=\""
    + alias4(((helper = (helper = helpers.productId || (depth0 != null ? depth0.productId : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"productId","hash":{},"data":data}) : helper)))
    + "\" data-recordid=\""
    + alias4(((helper = (helper = helpers.recordId || (depth0 != null ? depth0.recordId : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"recordId","hash":{},"data":data}) : helper)))
    + "\" data-accid=\""
    + alias4(((helper = (helper = helpers.accId || (depth0 != null ? depth0.accId : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"accId","hash":{},"data":data}) : helper)))
    + "\" data-userid=\""
    + alias4(((helper = (helper = helpers.userId || (depth0 != null ? depth0.userId : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"userId","hash":{},"data":data}) : helper)))
    + "\">\r\n	<div class=\"transfer_info clearfix\">\r\n		<p class=\"transfer_title left\">"
    + alias4(((helper = (helper = helpers.title || (depth0 != null ? depth0.title : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"title","hash":{},"data":data}) : helper)))
    + "</p>\r\n		<p class=\"right enddate\" data-lasttime=\""
    + alias4(((helper = (helper = helpers.surplusSeconds || (depth0 != null ? depth0.surplusSeconds : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"surplusSeconds","hash":{},"data":data}) : helper)))
    + "\"></p>\r\n	</div>\r\n	<div class=\"transfer_info transfer_info2 clearfix\">\r\n		<div class=\"transfer_amount left\">\r\n			<p class=\"transfer_info_title\">转让金额(元)</p>\r\n			<p class=\"amount\">"
    + alias4(((helper = (helper = helpers.amount || (depth0 != null ? depth0.amount : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"amount","hash":{},"data":data}) : helper)))
    + "</p>\r\n		</div>\r\n		<div class=\"transfer_rate right\">\r\n			<p class=\"transfer_info_title\">预期年化收益</p>\r\n			<p><span>"
    + alias4(((helper = (helper = helpers.minRate || (depth0 != null ? depth0.minRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"minRate","hash":{},"data":data}) : helper)))
    + "</span>%<span>~</span><span>"
    + alias4(((helper = (helper = helpers.maxRate || (depth0 != null ? depth0.maxRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"maxRate","hash":{},"data":data}) : helper)))
    + "</span>%</p>\r\n		</div>\r\n	</div>\r\n	<div class=\"transfer_info transfer_info3 clearfix\">\r\n		<p class=\"transfer_resttime left\">剩余天数:"
    + alias4(((helper = (helper = helpers.surplusDays || (depth0 != null ? depth0.surplusDays : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"surplusDays","hash":{},"data":data}) : helper)))
    + "天</p>\r\n		<p class=\"transfer_closeperiod right\">封闭期:"
    + alias4(((helper = (helper = helpers.closePeriod || (depth0 != null ? depth0.closePeriod : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"closePeriod","hash":{},"data":data}) : helper)))
    + "天</p>\r\n	</div>\r\n	<div class=\"invest_btn "
    + ((stack1 = (helpers.compareStatus || (depth0 && depth0.compareStatus) || alias2).call(alias1,(depth0 != null ? depth0.status : depth0),{"name":"compareStatus","hash":{},"fn":container.program(2, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "\" data-status=\""
    + alias4(((helper = (helper = helpers.status || (depth0 != null ? depth0.status : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"status","hash":{},"data":data}) : helper)))
    + "\">"
    + alias4(((helper = (helper = helpers.statusCN || (depth0 != null ? depth0.statusCN : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"statusCN","hash":{},"data":data}) : helper)))
    + "</div>\r\n</div>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    return "invest_btn_active";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});;Handlebars.registerHelper('compareStatus',function(status,option){
	if(status == 0 || status == 2){
		return option.fn(this);
	}
	return option.inverse(this);
});