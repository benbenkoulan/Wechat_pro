<%@ include file="/common.jsp"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>  
<!DOCTYPE HTML>
<html>
  <head>
	<title>${pageTitle}</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-touch-fullscreen" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />	
	<link rel="stylesheet" type="text/css" href="${static_path}/static/css/style.css" />
	<style type="text/css">
	.wrapper *{
		-webkit-box-sizing: border-box;
	}
	.wrapper {
		position: absolute;
		z-index: 1;
		top: 1.8rem;
		bottom: 0px;
		left: 0;
		width: 100%;
		overflow:hidden;
	}
	.scroller {
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
	.scroller .income_list{
		height:100%;
	}
	.xieyi{
		width: 60%;
		margin-left: 20%;
		border: 1px solid;
		border-radius:6px;
		color:#FF0000;
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
	</style>
</head>
<body>
	<div class="main">
		<div class="income_title clearfix">
			<a data-type="1" class="box cur">翼农计划详情</a>
			<a data-type="2" class="box">参与中的翼农计划</a>
		</div> 
		<div class="income_main clearfix">
			<div class="wrapper" id="ycb">
				<div class="scroller">
					<div class="income_list clearfix" id="ycb_all"></div>
					<div class="ycb_pull pulldown loading"></div>
				</div>
			</div>
			<div class="wrapper none" id="cyycb">
				<div class="scroller">
					<div class="income_list clearfix" id="cyycb_all"></div>
					<div class="cy_pull pulldown loading"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="${static_path}/static/js/jquery.min.js"></script>
	<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
	<script src="${static_path}/static/js/iscroll.js"></script>	
	<script src="${static_path}/static/js/resize.js"></script>
	<script>
		var openId = '${openId}';
		function ScrollHandler(id , url , templateId , dataAreaId){
			var wrapper = $(id),
				dataArea = $(dataAreaId),
				pullDown = $(id + ' .scroller .pulldown'),
				tempHTML = $(templateId).html(),
				template = Handlebars.compile(tempHTML),
				obj = {
					page : 1,
					wrapperShow : function(){
						if(this.page == 1){
							this.getData();
						}
						wrapper.show();
					}
				};
			pullDown.data("all","yes");
			obj.refresh = function(){
				this.scroll.refresh();
				pullDown.html("点击加载更多");
			},
			obj.createIScroll = function(){
				this.scroll = new IScroll(id, {mouseWheel:true, click:true, probeType:2,fixedScrollbar:true,shrinkScrollbars:'clip'});
				this.scroll.on('scroll', function(){
					if (this.y < (this.maxScrollY - 5) && pullDown.data('all')){
						pullDown.addClass("loading");
						pullDown.html("");
					}
				});
				this.scroll.on('scrollEnd', function(){
					if(pullDown.hasClass("loading") && !pullDown.data("isLoading")){
						pullDown.html("");
						pullDown.data("isLoading" , "yes")
						obj.getData();
					}
				});
			}
			obj.getData = function(){
				var p = this.page,
					request_data = {
					type : 'POST',
					url : url,
					dataType : 'json',
					data : {page:p,openId:openId}
				};
				request_data.success = function(data){
					pullDown.removeClass("loading");
					pullDown.data("isLoading" , "")
					if(data.status){
						var dArr = data.info.data;
						if(dArr && dArr.length){
							obj.page++;
							dataArea.append(template(dArr));
							if(!obj.scroll){
								pullDown.html("点击加载更多");
								obj.createIScroll();
							} else {
								obj.refresh();
							}
						} else {
							if(obj.page == 1){
								pullDown.data("all","");
								pullDown.html("<br/><br/><br/>暂无数据");
							}else{
								pullDown.remove();
							}
						}
					} else {
						alert("获取不到数据，请稍候再试");
					}
				};
				request_data.error = function(jqXHR ,textStatus, errorThrown){
					pullDown.data("isLoading" , "")
					if(textStatus == 'timeout'){
						alert("网络不给力，请稍后再试！");
					}
				};
				$.ajax(request_data);
			}
			obj.init = function(){
				pullDown.on('click', function(){
					if(pullDown.data('all')){
						pullDown.addClass("loading");
						pullDown.html("");
						obj.getData();	
					}
				});
			}
			obj.init();
			return obj;
	}
	$(function(){
		var ycb = ScrollHandler('#ycb' , '${ctx}/index/ajax/ycb_ajax' , '#tpl_all' , '#ycb_all'),
			cyycb = ScrollHandler('#cyycb' , '${ctx}/index/ajax/cy_ycb' , '#tpl_draw' , '#cyycb_all');
		ycb.getData();
		$('.box').bind('click',function(){
			var type;
			$('.box').removeClass('cur');
			$(this).addClass('cur');
			$('.wrapper').hide();
			type = $(this).data('type');
			if(type == 1){
				ycb.wrapperShow();
			}else{
				cyycb.wrapperShow();
			}
		});
		Handlebars.registerHelper("compare" , function(vip ,option) {
            if(vip == 1) {
                return option.fn(this);
            } else {
                return option.inverse(this);
            }
        });
		
		Handlebars.registerHelper("checkExpirence" , function(expirence , option){
			if(!expirence){
				return option.fn(this);
			} else {
				return option.inverse(this);
			}
		});
		
		$('.main').delegate(".xieyi" , "click" , function(e){
			var target = e.target,
			form = target.nextSibling.nextSibling;
			if(form){
				form.submit();
			} else {
				var url = target.getAttribute('data-href'),
					id = target.getAttribute('data-id');
				window.location.href = url + "&wid=" + id;
			}
		});
		document.addEventListener('touchmove', function(e){e.preventDefault();}, false);
	});
</script>
<script type="text/x-handlebars-template" id="tpl_all">
	{{#each this}}
	<ul>
		<li>
			<div>计划名称</div>
			<div>投资金额</div>
			<div>收益</div>
			<div>状态</div>
		</li>
		<li>
			<div><span>{{title}}</span></div>
			<div><span>{{effectiveamount}}</span></div>
			<div><span>{{realEarn}}</span></div>
			<div>{{strWmpsStatus}}</div>
		</li>
	</ul>
	{{/each}}
</script>
<script type="text/x-handlebars-template" id="tpl_draw">
	{{#each this}}
	<ul>
		<li>
			<div>计划名称</div>
			<div><span>{{title}}</span></div>
			<div>投资金额</div>
			<div><span>{{effectiveamount}}</span></div>
		</li>
		<li>
			<div>年化收益</div>
			<div>{{strInterestrate}}<br />
			{{#compare vip}}
				<img src="${static_path}/static/img/vip.png" style="display:inline-block;width:1rem;margin-left:.2rem; ">	
			{{/compare}}
			</div>
			<div>参与人数</div>
			<div>{{num}}</div>
		</li>
		<li>
			<div>计息时间</div>
			<div>{{strInterdate}}</div>
			<div>到期时间</div>
			<div>{{strEnddate}}</div>
		</li>
		{{#checkExpirence experience}}
			<li>
				<div>
					<p class="xieyi">债权详情</p>
					<form class="none" method="post" action="${ctx}/index/zhaiquan?openId=${openId}">
						<input type="hidden" name="id" value="{{id}}">
					</form>
				</div>
				<div>
					<p class="xieyi" data-href="${licaiUrl}" data-id={{id}}>理财协议</p>
				</div>
			</li>
		{{/checkExpirence}}
		
	</ul>
	{{/each}}
</script>
</body>
</html>