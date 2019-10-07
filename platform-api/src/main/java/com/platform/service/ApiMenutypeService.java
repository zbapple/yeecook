package com.platform.service;

import com.platform.dao.ApiMenutypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.platform.entity.MenutypeEntity;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-27 16:50:29
 */
@Service
public class ApiMenutypeService  {
    @Autowired
    private ApiMenutypeMapper menutypeDao;

    public MenutypeEntity queryObject(Integer id) {
        return menutypeDao.queryObject(id);
    }

    public List<MenutypeEntity> queryList(Map<String, Object> map) {
        return menutypeDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return menutypeDao.queryTotal(map);
    }

    public int save(MenutypeEntity menutype) {
        return menutypeDao.save(menutype);
    }

    public int update(MenutypeEntity menutype) {
        return menutypeDao.update(menutype);
    }

    public int delete(Integer id) {
        return menutypeDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return menutypeDao.deleteBatch(ids);
    }
}
