package com.platform.service;

import com.platform.dao.ApiServeInfoMapper;
import com.platform.entity.ServeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:08
 */
@Service
public class ApiServeInfoService {
    @Autowired
    private ApiServeInfoMapper apiServeInfoMapper;


    public ServeInfoVo queryObject(Integer id) {
        return apiServeInfoMapper.queryObject(id);
    }


    public List<ServeInfoVo> queryList(Map<String, Object> map) {
        return apiServeInfoMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiServeInfoMapper.queryTotal(map);
    }


    public int save(ServeInfoVo serveInfo) {
        return apiServeInfoMapper.save(serveInfo);
    }


    public int update(ServeInfoVo serveInfo) {
        return apiServeInfoMapper.update(serveInfo);
    }


    public int delete(Integer id) {
        return apiServeInfoMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiServeInfoMapper.deleteBatch(ids);
    }
}
