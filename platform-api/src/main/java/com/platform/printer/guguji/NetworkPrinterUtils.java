package com.platform.printer.guguji;

import com.platform.utils.Base64;
import com.platform.utils.DateUtils;
import com.platform.utils.HttpUtil;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NetworkPrinterUtils {

  public static String html="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
          "<html>\n" +
          "<head>\n" +
          "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
          "    <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n" +
          "    <title></title>\n" +
          "    <meta name=\"Generator\" content=\"Cocoa HTML Writer\">\n" +
          "    <meta name=\"CocoaVersion\" content=\"1561.6\">\n" +
          "    <style type=\"text/css\">\n" +
          "\n" +
          "        p.p1 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 100.0px;\n" +
          "            font: 72.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p2 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 50.0px;\n" +
          "            font: 36.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p3 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 90.0px;\n" +
          "            font: 64.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p4 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 78.0px;\n" +
          "            font: 48.0px 'Helvetica Neue';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #1ab394\n" +
          "        }\n" +
          "\n" +
          "        p.p5 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 67.0px;\n" +
          "            font: 48.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #000000\n" +
          "        }\n" +
          "\n" +
          "        p.p6 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 67.0px;\n" +
          "            font: 48.0px 'Helvetica Neue';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #000000\n" +
          "        }\n" +
          "\n" +
          "        p.p7 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 41.0px;\n" +
          "            font: 36.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p8 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            text-align: center;\n" +
          "            line-height: 67.0px;\n" +
          "            font: 48.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p9 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 50.0px;\n" +
          "            font: 36.0px 'PingFang SC';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p10 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 44.0px;\n" +
          "            font: 36.0px 'Helvetica Neue';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p12 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 67.0px;\n" +
          "            font: 48.0px 'PingFang SC Semibold';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p13 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 67.0px;\n" +
          "            font: 48.0px 'Helvetica Neue';\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        p.p14 {\n" +
          "            margin: 0.0px 0.0px 0.0px 0.0px;\n" +
          "            line-height: 55.0px;\n" +
          "            font: 48.0px Times;\n" +
          "            color: #000000;\n" +
          "            -webkit-text-stroke: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        span.s1 {\n" +
          "            font: 72.0px 'PingFang SC';\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s2 {\n" +
          "            font: 36.0px 'PingFang SC';\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s3 {\n" +
          "            font: 64.0px 'PingFang SC';\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s4 {\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s5 {\n" +
          "            font: 48.0px 'PingFang SC';\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s6 {\n" +
          "            font: 36.0px Times;\n" +
          "            font-kerning: none;\n" +
          "            color: #676a6c\n" +
          "        }\n" +
          "\n" +
          "        span.s7 {\n" +
          "            font: 36.0px 'Helvetica Neue';\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        span.s8 {\n" +
          "            font: 36.0px Times;\n" +
          "            font-kerning: none\n" +
          "        }\n" +
          "\n" +
          "        table.t1 {\n" +
          "            width: 808.0px;\n" +
          "            background-color: #ffffff;\n" +
          "            margin: 0.0px 0.0px 20.0px 0.0px;\n" +
          "            border-style: solid;\n" +
          "            border-width: 1.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            border-collapse: collapse;\n" +
          "            table-layout: fixed\n" +
          "        }\n" +
          "\n" +
          "        table.t2 {\n" +
          "            margin: 0.0px 0.0px 20.0px 0.0px;\n" +
          "            border-collapse: collapse\n" +
          "        }\n" +
          "\n" +
          "        td.td1 {\n" +
          "            width: 272.0px;\n" +
          "            background-color: #f5f5f6;\n" +
          "            border-style: solid;\n" +
          "            border-width: 0.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td2 {\n" +
          "            width: 172.0px;\n" +
          "            background-color: #f5f5f6;\n" +
          "            border-style: solid;\n" +
          "            border-width: 0.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td3 {\n" +
          "            width: 182.0px;\n" +
          "            background-color: #f5f5f6;\n" +
          "            border-style: solid;\n" +
          "            border-width: 0.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td4 {\n" +
          "            border-style: solid;\n" +
          "            border-width: 1.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td5 {\n" +
          "            border-style: solid;\n" +
          "            border-width: 1.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td6 {\n" +
          "            border-style: solid;\n" +
          "            border-width: 1.0px 1.0px 1.0px 1.0px;\n" +
          "            border-color: #000000 #000000 #000000 #000000;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "\n" +
          "        td.td7 {\n" +
          "            width: 758.0px;\n" +
          "            padding: 8.0px 8.0px 8.0px 8.0px\n" +
          "        }\n" +
          "    </style>\n" +
          "</head>\n" +
          "<body>\n" +
          "<div style=\"width: 808px;vertical-align: middle;text-align: center;\">\n" +
          "<img src=\"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180903/14575544989ba4.png\" style=\"width: 350px\">\n" +
          "</div>\n" +
          "<p class=\"p2\"><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
          "<p class=\"p3\"><span class=\"s3\"><b>单据编号</b></span></p>\n" +
          "<p class=\"p4\"><span class=\"s4\"><b>20180828165745351571612</b><br>\n" +
          "</span></p>\n" +
          "<p class=\"p5\"><span class=\"s5\"><b>客户：邹斌</b></span></p>\n" +
          "<p class=\"p5\"><span class=\"s5\"><b>地址：怀化市通道侗族自治县</b></span></p>\n" +
          "<p class=\"p6\"><span class=\"s5\"><b>联系方式：</b></span><span class=\"s4\"><b>13524419702</b></span></p>\n" +
          "<p class=\"p7\"><span class=\"s6\"><br>\n" +
          "</span><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
          "<table width=\"756.0\" cellspacing=\"0\" cellpadding=\"0\" class=\"t1\">\n" +
          "    <tbody>\n" +
          "    <tr>\n" +
          "        <td valign=\"middle\" class=\"td1\">\n" +
          "            <p class=\"p8\"><span class=\"s5\"><b>清单</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td2\">\n" +
          "            <p class=\"p8\"><span class=\"s5\"><b>数量</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td3\">\n" +
          "            <p class=\"p8\"><span class=\"s5\"><b>单价</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td3\">\n" +
          "            <p class=\"p8\"><span class=\"s5\"><b>总价</b></span></p>\n" +
          "        </td>\n" +
          "    </tr>\n" +
          "    <tr>\n" +
          "        <td valign=\"middle\" class=\"td4\">\n" +
          "            <p class=\"p9\"><span class=\"s4\"><b>德州烤肉</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td5\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>12344</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td6\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>121.00</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td6\" style=\"word-break:break-all;\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>1111111111211.00</b></span></p>\n" +
          "        </td>\n" +
          "    </tr>\n" +
          "    <tr>\n" +
          "        <td valign=\"middle\" class=\"td4\">\n" +
          "            <p class=\"p9\"><span class=\"s4\"><b>西班牙考士大夫、红色、加油、不放醋酱料、要清蒸、测试长度、测试测试长度测试长度长度测试长度测试长度</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td5\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>12344</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td6\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>121.00</b></span></p>\n" +
          "        </td>\n" +
          "        <td valign=\"middle\" class=\"td6\">\n" +
          "            <p class=\"p10\"><span class=\"s4\"><b>1111211.00</b></span></p>\n" +
          "        </td>\n" +
          "    </tr>\n" +
          "\n" +
          "    </tbody>\n" +
          "</table>\n" +
          "<table cellspacing=\"0\" cellpadding=\"0\" class=\"t2\">\n" +
          "    <tbody>\n" +
          "    <tr>\n" +
          "        <td valign=\"middle\" class=\"td7\">\n" +
          "            <p class=\"p12\"><span class=\"s5\"><b>总计：123123元</b></span></p>\n" +
          "        </td>\n" +
          "    </tr>\n" +
          "    </tbody>\n" +
          "</table>\n" +
          "<p class=\"p7\"><span class=\"s8\"><br>\n" +
          "</span><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
          "<p class=\"p12\"><span class=\"s5\"><b>供应商：宜厨优品</b></span></p>\n" +
          "<p class=\"p12\"><span class=\"s5\"><b>地址：农林上路八横路</b></span></p>\n" +
          "<p class=\"p13\"><span class=\"s5\"><b>总机：</b></span><span class=\"s4\"><b>(86) 012329821</b></span></p>\n" +
          "<p class=\"p14\"><span class=\"s4\"><br>\n" +
          "</span></p>\n" +
          "<p class=\"p2\"><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
          "<div style=\"width: 808px;vertical-align: middle;text-align: center;\">\n" +
          "<p class=\"p1\"><span class=\"s1\"><b>服务扫码</b></span></p>\n" +
          "\n" +
          "    <img style=\"width: 500px\" src=\"https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180904/1752152365a8a2.png\">\n" +
          "\n" +
          "<p class=\"p13\"><span class=\"s5\"><b>打印日期：</b></span><span class=\"s4\"><b>2018-09-03~12:23</b></span></p>\n" +
          "</div>\n" +
          "<p class=\"p2\"><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
          "</body>\n" +
          "</html>\n";


    public static String yeecookOrderPrint(){



        return "";
    }

    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String []ags){
        //System.out.println(printpaper("宜厨科技",true));
        System.out.println(printHtml(html));
        System.out.println(html);
    }

    public static String printpaper(String a){

        return printpaper(a,true);
    }

    public static String printpaper(String a,boolean isText){
        Map<String, Object> params =new HashMap<>();
        params.put("ak","f44d0a95686c4fc9ba8d2e9edd0f6eb3");
        params.put("timestamp",DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        params.put("memobirdID","291344c7d0c27bb1");
        params.put("userID","659089");
        if(isText){
            params.put("printcontent","T:"+Base64.encode(a,Base64.GBK));
        }else {
            params.put("printcontent", "P:Qk0OAgAAAAAAAD4AAAAoAAAAOgAAAMb///8BA" +
                    "AEAAAAAAAAAAADEDgAAxA4AAAIAAAACAAAAAAAA//////8A///////AAAP//// ///AAB///////+AAf///////+AB////////4AP///++/f/wB////719//gH/// /frv/+A////+7ff/8D////779//wP/////37//A////9dW//8D////6rV//wP/ ///a1r//A////+1bf/8D////7/7//wP/////////A/////qr//8D////7/1//w P////aq7//A////3/+1/8D////1VX//wP///17v1f/A////67q+v8D///1d3ev /wP////a3XX/A///9X/73v8D///+1R7rfwP//7XvG17/A//+21u9978D//1v/v da/wP/+6qr2+9/A//1bfbtW78D/263Xb/2/wP/9arvaq2/A/3vV7Xff38D//f6 3vXV/wP23/93Xvt/A///f9vrV78D/33/dr39fwP19+/d11f/A//f/+77uv8D// 9/t1bv/wP/e//b/bX/A//v//1Xf/8D//7/q7vX/wP////e7X//A//7/3W3v/8D ////333//wP///9r1v//Af///717//4B///+76///gD///+1///8AH//////// gAf///////+AAf///////gAA///////8AAA///////AAA==");
        }
        String sa= HttpUtil.URLPost("http://open.memobird.cn/home/printpaper",params,HttpUtil.URL_PARAM_DECODECHARSET_UTF8);
        return sa;
    }

    public static String printHtml(String a){
        String param= null;
        param = Base64.encode(a,Base64.GBK);
        Map<String, Object> params =new HashMap<>();
        params.put("ak","f44d0a95686c4fc9ba8d2e9edd0f6eb3");
        params.put("timestamp",DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        params.put("memobirdID","291344c7d0c27bb1");
        params.put("userID","659089");
        params.put("printHtml",param);
        String sa= HttpUtil.URLPost("http://open.memobird.cn/home/printpaperFromHtml",params,HttpUtil.URL_PARAM_DECODECHARSET_GBK);
        return sa;
    }

}
