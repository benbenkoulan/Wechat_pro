/**
 * Project Name:eloan_operate
 * File Name:Contant.java
 * Package Name:com.back.common.contant
 * Date:2015-9-1下午02:30:43
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/

package com.eloancn.wechat.common.constant;

import java.util.HashMap;

/**
 * ClassName:Contant <br/>
 * Function: 常量数据 <br/>
 * Date:     2015-9-1 下午02:30:43 <br/>
 * @author   liben
 * @version  
 * @see 	 
 */

@SuppressWarnings("serial")
public class Constant {
	
	//是否处于开发
	public static final boolean isDev = true;
	
	//是否在本地
	public static final boolean isLocal =true;
	
	public static final int status = 2;//1 : 113，2 : 81 else 线上
	
	//渠道参数分隔符
	public static final String SEPARATOR = "&";
	
	//生产环境静态服务器地址
	public static final String PRODUCTION_STATICSERVER = "https://mstatic.eloancn.com/wechat";
	
	//微信平台代码
	public static final String PLATFORM = "3";

	//获取静态资源文件存放地址
	public static final String getStaticServer(){
		if(isDev){
			return null;
		} else {
			return PRODUCTION_STATICSERVER;
		}
	}
	
	//连连支付异步通知baseurl
	public static final String BASE_URL = "https://wap.eloancn.com";
	//请求接口地址
	public static final String BASE_REQUESTURL = "http://localhost";
	public static final String PRODUCTION_BASEURL = "https://wap.eloancn.com";
	public static final String SESAME_PRODUCTION_BASEURL = "https://wap.eloancn.com/sesame";
	public static final String WECHAT_RPODUCTION_BASEURL = "https://wap.eloancn.com";
	public static final String APPUSER_PRODUCTION_BASEURL = "https://wap.eloancn.com/appuser";
	
	public static final String getSandBoxBaseUrl(){
		if(1 == Constant.status){//113
			return "http://192.168.3.113";
		} else if(2 == Constant.status){//81
			return "http://192.168.3.81:8082";
		} else {
			return "https://wap.eloancn.com";
		}
	}
	
	public static final String getSesameBaseURL(){
		return isDev ? ("http://172.30.18.249/sesame") : SESAME_PRODUCTION_BASEURL;
	}
	
	public static final String getAppUserBaseURL(){
		return isDev ? (getSandBoxBaseUrl() + "/appuser") : APPUSER_PRODUCTION_BASEURL;
	}
	
	public static final String getWechatBaseURL(){
		return isDev ? getSandBoxBaseUrl() : WECHAT_RPODUCTION_BASEURL;
	}
	
	public static final String getBaseURL(){
		return isDev ? getSandBoxBaseUrl() : PRODUCTION_BASEURL;
	}
	public static final String getBaseRequestUrl(String port){
		if(isLocal){
			return getSandBoxBaseUrl();//http://192.168.3.81:8082";
		} else {
			return new StringBuffer(BASE_REQUESTURL).append(":").append(port).toString();
		}
	}
	//获取后台接口请求地址
	public static final String getRequestUrl(String port) {
		return getBaseRequestUrl(port) + "/mobile";
	}
	
	public static final String getActivityRedisURL(){
		if(isDev){
			return "http://192.168.3.81";
		} else {
			return "http://192.168.8.61";
		}
	}

	/****微信接口地址****/
	public static final String SNSUSERURL = "https://api.weixin.qq.com/sns/userinfo";//二次认证获得微信用户信息
	public static final String WECHATUSERURL = "https://api.weixin.qq.com/cgi-bin/user/info";//获得服务号微信粉丝信息
	public static final String CODEURL = "https://api.weixin.qq.com/sns/oauth2/access_token";//二次认证授权
	public static final String ACCESSTOKENURL = "https://api.weixin.qq.com/cgi-bin/token";//刷新微信access_token
	public static final String TEMPLATEMSGURL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";//模板消息接口
	public static final String JSAPITICKETURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";//获取微信jsapiticket
	/****微信接口地址****/

	public static final int PAGE_SIZE = 10;	
	
	/**微信相关配置***/
	public static final String SANDBOX_OPENWEIXINURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + getAppId() + "&redirect_uri=" + getSandBoxBaseUrl() + "/wechat/html/account/myaccount.html&response_type=code&scope=snsapi_base#wechat_redirect";
	
	public static final String PRODUCTION_OPENWEIXINURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + getAppId() + "&redirect_uri=https://wap.eloancn.com/wechat/html/account/myaccount.html&response_type=code&scope=snsapi_base#wechat_redirect";

	/**accesstoken过期时间**/
	public static final int ACCESSTOKENTIME = 3600;
	/**微信相关配置***/
	
	public static final String getOPENWEIXINURL(){
		if(isDev){
			return SANDBOX_OPENWEIXINURL;
		} else {
			return PRODUCTION_OPENWEIXINURL;
		}
	}
	
	public static final String getOPENWEIXINURL(String page){
		if(isDev){
			return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + getAppId() + "&redirect_uri=" + getSandBoxBaseUrl() + "/wechat/" + page + "&response_type=code&scope=snsapi_base#wechat_redirect";
		} else {
			return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + getAppId() + "&redirect_uri=https://wap.eloancn.com/wechat/" + page + "&response_type=code&scope=snsapi_base#wechat_redirect";
		}
	}
	
	//获取微信公众号开发模式token
	public static final String getWechatToken(){
		if(isDev) {
			return SANDBOX_WECHAT_TOKEN;
		} else {
			return PRODUCTION_WECHAT_TOKEN;
		}
	}
	
	public static final String getAppId()	{
		if(isDev) {
			return SANDBOX_WECHAT_APPID;
		} else {
			return PRODUCTION_WECHAT_APPID;
		}
	}
	
	public static final String getAppSecret()	{
		if(isDev) {
			return SANDBOX_WECHAT_APPSECRET;
		} else {
			return PRODUCTION_WECHAT_APPSECRET;
		}
	}

	//系统缓存过期时间
	public static final int EXPIRETIME = 172800;
	
	public static final String REDISKEY = "redis.auto";
	
	/**接口地址***/
	public static final String LICAIXIEYI_NOUSER = "/template/tzxy.jsp";
	
	public static final String LICAIXIEYI_USER = "/template/tzxy_user.jsp";
	
	public static final String YEAR_SUMMARY = "/yearend/summary.jsp";
	
	public static final String QUERYORDER = "/lianlianpay/queryOrder.action";

	public static final String LOADSTATINFO = "/account/v2/loadStatInfo2_1.action";

	public static final String LOADRADIOBYUID = "/account/loadratioByUid.action";

	public static final String REGISTER = "/v3/register3_1.action";

	public static final String LOGIN = "/v2/login2_1.action";

	public static final String LOGOUT = "/logout.action";

	public static final String ADDWXUSER = "/wx/addwxuser.action";

	public static final String BINDWXUSER = "/wx/bindwxuser.action";
	
	public static final String FINDUSERVERIFYINFO = "/recharge/findUserVerifyInfo.action";

	public static final String CHECKMOBILE = "/checkMobile.action";

	public static final String CATCHREGCODE = "/v3/catchRegCode3_1.action";

	public static final String LOADWMPSLIST = "/wmps/v2/loadMywmpsLists2_1.action";
	
	public static final String YIJIEQINGWMPSSTATISTICS = "/wmps/yiJieQingWmpsStatistics.action";

	public static final String CANYUWMPSLIST = "/wmps/v2/canYuWmpsRecordList2_1.action";

	public static final String WXREDBAG = "/activity/listActivity.action";

	public static final String GETUSERDEDUCTION = "/activity/getUserDeduction5_1.action";

	public static final String FINDALLLENDINGMONEY = "/account/v2/findAllLendingMoney2_1.action";

	public static final String FINDWAITCOLLECTION = "/account/v2/findWaitCollectPageByHql2_1.action";
	
	public static final String LISTEXPRIENCES = "/activity/listExperiences.action";

	public static final String LOADFUNDSINFO = "/account/loadFundsInfo.action";

	public static final String EDITPASSWORD = "/account/editPassword.action";

	public static final String LOADMEMBERBYM = "/v2/loadMemberByM2_1.action";

	public static final String UPDATEMEMBERPWD = "/v2/updateMemberPwd2_1.action";

	public static final String NETWORKAUTHRECORD = "/recharge/networkAuthRecord.action";

	public static final String INITBANNER = "/initBanner.action";
	
	public static final String INIT = "/init.action";

	public static final String LOADALLRECORD = "/v2/loadAllRecords2_1.action";

	public static final String IDCARDAUTH = "/valid/idcardAuth3.action";

	public static final String SETPAYPASSWORD = "/valid/setPayPassword.action";

	public static final String EDITPAYPASSWORD = "/account/v2/editPayPassword2_1.action";

	public static final String LOADWMPSRECORDDETAIL = "/loadWmpsRecordDetailsById.action";

	public static final String PROCEEDSCULATOR = "/proceedsCulator.action";

	public static final String GAINCODEMOBILEAUTH = "/valid/v2/gainCodeMobileAuth2_1.action";

	public static final String SAVEMOBILEAUTH = "/valid/v2/saveMobileAuth2_1.action";

	public static final String QUERYBANKCARDLIST = "/lianlianpay/queryBankcardList.action";

	public static final String ADDWMPSBUYRECORD = "/wmps/v2/addWmpsBuyRecord2_3.action";
	
	public static final String ADDWMPSEXPRIENCEBUYRECORD = "/wmps/addExperienceWmpsBuyRecord.action";

	public static final String INITWITHDRAWPAGE = "/account/initWithDrawPage.action";

	public static final String PROVINCE = "/province.action";

	public static final String CITY = "/city.action";

	public static final String BANKDETAIL = "/account/bankDetail.action";

	public static final String CREATEPACCOUNT = "/recharge/createPAccount.action";

	public static final String ASKFORWITHDRAW = "/recharge/askForWithDraw.action";
	
	public static final String FINDPAYPWD = "/valid/v2/findPayPwd2_1.action";
	
	public static final String ADDZCBUYRECORD = "/wxvalid/addZCBuyRecord.action";
	
	public static final String BANKCARDUNBIND = "/lianlianpay/bankCardUnbind.action";
	
	public static final String TOKEN = "/account/token.action";
	
	public static final String LOADTENDERDETAIL = "/wmps/loadTenderDetail.action";
	
	public static final String LOADTENDERLIST = "/loadTenderList.action";
	
	public static final String DOWNLOADELECTRONLOAN = "/wmps/downLoadElectronLoan.action";
	
	public static final String YEEPAY = "/yeepay/yeePayAssembleParameter.action";
	
	public static final String QUERYMOBILELDYS = "/ldys/queryMobileLdys.action";
	
	public static final String UMPAYBANKCARDUNBIND = "/ldys/umpayBankCardUnbind.action";
	
	public static final String UMPAYGETORDERID = "/ldys/umpayGetOrderId.action";
	
	public static final String UMPAYORDERTOPAY = "/ldys/umpayOrderToPay.action";
	
	public static final String MATCHACTUAL = "/wmps/v3/match_Actual.action";
	
	public static final String MATCHCHANGEACTUAL = "/wmps/v3/matchChange_Actual.action";
	
	public static final String MATCHACTUALBACK = "/wmps/v3/match_ActualBack.action";
	
	public static final String MATCHACTUALBUY = "/wmps/v3/match_ActualBuy.action";
	
	public static final String MATCHWECHATACTUAL = "/wmps/v3/matchWechat_Actual.action";
	
	/***五一翼龙节***/
	public static final String LISTACTIVITYREDBAG51 = "/listActivityRedbag51.action";
	public static final String ACTIVITYREDISKEY = "/redisKey?key=";
	public static final String UPDATEREDISVALUE = "/updateRedisValue.action?redisKey=";
	/***五一翼龙节***/
	
	/**芝麻开花**/
	public static final String SESAMEDETAIL = "/v1/app001/b.do";
	public static final String SESAMELIST = "/v1/app001/a.do";
	public static final String SESAMEINVESTORS = "/v1/app003/a.do";
	public static final String SESAMETENDERLIST = "/v1/app004/a.do";
	public static final String SESAMEMYTENDERLIST = "/v1/app004/b.do";
	public static final String SESAMEINVEST = "/v1/app003/c.do";
	public static final String MYSESAMELIST = "/v1/app002/a.do";
	public static final String MYSESAMEDETAIL = "/v1/app002/c.do";
	public static final String SESAMEINVESTINIT = "/v1/app003/b.do";
	public static final String GETLOCKEDTENDERLIST = "/v1/app008/a.do";
	public static final String UNLOCKEDTENDERLIST = "/v1/app008/b.do";
	public static final String INVESTLOCKEDTENDERLIST = "/v1/app008/c.do";
	public static final String GETCURLOCKEDTENDERLIST = "/v1/app008/d.do";
	
	public static final String GETTRANSFERLIST = "/v1/app007/a.do";
	public static final String TRANSFERTENDERLIST = "/v1/app004/d.do";
	public static final String TRANSFERDETAIL = "/v1/app007/b.do";
	public static final String INVESTTRANSFERINIT = "/v1/app006/a.do";
	public static final String INVESTTRANSFER = "/v1/app006/b.do";
	public static final String CANCELTRANSFER = "/v1/app006/d.do";
	public static final String TRANSFERSESAME = "/v1/app006/c.do";
	public static final String TRANSFERINIT = "/v1/app006/e.do";
	/**芝麻开花**/
	
	/**appuser**/
	public static final String APPUSER_LOGIN = "/app001/v1/01";
	/**appuser**/
	
	/**接口地址***/
	
	public static final String AMTTYPE = "RMB";
	
	public static final String JSONDATATIP = "tip";
	 
	public static final String REQUESTSUCCESS = "success";
	
	/**前台提示信息**/
	public static final String LOGGOUTTIP = "已经退出登录";
	
	public static final String LOGGOUTERROR = "退出登录异常，请稍后再试";
	
	public static final String LOGINTIP = "登录成功";
	
	public static final String LOGINERROR = "登录失败";
	
	public static final String AUTHTIP = "认证成功";
	
	public static final String AUTHERROR = "认证异常，请稍后再试";
	
	public static final String FINDPWDTIP = "密码找回成功";
	
	public static final String FINDPWDERROR = "密码找回失败";
	
	public static final String MODIFYPAYPWDTIP = "支付密码修改成功";
	
	public static final String MODIFYPAYPWDERROR = "支付密码修改失败";
	
	public static final String MODIFYPWDTIP = "密码修改成功";
	
	public static final String MODIFYPWDERROR = "密码修改失败";
	
	public static final String SETPAYPWDTIP = "支付密码设置成功";
	
	public static final String SETPAYPWDERROR = "支付密码设置失败";
	
	public static final String DATAERROR = "数据异常，请稍后再试";
	
	public static final String TIXIANTIP = "提现申请已提交";
	
	public static final String TIXIANERROR = "提现申请提交异常，请稍后再试";
	
	public static final String INVESTTIP = "您已成功投资该产品";
	
	public static final String INVESTERROR = "投资该产品失败，请稍后再试";
	
	public static final String SENDCODEERROR = "发送验证码失败";
	
	public static final String CZERROR = "充值失败，请联系客服\n400-080-5055";

	public static final String ADDCARDTIP = "添加银行卡成功";
	
	public static final String ADDCARDERROR = "添加银行卡失败";
	
	public static final String UNBINDCARDTIP = "解除绑定成功";
	
	public static final String UNBINDCARDERROR = "解除绑定失败，请联系客服\n400-080-5055";
	
	public static final String VALIDATEPHONEERROR = "手机号码不正确";
	
	public static final String VALIDATECODEERROR = "验证码不能为空";
	
	public static final String VALIDATECODEERROR2 = "验证码不正确";
	
	public static final String IMAGECODEERROR = "图形码有误，请重新获取";
	
	public static final String VALIDATEPWDERROR1 = "新密码不能为空";
	
	public static final String VALIDATEPWDERROR2 = "两次密码不一致";
	
	public static final String VALIDATEPWDERROR3 = "旧密码不能为空";
	
	public static final String VALIDATELOGINPWD = "请输入登录密码";
	
	public static final String VALIDATEPAYPWD = "请设置支付密码";
	
	public static final String VALIDATENAMEERROR = "请填写真实姓名";
	
	public static final String VALIDATEIDCARDERROR = "请填写正确的身份证号";
	
	public static final String CODESENDSUCCESS = "验证码已发送，请注意查收。";
	
	public static final String VALIDATEPAYPWDERROR = "请设置支付密码";
	
	public static final String VALIDATEPAYPWDERROR1 = "请输入原支付密码";
	
	public static final String NOTSUCSCRIBEELOANCN = "请先关注翼龙贷网！";
	
	public static final String NOTAUTH = "您还未认证身份证信息";
	
	public static final String AUTHTIMEOUT = "您已经没有认证机会请联系客服人员解决\n400-080-5055";
	
	public static final String NOTSETPAYPASSWORD = "您还未设置支付密码";
	
	public static final String VALIDATEAMOUNTERROR = "请输入正确的金额";
	
	public static final String INVALIDOPERATION = "非法操作";
	
	public static final String VALIDATEADDCARDERROR = "请输入正确的银行卡号";
	
	public static final String LOGGEDIN = "请先登录";

	public static final String NOTLOGGEDIN = "NOTLOGGEDIN";
	
	public static final String NOT = "未";
	
	public static final String TIYANQITIP = "体验金已使用或者已过期";
	
	public static final String ORDERERROR = "生成订单失败";
	
	public static final String MATCHTENDERERROR = "亲，小翼现在有点忙，请稍候再试";
	/**前台提示信息**/
	
	public static final String PARAMTIP = "参数不合法";

	public static final String CANCELTRANSFERTIP = "取消转让成功";
	public static final String TRANSFERTIP = "转让成功";
	public static final String INVESTTRANSFERTIP = "购买成功";
	public static final String INVESTTRANSFERERROR = "亲，购买该转让标失败，请稍候再试";
	public static final String TRANSFERERROR = "亲，转让该标失败，请稍候再试";
	public static final String CANCELTRANSFERERROR = "亲，取消转让失败，请稍候再试";
	public static final String GETRANSFERLISTERROR = "亲，没有可用的转让标";
	public static final String GETTRANSFERDETAILERROR = "亲，查询不到该转让标信息哦";
	
	/**微信错误集合集合**/
	public static final HashMap<String , String> WECHATERRORS = new HashMap<String , String>(){
		{
			put("0" , "出错啦");
			put("1" , "系统错误");
		}
	};
	
	/**联动优势银行代码**/
	public static final HashMap<String, Integer> LDYSBANKS = new HashMap<String,Integer>(){
		{
			put("CCB" , 4);//建设银行
			put("ABC" , 5);//农业银行
			put("BOC" , 2);//中国银行
			put("CITIC" , 14);//中信银行
			put("CEB" , 12);//光大银行
			put("CIB" , 9);//兴业银行
			put("SPDB" , 7);//浦发银行
			put("ICBC" , 3);//工商银行
			put("PSBC" , 16);//邮储银行
			put("COMM" , 11);//交通银行
			put("CMBC" , 8);//民生银行
			put("GDB" , 10);//广发银行
			put("SPAB" , 28);//平安银行
			put("CMB" , 1);//招商银行
		}
	};
	
	/***llpay中的配置信息***/
	public static final String LLPAYVERSION = "1.2";
	public static final String BUSIPARTNER = "101001";
	public static final String OIDPARTNER = "201411281000116503";
	public static final String SIGNTYPE = "MD5";
	public static final String MD5KEY = "www.eloancn.com";
	public static final String LLPAYURL = "https://yintong.com.cn/llpayh5/authpay.htm";
	public static final String TRADERPRIKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM5sb7D1yhF+5C+/3KA1DzVflD2VQWm8zrC35/X+aWJRDazQF3DcBIviL9cFs7plFDqe0QEX7b5pPuNS8//li0TV/cLa+jK0sCodyX/VJECmkesK+7mxQKBsj0lm0bQIh/oyHy/ZdvHebJ9G79nOuTaBaFRqQUQtHFzFsXz+DaVxAgMBAAECgYEAo8rDLWU6e3t2ULUquSyg7rbyh5MBHoekcfDbbAP9Vvf9o9xSXUkXim9linaJ0CMYVBNkwDteeDJGplZSKgDX6dAWs+TPna9+ozCGstQnKYtmoJFwFP/lDoKuueq3gxBh8BnN+79plwr2xGJTWyDE1V9T/AyeiYJkEvhg0OFjtkkCQQDoDJYLO0ptrmoNc1ZsbfUMb+0FhynqSlTqwwmM6ddu3OTrkILE7YGEKieYdQRdpOXm6C6Fvcd/UFXsvkvOHhtPAkEA47q/aFuPntvt4+IpeUhW1VWnOHuW4Ej/WeqXVWn3AdTn1Csvzji7FtEn20J6rshz1LZDcllWLWZ4gT6u770DPwJAN43b1jCJrxyUk0fLpwXkJugc7LfhVxAYqGOtQ6kwRIBp5eOhCxh5a3TUKfHJ68lwqQlpKee3fUl0aMteRqh6jQJAdoMnPW347hlSUffO0Zk950PuBEz9Fbene+nD2+WLcF6WTyoydMW9R4CNl2OfaPpUnWxE6Wtol7PuHoS6kXTyDQJBANiaYk/yH3vcqyMeBZe/hu/nM6WwbFAih6R1KQWPsvBkCSoZisV2WXPk93PCo7hvs0U/cZ4g/o/Xukw6BMes4Mw=";
	public static final String APPREQUEST = "3";
	/***llpay中的配置信息***/
	
	//连连支付接收异步通知地址
	public static String NOTIFY_URL = "/mobile/notify2.action";
	public static String URL_RETURN = "/wechat/index/index.html";
	
	//连连支付回调地址
	public static String getURLReturn(){
		return URL_RETURN;
	}
	
	/**存入redis中key的组成部分**/
	public static final String ISVIP = "ISVIP";
	public static final String CZHI = "CZHI";
	public static final String ADDCARD = "ADDCARD";
	public static final String ZCGOOD = "ZCGOOD";
	public static final String REFERER = "REFERER";
	public static final String CHANNEL = "CHANNEL";
	public static final String EXPRIENCED = "EXPRIENCED";
	public static final String JSAPITICKET = "JSAPITICKET";
	/**新年活动缓存**/
	public static final String XINNIAN = "XINNIAN";
	
	/**redis key集合**/
	public static final HashMap<String , String> rediskeys = new HashMap<String , String>(){
		{
			put("" , "");
			put("ISVIP" , "ISVIP");
			put("CZHI" , "CZHI");
			put("ADDCARD" , "ADDCARD");
			put("ZCGOOD" , "ZCGOOD");
			put("REFERER" , "REFERER");
			put("CHANNEL" , "CHANNEL");
			put("EXPRIENCED" , "EXPRIENCED");
		}
	};
	
	/**进入登录页面过滤掉的参数**/
	public static final HashMap<String, String> skipParams = new HashMap<String , String>(){
		{
			put("jl_sign" , "jl_sign");
			put("ch_sign" , "ch_sign");
			put("act_sign" , "act_sign");
			put("code" , "code");
			put("openId" , "openId");
			put("state" , "state");
			put("viewName" , "viewName");
		}
	};
	
	//连连卡类型代码
	public static final HashMap<String , String> cardTypes = new HashMap<String , String> (){
		{
			put("01030000" , "5");
			put("01020000" , "3");
			put("03080000" , "1");
			put("01040000" , "2");
			put("01050000" , "4");
			put("03030000" , "12");
			put("03070000" , "17");
			put("03040000" , "11");
			put("03100000" , "7");
			put("03020000" , "14");
			put("03090000" , "9");
			put("01000000" , "16");
			put("03060000" , "10");
			put("03050000" , "8");
		}
	};
	
	//翼龙贷vip类型
	public static final HashMap<String , String> vipTypes = new HashMap<String , String> (){
		{
			put("2" , "2");
			put("3" , "3");
			put("4" , "4");
			put("5" , "5");
			put("6" , "6");
			put("8" , "8");
		}
	};
}

