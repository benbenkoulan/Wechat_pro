<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>投资得红包</title>
<link rel="stylesheet" href="${ctx}/static/css/activity/redbag.css" />
</head>
<body>
	<c:choose>
		<c:when test="${hiddenClose}">
			<img src="${ctx}/static/img/activity/nopic.png" id="nopic" alt="恭喜您，投资成功">
		</c:when>
		<c:otherwise>
			<div id="invest_success_area">
				<img src="${ctx}/static/img/activity/invest_success.png" alt="恭喜您，投资成功">
				<p>恭喜您，投资成功</p>
				<a class="success_btn" href="${investUrl}">返回投资页</a>
				<a class="success_btn" href="${myAccountUrl}">返回我的账户</a>
			</div>
		</c:otherwise>
	</c:choose>
	<!-- 阴影层 -->
	<div id="layer"></div>
	<!-- 阴影层 -->
	<div id="redbag_area">
		<c:if test="${!hiddenClose}">
		<img src="${ctx}/static/img/activity/close_hb.png" id="redbag_close" alt="close">
		</c:if>
		<div id="redbag_title">恭喜您</div>
		<div id="redbag_number">获得${number}个红包</div>
		<div id="redbag_group"><span>${group}</span>元</div>
		<div id="redbag_img_area">
			<img alt="红包" src="${ctx}/static/img/activity/${number}hb.png">
			<div id="redbag"><span>${sum}</span>元</div>
			<div id="prize_tip_area">
				<p>实物奖品将于</p>
				<p class="prize_tip_bold">5月4日、11日、18日、25日、30日</p>
				<p class="prize_tip_bold">上午11:00开奖</p>
				<p>敬请关注</p>
			</div>
		</div>
	</div>
<script>
(function(){
	var layer = document.getElementById('layer'),
		redbag_area = document.getElementById('redbag_area'),
		redBagClose = document.getElementById('redbag_close');
	if(redBagClose){
		redBagClose.addEventListener('click',function(){
			document.body.removeChild(layer);
			document.body.removeChild(redbag_area);
		},false);
	}
	var auto = setInterval(function(){
		layer.style.opacity = 0.7;
		redbag_area.style.opacity = 1;
		var nopic = document.getElementById('nopic');
		if(nopic){
			nopic.style.display = 'none';
		}
		clearInterval(auto);
	},500);
})();
</script>
</body>
</html>