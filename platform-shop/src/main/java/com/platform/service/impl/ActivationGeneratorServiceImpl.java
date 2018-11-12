package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ActivationGeneratorDao;
import com.platform.entity.ActivationGeneratorEntity;
import com.platform.service.ActivationGeneratorService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
@Service("activationGeneratorService")
public class ActivationGeneratorServiceImpl implements ActivationGeneratorService {
    @Autowired
    private ActivationGeneratorDao activationGeneratorDao;

    @Override
    public ActivationGeneratorEntity queryObject(Integer id) {
        return activationGeneratorDao.queryObject(id);
    }

    @Override
    public List<ActivationGeneratorEntity> queryList(Map<String, Object> map) {
        return activationGeneratorDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activationGeneratorDao.queryTotal(map);
    }

    @Override
    public int save(ActivationGeneratorEntity activationGenerator) {
        return activationGeneratorDao.save(activationGenerator);
    }

    @Override
    public int update(ActivationGeneratorEntity activationGenerator) {
        return activationGeneratorDao.update(activationGenerator);
    }

    @Override
    public int delete(Integer id) {
        return activationGeneratorDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return activationGeneratorDao.deleteBatch(ids);
    }
}
