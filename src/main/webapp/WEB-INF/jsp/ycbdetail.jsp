<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>我的翼农计划详情</title>
<style>
*{margin:0; padding:0; list-style:none; border:none; font-family:\5FAE\8F6F\96C5\9ED1;}
body{background:#f6f6f6; color:#333;font-size:12px;}
a{display:block;text-decoration:none;color:#6e6e6e;}
.fl{float:left;}
.fr{float:right;}
.textl{text-align:left;}
.textr{text-align:right;}
.ytop{padding:15px 16px 8px;border-bottom:4px solid #f6f2f2;color:#888888;width:100%;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box; background:#fff;}
.ytime{font-size:12px;color:#6e6e6e;}
.xq{margin-top:28px;width:100%;height:70px;}
.butt{margin:2% 6% 3%;background:#ff2c2c;border-radius:4px;text-align:center;line-height:40px;height:40px;color:#fff;font-size:16px;}
.botp{height:34px;line-height:34px;}
.disp{display:inline-block;}
table{width:100%;}
td{font-size:16px;text-align:center;}
th{font-size:10px;height:28px;}
.subv{color:#e93a3a;font-size:6px;background:#ffdf2a;vertical-align:text-top;padding:0px 1px 0;border:1px solid #fcba1c;border-radius:2px;font-weight:bold;}
.clear{clear:both;}
.line{width:100%;height:2px;background:#e93a3a;line-height:4px;}
.rate{position:absolute;top:-7px;right:0;width:30px;background:white;width:35px;}
.mbot{height:48px;border-bottom:1px solid #ececec;width:100%;background:#fff;}
.mbot li{font-size:16px;float:left;width:50%;height:48px;line-height:290%;text-align:center;}
.jd{color:#e93a3a;border-bottom:2px solid #e93a3a;text-align:center;}
.lxq li label{color:#6e6e6e;}
.lxq li{font-size:12px;height:36px;border-top:1px solid #f8f8f8;line-height:36px; padding:0px 16px;}
.bottom p{line-height:180%;padding:12px 16px;font-size:12px;color:#6e6e6e;}
.bottom{overflow:hidden;background:#fff;}
.pdw{padding:7px 6px;background:#fff;border-radius:4px;margin-top:8px;}
.zqlbxq,.tzlbxq{border-collapse: collapse;}
.zqlbxq tr,.tzlbxq tr{border-bottom:1px solid #f6f2f2;height:25px;}
.zqlbxq tr td{font-size:10px;color:#6e6e6e;text-align:left;}
.wz{text-align:center;margin-top:100px;font-size:14px;}
.wrapper{position: absolute; z-index: 1; top: 0;bottom: 0; left: 0; width: 100%;background: #ffffff;}
.loading{background: url(${static_path}/static/img/loading.gif) 50% 50% no-repeat;}
.pulldown{text-align:center;width: 100%;height: 30px;font-size: .7rem;border: 1px solid #f6f6f6;}
sub,sup {font-size:0.5em;_font-size:0.8em;}
sup {margin-left:-0.5em;}
dl{padding:2px 0;font-size: 10px;color: #666666;float: left;border-top-style: dashed;border-top-width: 1px;border-top-color: #dddddd;}
dl dt{float: left;line-height: 22px;}
dl dd{float: left;line-height: 22px;}
.dl{width: 40%;}
.dr{width: 60%;}
.title{color: #333333;font-size: 14px;font-weight: normal;line-height: 30px;border: 1px solid #ffffff;}
.clearfix:after{content: '.';visibility: hidden;clear: both;height: 0;display: block;}
.tender{background:#ffffff;width:96%;padding:0 2%;margin: 10px 0;border-top:1px solid #f0f0f0;border-bottom:1px solid #f0f0f0;}
.none{display:none;}
</style>
</head>
<body>
	<div class="wrapper" id="wrapper">
		<div id="scroller">
			<div class="ytop">
				<div class="fl" style="font-size:14px;">翼农计划<span style="color:#000000;font-size: 0.9rem;" id="title"></span>&nbsp;(${phases}天)</div>
				<span class="ytime fr"><a href="${licaiUrl}">协议</a></span>
				<div class="xq clear">
					<table>
						<tr>
							<th width="40%">预期年化收益</th>
							<th width="35%">持有金额(元)</th>
							<th width="25%" >已获利息(元)</th>
						</tr>
						<tr>
							<td>
							<span style="font-size:22px;color:#e93a3a;">${s0}</span><sub>%<c:if test="${vip ==1}"><img style="width:1rem;" src="${static_path}/static/img/vip.png"/></c:if></sub><sup>
							<c:if test="${s1 != null && s1 != ''}">
							<sup style="color:#e93a3a;font-size:10px;">+${s1}</sup>
							</c:if>
							<c:if test="${s2 != null && s2 != ''}">
							<sup style="color:#e93a3a;font-size:10px;">&nbsp;+${s2}</sup>
							</c:if> 
							</sup>
						</td>
							<td style="color:#333;">${effectiveamount}</td>
							<td style="color:#333;">${realEarn}</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="clear">
				<ul class="mbot">
				<li class="tab jd" id="cp" data-contentid="cpxq">产品详情</li>
				<li class="tab" id="zq" data-contentid="zqlb">债权列表</li>
				</ul>
				<div class="info bottom clear" id="cpxq">
					<p>翼农计划是翼龙贷推出的定期产品，可灵活选择多种期限进行投资。</p>
					<ul class="lxq">
						<li style="background:#f8f8f8;font-size:14px;">投资说明</li>
						<li>
							<label class="fl">起投金额：</label>
							<span class="fr">100元，并以整数倍递增</span>
						</li>
						<li>
							<label class="fl">投资人数：</label>
							<span class="fr">不限</span>
						</li>
						<li>
							<label class="fl">付息方式：</label>
							<span class="fr">每日付息，到期还本</span>
						</li>
						<li>
							<label class="fl">计息时间：</label>
							<span class="fr">${interdate}</span>
						</li>
						<li>
							<label class="fl">首次付息时间：</label>
							<span class="fr" >${scfxDate}</span>
						</li>
						<li>
							<label class="fl">保障方式：</label>
							<span class="fr">风险准备金</span>
						</li>
						<li style="background:#f8f8f8; font-size:14px;">
							<label>退出说明</label>
							<span class="fr"></span>
						</li>
						<li>
							<label  class="fl">退出方式：</label>
							<span class="fr">期满自动退出</span>
						</li>
						<li>
							<label class="fl">退出时间：</label>
							<span class="fr">${tcDate}</span>
						</li>
					</ul>
				</div>
				<div class="info none"  id="zqlb">
					<div id="zqlb_content"></div>
					<div class="pulldown loading"></div>
				</div>
			</div>
		</div>
	</div>    
<script src="${static_path}/static/js/jquery.min.js"></script>    
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>	
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/jquery.datapage.js"></script>
<script type="text/x-handlebars-template" id="zqlb_tpl">
{{#each this}}
<a class="tender" href="{{compare id}}" data-id="{{id}}">
	<div class="title">{{detailsTitle}}</div>
	<div class="clearfix">
		<dl class="dl clearfix">
			<dt>借款人姓名：</dt>
			<dd>{{realname3}}</dd>
		</dl>
		<dl class="dr">
			<dt>身份证号：</dt>
			<dd>{{strIdcard}}</dd>
		</dl>
		<dl class="dl clearfix">
			<dt>借款人地区：</dt>
			<dd>{{disCityname}}</dd>
		</dl>
		<dl class="dr">
			<dt>借款总额：</dt>
			<dd>{{amount}}</dd>
		</dl>
	</div>
</a>
{{/each}}
</script>
<script>
window.id = '${id}';
window.openId = '${openId}';
window.contextPath = '${ctx}';
window.tenderDetailUrl = '${tenderDetailUrl}';
(function(w, j, h, d) {
	var url = w.location.search;
	if(url.indexOf('?') != -1){
		var str = url.substr(1);
		var params = str.split('&');
		var title;
		for(var i=0,len=params.length;i<len;i++){
			var map = params[i].split('=');
			if('title' == map[0]){
				j('#title').html('&nbsp;' + decodeURI(map[1]) + '&nbsp;');
				break;
			}
		}
	}
	var eventUtil = {
        tabs: j('.tab'),
        infos: j('.info'),
        bindEvent: function() {
            var that = this;
            that.tabs.bind('click', function() {
                that.tabs.removeClass('jd');
                that.infos.addClass('none');
                j(this).addClass('jd');
                var tabId = j(this).attr('id');
                var tab = that.infoMap[tabId];
                if (tab) {
                    tab.removeClass('none')
                }
                d.iScroll.refresh();
                d.setListenerName(j(this).data('contentid'));
                var listener = d.getListener('zqlb');
                if (!listener && 'zq' == tabId) {
                    var content = j('#zqlb_content'),
                        pullDown = content.siblings('.pulldown'),
                        template = h.compile(j('#zqlb_tpl').html());
                    d.addListener('zqlb', {
                        url: w.contextPath + '/index/ajax/zqlb_ajax',
                        page: 1,
                        data: {
                            openId: w.openId,
                            id: w.id
                        },
                        pullDown: pullDown,
                        content: content,
                        template: template
                    });
                    d.getData()
                } else {
                    d.iScroll.refresh()
                }
            })
        },
        init: function() {
            var that = this;
            that.bindEvent();
            that.infoMap = {};
            that.tabs.each(function() {
                var tabId = j(this).attr('id');
                var info = j('#' + j(this).data('contentid'));
                that.infoMap[tabId] = info
            })
        }
    };
    eventUtil.init();
    h.registerHelper('compare', function(id) {
        if (w.tenderDetailUrl) {
            return w.tenderDetailUrl + id
        } else {
            return '#'
        }
    });
})(window, $, Handlebars, dataPage);</script>
</body>
</html>