<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>我的奖励</title>
<link rel="stylesheet" href="${static_path}/static/css/layer.css" />
<style>
html,body{width: 100%;font-family:"Microsoft YaHei";}
*{margin: 0;padding: 0;}
ul{list-style: none;width: 100%;}
.none{display: none;}
.clearfix:after{font-size: 0;content:'.';visibility: hidden;display: block;clear: both;}
.header{position:fixed;left:0;top:0;z-index:99; border-top: 1px solid #f0edec;width: 100%;height: 30px;line-height: 30px;font-size: 0.8rem;background: #ffffff;}
div a{text-decoration:none;box-sizing:border-box;border-bottom: 1px solid #f0edec;display:block;float:left;width: 33.33%;text-align: center;border-left: 1px solid #f0edec;color: #000000;}
.active{border-bottom: 2px solid #f84d4d;color: #f84d4d;font-weight: 700;}
.title{position:fixed;left:0;top:32px;z-index:99;height:30px;line-height: 30px;background: #f6f4f4;border-bottom: 1px solid #cccccc;font-size: 0.8rem;}
.title li{float: left;width: 33.33%;text-align: center;}
#wrapper{position: absolute; z-index: 1; top: 64px; bottom: 0; left: 0; width: 100%;}
.loading{background: url(${static_path}/static/img/loading.gif) 50% 50% no-repeat;}
.pulldown{text-align:center;width: 100%;height: 30px;font-size: .7rem;line-height: 30px;}
.list{border-bottom: 1px solid #cccccc;}
.list li{float: left;width: 33.33%;text-align: center;font-size: 0.8rem;}
.name_time{padding: 6px 0;}
.other_info{height: 54px;line-height: 54px;}
.time{transform:scale(0.8);-webkit-transform:scale(0.8);}
.not_used{color:#f02216;}
</style>
</head>
<body>
	<!--弹窗层---开始-->
	<div class="alert_layer"></div>
	<div class="alert_box alert_box_ok">
		<p class="alert_title">提示</p>
		<p class="alert_content alert_content_left"></p>
		<p class="alert_attention"></p>
		<div class="alert_btn_container clearfix">
			<div class="alert_btn alert_btn1 alert_btn_cancel">取消</div>
			<div class="alert_btn alert_btn1 alert_btn_ok">去投资</div>
		</div>
	</div>
	<!--弹窗层---结束-->
	<div class="header clearfix">
		<a href="javascript:void(0);" data-id='#hongbao' data-type='1' class="tab active">红包</a>
		<a href="javascript:void(0);" data-id='#tiyanjin' data-type='3' class="tab">体验金</a>
		<a href="javascript:void(0);" data-id='#dikouquan' data-type='2' class="tab">抵扣券</a>
	</div>
	<ul class="title clearfix" id="hongbao_title">
		<li>红包/过期时间</li>
		<li>金额(元)</li>
		<li>状态/操作</li>
	</ul>
	<ul class="title none clearfix" id="tiyanjin_title">
		<li>体验金/过期时间</li>
		<li>体验金(元)</li>
		<li>状态/操作</li>
	</ul>
	<ul class="title none clearfix" id="dikouquan_title">
		<li>抵扣券/过期时间</li>
		<li>金额(元)</li>
		<li>状态/操作</li>
	</ul>
	<div id="wrapper">
		<div id="scroller">
			<div id="hongbao" class="container">
				<div id="hongbao_content"></div>
				<div class="pulldown loading"></div>
			</div>
			<div id="tiyanjin" class="container none">
				<div id="tiyanjin_content"></div>
				<div class="pulldown loading"></div>
			</div>
			<div id="dikouquan" class="container none">
				<div id="dikouquan_content"></div>
				<div class="pulldown loading"></div>
			</div>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>    
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>	
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/jquery.datapage.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script id="template" type="text/x-handlebars-template">
{{#each this}}
<ul class="list clearfix">
	<li class="name_time">
		{{#if name}}<div>{{name}}</div>{{/if}}
		{{#if title}}<div>{{title}}</div>{{/if}}
		<div class="time">{{getEndDate etime overduedate edateStringMobile}}</div>
	</li>
	{{#if money}}<li class="other_info">{{money}}</li>{{/if}}
	{{#if balance}}<li class="other_info">{{balance}}</li>{{/if}}
	{{#checkStatus statusStr status strStatus}}
		<li class="other_info not_used" data-desc='{{desc}}' data-attention='{{attention}}' data-rule='{{ruleDesc}}'>未使用</li>
	{{else}}
		{{#checkStatusStr statusStr}}
			<li class="other_info">{{statusStr}}</li>
		{{else}}
			{{#if strStatus}}<li class="other_info">{{strStatus}}</li>{{/if}}
			{{#if status}}<li class="other_info">{{status}}</li>{{/if}}
		{{/checkStatusStr}}
	{{/checkStatus}}
</ul>
{{/each}}
</script>
<script>
	(function(w,j,h,d,e){
		var request_url = '${ctx}/index/ajax/hd_ajax',
			invested_url = '/wechat/html/index.html?id=${openId}&platform=3',
			openId = '${openId}';
		var tabs = j('.tab'),
			containers = j('.container'),
			titles = j('.title'),
			templateHTML = h.compile(j('#template').html()),
			cache = {
				put : function(key,value){
					this[key] = value;
					return value;
				},
				get : function(key){
					return this[key];
				}
			};
		tabs.on('click',function(){
			var tab = j(this),
				containerId = tab.data('id'),
				titleId = containerId + '_title';
			var container = cache.get(containerId) || cache.put(containerId,j(containerId));
			var title = cache.get(titleId) || cache.put(titleId,j(titleId));
			tabs.removeClass('active');
			tab.addClass('active');//导航栏切换
			containers.addClass('none');
			container.removeClass('none');//内容切换
			titles.addClass('none');
			title.removeClass('none');//标题切换
			d.iScroll.refresh();
			d.setListenerName(containerId);
			if(!d.getListener(containerId)){
				var content = j(containerId + '_content'),
					pulldown = content.siblings('.pulldown'),
					type = tab.data('type');
				d.addListener(containerId,{url:request_url,page:1,content:content,pullDown:pulldown,template:templateHTML,data:{
					tabType : type,
					openId : openId
				}});
				d.getData();
			}
		});
		e.setOkRedirectUrl(invested_url);//设置投资地址
		containers.delegate('.not_used','click',function(){
			var that = j(this);
			var desc = that.data('desc') || that.data('rule'),
				attention = that.data('attention');
			if(desc && attention){
				e.alert1(desc,attention);
			} else {
				w.location.href = 	invested_url;
			};
		});
		//添加红包wrapper监听
		var hongbao_content = j('#hongbao_content'),
			hongbao_pulldown = hongbao_content.siblings('.pulldown');
		d.addListener('#hongbao',{url:request_url,page:1,content:hongbao_content,pullDown:hongbao_pulldown,template:templateHTML,data:{
			tabType : '1',
			openId : openId
		}});
		d.setListenerName('#hongbao');
		d.getData();
		h.registerHelper('getEndDate',function(etime,overduedate,edateStringMobile){
			if(etime){
				return etime;
			} else if(edateStringMobile){
				return edateStringMobile;
			} else {
				return e.convertTime(overduedate);
			}
		});	
		h.registerHelper('checkStatus',function(statusStr,status,strStatus,option){
			if(statusStr){//抵扣券
				if(statusStr == '未使用'){
					return option.fn(this);
				} else {
					return option.inverse(this);
				}
			} else if(strStatus){//体验金
				if(strStatus == '有效'){
					return option.fn(this);
				} else {
					return option.inverse(this);
				}
			} else {//红包
				if(status == '未使用'){
					return option.fn(this);
				} else {
					return option.inverse(this);
				}
			}
		});
		h.registerHelper('checkStatusStr',function(statusStr,option){
			if(statusStr){//抵扣券
				return option.fn(this);
			} else {//红包
				return option.inverse(this);
			}
		});
	})(window,$,Handlebars,dataPage,eloancnUtil);
</script>
</body>
</html>