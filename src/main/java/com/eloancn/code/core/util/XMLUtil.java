/**
 * @Title: XMLUtil.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.eloancn.code.core.model.resp.RespArticleModel;
import com.eloancn.code.core.model.resp.RespNewsModel;
import com.eloancn.code.core.model.resp.RespTextModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @ClassName: XMLUtil
 * @Description: xml解析工具.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午3:05:16
 */

public class XMLUtil {
    /** 
     * @Description:解析微信发来的请求（XML）
     * @param inputStream
     * @return
     * @throws Exception
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
        
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    }  
  
    /**
     * @Description:文本消息对象转换成xml 
     * @param textMessage
     * @return
     * @throws
     */
    public static String textMessageToXml(RespTextModel text) {  
        xstream.alias("xml", text.getClass());  
        return xstream.toXML(text);  
    }  
  
    /**
     * @Description:图文消息对象转换成xml 
     * @param newsMessage
     * @return
     * @throws
     */
    public static String newsMessageToXml(RespNewsModel news) {  
        xstream.alias("xml", news.getClass());  
        xstream.alias("item", new RespArticleModel().getClass());  
        return xstream.toXML(news);  
    }  

    /**
     * 扩展xstream，使其支持CDATA块 
     */
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
                @SuppressWarnings({"rawtypes" })
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });
}
