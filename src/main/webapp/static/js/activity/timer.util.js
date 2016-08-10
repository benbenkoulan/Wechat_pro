/**
 * 定时任务工具类
 * author:lb
 */
var timerUtil = (function(w){
	var timerCache = {};
	var requestAnimFrame = (function(){
		return w.requestAnimationFrame || 
				w.webkitRequestAnimationFrame ||
				w.mozRequestAnimationFrame ||
				function(callback){
					w.setInterval(callback,1000 / 16);
				};
	})();
	var cancelAnimFrame = (function(){
		return w.cancelAnimationFrame ||
				w.webkitCancelAnimationFrame ||
				w.mozCancelAnimationFrame ||
				function(id){
					w.clearInterval(id);
				};
	})();
	function Timer(auto,fun,speed){
		this.auto = auto;
		this.fun = fun;
		this.speed = speed;
	};
	return {
		addAnimation : function(animationName,callback,initSpeed){
			var tempDateTime = new Date().getTime();
			var fun = function(){
				var auto = requestAnimFrame(fun);
				timerCache[animationName] = auto + '_Animation';
				var cDateTime = new Date().getTime();
				if((cDateTime - tempDateTime) < initSpeed)return;
				tempDateTime += initSpeed;
				callback();
			}
			fun();
		},
		addAnimationListener : function(animationName,obj,callback,initSpeed){
			var tempDateTime = new Date().getTime();
			var fun = function(){
				var auto = requestAnimFrame(fun);
				timerCache[animationName] = auto + '_Animation';
				var cDateTime = new Date().getTime();
				if((cDateTime - tempDateTime) < initSpeed)return;
				tempDateTime += initSpeed;
				callback.call(obj);
			}
			fun();
		},
		removeAnimation : function(animationName){
			var autoKey = timerCache[animationName];
			if(timer){
				cancelAnimFrame(parseInt(autoKey));
			}
		},
		addTimer : function(timerName,fun,initSpeed){
			var auto = w.setInterval(fun,initSpeed);
			var timer = new Timer(auto,fun,initSpeed);
			timerCache[timerName] = timer;
		},
		removeTimer : function(timerName){
			var timer = timerCache[timerName];
			if(timer && timer.auto){
				w.clearInterval(timer.auto);
			}
			return timer;
		},
		changeTimerSpeed : function(timerName,speed){
			var timer = this.removeTimer(timerName);
			this.addTimer(timerName,timer.fun,speed);
		},
		init : function(){
			return this;
		}
	}
})(window).init();