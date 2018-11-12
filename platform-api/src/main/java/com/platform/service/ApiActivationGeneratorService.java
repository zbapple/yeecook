package com.platform.service;

import com.platform.dao.ApiActivationGeneratorMapper;
import com.platform.entity.ActivationGeneratorVo;
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
public class ApiActivationGeneratorService {
    @Autowired
    private ApiActivationGeneratorMapper apiActivationGeneratorMapper;


    public ActivationGeneratorVo queryObject(Integer id) {
        return apiActivationGeneratorMapper.queryObject(id);
    }


    public List<ActivationGeneratorVo> queryList(Map<String, Object> map) {
        return apiActivationGeneratorMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiActivationGeneratorMapper.queryTotal(map);
    }


    public int save(ActivationGeneratorVo activationGenerator) {
        return apiActivationGeneratorMapper.save(activationGenerator);
    }


    public int update(ActivationGeneratorVo activationGenerator) {
        return apiActivationGeneratorMapper.update(activationGenerator);
    }


    public int delete(Integer id) {
        return apiActivationGeneratorMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiActivationGeneratorMapper.deleteBatch(ids);
    }
}
