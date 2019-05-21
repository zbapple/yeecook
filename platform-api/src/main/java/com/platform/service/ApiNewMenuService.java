package com.platform.service;
import com.platform.dao.ApiNewMenuMapper;
import com.platform.entity.ApiNewMenuVo;
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
public class ApiNewMenuService {
    @Autowired
    private ApiNewMenuMapper newMenuDao;

    public ApiNewMenuVo queryObject(Integer id) {
        return newMenuDao.queryObject(id);
    }
    public List<ApiNewMenuVo> queryList(Map<String, Object> map) {
        return newMenuDao.queryList(map);
    }
    public int queryTotal(Map<String, Object> map) {
        return newMenuDao.queryTotal(map);
    }
    public int save(ApiNewMenuVo newMenu) {
        return newMenuDao.save(newMenu);
    }
    public int update(ApiNewMenuVo newMenu) {
        return newMenuDao.update(newMenu);
    }
    public int delete(Integer id) {
        return newMenuDao.delete(id);
    }
    public int deleteBatch(Integer[] ids) {
        return newMenuDao.deleteBatch(ids);
    }
}