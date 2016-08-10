this["eloancnTemplate"] = this["eloancnTemplate"] || {};

this["eloancnTemplate"]["static/tpls/banner.handlebars"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<div class=\"swiper-slide\">\r\n	<a href=\""
    + alias4(((helper = (helper = helpers.hrefUrl || (depth0 != null ? depth0.hrefUrl : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"hrefUrl","hash":{},"data":data}) : helper)))
    + "\">\r\n		<img style=\"width:100%;vertical-align:bottom;\" src=\""
    + alias4(((helper = (helper = helpers.picturePath || (depth0 != null ? depth0.picturePath : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"picturePath","hash":{},"data":data}) : helper)))
    + "\" alt=\""
    + alias4(((helper = (helper = helpers.description || (depth0 != null ? depth0.description : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"description","hash":{},"data":data}) : helper)))
    + "\" />\r\n	</a>\r\n</div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return "<div class=\"swiper-wrapper\">\r\n"
    + ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</div>\r\n<div class=\"swiper-pagination\"></div>";
},"useData":true});