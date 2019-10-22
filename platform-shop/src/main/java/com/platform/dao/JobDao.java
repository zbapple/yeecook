package com.platform.dao;

import com.platform.entity.JobEntity;

import java.util.List;

/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-11 10:36:32
 */
public interface JobDao extends BaseDao<JobEntity> {
    List<JobEntity> queryMessage();
}
