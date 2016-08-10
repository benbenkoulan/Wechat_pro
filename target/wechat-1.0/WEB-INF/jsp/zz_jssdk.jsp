<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是一个微信页面</title>
<script src="//res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="${static_path}/static/js/jquery.min.js"></script>
<script src="${static_path}/static/js/jquery.request.js"></script>
<script src="${static_path}/static/js/sha1.js"></script>
</head>
<body>
	<script>
	function StringBuffer(){
		this._strArr = [];
	};
	StringBuffer.prototype = {
		append : function(str){
			this._strArr.push(str);
			return this;
		},
		toString : function(){
			return this._strArr.join('');
		},
		clear : function(){
			this._strArr.length = 0;
		}
	};
	(function(wx , d , m ,w){
		var timestamp = m.round(new Date().getTime()/1000),
		noncestr = 'eloancn';
		requestUtil.post('/wechat/connector/jsticket',{isExpired:false},function(data){
			console.log('----jssticket---' + data);
			var sign = new StringBuffer();
			sign.append('jsapi_ticket=')
				.append(data.ticket)
				.append('&noncestr=')
				.append(noncestr)
				.append('&timestamp=')
				.append(timestamp)
				.append('&url=')
				.append(w.location.href);
			var configData = {
					debug: false,
					appId :'wx98908d372b119c48',
					timestamp : timestamp,
					nonceStr : noncestr,
					signature : hex_sha1(sign.toString()),
					jsApiList : ['onMenuShareTimeline']
			}
			wx.config(configData);
			wx.ready(function(){
				wx.checkJsApi({
					jsApiList : ['onMenuShareTimeline'],
					success: function(res) {
				        /* alert('success:' + res); */
				    }
				});
			});
			wx.error(function(res){
				alert('error:' + res);
			});
			
		});
	})(wx , document , Math , window);
	(function(){
		setInterval(function(){
			wx.onMenuShareTimeline({
				title : '这算不算敏感词',
				desc : '哪里敏感了',
				link : 'https://www.baidu.com/s?wd=翼龙贷',
				trigger: function (res) {
			      /* alert('用户点击发送给朋友'); */
			    },
			    success: function (res) {
			      alert('分享成功');
			    },
			    cancel: function (res) {
			      alert('已取消');
			    },
			    fail: function (res) {
			      alert(JSON.stringify(res));
			    }
			});
		},0);
	})();
</script>
</body>
</html>