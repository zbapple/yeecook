package com.platform.entity.pojo.service;

import com.platform.printer.yly.Methods;

public class printer {
    public static void main(String age[]) {
        String  obj=
                "\n" +
                        "<FS>老鸽</FS>\n" +
                        "\n" +
                        "瘦肉丁  200g\n" +
                        "鱼腩  150g\n" +

                        "排骨   250g\n" +

                        "鸡肉丁   300g\n" +

                        "\n" +
                        "     " +
                        "                宜厨.云厨房" +
                        "\n" +
                        "     " +
                        "                2019.07.30" ;

        int count =5;

        prt(count,obj);
    }


    public  static  void prt(int count,String obj){
        try {
            Methods.getInstance().init("1038775691","53884f576f3fba3cf4312019eae5aff4");
            Methods.token="0762e49a2ea94f4ab091d818a0355591";  //token
            Methods.refresh_token="01c2d1329dd14225b06c8583ee62c59c"; //refresh_token
            Methods.getInstance().getFreedomToken1(); //刷新 token refresh_token 此方法用于测试
            //Methods.getInstance().getFreedomToken() //正式环境直接调用此方法将token refresh_token保存在缓存中
            Methods.getInstance().addPrinter("4004584905","rmhqa8kpf4ae");

            for (int i=0 ;i<count;i++) {
                Methods.getInstance().print("4004584905", obj, "10");
            }
        } catch (Methods.PrinterException e) {
            e.printStackTrace();

        }
    }
}
