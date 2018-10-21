package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ActivationCodeLogDao;
import com.platform.entity.ActivationCodeLogEntity;
import com.platform.service.ActivationCodeLogService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
@Service("activationCodeLogService")
public class ActivationCodeLogServiceImpl implements ActivationCodeLogService {
    @Autowired
    private ActivationCodeLogDao activationCodeLogDao;

    @Override
    public ActivationCodeLogEntity queryObject(Integer id) {
        return activationCodeLogDao.queryObject(id);
    }

    @Override
    public List<ActivationCodeLogEntity> queryList(Map<String, Object> map) {
        return activationCodeLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activationCodeLogDao.queryTotal(map);
    }

    @Override
    public int save(ActivationCodeLogEntity activationCodeLog) {
        return activationCodeLogDao.save(activationCodeLog);
    }

    @Override
    public int update(ActivationCodeLogEntity activationCodeLog) {
        return activationCodeLogDao.update(activationCodeLog);
    }

    @Override
    public int delete(Integer id) {
        return activationCodeLogDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return activationCodeLogDao.deleteBatch(ids);
    }
}
