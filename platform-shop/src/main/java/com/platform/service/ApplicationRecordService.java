package com.platform.service;

import com.platform.entity.ApplicationRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
public interface ApplicationRecordService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ApplicationRecordEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ApplicationRecordEntity> queryList(Map<String, Object> map);

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
     * @param applicationRecord 实体
     * @return 保存条数
     */
    int save(ApplicationRecordEntity applicationRecord);

    /**
     * 根据主键更新实体
     *
     * @param applicationRecord 实体
     * @return 更新条数
     */
    int update(ApplicationRecordEntity applicationRecord);

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
