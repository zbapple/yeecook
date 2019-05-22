package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NewNutrientsDao;
import com.platform.entity.NewNutrientsEntity;
import com.platform.service.NewNutrientsService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 14:49:30
 */
@Service("newNutrientsService")
public class NewNutrientsServiceImpl implements NewNutrientsService {
    @Autowired
    private NewNutrientsDao newNutrientsDao;

    @Override
    public NewNutrientsEntity queryObject(Integer id) {
        return newNutrientsDao.queryObject(id);
    }

    @Override
    public List<NewNutrientsEntity> queryList(Map<String, Object> map) {
        return newNutrientsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return newNutrientsDao.queryTotal(map);
    }

    @Override
    public int save(NewNutrientsEntity newNutrients) {
        return newNutrientsDao.save(newNutrients);
    }

    @Override
    public int update(NewNutrientsEntity newNutrients) {
        return newNutrientsDao.update(newNutrients);
    }

    @Override
    public int delete(Integer id) {
        return newNutrientsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return newNutrientsDao.deleteBatch(ids);
    }
}
