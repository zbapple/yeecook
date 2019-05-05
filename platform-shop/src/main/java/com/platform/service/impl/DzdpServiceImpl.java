package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.DzdpDao;
import com.platform.entity.DzdpEntity;
import com.platform.service.DzdpService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-02-25 17:08:00
 */
@Service("dzdpService")
public class DzdpServiceImpl implements DzdpService {
    @Autowired
    private DzdpDao dzdpDao;

    @Override
    public DzdpEntity queryObject(Long id) {
        return dzdpDao.queryObject(id);
    }

    @Override
    public List<DzdpEntity> queryList(Map<String, Object> map) {
        return dzdpDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dzdpDao.queryTotal(map);
    }

    @Override
    public int save(DzdpEntity dzdp) {
        return dzdpDao.save(dzdp);
    }

    @Override
    public int update(DzdpEntity dzdp) {
        return dzdpDao.update(dzdp);
    }

    @Override
    public int delete(Long id) {
        return dzdpDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return dzdpDao.deleteBatch(ids);
    }
}
