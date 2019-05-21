package com.platform.service;

import com.platform.dao.ApiNewMenussMapper;
import com.platform.entity.ApiNewMenuListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-05-18 10:28:00
 */
@Service
public class ApiNewMenussService  {
    @Autowired
    private ApiNewMenussMapper newMenuListDao;
    public ApiNewMenuListVo queryObject(Integer id) {
        return newMenuListDao.queryObject(id);
    }
    public List<ApiNewMenuListVo> queryList(Map<String, Object> map) {
        return newMenuListDao.queryList(map);
    }
    public int queryTotal(Map<String, Object> map) {
        return newMenuListDao.queryTotal(map);
    }
    public int save(ApiNewMenuListVo newMenuList) {
        return newMenuListDao.save(newMenuList);
    }
    public int update(ApiNewMenuListVo newMenuList) {
        return newMenuListDao.update(newMenuList);
    }
    public int delete(Integer id) {
        return newMenuListDao.delete(id);
    }
    public int deleteBatch(Integer[] ids) {
        return newMenuListDao.deleteBatch(ids);
    }
}
