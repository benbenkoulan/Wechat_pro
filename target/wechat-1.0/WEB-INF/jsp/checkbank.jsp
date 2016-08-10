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
		<div class="tnd_search">
			<input type="text" placeholder="请输入开户行名称">
		</div>
		<ul class="tnd_belongs tnd_where">
			<c:forEach items="${bank}" var="b">
				<li><a class="bank" data-href="${ctx}/index/add_card?openId=${openId}&type=${type}"><span class="tnd_search_style">${b.bankname_s}</span></a></li>
			</c:forEach>
		</ul>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/handlebars-v3.0.1.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script>
	$('.bank').on('click', function(){
		var _href = $(this).data("href");
		$.ajax({
			type: "POST",
			url: "${ctx}/index/ajax/save_bank",
			data: {openId:'${openId}',bank:$(this).text()},
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
	
	$(function(){
		var _data = JSON.parse('${bank}'),
			inputText = document.querySelector("input[type='text']"),
			_flag = false;
		function filter(e){
		 	var s = e.target.value;
		 	var search_data = [];
			for(var i = 0; i < _data.length; i++){
				if(_data[i].bankname_s.indexOf(s) >= 0){
					search_data.push(_data[i]);
				}
			}
			var bankTemplate = Handlebars.compile($("#item_tpl").html());
			var html = bankTemplate(search_data);
			$(".tnd_where").html(html);
			$('.bank').on('click', function(){
				var _href = $(this).data("href");
				if(_flag){
					return;
				} else {
					_flag = true;
				}
				$.ajax({
					type: "POST",
					url: "${ctx}/index/ajax/save_bank",
					data: {openId:'${openId}',bank:$(this).text()},
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
		}
		
		var ie = !!window.ActiveXObject;  
		if(ie){  
			inputText.onpropertychange = filter;  
		}else{  
			inputText.addEventListener("input",filter,false);  
		}  
	});
</script>
<script type="text/x-handlebars-template" id="item_tpl">
	{{#each this}}
		<li><a class="bank" data-href="${ctx}/index/add_card?openId=${openId}&type=${type}"><span class="tnd_search_style">{{bankname_s}}</span></a></li>
	{{/each}}
</script>
</body>
</html>