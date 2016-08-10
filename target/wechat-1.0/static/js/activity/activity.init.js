/**
 * 设定转盘大小，位置等等
 * author:lb
 */
(function(){
	var prizes = document.querySelectorAll('.prize');//转盘单元
	var width = document.body.clientWidth * 0.92;//转盘总宽
	var padding = 0.02 * document.body.clientWidth;//转盘的空隙
	var prizeWidth = (width - padding * 2) / 5;//每个方块的宽
	var rotateContainer = document.getElementById('rotateContainer'),//转盘容器
		rotateRule = document.getElementById('rotateRule');//转盘规则
	rotateContainer.style.height = prizeWidth * 4 + padding * 2 +  'px';//设置转盘高度=方块高度*4加上空隙
	rotateContainer.style.width = width +  'px';
	rotateRule.style.left = padding * 2 + prizeWidth + 'px';//设置转盘规则位置
	rotateRule.style.top = padding * 2 + prizeWidth + 'px';
	rotateRule.style.width = prizeWidth * 3 - padding * 2 + 'px';
	rotateRule.style.height = prizeWidth * 2  - padding * 3+ 'px';
	rotateRule.style.padding = padding + "px 0";
	//初始化转盘各个奖项的位置
	for(var i=0,len=prizes.length;i<len;i++){
		var prize = prizes[i];
		prize.style.width = prizeWidth + 'px';
		prize.style.height = prizeWidth + 'px';
		switch(i){
			case 0:{
				prize.style.left = padding + 'px';
				prize.style.top = padding + 'px';
			};break;
			case 11:{
				prize.style.left = padding + 'px';
				prize.style.top = padding + prizeWidth * 3 + 'px';
			};break;
			case 12:{
				prize.style.left = padding + 'px';
				prize.style.top = padding + prizeWidth * 2 + 'px';
			};break;
			case 13: {
				prize.style.left = padding + 'px';
				prize.style.top = padding + prizeWidth + 'px';
			};break;
			case 1:{
				prize.style.left = padding + prizeWidth + 'px';
				prize.style.top = padding + 'px';
			};break;
			case 10: {
				prize.style.left = padding + prizeWidth + 'px';
				prize.style.top = padding + prizeWidth * 3 + 'px';
			};break;
			case 2: {
				prize.style.left = padding + prizeWidth * 2 +'px';
				prize.style.top = padding + 'px';
			};break;
			case 9: {
				prize.style.left = padding + prizeWidth * 2 +'px';
				prize.style.top = padding + prizeWidth * 3 + 'px';
			};break;
			case 3: {
				prize.style.left = padding + prizeWidth * 3 + 'px';
				prize.style.top = padding + 'px';
			};break;
			case 8: {
				prize.style.left = padding + prizeWidth * 3 + 'px';
				prize.style.top = padding + prizeWidth * 3 + 'px';
			};break;
			case 4: {
				prize.style.left = padding + prizeWidth * 4 +'px';
				prize.style.top = padding + 'px';
			};break;
			case 5: {
				prize.style.left = padding + prizeWidth * 4 +'px';
				prize.style.top = padding + prizeWidth + 'px';
			};break;
			case 6: {
				prize.style.left = padding + prizeWidth * 4 +'px';
				prize.style.top = padding + prizeWidth * 2 + 'px';
			};break;
			case 7: {
				prize.style.left = padding + prizeWidth * 4 +'px';
				prize.style.top = padding + prizeWidth * 3 + 'px';
			};break;
			default:break;
		}
	};
	var redBagRule = document.getElementById('redbag_rule'),
		layer = document.getElementById('layer'),
		prizeContainer = document.getElementById('prizeContainer');
	document.getElementById('canyu_btn').addEventListener('click',function(){
		if(window.touziURL){//wechat,wap
			window.location.href = window.touziURL;
		} else {
			if(window.platform == '1'){//android
				share.go();
			} else if(platform == '2'){//ios
				goRedBagList();
			}
		}
	});
	document.getElementById('close').addEventListener('click',function(){
		prizeContainer.classList.add('none');
		layer.classList.add('none');
	});
	document.getElementById('rule_btn').addEventListener('click',function(){
		layer.classList.remove('none');
		redBagRule.classList.remove('none');
	});
	document.getElementById('rule_exit').addEventListener('click',function(){
		layer.classList.add('none');
		redBagRule.classList.add('none');
	});
	window.layer = layer;
	window.prizeContainer =prizeContainer;
	window.prizes = prizes
})();