package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.YfdsInfoDao;
import com.platform.entity.YfdsInfoEntity;
import com.platform.service.YfdsInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@Service("yfdsInfoService")
public class YfdsInfoServiceImpl implements YfdsInfoService {
    @Autowired
    private YfdsInfoDao yfdsInfoDao;

    @Override
    public YfdsInfoEntity queryObject(Integer id) {
        return yfdsInfoDao.queryObject(id);
    }

    @Override
    public List<YfdsInfoEntity> queryList(Map<String, Object> map) {
        return yfdsInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return yfdsInfoDao.queryTotal(map);
    }

    @Override
    public int save(YfdsInfoEntity yfdsInfo) {
        return yfdsInfoDao.save(yfdsInfo);
    }

    @Override
    public int update(YfdsInfoEntity yfdsInfo) {
        return yfdsInfoDao.update(yfdsInfo);
    }

    @Override
    public int delete(Integer id) {
        return yfdsInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return yfdsInfoDao.deleteBatch(ids);
    }
}
