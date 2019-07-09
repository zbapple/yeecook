package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.entity.ApiMenuDetaileVo;
import com.platform.entity.MenuDetailsVo;

import com.platform.entity.UserVo;

import com.platform.service.ApiMenuDetailsService;

import com.platform.util.ApiBaseAction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * 餐单详情表
 id
 餐单id
 餐单类型
 菜品id
 菜品名称
 是否叶子节点
 父级id
 用餐时间
 餐单日期Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:55
 */
@Api(tags = "食谱详情")
@RestController
@RequestMapping("/api/MenuDetails")
public class ApiMenuDetailsController extends ApiBaseAction {
    @Autowired
    private ApiMenuDetailsService menuDetailsService;

    @ApiOperation(value = "今日食谱")
    @PostMapping("menuinfo")
    public Object menuinfo(@LoginUser UserVo loginUser) {
        Map<String, Object> result = new HashMap<>();
        JSONObject dayjsonparam =this.getJsonRequest();
        Long userid = loginUser.getUserId();
        String today = dayjsonparam.getString("today");
        Map dayfoodmap = new HashMap();
        dayfoodmap.put("userid", userid);
        dayfoodmap.put("today", today);
        List<MenuDetailsVo> userlist = menuDetailsService.queryList(dayfoodmap);
        Double countgood = 0.0;
        Double sumcalluch = 0.0;
        Double couteve = 0.0;
        Double sum = 0.0;
        List beabreakfastlist=new ArrayList();
        List Lunchlist=new ArrayList();
        List Dinnerlist=new ArrayList();
         if (userlist != null) {
            for (MenuDetailsVo MenuDetailsVoItem : userlist) {
                if (MenuDetailsVoItem.getMenuType().equals("0") || MenuDetailsVoItem.getMenuType().equals("3") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    Map namemap=new HashMap();
                    countgood += MenuDetailsVoItem.getDishescalories();
                    namemap.put("namegood",MenuDetailsVoItem.getDishesName()) ;
                    beabreakfastlist.add(namemap);
                } else if (MenuDetailsVoItem.getMenuType().equals("1") || MenuDetailsVoItem.getMenuType().equals("4") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    Map namemap1=new HashMap();
                    sumcalluch += MenuDetailsVoItem.getDishescalories();
                    namemap1.put("nameluch",MenuDetailsVoItem.getDishesName());
                    Lunchlist.add(namemap1);
                } else if (MenuDetailsVoItem.getMenuType().equals("2")  || MenuDetailsVoItem.getMenuType().equals("5") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    Map namemap2=new HashMap();
                    couteve += MenuDetailsVoItem.getDishescalories();
                 namemap2.put("nameev",MenuDetailsVoItem.getDishesName());
                 Dinnerlist.add(namemap2);
                }
            }
            sum = countgood + sumcalluch + couteve;
            result.put("beabreakfastlist", beabreakfastlist);
            result.put("countgood", countgood);
            result.put("namemLunchlistap1", Lunchlist);
            result.put("sumcalluch", sumcalluch);
            result.put("Dinnerlist", Dinnerlist);
            result.put("couteve", couteve);
            result.put("sum", sum);
            result.put("flag", 1);
            return toResponsSuccess(result);
        } else {
            result.put("flag", 0);
            return toResponsSuccess(result);
        }
    }

    @ApiOperation(value = "用户一天的食谱")
    @PostMapping("todayinfo")
    public Object todayinfo( @LoginUser UserVo lginUser) {
        Map<String, Object> result = new HashMap<>();
        JSONObject todayinfojson = this.getJsonRequest();
       String todays = todayinfojson.getString("todays");
        Long nideshopUserid = lginUser.getUserId();
        Map todaymap = new HashMap();
        todaymap.put("nideshopUserid", nideshopUserid);
        todaymap.put("todays", todays);
        List<MenuDetailsVo> menuDetailsVoList = menuDetailsService.querListvo(todaymap); //查询出菜谱所有菜品信息
        List<ApiMenuDetaileVo> apiMenuDetaileVos = new ArrayList<>();    //传给前端的数据格式
        Double breakfastcal=0.0; //早餐总能量
        Double lunchcal=0.0;     //午餐总能量
        Double dinnercal=0.0;    //晚餐总能量

        Date breakfasttime = null;   //早餐时间段
        Date lunchtime = null;       //午餐时间段
        Date dinnertime = null;      //晚餐时间段

        Double breakfastSnackscal=0.0; //加餐早餐总能量
        Double dinnerSnackscal=0.0;    //加餐晚餐总能量
        Double lunchSnackscal=0.0;     //加餐午餐总能量

        Double breakfastsumcal=0.0;    //早餐与加餐总能量
        Double lunchsumcal=0.0;        //午餐与加餐总能量
        Double dinnersumcal=0.0;       //晚餐与加餐总能量

        List<Map>  zhengcan_0=new ArrayList<>();   //正餐早餐集合
        List<Map>  zhengcan_1=new ArrayList<>();   //正餐中餐集合
        List<Map>  zhengcan_2=new ArrayList<>();   //正餐晚餐集合

        List<Map> jiacan_3=new ArrayList<>();      //加餐早餐集合
        List<Map> jiacan_4=new ArrayList<>();      //加餐中餐集合
        List<Map> jiacan_5=new ArrayList<>();      //加餐晚餐集合

        //迭代总的餐单菜品
        for (MenuDetailsVo menuDetailsVoItem : menuDetailsVoList) {

            //归类早中晚菜品数
            switch (menuDetailsVoItem.getMenuType()) {
                case "0":
                        Map breakfast=new HashMap();
                        breakfast.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                        breakfasttime=menuDetailsVoItem.getMealTime();
                        breakfast.put("dishesname",menuDetailsVoItem.getDishesName());
                        breakfast.put("breakfastcal",menuDetailsVoItem.getDishescalories());
                        breakfastcal+=menuDetailsVoItem.getDishescalories();
                        zhengcan_0.add(breakfast);
                    break;
                case "1":
                    Map lunch=new HashMap();
                    lunch.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    lunchtime=menuDetailsVoItem.getMealTime();
                    lunch.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunch.put("lunchcal",menuDetailsVoItem.getDishescalories());
                    lunchcal+=menuDetailsVoItem.getDishescalories();
                    zhengcan_1.add(lunch);
                    break;
                case "2":
                    Map dinner=new HashMap();
                    dinner.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    dinnertime=menuDetailsVoItem.getMealTime();
                    dinner.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinner.put("dinnercal",menuDetailsVoItem.getDishescalories());
                    dinnercal+=menuDetailsVoItem.getDishescalories();
                    zhengcan_2.add(dinner);
                    break;
                case "3":
                    Map breakfastSnacks=new HashMap();
                    breakfastSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    breakfastSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    breakfastSnacks.put("breakfastSnackscal",menuDetailsVoItem.getDishescalories());
                    breakfastSnackscal+=menuDetailsVoItem.getDishescalories();
                    jiacan_3.add(breakfastSnacks);
                    break;
                case "4":
                    Map lunchSnacks=new HashMap();
                    lunchSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    lunchSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunchSnacks.put("lunchsumcal",menuDetailsVoItem.getDishescalories());
                    lunchSnackscal+=menuDetailsVoItem.getDishescalories();
                    jiacan_4.add(lunchSnacks);
                    break;
                case "5":
                    Map dinnerSnacks=new HashMap();
                    dinnerSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    dinnerSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinnerSnacks.put("dinnerSnackscal",menuDetailsVoItem.getDishescalories());
                    dinnerSnackscal+=menuDetailsVoItem.getDishescalories();
                    jiacan_5.add(dinnerSnacks);
                    break;

            }

        }

        breakfastsumcal=breakfastcal+breakfastSnackscal;
        lunchsumcal=lunchSnackscal+lunchcal;
        dinnersumcal=dinnerSnackscal+dinnercal;

        ApiMenuDetaileVo apiMenuDetaileVo_1=new ApiMenuDetaileVo();
        apiMenuDetaileVo_1.setMenuType("0");
        apiMenuDetaileVo_1.setTime(breakfasttime);
        apiMenuDetaileVo_1.setSumcal(breakfastsumcal);
        apiMenuDetaileVo_1.setJiacan(jiacan_3);
        apiMenuDetaileVo_1.setZhengcan(zhengcan_0);
        ApiMenuDetaileVo apiMenuDetaileVo_2=new ApiMenuDetaileVo();
        apiMenuDetaileVo_2.setMenuType("1");
        apiMenuDetaileVo_2.setTime(lunchtime);
        apiMenuDetaileVo_2.setSumcal(lunchsumcal);
        apiMenuDetaileVo_2.setJiacan(jiacan_4);
        apiMenuDetaileVo_2.setZhengcan(zhengcan_1);
        ApiMenuDetaileVo apiMenuDetaileVo_3=new ApiMenuDetaileVo();
        apiMenuDetaileVo_3.setMenuType("2");
        apiMenuDetaileVo_3.setTime(dinnertime);
        apiMenuDetaileVo_3.setSumcal(dinnersumcal);
        apiMenuDetaileVo_3.setJiacan(jiacan_5);
        apiMenuDetaileVo_3.setZhengcan(zhengcan_2);

        apiMenuDetaileVos.add(apiMenuDetaileVo_1);
        apiMenuDetaileVos.add(apiMenuDetaileVo_2);
        apiMenuDetaileVos.add(apiMenuDetaileVo_3);

        return toResponsSuccess(apiMenuDetaileVos);
    }
}

