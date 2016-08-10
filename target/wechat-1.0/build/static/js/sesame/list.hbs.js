this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/sesame/list.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<div class=\"sesame\" data-detailurl=\""
    + alias4(((helper = (helper = helpers.detail_url || (depth0 != null ? depth0.detail_url : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"detail_url","hash":{},"data":data}) : helper)))
    + "\" data-investurl=\""
    + alias4(((helper = (helper = helpers.invest_url || (depth0 != null ? depth0.invest_url : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"invest_url","hash":{},"data":data}) : helper)))
    + "\">\r\n	<div class=\"content content1 clearfix\">\r\n		<p class=\"left\">"
    + alias4(((helper = (helper = helpers.title || (depth0 != null ? depth0.title : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"title","hash":{},"data":data}) : helper)))
    + "</p>\r\n		<p class=\"right enddate\" data-lasttime=\""
    + alias4(((helper = (helper = helpers.surplusSeconds || (depth0 != null ? depth0.surplusSeconds : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"surplusSeconds","hash":{},"data":data}) : helper)))
    + "\"></p>\r\n	</div>\r\n	<div class=\"content content2 clearfix\">\r\n		<div class=\"left left1\">\r\n			<p class=\"title\">封闭期</p>\r\n			<p class=\"info1\"><span>"
    + alias4(((helper = (helper = helpers.closed_period || (depth0 != null ? depth0.closed_period : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"closed_period","hash":{},"data":data}) : helper)))
    + "</span>天</p>\r\n		</div>\r\n		<div class=\"right right1\">\r\n			<p class=\"title\">预期年化收益</p>\r\n			<p class=\"main_content clearfix\">\r\n				<span class=\"rate\">"
    + alias4(((helper = (helper = helpers.minRate || (depth0 != null ? depth0.minRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"minRate","hash":{},"data":data}) : helper)))
    + "</span>\r\n				<span class=\"sup\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.addRate : depth0),{"name":"if","hash":{},"fn":container.program(2, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.vipRate : depth0),{"name":"if","hash":{},"fn":container.program(4, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</span>\r\n				<span class=\"percent\">%</span>\r\n				<span class=\"rate\">&nbsp;&nbsp;~&nbsp;&nbsp;"
    + alias4(((helper = (helper = helpers.maxRate || (depth0 != null ? depth0.maxRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"maxRate","hash":{},"data":data}) : helper)))
    + "</span>\r\n				<span class=\"sup\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.addRate : depth0),{"name":"if","hash":{},"fn":container.program(2, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.vipRate : depth0),{"name":"if","hash":{},"fn":container.program(4, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</span>\r\n				<span class=\"percent\">%</span>\r\n			</p>\r\n		</div>\r\n	</div>\r\n	<div class=\"content content3 clearfix\">\r\n		<p class=\"left\">最长持有时间:"
    + alias4(((helper = (helper = helpers.investPeriod || (depth0 != null ? depth0.investPeriod : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"investPeriod","hash":{},"data":data}) : helper)))
    + "天</p>\r\n		<p class=\"right\">募集金额:"
    + alias4(((helper = (helper = helpers.total_amount || (depth0 != null ? depth0.total_amount : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"total_amount","hash":{},"data":data}) : helper)))
    + "/"
    + alias4(((helper = (helper = helpers.max_amount || (depth0 != null ? depth0.max_amount : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"max_amount","hash":{},"data":data}) : helper)))
    + "万</p>\r\n	</div>\r\n"
    + ((stack1 = (helpers.compareStatus || (depth0 && depth0.compareStatus) || alias2).call(alias1,(depth0 != null ? depth0.productStatus : depth0),(depth0 != null ? depth0.surplusSeconds : depth0),{"name":"compareStatus","hash":{},"fn":container.program(6, data, 0),"inverse":container.program(8, data, 0),"data":data})) != null ? stack1 : "")
    + "</div>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.addRate || (depth0 != null ? depth0.addRate : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"addRate","hash":{},"data":data}) : helper)));
},"4":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.vipRate || (depth0 != null ? depth0.vipRate : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vipRate","hash":{},"data":data}) : helper)))
    + "<img src=\"/wechat/static/img/v2/vip_03.png\">";
},"6":function(container,depth0,helpers,partials,data) {
    return "		<div class=\"invest_btn invest_btn_active\">马上投资</div>\r\n";
},"8":function(container,depth0,helpers,partials,data) {
    return "		<div class=\"invest_btn\">已结束</div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});;Handlebars.registerHelper('compareStatus',function(productStatus,surplusSeconds,option){
	if(productStatus == 'pub' && surplusSeconds > 0){
		return option.fn(this);
	} else {
		return option.inverse(this);
	}
});