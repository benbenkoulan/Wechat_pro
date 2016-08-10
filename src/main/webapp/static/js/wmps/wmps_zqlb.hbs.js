Handlebars.registerHelper('compare', function(id) {
    if (window.tenderDetailUrl) {
        return window.tenderDetailUrl + id;
    } else {
    	return '#';
    }
});this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/wmps/wmps_zqlb.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3=container.escapeExpression, alias4="function";

  return "<a class=\"pdw\" style=\"margin-top:0\" href=\""
    + alias3((helpers.compare || (depth0 && depth0.compare) || alias2).call(alias1,(depth0 != null ? depth0.id : depth0),{"name":"compare","hash":{},"data":data}))
    + "\" data-id=\""
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"id","hash":{},"data":data}) : helper)))
    + "\">\r\n	<table class=\"zqlbxq\">\r\n		<tr>\r\n			<td colspan=\"2\" style=\"font-size:1rem;color:#333333;\">"
    + alias3(((helper = (helper = helpers.detailsTitle || (depth0 != null ? depth0.detailsTitle : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"detailsTitle","hash":{},"data":data}) : helper)))
    + "</td>\r\n		</tr>\r\n		<tr style=\"height:24px;\">\r\n			<td>借款人姓名："
    + alias3(((helper = (helper = helpers.realname3 || (depth0 != null ? depth0.realname3 : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"realname3","hash":{},"data":data}) : helper)))
    + "</td>\r\n			<td>身份证号："
    + alias3(((helper = (helper = helpers.strIdcard || (depth0 != null ? depth0.strIdcard : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"strIdcard","hash":{},"data":data}) : helper)))
    + "</td>\r\n		</tr>\r\n		<tr style=\"height:24px;border-bottom:none;\">\r\n			<td>借款人地区："
    + alias3(((helper = (helper = helpers.disCityname || (depth0 != null ? depth0.disCityname : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"disCityname","hash":{},"data":data}) : helper)))
    + "</td>\r\n			<td>借款总额："
    + alias3(((helper = (helper = helpers.amount || (depth0 != null ? depth0.amount : depth0)) != null ? helper : alias2),(typeof helper === alias4 ? helper.call(alias1,{"name":"amount","hash":{},"data":data}) : helper)))
    + "</td>\r\n		</tr>\r\n	</table>\r\n</a>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});