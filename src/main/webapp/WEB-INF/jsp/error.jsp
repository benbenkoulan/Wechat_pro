<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>错误页面</title>
<meta charset="utf-8">
<meta name="keywords" content="翼龙贷、翼农计划、互联网金融、投资理财、P2P理财、网贷、p2p平台、普惠金融、三农贷款、农业贷款、网络理财、如何理财、理财网、怎样理财、如何投资、短期理财、个人理财">
<meta name="description" content="翼龙贷(www.eloancn.com)-专业安全的P2P网贷平台，优质高效的互联网金融理财网。致力于为投资理财用户提供安全、便捷、高收益的p2p理财服务，同时为农业贷款、三农贷款用户提供快速借款渠道。投资理财用户可通过翼龙贷平台投资翼农计划理财产品将闲置资金出借给有贷款需求的人，从而获得稳定高收益实现财富增值。">
<meta name="author" content="翼龙贷">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="mobileOptimized" content="width">
<meta name="handheldFriendly" content="true">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<style>
*{margin:0; padding:0; list-style:none; border:none; font-family:\5FAE\8F6F\96C5\9ED1;}
body{background:#fff; color:#333;}
.wrap{width:100%;}
.wenz{text-align:center;font-size:14px;}
.hselo{display:block;margin:39% auto 7%;}
</style>
</head>
<body>
	<div class="wrap">
		<img src="${static_path}/static/img/hselo.png" class="hselo" width="26%"/>
	    <div class="wenz">${errorMsg}<a href="${returnUrl}">点击返回</a></div>
	</div>
</body>
</html>