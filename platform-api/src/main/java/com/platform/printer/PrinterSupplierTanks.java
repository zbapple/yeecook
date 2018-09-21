package com.platform.printer;


public class PrinterSupplierTanks implements Runnable {

    public PrinterSupplierTemplate printerTemplate;

    public PrinterSupplierTanks(PrinterSupplierTemplate printerTemplate1) {
        this.printerTemplate = printerTemplate1;
    }

    @Override
    public void run() {
        try {
            NetworkPrinterUtils.printHtml(printerTemplate.getPrinterHtmlStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
