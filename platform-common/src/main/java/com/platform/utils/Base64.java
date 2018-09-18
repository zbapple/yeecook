package com.platform.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 16:17<br>
 * 描述: Base64 <br>
 */
public class Base64 {

    public static final String UTF_8="utf-8";
    public static final String GBK="gb2312";

    // 加密
    public static String encode(String str) {
        return encode(str,UTF_8);
    }

    // 解密
    public static String decode(String s) {
        return decode(s,UTF_8);
    }

    public static String encode(String str,String code) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes(code);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    public static String decode(String s,String code) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String toGBK(String source) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = source.getBytes("GBK");
        for (byte b : bytes) {
            sb.append("%" + Integer.toHexString((b & 0xff)).toUpperCase());
        }

        return sb.toString();
    }

}