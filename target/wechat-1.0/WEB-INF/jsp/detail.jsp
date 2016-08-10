<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="翼龙贷、翼农计划、互联网金融、投资理财、P2P理财、网贷、p2p平台、普惠金融、三农贷款、农业贷款、网络理财、如何理财、理财网、怎样理财、如何投资、短期理财、个人理财">
<meta name="description" content="翼龙贷(www.eloancn.com)-专业安全的P2P网贷平台，优质高效的互联网金融理财网。致力于为投资理财用户提供安全、便捷、高收益的p2p理财服务，同时为农业贷款、三农贷款用户提供快速借款渠道。投资理财用户可通过翼龙贷平台投资翼农计划理财产品将闲置资金出借给有贷款需求的人，从而获得稳定高收益实现财富增值。">
<meta name="author" content="翼龙贷">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="mobileOptimized" content="width">
<meta name="handheldFriendly" content="true">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>翼农计划详情</title>
<style>
*{margin:0; padding:0; list-style:none; border:none; font-family:\5FAE\8F6F\96C5\9ED1;}
body{background:#f6f2f2; color:#333;font-size:12px;}
a{display:block;text-decoration:none;color:white;}
.fl{float:left;}
.fr{float:right;}
.textl{text-align:left;}
.textr{text-align:right;}
.ytop{padding:15px 16px 8px;border-bottom:4px solid #f6f2f2;color:#888888;width:100%;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box; background:#fff;}
.ytime{font-size:12px;color:#6e6e6e;}
.xq{margin-top:28px;width:100%;height:70px;}
.butt{background:#f84d4d;width:90%;margin:8px 5%;border-radius:4px; text-align:center;line-height:40px;height:40px;color:#fff;font-size:16px;}
.botp{height:34px;line-height:34px;}
.disp{display:inline-block;}
table{width:100%;}
td{font-size:13px;text-align:center;}
th{font-size:10px;height:28px;}
.subv{color:#e93a3a;font-size:7px;background:#ffdf2a;vertical-align:text-top;padding:1px 2px 0;border:1px solid #fcba1c;border-radius:2px;font-weight:bold;}
.clear{clear:both;}
.line{width:${data.percentage}%;height:2px;background:#e93a3a;line-height:4px;}
.rate{position:absolute;top:-7px;right:0;width:30px;background:white;width:35px;}
.mbot{height:48px;border-bottom:1px solid #ececec;width:100%;background:#fff;}
.mbot li{font-size:16px;float:left;width:33.33%;height:48px;line-height:290%;text-align:center;}
.jd{color:#e93a3a;border-bottom:2px solid #e93a3a;text-align:center;}
.lxq li label{color:#6e6e6e;}
.lxq li{font-size:12px;height:36px;border-top:1px solid #f8f8f8;line-height:36px; padding:0px 16px;}
.bottom p{line-height:180%;padding:12px 16px;font-size:12px;color:#6e6e6e;}
.bottom{overflow:hidden;background:#fff;}
.zqwrap{padding:8px 10px 8px;display:none;}
.pdw{padding:7px 6px;background:#fff;border-radius:4px;margin-top:8px;}
.zqlbxq,.tzlbxq{border-collapse: collapse;width:100%;}
.zqlbxq tr,.tzlbxq tr{border-bottom:1px dashed #cccccc;height:36px;}
.zqlbxq tr td{font-size:10px;color:#6e6e6e;text-align:left;width:50%;}
.tzlb{background:#fff;padding:15px 16px;display:none; position: relative;}
.tzlbxq tr td{font-size:10px;color:#6e6e6e;}
sub,sup {font-size:0.5em;_font-size:0.8em;}
sup {margin-left:-0.5em;} 
.wrapper{position: absolute; z-index: 1; top: 0;bottom: 50px; left: 0; width: 100%;background: #ffffff;}
.loading{background: url(${static_path}/static/img/loading.gif) 50% 50% no-repeat;}
.pulldown{text-align:center;width: 100%;height: 30px;font-size: .7rem;}
</style>
</head>
<body>
<div class="wrapper" id="wrapper">
	<div id="scroller">
		<div class="ytop">
			<div class="fl" style="font-size:14px;">翼农计划<span style="color:#000000;font-size:0.9rem;">&nbsp;${data.title}&nbsp;</span></div>
			<span class="ytime fr"><b class="hour"></b>时 <b class="minute"></b>分 <b class="second"></b>秒</span>
			<div class="xq clear">
				<table>
					<tr>
						<th width="40%">预期年化收益</th>
						<th width="25%">合约期限</th>
						<th width="35%" >募集余额</th>
					</tr>
					<tr>
						<td>
							<span style="font-size:20px;color:#e93a3a;">${data.s0}&nbsp;</span>
							<c:if test="${null != data.s1}">
							<sup style="color:#e93a3a;font-size:12px;">+${data.s1}&nbsp;</sup>
							</c:if>
							<c:if test="${null != data.s2}">
							<sup style="color:#e93a3a;font-size:12px;">+${data.s2}</sup>
							</c:if>
							<c:if test="${isvip != null && isvip.vip}">
							<sup style="color:#e93a3a;font-size:12px;">+${isvip.strRatio}</sup>
							</c:if>
							<sub>
								<c:if test="${isvip != null && isvip.vip}">
								<img style="width:1rem;" src="${static_path}/static/img/vip.png"/>
								</c:if>
							</sub>
						</td>
						<td style=";color:#333;">${data.strPhases}
							<c:choose>
							<c:when test="${data.phasesType == 1}">(天)</c:when>
							<c:otherwise>(月)</c:otherwise>
							</c:choose>
						</td>
						<td style="color:#333;">
							${data.amount}/${data.maxAmount}万
						</td>
					</tr>
				</table>
			</div>
			<div style="position:relative;background: 1px #cccccc;" class="clear">
				<div class="line"></div>
					<span class="rate disp">${data.percentage}%</span>
				</div>
				<p class="botp clear"><span class="fl">剩余可投：<fmt:formatNumber value="${data.restAmount / 10000}" pattern="0.00"></fmt:formatNumber>万</span><span class="fr">参与人数：${data.yuGouNum}</span></p>
			</div>
			<div class="clear">
				<ul class="mbot">
		        	<li class="tab jd" id="cp">产品详情</li>
		            <li class="tab" id="zq">债权列表</li>
		            <li class="tab" id="tz">投资列表</li>
		        </ul>
		        <div class="bottom clear" id="cpxq">
		        	  <p>翼农计划是翼龙贷推出的定期产品，可灵活选择多种期限进行投资。</p>
		              <ul class="lxq">
		              	<li style="background:#f8f8f8;font-size:14px;">投资说明</li>
		              	<li>
		                	<label class="fl">起投金额：</label>
		                    <span class="fr">100元，并以整数倍递增</span>
		                </li>
		                <li>
		                	<label  class="fl">投资人数：</label>
		                    <span class="fr">不限</span>
		                </li>
		                <li>
		                	<label  class="fl">付息方式：</label>
		                    <span class="fr">每日付息，到期还本</span>
		                </li>
		                <li>
		                	<label  class="fl">计息时间：</label>
		                    <span class="fr">${data.strInterdate}</span>
		                </li>
		                <li>
		                	<label  class="fl">首次付息时间：</label>
		                    <span class="fr" >${data.firstInterDate}</span>
		                </li>
		                <li>
		                	<label  class="fl">保障方式：</label>
		                    <span class="fr">风险准备</span>
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
		                    <span class="fr">${data.outDate}</span>
		                </li>
		              </ul>
		        </div>
				<div class="zqwrap"  id="zqlb">
					<div id="zqlb_content"></div>
					<div class="pulldown loading"></div>
				</div>
				<div class="tzlb" id="tzlb">
				<c:choose>
					<c:when test="${null != data.pageinfoJSON.data && data.pageinfoJSON.data.size() > 0}">
		                <table class="tzlbxq" id="tzlb_content">
			            	<tr style="border-top:1px solid #f6f2f2;">
			                    <th width="15%">投资人</th>
			                    <th width="25%">投资金额</th>
			                    <th width="30%">投资方式</th>
			                    <th width="30%">加入时间</th>
	                    		<c:forEach items="${data.pageinfoJSON.data}" var="d">
			                    	<tr height="50">
										<td>${d.realname2}</td>
										<td style="color:#e93a3a;">${d.amount}</td>
										<td>${d.strType}</td>
										<td>${d.strCdate}</td>
									</tr>
			                    </c:forEach> 
			                </tr>
			            </table>
			            <c:choose>
			            	<c:when test="${data.pageinfoJSON.data.size() >= 10}">
				        		<div class="pulldown">上拉或点击加载更多</div>    	
				            </c:when>
			            </c:choose>
		            </c:when>
		            <c:otherwise>
                  		<div style="text-align: center;background: #ffffff;"><br/><br/><br/>暂无数据</div>
                  	</c:otherwise>
		             </c:choose>
		        </div>
			</div>
	</div>
</div>
<div style="position: fixed;bottom: 0;z-index: 1000;width: 100%;background: #ffffff;border: 1px solid #cccccc;height:56px; ">
	<c:choose>
		<c:when test="${data.status == 1}">
			<div id="touzi" class="butt"><a id="check_id" data-href="${ctx}/index/${href}?id=${id}&openId=${openId}">投资</a></div>
		</c:when>
		<c:when test="${data.status == 2 && data.usedTime == 1}">
			<div id="jiesu" class="butt" style="background: #c1c1c1"><a href="javascript:;">已抢光</a></div>
		</c:when>
		<c:otherwise>
			<div id="jiesu" class="butt" style="background: #c1c1c1"><a href="javascript:;">已结束</a></div>
		</c:otherwise>
	</c:choose>
	<div class="butt">投资</div>
</div>
<script src="${static_path}/static/js/jquery.min.js"></script>    
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/iscroll.js"></script>	
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/jquery.datapage.js"></script>
<script type="text/x-handlebars-template" id="tzxq_tpl">
{{#each this}}
<tr height="50">
	<td>{{realname2}}</td>
	<td style="color:#e93a3a;">{{amount}}</td>
	<td>{{strType}}</td>
	<td>{{strCdate}}</td>
</tr>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="zqlb_tpl">
{{#each this}}
<a class="pdw" style="margin-top:0" href="{{compare id}}" data-id="{{id}}">
	<table class="zqlbxq">
		<tr>
			<td colspan="2" style="font-size:12px;text-align:left;color:#333333;">{{detailsTitle}}</td>
		</tr>
		<tr style="height:24px;">
			<td>借款人姓名：{{realname3}}</td>
			<td>身份证号：{{strIdcard}}</td>
		</tr>
		<tr style="height:24px;border-bottom:none;">
			<td>借款人地区：{{disCityname}}</td>
			<td>借款总额：{{amount}}</td>
		</tr>
	</table>
</a>
{{/each}}
</script>
<script>
window.id = '${id}';
window.openId = '${openId}';
window.contextPath = '${ctx}';
window.tenderDetailUrl = '${tenderDetailUrl}';
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
(function(w,j,h,r,d,m) {
    var cp = document.getElementById('cp');
    var zq = document.getElementById('zq');
    var tz = document.getElementById('tz');
    var cpxq = document.getElementById('cpxq');
    var zqlb = document.getElementById('zqlb');
    var tzlb = document.getElementById('tzlb');
    cp.onclick = function() {
        cp.className = "cp jd";
        zq.className = "zq";
        tz.className = "tz";
        zqlb.style.display = "none";
        tzlb.style.display = "none";
        cpxq.style.display = "block";
        d.iScroll.refresh();
        d.setListenerName('')
    };
    zq.onclick = function() {
        zq.className = "zq jd";
        cp.className = "cp";
        tz.className = "tz";
        cpxq.style.display = "none";
        tzlb.style.display = "none";
        zqlb.style.display = "block";
        var listener = d.getListener('zqlb');
        d.setListenerName('zqlb');
        d.iScroll.refresh();
        if (!listener) {
            var content = j('#zqlb_content'),
                pullDown = content.siblings('.pulldown'),
                template = h.compile(j('#zqlb_tpl').html());
            d.addListener('zqlb', {
                url: w.contextPath + '/index/ajax/zqlb_ajax',
                page: 1,
                data: {
                    openId: w.openId,
                    id: w.id,
                    type: '1'
                },
                pullDown: pullDown,
                content: content,
                template: template
            });
            d.getData()
        }
    };
    tz.onclick = function() {
        tz.className = "tz jd";
        cp.className = "cp";
        zq.className = "zq";
        zqlb.style.display = "none";
        cpxq.style.display = "none";
        tzlb.style.display = "block";
        d.setListenerName('tzxq');
        d.iScroll.refresh();
        var listener = d.getListener('tzxq');
        if (!listener) {
            var content = j('#tzlb_content'),
                pullDown = content.siblings('.pulldown'),
                template = h.compile(j('#tzxq_tpl').html());
            d.addListener('tzxq', {
                url: w.contextPath + '/index/ajax/detail_ajax',
                page: 2,
                data: {
                    openId: w.openId,
                    id: w.id
                },
                pullDown: pullDown,
                content: content,
                template: template
            });
            j('.pulldown').on('click',function(){
            	d.getData();
            });
        }
    };
    h.registerHelper('compare', function(id) {
        if (w.tenderDetailUrl) {
            return w.tenderDetailUrl + id
        } else {
            return '#'
        }
    });
    j('#check_id').on('click', function() {
        var _0 = j(this).data('href');
        r.post(w.contextPath + '/index/ajax/check_bind_ajax', {
            openId: w.openId
        }, function(data) {
            if (data.info) {
                alert(data.info)
            }
            if (data.status == 0) {
                location.href = w.contextPath + data.url
            } else if (data.status == 1) {
                location.href = _0
            }
        })
    });
    var timeUtil = {
    	hour : j('.hour'),
    	minute : j('.minute'),
    	second : j('.second'),
    	countDown : function(){
    		var that = this;
    		that.countDownId = w.setInterval(function(){
    			var startTime = new Date();
        		var ms = that.endTime.getTime() - startTime.getTime();
        		var h,min,s;
    			if(ms > 0 && that.status == 1){
        			h = m.floor((ms)/3600000);
        			min = m.floor((ms-h*3600000)/60000);
        			s = m.floor((ms-h*3600000-min*60000)/1000);
        		} else {
        			h = '00';
        			min = '00';
        			s = '00';
        			w.clearInterval(that.countDownId);
        		}
    			that.hour.html(h);
    			that.minute.html(min);
    			that.second.html(s);
    		},1000);
    	},
    	init : function(){
    		this.endTime = new Date('${data.end_date}');
    		this.status = '${data.status}';
    		var startTime = new Date();
    		var ms = this.endTime.getTime() - startTime.getTime();
    		if(ms > 0){
    			this.countDown();
    		} else {
    			this.hour.html('00');
    			this.minute.html('00');
    			this.second.html('00');
    		}
    	}
    }
    timeUtil.init();
})(window, $, Handlebars, requestUtil, dataPage,Math);
</script> 
</body>
</html>