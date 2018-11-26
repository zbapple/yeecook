package com.platform.service;

import com.platform.dao.ApiXetYqmMapper;
import com.platform.entity.XetYqmVo;
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
public class ApiXetYqmService {
    @Autowired
    private ApiXetYqmMapper apiXetYqmMapper;


    public XetYqmVo queryObject(Integer id) {
        return apiXetYqmMapper.queryObject(id);
    }


    public List<XetYqmVo> queryList(Map<String, Object> map) {
        return apiXetYqmMapper.queryList(map);
    }

    public List<XetYqmVo> query1(Map<String, Object> map) {
        return apiXetYqmMapper.query1(map);
    }

    public int update(XetYqmVo xetYqmVo) {
        return apiXetYqmMapper.update(xetYqmVo);
    }


}
