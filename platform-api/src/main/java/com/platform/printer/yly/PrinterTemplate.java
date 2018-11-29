package com.platform.printer.yly;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderVo;
import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;
import com.platform.printer.guguji.NetworkPrinterUtils;
import com.platform.printer.guguji.PrinterStringUtils;
import com.platform.printer.vo.YeecookSupplierVo;
import com.platform.printer.vo.YeecookVo;
import com.platform.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//易联云
public class PrinterTemplate {

    @Getter
    @Setter
    private YeecookVo yeecookVo;
    private String startImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180903/14575544989ba4.png";
    private String endImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180904/1752152365a8a2.png";
    @Getter@Setter
    SysPrinterVo sysPrinterVo;
    @Getter@Setter
    List<SysPrinterUserVo> list;

    public PrinterTemplate(YeecookVo yeecookVo1, SysPrinterVo sysPrinterVo, List<SysPrinterUserVo> list) {
        this.yeecookVo = yeecookVo1;
        this.sysPrinterVo=sysPrinterVo;
        this.list=list;
    }

    private String htmlhead = "";

    private String htmlbodyStart = "";

    private String htmlbodyEnd ="";

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
//        PrinterTemplate printerTemplate=new PrinterTemplate(yeecookVo);
//        String html=printerTemplate.getPrinterHtmlStr();
//        System.out.println(html);
      //  NetworkPrinterUtils.printHtml(html);
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
        String htmlhead = "";
        this.htmlhead=htmlhead;
        return this.htmlhead;
    }

    public String getPrinterHtmlStr() {
        List<OrderGoodsVo> goodsVoList = yeecookVo.getOrderGoodsVoList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getHtmlbodyStart());
        for (OrderGoodsVo orderGoodsVo : goodsVoList) {
            int number = orderGoodsVo.getNumber().intValue();
            String retailPrice = orderGoodsVo.getRetail_price().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
            String zj = orderGoodsVo.getRetail_price().multiply(new BigDecimal(String.valueOf(number))).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
            String name = orderGoodsVo.getGoods_name() + orderGoodsVo.getGoods_specifition_name_value();
            String goodsOv = "<tr>" +
                    "<td>" + name + "</td>" +
                    "<td>[" + number + "]</td>" +
                    "<td>[" + retailPrice + "]</td>" +
                    "<td>[" + zj + "]</td>" +
                    "</tr>";
            stringBuilder.append(goodsOv);
        }
        stringBuilder.append("</table>\n");
        stringBuilder.append(getHtmlbodyEnd());
        stringBuilder.append(" <QR>"+endImg+"</QR>");
        return stringBuilder.toString();
    }

    public String getHtmlbodyStart() {
        StringBuilder address=new StringBuilder();
        address.append(yeecookVo.getOrderVo().getProvince());
        address.append(yeecookVo.getOrderVo().getCity());
        address.append(yeecookVo.getOrderVo().getDistrict());
        address.append(yeecookVo.getOrderVo().getAddress());
        StringBuilder htmlbodyStart=new StringBuilder();
        htmlbodyStart.append("<center>------------------------------------------------</center>\n");
        htmlbodyStart.append("<FB><FS2><center>订单编号</center></FS2></FB>\n" );
        htmlbodyStart.append("<FB><FS><center>" + yeecookVo.getOrderVo().getOrder_sn() + "</center></FS></FB>\n");
        htmlbodyStart.append("<FB>客户：" + yeecookVo.getOrderVo().getConsignee() + "</FB>\n" );
        htmlbodyStart.append("<FB>联系方式：" + yeecookVo.getOrderVo().getMobile() + "</FB>\n" );
        htmlbodyStart.append("<FB>地址：" + address.toString() + "<FB>\n" );
        htmlbodyStart.append("<center>------------------------------------------------</center>\n" );
        htmlbodyStart.append("<table>" );
        htmlbodyStart.append("<tr>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append("清单" );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append("数量" );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append("单价" );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append("总价" );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("</tr>" );
        htmlbodyStart.append("<tr>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append(" " );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append(" " );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>" );
        htmlbodyStart.append(" " );
        htmlbodyStart.append("</td>" );
        htmlbodyStart.append("<td>");
        htmlbodyStart.append(" ");
        htmlbodyStart.append("</td>");
        htmlbodyStart.append("</tr>");
        this.htmlbodyStart = htmlbodyStart.toString();
        return this.htmlbodyStart;
    }

    public String getHtmlbodyEnd() {

        String htmlbodyEnd1 =
                "<FB>总计：" + yeecookVo.getOrderVo().getOrder_price().toString() + "元</FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>宜厨优品</FB>\n" +
                        "<FB>地址：农林上路八横路</FB>\n" +
                        "<FB>总机：(86) 012329821 </FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>下单日期：" + DateUtils.format(yeecookVo.getOrderVo().getPay_time(), DateUtils.DATE_TIME_PATTERN) + "</FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>打印日期：" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) + "</FB>\n" +"\n\n"+
                        "<center>--------------------扫码服务--------------------</center>\n";

        this.htmlbodyEnd = htmlbodyEnd1;
        return htmlbodyEnd;
    }




}
