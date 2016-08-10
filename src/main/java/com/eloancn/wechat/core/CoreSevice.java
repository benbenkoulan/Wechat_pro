package com.eloancn.wechat.core;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.common.utils.WechatUtil;
import com.eloancn.wechat.entities.XStreamCDATA;
import com.eloancn.wechat.entities.base.WechatBaseMsg;
import com.eloancn.wechat.entities.request.WechatCustomMsgRequest;
import com.eloancn.wechat.entities.request.WechatEventMsgRequest;
import com.eloancn.wechat.entities.request.WechatMenuMsgRequest;
import com.eloancn.wechat.entities.request.WechatScanMsgRequest;
import com.eloancn.wechat.entities.request.WechatSubscribeMsgRequest;
import com.eloancn.wechat.entities.request.WechatTextMsgRequest;
import com.eloancn.wechat.entities.response.WechatTextMsgResponse;
import com.eloancn.wechat.model.KeyWords;
import com.eloancn.wechat.model.UserMsg;
import com.eloancn.wechat.model.WechatUser;
import com.eloancn.wechat.service.KeyWordsService;
import com.eloancn.wechat.service.UserMsgService;
import com.eloancn.wechat.service.WechatUserService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**   
* @ClassName:CoreSevice.java 
* @Function com.eloancn.wechat.core 
* @author: liben
* @date 2015年9月9日 上午10:02:49 
*/
public class CoreSevice {
	
	public static String ProcessRequest(HttpServletRequest request , WechatUserService wechatUserService , 
			KeyWordsService keyWordsService , UserMsgService userMsgService) throws Exception {
		int msgId = 0;
		String str = Tools.getRequestStr(request);
		WechatBaseMsg msgResponse = null;
		WechatBaseMsg message = ParseXML(str);
		String openId = message.getFromUserName();
		List<KeyWords> kwList = null;
		//事件类型消息
		if(WechatMessageType.REQUEST_EVENT_MESSAGE.equals(message.getMsgType())) {
			WechatEventMsgRequest eventMessage = (WechatEventMsgRequest)message;
			if(WechatMessageType.EVENT_CLICK.equals(eventMessage.getEvent())) {
				WechatMenuMsgRequest menuMessage = (WechatMenuMsgRequest)eventMessage;
				kwList = keyWordsService.getKeyWords(menuMessage.getEventKey());
			} else if(WechatMessageType.EVENT_SUBSCRIBE.equals(eventMessage.getEvent())) {
				//关注
				WechatUser wu = wechatUserService.getWechatUserInfo(openId);
				if(wu == null){
					//初次关注
					wu = WechatUtil.getWechatUser(openId);
					//保存wxuser
					wechatUserService.saveWechatUser(wu);
				} else {
					//非初次关注
					wu = WechatUtil.getWechatUser(openId);
					//更新wxuser
					wechatUserService.updateWechatUser(wu);
				}
				kwList = keyWordsService.getSubscribeKeyWords();
			} else if(WechatMessageType.EVENT_UNSUBSCRIBE.equals(eventMessage.getEvent())) {	
				//取消关注
				WechatUser wu = new WechatUser();
				wu.setUNSUBSCRIBE_TIME(DateUtil.getCurrentTime());
				wu.setSUBSCRIBE(0);
				wu.setOPENID(openId);
				//更新wxuser关注信息
				wechatUserService.setUnSubscribe(wu);
			}
		} else if(WechatMessageType.REQUEST_TEXT_MESSAGE.equals(message.getMsgType())) {	//文本类型消息
			WechatTextMsgRequest textMessage = (WechatTextMsgRequest)message;
			//保存微信用户消息
			UserMsg um = new UserMsg();
			um.setFROMUSERNAME(textMessage.getFromUserName());
			um.setCONTENT(textMessage.getContent());
			um.setMSGTYPE(textMessage.getMsgType());
			um.setCREATETIME(DateUtil.getCurrentTime());
			um.setREPLYCONTENT("");
			//保存微信用户消息
			msgId = userMsgService.saveUserMsg(um);
			kwList = keyWordsService.getKeyWords(textMessage.getContent());
		} else if(WechatMessageType.EVENT_SCAN.equals(message.getMsgType())){
			WechatScanMsgRequest scanMessage = (WechatScanMsgRequest)message;
			String EventKey = scanMessage.getEventKey();
			if(EventKey.contains("qrscene_")){//未关注
				String param = EventKey.split("qrscene_")[1];
				if(Tools.isEmpty(param))param = "";
				WechatUser wu = wechatUserService.getWechatUserInfo(openId);
				if(wu == null){
					//初次关注
					wu = WechatUtil.getWechatUser(openId);
					wu.setTAG(param);
					//保存wxuser
					wechatUserService.saveWechatUserByScan(wu);
				} else {
					//非初次关注
					wu = WechatUtil.getWechatUser(openId);
					wu.setTAG(param);
					//更新wxuser
					wechatUserService.updateWechatUser(wu);
				}
				kwList = keyWordsService.getKeyWords("scan_market");
			}
		}
		msgResponse = checkKeyWords(kwList , message , keyWordsService);
		//没有对应的关键字回复
		if(null == msgResponse){
			msgResponse = new WechatTextMsgResponse(message.getFromUserName(), message.getToUserName(), String.valueOf(new Date().getTime()), WechatMessageType.RESPONSE_TEXT_MESSAGE, "您好，如有问题咨询，可点击右下方\"翼龙互动\"-\"联系客服\"与翼小龙聊天喽！");
		}
		if(WechatMessageType.REQUEST_TEXT_MESSAGE.equals(message.getMsgType())){
			if(WechatMessageType.REPONSE_NEWS_MESSAGE.equals(msgResponse.getMsgType())) {
				
			} else if(WechatMessageType.RESPONSE_TEXT_MESSAGE.equals(msgResponse.getMsgType())) {
				WechatTextMsgResponse textResponse = (WechatTextMsgResponse)msgResponse;
				//更新回复微信用户消息
				UserMsg um = new UserMsg();
				um.setMSGID(msgId);
				um.setREPLYCONTENT(textResponse.getContent());
				um.setREPLYTIME(DateUtil.getCurrentTime());
				userMsgService.updateReplyMsg(um);
			}
		}
		XStream xstream = createXstream();
		xstream.alias("xml", msgResponse.getClass());
		return xstream.toXML(msgResponse);
	}
	
	public static WechatBaseMsg ParseXML(String msg) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(msg.getBytes()));
		Element root = doc.getRootElement();
		Map<String, String> messageMap = new HashMap<String , String>();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		for(Element el : list) {
			messageMap.put(el.getName(), el.getText());
		}
		String ToUserName = messageMap.get("ToUserName");
		String FromUserName = messageMap.get("FromUserName");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String CreateTime = dateFormat.format(new Date(Integer.parseInt(messageMap.get("CreateTime")) * 1000));
		String MsgType = messageMap.get("MsgType");
		//事件消息
		if(WechatMessageType.REQUEST_EVENT_MESSAGE.equals(MsgType))	{
			WechatEventMsgRequest wem = null;
			String Event = messageMap.get("Event");
			if(WechatMessageType.EVENT_SUBSCRIBE.equals(Event)) {//关注
				String Ticket = messageMap.get("Ticket");
				if(Tools.notEmpty(Ticket)){
					String EventKey = messageMap.get("EventKey");//二维码场景ID
					wem = new WechatScanMsgRequest(ToUserName, FromUserName, CreateTime, WechatMessageType.EVENT_SCAN, Event, EventKey,Ticket);
				} else {
					wem = new WechatSubscribeMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, Event);
				}
			} else if(WechatMessageType.EVENT_UNSUBSCRIBE.equals(Event)) {//取消关注
				wem = new WechatSubscribeMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, Event);
			} else if(WechatMessageType.EVENT_CLICK.equals(Event)) {//点击菜单
				String EventKey = messageMap.get("EventKey");	//菜单事件KEY
				wem = new WechatMenuMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, Event, EventKey);
			} else if(WechatMessageType.EVENT_VIEW.equals(Event)) {
				String EventKey = messageMap.get("EventKey");	//菜单URL
				wem = new WechatMenuMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, Event, EventKey);
			} else if(WechatMessageType.EVENT_SCAN.equals(Event)){
				String EventKey = messageMap.get("EventKey");//二维码场景ID
				String Ticket = messageMap.get("Ticket");
				wem = new WechatScanMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, Event, EventKey,Ticket);
			}
			return wem;
		} else {//普通消息
			WechatCustomMsgRequest wbm = null;
			String MsgId = messageMap.get("MsgId");
			if(WechatMessageType.REQUEST_TEXT_MESSAGE.equals(MsgType)) {//文本消息
				String Content = messageMap.get("Content");
				wbm = new WechatTextMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, MsgId, Content);
			} else if(WechatMessageType.REQUEST_IMAGE_MESSAGE.equals(MsgType)) {//图片消息
				wbm = new WechatTextMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, MsgId, null);
			} else {
				wbm = new WechatTextMsgRequest(ToUserName, FromUserName, CreateTime, MsgType, MsgId, null);
			}
			return wbm;
		}
	}
	
	public static XStream createXstream() {
	    return new XStream(new XppDriver() {
	      @Override
	      public HierarchicalStreamWriter createWriter(Writer out) {
	        return (HierarchicalStreamWriter) new PrettyPrintWriter(out) {
	          boolean cdata = false;
	          Class<?> targetClass = null;
	          @Override
	          public void startNode(String name,
	              @SuppressWarnings("rawtypes") Class clazz) {
	            super.startNode(name, clazz);
	            //业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签
	            if(!name.equals("xml")){
	              cdata = needCDATA(targetClass, name);
	            }else{
	              targetClass = clazz;
	            }
	          }
	          @Override
	          protected void writeText(QuickWriter writer, String text) {
	            if (cdata) {
	              writer.write("<![CDATA[" + text + "]]>");
	            } else {
	              writer.write(text);
	            }
	          }
	        };
	      }
	    });
	}
	
	private static boolean needCDATA(Class<?> targetClass, String fieldAlias){
		boolean cdata = false;
		//first, scan self
		cdata = existsCDATA(targetClass, fieldAlias);
		if(cdata) return cdata;
		//if cdata is false, scan supperClass until java.lang.Object
		Class<?> superClass = targetClass.getSuperclass();
		while(!superClass.equals(Object.class)){
		  cdata = existsCDATA(superClass, fieldAlias);
		  if(cdata) return cdata;
		  superClass = superClass.getClass().getSuperclass();
		}
		return false;
	}
	private static boolean existsCDATA(Class<?> clazz, String fieldAlias){
		//scan fields
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
		  //1. exists XStreamCDATA
		  if(field.getAnnotation(XStreamCDATA.class) != null ){
		    XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
			//2. exists XStreamAlias
			if(null != xStreamAlias){
			  if(fieldAlias.equals(xStreamAlias.value()))//matched
			return true;
			}else{// not exists XStreamAlias
		      if(fieldAlias.equals(field.getName()))
		        return true;
		    }
		  }
		}
		return false;
	}
	
	private static WechatBaseMsg checkKeyWords(List<KeyWords> kwList , WechatBaseMsg msg , KeyWordsService keyWordsServices) throws Exception{
		WechatBaseMsg msgResponse = null;
		if(Tools.isNull(kwList)){
			return null;
		} else {
			msgResponse = new WechatTextMsgResponse(msg.getFromUserName(), msg.getToUserName(), String.valueOf(new Date().getTime()), WechatMessageType.RESPONSE_TEXT_MESSAGE, kwList.get(0).getRESPONSE());
		}
		return msgResponse;
	}
}
