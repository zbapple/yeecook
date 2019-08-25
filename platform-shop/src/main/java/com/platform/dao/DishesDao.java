package com.platform.dao;

import com.platform.entity.DishesEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜品表
Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:33:22
 */
public interface DishesDao extends BaseDao<DishesEntity> {
    //菜品类型
    List<DishesEntity> querylisttype(Map<String,Object> map);
}
