package com.dannyzhou.wechatpay.utils;

import com.dannyzhou.wechatpay.exception.WechatRequestException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danny on 2017/6/9.
 */
public class XMLUtils {

    private XMLUtils() {
    }

    private static XMLUtils xmlUtils = new XMLUtils();

    public static XMLUtils getInstance() {
        return xmlUtils;
    }

    /**
     * 解析字符串（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> parseXml(String msg) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        // 从request中取得输入流
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(
                    msg.getBytes("UTF-8"));

            // 读取输入流
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new WechatRequestException();
            }
        }
        return map;
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }

                @Override
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

    private XStream inclueUnderlineXstream = new XStream(new DomDriver(null,
            new XmlFriendlyNameCoder("_-", "_")));

    public XStream getXstreamInclueUnderline() {
        return inclueUnderlineXstream;
    }

    public XStream xstream() {
        return xstream;
    }

}
