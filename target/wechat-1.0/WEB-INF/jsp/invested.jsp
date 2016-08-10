<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="${static_path}/static/css/style.css?v=1.0" />
<script src="${static_path}/static/js/resize.js"></script>
<style type="text/css">
	#wrapper *{
		-webkit-box-sizing: border-box;
	}
	#wrapper {
		position: absolute;
		z-index: 1;
		top: 7.05em;
		bottom: 0px;
		left: 0;
		width: 100%;
		overflow:hidden;
	}
	#scroller {
		position: absolute;
		z-index: 1;
		-webkit-tap-highlight-color: rgba(0,0,0,0);
		width: 100%;
		-webkit-transform: translateZ(0);
		-moz-transform: translateZ(0);
		-ms-transform: translateZ(0);
		-o-transform: translateZ(0);
		transform: translateZ(0);
		-webkit-touch-callout: none;
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
		-webkit-text-size-adjust: none;
		-moz-text-size-adjust: none;
		-ms-text-size-adjust: none;
		-o-text-size-adjust: none;
		text-size-adjust: none;
	}
	#scroller #invest{
		height:100%;
	}
	.pulldown{
      text-align: center;
      width: 100%;
      height: 30px;
		font-size: .7rem;
		color: rgb(97, 97, 97);
    }
    .pulldown.loading{
        background: url(${static_path}/static/img/loading.gif) 50% 50% no-repeat;
    }
	.newy{display:block;position:absolute;top:6%;left:10%;width:80%;z-index: 99999;}
	#hides{display:none;}
</style>
<link rel="stylesheet" href="${static_path}/static/css/idangerous.swiper.css" />
</head>
<body>
	<div id="hides">
		<img src="${ctx}/static/img/newy.png" class="newy" />
	</div>
	<div class="main">
		<div class="invest_banner clearfix">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${banner}" var="b">
					<div class="swiper-slide">
						<a class="c_page" href="${b.hrefUrl}">
							<img src="${b.picturePath}" />
						</a>
					</div>
					</c:forEach>
				</div>
			</div>
			<div class="pagination"></div>
		</div>
		<div class="tnd_invest_top"><a class="c_page" href="http://m.eloancn.com/page/wexin/yicunbaoshare.html">了解翼农计划</a></div>
		<div id="wrapper">
			<div id="scroller">
				<div class="tnd_invest_list clearfix" id="invest"></div>
				<div class="pulldown loading"></div>
			</div>
		</div>
		<a href="${ctx}/index/index.html?openId=${openId}" style="z-index:10;" class="invest_gr c_page">
			<c:choose>
				<c:when test="${userinfo.yld_head_photo != null}">
					<img src="http://img.eloancn.com/${userinfo.yld_head_photo}" />
				</c:when>
				<c:otherwise>
					<img src="${static_path}/static/img/invest_img.jpg" />
				</c:otherwise>
			</c:choose>
		</a>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/swiper.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>
	<script type="text/x-handlebars-template" id="tpl_invest">
	{{#each this}}
		<dl>
			<dt><a data-type="{{type}}" class="detail"  data-href="${ctx}{{d_url}}"  href="javascript:void(0);">翼农计划<span>{{title}}</span></a></dt>
			<dd>
				<a data-type="{{type}}" class="detail" data-href="${ctx}{{d_url}}" href="javascript:void(0);">
					<div class="tnd_invest_left">
						<b>{{strInterestrate}}</b>%
						<p>预期年化收益</p>
						{{#if ewai}}
							<span>+{{ewai}}</span>
						{{else}}
							<span></span>
						{{/if}}
						{{#if vip}}
							{{#if ewai}}
								<span style="right:-2.2rem;">
							{{else}}								
								<span style="right:-1.8rem;">
							{{/if}}
								+{{strRatio}}
								<img src="${static_path}/static/img/vip.png" style="width:1.1rem;margin-top:.5rem;" />
							</span>
						{{/if}}
					</div>
					<div class="tnd_invest_right">
						合约期限：<span>{{qixian}}</span>{{qx_type}}<br />
						已募集/总金额：<span>{{amount}}/{{SLMaxAmount}}</span>万
					</div>
				</a>
				{{#compare status}}
					<a data-href="${ctx}{{tz_url}}" data-type="{{type}}" style="color:#fff;" class="btn c_page check_bind">{{wenan}}</a>
				{{else}}
					<a href="javascript:;" data-type="{{type}}" class="btn tnd_btn c_page">{{wenan}}</a>
				{{/compare}}
			</dd>
		</dl>
	{{/each}}
	</script>
	<script>
		/*写成单例模式----liben---20160203
		参数分别为window，document，jquery,Handlebars
		*/
		(function(w,d,jq,h){
			var openId='${openId}',intervalId,mySwiper=new Swiper('.swiper-container',{pagination:'.pagination',autoplay:2000,loop:true,autoplayDisableOnInteraction:false,grabCursor:true,paginationClickable:true}),invest_tempate=h.compile(jq('#tpl_invest').html());var invest_check={flag:false,checkBind:function(btn){if(this.flag){return}else{this.flag=true}var old_url=btn.data('href'),type=btn.data('type');jq.ajax({type:'POST',url:'${ctx}/index/ajax/check_bind_ajax',data:{openId:openId},dataType:'json',success:function(data){invest_check.flag=false;if(data.info){w.alert(data.info)}if(data.status){invest_check.checkExpirence(type,old_url)}else{w.location.href='${ctx}'+data.url}},error:function(jqXHR,textStatus,errorThrown){invest_check.flag=false;if(textStatus=='timeout'){alert('网络不给力，请稍后再试！')}}})},checkExpirence:function(type,old_url){if(type){jq.ajax({type:'POST',url:'${ctx}/index/ajax/check_expirence_ajax',data:{openId:openId},dataType:'json',success:function(data){if(data.status){w.location.href=old_url}else{alert(data.info)}},error:function(jqXHR,textStatus,errorThrown){if(textStatus=='timeout'){alert('网络不给力，请稍后再试！')}}})}else{w.location.href=old_url}}};var invest_page={pullDownEl:jq('.pulldown'),invest:jq('#invest'),scroller:jq('#scroller'),bindEvent:function(){this.pullDownEl.on('click',function(){invest_page.pullDownEl.data('flip','flip');invest_page.pullDownEl.addClass('loading');invest_page.pullDownEl.html('');invest_data.loadData()});this.scroller.delegate('.detail','click',function(){invest_check.checkExpirence(jq(this).data('type'),jq(this).data('href'))});this.scroller.delegate('.check_bind','click',function(){invest_check.checkBind($(this))})},appendPageData:function(html){this.invest.append(html)},refresh:function(){this.myScroll.refresh();this.pullDownEl.data('flip','');this.pullDownEl.removeClass('loading');this.pullDownEl.html('点击加载更多')},init:function(){this.bindEvent();invest_data.loadData()}};var invest_data={page:1,flag:false,loadData:function(){if(this.flag){return}else{this.flag=true}jq.ajax({type:'POST',url:'${ctx}/index/ajax/invested_ajax',data:{openId:openId,page:this.page},dataType:"json",success:function(data){invest_data.flag=false;if(data.status){var dArr=data.info.data;if(dArr){invest_data.page++;var html=invest_tempate(dArr);invest_page.appendPageData(html);if(!invest_page.myScroll){invest_page.pullDownEl.removeClass('loading');invest_page.pullDownEl.html('点加载更多');intervalId=w.setInterval(function(){invest_page.myScroll=new IScroll('#wrapper',{mouseWheel:true,click:true,probeType:2});invest_page.myScroll.on('scroll',function(){if(this.y<(this.maxScrollY-5)&&!invest_page.pullDownEl.data('flip')){invest_page.pullDownEl.data('flip','flip');invest_page.pullDownEl.addClass('loading');invest_page.pullDownEl.html('')}});invest_page.myScroll.on('scrollEnd',function(){if(invest_page.pullDownEl.data('flip')){invest_data.loadData()}});if(intervalId){w.clearInterval(intervalId)}},1000)}else{invest_page.refresh()}}else{invest_page.pullDownEl.removeClass('loading');if(invest_page.page==1){invest_page.pullDownEl.html('<br/><br/><br/>暂无数据')}else{invest_page.pullDownEl.remove()}}}else{w.alert(data.info)}},error:function(jqXHR,textStatus,errorThrown){invest_data.flag=false;if(textStatus=='timeout'){w.alert('网络不给力，请稍后再试！')}}})}};w.history.replaceState(null,'${pageTitle}','${ctx}/index/invested?openId='+openId);h.registerHelper('compare',function(status,option){if(status==1){return option.fn(this)}else{return option.inverse(this)}});invest_page.init();
		})(window,document,$,Handlebars);
	</script>
</body>
</html>