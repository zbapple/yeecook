package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.UserDetectionCycleDao;
import com.platform.entity.UserDetectionCycleEntity;
import com.platform.service.UserDetectionCycleService;

/**
 * 用户检测周期表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:17:10
 */
@Service("userDetectionCycleService")
public class UserDetectionCycleServiceImpl implements UserDetectionCycleService {
    @Autowired
    private UserDetectionCycleDao userDetectionCycleDao;

    @Override
    public UserDetectionCycleEntity queryObject(Integer id) {
        return userDetectionCycleDao.queryObject(id);
    }

    @Override
    public List<UserDetectionCycleEntity> queryList(Map<String, Object> map) {
        return userDetectionCycleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userDetectionCycleDao.queryTotal(map);
    }

    @Override
    public int save(UserDetectionCycleEntity userDetectionCycle) {
        return userDetectionCycleDao.save(userDetectionCycle);
    }

    @Override
    public int update(UserDetectionCycleEntity userDetectionCycle) {
        return userDetectionCycleDao.update(userDetectionCycle);
    }

    @Override
    public int delete(Integer id) {
        return userDetectionCycleDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userDetectionCycleDao.deleteBatch(ids);
    }
}
