this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/sesame/sesame_list.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3=container.escapeExpression, alias4="function";

  return "<div class=\"ytop\">\r\n	<a style=\"display:block\" href=\""
    + alias3((helpers.appendIdAndPlatform || (depth0 && depth0.appendIdAndPlatform) || alias2).call(alias1,(depth0 != null ? depth0.detail_url : depth0),{"name":"appendIdAndPlatform","hash":{},"data":data}))
    + "\">\r\n		<div class=\"fl\" style=\"font-size:1rem;color:#333333\">"
    + alias3(((helper = (helper = helpers.title || (depth0 != null ? depth0.title : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"title","hash":{},"data":data}) : helper)))
    + "</div>\r\n		<span class=\"enddate ytime fr\" data-lasttime=\""
    + alias3(((helper = (helper = helpers.surplusSeconds || (depth0 != null ? depth0.surplusSeconds : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"surplusSeconds","hash":{},"data":data}) : helper)))
    + "\"></span>\r\n		<div style=\"clear:both;\"></div>\r\n		<div class=\"xq clear\">\r\n			<table><tbody>\r\n				<tr>\r\n					<th width=\"28%\" style=\"background:#f2f2f2;color:#6e6e6e;\">封闭期</th>\r\n					<th style=\"color:#6e6e6e;\">预期年化收益</th>\r\n				</tr>\r\n				<tr>\r\n					<td width=\"28%\" style=\"background:#f2f2f2;color:#333333;\"><span style=\"font-size:1.4rem;background:#f2f2f2;\">"
    + alias3(((helper = (helper = helpers.closed_period || (depth0 != null ? depth0.closed_period : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"closed_period","hash":{},"data":data}) : helper)))
    + "</span>天</td>\r\n					<td style=\"color:#6e6e6e;\"><span style=\"font-size:1.4rem;color:#e93a3a;\">&nbsp;&nbsp;&nbsp;&nbsp;"
    + alias3(((helper = (helper = helpers.minRate || (depth0 != null ? depth0.minRate : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"minRate","hash":{},"data":data}) : helper)))
    + "</span> %<span style=\"font-size:1.4rem;color:#e93a3a;\">&nbsp;&nbsp;~&nbsp;&nbsp;"
    + alias3(((helper = (helper = helpers.maxRate || (depth0 != null ? depth0.maxRate : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"maxRate","hash":{},"data":data}) : helper)))
    + "</span> %</td>\r\n				</tr>\r\n			</tbody></table>\r\n		</div>\r\n	</a>\r\n	<p class=\"botp clear\"><span class=\"fl\">最长持有天数："
    + alias3(((helper = (helper = helpers.investPeriod || (depth0 != null ? depth0.investPeriod : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"investPeriod","hash":{},"data":data}) : helper)))
    + "天</span><span class=\"fr\">募集余额："
    + alias3(((helper = (helper = helpers.total_amount || (depth0 != null ? depth0.total_amount : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"total_amount","hash":{},"data":data}) : helper)))
    + "/"
    + alias3(((helper = (helper = helpers.max_amount || (depth0 != null ? depth0.max_amount : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"max_amount","hash":{},"data":data}) : helper)))
    + "万</span></p>\r\n	<div class=\"invest clear\">\r\n"
    + ((stack1 = (helpers.compareStatus || (depth0 && depth0.compareStatus) || alias2).call(alias1,(depth0 != null ? depth0.productStatus : depth0),{"name":"compareStatus","hash":{},"fn":container.program(2, data, 0),"inverse":container.program(4, data, 0),"data":data})) != null ? stack1 : "")
    + "	</div>\r\n</div>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    return "			<a class=\"butt clear\" href=\""
    + container.escapeExpression((helpers.appendIdAndPlatform || (depth0 && depth0.appendIdAndPlatform) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.invest_url : depth0),{"name":"appendIdAndPlatform","hash":{},"data":data}))
    + "\" style=\"margin:1% 0% 3%;width:100%;display:block;\">马上投资</a>\r\n";
},"4":function(container,depth0,helpers,partials,data) {
    return "			<div class=\"butt2 clear\" onclick=\"\" style=\"margin:3% 0% 3%;width:100%;\">已结束</div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});