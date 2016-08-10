/**
 * 轮播类
 * author:lb
 */
var loopUtil = (function(){
	function Loop(loopBoxId,size,speed){
		this.init(loopBoxId);
		this.size = size;
		this.speed = speed;
	};
	Loop.prototype.init = function(loopBoxId){
		this.loopBoxId = loopBoxId;
		this.loopBox = document.getElementById(loopBoxId);
		this.loopBox.style.top = '0px';
		this.units = this.loopBox.querySelectorAll('.award-list');
		this.loopCount = 0;
	};
	Loop.prototype.start = function(){
		if(!this.size){
			this.loopBox.innerHTML = "<div style=\"text-align: center;line-height: 200px;color:#ccfa9b;font-size: 2.0rem;\">大奖即将开启</div>";
		} else if(this.size > 10 && !this.loopFlag) {
			timerUtil.addAnimationListener(this.loopBoxId,this,this.loop,this.speed);
			this.loopFlag = true;
		}
	};
	Loop.prototype.loop = function(){
		this.loopCount -= 1;
		this.loopBox.style.top = this.loopCount + 'px';
		if(this.loopCount <= -30){
			var firstItem = this.units[0];
			var newItem = firstItem.cloneNode(true);
			this.loopBox.removeChild(firstItem);
			this.loopBox.appendChild(newItem);
			this.init(this.loopBoxId);
		}
	};
	Loop.prototype.pause = function(){
		timerUtil.removeAnimation(this.loopBoxId);
		this.loopFlag = false;
	};
	var loopCache = [];
	return {
		pause : function(except_index){
			for(var i=0,len=this.cacheSize;i<len;i++){
				var loop = loopCache[i];
				if(i == except_index){
					if(!loop.loopFlag){
						loop.start();
					};
					continue;
				};
				loopCache[i].pause();
			}
		},
		createLoop : function(name,size,speed,isStart){
			var loop = new Loop(name,size,speed);
			if(isStart){
				loop.start();
			}
			loopCache = loopCache || [];
			loopCache.push(loop);//将loop对象存入缓存数组里
			this.cacheSize = loopCache.length;
			return loop;
		}
	};
})();