package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SupplieInfoDao;
import com.platform.entity.SupplieInfoEntity;
import com.platform.service.SupplieInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@Service("supplieInfoService")
public class SupplieInfoServiceImpl implements SupplieInfoService {
    @Autowired
    private SupplieInfoDao supplieInfoDao;

    @Override
    public SupplieInfoEntity queryObject(Integer id) {
        return supplieInfoDao.queryObject(id);
    }

    @Override
    public List<SupplieInfoEntity> queryList(Map<String, Object> map) {
        return supplieInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return supplieInfoDao.queryTotal(map);
    }

    @Override
    public int save(SupplieInfoEntity supplieInfo) {
        return supplieInfoDao.save(supplieInfo);
    }

    @Override
    public int update(SupplieInfoEntity supplieInfo) {
        return supplieInfoDao.update(supplieInfo);
    }

    @Override
    public int delete(Integer id) {
        return supplieInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return supplieInfoDao.deleteBatch(ids);
    }
}
