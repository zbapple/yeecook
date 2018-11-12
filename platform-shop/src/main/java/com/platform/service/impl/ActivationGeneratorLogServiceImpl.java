package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ActivationGeneratorLogDao;
import com.platform.entity.ActivationGeneratorLogEntity;
import com.platform.service.ActivationGeneratorLogService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
@Service("activationGeneratorLogService")
public class ActivationGeneratorLogServiceImpl implements ActivationGeneratorLogService {
    @Autowired
    private ActivationGeneratorLogDao activationGeneratorLogDao;

    @Override
    public ActivationGeneratorLogEntity queryObject(Integer id) {
        return activationGeneratorLogDao.queryObject(id);
    }

    @Override
    public List<ActivationGeneratorLogEntity> queryList(Map<String, Object> map) {
        return activationGeneratorLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activationGeneratorLogDao.queryTotal(map);
    }

    @Override
    public int save(ActivationGeneratorLogEntity activationGeneratorLog) {
        return activationGeneratorLogDao.save(activationGeneratorLog);
    }

    @Override
    public int update(ActivationGeneratorLogEntity activationGeneratorLog) {
        return activationGeneratorLogDao.update(activationGeneratorLog);
    }

    @Override
    public int delete(Integer id) {
        return activationGeneratorLogDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return activationGeneratorLogDao.deleteBatch(ids);
    }
}
