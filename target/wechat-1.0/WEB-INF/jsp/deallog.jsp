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
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/layer.css" />
<style type="text/css">
	#wrapper *{
		-webkit-box-sizing: border-box;
	}
	#wrapper {
		position: absolute;
		z-index: 1;
		top: 0;
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
	#scroller #records{
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
<!--弹窗层---开始-->
<div class="alert_layer"></div>
<div class="alert_box alert_box_yes">
	<p class="alert_title">提示</p>
	<p class="alert_content alert_content_center"></p>
	<p class="alert_attention"></p>
	<div class="alert_btn_container">
		<div class="alert_btn alert_btn_know">知道了</div>
	</div>
</div>
<!--弹窗层---结束-->
	<div class="main">
		<div id="wrapper">
			<div id="scroller">
				<div class="records" id="records"></div>
				<div class="pulldown loading"></div>
			</div>
		</div>
		<a href="javascript:;" style="z-index:100;" class="records_ico"></a>
	</div>
	<!--弹出层-->
	<div class="layer none">
		<div class="layer_main position">
			<div class="records_title box">筛选方式</div>
			<div class="records_screening box clearfix">
				<h3>选择交易类型</h3>
				<ul class="records_select clearfix">
					<li class="cur" data-val="1">全部</li>
					<li data-val="2">充值</li>
					<li data-val="3">放款</li>
					<li data-val="4">提现</li>
					<li data-val="5">债权</li>
				</ul>
				<h3>选择查看时间 <span style="font-size:.5rem;color:#f00;">(起始时间不能大于等于结束时间)</span></h3>
				<ul class="records_time clearfix">
					<li>起始时间</li>
					<li>
						<div class="custom_select">
							<div class="lable">请选择</div>
							<div class="icon"></div>
							<select id="start_year" class="select">
								<option value="">请选择</option>
								${str}
							</select>
						</div>
						<div class="records_time_txt">年</div>
						<div class="custom_select">
							<div class="lable">请选择</div>
							<div class="icon"></div>
							<select id="start_month" class="select">
								<option value="">请选择</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
						</div>
						<div class="records_time_txt">月</div>
					</li>
					<li>终止时间</li>
					<li>
						<div class="custom_select">
							<div class="lable">请选择</div>
							<div class="icon"></div>
							<select id="end_year" class="select">
								<option value="">请选择</option>
								${str}
							</select>
						</div>
						<div class="records_time_txt">年</div>
						<div class="custom_select">
							<div class="lable">请选择</div>
							<div class="icon"></div>
							<select id="end_month" class="select">
								<option value="">请选择</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
						</div>
						<div class="records_time_txt">月</div>
					</li>
				</ul>
			</div>
			<div class="records_btn clearfix">
				<a href="javascript:;" class="close">取消</a>
				<a class="btn" href="javascript:;">确定</a>
			</div>
		</div>
	</div>
	<!--弹出层-->
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/jquery.datapage.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script type="text/x-handlebars-template" id="tpl_records">
{{#each this}}
	<dl class="clearfix">
		<dt>
			<span>{{description}}</span>
		{{#compare income}}
			<b>-{{outgo}}</b>
			{{else}}	
			<b class="records_add">{{income}}</b>
		{{/compare}}
		</dt>
		<dd>
			<span>{{strDate}}</span>
			<b>余额: {{balance}}</b>
		</dd>
	</dl>
{{/each}}
</script>
<script>
(function(j,r,e,h,d){
	var contextPath = '${ctx}',
		openId = '${openId}';
	var dealLogUrl = contextPath + '/index/ajax/log_ajax',
		request_data = {openId:openId,operateType:''};
	var records_ico = j('.records_ico'),
		layer = j('.layer'),
		record_select_li = j('.records_select li'),
		select = j('.select'),
		btn = j('.btn'),
		close = j('.close'),
		records = j('#records'),
		pulldown = records.siblings('.pulldown'),
		tpl_records = j('#tpl_records'),
		start_year,start_month,end_year,end_month;
	var listener = {url:dealLogUrl,page:1,content:records,pullDown:pulldown,template:h.compile(tpl_records.html()),data:request_data};
	d.setListenerName('deal_log');
	d.addListener('deal_log',listener);
	d.getData();
	records_ico.bind('click',function(){
		layer.show();
	});
	record_select_li.bind('click',function(){
		record_select_li.removeClass('cur');
		j(this).addClass('cur');
	});
	select.bind('change',function(){
		var that = j(this);
		var text = that.find("option:checked").text();
		that.siblings(".lable").text(text);
	});
	close.bind('click',function(){
		layer.hide();
	});
	btn.bind('click',function(){
		start_year = start_year || j('#start_year');
		end_year = end_year || j('#end_year');
		start_month = start_month || j('#start_month');
		end_month = end_month || j('#end_month');
		var start,end,startYearVal,startMonthVal,endYearVal,endMonthVal;
		startYearVal = start_year.val();
		if(startYearVal){
			start = startYearVal;	
			startMonthVal = start_month.val() || '01';
			start += startMonthVal;
		}
		endYearVal = end_year.val();
		if(endYearVal){
			end = endYearVal;
			endMonthVal = end_month.val() || '01';
			end += endMonthVal;
		}
		if(start > end){
			e.alert2('开始时间不能大于结束时间');
		} else {
			request_data['startdate'] = startYearVal + '-' + startMonthVal + '-01';
			request_data['enddate'] = endYearVal + '-' + endMonthVal + '-01';
			request_data['operateType'] = j('.cur').data('val');
			listener['data'] = request_data;
			listener['page'] = 1;
			records.html('');
			d.addListener('deal_log',listener);
			d.getData();
			layer.hide();
		}
	});
	h.registerHelper("compare" , function(income,option) {
        if(income == 0) {
            return option.fn(this);
        } else {
            return option.inverse(this);
        }
    });
})($,requestUtil,eloancnUtil,Handlebars,dataPage);
</script>
</body>
</html>