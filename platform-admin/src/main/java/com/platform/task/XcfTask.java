package com.platform.task;

import com.platform.charles.xcf.Courses;
import com.platform.charles.xcf.JsonRootBean;
import com.platform.charles.xcf.XcfCharlesInfoEntity;
import com.platform.service.XcfCharlesInfoService;
import com.platform.charles.xcf.XcfCharles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("xcfTask")
public class XcfTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private XcfCharlesInfoService xcfCharlesInfoService;

    public void run(String params) {
        logger.info("正在抓取下厨房数据，正在被执行，参数为：" + params);
        XcfCharles xcfCharles=new XcfCharles();
       List<JsonRootBean> list= xcfCharles.getData();

        Date date = new Date();
        Calendar cale = Calendar.getInstance();
        cale.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天
        cale.setTime(date);
        int weeks = cale.get(Calendar.WEEK_OF_YEAR);
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        List<XcfCharlesInfoEntity> xcfCharlesInfoEntities=new ArrayList();
        Map mapQuery=new HashMap();
        mapQuery.put("years",year);
        mapQuery.put("month",month);
        mapQuery.put("day",day);
        boolean isUpdate =xcfCharlesInfoService.queryTotal(mapQuery)>0;

         for (JsonRootBean jsonRootBean:list){

             if(jsonRootBean.getStatus().equals("ok")){
                 for (Courses courses:jsonRootBean.getContent().getCourses()){
                     XcfCharlesInfoEntity xcfCharlesInfoEntity=new XcfCharlesInfoEntity();
                     xcfCharlesInfoEntity.setId(Long.valueOf(courses.getId()));
                     xcfCharlesInfoEntity.setAddtime(date);
                     xcfCharlesInfoEntity.setCourse(courses.getName());
                     xcfCharlesInfoEntity.setSales(Long.valueOf(courses.getTotal_sales_volume()));
                     xcfCharlesInfoEntity.setLecturer(courses.getLecturer().getName());
                     xcfCharlesInfoEntity.setPrice(courses.getKinds().get(0).getPrice()+"");
                     xcfCharlesInfoEntity.setYears(year);
                     xcfCharlesInfoEntity.setMonth(month);
                     xcfCharlesInfoEntity.setDay(day);
                     xcfCharlesInfoEntity.setWeeks(weeks);
                     xcfCharlesInfoEntities.add(xcfCharlesInfoEntity);
                 }
                 if(isUpdate){
                     xcfCharlesInfoService.updateBatch(year,month,day,xcfCharlesInfoEntities);
                 }else {
                     xcfCharlesInfoService.saveBatch(xcfCharlesInfoEntities);
                 }
                 xcfCharlesInfoEntities.clear();
             }
         }
    }

    public void test2() {
        logger.info("我是不带参数的test2方法，正在被执行");
    }
}
