package com.platform.printer;

import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;
import com.platform.printer.yly.Methods;
import com.platform.printer.yly.PrinterSupplierTemplate;


import java.util.List;

public class PrinterSupplierTanks implements Runnable {

    public PrinterSupplierTemplate printerTemplate;



    public PrinterSupplierTanks(PrinterSupplierTemplate printerTemplate1) {
        this.printerTemplate = printerTemplate1;
    }

    @Override
    public void run() {
        try {
          //  NetworkPrinterUtils.printHtml(printerTemplate.getPrinterHtmlStr()); 咕咕机打印
            //易联云打印
            SysPrinterVo sysPrinterVo= printerTemplate.getSysPrinterVo();
            List<SysPrinterUserVo> list=printerTemplate.getList();
            Methods.getInstance().init(sysPrinterVo.getAppId(),sysPrinterVo.getAppKey());
            Methods.getInstance().getFreedomToken();
            for (SysPrinterUserVo sysPrinterUserVo:list){
                Methods.getInstance().addPrinter(sysPrinterUserVo.getMachineCode(),sysPrinterUserVo.getMachineKey());
                Methods.getInstance().print(sysPrinterUserVo.getMachineCode(),printerTemplate.getPrinterHtmlStr(),printerTemplate.getYeecookVo().getOrderVo().getOrderSupSn());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
