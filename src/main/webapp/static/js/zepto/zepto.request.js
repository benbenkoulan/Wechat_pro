/**
 * 请求工具类
 */
(function(w,z){
	var request_flag = false,
		layer_request_flag = false,
		layer_html1 = '<div id="post_layer" style="width:100%;height:100%;background:#ffffff;opacity:0;margin:0;padding:0;position:absolute;left:0;top:0;z-index:999;"></div>'
					+ '<div id="post_loading" style="width:32%;padding-top:5%;left:34%;top:32%;position:absolute;z-index:1000;opacity:0.8;background:black;border-radius:6px;text-align:center;color:#ffffff;">'
					+ '<img src="/wechat/static/img/v2/loading.gif" style="width:26%;max-width:100%;display:inline;">'
					+ '<p style="text-align:center;width:100%;font-size:12px;padding-top:6%;padding-bottom:8%;">',
		layer_html2 = '</p></div>',
		post_layer,post_loading;
	var setFlag = function(flag){
			request_flag = flag;
	};
	var setLayerFlag = function(flag){
		layer_request_flag = flag;
	};
	var showLayer = function(content){
		if(post_layer && post_loading && !content){
			post_layer.show();
			post_loading.show();
		} else {
			post_layer && post_layer.remove();
			post_loading && post_loading.remove();
			content = content || '正在加载';
			z('body').append(layer_html1 + content + layer_html2);		
			post_layer = z('#post_layer');
			post_loading = z('#post_loading');
		}
	};
	var hideLayer = function(){
		if(post_layer && post_loading){
			post_layer.hide();
			post_loading.hide();
		}
	};
	w.requestUtil =  {
	post : function(options){
		if(request_flag){
			return;
		} else {
			request_flag = true;
		}
		var complete = options.complete;
		options.type = 'POST';
		options.dataType = 'json';
		options.timeout = 15000;
		options.success = options.success.bind(options);
		options.complete = function(){
			setFlag(false);
			complete && complete();
		}
		options.error = function(xhr, errorType, error){

		}
		z.ajax(options);
	},
	postLayer : function(options){
		if(layer_request_flag){
			return;
		} else {
			setLayerFlag(true);
		}
		showLayer(options.content);
		var complete = options.complete;
		options.type = 'POST';
		options.dataType = 'json';
		options.timeout = 15000;
		options.success = options.success.bind(options);
		options.complete = function(){
			setLayerFlag(false);
			hideLayer();
			complete && complete();
		}
		options.error = function(xhr, errorType, error){
			
		}
		z.ajax(options);
	}
}
}(window,$));