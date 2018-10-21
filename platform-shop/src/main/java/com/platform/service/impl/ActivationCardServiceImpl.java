package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ActivationCardDao;
import com.platform.entity.ActivationCardEntity;
import com.platform.service.ActivationCardService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
@Service("activationCardService")
public class ActivationCardServiceImpl implements ActivationCardService {
    @Autowired
    private ActivationCardDao activationCardDao;

    @Override
    public ActivationCardEntity queryObject(Integer id) {
        return activationCardDao.queryObject(id);
    }

    @Override
    public List<ActivationCardEntity> queryList(Map<String, Object> map) {
        return activationCardDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activationCardDao.queryTotal(map);
    }

    @Override
    public int save(ActivationCardEntity activationCard) {
        return activationCardDao.save(activationCard);
    }

    @Override
    public int update(ActivationCardEntity activationCard) {
        return activationCardDao.update(activationCard);
    }

    @Override
    public int delete(Integer id) {
        return activationCardDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return activationCardDao.deleteBatch(ids);
    }
}
