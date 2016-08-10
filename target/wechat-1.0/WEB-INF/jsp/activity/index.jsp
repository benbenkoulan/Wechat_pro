<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<title>5月翼龙节感恩大回馈</title>
<link rel="stylesheet" href="${ctx}/static/css/activity/index.css?v=4.1" />
</head>
<body>
	<div id="layer" class="none"></div>
	<div id="redbag_rule" style="width: 90%;left: 5%;top:8%;position: fixed;z-index: 9999;display: none;">
		<div id="rule_exit">
			<img src="${ctx}/static/img/activity/exit1.png" alt="关闭">
		</div>
		<div>
			<img src="${ctx}/static/img/activity/rbtop.png" style="display: block;" alt="翼龙节红包使用规则">
		</div>
		<div style="position: absolute;width: 100%;text-align: center;color: #fff100;font-size: 1rem;top:3%;">红包使用规则</div>
		<div class="rule_content">
			<p>1、每投资一笔"翼农计划"，只能变现该笔投资金额1%以内的红包一个，红包不可拆分变现（比如投资10000元最高可变现100元红包一个）；</p>
			<p>2、多个红包可用时，优先变现金额最大的；有多个金额相同的红包时，优先变现即将过期的；</p>
			<p>3、投资时不用选择使用红包，投资成功后系统自动结算红包金额并发放到账户；</p>
			<p style="color: #fff100;">4、PC可查看红包，暂不支持使用红包，可在APP、微信翼龙钱包、wap站"活动奖励"中查询和使用红包，也可到"交易记录"查询详情；</p>
			<p>5、红包有效期：30天（自红包获得之时起开始生效）；</p>
			<p>6、红包只支持90天及以上的"翼农计划"。</p>
		</div>
	</div>
	<img src="${ctx}/static/img/activity/yidong1.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong2.png" alt="五月翼龙节">
	<div style="width:100%;background:#66be07;text-align:center;padding:10px 0;">
		<img src="${ctx}/static/img/activity/yidong3.png" id="canyu_btn" alt="五月翼龙节">
	</div>
	<div id="rotateContainer">
		<div id="rotateLayer">
			<div id="timer" class="none"></div>
		</div>
		<div class="prize" data-name="iMac一体机">
			<img src="${ctx}/static/img/activity/gift1.png" alt="iMac一体机">
			<div class="prize_name none">iMac一体机</div>
		</div>
		<div class="prize" data-name="Nikon单反相机">
			<img src="${ctx}/static/img/activity/gift2.png" alt="Nikon单反相机">
			<div class="prize_name none">Nikon单反相机</div>
		</div>
		<div class="prize" data-name="iPhone 6s Plus">
			<img src="${ctx}/static/img/activity/gift3.png" alt="iPhone 6s Plus">
			<div class="prize_name none">iPhone 6s Plus</div>
		</div>
		<div class="prize" data-name="三星 Galaxy S7 edge">
			<img src="${ctx}/static/img/activity/gift4.png" alt="三星 Galaxy S7 edge">
			<div class="prize_name none">三星 Galaxy S7 edge</div>
		</div>
		<div class="prize" data-name="小米空气净化器">
			<img src="${ctx}/static/img/activity/gift5.png" alt="小米空气净化器">
			<div class="prize_name none">小米空气净化器</div>
		</div>
		<div class="prize" data-name="Kindle阅读器">
			<img src="${ctx}/static/img/activity/gift6.png" alt="Kindle阅读器">
			<div class="prize_name none">Kindle阅读器</div>
		</div>
		<div class="prize" data-name="富士趣奇相机">
			<img src="${ctx}/static/img/activity/gift7.png" alt="富士趣奇相机">
			<div class="prize_name none">富士趣奇相机</div>
		</div>
		<div class="prize" data-name="谢谢参与">
			<img src="${ctx}/static/img/activity/gift8.png" alt="谢谢参与">
		</div>
		<div class="prize" data-name="九阳智能榨汁机">
			<img src="${ctx}/static/img/activity/gift9.png" alt="九阳智能榨汁机">
			<div class="prize_name none">九阳智能榨汁机</div>
		</div>
		<div class="prize" data-name="索尼移动电源">
			<img src="${ctx}/static/img/activity/gift10.png" alt="索尼移动电源">
			<div class="prize_name none">索尼移动电源</div>
		</div>
		<div class="prize" data-name="亚都空气加湿器">
			<img src="${ctx}/static/img/activity/gift11.png" alt="亚都空气加湿器">
			<div class="prize_name none">亚都空气加湿器</div>
		</div>
		<div class="prize" data-name="京东E卡">
			<img src="${ctx}/static/img/activity/gift12.png" alt="京东E卡">
			<div class="prize_name none">京东E卡</div>
		</div>
		<div class="prize" data-name="谢谢参与">
			<img src="${ctx}/static/img/activity/gift8.png" alt="谢谢参与">
		</div>
		<div class="prize" data-name="iPad">
			<img src="${ctx}/static/img/activity/gift14.png" alt="iPad">
			<div class="prize_name none">iPad</div>
		</div>
		<div id="rotateRule">
			<p>距离开奖时间</p>
			<p><span id="prize_day">00</span>天<span id="prize_hour">00</span>时<span id="prize_minute">00</span>分<span id="prize_second">00</span>秒</p>
			<p style="text-align:justify;font-size:0.6rem;">5月4日、11日、18日、25日、30日上午11：00开奖，请随时关注活动详情。</p>
		</div>
	</div>
	<div id="album">
		<ul id="album_tab" class="clearfix">
			<li data-index="0" class="album_tab_active" style="border-left:0!important;">5月4号</li>
			<li data-index="1">5月11号</li>
			<li data-index="2">5月18号</li>
			<li data-index="3">5月25号</li>
			<li data-index="4">5月30号</li>
		</ul>
	</div>
	<img src="${ctx}/static/img/activity/yidong4.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong5.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong6.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong7.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong8.png" alt="五月翼龙节">
	<div id="prizeContainer" class="none">
		<img src="${ctx}/static/img/activity/close_hb.png" id="close" alt="close">
		<img id="prize_img" src="" alt="奖品">
	</div>
	<img src="${ctx}/static/img/activity/yidong9.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong10.png" alt="五月翼龙节">
	<div style="width:100%;background:#ade7ff;text-align:center;padding:10px 0;">
		<img src="${ctx}/static/img/activity/yidong11.png" style="width:50%;" id="rule_btn" alt="五月翼龙节">
	</div>
	<img src="${ctx}/static/img/activity/yidong12.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong13.png" alt="五月翼龙节">
	<img src="${ctx}/static/img/activity/yidong14.png" alt="五月翼龙节">
	<div id="qrcode_area">
		<img src="${ctx}/static/img/activity/qrcode_1.png" alt="翼龙APP">
	</div>
	<img src="${ctx}/static/img/activity/yidong16.png" alt="五月翼龙节">
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${ctx}/static/js/activity/timer.util.js?v=2.0"></script>
<script src="${ctx}/static/js/activity/activity.loop.js?v=1.0"></script>
<script type="text/x-handlebars-template" id="prize_tpl">
{{#each this}}
<div class="album_unit {{#if active}}album_unit_active{{/if}}">
	<ul class="loop_title clearfix">
		<li>获奖用户</li>
		<li>获得奖品</li>
		<li>投资金额</li>
	</ul>
	<div>
		{{#with top_three}}
		{{#each this}}
		{{#topThreePeriod periods ../../period}}
		<ul class="award-list clearfix">
			<li style="color:#FFEE00;">{{mobile}}</li>
			<li style="color:#FFEE00;">{{name}}</li>
			<li style="color:#FFEE00;">{{totalamount}}</li>
		</ul>
		{{/topThreePeriod}}
		{{/each}}
		{{/with}}
	</div>
	<div class="prize_loop_root">
		<div id="{{albumId}}" class="prize_loop">
		{{#with prize_data}}
		{{#each this}}
		{{#comparePeriod type periods ../../period}}
		<ul class="award-list clearfix">
			<li>{{mobile}}</li>
			<li>{{name}}</li>
			<li>{{totalamount}}</li>
		</ul>	
		{{/comparePeriod}}
		{{/each}}	
		{{/with}}
		</div>
	</div>
</div>
{{/each}}
</script>
<script>
(function(){
	'use strict'
	var album = document.getElementById('album');
	var prize_tpl = document.getElementById('prize_tpl').innerHTML;
	var jsonObj = JSON.parse('${jsonObj}' || '{}');
	var prize_data = jsonObj['prize_data'] || [];
	var top_three = jsonObj['top_three'] || [];
	var array = [{active:1,albumId:'prize_0504',period:1,prize_data:prize_data,top_three:top_three},
	             {albumId:'prize_0511',period:2,prize_data:prize_data,top_three:top_three},
	             {albumId:'prize_0518',period:3,prize_data:prize_data,top_three:top_three},
	             {albumId:'prize_0525',period:4,prize_data:prize_data,top_three:[]},
	             {albumId:'prize_0530',period:5,prize_data:[],top_three:[]}];
	var nextPeriod = parseInt('${next_period}' || '0');
	Handlebars.registerHelper('comparePeriod',function(type,periods,period,option){//type:奖品类型(1:红包,2:实物),periods:奖品期数,period:显示的期数
		if(type == 1 && nextPeriod >= period){//红包，只要满足下一期数大于本期期数
			return option.fn(this);
		} else if(type == 2 && periods == period && nextPeriod > period){//过滤非本期实物奖品
			return option.fn(this);
		}
		return option.inverse(this);
	});
	Handlebars.registerHelper('topThreePeriod',function(periods,period,option){//periods:奖品期数,period:显示的期数
		if(nextPeriod > period && periods == period){
			return option.fn(this);
		} else {
			return option.inverse(this);
		}
	});
	var template = Handlebars.compile(prize_tpl);
	album.innerHTML += template(array);
	loopUtil.createLoop('prize_0504',108,50,true);
	loopUtil.createLoop('prize_0511',108,50);
	loopUtil.createLoop('prize_0518',nextPeriod > 2 ? 108 : 0,50);
	loopUtil.createLoop('prize_0525',nextPeriod > 3 ? 108 : 0,50);
	loopUtil.createLoop('prize_0530',nextPeriod > 4 ? 108 : 0,50);
})();
</script>
<script src="${static_path}/static/js/jquery.request.js?v=1.0"></script>
<script src="${ctx}/static/js/activity/activity.album.js?v=1.0"></script>
<script>
(function(){
	var prizeDay = document.getElementById('prize_day'),
		prizeHour = document.getElementById('prize_hour'),
		prizeMinute = document.getElementById('prize_minute'),
		prizeSecond = document.getElementById('prize_second'),
		timer = document.getElementById('timer'),//倒计时
		rotateLayer = document.getElementById('rotateLayer'),//转盘阴影层
		prizeImg = document.getElementById('prize_img');//奖品图片
	var currentDateTime = ('${currentTime}' || new Date().getTime() / 1000) * 1000;
	var prizeDateTime = new Date('${prizeTime}').getTime();
	var countDown = function(){
		var ms = prizeDateTime - currentDateTime;
		if(ms >= 0){
			var d = Math.floor(ms / 3600000 / 24);
			var h = Math.floor((ms - d * 24 * 3600000)/3600000);
			var min = Math.floor((ms- d * 24 * 3600000 - h*3600000)/60000);
			var s = Math.floor((ms- d * 24 * 3600000 - h*3600000-min*60000)/1000);
			prizeDay.innerHTML = d > 9 ? d : ('0' + d);
			prizeHour.innerHTML = h > 9 ? h : ('0' + h);
			prizeMinute.innerHTML = min > 9 ? min : ('0' + min);
			prizeSecond.innerHTML = s > 9 ? s : ('0' + s);
			if(ms <= 5000){//倒计时五秒
				timer.innerHTML = s;
				timer.classList.remove('none');
			}
		} else {
			rotateLayer.classList.add('none');//去掉转盘阴影
			timer.classList.add('none');//去掉倒计时五秒
			timerUtil.removeTimer('countDown');
			loopUtil.pause();//停止轮播，保证抽奖无误
			prizeUtil.rotate();//摇奖
		}
		currentDateTime += 1000;
	};
	var loadPrizeImg = function(prizeNo,userId){//加载奖品图片
		prizeImg.onload = function(){
			prizeContainer.classList.remove('none');
			layer.classList.remove('none');
			requestUtil.post('${ctx}/activity/update_ajax',{param1:userId},function(data){});
		};
		prizeImg.src = '${ctx}/static/img/activity/app_img_' + prizeNo + '.png';
	};
	var prizeUtil = (function(){
		var userId = '${userId}';
		var tempNo = '${prizeNo}';
		var isCheck = '${isCheck}' || '1';
		//如果没有登录，直接转到谢谢参与
		var prizeNo = (userId && 'null' != userId && tempNo) ? tempNo : (Math.random() > 0.5 ? 7 : 12);
		var confirmFlag = (prizeNo != 7 && prizeNo != 12);
		return {
			rotate : function(){
				if(confirmFlag){//如果中奖，请求确认
					requestUtil.post('${ctx}/activity/prize_ajax',{param1:userId,param2:prizeNo},function(data){
						confirmFlag = false;//设置确认完成标志
						if(!data.status){
							prizeNo = (Math.random() > 0.5 ? 7 : 12);//随机设置一个谢谢参与
						}
					});
				};
				var len = prizes.length;
				var c=1,a=0,speed=30,circle=8;//初始化转盘
				timerUtil.addTimer('rotateTimer',function(){
					for(var i=0;i<len;i++){
						prizes[i].classList.remove('active');
					};
					if(a > 13){//一圈
						a = 0;
						c++;//当前圈数+1
						speed += 25;//改变速度
						timerUtil.changeTimerSpeed('rotateTimer',speed);
					};
					prizes[a].classList.add('active');
					//停止转盘前提:当前圈数大于或等于设置的圈数，当前的奖品号码等于用户中奖号码，和请求确认标志为false
					if(c >= circle && a == prizeNo && !confirmFlag){
						//抽奖完毕，开启轮播
						var active_tab = document.querySelector('.album_tab_active');
						loopUtil.pause(active_tab.getAttribute('data-index'));
						var prizeName = prizes[a].firstChild.nextSibling.nextSibling.nextSibling;
						if(prizeName){
							prizeName.classList.remove('none');
						}
						if(prizeNo != 7 && prizeNo != 12){//中奖提示
							loadPrizeImg(prizeNo,userId);
						}
						timerUtil.removeTimer('rotateTimer');//停止抽奖
					}
					a++;
				},speed);
			},
			displayImg : function(){
				if(!parseInt(isCheck)){
					loadPrizeImg(prizeNo,userId);
				}
			}
		};
	})();
	if(prizeDateTime > currentDateTime){//开奖时间未到
		timerUtil.addTimer('countDown',countDown,1000);
	} else {
		prizeUtil.displayImg();
	}
	window.platform = '${platform}';
	window.touziURL = '${touziUrl}';
})();
</script>
<script src="${ctx}/static/js/activity/activity.init.js?v=1.0"></script>
</body>
</html>