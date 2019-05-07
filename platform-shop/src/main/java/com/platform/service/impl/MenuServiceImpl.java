package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.MenuDao;
import com.platform.entity.MenuEntity;
import com.platform.service.MenuService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-07 23:34:58
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public MenuEntity queryObject(Integer id) {
        return menuDao.queryObject(id);
    }

    @Override
    public List<MenuEntity> queryList(Map<String, Object> map) {
        return menuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return menuDao.queryTotal(map);
    }

    @Override
    public int save(MenuEntity menu) {
        return menuDao.save(menu);
    }

    @Override
    public int update(MenuEntity menu) {
        return menuDao.update(menu);
    }

    @Override
    public int delete(Integer id) {
        return menuDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return menuDao.deleteBatch(ids);
    }
}
