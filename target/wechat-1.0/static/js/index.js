(function(w,j,h,r,e,et){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		banner_template = et['static/tpls/banner.handlebars'],
		product_template = et['static/tpls/product.handlebars'];
	j('#account_page').click(function(){
		w.location.href = '/wechat/html/account/myaccount.html?id=' + id + '&platform=' + platform + '&v=' + Math.random();
	});
	r.postLayer('/wechat/public/ajax/index/init',{id:id,platform:platform},function(data){
		if(data.status){
			var info = data.info,
				banners = info.banners || [],
				swiper_container = j('.swiper-container');
			j('.index_main_list').append(product_template(info));
			j('#wmps_page').attr('href','/wechat/html/wmps/list.html?id=' + id + '&platform=' + platform);
			j('#sesame_page').attr('href','/wechat/html/sesame/list.html?id=' + id + '&platform=' + platform);
			j('#zrzq').attr('href','/wechat/html/transfer/list.html?id=' + id + '&platform=' + platform)
			swiper_container.append(banner_template(banners));
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true
			});
			e.countDown();
			var body = j('body'),
				height = body.height();
			body.height(height + 36 + 'px');
		}
	});
})(window,$,Handlebars,requestUtil,eloancnUtil,eloancnTemplate);