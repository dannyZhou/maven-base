package com.dannyzhou.wechatpay.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by danny on 2017/6/9.
 */
@Getter
@Setter
public class Transfer {

    private String mch_appid;// 公众账号
    private String mchid;// 商户号
    private String nonce_str;// 随机字符串
    private String sign;// 签名
    private String partner_trade_no;// 商户订单号
    private String openid;// 用户openid

    //校验用户姓名选项  NO_CHECK：不校验真实姓名
    private String check_name;
    private int amount;// 付款金额
    private String desc;// 企业付款描述信息
    private String spbill_create_ip;// 调用接口的机器Ip地址
}
