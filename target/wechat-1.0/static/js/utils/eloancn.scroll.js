(function(){
	var debounce = function(func,wait){
		var context,args,result;
		var later = function(){
			result = func.apply(context,args);
			context = args = null;
		}
		return function(){
			context = this;
			args = arguments;
			setTimeout(later,wait);	
		}
		return result;
	}
	var scroll = new IScroll('#wrapper',{click:true,probeType:2}),
		currentPageScroll,
		pullDownStart,
		pageScrollCache = {};
	scroll.on('scrollStart',function(){
		pullDownStart = this.y;
	});
	scroll.on('scroll',function(){
		if(this.y < this.maxScrollY && this.pointY < 1){
			this.scrollTo(0,this.maxScrollY,400);
		}
	});
	scroll.on('scrollEnd',debounce(function(){
			if(Math.abs(this.y - pullDownStart) <= 5 && this.directionY >= 0){
				currentPageScroll.next();
			}
		},50)
	);
	function PageScroll(content,pullDown,template,options){
		this.content = content;
		this.pullDown = pullDown;
		this.template = template;
		this.options = {
			page : options.page || 1,
			url : options.url || '',
			data : options.data || {},
			success : this.success.bind(this),
			callback : options.callback
		}
	}
	PageScroll.prototype.success = function(data){
		this.pullDown.removeClass('loading');
		this.pullDown.addClass('loaded');
		this.append(data);
		this.options.callback && this.options.callback();
	};
	PageScroll.prototype.next = function(){
		this.pullDown.removeClass('loaded');
		this.pullDown.addClass('loading')
		this.options.data.page = this.options.page;
		requestUtil.post(this.options);
	};
	PageScroll.prototype.append = function(data){
		if(data.status){
			var info = data.info;
			if(info && info.length){
				this.options.page++;
				this.content.append(this.template(info));
				this.refresh();
				if(info.length < 10){
					this.pullDown.remove();
					this.loadingAll = true;
				}
				return;
			}
		}
		this.loadingAll = true;
		this.pullDown.remove();
		if(this.options.page === 1){
			this.content.addClass('nothing');	
		}
	};
	PageScroll.prototype.refresh = function(){
		window.setTimeout(scroll.refresh.bind(scroll),0);
	};
	window.scrollUtil = {
		addPageScroll : function(name,content,pullDown,template,options){
			var that = this;
			pageScrollCache[name] = new PageScroll(content,pullDown,template,options);
			pullDown.bind('click',that.fetchData.bind(that));
		},
		hasAddedPageScroll : function(name){
			retrun (!!pageScrollCache.name);
		},
		setCurrentPageScroll : function(name){
			currentPageScroll = pageScrollCache[name] || currentPageScroll;
		},
		fetchData : function(){
			currentPageScroll && !(currentPageScroll.loadingAll) && currentPageScroll.next();
		}
	};
})();