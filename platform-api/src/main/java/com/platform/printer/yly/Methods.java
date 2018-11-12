package com.platform.printer.yly;


import com.platform.cache.J2CacheUtils;
import com.platform.entity.OrderGoodsVo;
import com.platform.entity.OrderSupplierVo;
import com.platform.printer.vo.YeecookSupplierVo;
import com.platform.utils.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 易联云接口工具类
 */

public class Methods {
    /**
     * 易联云颁发给开发者的应用ID 非空值
     */
    public static String CLIENT_ID;

    /**
     * 易联云颁发给开发者的应用secret 非空值
     */
    public static String CLIENT_SECRET;

    public static String PRINT_TK="printTk";

    /**
     * token
     */
    public static String token;

    /**
     * 刷新token需要的 refreshtoken
     */
    public static String refresh_token;

    /**
     * code
     */
    public static String CODE;

    private Methods() {
    }

    private static class SingleMethods {
        private static final Methods COCOS_MANGER = new Methods();
    }

    public static final Methods getInstance() {
        return SingleMethods.COCOS_MANGER;
    }

    /**
     * 开放式初始化
     *
     * @param client_id
     * @param client_secret
     * @param code
     */
    public void init(String client_id, String client_secret, String code) {
        CLIENT_ID = client_id;
        CLIENT_SECRET = client_secret;
        CODE = code;
    }

    /**
     * 自有初始化
     *
     * @param client_id
     * @param client_secret
     */
    public void init(String client_id, String client_secret) {
        CLIENT_ID = client_id;
        CLIENT_SECRET = client_secret;
    }

    /**
     * 开放应用
     */
    public String getToken() {
        String result = LAVApi.getToken(CLIENT_ID,
                "authorization_code",
                LAVApi.getSin(),
                CODE,
                "all",
                String.valueOf(System.currentTimeMillis() / 1000),
                LAVApi.getuuid());
        try {
            JSONObject json = new JSONObject(result);
            JSONObject body = json.getJSONObject("body");
            token = body.getString("access_token");
            refresh_token = body.getString("refresh_token");
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("getToken出现Json异常！" + e);
        }
        return result;
    }

    /**
     * 自有应用服务
     */
    public String getFreedomToken() {
        token= (String) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, PRINT_TK);
        String result="";
        if(!StringUtils.isNotEmpty(token)) {
             result = LAVApi.getToken(CLIENT_ID,
                    "client_credentials",
                    LAVApi.getSin(),
                    "all",
                    String.valueOf(System.currentTimeMillis() / 1000),
                    LAVApi.getuuid());
            try {
                JSONObject json = new JSONObject(result);
                JSONObject body = json.getJSONObject("body");
                token = body.getString("access_token");
                refresh_token = body.getString("refresh_token");
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, PRINT_TK, token);
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("getFreedomToken出现Json异常！" + e);
            }
        }
        return result;
    }

    /**
     * 刷新token
     */
    public String refreshToken() {
        String result = LAVApi.refreshToken(CLIENT_ID,
                "refresh_token",
                "all",
                LAVApi.getSin(),
                refresh_token,
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
        try {
            JSONObject json = new JSONObject(result);
            JSONObject body = json.getJSONObject("body");
            token = body.getString("access_token");
            refresh_token = body.getString("refresh_token");
            J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, PRINT_TK, token);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("refreshToken出现Json异常！" + e);
        }
        return result;
    }

    /**
     * 添加终端授权 开放应用服务模式不需要此接口 ,自有应用服务模式所需参数
     */
    public String addPrinter(String machine_code, String msign) {
        return LAVApi.addPrinter(CLIENT_ID,
                machine_code,
                msign,
                token,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 极速授权
     */
    public String speedAu(String machine_code, String qr_key) {
        return LAVApi.speedAu(CLIENT_ID,
                machine_code,
                qr_key,
                "all",
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 打印
     */
    public String print(String machine_code, String content, String origin_id) {
        return LAVApi.print(CLIENT_ID,
                token,
                machine_code,
                content,
                origin_id,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 删除终端授权
     */
    public String deletePrinter(String machine_code) {
        return LAVApi.deletePrinter(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 添加应用菜单
     */
    public String addPrintMenu(String machine_code, String content) {
        return LAVApi.addPrintMenu(CLIENT_ID,
                token,
                machine_code,
                content,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 关机重启接口
     */
    public String shutDownRestart(String machine_code, String response_type) {
        return LAVApi.shutDownRestart(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 声音调节
     */
    public String setSound(String machine_code, String response_type, String voice) {
        return LAVApi.setSound(CLIENT_ID,
                token,
                machine_code,
                response_type,
                voice,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 获取机型打印宽度接口
     */
    public String getPrintInfo(String machine_code) {
        return LAVApi.getPrintInfo(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 获取机型软硬件版本接口
     */
    public String getVersion(String machine_code) {
        return LAVApi.getVersion(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 取消所有未打印订单
     */
    public String cancelAll(String machine_code) {
        return LAVApi.cancelAll(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 取消单条未打印订单
     */
    public String cancelOne(String machine_code, String order_id) {
        return LAVApi.cancelOne(CLIENT_ID,
                token,
                machine_code,
                order_id,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 设置logo
     */
    public String setIcon(String machine_code, String img_url) {
        return LAVApi.setIcon(CLIENT_ID,
                token,
                machine_code,
                img_url,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 删除logo
     */
    public String deleteIcon(String machine_code) {
        return LAVApi.deleteIcon(CLIENT_ID,
                token,
                machine_code,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 打印方式
     */
    public String btnPrint(String machine_code, String response_type) {
        return LAVApi.btnPrint(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    /**
     * 接单拒单设置接口
     */
    public String getOrder(String machine_code, String response_type) {
        return LAVApi.getOrder(CLIENT_ID,
                token,
                machine_code,
                response_type,
                LAVApi.getSin(),
                LAVApi.getuuid(),
                String.valueOf(System.currentTimeMillis() / 1000));
    }

    public static void main(String age[]){

        YeecookSupplierVo yeecookVo=new YeecookSupplierVo();
        List<OrderGoodsVo> orderGoodsVoList=new ArrayList<>();
        OrderGoodsVo orderGoodsVo=new OrderGoodsVo();
        orderGoodsVo.setGoods_name("西部sdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdf牛仔");
        orderGoodsVo.setGoods_specifition_name_value("特大号、牛逼");
        orderGoodsVo.setNumber(12);
        orderGoodsVo.setRetail_price(new BigDecimal("11112.00"));
        OrderSupplierVo orderVo = new OrderSupplierVo();
        orderVo.setAddress("大新街十一郎社区维多利亚港口大厦");
        orderVo.setProvince("甘肃省");
        orderVo.setCity("临夏回族自治州");
        orderVo.setDistrict("积石山保安族东乡族撒拉族自治县");
        orderVo.setOrderSupSn("123131123123123121");
        orderVo.setMobile("13524419722");
        orderVo.setConsignee("浪里白条");
        orderVo.setOrderPrice(new BigDecimal("12312312.00"));
        for (int i=0;i<4;i++) {
            orderGoodsVoList.add(orderGoodsVo);
        }
        yeecookVo.setOrderGoodsVoList(orderGoodsVoList);
        yeecookVo.setOrderVo(orderVo);
//        PrinterSupplierTemplate printerTemplate=new PrinterSupplierTemplate(yeecookVo);
//
//        Methods.getInstance().init("1038775691","53884f576f3fba3cf4312019eae5aff4");
//        Methods.token="2c11d620c6e9420aae0ebb45ba156786";
//        System.out.println(Methods.getInstance().getFreedomToken());
//        //Methods.getInstance().refreshToken();
//        //Methods.getInstance().deleteIcon("4004580684");
//
//        System.out.println(Methods.getInstance().print("4004580684",printerTemplate.getPrinterHtmlStr(),"9"));

    }

}
