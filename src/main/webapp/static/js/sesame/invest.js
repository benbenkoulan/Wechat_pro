(function(w,j,r,e){
	'use strict'
	var param_obj = e.getParams();
	var id = param_obj.id || '',
		platform = param_obj.platform || '',
		pid = param_obj.pid || '';
	r.postLayer('/wechat/sesame/ajax/invest/init/' + pid,{id:id,platform:platform},function(data){
		if(data.status){
			var info = data.info || {};
			var restAmountValue = info.restAmount || '0.0',
				balanceValue = info.balance,
				maxRate = info.maxRate || 0,
				minRate = info.minRate || 0,
				closePeriodValue = info.minRate || 0,
				holdTimeValue = info.investPeriod || 0,
				closePeriodValue = info.closed_period || '';
			j('#title').append(info.title || '');
			j('#closePeriod').append(closePeriodValue);
			j('#balance').append(info.balance || 0);
			j('#holdTime').append(holdTimeValue);
			j('#interests').append('<span style="font-size:1.4rem;color:#e93a3a;">' + minRate + '</span>%<span style="font-size:1.4rem;color:#e93a3a;">&nbsp;&nbsp;~&nbsp;&nbsp;' + maxRate + '</span> %');
			j('#fwxy').attr('href',info.protocolUrl);
			j('#restAmount').html(restAmountValue);
			j('.enddate').data('lasttime',info.surplusSeconds || 0);
			e.countDown();
			touziUtil.maxRate = maxRate / 100;
			var ratio = keepTwoValue(holdTimeValue / 365);
			touziUtil.term = ratio * 12;
			touziUtil.restAmount = restAmountValue * 10000;
			touziUtil.balance = balanceValue;
			touziUtil.init();
		};
	});
	var keepTwoValue = function(value){
		return value && Math.round(value * 100) / 100;
	};
	var touziUtil = {
		sub:j('#sub'),
		add:j('#add'),
		max:j('#max'),
		num:j('#num'),
		expectInterest:j('#expect_interest'),
		investBtn:j('#invest_btn'),
		checkNum : function(){
			this.expectInterest.html('购买金额必须为100的整数倍');
			this.investBtn.removeClass('invest_btn_active');
			if(this.maxAmount < 100){
				if(this.restAmount == 0){
					this.expectInterest.html('本期投资上限已达到');
				};
				this.sub.removeClass('sub_add_active');
				this.add.removeClass('sub_add_active');
				return;
			};
			var numValue = +(this.num.val() || 0);
			if(numValue == 0){
				this.sub.removeClass('sub_add_active');
				return;
			};
			this.sub.addClass('sub_add_active');
			if(numValue > this.maxAmount){
				return;
			} else if(numValue == this.maxAmount){
				this.add.removeClass('sub_add_active');
			} else {
				this.add.addClass('sub_add_active');
			};
			var num_value = Math.floor(numValue / 100) * 100;
			if(numValue != num_value){//判断是否是100的倍数
				return;
			};
			this.investBtn.addClass('invest_btn_active');
			this.caculateInterest();
		},
		caculateInterest : function(){
			var numValue = +(this.num.val() || 0);
			var profit1 = keepTwoValue(keepTwoValue(numValue * this.maxRate / 12) * this.term);
			this.expectInterest.html('预期收益:<span style="font-size:0.8rem;">(不等同于实际收益)</span><span style="color:#f84d4d;">' + profit1 + '</span>元');
		},
		bindEvent:function(){
			var that = this;
			that.sub.bind('touchstart',function(){
				if(!j(this).hasClass('sub_add_active')){
					return;
				};
				var numValue = +(that.num.val() || 0);
				numValue -= 100;
				that.num.val((numValue > 0) ? numValue : '');
				that.checkNum();
			});
			that.add.bind('touchstart',function(){
				if(!j(this).hasClass('sub_add_active')){
					return;
				};
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
					};
				};
				that.checkNum();
			});
			that.investBtn.bind('touchstart',function(){
				var btn = j(this);
				if(!btn.hasClass('invest_btn_active')){
					return;
				};
				var numValue = +(that.num.val() || 0);
				e.setOkAction(function(){
					e.hideLayer();
					btn.removeClass('invest_btn_active');
					r.postLayer('/wechat/sesame/ajax/invest',{id:id,pid:pid,amount:numValue,platform:platform},function(data){
						e.alert2(data.info);
						if(data.status){
							e.setKnowRedirectUrl(data.url);	
						};
					},'投资中..');
				});
				e.alert1('您确定要投资' + numValue + '元?');
			});
		},
		init:function(){
			var amount = this.restAmount > this.balance ? this.balance : this.restAmount;
			this.maxAmount = Math.floor(amount / 100) * 100;
			this.bindEvent();
			this.checkNum();
		}
	};
})(window,$,requestUtil,eloancnUtil);