this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/wmps/wmps_list.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3=container.escapeExpression, alias4="function";

  return "<a class=\"invest-tit\" href=\""
    + alias3((helpers.appendIdAndPlatform || (depth0 && depth0.appendIdAndPlatform) || alias2).call(alias1,(depth0 != null ? depth0.detail_url : depth0),{"name":"appendIdAndPlatform","hash":{},"data":data}))
    + "\" style=\"display:block;\">翼农计划<b>"
    + alias3(((helper = (helper = helpers.period || (depth0 != null ? depth0.period : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"period","hash":{},"data":data}) : helper)))
    + "</b><span data-lasttime=\""
    + alias3((helpers.compareStatus || (depth0 && depth0.compareStatus) || alias2).call(alias1,(depth0 != null ? depth0.status : depth0),(depth0 != null ? depth0.lasttime : depth0),{"name":"compareStatus","hash":{},"data":data}))
    + "\" class=\"enddate\"></span></a>\r\n<div class=\"tot\" style=\"border-bottom:8px solid #f6f2f2;\">\r\n	<a class=\"yield\" style=\"display:block;\" href=\""
    + alias3((helpers.appendIdAndPlatform || (depth0 && depth0.appendIdAndPlatform) || alias2).call(alias1,(depth0 != null ? depth0.detail_url : depth0),{"name":"appendIdAndPlatform","hash":{},"data":data}))
    + "\">\r\n		<p style=\"width:36%\">\r\n			<span class=\"yield-tit\">预期年化收益</span>\r\n			<b style=\"position:relative\">"
    + alias3(((helper = (helper = helpers.interest || (depth0 != null ? depth0.interest : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"interest","hash":{},"data":data}) : helper)))
    + "<span class=\"sup\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.ewai : depth0),{"name":"if","hash":{},"fn":container.program(2, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.vip_rate : depth0),{"name":"if","hash":{},"fn":container.program(4, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</span></b>\r\n			<span class=\"pct\">%</span>\r\n		</p>\r\n		<p style=\"text-align:center;width:28%\"><span class=\"yield-tit\">合约期限</span><span class=\"sum\" style=\"padding-top:12px;\">"
    + alias3(((helper = (helper = helpers.term || (depth0 != null ? depth0.term : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"term","hash":{},"data":data}) : helper)))
    + "</span></p>\r\n		<p style=\"text-align:right;width:36%\"><span class=\"yield-tit\">募集金额&nbsp;</span><span class=\"sum\" style=\"padding-top:12px;\">"
    + alias3(((helper = (helper = helpers.raised || (depth0 != null ? depth0.raised : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"raised","hash":{},"data":data}) : helper)))
    + "/"
    + alias3(((helper = (helper = helpers.sum || (depth0 != null ? depth0.sum : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"sum","hash":{},"data":data}) : helper)))
    + "万</span></p>\r\n	</a>\r\n"
    + ((stack1 = (helpers.compare_btn || (depth0 && depth0.compare_btn) || alias2).call(alias1,(depth0 != null ? depth0.status : depth0),{"name":"compare_btn","hash":{},"fn":container.program(6, data, 0),"inverse":container.program(8, data, 0),"data":data})) != null ? stack1 : "")
    + "</div>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.ewai || (depth0 != null ? depth0.ewai : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"ewai","hash":{},"data":data}) : helper)));
},"4":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.vip_rate || (depth0 != null ? depth0.vip_rate : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vip_rate","hash":{},"data":data}) : helper)))
    + "<img src=\"/wechat/static/img/v2/vip_03.png\" width=\"20\" />";
},"6":function(container,depth0,helpers,partials,data) {
    return "		<a class=\"invest_btn invest_btn_active\" href=\""
    + container.escapeExpression((helpers.appendIdAndPlatform || (depth0 && depth0.appendIdAndPlatform) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.invest_url : depth0),{"name":"appendIdAndPlatform","hash":{},"data":data}))
    + "\" style=\"display:block;\">马上投资</a>\r\n";
},"8":function(container,depth0,helpers,partials,data) {
    return "		<div class=\"invest_btn invest_btn_inactive\">已结束</div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});;Handlebars.registerHelper('compare_btn',function(status,option){
	if(status == 1){
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
Handlebars.registerHelper('appendIdAndPlatform',function(url){
	if(!url)return '#';
	url += ('&id=' + window.id);
	url += ('&platform=' + window.platform);
	url += ('&v=' + Math.random());
	return url;
})