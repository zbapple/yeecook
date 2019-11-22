package com.platform.service;

import com.platform.dao.ApiStroeTypeMapper;
import com.platform.entity.StroeTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-11-04 11:04:11
 */
@Service
public class ApiStroeTypeService  {
    @Autowired
    private ApiStroeTypeMapper stroeTypeDao;

    public StroeTypeVo queryObject(Integer id) {
        return stroeTypeDao.queryObject(id);
    }

    public List<StroeTypeVo> queryList(Map<String, Object> map) {
        return stroeTypeDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return stroeTypeDao.queryTotal(map);
    }

    public int save(StroeTypeVo stroeType) {
        return stroeTypeDao.save(stroeType);
    }

    public int update(StroeTypeVo stroeType) {
        return stroeTypeDao.update(stroeType);
    }

    public int delete(Integer id) {
        return stroeTypeDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return stroeTypeDao.deleteBatch(ids);
    }
}
