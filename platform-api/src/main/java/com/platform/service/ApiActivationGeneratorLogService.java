package com.platform.service;

import com.platform.dao.ApiActivationGeneratorLogMapper;
import com.platform.entity.ActivationGeneratorLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-10-23 14:50:07
 */
@Service
public class ApiActivationGeneratorLogService {
    @Autowired
    private ApiActivationGeneratorLogMapper apiActivationGeneratorLogMapper;


    public ActivationGeneratorLogVo queryObject(Integer id) {
        return apiActivationGeneratorLogMapper.queryObject(id);
    }


    public List<ActivationGeneratorLogVo> queryList(Map<String, Object> map) {
        return apiActivationGeneratorLogMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiActivationGeneratorLogMapper.queryTotal(map);
    }


    public int save(ActivationGeneratorLogVo activationGeneratorLog) {
        return apiActivationGeneratorLogMapper.save(activationGeneratorLog);
    }


    public int update(ActivationGeneratorLogVo activationGeneratorLog) {
        return apiActivationGeneratorLogMapper.update(activationGeneratorLog);
    }


    public int delete(Integer id) {
        return apiActivationGeneratorLogMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiActivationGeneratorLogMapper.deleteBatch(ids);
    }
}
