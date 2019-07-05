package com.platform.dao;
import com.platform.entity.MenuDetailsVo;

import java.util.List;
import java.util.Map;


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
 餐单日期Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:55
 */
public interface ApiMenuDetailsMapper extends BaseDao<MenuDetailsVo> {
    List<MenuDetailsVo> querListvo(Map<String,Object> params);
}
