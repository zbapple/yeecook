package com.platform.printer.guguji;

import java.util.ArrayList;
import java.util.List;

public class PrinterStringUtils {

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    public static void main(String[] args) {
        String test="甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县大新街十一郎社区维多利亚港口大厦1楼201室1";
        StringBuilder adrrs= new StringBuilder();
        for (String a:getStrList(test,16)
             ) {
            adrrs.append(a);
            adrrs.append("<br>");
        }
        System.out.println(adrrs);
    }

    /**
     * 把原始字符串分割成指定长度添加html换行符<br>显示在html
     *
     * @param str
     *            原始字符串
     * @param length
     *            指定长度
     * @return
     */
    public static String fmHtmlBr(String str,int length){
        if(null==str){ return "";}
        if(str.length()<length){ return str;}
        StringBuilder adrrs= new StringBuilder();
        for (String a:getStrList(str,length)
                ) {
            adrrs.append(a);
            adrrs.append("<br>");
        }
        return adrrs.toString();
    }

    public static String fmHtmlBr(String str,int length,String n){
        if(null==str){ return "";}
        if(str.length()<length){ return str;}
        StringBuilder adrrs= new StringBuilder();
        for (String a:getStrList(str,length)
        ) {
            adrrs.append(a);
            adrrs.append(n);
        }
        return adrrs.toString();
    }

}
