package com.platform.service;

import com.platform.dao.ApiUserNutritionMenuMapper;
import com.platform.entity.UserNutritionMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:59
 */
@Service
public class ApiUserNutritionMenuService  {
    @Autowired
    private ApiUserNutritionMenuMapper userNutritionMenuDao;


    public UserNutritionMenuVo queryObject(Integer id) {
        return userNutritionMenuDao.queryObject(id);
    }

    public List<UserNutritionMenuVo> queryList(Map<String, Object> map) {
        return userNutritionMenuDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return userNutritionMenuDao.queryTotal(map);
    }

    public int save(UserNutritionMenuVo userNutritionMenu) {
        return userNutritionMenuDao.save(userNutritionMenu);
    }

    public int update(UserNutritionMenuVo userNutritionMenu) {
        return userNutritionMenuDao.update(userNutritionMenu);
    }

    public int delete(Integer id) {
        return userNutritionMenuDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return userNutritionMenuDao.deleteBatch(ids);
    }
}
