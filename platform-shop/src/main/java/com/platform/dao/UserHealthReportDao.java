package com.platform.dao;

import com.platform.entity.UserHealthReportEntity;

/**
 * 用户健康报告表
Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:14:48
 */
public interface UserHealthReportDao extends BaseDao<UserHealthReportEntity> {
    UserHealthReportEntity queryWeight(Integer id);

}
