(function(w,j,r,e){
	'use strict';
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '',
		type = param_obj.type || '1';
	r.postLayer('/wechat/wmps/ajax/invest/init/' + pid,{id:id,platform:platform},function(data){
		if(data.status){
			var info = data.info || {};
			var balanceValue = info.balance || '0.0',
				termValue = info.term || 0,
				restAmountValue = info.restAmount || '0.0';
			var interest1 = (info.interest1 + '<span style="font-size:0.5rem;color:#888888">%</span>');
			var inter = '';
			var interest2 = info.interest2 && (+info.interest2);
			if(interest2){
				inter = ('+' + interest2);
			}
			var strRatio = info.strRatio && (+info.strRatio);
			if(strRatio){
				inter += ('+' + strRatio);
				inter += ' <img src="/wechat/static/img/v2/vip_03.png" width="25" />';
			}
			if(inter){
				j('#interest1').append(interest1);
			} else {
				j('#interest1').append('&nbsp;&nbsp;' + interest1);
			}
			j('#add_interest').html(inter);
			j('#term').text(termValue + '天');
			var title = j('#title');
			if(info.icon){
				title.append('<img src="' + info.icon + '" style="width:1.1rem;vertical-align:sub;">');
			}
			title.append('翼农计划' + info.period);
			j('#restAmount').text(restAmountValue + '元');
			j('#balance').text(balanceValue);
			j('#licai_url').attr('href',info.licaiUrl);
			j('.enddate').data('lasttime',info.lasttime);
			e.countDown();
			touziUtil.maxAmount = Math.floor((restAmountValue > balanceValue ? balanceValue : restAmountValue) / 100) * 100;
			touziUtil.interest = info.totalInterest || 0;
			touziUtil.term = termValue;
			touziUtil.init();
		}
	});
	var touziUtil = {
		sub:j('#sub'),
		add:j('#add'),
		max:j('#max'),
		num:j('#num'),
		expectInterest:j('#expect_interest'),
		investBtn:j('.invest_btn'),
		checkNum : function(){
			this.expectInterest.html('购买金额必须为100的整数倍');
			this.investBtn.removeClass('invest_btn_active');
			if(this.maxAmount < 100){
				this.sub.removeClass('sub_add_active');
				this.add.removeClass('sub_add_active');
				return;
			}
			var numValue = +(this.num.val() || 0);
			if(numValue === 0){
				this.sub.removeClass('sub_add_active');
				return;
			}
			this.sub.addClass('sub_add_active');
			if(numValue > this.maxAmount){
				return;
			} else if (numValue == this.maxAmount){
				this.add.removeClass('sub_add_active');
			} else {
				this.add.addClass('sub_add_active');
			}
			var num_value = Math.floor(numValue / 100) * 100;
			if(numValue != num_value){
				return;
			}
			this.investBtn.addClass('invest_btn_active');
			this.caculateInterest();
		},
		caculateInterest : function(){
			var numValue = +(this.num.val() || 0);
			var p1 = Math.round((numValue * this.interest) / 365 * 100) / 100;
			var profit = Math.round(p1 * this.term * 100) / 100;
			this.expectInterest.html('预期收益:<span style="font-size:0.8rem;">(不等同于实际收益)</span><span style="color:#f84d4d;">' + profit + '</span>元');
		},
		bindEvent:function(){
			var that = this;
			that.sub.bind('touchstart',function(){
				if(!j(this).hasClass('sub_add_active')){
					return;
				}
				var numValue = +(that.num.val() || 0);
				numValue -= 100;
				that.num.val((numValue > 0) ? numValue : '');
				that.checkNum();
			});
			that.add.bind('touchstart',function(){
				if(!j(this).hasClass('sub_add_active')){
					return;
				}
				var numValue = +(that.num.val() || 0);
				numValue += 100;
				that.num.val((numValue <= that.maxAmount) ? numValue : that.maxAmount);
				that.checkNum();
			});
			that.max.bind('touchstart',function(){
				var maxAmount = that.maxAmount;
				if(maxAmount == 0){
					that.num.val('');
				} else {
					that.num.val(maxAmount);
				}
				that.checkNum();
			});
			that.num.bind('input propertychange',function(){
				var that_num = j(this);
				var numStr = that_num.val();
				if(!e.isNumber(numStr)){
					that_num.val('');
				} else {
					var numValue = +(numStr || 0);
					if(numValue > that.maxAmount){
						that_num.val(that.maxAmount);
					}
				}
				that.checkNum();
			});
			that.investBtn.bind('touchstart',function(){
				var btn = j(this);
				if(!btn.hasClass('invest_btn_active')){
					return;
				}
				var numValue = +(that.num.val() || 0);
				e.setOkAction(function(){
					e.hideLayer();
					e.pauseCountDown();
					btn.removeClass('invest_btn_active');
					r.postLayer('/wechat/wmps/ajax/invest',{pid:pid,id:id,type:type,platform:platform,amount:numValue},function(data){
						e.alert2(data.info);
						if(data.status){
							e.setKnowRedirectUrl(data.url);	
						}
					},'投资中..');	
				});
				e.alert1('您确定要投资' + numValue + '元?');
			});
		},
		init:function(){
			this.bindEvent();
			this.checkNum();
		}
	};
})(window,$,requestUtil,eloancnUtil);