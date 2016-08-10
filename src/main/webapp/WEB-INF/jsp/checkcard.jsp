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
</head>
<body>
	<div class="main">
		<div class="tnd_with box clearfix">
			<c:forEach items="${data}" var="d">
				<a href="${ctx}/index/tixian?openId=${openId}&cardId=${d.id}">
					<dl class="tnd_with_top clearfix tnd_with_top_dl">
						<dt><img src="${static_path}/static/img/bank/${d.type}.png" ><span>${d.strType}</span></dt>
						<dd>尾号:${d.account}<img src="${ctx}/static/img/with_ico1.png" ></dd>
					</dl>
				</a>
			</c:forEach>
			<div class="form_btn with_btn"><a href="${ctx}/index/add_card?openId=${openId}" class="btn">添加银行卡</a></div>
		</div>
	</div>
<script src="${static_path}/static/js/resize.js"></script>
</body>
</html>