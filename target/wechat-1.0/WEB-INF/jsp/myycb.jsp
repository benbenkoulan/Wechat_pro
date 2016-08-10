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
<title>我的翼农计划</title>
<style>
*{margin:0; padding:0; list-style:none; border:none; font-family:\5FAE\8F6F\96C5\9ED1;}
body{background:#f6f2f2; color:#333;font-size:12px;}
a{display:block;text-decoration:none;color:white;}
.subv{color:#e93a3a;font-size:7px;background:#ffdf2a;vertical-align:text-top;padding:1px 2px 0px;border:1px solid #fcba1c;border-radius:2px;font-weight:bold;}
.cyjq{height:48px;border-bottom:1px solid #ececec;width:100%;background:#fff;position: fixed;z-index: 999;}
.cyjq li{font-size:16px;float:left;width:50%;height:48px;line-height:290%;text-align:center;}
.jd{color:#e93a3a;border-bottom:2px solid #e93a3a;text-align:center;}
.lxq li label{color:#6e6e6e;}
.lxq li{font-size:12px;height:36px;border-top:1px solid #f8f8f8;line-height:36px; padding:0px 16px;}
.bottom p{line-height:180%;padding:12px 16px;font-size:12px;color:#6e6e6e;}
.bottom{overflow:hidden;display:none;background:#fff;}
.zqwrap{background:#f6f2f2;padding:8px 10px 0;}
.ycb{padding:12px 10px;background:#fff;border-radius:4px;margin-top:8px;}
.zqlbxq{border-collapse: collapse;width:100%;}
.zqlbxq tr{border-bottom:1px dashed #f6f2f2;height:25px;}
.zqlbxq tr td{font-size:10px;color:#6e6e6e;text-align:left;width:50%;}
.zqlbxq tr td.rtip{text-align:right;}
.shen{background:#ed2929;color:#fff;border-radius:2px;font-size:10px;padding:1px 2px;}
.xi{background:#DAA520;color:#fff;border-radius:2px;font-size:10px;padding:1px 2px;}
.wrapper{position: absolute; z-index: 1; top: 45px; bottom: 0; left: 0; width: 100%;}
.loading{background: url(${static_path}/static/img/loading.gif) 50% 50% no-repeat;}
.pulldown{text-align:center;width: 100%;height: 30px;font-size: .7rem;}
.none{display: none;}
</style>
</head>
<body>
	<ul class="cyjq">
		<li class="tab jd" id="cy" data-contentid="cyz">持有中</li>
	 	<li class="tab" id="jq" data-contentid="yjq">已结清</li>
	</ul>
	<div class="wrapper" id="wrapper">
		<div id="scroller">
			<div id="cyz" class="info">
	            <div class="content" id="cyz_content"></div>
	            <div class="pulldown loading"></div>
	        </div> 
	        <div id="yjq" class="info none">
	         	<div class="content" id="yjq_content"></div>
	         	<div class="pulldown loading"></div>
     		 </div>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>    
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>	
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/jquery.datapage.js"></script>
<script type="text/x-handlebars-template" id="cyz_tpl">
{{#each this}}
<div class="zqwrap cyzycb" 
data-title="{{title}}" data-phases="{{phases}}" 
data-strinterestrate="{{strInterestrate}}" data-vip="{{vip}}" data-id="{{id}}"
data-effectiveamount="{{effectiveamount}}" data-realearn="{{realEarn}}"
data-interdate="{{strInterdate}}">
	<div class="ycb" style="margin-top:0">
		<table class="zqlbxq">
			<tr>
				<td style="font-size:12px;text-align:left;color:#e93a3a;">翼农计划{{title}}</td>
					{{#compareStatus strWmpsStatus}}
						<td class="rtip"><span class="xi">息</span></td>
					{{else}}
						<td class="rtip"><span class="shen">审</span></td>
					{{/compareStatus}}
			</tr>
			<tr>
				<td>投资金额：{{effectiveamount}}元</td>
				<td>预期利率：<span style="color:#e93a3a;">{{strInterestrate}}&nbsp;</span>
				{{#compareVip vip}}
					<span class="subv">VIP</span>
				{{/compareVip}}
				</td>
			</tr>
			<tr>
				<td>已获利息：{{realEarn}}元</td>
				<td>到期利息：{{preEarn}}元</td>
			</tr>
			<tr style="border-bottom:none;">
				<td>计息时间：{{strInterdate}}</td>
				<td>到期时间：{{strEnddate}}</td>
			</tr>
		</table>
	</div>  
</div>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="yjq_tpl">
{{#each this}}
<div class="zqwrap">
	<div class="ycb" style="margin-top:0">
		<table class="zqlbxq">
			<tr>
				<td style="font-size:12px;text-align:left;color:#e93a3a;">翼农计划{{title}}</td>
				<td></td>
			</tr>
			<tr>
				<td>投资金额：{{effectiveamount}}元</td>
				<td>利率：<span style="color:#e93a3a;">{{strInterestrate}}&nbsp;</span>
				{{#compareVip vip}}
					<span class="subv">VIP</span>
				{{/compareVip}}
				</td>
			</tr>
			<tr>
				<td>持有时间：{{phases}}天</td>
				<td>实收利息：{{realEarn}}元</td>
			</tr>
			<tr style="border-bottom:none;">
				<td>计息时间：{{strInterdate}}</td>
				<td>到期时间：{{strEnddate}}</td>
			</tr>
		</table>
	</div>  
</div>
{{/each}}
</script>
<script>
window.openId = '${openId}';
window.contextPath = '${ctx}';
window.requestUrl = '${ctx}/index/ajax/cy_ycb';
(function(w, h, j, d) {
    var cyz_content = j('#cyz_content'),
        cyz_pullDown = cyz_content.siblings('.pulldown'),
        cyz_template = h.compile(j('#cyz_tpl').html());
    d.addListener('cyz', {
        url: w.requestUrl,
        page: 1,
        data: {openId: w.openId},
        pullDown: cyz_pullDown,
        content: cyz_content,
        template: cyz_template
    });
    d.setListenerName('cyz');
    d.getData();
    var eventUtil = {
        tabs: j('.tab'),
        infos: j('.info'),
        jq: j('#jq'),
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
                var listener = d.getListener('yjq');
                if (!listener && 'jq' == tabId) {
                    var yjq_content = j('#yjq_content'),
                        yjq_pullDown = yjq_content.siblings('.pulldown'),
                        yjq_template = h.compile(j('#yjq_tpl').html());
                    d.addListener('yjq', {
                        url: w.requestUrl,
                        page: 1,
                        data: {openId: w.openId,
                            status: 4
                        },
                        pullDown: yjq_pullDown,
                        content: yjq_content,
                        template: yjq_template
                    });
                    d.getData()
                }
            });
            j('body').delegate('.cyzycb', 'click', function() {
                var title = j(this).data('title'),
                    phases = j(this).data('phases'),
                    strInterestrate = j(this).data('strinterestrate'),
                    vip = j(this).data('vip'),
                    effectiveamount = j(this).data('effectiveamount'),
                    realEarn = j(this).data('realearn'),
                    id = j(this).data('id'),
                    interdate = j(this).data('interdate');
                var url = w.contextPath + '/index/ycb_detail?openId=' + w.openId + '&phases=' + phases + '&vip=' + vip + '&effectiveamount=' + effectiveamount + '&realEarn=' + realEarn + '&id=' + id + '&title=' + title + '&interdate=' + interdate;
                strInterestrate = strInterestrate.replace('%', '');
                var s = strInterestrate.split('+');
                for (var i = 0, len = s.length; i < len; i++) {
                    url += ('&s' + i + '=' + s[i])
                }
                w.location.href = encodeURI(url);
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
    h.registerHelper('compareStatus', function(status, option) {
        if (status == '计息中') {
            return option.fn(this)
        } else {
            return option.inverse(this)
        }
    });
    h.registerHelper("compareVip", function(vip, option) {
        if (vip) {
            return option.fn(this)
        } else {
            return option.inverse(this)
        }
    })
})(window, Handlebars, $, dataPage);
</script>
</body>
</html>