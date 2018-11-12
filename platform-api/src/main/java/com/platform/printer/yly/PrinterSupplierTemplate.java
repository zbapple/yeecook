package com.platform.printer.yly;

import com.platform.entity.OrderGoodsVo;
import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;
import com.platform.printer.vo.YeecookSupplierVo;
import com.platform.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PrinterSupplierTemplate {

    @Getter@Setter
    private YeecookSupplierVo yeecookVo;
    private String startImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180903/14575544989ba4.png";
    private String endImg = "https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20180904/1752152365a8a2.png";
    @Getter@Setter
    SysPrinterVo sysPrinterVo;
    @Getter@Setter
    List<SysPrinterUserVo> list;

    public PrinterSupplierTemplate(YeecookSupplierVo yeecookVo1,SysPrinterVo sysPrinterVo,List<SysPrinterUserVo> list) {
        this.yeecookVo = yeecookVo1;
        this.sysPrinterVo=sysPrinterVo;
        this.list=list;
    }

    private String htmlbodyStart = "";

    private String htmlbodyEnd = "";

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


    public String getHtmlbodyStart() {
        String address = yeecookVo.getOrderVo().getProvince() + yeecookVo.getOrderVo().getCity() + yeecookVo.getOrderVo().getDistrict() + yeecookVo.getOrderVo().getAddress();
        // address = PrinterStringUtils.fmHtmlBr(address, 16, "\n");
        String htmlbodyStart =
                "<center>------------------------------------------------</center>\n" +
                        "<FB><FS2><center>订单编号</center></FS2></FB>\n" +
                        "<FB><FS><center>" + yeecookVo.getOrderVo().getOrderSupSn() + "</center></FS></FB>\n" +
                        "<FB>客户：" + yeecookVo.getOrderVo().getConsignee() + "</FB>\n" +
                        "<FB>联系方式：" + yeecookVo.getOrderVo().getMobile() + "</FB>\n" +
                        "<FB>地址：" + address + "<FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<table>" +
                        "<tr>" +
                        "<td>" +
                        "清单" +
                        "</td>" +
                        "<td>" +
                        "数量" +
                        "</td>" +
                        "<td>" +
                        "单价" +
                        "</td>" +
                        "<td>" +
                        "总价" +
                        "</td>" +
                        "</tr>" + "<tr>" +
                        "<td>" +
                        " " +
                        "</td>" +
                        "<td>" +
                        " " +
                        "</td>" +
                        "<td>" +
                        " " +
                        "</td>" +
                        "<td>" +
                        " " +
                        "</td>" +
                        "</tr>";
        this.htmlbodyStart = htmlbodyStart;
        return this.htmlbodyStart;
    }

    public String getHtmlbodyEnd() {
        String gys = yeecookVo.getOrderVo().getSupplierName();
        String gys_dz =yeecookVo.getOrderVo().getSupplierAddress();
        String gys_tel = yeecookVo.getOrderVo().getSupplierTelephone();
        String htmlbodyEnd1 =
                "<FB>总计：" + yeecookVo.getOrderVo().getOrderPrice().toString() + "元</FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>供应商：" + gys + "</FB>\n" +
                        "<FB>地址：" + gys_dz + "</FB>\n" +
                        "<FB>总机：" + gys_tel + "</FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>下单日期：" + DateUtils.format(yeecookVo.getOrderVo().getPayTime(), DateUtils.DATE_TIME_PATTERN) + "</FB>\n" +
                        "<center>------------------------------------------------</center>\n" +
                        "<FB>打印日期：" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) + "</FB>\n" +"\n\n"+
                        "<center>--------------------扫码服务--------------------</center>\n";

        this.htmlbodyEnd = htmlbodyEnd1;
        return htmlbodyEnd;
    }

}
