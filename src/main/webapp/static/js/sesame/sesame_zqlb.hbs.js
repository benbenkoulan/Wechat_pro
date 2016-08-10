this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/sesame/sesame_zqlb.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<a class=\"pdw\" href=\""
    + alias4(((helper = (helper = helpers.detailUrl || (depth0 != null ? depth0.detailUrl : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"detailUrl","hash":{},"data":data}) : helper)))
    + "\">\r\n	<table class=\"zqlbxq\">\r\n	<tr>\r\n		<td colspan=\"2\" style=\"font-size:0.8rem;text-align:left;color:#333333;\">"
    + alias4(((helper = (helper = helpers.title || (depth0 != null ? depth0.title : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"title","hash":{},"data":data}) : helper)))
    + "</td>\r\n	</tr>\r\n	<tr>\r\n		<td class=\"tdauto\">借款人姓名："
    + alias4(((helper = (helper = helpers.owner_name || (depth0 != null ? depth0.owner_name : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"owner_name","hash":{},"data":data}) : helper)))
    + "</td>\r\n		<td class=\"tdauto\">身份证号："
    + alias4(((helper = (helper = helpers.idcard || (depth0 != null ? depth0.idcard : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"idcard","hash":{},"data":data}) : helper)))
    + "</td>\r\n	</tr>\r\n	<tr style=\"border-bottom:none;\">\r\n		<td class=\"tdauto\">借款人地区："
    + alias4(((helper = (helper = helpers.areaname || (depth0 != null ? depth0.areaname : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"areaname","hash":{},"data":data}) : helper)))
    + "</td>\r\n		<td class=\"tdauto\">借款总额："
    + alias4(((helper = (helper = helpers.unmatch_amount || (depth0 != null ? depth0.unmatch_amount : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"unmatch_amount","hash":{},"data":data}) : helper)))
    + "</td>\r\n	</tr>\r\n	</table>\r\n</a>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});