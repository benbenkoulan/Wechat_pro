<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>${pageTitle}</title>
<style>
		*{
			margin: 0;
			padding: 0;
			font-size: 14px;
		}
		html , body{
			background: #f6f6f6;
		}
		.zhaiquan{
			background: #FFFFFF;
			width: 96%;
			margin-left: 2%;
		}
		.zhaiquan header{
			padding:8px;
		}
		.zhaiquan ul{
			list-style: none;
			border-bottom:1px solid #ff0000;
			padding:5px 0;
		}
		.zhaiquan ul li{
			float:left;
			border-top: 1px #ff0000 dotted;
			padding-left: 1%;
			padding-top:5px;
			padding-bottom:5px;
		}
		@media screen and (max-width:540px){
			.zhaiquan ul li{
				width: 98%;
			}
		}
		@media screen and (min-width: 550px){
			.zhaiquan ul li{
				width: 49%;
			}
		}
		.zhaiquan ul li div{
			display: inline-block;
			text-align: center;
		}
		.title{
			width:30%;
		}
		.clearfix:after{
			display: block;
			content:"";
			visibility: hidden;
			clear: both;
		}
	</style>
</head>
<body>	
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
	<script type="text/x-handlebars-template" id="tpl_zhaiquan">
		{{#each this}}
		<div class="zhaiquan" data-url="{{compare ../tenderDetailUrl id}}">
			<header>
				<h2>借款名称：{{detailsTitle}}</h2>
			</header>
			<ul class="clearfix">
				<li>
					<div class="title">借款人</div>
					<div>{{realname3}}</div>
				</li>
				<li>
					<div class="title">身份证号</div>
					<div>{{strIdcard}}</div>
				</li>
				<li>
					<div class="title">借款人地区</div>
					<div>{{disCityname}}</div>
				</li>
				<li>
					<div class="title">借款总额</div>
					<div>{{amount}}</div>
				</li>
				<li>
					<div class="title">借款利率</div>
					<div>{{strTenderInter}}%</div>
				</li>
				<li>
					<div class="title">借款期限</div>
					<div>{{phases}}天</div>
				</li>
			</ul>	
		</div>
	{{/each}}
	</script>
	<script>
		(function(){
			var data,
				tpl,
				zhaiquanTpl,
				html,
				jsonData = '${data}';
			if(jsonData){
				data = JSON.parse(jsonData);
				data.tenderDetailUrl = '${tenderDetailUrl}';
			}
			tpl = $('#tpl_zhaiquan').html();
			Handlebars.registerHelper('compare',function(tenderDetailUrl,id){
				if(tenderDetailUrl){
					return tenderDetailUrl + id;
				} else {
					return null;
				}
			});
			zhaiquanTpl = Handlebars.compile(tpl);
			html = zhaiquanTpl(data);
			if(html.trim()){
				$('body').html(html);
			}else{
				$('body').html("<br/><br/><br/><div style='text-align:center;'>暂无数据</div>");	
			}
			$('.zhaiquan').on('click',function(){
				var url = $(this).data('url');
				if(url){
					window.location.href = url;
				}
			});
		}());
	</script>
</body>
</html>