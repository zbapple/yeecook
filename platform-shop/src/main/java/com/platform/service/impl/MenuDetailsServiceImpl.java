package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.MenuDetailsDao;
import com.platform.entity.MenuDetailsEntity;
import com.platform.service.MenuDetailsService;

/**
 * 餐单详情表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:23:22
 */
@Service("menuDetailsService")
public class MenuDetailsServiceImpl implements MenuDetailsService {
    @Autowired
    private MenuDetailsDao menuDetailsDao;

    @Override
    public List<MenuDetailsEntity> queryListvo(Map<String, Object> map) {
       return menuDetailsDao.queryListvo(map); }

    @Override
    public MenuDetailsEntity queryObject(Integer id) {
        return menuDetailsDao.queryObject(id);
    }

    @Override
    public List<MenuDetailsEntity> queryList(Map<String, Object> map) {
        return menuDetailsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuDetailsDao.queryTotal(map);
    }

    @Override
    public int save(MenuDetailsEntity menuDetails) {
        return menuDetailsDao.save(menuDetails);
    }

    @Override
    public int update(MenuDetailsEntity menuDetails) {
        return menuDetailsDao.update(menuDetails);
    }

    @Override
    public int delete(Integer id) {
        return menuDetailsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return menuDetailsDao.deleteBatch(ids);
    }
}
