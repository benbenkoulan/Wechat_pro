<%@ include file="/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要投资</title>
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>	
<link rel="stylesheet" href="${static_path}/static/css/style.css" />
<link rel="stylesheet" type="text/css" href="${static_path}/static/css/layer.css" />
</head>
<body>
<!--弹窗层---开始-->
<div class="alert_layer"></div>
<div class="alert_box alert_box_ok">
	<p class="alert_title">提示</p>
	<p class="alert_content alert_content_center"></p>
	<p class="alert_attention"></p>
	<div class="alert_btn_container clearfix">
		<div class="alert_btn alert_btn1 alert_btn_cancel">取消</div>
		<div class="alert_btn alert_btn1 alert_btn_ok">确定</div>
	</div>
</div>
<div class="alert_box alert_box_yes">
	<p class="alert_title">提示</p>
	<p class="alert_content alert_content_center"></p>
	<p class="alert_attention"></p>
	<div class="alert_btn_container">
		<div class="alert_btn alert_btn_know">知道了</div>
	</div>
</div>
<!--弹窗层---结束-->
	<div class="main">
		<div class="bank_table box">
			<dl class="tnd_bank clearfix box">
				<dt>翼农计划<b>${data.title}</b></dt>
				<dd>
					<span>预期年化收益：<b id="nhsy">${data.strInterestrate}</b>
						<c:if test="${isvip != null && isvip.vip}">
							<b id="vipsy">+${isvip.strRatio}</b><img src="${static_path}/static/img/vip.png" style="width:1rem;display:inline-block;margin-left:.2rem;" />
						</c:if> 
					</span>
					<span>合约期：<b id="hyq">${data.strPhases}</b></span>
				</dd>
			</dl>
			<div class="buy_main">
				<form id="myform" action="">
					<fmt:parseNumber value="${info.balance}"  var="balance"  type="NUMBER"/>
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" style="width:4.8rem;">可投金额(元):</td>
							<td><b class="ktNum" style="font-size:1.05rem;">
								${data.maxAmount - data.amount}
							</b></td>
						</tr>
						<tr>
							<td align="right">账户余额(元):</td>
							<td><b class="yeNum" style="font-size:1.05rem;">${info.balance}</b><a href="${ctx}/index/czhi?openId=${openId}" class="buy_a">充值</a></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="tnd_buy">
									<span class="
										<c:choose>
											<c:when test="${balance > 100}">sub</c:when>
											<c:otherwise>sub_bg</c:otherwise>
										</c:choose>
									">-</span>
									<input type="text" name="invest" class="invest box" id="invest" placeholder="请输入购买金额(元)">
									<span class="
										<c:choose>
											<c:when test="${balance > 100}">plus</c:when>
											<c:otherwise>plus_bg</c:otherwise>
										</c:choose>
									">+</span>
								</div>
								<a href="javascript:;" class="buy_amax">最大金额</a>
								<div class="errorTxt investError none"></div>
							</td>
						</tr>
						<tr>
							<td align="right">预计收益(元):</td>
							<td><span id="shouyi">0</span></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="form_btn tnd_bank_btn">
				<a href="javascript:;" class="btn <c:if test="${balance < 100}">btn_bg</c:if>">同意协议并投资</a>
			</div>
			<div class="tnd_with_zi clearfix"><a href="${licaiUrl}?wid=${data.id}">《翼龙贷在线居间服务协议》</a></div>
		</div>
	</div>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/resize.js"></script>
<script src="${static_path}/static/js/eloancn.wechat.util.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script>
	$(function(){
		var wid = '${data.id}';
		$(document).keypress(function(e) {
            if (e.which == 13 || e.keyCode == 13){
               return false;
            }
        });
		$('.buy_amax').on('click', function(){
			var yeNum = $('.yeNum').text().trim();
			if(Number(yeNum) < 100){
				$('.investError').show().html('您的可用余额不足100!');
				return;
			}
			var ktNum = $('.ktNum').text().trim();
			if(Number(yeNum) >= Number(ktNum)){
				$('#invest').val(ktNum);
				count_sy();
				return;
			}
			if(Number(yeNum) < Number(ktNum)){
				$('#invest').val(Math.floor(yeNum / 100) + '00');
				count_sy();
				return;
			}
		});
		
		$('#invest').bind('blur', function(){
			if(!(Number($(this).val()))){
				$('.investError').show().html('请输入100元的整数倍!');
			}else{
				$('.investError').hide();
				var yeNum = $('.yeNum').text();
				if(Number(yeNum) < 100){
					$('.investError').show().html('您的可用余额不足100!');
					return;
				}
				var ktNum = $('.ktNum').text(),
					min = Math.min(yeNum, ktNum);	//计算可投最大金额
				//判断输入的金额是否超过可投最大金额
				if($(this).val() > min){
					var that = $(this);
					eloancnUtil.alert1('您输入金额过大，转为最大可投金额?');
					eloancnUtil.setOkAction(function(){
						that.val(min);
						if(that.val() < 100){
							that.val('0');
						}
						var val = that.val();
						var i = Math.floor(val / 100);
						if(i){
							that.val(i + '00');
						}else{
							that.val('0');
						}
						eloancnUtil.hideLayer();
					});
					that.val('0');
				}
				//合约期
				var contractDays = parseInt($('#hyq').text());
				var Rate = eval($('#nhsy').text().replace('%', '') + $('#vipsy').text().replace('%', ''));
				var interestRate=parseFloat(Rate / 100);
				$.ajax({
		            type: "POST",
		            url: "${ctx}/index/ajax/calculated_interest",
		            data: {openId:'${openId}',amount:$('#invest').val(),'contractDays':contractDays,'interestRate':interestRate},
		            dataType: "json",
		            success: function(data){	
						if(data.status == 1){
							$(data.weizhi).show().html(data.info);		
						}
					}
				});
			}
		});
		$('.plus').bind('click', function(){
			$('.investError').html('');
			var min = Math.min($('.yeNum').text(),$('.ktNum').text());
			if($('#invest').val() > min){
				$('#invest').val(min);
			}
			var new_num = Math.floor(Number($('#invest').val()) / 100) * 100 + 100;
			if(new_num >  min){
				$('#invest').val(min);
			}else{
				$('#invest').val(new_num);
			}
			//合约期
            var contractDays=parseInt($('#hyq').text());
			var Rate = eval($('#nhsy').text().replace('%', '') + $('#vipsy').text().replace('%', ''));
            var interestRate=parseFloat(Rate / 100);
			$.ajax({
	            type: "POST",
	            url: "${ctx}/index/ajax/calculated_interest",
	            data: {openId:'${openId}',amount:$('#invest').val(),contractDays:contractDays,interestRate:interestRate},
	            dataType: "json",
	            success: function(data){        
	                if(data.status == 1){
	                	$(data.weizhi).show().html(data.info);          
	                }
	            }
            });
		});
		$('.sub').bind('click', function(){
			var min = Math.min($('.yeNum').text(),$('.ktNum').text());
			if($('#invest').val() > min){
				$('#invest').val(min);
			}
			var new_num = Math.ceil(Number($('#invest').val()) / 100) * 100 - 100;
			if(new_num <= 0){
				$('#invest').val('0');
				$("#shouyi").html('0');
				return "";
			}
			if(new_num >  min){
            	$('#invest').val(min);
            }else{
               	$('#invest').val(new_num);
            }
			//合约期
            var contractDays=parseInt($('#hyq').text());
           	var Rate = eval($('#nhsy').text().replace('%', '') + $('#vipsy').text().replace('%', ''));
			var interestRate=parseFloat(Rate / 100);
			$.ajax({
                type: "POST",
                url: "${ctx}/index/ajax/calculated_interest",
                data: {openId:'${openId}',amount:$('#invest').val(),'contractDays':contractDays,'interestRate':interestRate},
                dataType: "json",
                success: function(data){
	                if(data.status == 1){
	                	$(data.weizhi).show().html(data.info);
	                }
                }
			});
		});
		$(".btn").bind("click", function(){
			if($(this).hasClass('btn_bg')){
				return;
			}
			var investVal = $('#invest').val();
			if(!(Number(investVal)) || (investVal == '') || (investVal == 0) || (investVal > Number($('.ktNum').html()))){
				$('.investError').show().html('请输入100元的整数倍!');
				$('#invest').focus();
				return;
			}else if(investVal > Number($('.yeNum').html())){
				$('.investError').show().html('账户余额不足!');
				$('#invest').focus();
				return;
			} else{
				$('.numError').hide();
			}
			eloancnUtil.alert1('您确定投资' + investVal + '元?');
			var that = $(this);
			eloancnUtil.setOkAction(function(){
				that.addClass('btn_bg');
				requestUtil.post('${ctx}/index/ajax/touzi_ajax',{openId:'${openId}',wid:wid,amount:$('#invest').val()},function(data){
					that.removeClass('btn_bg');
					eloancnUtil.hideLayer();
					eloancnUtil.alert2(data.info);
					if(data.status){
						eloancnUtil.setKnowRedirectUrl(data.url);
					}
				});
			});
		});
		function count_sy(){
			if(Number($('.yeNum').html()) < 100){
				$('#invest').val('0');
				$('#shouyi').text('0.00');
			}else{
				var temp = parseInt($('#invest').val()) * eval($('#nhsy').text().replace('%', '') + $('#vipsy').text().replace('%', '')) / 36500;
				temp = temp.toFixed(2) * parseInt($('#hyq').text());
				$('#shouyi').text(temp.toFixed(2));
			}
			$('.investError').hide();
		}
		document.addEventListener('touchmove', function(e){e.preventDefault();}, false);
	});
</script>
</body>
</html>