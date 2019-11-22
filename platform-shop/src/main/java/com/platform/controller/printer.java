package com.platform.controller;


import com.platform.printer.yly.Methods;
import com.platform.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@RestController
@RequestMapping("printer")
public class printer {
//    public static void main(String age[]) {
//        String  obj=
//                "\n" +
//                        "<FS>老鸽</FS>\n" +
//                        "\n" +
//                        "瘦肉丁  200g\n" +
//                        "鱼腩  150g\n" +
//
//                        "排骨   250g\n" +
//
//                        "鸡肉丁   300g\n" +
//
//                        "\n" +
//                        "     " +
//                        "                宜厨.云厨房" +
//                        "\n" +
//                        "     " +
//                        "                2019.07.30" ;
//
//        int count =5;
//
//        prt(count,obj);
//    }
    @RequestMapping("/status")
    public R status(){
        try {
            Methods.getInstance().init("1038775691", "53884f576f3fba3cf4312019eae5aff4");
            Methods.token = "0762e49a2ea94f4ab091d818a0355591";  //token
            Methods.refresh_token = "01c2d1329dd14225b06c8583ee62c59c"; //refresh_token
            Methods.getInstance().getFreedomToken(); //正式环境直接调用此方法将token refresh_token保存在缓存中
            Methods.getInstance().addPrinter("4004584905", "rmhqa8kpf4ae");
            String  state=Methods.getInstance().printStatus("4004584905");
            return R.ok().put("state",state);
        } catch (Methods.PrinterException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
    @RequestMapping("/prt")
    public  R prt(@RequestBody String obj){
        if( null != obj && obj.length()>0) {
            String[] str = obj.split("&");
            String[] str2 = str[0].split("count=");
                int count = Integer.parseInt(str2[1]);
                String[] str3 = str[1].split("content=");
                String content = null;
                try {
                    content = URLDecoder.decode(str3[1], "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (count != 0 && content != null) {
                    try {
                        Methods.getInstance().init("1038775691", "53884f576f3fba3cf4312019eae5aff4");
                        Methods.token = "0762e49a2ea94f4ab091d818a0355591";  //token
                        Methods.refresh_token = "01c2d1329dd14225b06c8583ee62c59c"; //refresh_token
//            Methods.getInstance().getFreedomToken1(); //刷新 token refresh_token 此方法用于测试
                        Methods.getInstance().getFreedomToken(); //正式环境直接调用此方法将token refresh_token保存在缓存中
                        Methods.getInstance().addPrinter("4004584905", "rmhqa8kpf4ae");
                        for (int i = 0; i < count; i++) {
                            Methods.getInstance().print("4004584905", content, "10");
                        }
                    } catch (Methods.PrinterException e) {
                        e.printStackTrace();
                    }
                } else if (count == 0) {
                    String msg = "打印份数不能为空";
                    return R.error(msg);
                }
            }
        return R.ok();
    }
}
