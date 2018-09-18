package com.platform.printer;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;
import com.platform.printer.vo.YeecookVo;
import com.platform.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//咕咕机
public class PrinterTemplate {

    private YeecookVo yeecookVo;
    private String startImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180903/14575544989ba4.png";
    private String endImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180904/1752152365a8a2.png";


    public PrinterTemplate(YeecookVo yeecookVo1) {
        this.yeecookVo = yeecookVo1;
    }

    private String htmlhead = "";

    private String htmlbodyStart = "";

    private String htmlbodyEnd ="";


    public String getPrinterHtmlStr() {

        List<OrderGoodsVo> goodsVoList = yeecookVo.getOrderGoodsVoList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getHtmlhead());
        stringBuilder.append(getHtmlbodyStart());

        for (OrderGoodsVo orderGoodsVo : goodsVoList) {
            int number = orderGoodsVo.getNumber().intValue();
            String retailPrice = orderGoodsVo.getRetail_price().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
            String zj = orderGoodsVo.getRetail_price().multiply(new BigDecimal(String.valueOf(number))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
            String name = orderGoodsVo.getGoods_name() + orderGoodsVo.getGoods_specifition_name_value();
            String goodsOv = "<tr>\n" +
                    "        <td valign=\"middle\" class=\"td4\">\n" +
                    "            <p class=\"p9\"><span class=\"s4\"><b>" + name + "</b></span></p>\n" +
                    "        </td>\n" +
                    "        <td valign=\"middle\" class=\"td5\">\n" +
                    "            <p class=\"p10\"><span class=\"s4\" style=\"word-break:break-all;\">" +
                    "            <b>" + number + "</b></span></p>\n" +
                    "        </td>\n" +
                    "        <td valign=\"middle\" class=\"td6\" style=\"word-break:break-all;\">\n" +
                    "            <p class=\"p10\"><span class=\"s4\"><b>" + retailPrice + "</b></span></p>\n" +
                    "        </td>\n" +
                    "        <td valign=\"middle\" class=\"td6\" style=\"word-break:break-all;\">\n" +
                    "            <p class=\"p10\"><span class=\"s4\"><b>" + zj + "</b></span></p>\n" +
                    "        </td>\n" +
                    "    </tr>\n";
            stringBuilder.append(goodsOv);
        }
        stringBuilder.append(getHtmlbodyEnd());
        return stringBuilder.toString();
    }

    public static void main(String [] age){
        YeecookVo yeecookVo=new YeecookVo();
        List<OrderGoodsVo> orderGoodsVoList=new ArrayList<>();
        OrderGoodsVo orderGoodsVo=new OrderGoodsVo();
        orderGoodsVo.setGoods_name("西部牛仔");
        orderGoodsVo.setGoods_specifition_name_value("特大号、牛逼");
        orderGoodsVo.setNumber(12);
        orderGoodsVo.setRetail_price(new BigDecimal("12.00"));
        OrderVo orderVo = new OrderVo();
        orderVo.setAddress("大新街十一郎社区维多利亚港口大厦");
        orderVo.setProvince("甘肃省");
        orderVo.setCity("临夏回族自治州");
        orderVo.setDistrict("积石山保安族东乡族撒拉族自治县");
        orderVo.setOrder_sn("123131123123123121");
        orderVo.setMobile("13524419722");
        orderVo.setConsignee("浪里白条");
        orderVo.setOrder_price(new BigDecimal("12312312.00"));
        for (int i=0;i<4;i++) {
            orderGoodsVoList.add(orderGoodsVo);
        }
        yeecookVo.setOrderGoodsVoList(orderGoodsVoList);
        yeecookVo.setOrderVo(orderVo);
        PrinterTemplate printerTemplate=new PrinterTemplate(yeecookVo);
        String html=printerTemplate.getPrinterHtmlStr();
        System.out.println(html);
        NetworkPrinterUtils.printHtml(html);
    }

    public String getStartImg() {
        return startImg;
    }

    public void setStartImg(String startImg) {
        this.startImg = startImg;
    }

    public String getEndImg() {
        return endImg;
    }

    public void setEndImg(String endImg) {
        this.endImg = endImg;
    }

    public String getHtmlhead() {
        String htmlhead = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
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
                "</head>\n";
        this.htmlhead=htmlhead;
        return this.htmlhead;
    }


    public String getHtmlbodyStart() {
        String address="地址："+yeecookVo.getOrderVo().getProvince() + yeecookVo.getOrderVo().getCity() +yeecookVo.getOrderVo().getDistrict()+ yeecookVo.getOrderVo().getAddress();
        address=PrinterStringUtils.fmHtmlBr(address,16);
        String htmlbodyStart =
                "<body>\n" +
                        "<div style=\"width: 808px;vertical-align: middle;text-align: center;\">\n" +
                        "<img src=\"" + startImg + "\" style=\"width: 350px\">\n" +
                        "</div>\n" +
                        "<p class=\"p2\"><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
                        "<p class=\"p3\"><span class=\"s3\"><b>订单编号</b></span></p>\n" +
                        "<p class=\"p4\"><span class=\"s4\"><b>" +  yeecookVo.getOrderVo().getOrder_sn() + "</b><br>\n" +
                        "</span></p>\n" +
                        "<p class=\"p5\"><span class=\"s5\"><b>客户：" + yeecookVo.getOrderVo().getConsignee() + "</b></span></p>\n" +
                        "<p class=\"p6\"><span class=\"s5\"><b>联系方式：</b></span><span class=\"s4\"><b>" + yeecookVo.getOrderVo().getMobile() + "</b></span></p>\n" +
                        "<p class=\"p5\"><span class=\"s5\"><b>" + address + "</b></span></p>\n" +
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
                        "    </tr>\n";
        this.htmlbodyStart=htmlbodyStart;
        return this.htmlbodyStart;
    }


    public String getHtmlbodyEnd() {
        String htmlbodyEnd1 =
                "    </tbody>\n" +
                        "</table>\n" +
                        "<table cellspacing=\"0\" cellpadding=\"0\" class=\"t2\">\n" +
                        "    <tbody>\n" +
                        "    <tr>\n" +
                        "        <td valign=\"middle\" class=\"td7\">\n" +
                        "            <p class=\"p12\"><span class=\"s5\"><b>总计："+yeecookVo.getOrderVo().getOrder_price().toString()+"元</b></span></p>\n" +
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
                        "    <img style=\"width: 500px\" src=\"" + endImg + "\">\n" +
                        "\n" +
                        "<p class=\"p13\"><span class=\"s5\"><b>打印日期：</b></span><span class=\"s4\"><b>" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) + "</b></span></p>\n" +
                        "</div>\n" +
                        "<p class=\"p2\"><span class=\"s2\"><b>………………..……………….............…………............</b></span></p>\n" +
                        "</body>\n" +
                        "</html>\n";
        this.htmlbodyEnd=htmlbodyEnd1;
        return htmlbodyEnd;
    }


}
