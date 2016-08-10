this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/product.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return ((stack1 = helpers["with"].call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.products : depth0),{"name":"with","hash":{},"fn":container.program(2, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"2":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(3, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"3":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1, helper, alias1=container.escapeExpression, alias2=depth0 != null ? depth0 : {}, alias3=helpers.helperMissing, alias4="function";

  return "<a data-vip=\""
    + alias1(container.lambda(((stack1 = (depths[2] != null ? depths[2].vip : depths[2])) != null ? stack1.strRatio : stack1), depth0))
    + "\" class=\"index_main_item\" href=\""
    + alias1(((helper = (helper = helpers.detail_href || (depth0 != null ? depth0.detail_href : depth0)) != null ? helper : alias3),(typeof helper === alias4 ? helper.call(alias2,{"name":"detail_href","hash":{},"data":data}) : helper)))
    + "\" "
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(4, data, 0, blockParams, depths),"inverse":container.program(6, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + ">\r\n	<div class=\"item_title clearfix\">\r\n		<p style=\"float:left;width:60%;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;\">"
    + ((stack1 = helpers["if"].call(alias2,(depth0 != null ? depth0.icon : depth0),{"name":"if","hash":{},"fn":container.program(8, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(10, data, 0, blockParams, depths),"inverse":container.program(12, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + "</p>\r\n		<p style=\"float:right;\" class=\"enddate\" data-lasttime=\""
    + alias1(((helper = (helper = helpers.lasttime || (depth0 != null ? depth0.lasttime : depth0)) != null ? helper : alias3),(typeof helper === alias4 ? helper.call(alias2,{"name":"lasttime","hash":{},"data":data}) : helper)))
    + "\"></p>\r\n	</div>\r\n	<table class=\"item_table\">\r\n		<tr class=\"td1\"><td width=\"50%\">预期年化收益</td><td width=\"50%\">"
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(14, data, 0, blockParams, depths),"inverse":container.program(16, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + "</td></tr>\r\n		<tr class=\"td2\">\r\n			<td>\r\n"
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(18, data, 0, blockParams, depths),"inverse":container.program(23, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + "			</td>\r\n"
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(25, data, 0, blockParams, depths),"inverse":container.program(27, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + "		</tr>\r\n		<tr class=\"td3\">\r\n"
    + ((stack1 = (helpers.compareProductType || (depth0 && depth0.compareProductType) || alias3).call(alias2,(depth0 != null ? depth0.type : depth0),{"name":"compareProductType","hash":{},"fn":container.program(29, data, 0, blockParams, depths),"inverse":container.program(31, data, 0, blockParams, depths),"data":data})) != null ? stack1 : "")
    + "			<td style=\"border-left:1px solid #afa7a5;\">募集金额<span>"
    + alias1(((helper = (helper = helpers.raised || (depth0 != null ? depth0.raised : depth0)) != null ? helper : alias3),(typeof helper === alias4 ? helper.call(alias2,{"name":"raised","hash":{},"data":data}) : helper)))
    + "/"
    + alias1(((helper = (helper = helpers.sum || (depth0 != null ? depth0.sum : depth0)) != null ? helper : alias3),(typeof helper === alias4 ? helper.call(alias2,{"name":"sum","hash":{},"data":data}) : helper)))
    + "万</span></td>\r\n		</tr>\r\n	</table>\r\n</a>\r\n";
},"4":function(container,depth0,helpers,partials,data) {
    return "";
},"6":function(container,depth0,helpers,partials,data) {
    return "style=\"border:none;\"";
},"8":function(container,depth0,helpers,partials,data) {
    var helper;

  return "<img src=\""
    + container.escapeExpression(((helper = (helper = helpers.icon || (depth0 != null ? depth0.icon : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"icon","hash":{},"data":data}) : helper)))
    + "\" style=\"width:1.1rem;vertical-align:sub;\">";
},"10":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return alias4(((helper = (helper = helpers.productname || (depth0 != null ? depth0.productname : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"productname","hash":{},"data":data}) : helper)))
    + alias4(((helper = (helper = helpers.period || (depth0 != null ? depth0.period : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"period","hash":{},"data":data}) : helper)));
},"12":function(container,depth0,helpers,partials,data) {
    var helper;

  return container.escapeExpression(((helper = (helper = helpers.period || (depth0 != null ? depth0.period : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"period","hash":{},"data":data}) : helper)));
},"14":function(container,depth0,helpers,partials,data) {
    return "合约期限";
},"16":function(container,depth0,helpers,partials,data) {
    return "封闭期";
},"18":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing;

  return "				<div class=\"num\">"
    + container.escapeExpression(((helper = (helper = helpers.interest || (depth0 != null ? depth0.interest : depth0)) != null ? helper : alias2),(typeof helper === "function" ? helper.call(alias1,{"name":"interest","hash":{},"data":data}) : helper)))
    + "<i>%</i><span class=\"sup\" style=\"text-align: left;\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.ewai : depth0),{"name":"if","hash":{},"fn":container.program(19, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = (helpers.checkVip || (depth0 && depth0.checkVip) || alias2).call(alias1,(depth0 != null ? depth0.type : depth0),((stack1 = (depths[2] != null ? depths[2].vip : depths[2])) != null ? stack1.strRatio : stack1),{"name":"checkVip","hash":{},"fn":container.program(21, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</span></div>\r\n";
},"19":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.ewai || (depth0 != null ? depth0.ewai : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"ewai","hash":{},"data":data}) : helper)));
},"21":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return "+"
    + container.escapeExpression(container.lambda(((stack1 = (depths[2] != null ? depths[2].vip : depths[2])) != null ? stack1.strRatio : stack1), depth0))
    + "<img src=\"/wechat/static/img/v2/vip_03.png\" width=\"18\">";
},"23":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "				<div class=\"num\">"
    + alias4(((helper = (helper = helpers.minRate || (depth0 != null ? depth0.minRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"minRate","hash":{},"data":data}) : helper)))
    + "<i>%</i>~"
    + alias4(((helper = (helper = helpers.maxRate || (depth0 != null ? depth0.maxRate : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"maxRate","hash":{},"data":data}) : helper)))
    + "<i>%</i></div>\r\n";
},"25":function(container,depth0,helpers,partials,data) {
    var helper;

  return "			<td style=\"text-align: center;\">"
    + container.escapeExpression(((helper = (helper = helpers.term || (depth0 != null ? depth0.term : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"term","hash":{},"data":data}) : helper)))
    + "</td>\r\n";
},"27":function(container,depth0,helpers,partials,data) {
    var helper;

  return "			<td style=\"text-align: center;\">"
    + container.escapeExpression(((helper = (helper = helpers.closedPeriod || (depth0 != null ? depth0.closedPeriod : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"closedPeriod","hash":{},"data":data}) : helper)))
    + "天</td>\r\n";
},"29":function(container,depth0,helpers,partials,data) {
    var helper;

  return "			<td>已投人数<span>"
    + container.escapeExpression(((helper = (helper = helpers.number || (depth0 != null ? depth0.number : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"number","hash":{},"data":data}) : helper)))
    + "</span>人</td>\r\n";
},"31":function(container,depth0,helpers,partials,data) {
    var helper;

  return "			<td>最长持有时间<span>"
    + container.escapeExpression(((helper = (helper = helpers.investPeriod || (depth0 != null ? depth0.investPeriod : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"investPeriod","hash":{},"data":data}) : helper)))
    + "</span>天</td>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return ((stack1 = helpers["with"].call(depth0 != null ? depth0 : {},depth0,{"name":"with","hash":{},"fn":container.program(1, data, 0, blockParams, depths),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true,"useDepths":true});;Handlebars.registerHelper('compareProductType',function(type,option){
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