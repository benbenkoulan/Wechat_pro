<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,minimum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>${pageTitle}</title>
    <meta name="keywords" content="翼龙贷、翼存宝、互联网金融、投资理财、P2P理财、网贷、p2p平台、普惠金融、三农贷款、农业贷款、网络理财、如何理财、理财网、怎样理财、如何投资、短期理财、个人理财">
    <meta name="description" content="翼龙贷(www.eloancn.com)-专业安全的P2P网贷平台，优质高效的互联网金融理财网。致力于为投资理财用户提供安全、便捷、高收益的p2p理财服务，同时为农业贷款、三农贷款用户提供快速借款渠道。投资理财用户可通过翼龙贷平台投资翼存宝理财产品将闲置资金出借给有贷款需求的人，从而获得稳定高收益实现财富增值。">
    <meta name="author" content="翼龙贷">

	<link rel="stylesheet" href="${static_path}/static/css/style1.css" />
    <style>
        html{
            font-size:20px;
        }
    </style>
    <link rel="stylesheet" href="${static_path}/static/css/idangerous.swiper1.css"/>
</head>
<body>
<!-- Swiper -->
<div class="swiper-container">
    <div class="swiper-wrapper">
       <c:forEach items="${banners}" var="b">
		<div class="swiper-slide">
			<a href="${b.hrefUrl}">
				<img src="${b.picturePath}"/>
			</a>
		</div>
		</c:forEach>
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
</div>

<div class="mainnav">
    <div class="item left"><div class="item_icon"><img src="${static_path}/static/images/indexico_money.png" /> </div><p class="item_h">翼农计划</p><P>多种期限，自由选择</P> </div>
    <div class="item middle"><div class="item_icon" style="background: #f97664;"><img src="${static_path}/static/images/indexico_up.png" /> </div><p class="item_h">芝麻开花</p><P>短期可转让，长期持有收益节节高</P></div>
    <div class="item right"><div class="item_icon" style="background: #91c66a;"><img src="${static_path}/static/images/indexico_cricle.png" /> </div><p class="item_h">转让专区</p><P>变现更容易，流动性更高</P></div>

</div>
<div class="index_main">
    <div class="index_main_t">为您推荐</div>
    <div class="index_main_list" id="list_main"></div>
</div>
<footer class="footer">
    <ul>
        <li><img src="${static_path}/static/images/index_03_r.png" width="15%" /><br><span class="foot-active">首页</span></li>
        <li><img src="${static_path}/static/images/my_03_gray.png" width="15%" /><br>我的</li>
    </ul>
</footer>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/swiper.min.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>

<!-- Initialize Swiper -->
<script type="text/x-handlebars-template" id="tpl_invest">
	{{#each this}}
	<div class="index_main_item">
	    <p class="item_title">{{transformat type}}<b>{{period}}</b>期<span>{{changeTimetemp enddate}}</span></p>
	    <table class="item_table">
	        <tr class="td1"><td width="50%">预期年化收益</td><td width="50%">合约期限</td></tr>
	        <tr class="td2"><td><div class="num">{{interest}}<i>%</i><span class="sup">+0.5 <img src="${static_path}/static/images/vip_03.png" width="25"></span></div></td><td style="text-align: center;">{{term}}天</td></tr>
	        <tr class="td3"><td>已投人数<span>{{number}}</span>人</td><td style="border-left:1px solid #666;">募集金额<span>{{raised}}/{{sum}}万</span></td></tr>
	    </table>
	</div>
	{{/each}}
</script>
<script>
	var swiper = new Swiper('.swiper-container', {
					pagination: '.swiper-pagination',
					autoplay:2000,
					loop:true,
					autoplayDisableOnInteraction:false,
					grabCursor:true,
					paginationClickable:true
				});
	(function(doc, win,jq,h) {
        var docEl = doc.documentElement,
                isIOS = navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
                dpr = isIOS ? Math.min(win.devicePixelRatio, 3) : 1,
                dpr = window.top === window.self ? dpr : 1, //被iframe引用时，禁止缩放
                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
        docEl.dataset.dpr = dpr;
        var recalc = function() {
            var width = docEl.clientWidth;
            if (width / dpr > 1080) {
                width = 1080 * dpr;
            }
            docEl.dataset.width = width
            docEl.dataset.percent = 100 * (width / 1080);
            docEl.style.fontSize = 100 * (width / 1080) + 'px';
        };
        recalc();
        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);

		var openId='${openId}';
		invest_tempate = h.compile(jq("#tpl_invest").html());
		requestUtil.post("${ctx}/public/ajax/index",{openId:openId},function(data){
			jq("#list_main").html(invest_tempate(data.info.products));
		});
    
		h.registerHelper("transformat",function(f){
			switch(f){
				case "1":return "翼农计划";break;
				case "2":return "芝麻开花";break;
				case "3":return "转让标";break;
			}
		});

		h.registerHelper("changeTimetemp",function(t){
			return new Date(parseInt(t) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
		})
    })(document, window,$,Handlebars);
</script>

 </body>
</html>