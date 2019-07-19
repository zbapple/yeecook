package com.platform.service;

import com.platform.entity.MenuPlanEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户膳食计划Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-19 19:14:23
 */
public interface MenuPlanService {

    /**
     *  根据主键查询详情集合
     **/
    MenuPlanEntity queryMenu(Integer id);

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    MenuPlanEntity queryObject(Integer id);
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<MenuPlanEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param menuPlan 实体
     * @return 保存条数
     */
    int save(MenuPlanEntity menuPlan);

    /**
     * 根据主键更新实体
     * @return 更新条数
     */
    int update(Integer id);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);

    /**
     *
     * @Param
     **/
     void updateinfo(MenuPlanEntity menuPlan);


}
