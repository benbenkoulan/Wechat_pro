/**
 * js wechat util
 * **/
var eloancnUtil = (function(w,d,jq){
	var alert_layer = d.querySelector('.alert_layer'),
		alert_box_ok = d.querySelector('.alert_box_ok'),
		alert_box_yes = d.querySelector('.alert_box_yes'),
		alert_btn_ok = d.querySelector('.alert_btn_ok'),
		alert_btn_cancel = d.querySelector('.alert_btn_cancel'),
		alert_btn_know = d.querySelector('.alert_btn_know');
	var bind = function(node,event_type,fun){
		if(!node){return;}
		if(typeof node.addEventListener == 'function'){
			node.addEventListener(event_type,fun);
		} else if(typeof alert_btn_cancel.attachEvent == 'function'){
			node.attachEvent(event_type,fun);
		}
	};
	var setNodeContent = function(root_node,content,attention){
		var alert_content = root_node.firstChild.nextSibling.nextSibling.nextSibling;
		if(typeof alert_content.innerText == 'string'){
			alert_content.innerText = content;
		} else if(typeof alert_content.textContent == 'string'){
			alert_content.textContent.textContent = content;
		}
		if(attention){
			var alert_attention = alert_content.nextSibling.nextSibling;
			if(typeof alert_attention.innerText == 'string'){
			alert_attention.innerText = attention;
		} else if(typeof alert_attention.textContent == 'string'){
			alert_attention.textContent.textContent = attention;
		}
	}
	};
	function StringBuffer(str){
		this.cache = [];
		if(str || str == 0){
			this.cache.push(str);
		}
	}
	StringBuffer.prototype.append = function(str){
		if(str || str == 0){
			this.cache.push(str);
		}
		return this;
	};
	StringBuffer.prototype.toString = function(){
		return this.cache.join('');
	};
	var reg = /^[1-9]+[0-9]*$/,
		init_time = {day:0,hour:0,minute:0,second:0};
	var getTime = function(ms,type){
		if(!ms)return;
		var time = {};
		if(type){
			time.day = Math.floor(ms / (60 * 60 * 24));
			time.hour = Math.floor((ms - time.day * 60 * 60 * 24) / (60 * 60));
			time.minute = Math.floor((ms - time.day * 60 * 60 * 24 - time.hour * 60 * 60 ) / 60);
			time.second = Math.floor(ms - time.day * 60 * 60 * 24 - time.hour * 60 * 60 - time.minute * 60);
		} else {
			time.hour = Math.floor(ms / (60 * 60));
			time.minute = Math.floor((ms - time.hour * 60 * 60 ) / 60);
			time.second = Math.floor(ms - time.hour * 60 * 60 - time.minute * 60);
		}
		return time;
	};
	var getTimeText = function(time,type){
		var minute = time.minute,
			second = time.second,
			hour,day;
			sb = new StringBuffer();
		if(type == 1){
			day = time.day;
			hour = time.hour;
			sb.append(day < 10 ? '0' : '');
			sb.append(day);
			sb.append('天');
			sb.append(hour < 10 ? '0' : '');
			sb.append(hour);
			sb.append(' 时 ');
			sb.append(minute < 10 ? '0' : '');
			sb.append(minute);
			sb.append(' 分 ');
			sb.append(second < 10 ? '0' : '');
			sb.append(second);
			sb.append(' 秒');
		} else if(type == 2){
			sb.append(minute < 10 ? '0' : '');
			sb.append(minute);
			sb.append(':');
			sb.append(second < 10 ? '0' : '');
			sb.append(second);
		} else {
			hour = time.hour;
			sb.append(hour < 10 ? '0' : '');
			sb.append(hour);
			sb.append(' 时 ');
			sb.append(minute < 10 ? '0' : '');
			sb.append(minute);
			sb.append(' 分 ');
			sb.append(second < 10 ? '0' : '');
			sb.append(second);
			sb.append(' 秒');
		}
		return sb.toString();
	};
	return {
		isNumber : function(str){
			return reg.test(str);
		},
		getParams : function(href){
			var param_obj = {};
			href = href || w.location.href;
			var strs = href.split('?');
			if(strs.length > 1){
				var href_str = strs[1];
				var params = href_str.split('&');
				for(var i=0,len=params.length;i<len;i++){
					var ps = params[i].split('=');
					param_obj[ps[0]] = (ps[1] || '');
				}
			}
			param_obj.platform = param_obj.platform || '3';
			return param_obj;
		},
		refreshState : function(href){
			href = href || w.location.href;
			var strs = href.split('&v=');
			var new_href = strs[0] + '&v=' + Math.random();
			w.history.replaceState('',w.document.title,new_href);
		},
		hideLayer : function(){
			var auto = setInterval(function(){
				alert_layer.style.display = 'none';
				alert_box_yes.style.display = 'none';
				alert_box_ok.style.display = 'none';
				clearInterval(auto);
			},0);
		},
		setOkRedirectUrl : function(url){
			this._okRedirectURL = url;
			this._okFlag = 1;
		},
		setOkAction : function(fun){
			this._okAction = fun;
			this._okFlag = 2;
		},
		setKnowRedirectUrl : function(url){
			this._knowRedirectURL = url;
			this._knowFlag = 1;
		},
		setKnowAction : function(fun){
			this._knowAction = fun;
			this._knowFlag = 2;
		},
		setAlertContet : function(type,content,attention){
			if(type == 'ok'){
				setNodeContent(alert_box_ok,content,attention);
			} else if(type == 'yes'){
				setNodeContent(alert_box_yes,content,attention);
			}
		},
		bindEvent : function(){
			var that = this;
			bind(alert_btn_cancel,'click',function(e){
				var target = e.target || e.srcElement;
				var box = target.parentNode.parentNode;
				alert_layer.style.display = 'none';
				box.style.display = 'none';
			});
			bind(alert_btn_ok,'click',function(e){
				if(that._okRedirectURL && that._okFlag == 1){
					w.location.href = that._okRedirectURL;
				}
				if(that._okAction && that._okFlag == 2){
					that._okAction();
				}
			});
			bind(alert_btn_know,'click',function(e){
				var target = e.target || e.srcElement;
				var box = target.parentNode.parentNode;
				alert_layer.style.display = 'none';
				box.style.display = 'none';
				if(that._knowRedirectURL && that._knowFlag == 1){
					w.location.href = that._knowRedirectURL;
				}
				if(that._knowAction && that._knowFlag == 2){
					that._knowAction();
				}
			});
		},
		alert1 : function(content,attention){
			if(content){
				setNodeContent(alert_box_ok,content,attention);
			}
			var auto = setInterval(function(){
				alert_layer.style.display = 'block';
				alert_box_ok.style.display = 'block';
				clearInterval(auto);
			});
		},
		alert2 : function(content,attention){
			if(content){
				setNodeContent(alert_box_yes,content,attention);
			}
			var auto = setInterval(function(){
				alert_layer.style.display = 'block';
				alert_box_yes.style.display = 'block';
				clearInterval(auto);
			});
		},
		convertTime : function(timestamp){
			var date = new Date(timestamp*1000),
				month = date.getMonth()+1,
				day = date.getDate();
			return date.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day>9 ? day : ('0' + day)) + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
		},
		refreshEnd : function(){
			this.enddates = jq('.enddate:not(".end")');
		},
		pauseCountDown : function(){
			w.clearInterval(this.countDownAuto);
		},
		countDown : function(type,callback){
			var that = this;
			if(!that.enddates){
				that.refreshEnd();
			}
			that.countDownAuto = w.setInterval(function(){
				var len = that.enddates.size();
				for(var i=0;i<len;i++){
					var enddate = jq(that.enddates[i]);
					var ms = parseInt(enddate.data('lasttime'));
					if(ms > 0 && !enddate.hasClass('end')){
						var time = getTime(ms,type);
						if(time){
							enddate.text(getTimeText(time,type));
							enddate.data('lasttime',--ms);
						}
					} else {
						enddate.addClass('end');
						enddate.html(getTimeText(init_time,type));
						if(callback){
							callback.call(enddate);
						}
					}
				}
			},1000);
		},
		init : function(){
			this.bindEvent();
			return this;
		}
	};
})(window,document,$).init();