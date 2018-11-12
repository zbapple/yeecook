package com.platform.service;

import com.platform.dao.ApiActivationCodeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.entity.ActivationCodeLogVo;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:07
 */
@Service
public class ApiActivationCodeLogService {
    @Autowired
    private ApiActivationCodeLogMapper apiActivationCodeLogMapper;

    
    public ActivationCodeLogVo queryObject(Integer id) {
        return apiActivationCodeLogMapper.queryObject(id);
    }


    public List<ActivationCodeLogVo> queryList(Map<String, Object> map) {
        return apiActivationCodeLogMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiActivationCodeLogMapper.queryTotal(map);
    }


    public int save(ActivationCodeLogVo activationCodeLog) {
        return apiActivationCodeLogMapper.save(activationCodeLog);
    }

    
    public int update(ActivationCodeLogVo activationCodeLog) {
        return apiActivationCodeLogMapper.update(activationCodeLog);
    }

    
    public int delete(Integer id) {
        return apiActivationCodeLogMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiActivationCodeLogMapper.deleteBatch(ids);
    }
}
