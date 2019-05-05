package com.platform.dao;

import com.platform.entity.XetYqmEntity;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-18 13:21:53
 */
public interface XetYqmDao extends BaseDao<XetYqmEntity> {
    /**
     * 批量插入
     * @param optionList
     * @return
     */
    int addBatch(List<XetYqmEntity> optionList);

     List<XetYqmEntity> query1(Map<String, Object> map);
}
