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
    public Object menuinfo(Date today) {
        UserVo loginUser = new UserVo();
        Map<String, Object> result = new HashMap<>();
        JSONObject dayjsonparam = getJsonRequest();
        Long userid = loginUser.getUserId();
        today = dayjsonparam.getDate("today");
        Map dayfoodmap = new HashMap();
        dayfoodmap.put("userid", userid);
        dayfoodmap.put("today", today);
        List<MenuDetailsVo> userlist = menuDetailsService.queryList(dayfoodmap);
        String namegood = null;
        String nameluch = null;
        String nameeve = null;
        Double countgood = 0.0;
        Double sumcalluch = 0.0;
        Double couteve = 0.0;
        Double sum = 0.0;
        if (userlist != null) {
            for (MenuDetailsVo MenuDetailsVoItem : userlist) {
                if (MenuDetailsVoItem.getMenuType().equals("0") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    countgood += MenuDetailsVoItem.getDishescalories();
                    namegood = MenuDetailsVoItem.getDishesName();
                } else if (MenuDetailsVoItem.getMenuType().equals("1") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    sumcalluch += MenuDetailsVoItem.getDishescalories();
                    nameluch = MenuDetailsVoItem.getDishesName();
                } else if (MenuDetailsVoItem.getMenuType().equals("2") && userid.equals(MenuDetailsVoItem.getNideshopUserid())) {
                    couteve += MenuDetailsVoItem.getDishescalories();
                    nameeve = MenuDetailsVoItem.getDishesName();
                }
            }
            sum = countgood + sumcalluch + couteve;
            String[] namemap = new String[]{namegood};
            String[] namemap1 = new String[]{nameluch};
            String[] namemap2 = new String[]{nameeve};
            result.put("namemap", namemap);
            result.put("countgood", countgood);
            result.put("namemap1", namemap1);
            result.put("sumcalluch", sumcalluch);
            result.put("namemap2", namemap2);
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
    public Object todayinfo(Date todays, @LoginUser UserVo lginUser) {
        Map<String, Object> result = new HashMap<>();
        JSONObject todayinfojson = this.getJsonRequest();
        todays = todayinfojson.getDate("today");
        Long nideshopUserid = lginUser.getUserId();
        Map todaymap = new HashMap();
        todaymap.put("nideshopUserid", nideshopUserid);
        todaymap.put("todays", todays);
        List<MenuDetailsVo> menuDetailsVoList = menuDetailsService.querListvo(todaymap);
        List<ApiMenuDetaileVo> apiMenuDetaileVos = new ArrayList<>();
        Double breakfastcal=0.0;
        Double lunchcal=0.0;
        Double dinnercal=0.0;
        Date breakfasttime;
        Date lunchtime;
        Date dinnertime;
        Double breakfastSnackscal=0.0;
        Double dinnerSnackscal=0.0;
        Double lunchSnackscal=0.0;
        Double breakfastsumcal=0.0;
        Double lunchsumcal=0.0;
        Double dinnersumcal=0.0;
        //迭代总的餐单菜品
        for (MenuDetailsVo menuDetailsVoItem : menuDetailsVoList) {
            //归类早中晚菜品数据
            switch (menuDetailsVoItem.getMenuType()) {
                case "0":
                        Map breakfast=new HashMap();
                        breakfast.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                        breakfasttime=menuDetailsVoItem.getMealTime();
                        breakfast.put("dishesname",menuDetailsVoItem.getDishesName());
                        breakfast.put("breakfastcal",menuDetailsVoItem.getDishescalories());
                        breakfastcal+=menuDetailsVoItem.getDishescalories();
                    break;
                case "1":
                    Map lunch=new HashMap();
                    lunch.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    lunchtime=menuDetailsVoItem.getMealTime();
                    lunch.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunch.put("lunchcal",menuDetailsVoItem.getDishescalories());
                    lunchcal+=menuDetailsVoItem.getDishescalories();
                    break;
                case "2":
                    Map dinner=new HashMap();
                    dinner.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    dinnertime=menuDetailsVoItem.getMealTime();
                    dinner.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinner.put("dinnercal",menuDetailsVoItem.getDishescalories());
                    dinnercal+=menuDetailsVoItem.getDishescalories();
                    break;
                case "3":
                    Map breakfastSnacks=new HashMap();
                    breakfastSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    breakfastSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    breakfastSnacks.put("breakfastSnackscal",menuDetailsVoItem.getDishescalories());
                    breakfastSnackscal+=menuDetailsVoItem.getDishescalories();
                    break;
                case "4":
                    Map lunchSnacks=new HashMap();
                    lunchSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    lunchSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    lunchSnacks.put("lunchsumcal",menuDetailsVoItem.getDishescalories());
                    lunchsumcal+=menuDetailsVoItem.getDishescalories();
                    break;
                case "5":
                    Map dinnerSnacks=new HashMap();
                    dinnerSnacks.put("dishescoverpic",menuDetailsVoItem.getDishescoverpic());
                    dinnerSnacks.put("dishesname",menuDetailsVoItem.getDishesName());
                    dinnerSnacks.put("dinnerSnackscal",menuDetailsVoItem.getDishescalories());
                    dinnerSnackscal+=menuDetailsVoItem.getDishescalories();
                    break;

            }
            breakfastsumcal=breakfastcal+breakfastSnackscal;
            lunchsumcal=lunchSnackscal+lunchcal;
            dinnersumcal=dinnerSnackscal+dinnercal;
            if(null!=menuDetailsVoItem.getLeafNode() && menuDetailsVoItem.getLeafNode() ==1 ){
                ApiMenuDetaileVo apiMenuDetaileVo=new ApiMenuDetaileVo();
                apiMenuDetaileVo.setSumcal(breakfastsumcal);
                apiMenuDetaileVo.setZaocantime(apiMenuDetaileVo.getZaocantime());
                    for(MenuDetailsVo menuDetailsVo : menuDetailsVoList){
                        if(menuDetailsVoItem.getId()==menuDetailsVo.getFatherId()){
                            HashMap jiacan=new HashMap();
                            jiacan.put("dishesname",menuDetailsVo.getDishesName());
                            jiacan.put("dishescalories",menuDetailsVo.getDishescalories());

                        }
            }
            }
        }
        return toResponsSuccess(apiMenuDetaileVos);
    }
}

