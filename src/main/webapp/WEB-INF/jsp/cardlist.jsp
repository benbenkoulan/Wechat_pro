<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>	
<link rel="stylesheet" href="${static_path}/static/css/style.css" />
<style type="text/css">
	body{background:#fff;}
</style>
</head>
<body>
<div class="main">
	<ul class="tnd_belongs">
		<c:forEach items="${bank}" var="b">
			<li>
				<a class="city" data-val="${b.key}" data-href="${ctx}/index/add_card?openId=${openId}&type=${b.key}">
				<i><img src="${static_path}/static/img/bank/${b.key}.png"></i>
				<span>${b.value}</span>
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	$('.city').on('click', function(){
		var _href = $(this).data("href");
		$.ajax({
			type: "POST",
			url: "${ctx}/index/ajax/save_card",
			data: {openId:'${openId}',type:$(this).data('val')},
			dataType: "json",
			success: function(){
				location.href = _href;
			},
			error: function(jqXHR ,textStatus, errorThrown){
				if(textStatus == 'timeout'){
					alert("网络不给力，请稍后再试！");
				}
			}
		});
	});
</script>
</body>
</html>