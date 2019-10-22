package com.platform.dao;

import com.platform.entity.MenuDetailsEntity;

import java.util.List;
import java.util.Map;

/**
 * 餐单详情表
Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:23:22
 */
public interface MenuDetailsDao extends BaseDao<MenuDetailsEntity> {
    List<MenuDetailsEntity> queryListvo(Map<String,Object> params);
}
