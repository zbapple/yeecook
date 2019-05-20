package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NewMenuListDao;
import com.platform.entity.NewMenuListEntity;
import com.platform.service.NewMenuListService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
@Service("newMenuListService")
public class NewMenuListServiceImpl implements NewMenuListService {
    @Autowired
    private NewMenuListDao newMenuListDao;

    @Override
    public NewMenuListEntity queryObject(Integer id) {
        return newMenuListDao.queryObject(id);
    }

    @Override
    public List<NewMenuListEntity> queryList(Map<String, Object> map) {
        return newMenuListDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newMenuListDao.queryTotal(map);
    }

    @Override
    public int save(NewMenuListEntity newMenuList) {
        return newMenuListDao.save(newMenuList);
    }

    @Override
    public int update(NewMenuListEntity newMenuList) {
        return newMenuListDao.update(newMenuList);
    }

    @Override
    public int delete(Integer id) {
        return newMenuListDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return newMenuListDao.deleteBatch(ids);
    }
}
