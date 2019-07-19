package com.platform.service;

import com.platform.entity.UserDetectionCycleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户检测周期表
Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:17:10
 */
public interface UserDetectionCycleService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UserDetectionCycleEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UserDetectionCycleEntity> queryList(Map<String, Object> map);

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
     * @param userDetectionCycle 实体
     * @return 保存条数
     */
    int save(UserDetectionCycleEntity userDetectionCycle);

    /**
     * 根据主键更新实体
     *
     * @param userDetectionCycle 实体
     * @return 更新条数
     */
    int update(UserDetectionCycleEntity userDetectionCycle);

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
