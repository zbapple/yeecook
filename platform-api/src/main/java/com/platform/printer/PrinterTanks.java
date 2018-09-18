package com.platform.printer;


public class PrinterTanks implements Runnable {

    public PrinterTemplate printerTemplate;

    public PrinterTanks(PrinterTemplate printerTemplate1) {
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
