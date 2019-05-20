package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NewMenuDao;
import com.platform.entity.NewMenuEntity;
import com.platform.service.NewMenuService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
@Service("newMenuService")
public class NewMenuServiceImpl implements NewMenuService {
    @Autowired
    private NewMenuDao newMenuDao;

    @Override
    public NewMenuEntity queryObject(Integer id) {
        return newMenuDao.queryObject(id);
    }

    @Override
    public List<NewMenuEntity> queryList(Map<String, Object> map) {
        return newMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newMenuDao.queryTotal(map);
    }

    @Override
    public int save(NewMenuEntity newMenu) {
        return newMenuDao.save(newMenu);
    }

    @Override
    public int update(NewMenuEntity newMenu) {
        return newMenuDao.update(newMenu);
    }

    @Override
    public int delete(Integer id) {
        return newMenuDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return newMenuDao.deleteBatch(ids);
    }
}
