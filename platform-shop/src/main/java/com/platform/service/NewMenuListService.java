package com.platform.service;

import com.platform.entity.NewMenuListEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
public interface NewMenuListService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    NewMenuListEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<NewMenuListEntity> queryList(Map<String, Object> map);

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
     * @param newMenuList 实体
     * @return 保存条数
     */
    int save(NewMenuListEntity newMenuList);

    /**
     * 根据主键更新实体
     *
     * @param newMenuList 实体
     * @return 更新条数
     */
    int update(NewMenuListEntity newMenuList);

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
}
