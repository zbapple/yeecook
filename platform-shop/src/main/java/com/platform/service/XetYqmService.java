package com.platform.service;

import com.platform.entity.XetYqmEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-18 13:21:53
 */
public interface XetYqmService {

    /**
     * 根据主键查询实体
     *
     * @param invitationCode
     * @return 实体
     */
    XetYqmEntity queryObject(String invitationCode);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<XetYqmEntity> queryList(Map<String, Object> map);

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
     * @param xetYqm 实体
     * @return 保存条数
     */
    int save(XetYqmEntity xetYqm);

    /**
     * 根据主键更新实体
     *
     * @param xetYqm 实体
     * @return 更新条数
     */
    int update(XetYqmEntity xetYqm);

    /**
     * 根据主键删除
     *
     * @param invitationCode
     * @return 删除条数
     */
    int delete(String invitationCode);

    /**
     * 根据主键批量删除
     *
     * @param invitationCodes
     * @return 删除条数
     */
    int deleteBatch(String[] invitationCodes);

    /**
     * 批量插入
     * @param optionList
     * @return
     */
    int addBatch(List<XetYqmEntity> optionList);

    List<XetYqmEntity> query1(Map<String, Object> map);

}
