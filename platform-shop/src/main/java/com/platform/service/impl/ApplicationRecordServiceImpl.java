package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApplicationRecordDao;
import com.platform.entity.ApplicationRecordEntity;
import com.platform.service.ApplicationRecordService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
@Service("applicationRecordService")
public class ApplicationRecordServiceImpl implements ApplicationRecordService {
    @Autowired
    private ApplicationRecordDao applicationRecordDao;

    @Override
    public ApplicationRecordEntity queryObject(Integer id) {
        return applicationRecordDao.queryObject(id);
    }

    @Override
    public List<ApplicationRecordEntity> queryList(Map<String, Object> map) {
        return applicationRecordDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return applicationRecordDao.queryTotal(map);
    }

    @Override
    public int save(ApplicationRecordEntity applicationRecord) {
        return applicationRecordDao.save(applicationRecord);
    }

    @Override
    public int update(ApplicationRecordEntity applicationRecord) {
        return applicationRecordDao.update(applicationRecord);
    }

    @Override
    public int delete(Integer id) {
        return applicationRecordDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return applicationRecordDao.deleteBatch(ids);
    }
}
