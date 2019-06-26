package com.platform.service;

import com.platform.entity.UserBodyInformationEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户身体信息表
Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:20:43
 */
public interface UserBodyInformationService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UserBodyInformationEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UserBodyInformationEntity> queryList(Map<String, Object> map);

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
     * @param userBodyInformation 实体
     * @return 保存条数
     */
    int save(UserBodyInformationEntity userBodyInformation);

    /**
     * 根据主键更新实体
     *
     * @param userBodyInformation 实体
     * @return 更新条数
     */
    int update(UserBodyInformationEntity userBodyInformation);

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
