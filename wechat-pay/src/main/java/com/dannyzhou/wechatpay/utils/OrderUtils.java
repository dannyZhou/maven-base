package com.dannyzhou.wechatpay.utils;

import java.util.Random;

/**
 * Created by danny on 2017/6/8.
 */
public class OrderUtils {

    private static Random random = new Random();

    private OrderUtils() {
    }

    public static String generateOrderId() {
        StringBuilder builder = new StringBuilder();
        builder.append(System.currentTimeMillis());
        builder.append("0000");
        builder.append(random.nextInt());

        return builder.toString();
    }
}
