package com.dannyzhou.wechatpay.service;

import com.dannyzhou.wechatpay.WechatConfig;
import com.dannyzhou.wechatpay.dto.Transfer;
import com.dannyzhou.wechatpay.enums.ResultType;
import com.dannyzhou.wechatpay.exception.WechatRequestException;
import com.dannyzhou.wechatpay.utils.XMLUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by danny on 2017/6/9.
 */
public class EnterprisePay {

    private EnterprisePay() {
    }

    private static EnterprisePay enterprisePay = new EnterprisePay();

    public static EnterprisePay getInstance() {
        return enterprisePay;
    }

    /**
     *
     * @param openId
     * @param money
     * @param internalOrderNumber
     * @param description
     * @return map type, request and response
     */
    public Map<ResultType, String> withdrawMoney(String openId, Integer money, String internalOrderNumber, String description) {
        XMLUtils xmlUtils = XMLUtils.getInstance();

        Transfer transfers = new Transfer();
        String nonce = UUID.randomUUID().toString().substring(0, 30);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        transfers.setMch_appid(WechatConfig.APP_ID);// 自己的公众账号
        transfers.setMchid(WechatConfig.MERCHANT_NUMBER);//自己的 商户号
        transfers.setNonce_str(nonce);// 随机字符串
        transfers.setOpenid(openId);// 用户openId
        transfers.setCheck_name(WechatConfig.NO_CEHCK_USERNAME);// 校验用户姓名选项
        transfers.setAmount(money);// 付款金额
        transfers.setDesc(description);// 企业付款描述信息
        transfers.setSpbill_create_ip(WechatConfig.SOURCE_IP);// 调用接口的机器Ip地址
        transfers.setPartner_trade_no(internalOrderNumber);// 商户订单号
        String sign = createSendRedPackOrderSign(transfers);
        transfers.setSign(sign);// 签名

        xmlUtils.xstream().alias("xml", transfers.getClass());
        String requestString = xmlUtils.xstream().toXML(transfers);

        String responseString = sendToServer(WechatConfig.ENTERPRISE_PAY_URL, requestString);

        Map<ResultType, String> result = new HashMap<ResultType, String>(2);
        result.put(ResultType.REQUEST, requestString);
        result.put(ResultType.RESPONSE, responseString);

        return result;
    }

    private String createSendRedPackOrderSign(Transfer transfers) {

        StringBuffer sign = new StringBuffer();
        sign.append("mch_appid=").append(transfers.getMch_appid());
        sign.append("&mchid=").append(transfers.getMchid());
        sign.append("&nonce_str=").append(transfers.getNonce_str());
        sign.append("&partner_trade_no=").append(transfers.getPartner_trade_no());
        sign.append("&openid=").append(transfers.getOpenid());
        sign.append("&check_name=").append(transfers.getCheck_name());
        sign.append("&amount=").append(transfers.getAmount());
        sign.append("&desc=").append(transfers.getDesc());
        sign.append("&spbill_create_ip=").append(transfers.getSpbill_create_ip());
        sign.append("&key=").append(WechatConfig.PARTNER_KEY);

        return DigestUtils.md5Hex(sign.toString()).toUpperCase();
    }

    private String sendToServer(String url, String data) {
        StringBuilder message = new StringBuilder();
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(WechatConfig.CERT_LOCATION);
            keyStore.load(inputStream, WechatConfig.PARTNER_ID.toCharArray());
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WechatConfig.PARTNER_ID.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            HttpPost httpost = new HttpPost(url);

            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            //System.out.println("executing request" + httpost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                //System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                if (entity != null) {
                    //System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException ex) {
                throw new WechatRequestException(ex);
            } finally {
                response.close();
            }
        } catch (Exception ex) {
            throw new WechatRequestException(ex);
        }

        return message.toString();
    }
}
