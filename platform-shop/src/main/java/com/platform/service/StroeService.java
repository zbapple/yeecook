package com.platform.service;

import com.platform.entity.StroeEntity;


import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-01 11:00:09
 */
public interface StroeService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    StroeEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<StroeEntity> queryList(Map<String, Object> map);

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
     * @param stroe 实体
     * @return 保存条数
     */
    int save(StroeEntity stroe);

    /**
     * 根据主键更新实体
     *
     * @param stroe 实体
     * @return 更新条数
     */
    int update(StroeEntity stroe);

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
     * @Param id
     **/
    int updateStatus(Integer id);
    /**
     * 门店恢复
     * @Param
     **/
    /**
     * 商品从回收站恢复
     *
     * @param ids
     * @return
     */
    int back(Integer[] ids);
    /**
     * 实际删除条数
     **/
    int deleteIs(Integer id);
    /**
     *
     * 实际批量删除
     **/
    int deleteAll(Integer[] ids);
}
