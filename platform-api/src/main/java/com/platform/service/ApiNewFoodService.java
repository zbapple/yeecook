package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiNewFoodMapper;
import com.platform.entity.ApiNewFoodVo;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:59:56
 */
@Service
public class ApiNewFoodService {
    @Autowired
    private ApiNewFoodMapper newFoodDao;

    
    public ApiNewFoodVo queryObject(Integer id) {
        return newFoodDao.queryObject(id);
    }


    public List<ApiNewFoodVo> queryList(Map<String, Object> map) {
        return newFoodDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return newFoodDao.queryTotal(map);
    }

    public int save(ApiNewFoodVo newFood) {
        return newFoodDao.save(newFood);
    }


    public int update(ApiNewFoodVo newFood) {
        return newFoodDao.update(newFood);
    }


    public int delete(Integer id) {
        return newFoodDao.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return newFoodDao.deleteBatch(ids);
    }
}
