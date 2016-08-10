/**
 * 基于iscroll的分页
 */
(function(w,j){
	function DataPageListener(listener){
		this.template = listener.template;
		this.pullDown = listener.pullDown;
		this.content = listener.content;
		this.page = listener.page || 1;
		this.data = listener.data || {};
		this.url = listener.url;
		this.callback = listener.callback;
	}
	DataPageListener.prototype.success = function(data){
		this.isloading = false;
		this.canloading = false;
		this.pullDown.removeClass('loading');
		this.pullDown.text('上拉加载更多');
		if(data.status){
			var dataInfo = data.info,
				dataArr = (dataInfo && dataInfo.data) || (dataInfo && dataInfo.pageinfoJSON && dataInfo.pageinfoJSON.data) || dataInfo;	
			if(dataArr && dataArr.length){//获取到数据
				if(this.no_data){
					this.no_data.remove();
					this.no_data = null;
				}
				this.page++;
				this.content.append(this.template(dataArr));
				iScroll.refresh();
			} else {//没有获取到数据
				if(this.page == 1 && !this.no_data){
					this.content.parent().append('<div class="no_data" style="width:100%;text-align:center;margin-top:50px;font-size:1.4rem;">暂无数据</div>');
					this.no_data =  this.content.siblings('.no_data');
				}
				iScroll.refresh();
			}
			(!dataArr || !dataArr.length || dataArr.length < 10) && this.pullDown.addClass('none');
			this.callback && this.callback();
		} else {
			this.pullDown.addClass('none');
			if(!this.no_data){
				this.content.parent().append('<div class="no_data" style="width:100%;text-align:center;margin-top:50px;font-size:1.4rem;">暂无数据</div>');
				this.no_data =  this.content.siblings('.no_data');
			}
		}
	}
	var dataPageUtil = {
		addListener : function(listenerName,listener){//添加scroll监听
			this._listeners = this._listeners || {};
			listener = listener || {};
			this._listeners[listenerName] = new DataPageListener(listener);
		},
		setListenerName : function(listenerName){//设置当前scroll监听
			this.listenerName = listenerName;
		},
		refreshRequestData : function(listenerName,data){
			var listener = this.getListener(listenerName);
			listener.page = data.page;
			listener.data = data;
		},
		refreshIScroll : function(){
			iScroll.refresh();
		},
		getListener : function(listenerName){//获取scroll监听
			return (this._listeners || {})[listenerName || this.listenerName];
		},
		getData : function(){//获取数据
			var listener = this.getListener();
			if(!listener){return;}
			listener.data.page = listener.page;
			w.requestUtil.post(listener);
		}
	};
	var iScroll = new IScroll("#wrapper",{mouseWheel: true,click: true,probeType: 2});
	iScroll.on('scroll',function(){//绑定滑动事件
		var listener = dataPageUtil.getListener();
		if (this.y < (this.maxScrollY - 5) && listener && !listener.loadingall && !listener.pullDown.hasClass('none')){
			listener.pullDown.text('释放即可刷新');
			listener.canloading = true;//设置可刷新标志
		}
	});
	iScroll.on('scrollEnd',function(){
		var listener = dataPageUtil.getListener();
		if(listener && listener.canloading && !listener.isloading){
			listener.isloading = true;
			listener.pullDown.text('');
			listener.pullDown.addClass('loading');
			dataPageUtil.getData();	
		}
	});
	w.dataPageUtil = dataPageUtil;
})(window,$);