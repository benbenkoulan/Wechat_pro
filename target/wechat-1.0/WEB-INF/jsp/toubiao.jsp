<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<a data-type="1" class="box cur">参与中</a>
			<a data-type="2" class="box">回收中</a>
		</div>
		<div class="income_main clearfix">
			<div class="wrapper" id="canyu_wrapper">
				<div class="scroller">
					<div class="income_list tnd_income_list clearfix" id="canyu_all"></div>
					<div class="pulldown loading"></div>
				</div>
			</div>
			<div class="wrapper none" id="huishou_wrapper">
				<div class="scroller">
					<div class="income_list tnd_income_list clearfix" id="huishou_all"></div>
					<div class="pulldown loading"></div>
				</div>
			</div>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script type="text/x-handlebars-template" id="tpl_all">
	{{#each this}}
	<ul>
		<div class="scat_ico"><span>{{strStatus}}</span></div>
		<li class="scat_title">{{title3}}</li>
		<li>
			<div>借款人</div>
			<div>{{realname2}}</div>
			<div>利率</div>
			<div><span>{{strInterestrate}}</span></div>
		</li>
		<li>
			<div>投标金额</div>
			<div><span>{{formatEffectiveamount}}</span></div>
			<div>投标时间</div>
			<div>{{cdateString}}</div>
		</li>
	</ul>
	{{/each}}
</script>
<script type="text/x-handlebars-template" id="tpl_draw">
	{{#each this}}
	<ul>
		<li class="scat_title">{{title}}</li>
		<li>
			<div>借款人</div>
			<div>{{realname2}}</div>
			<div>年利率</div>
			<div>{{realinterestrate}}</div>
		</li>
		<li>
			<div>贷出金额</div>
			<div>{{formatLendedmoney}}</div>
			<div>应收本息</div>
			<div><span>{{formatTocollectIntAndMoney}}</span></div>
		</li>
		<li>
			<div>已收本息</div>
			<div>{{formatCollectedIntAndMoney}}</div>
			<div>待收金额</div>
			<div>{{formatSurplusMoney}}</div>
		</li>
	</ul>	
	{{/each}}
</script>
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
			if(pullDown.hasClass("loading")){
				pullDown.html("");
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

(function(jq){
	var box = jq(".box"),
		canyu = ScrollHandler('#canyu_wrapper' , '${ctx}/index/ajax/my_tb_cy' , '#tpl_all' , '#canyu_all'),
		huishou = ScrollHandler('#huishou_wrapper' , '${ctx}/index/ajax/my_tb_hx' , '#tpl_draw' , '#huishou_all');
	canyu.getData();
	box.on("click",function(){
		box.removeClass("cur");
		var cur = jq(this);
		cur.addClass("cur");
		$('.wrapper').hide();
		type = $(this).data('type');
		if(type == 1){
			canyu.wrapperShow();
		}else{
			huishou.wrapperShow();
		}
	});
}($));
</script>
</body>
</html>