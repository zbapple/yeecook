package com.platform.service;

import com.platform.entity.UserHealthReportEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户健康报告表
Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:14:48
 */
public interface UserHealthReportService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UserHealthReportEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UserHealthReportEntity> queryList(Map<String, Object> map);

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
     * @param userHealthReport 实体
     * @return 保存条数
     */
    int save(UserHealthReportEntity userHealthReport);

    /**
     * 根据主键更新实体
     *
     * @param userHealthReport 实体
     * @return 更新条数
     */
    int update(UserHealthReportEntity userHealthReport);

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
