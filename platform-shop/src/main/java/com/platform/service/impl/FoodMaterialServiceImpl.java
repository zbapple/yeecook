package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FoodMaterialDao;
import com.platform.entity.FoodMaterialEntity;
import com.platform.service.FoodMaterialService;

/**
 * 食材表

Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:28:09
 */
@Service("foodMaterialService")
public class FoodMaterialServiceImpl implements FoodMaterialService {
    @Autowired
    private FoodMaterialDao foodMaterialDao;

    @Override
    public FoodMaterialEntity queryObject(Integer id) {
        return foodMaterialDao.queryObject(id);
    }

    @Override
    public List<FoodMaterialEntity> queryList(Map<String, Object> map) {
        return foodMaterialDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodMaterialDao.queryTotal(map);
    }

    @Override
    public int save(FoodMaterialEntity foodMaterial) {
        return foodMaterialDao.save(foodMaterial);
    }

    @Override
    public int update(FoodMaterialEntity foodMaterial) {
        return foodMaterialDao.update(foodMaterial);
    }

    @Override
    public int delete(Integer id) {
        return foodMaterialDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodMaterialDao.deleteBatch(ids);
    }
}
