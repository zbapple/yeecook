package com.platform.service;

import com.platform.dao.ApiServeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.entity.ServeTypeVo;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:08
 */
@Service
public class ApiServeTypeService {
    @Autowired
    private ApiServeTypeMapper apiServeTypeMapper;


    public ServeTypeVo queryObject(Integer id) {
        return apiServeTypeMapper.queryObject(id);
    }


    public List<ServeTypeVo> queryList(Map<String, Object> map) {
        return apiServeTypeMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiServeTypeMapper.queryTotal(map);
    }


    public int save(ServeTypeVo serveType) {
        return apiServeTypeMapper.save(serveType);
    }


    public int update(ServeTypeVo serveType) {
        return apiServeTypeMapper.update(serveType);
    }


    public int delete(Integer id) {
        return apiServeTypeMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiServeTypeMapper.deleteBatch(ids);
    }
}
