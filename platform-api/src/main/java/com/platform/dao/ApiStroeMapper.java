package com.platform.dao;

import com.platform.entity.StroeVo;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-19 10:11:30
 */
public interface ApiStroeMapper extends BaseDao<StroeVo> {
    //排序
    List<StroeVo> querysort(Map<String,Object> params);
}
