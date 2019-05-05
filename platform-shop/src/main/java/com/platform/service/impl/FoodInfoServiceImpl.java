package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.FoodInfoDao;
import com.platform.entity.FoodInfoEntity;
import com.platform.service.FoodInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@Service("foodInfoService")
public class FoodInfoServiceImpl implements FoodInfoService {
    @Autowired
    private FoodInfoDao foodInfoDao;

    @Override
    public FoodInfoEntity queryObject(Integer id) {
        return foodInfoDao.queryObject(id);
    }

    @Override
    public List<FoodInfoEntity> queryList(Map<String, Object> map) {
        return foodInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return foodInfoDao.queryTotal(map);
    }

    @Override
    public int save(FoodInfoEntity foodInfo) {
        return foodInfoDao.save(foodInfo);
    }

    @Override
    public int update(FoodInfoEntity foodInfo) {
        return foodInfoDao.update(foodInfo);
    }

    @Override
    public int delete(Integer id) {
        return foodInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return foodInfoDao.deleteBatch(ids);
    }
}
