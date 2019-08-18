package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.dao.UserNutritionMenuDao;
import com.platform.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户膳食计划实体
 * 表名 menu_plan
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-19 19:14:23
 */
@Data
public class MenuPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //用户id
    private Integer nideshopUserId;
    //餐单编号
    private String menuSn;
    //用户名称
    private String userName;
    //用户昵称
    private String nickName;
    //用户头像
    private String avatar;
    //主表食谱类型
    private String nutritionMenuType;
    //计划开始时间
    private Date serviceCycleSt;
    //机构id
    private Integer cateringServiceOrgId;
    //机构名称
    private String cateringServiceOrgName;
    //食谱名称
    private String menuName;
    //餐单封面图片
    private String menuCoverPic;
    //计划结束时间
    private Date serviceCycleEt;
    //服务阶段
    private String serviceStage;
    //0:未签约 1:已签约
    private Long deptId;
    private Integer menuStatus = 0;
    //检测周期
    private String inspectionCycle;
    //初始体重
    private Double weight;
    //食谱类型
    private String menuType;
    //菜品id
    private Integer dishesId;
    //菜品名称
    private String dishesName;
    //菜品描述
    private String dishesDescribe;
    //菜品图片
    private String dishesCoverPic;
    //菜品卡路里
    private String dishesCalories;
    //营养原理
    private String nutritionPrinciple;
    //餐单生成时间
    private Date menuDate;
    //用餐时间
    private Date mealTime;
    //新建餐单
    private List<MenuDetailsEntity> foodlist;
    private List<MenuDetailsEntity> foodlistadd;
    private List<MenuDetailsEntity> foodlist1;
    private List<MenuDetailsEntity> foodlistadd1;
    private List<MenuDetailsEntity> foodlist2;
    private List<MenuDetailsEntity> foodlistadd2;
    private List<MenuPlanEntity> menuCoverPics;


    public void setServiceCycleSt(Date serviceCycleSt) {
        this.serviceCycleSt = serviceCycleSt;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getServiceCycleSt() {
        return serviceCycleSt;
    }

    public void setServiceCycleEt(Date serviceCycleEt) {
        this.serviceCycleEt = serviceCycleEt;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getServiceCycleEt() {
        return serviceCycleEt;
    }



    /**
     * 自定义餐单编码格式  服务机构id+年月日+餐单id
     **/
    public static String getNewSn(){
        Calendar cl=Calendar.getInstance();
        cl.setTime(new Date());
        String strdate = DateUtils.format(cl.getTime(), DateUtils.DATE_PATTERN);
        MenuPlanEntity menuPlanEntity=new MenuPlanEntity();
        Integer cid=menuPlanEntity.getCateringServiceOrgId();
        Integer id=menuPlanEntity.getId();
        String str=cid+strdate+id;
        menuPlanEntity.setMenuSn(str);
        return str;
    }

}