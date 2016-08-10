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
		<ul class="tnd_belongs tnd_city">
			<c:forEach items="${city}" var="c">
				<li><a class="city" data-href="${ctx}/index/check_bank?openId=${openId}"><span>${c}</span></a></li>
			</c:forEach>
		</ul>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	var _flag = false;
	$('.city').on('click', function(){
		var _href = $(this).data("href");
		if(_flag){
			return;
		} else {
			_flag = true;
		}
		$.ajax({
			type: "POST",
			url: "${ctx}/index/ajax/save_city",
			data: {openId:'${openId}',city:$(this).text()},
			dataType: "json",
			success: function(){
				_flag = false;
				location.href = _href;
			},
			error: function(jqXHR ,textStatus, errorThrown){
				_flag = false;
				if(textStatus == 'timeout'){
					alert("网络不给力，请稍后再试！");
				}
			}
		});
	});
</script>
</body>
</html>