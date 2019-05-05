package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NewFoodDao;
import com.platform.entity.NewFoodEntity;
import com.platform.service.NewFoodService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@Service("newFoodService")
public class NewFoodServiceImpl implements NewFoodService {
    @Autowired
    private NewFoodDao newFoodDao;

    @Override
    public NewFoodEntity queryObject(Integer id) {
        return newFoodDao.queryObject(id);
    }

    @Override
    public List<NewFoodEntity> queryList(Map<String, Object> map) {
        return newFoodDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newFoodDao.queryTotal(map);
    }

    @Override
    public int save(NewFoodEntity newFood) {
        return newFoodDao.save(newFood);
    }

    @Override
    public int update(NewFoodEntity newFood) {
        return newFoodDao.update(newFood);
    }

    @Override
    public int delete(Integer id) {
        return newFoodDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return newFoodDao.deleteBatch(ids);
    }
}
