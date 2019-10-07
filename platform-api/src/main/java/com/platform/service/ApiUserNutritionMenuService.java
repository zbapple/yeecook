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
    //门店套餐
    public List<UserNutritionMenuVo> querlistmenu(Map<String, Object> map){
        return userNutritionMenuDao.querlistmenu(map);}
     //门店套餐详情
    public List<UserNutritionMenuVo> querlistmenuinfo(Map<String, Object> map){
        return userNutritionMenuDao.querlistmenuinfo(map);}

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
