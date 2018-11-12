package com.platform.printer;


import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;

import com.platform.printer.yly.Methods;
import com.platform.printer.yly.PrinterTemplate;


import java.util.List;

public class PrinterTanks implements Runnable {

    public PrinterTemplate printerTemplate;


    public PrinterTanks(PrinterTemplate printerTemplate1) {
        this.printerTemplate = printerTemplate1;
    }

    @Override
    public void run() {
        try {
            //易联云打印
            SysPrinterVo sysPrinterVo= printerTemplate.getSysPrinterVo();
            List<SysPrinterUserVo> list=printerTemplate.getList();
            Methods.getInstance().init(sysPrinterVo.getAppId(),sysPrinterVo.getAppKey());
            Methods.getInstance().getFreedomToken();
            for (SysPrinterUserVo sysPrinterUserVo:list){
                Methods.getInstance().addPrinter(sysPrinterUserVo.getMachineCode(),sysPrinterUserVo.getMachineKey());
                Methods.getInstance().print(sysPrinterUserVo.getMachineCode(),printerTemplate.getPrinterHtmlStr(),printerTemplate.getYeecookVo().getOrderVo().getOrder_sn());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
