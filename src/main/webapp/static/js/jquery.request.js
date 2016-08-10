/**
 * 请求工具类
 */
var requestUtil = (function(jq){
	var request_flag = false,
		layer_request_flag = false,
		layer_html1 = '<div id="post_layer" style="width:100%;height:100%;background:#ffffff;opacity:0;margin:0;padding:0;position:absolute;left:0;top:0;z-index:999;"></div>'
					+ '<div id="post_loading" style="width:32%;padding-top:5%;left:34%;top:32%;position:fixed;z-index:1000;opacity:0.8;background:black;border-radius:6px;text-align:center;color:#ffffff;">'
					+ '<img src="/wechat/static/img/v2/loading.gif" style="width:26%;max-width:100%;display:inline;">'
					+ '<p style="text-align:center;width:100%;font-size:12px;padding-top:6%;padding-bottom:8%;">',
		layer_html2 = '</p></div>',
		post_layer,post_loading;
	var setLayerFlag = function(flag){
		layer_request_flag = flag;
	};
	var showLayer = function(content){
		if(post_layer && post_loading && !content){
			post_layer.show();
			post_loading.show();
		} else {
			if(post_layer){
				post_layer.remove();
			};
			if(post_loading){
				post_loading.remove();
			};
			content = content || '正在加载';
			jq('body').append(layer_html1 + content + layer_html2);		
			post_layer = jq('#post_layer');
			post_loading = jq('#post_loading');
		}
	};
	var hideLayer = function(){
		if(post_layer && post_loading){
			post_layer.hide();
			post_loading.hide();
		}
	};
	return {
		post : function(url,data,success_fun){
			if(request_flag){
				return;
			} else {
				request_flag = true;
			}
			var that = this;
			jq.post(url,data,success_fun,'json').error(function(jqXHR, textStatus, errorThrown){
			if (textStatus == 'timeout') {
			alert('网络不给力，请稍后再试');
			}}).complete(function(){
					that.setFlag(false);
				});
			},
		setFlag : function(flag){
			request_flag = flag;
		},
		postLayer : function(url,data,success_fun,content){
			if(layer_request_flag){
				return;
			} else {
				setLayerFlag(true);
			}
			showLayer(content);
			var that = this;
			jq.post(url,data,success_fun,'json').error(function(jqXHR, textStatus, errorThrown){
				if (textStatus == 'timeout') {
				alert('网络不给力，请稍后再试');
				}}).complete(function(){
					setLayerFlag(false);
					hideLayer();
				});
		}
	}
}($));