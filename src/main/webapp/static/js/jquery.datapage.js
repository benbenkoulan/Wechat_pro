/**
 * 基于iscroll的分页
 */
var dataPage = (function(w,j,r){
	return {
		addListener : function(listenerName,listener){//添加scroll监听
			this._listeners = this._listeners || {};
			listener = listener || {};
			listener.data = listener.data || {};
			listener.page = listener.page || 1;
			this._listeners[listenerName] = listener;
		},
		setListenerName : function(listenerName){//设置当前scroll监听
			this.listenerName = listenerName;
		},
		getListener : function(listenerName){//获取scroll监听
			return (this._listeners || {})[listenerName];
		},
		getData : function(){//获取数据
			var that = this;
			var listener = that.getListener(that.listenerName);
			if(!listener){return;}
			listener.data.page = listener.page;
			r.post(listener.url,listener.data,function(data){
				listener.isloading = false;
				listener.canloading = false;
				listener.pullDown.removeClass('loading');
				listener.pullDown.text('上拉加载更多');
				if(data.status){
					var dataInfo = data.info,
						dataArr = (dataInfo && dataInfo.data) || (dataInfo && dataInfo.pageinfoJSON && dataInfo.pageinfoJSON.data) || dataInfo;	
					if(dataArr && dataArr.length){//获取到数据
						if(listener.no_data){
							listener.no_data.remove();
							listener.no_data = null;
						}
						listener.page++;
						listener.content.append(listener.template(dataArr));
						if(!that.iScroll){
							that.createIScroll();
						} else {
							that.iScroll.refresh();
						}
					} else {//没有获取到数据
						if(listener.page == 1 && !listener.no_data){
							listener.content.parent().append('<div class="no_data" style="width:100%;text-align:center;margin-top:50px;font-size:1.0rem;">暂无数据</div>');
							listener.no_data =  listener.content.siblings('.no_data');
						}
						that.iScroll.refresh();
					}
					listener.loadingall = !(dataArr && dataArr.length) ? true : (dataArr.length < 10);//判断是否加载所有数据
					if(listener.loadingall){
						listener.pullDown.remove();
					}
					var callback = listener.callback;
					if(callback){
						callback();
					}
				} else {
					listener.pullDown.remove();
					if(!listener.no_data){
						listener.content.parent().append('<div class="no_data" style="width:100%;text-align:center;margin-top:50px;font-size:1.0rem;">暂无数据</div>');
						listener.no_data =  listener.content.siblings('.no_data');
					}
				}
			});
		},
		init : function(){
			var that = this;
			that.iScroll = new IScroll("#wrapper",{mouseWheel: true,click: true,probeType: 2});
			that.iScroll.on('scroll',function(){//绑定滑动事件
				var listener = that.getListener(that.listenerName);
				if (this.y < (this.maxScrollY - 5) && listener && !listener.loadingall){
					listener.pullDown.text('释放即可刷新');
					listener.canloading = true;//设置可刷新标志
				}
			});
			that.iScroll.on('scrollEnd',function(){
				var listener = that.getListener(that.listenerName);
				if(listener && listener.canloading && !listener.isloading){
					listener.isloading = true;
					listener.pullDown.text('');
					listener.pullDown.addClass('loading');
					that.getData();	
				}
			});
			return that;
		}
	};
})(window,$,requestUtil).init();