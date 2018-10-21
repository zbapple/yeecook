package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ServeTypeDao;
import com.platform.entity.ServeTypeEntity;
import com.platform.service.ServeTypeService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-21 01:13:48
 */
@Service("serveTypeService")
public class ServeTypeServiceImpl implements ServeTypeService {
    @Autowired
    private ServeTypeDao serveTypeDao;

    @Override
    public ServeTypeEntity queryObject(Integer id) {
        return serveTypeDao.queryObject(id);
    }

    @Override
    public List<ServeTypeEntity> queryList(Map<String, Object> map) {
        return serveTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return serveTypeDao.queryTotal(map);
    }

    @Override
    public int save(ServeTypeEntity serveType) {
        return serveTypeDao.save(serveType);
    }

    @Override
    public int update(ServeTypeEntity serveType) {
        return serveTypeDao.update(serveType);
    }

    @Override
    public int delete(Integer id) {
        return serveTypeDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return serveTypeDao.deleteBatch(ids);
    }
}
