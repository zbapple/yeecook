package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ServeInfoDao;
import com.platform.entity.ServeInfoEntity;
import com.platform.service.ServeInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-28 00:48:59
 */
@Service("serveInfoService")
public class ServeInfoServiceImpl implements ServeInfoService {
    @Autowired
    private ServeInfoDao serveInfoDao;

    @Override
    public ServeInfoEntity queryObject(Integer id) {
        return serveInfoDao.queryObject(id);
    }

    @Override
    public List<ServeInfoEntity> queryList(Map<String, Object> map) {
        return serveInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return serveInfoDao.queryTotal(map);
    }

    @Override
    public int save(ServeInfoEntity serveInfo) {
        return serveInfoDao.save(serveInfo);
    }

    @Override
    public int update(ServeInfoEntity serveInfo) {
        return serveInfoDao.update(serveInfo);
    }

    @Override
    public int delete(Integer id) {
        return serveInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return serveInfoDao.deleteBatch(ids);
    }
}
