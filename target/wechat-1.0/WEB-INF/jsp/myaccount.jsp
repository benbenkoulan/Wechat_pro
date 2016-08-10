<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,uer-scalabe=no">
	<title>${pageTitle}</title>
    <link rel="stylesheet" href="${static_path}/static/css/style1.css" />
</head>

<body style="background:#f6f2f2">
	<div style="height:650px;">
	<header class="header">
    	<div class="head">
            <span class="tou">
            	<img src="${static_path}/static/images/head_03.png" width="55" />
            	<img src="${static_path}/static/images/vip_03.png" class="vip" width="25px" />
            </span>
            <span class="name">高小洁</span>
        </div>
        <p class="money">今日收益(元)</p>
        <p class="number">36.88</p>
        <div class="zichan">
        	<p class="zichan-left"><span>总资产(元)</span><b>66878.00</b></p>
            <p><span>已赚利息(元)</span><b id="available">3206.00</b></p>
        </div>
    </header>
    <ul class="list-item">
    	<li><a href="">翼农计划</a></li>
        <li><a href="">芝麻开花</a></li>
        <li><a href="">散标投资<span class="daishou">本月待收 108.56元</span></a></li>
        <li><a href="">交易记录</a></li>
        <li><a href="" id="active">活动奖励<span class='daishou' style="color: #333;"></span></a></li>
        <li><a href="">账户安全</a></li>
    </ul>
    <div class="recharge">
    	<button class="recharge-btn">充值</button>
        <button class="cash-btn">提现</button>
    </div>
    </div>
    <footer class="footer">
    	<ul>
        	<li><img src="${static_path}/static/images/index_03.png" width="15%" /><br>首页</li>
            <li><img src="${static_path}/static/images/my_03.png" width="15%" /><br><span class="foot-active">我的</span></li>
        </ul>
    </footer>
    <script src="${static_path}/static/js/jquery.min.js"></script>
    <script src="${static_path}/static/js/jquery.request.js"></script>
</body>
<script>
	(function(d,j,r){
		r.post("${ctx}/public/ajax/myaccount?id="+'${openId}',{platform:4},function(data){
			var o = data.info;
			$("span.name").html(o.name);
			$("p.number").html(o.today_profit);
			$("p.zichan-left").find("b").html(o.total_amount);
			$("#available").html(o.available_amount);
			var n = parseInt(o.redbag_number);
			var v = o.vip_rate;
			if(n>=0){
				$("a#active").find("span.daishou").html(n+"张").show();
			}
			else{
				$("a#active").find("span.daishou").hide();
			}
			if(v == "" || 0 == parseFloat(v)){
				$("span.tou").find("img.vip").hide();
			}
			else{
				$("span.tou").find("img.vip").show();
			}
		});
	})(document,$,requestUtil)
</script>
</html>
