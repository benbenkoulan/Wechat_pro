/**
 * 相册效果js
 * author:lb
 */
(function(){
	'use strict'
	function ChainUnit(node,before,after,tab){
		this.node = node;
		this.before = before;
		this.after = after;
		this.tab = tab;
	};
	ChainUnit.prototype.focus = function(className){
		this.node.classList.add(className);
		if(this.before){
			this.before.classList.remove(className);
		}
		if(this.after){
			this.after.classList.remove(className);
		}	
	};
	ChainUnit.prototype.goNext = function(className){
		this.node.classList.remove(className);
		if(this.after){
			this.after.classList.add(className);
		}
	};
	ChainUnit.prototype.goPrev = function(className){
		this.node.classList.remove(className);
		if(this.before){
			this.before.classList.add(className);
		}
	};
	var album = document.getElementById('album');
	var width = album.clientWidth;
	var height = album.clientHeight;
	var album_units = document.querySelectorAll('.album_unit');
	var album_tab = document.getElementById('album_tab');
	var album_tabs = document.querySelectorAll('#album_tab li');
	var album_length = album_units.length;
	var firstUnit = album_units[0];
	var lastUnit = album_units[album_length -1];
	var chains = [];
	var tabChains = [];
	var currentIndex = 0;
	for(var i=0;i<album_length;i++){
		var album_unit = album_units[i];
		album_unit.style.width = width + 'px';
		album_unit.style.height = height + 'px';
		album_unit.style.left = i * width + 'px';
		chains.push(new ChainUnit(album_unit,album_units[i-1],album_units[i+1]));
		tabChains.push(new ChainUnit(album_tabs[i],album_tabs[i-1],album_tabs[i+1]))
	};
	var sx,//初始位置
	x,//滑动位置
	sd=0,//需要移动的距离
	flag=0,//移动的类型
	changeWidth = parseInt(width/3);
	album.addEventListener('touchstart',function(e){
		var touchNode = e.targetTouches[0];
		x = Math.floor(touchNode.clientX);
		sx = x;
		for(var i=0;i<album_length;i++){
			album_units[i].classList.remove('transition');
		};
	});
	album.addEventListener('touchmove',function(e){
		var touchNode = e.targetTouches[0];
		var mx = Math.floor(touchNode.clientX);
		var px = mx - x;
		var d = sx - mx;
		x = mx;
		for(var i=album_length - 1;i>=0;i--){
			var album_unit = album_units[i];
			var left = parseInt(album_unit.style.left) || 0;
			album_unit.style.left = left + px + 'px';
		};
		var firstUnitLeft = parseInt(firstUnit.style.left);
		var lastUnitLeft = parseInt(lastUnit.style.left);
		if(!currentIndex){
			currentIndex = 0;
		}
		var albumUnit = chains[currentIndex];
		var tabUnit = tabChains[currentIndex];
		if(d > changeWidth && lastUnitLeft > 0){
			albumUnit.goNext('album_unit_active');
			tabUnit.goNext('album_tab_active');
			sd = d - width;
			flag = 1;
		} else if(d < - changeWidth && firstUnitLeft < 0){
			albumUnit.goPrev('album_unit_active');
			tabUnit.goPrev('album_tab_active');
			sd = width + d;
			flag = 2;
		} else {
			albumUnit.focus('album_unit_active');
			tabUnit.focus('album_tab_active');
			sd = d;
			flag = 0;
		}
	});
	album.addEventListener('touchend',function(e){
		if(flag == 1 && currentIndex < album_length){
			currentIndex++;
		} else if(flag == 2 && currentIndex >= 0){
			currentIndex--;
		}
		for(var i=0;i<album_length;i++){
			var album_unit = album_units[i];
			album_unit.classList.add('transition');
			var left = parseInt(album_unit.style.left) || 0;
			album_unit.style.left = left + sd + 'px';
		};
		loopUtil.pause(currentIndex);
		x = 0;
		sx = 0;
		sd = 0;
		flag = 0;
	});
	album_tab.addEventListener('click',function(e){
		var target = e.target;
		if(target.nodeName.toUpperCase() == 'LI'){
			currentIndex = parseInt(target.getAttribute('data-index'));
			for(var i=0;i<album_length;i++){
				var album_unit = album_units[i];
				album_unit.classList.add('transition');
				album_unit.classList.remove('album_unit_active');
				album_unit.style.left = width * (i - currentIndex) + 'px';
				album_tabs[i].classList.remove('album_tab_active');
			};
			target.classList.add('album_tab_active');
			album_units[currentIndex].classList.add('album_unit_active');
			loopUtil.pause(currentIndex);
		}
	});
})();