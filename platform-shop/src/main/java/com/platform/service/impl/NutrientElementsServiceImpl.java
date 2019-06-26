package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.NutrientElementsDao;
import com.platform.entity.NutrientElementsEntity;
import com.platform.service.NutrientElementsService;

/**
 * 营养元素表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:22:12
 */
@Service("nutrientElementsService")
public class NutrientElementsServiceImpl implements NutrientElementsService {
    @Autowired
    private NutrientElementsDao nutrientElementsDao;

    @Override
    public NutrientElementsEntity queryObject(Integer id) {
        return nutrientElementsDao.queryObject(id);
    }

    @Override
    public List<NutrientElementsEntity> queryList(Map<String, Object> map) {
        return nutrientElementsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return nutrientElementsDao.queryTotal(map);
    }

    @Override
    public int save(NutrientElementsEntity nutrientElements) {
        return nutrientElementsDao.save(nutrientElements);
    }

    @Override
    public int update(NutrientElementsEntity nutrientElements) {
        return nutrientElementsDao.update(nutrientElements);
    }

    @Override
    public int delete(Integer id) {
        return nutrientElementsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return nutrientElementsDao.deleteBatch(ids);
    }
}
