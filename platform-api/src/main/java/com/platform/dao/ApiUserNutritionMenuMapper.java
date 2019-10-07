package com.platform.dao;
import com.platform.entity.UserNutritionMenuVo;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:59
 */
public interface ApiUserNutritionMenuMapper extends BaseDao<UserNutritionMenuVo> {
    List<UserNutritionMenuVo> querlistmenu(Map<String,Object> params);
    List<UserNutritionMenuVo> querlistmenuinfo(Map<String,Object> params);
}
