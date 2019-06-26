package com.platform.dao;

import com.platform.entity.MenuPlanEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 用户膳食计划Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-19 19:14:23
 */
public interface MenuPlanDao extends BaseDao<MenuPlanEntity> {

     MenuPlanEntity queryObjectA(Object id);
     List<MenuPlanEntity> queryListA(Map<String, Object> params);
     int queryTotalA(Map<String, Object> params);
}
