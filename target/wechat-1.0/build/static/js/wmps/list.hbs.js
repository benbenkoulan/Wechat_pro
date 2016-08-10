this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/wmps/list.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<div class=\"wmps\" data-detailurl=\""
    + alias4(((helper = (helper = helpers.detail_url || (depth0 != null ? depth0.detail_url : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"detail_url","hash":{},"data":data}) : helper)))
    + "\" data-investurl=\""
    + alias4(((helper = (helper = helpers.invest_url || (depth0 != null ? depth0.invest_url : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"invest_url","hash":{},"data":data}) : helper)))
    + "\">\r\n	<div class=\"content clearfix\">\r\n		<p class=\"left title\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.icon : depth0),{"name":"if","hash":{},"fn":container.program(2, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "翼农计划"
    + alias4(((helper = (helper = helpers.period || (depth0 != null ? depth0.period : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"period","hash":{},"data":data}) : helper)))
    + "</p>\r\n		<p class=\"right enddate\" data-lasttime=\""
    + alias4((helpers.compareStatus || (depth0 && depth0.compareStatus) || alias2).call(alias1,(depth0 != null ? depth0.status : depth0),(depth0 != null ? depth0.lasttime : depth0),{"name":"compareStatus","hash":{},"data":data}))
    + "\"></p>\r\n	</div>\r\n	<div class=\"content content2 clearfix\">\r\n		<div class=\"left rate info\">\r\n			<p class=\"content_title\">预期年化收益</p>\r\n			<p>\r\n				<b>"
    + alias4(((helper = (helper = helpers.interest || (depth0 != null ? depth0.interest : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"interest","hash":{},"data":data}) : helper)))
    + "<span class=\"sup\">"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.ewai : depth0),{"name":"if","hash":{},"fn":container.program(4, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + " "
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.vip_rate : depth0),{"name":"if","hash":{},"fn":container.program(6, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</span></b>%\r\n			</p>\r\n		</div>\r\n		<div class=\"left period info\">\r\n			<p class=\"content_title\">合约期限</p>\r\n			<p>"
    + alias4(((helper = (helper = helpers.term || (depth0 != null ? depth0.term : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"term","hash":{},"data":data}) : helper)))
    + "</p>\r\n		</div>\r\n		<div class=\"left amount info\">\r\n			<p class=\"content_title\">募集金额</p>\r\n			<p>"
    + alias4(((helper = (helper = helpers.raised || (depth0 != null ? depth0.raised : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"raised","hash":{},"data":data}) : helper)))
    + "/"
    + alias4(((helper = (helper = helpers.sum || (depth0 != null ? depth0.sum : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"sum","hash":{},"data":data}) : helper)))
    + "万</p>\r\n		</div>\r\n	</div>\r\n"
    + ((stack1 = (helpers.compare_btn || (depth0 && depth0.compare_btn) || alias2).call(alias1,(depth0 != null ? depth0.status : depth0),(depth0 != null ? depth0.lasttime : depth0),{"name":"compare_btn","hash":{},"fn":container.program(8, data, 0),"inverse":container.program(10, data, 0),"data":data})) != null ? stack1 : "")
    + "</div>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    var helper;

  return "<img src=\""
    + container.escapeExpression(((helper = (helper = helpers.icon || (depth0 != null ? depth0.icon : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"icon","hash":{},"data":data}) : helper)))
    + "\">";
},"4":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.ewai || (depth0 != null ? depth0.ewai : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"ewai","hash":{},"data":data}) : helper)));
},"6":function(container,depth0,helpers,partials,data) {
    var helper;

  return "+"
    + container.escapeExpression(((helper = (helper = helpers.vip_rate || (depth0 != null ? depth0.vip_rate : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vip_rate","hash":{},"data":data}) : helper)))
    + "<img src=\"/wechat/static/img/v2/vip_03.png\">";
},"8":function(container,depth0,helpers,partials,data) {
    return "		<div class=\"invest_btn invest_btn_active\">马上投资</div>\r\n";
},"10":function(container,depth0,helpers,partials,data) {
    return "		<div class=\"invest_btn\">已结束</div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});