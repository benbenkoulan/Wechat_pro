function parseISO8601(dateStringInRange) {
	var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,
	date = new Date(NaN), month,
	parts = isoExp.exec(dateStringInRange);
	if(parts) {
		month = +parts[2];
		date.setFullYear(parts[1], month - 1, parts[3]);
		if(month != date.getMonth() + 1) {
			date.setTime(NaN);
		}
	}
	return date;
}

/**
 * jquery.validate相关扩展验证
 * @author Administrator
 */
//验证用户名
jQuery.validator.addMethod("userNameCheck", function(value, element) {
	//return this.optional(element) || /^[a-zA-Z]\w{4,20}$/.test(value);
	return this.optional(element) ||/^[a-zA-Z\u4e00-\u9fa5][\w\u4e00-\u9fa5\d]{2,29}$/.test(value);
}, "请输入3-30位以字母或中文开头的字符，不能含有特殊字符!");
jQuery.validator.addMethod("legealName",function(value,element){
	return this.optional(element) ||/^[a-zA-Z\u4e00-\u9fa5][\w\u4e00-\u9fa5\d]{1,29}$/.test(value);
},"请输入2-30位以字母或中文开头的字符，不能含有特殊字符!")
//字符验证
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "只能包括中文字、英文字母、数字和下划线");
//字符验证
jQuery.validator.addMethod("validateCodeCheck", function(value, element) {
	return this.optional(element) || /^[\w\d]{4}$/.test(value);
}, "只能输入4位英文字母或数字");
//邮政编码验证
jQuery.validator.addMethod("isEmail", function(value, element) {
	var isEmailRegex = /^((([a-z]|[A-Z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|[A-Z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[A-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|[A-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[A-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/.test(value);
	return this.optional(element) || isEmailRegex;
}, "请正确填写邮箱格式");
//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /(^(13|14|15|18)\d{9}$)/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");
//电话号码验证
jQuery.validator.addMethod("isTel", function(value, element) {
	var tel = /^\d{3,4}-\d{7,8}$/; //电话号码格式010-12345678
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码");
//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value,element) {
	var length = value.length;
	var mobile = /(^(13|14|15|18)\d{9}$)|(^\d{3,4}-\d{7,8}$)/;
	//var tel = /^\d{3,4}-?\d{7,9}$/;
	return this.optional(element)|| mobile.test(value);
}, "请正确填写您的联系电话");
//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isQQ", function(value,element) {
	return this.optional(element) || /^[0-9][0-9]{4,10}$/.test(value);
}, "请正确输入QQ号码(5到11位纯数字)!");
//身份证验证
jQuery.validator.addMethod("isCard", function(value,element) {
	if(value==null || $.trim(value)=='')
		return true;
	var currDate = new Date();
	var currYear = currDate.getFullYear()-1;
	//console.log("currYear="+currYear)
	var currYearFourth = currYear%10;
	//console.log("currYearFourth="+currYearFourth);
	var currYearThird = parseInt((currYear%100)/10);
	//console.log("currYearThird="+currYearThird);
	var currYearSecond = parseInt((currYear%1000)/100);
	//console.log("currYearSecond="+currYearSecond);
	var currYearFrist = parseInt((currYear%10000)/1000);
	//console.log("currYearFrist="+currYearFrist);
	
	var lastCentury = currDate.getFullYear()-100;
	var lastCenturyFourth = lastCentury%10;
	var lastCenturyThird = parseInt((lastCentury%100)/10);
	var lastCenturySecond = parseInt((lastCentury%1000)/100);
	var lastCenturyFrist = parseInt((lastCentury%10000)/1000);
	
	var regexStr1 = "("+lastCenturyFrist+"["+lastCenturySecond+"-9][0-9][0-9])";
	//var regexObj = new RegExp("("+lastCenturyFrist+"["+lastCenturySecond+"-9]["+lastCenturyThird+"-9]["+lastCenturyFourth+"-9])");
	//console.log(regexObj);
	//console.log(regexObj.test("0989"));
	//var regexObj = new RegExp("^[1-9]\\d{5}[]((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|\\X|\\x])$");
	//console.log(regexObj);
	var regexStr2 = "";
	if(currYearSecond == 0){
		if(currYearThird == 0){
			regexStr2 = "("+currYearFrist+"00[0-"+currYearFourth+"])";
		}else{
			regexStr2 = "("+currYearFrist+"0(([0-"+(currYearThird-1)+"][0-9])|("+currYearThird+"[0-"+currYearFourth+"])))";
		}
	}else{
		regexStr2 = "("+currYearFrist+"([0-"+(currYearSecond-1)+"][0-9][0-9])|("+currYearSecond+"[0-"+currYearThird+"][0-"+currYearFourth+"]))";
	}
	//var regexObj2 = new RegExp("("+currYearFrist+"[0-"+currYearSecond+"][0-"+currYearThird+"][0-"+currYearFourth+"])");
	//console.log(regexObj2);
	//console.log(regexObj2.test("2112"));
	
	var regexAll = "("+regexStr1+"|"+regexStr2+")";
	var regexObj = new RegExp("^[1-9]\\d{5}"+regexAll+"((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|\\X|\\x])$");
	var cardResult = regexObj.test(value);
	//时间对不对
	try{
		if(cardResult){
			var dateStr = value.substr(6,4)+"-"+value.substr(10,2)+"-"+value.substr(12,2);
			var brithDate;
			var browser=$.browser.msie;
			var b_version=$.browser.version;
			var versionInt = parseInt(b_version);
			if(browser && versionInt <= 8)
			{
				brithDate = parseISO8601(dateStr);
			}else{
				brithDate = new Date(dateStr);
			}
			var brithDateStr = brithDate.getFullYear()+"-"+((brithDate.getMonth()+1)>=10?(brithDate.getMonth()+1):"0"+(brithDate.getMonth()+1))+"-"+(brithDate.getDate()>=10?brithDate.getDate():"0"+brithDate.getDate());
			if(dateStr != brithDateStr){
				cardResult = false;
			}
		}
//		console.log(brithDate);
	}catch(e){
		cardResult = false;
//		console.log(e);
	}
	return this.optional(element) || cardResult;
}, "请正确输入证件号码!");

//金额为非负数
jQuery.validator.addMethod("isPositiveMoeny",function(value,element){
	var hasPoint = /^[1-9]\d{0,6}\.{1}\d{1,2}$/;
	var noPonint = /^[1-9]\d{0,6}$/;
	var result = false;
	if(value.indexOf(".") ==-1)
		result = noPonint.test(value);
	else 
		result = hasPoint.test(value);
	return this.optional(element) || result;
},"请输入正确的交易金额,最大金额级别百万,小数位最多为2位!");

//验证上传图片
jQuery.validator.addMethod("isImg",function(value,element){
	var img = /.(gif|jpg|jpeg|png)$/;
	return this.optional(element) || img.test(value);
},"上传图片格式不正确!");


////验证非法输入字符
//jQuery.validator.addMethod("isLegalText",function(value,element){
//	var reg = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/;
//	return this.optional(element) || !reg.test(value);
//},"输入包含非法字符!")

//验证非法输入字符
jQuery.validator.addMethod("onlyChinese",function(value,element){
	var reg = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || reg.test(value);
},"只能输入中文!");

//验证非法输入字符
jQuery.validator.addMethod("onlyChineseKH",function(value,element){
	var reg = /^[\u4e00-\u9fa5\uff08\uff09\u0028\u0029]+$/;
	return this.optional(element) || reg.test(value);
},"只能输入中文!");

//验证非法输入字符
jQuery.validator.addMethod("onlyEnglishNumber",function(value,element){
	var reg = /^[\w\d]+$/;
	return this.optional(element) || reg.test(value);
},"只能输入英文或数字!");

//验证时间
jQuery.validator.addMethod("checkMinute",function(value,element){
	var reg = /^([0-1][0-9]|[2][0-3]):([0-5][0-9])$/;
	return this.optional(element) || reg.test(value);
},"请输入正确的时间格式00:00——24:00!");

//验证整数和小数
jQuery.validator.addMethod("checkIntAndNum",function(value,element){
	var reg = /^([1-9]\d*)?[0-9](\.[0-9]{1,2})?$/;
	return this.optional(element) || reg.test(value);
},"无效的整数或者小数,小数点后最多保留两位");
//验证整数
jQuery.validator.addMethod("checkInt",function(value,element){
	var reg = /^[\-\+]?\d+$/;
	return this.optional(element) || reg.test(value);
},"无效的整数");

/**
 * 校验文本框和复选框组合
 * @param {Object} value 
 * @param {Object} element
 * @param {Object} params 例子 iptAndChkGroup:{"inputNames":"文本框的名字","chkNames":"复选框的名字"} 
 * 									<br />或者  params:{"inputNames":["文本框的名字1","文本框的名字2"],"chkNames":["复选框的名字1","复选框的名字2"]}
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
jQuery.validator.addMethod("iptAndChkGroup", function(value ,element ,params) {
	var result = false;
	if($(element).is(":text")){
		var item1_value = $.trim(value);
		if(item1_value && item1_value!="" && item1_value!="undefined"){
			result = true;
		}
	}
//	else if($(element).is(":checkbox")){
//	console.log(element);
//	}
	if(!result){
		//校验文本框
		var inputNames = params.inputNames;
		if(inputNames){
			if(inputNames instanceof Array){
				for(var i=0;i<inputNames.length;i++){
					var curIptVal = $.trim($("[name='"+inputNames[i]+"']").val());
					if(curIptVal && curIptVal!="" && curIptVal!="undefined"){
						result=true;
						break;
					}
				} 
			}else{
				var curIptVal = $.trim($("[name='"+inputNames+"']").val());
				if(curIptVal && curIptVal!="" && curIptVal!="undefined"){
						result=true;
				}
			}
		}
		if(!result){
			var chkNames = params.chkNames;
			if(chkNames){
				if(chkNames instanceof Array){
					for(var i=0;i<chkNames.length;i++){
						var curChks = $("[name='"+chkNames[i]+"']");
						if(curChks){
							for(var j=0;j<curChks.length;j++){
								if($(curChks[j]).attr("checked")){
									result=true;
									break;
								}
							}
						}
						if(result)break;
					}
				}else{
					var curChks = $("[name='"+chkNames+"']");
					if(curChks){
						for(var j=0;j<curChks.length;j++){
							if($(curChks[j]).attr("checked")){
								result=true;
								break;
							}
						}
					}
				}
			}
		}
	}
	return  result;
}, "请输入或者选择其中一项!");
//两个至少填写一项
jQuery.validator.addMethod("require_from_group", function(value, element, options){
    var numberRequired = options[0],
    selector = options[1],
    $fields = $(selector, element.form),
    validOrNot = $fields.filter(function() {
        return $(this).val();
    }).length >= numberRequired,
    validator = this;
    if(!$(element).data('being_validated')) {
        $fields.data('being_validated', true).each(function(){
            validator.valid(this);
        }).data('being_validated', false);
    }
    return validOrNot;
}, jQuery.format("手机和固定电话至少填写一项！"));


/**
 * 管理平台校验规则
 */
//手机号码
var mobile = /^1[2-9]{1}[0-9]{9}$/;
//固定电话（区号+号码）
var tel = /^\d{3,4}-?\d{7,8}$/;
//特殊字符和空格校验
var toFilter=new RegExp("\script");
//其他特殊字符过滤
var otherFilter = new RegExp("[<|>|%|*|$|@|#|^]");
//var otherFilter = new RegExp("[<|>|%|*|$|@|#|^]");
//中文
var isChs = /[\u4e00-\u9fa5]/;
//字母和数字
var charAndNum = /^[A-Za-z0-9]+$/;
//email
//var emailReg=/^([a-zA-Z0-9]+[_|-|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|-|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,3}$/gi;
//邮箱
var emailReg = /^.+@.+\..+$/;
//邮编
var pattern =/^[0-9]{6}$/;
//QQ
var QQ =/^[1-9]{1}[0-9]{4,10}$/;//{4,10}表示4位到10位 
//运单号
var billNoReg = /^[0-9]{12}$/;
//交易流水号
var deliverTransNO = /^[J][0-9]{14}$/;
//调度单号
var deliverScheduleNo = /^[D][0-9]{13}$/;
//订单号
var orderNoReg = /^[A-Z]{1}[0-9]{14}$/;

jQuery.validator.addMethod("require_price_group", function(value, element, options){
    if(0.0 == value){
      $(options[1]).val("");   
    }
    var numberRequired = options[0],
    selector = options[1],
    $fields = $(selector, element.form),
    validOrNot = $fields.filter(function() {
        return $(this).val();
    }).length >= numberRequired,
    validator = this;
    if(!$(element).data('being_validated')) {
        $fields.data('being_validated', true).each(function(){
            validator.valid(this);
        }).data('being_validated', false);
    }
    return validOrNot;
}, jQuery.format("请至少添加一种有效价格"));

//手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) { 
	var length = value.length; 
	return this.optional(element) || (mobile.test(value)); 
}, "请输入正确的手机号码！");

//固定电话验证
jQuery.validator.addMethod("tel", function(value, element) { 
	return this.optional(element) || (tel.test(value)); 
}, "请输入正确的固定电话号码！");

jQuery.validator.addMethod("english", function(value, element) {  
	var english = /^[a-zA-Z\s]*$/;  
	return this.optional(element) || (english.test(value));  
}, "请输入英文！");  


//安全过滤
jQuery.validator.addMethod("toFilter", function(value, element) { 
	return this.optional(element) || (!value.match(toFilter)); 
}, "请不要输入特殊字符！");

//特殊字符过滤
jQuery.validator.addMethod("otherFilter", function(value, element) { 
	return this.optional(element) || (!value.match(otherFilter)); 
}, "请不要输入特殊字符！");

//中文
jQuery.validator.addMethod("isChs", function(value, element) { 
	return this.optional(element) || (isChs.test(value)); 
}, "请输入中文！");

//非中文
jQuery.validator.addMethod("notChs", function(value, element) { 
	return this.optional(element) || (!isChs.test(value)); 
}, "请不要输入中文！");

//字母和数字
jQuery.validator.addMethod("charAndNum", function(value, element) { 
	return this.optional(element) || (charAndNum.test(value)); 
}, "请输入字母或数字或两者的组合！");
//email校验
jQuery.validator.addMethod("emailReg", function(value, element) { 
	return this.optional(element) || (emailReg.test(value)); 
}, "请输入有效的email邮箱");
//邮编校验
jQuery.validator.addMethod("pattern", function(value, element) { 
	return this.optional(element) || (pattern.test(value)); 
}, "请输入有效的邮政编号");
//QQ校验
jQuery.validator.addMethod("QQ", function(value, element) { 
	return this.optional(element) || (QQ.test(value)); 
}, "请输入有效的QQ号码");

//字母和数字
jQuery.validator.addMethod("orderNoReg", function(value, element) { 
	return this.optional(element) || (orderNoReg.test($.trim(value))); 
}, "请输入正确的订单号！");

//运单跟踪时的运单号校验
jQuery.validator.addMethod("traBlNo", function(value, element) { 
	var blNos = value.replace(/，/g,',').replace(/\r\n/g,',').replace(/\n/g,",").replace(/,,/g,",");
	var data = blNos.split(",");
	
	var blNo = true;
	if(data.length > 0){
		for(var i=0; i<data.length; i++){
			if(data[i].length != 12){
				blNo = false;
			}
		}
	}
	return (this.optional(element) || data.length <= 5) && blNo; 
}, "请输入正确的运单号，多个运单号用‘，’隔开,最多可输入5个。");

//单个运单号的校验
jQuery.validator.addMethod("billNoReg", function(value, element) { 
	return this.optional(element) || (billNoReg.test($.trim(value))); 
}, "请输入正确的运单号");

//单个记录的校验
jQuery.validator.addMethod("deliverTransNo", function(value, element) { 
	return this.optional(element) || (deliverTransNO.test($.trim(value))); 
}, "请输入正确的交易流水号");

jQuery.validator.addMethod("deliverNo", function(value, element) { 
	return this.optional(element) || (deliverScheduleNo.test($.trim(value))); 
}, "请输入正确的调度单号");

//联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("mobileOrTel", function(value,element) {
	var length = value.length;
	var mobileOrTel = /(^1[2-9]{1}[0-9]{9}$)|(^0[\d]{2,3}-?\d{7,8}$)/;
	return this.optional(element)|| mobileOrTel.test(value);
}, "请正确填写您的联系电话");

//车牌号格式
jQuery.validator.addMethod("vehicleNum", function(value, element) {
        var vehicleNum = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/
        return this.optional(element) || (vehicleNum.test(value));
    }, "输入的车牌号格式不正确");
    
 var id = $("#id").val();
 if(null != id && "" != id){
     $("#vehicleCodeClass").addClass('textfield2');
     $("#code").attr("readonly","readonly");
 } 
  //script安全过滤
//特殊字符和空格校验
var toFilter=new RegExp("\script|#");
jQuery.validator.addMethod("isLegalText", function(value, element) { 
	return this.optional(element) || (!value.match(toFilter)); 
}, "请不要输入特殊字符！");
//弹出框上验证密码是否一致
jQuery.validator.addMethod("dialogSamePwd",function(value,element,params){
	var result = false;
	if(value==$.parentDom(params).val()){
		result=true;
	}
	return this.optional(element) || result;
},"两次输入的密码不一致!!");

//最小值但不等于
jQuery.validator.addMethod("minNotEq", function(value, element, param) { 
	return this.optional(element) || (Number(value) > Number(param));
}, "数值太小！");

//最大值但不等于
jQuery.validator.addMethod("maxNotEq", function(value, element, param) { 
	return this.optional(element) || (Number(value) <= Number(param));
}, "数值太大！");
//校验与参考相除必须是整数倍
jQuery.validator.addMethod("checkIntMultiple",function(value,element, param){
	var reg = /^[\-\+]?\d+$/;
	return this.optional(element) || reg.test(Number(value)/Number(param));
},"不是整数倍");
