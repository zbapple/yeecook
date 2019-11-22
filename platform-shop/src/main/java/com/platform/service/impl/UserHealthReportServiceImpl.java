package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.UserHealthReportDao;
import com.platform.entity.UserHealthReportEntity;
import com.platform.service.UserHealthReportService;

/**
 * 用户健康报告表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:14:48
 */
@Service("userHealthReportService")
public class UserHealthReportServiceImpl implements UserHealthReportService {

    @Autowired
    private UserHealthReportDao userHealthReportDao;
    @Override
    public UserHealthReportEntity queryObject(Integer id) {
        return userHealthReportDao.queryObject(id);
    }

    @Override
    public List<UserHealthReportEntity> queryList(Map<String, Object> map) {
        return userHealthReportDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userHealthReportDao.queryTotal(map);
    }

    @Override
    public int save(UserHealthReportEntity userHealthReport) {
        return userHealthReportDao.save(userHealthReport);
    }

    @Override
    public int update(UserHealthReportEntity userHealthReport) {
        return userHealthReportDao.update(userHealthReport);
    }

    @Override
    public int delete(Integer id) {
        return userHealthReportDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userHealthReportDao.deleteBatch(ids);
    }

    @Override
    public UserHealthReportEntity queryWeight(Integer id) {
        Object weight=userHealthReportDao.queryWeight(id);
        if (weight==null){
            return null;
        }
        return userHealthReportDao.queryWeight(id);
    }

    @Override
    public UserHealthReportEntity queryUserReport(Integer id) {
        UserHealthReportEntity userHealthReportEntity=userHealthReportDao.queryUserReport(id);
        if (userHealthReportEntity == null){
            return null;
        }
        return userHealthReportDao.queryUserReport(id);
    }

}
