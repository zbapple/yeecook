package com.platform.service;

import com.platform.dao.ApiActivationCardMapper;
import com.platform.entity.ActivationCardVo;
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
public class ApiActivationCardService {
    @Autowired
    private ApiActivationCardMapper apiActivationCardMapper;

    
    public ActivationCardVo queryObject(Integer id) {
        return apiActivationCardMapper.queryObject(id);
    }


    public List<ActivationCardVo> queryList(Map<String, Object> map) {
        return apiActivationCardMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return apiActivationCardMapper.queryTotal(map);
    }


    public int save(ActivationCardVo activationCard) {
        return apiActivationCardMapper.save(activationCard);
    }


    public int update(ActivationCardVo activationCard) {
        return apiActivationCardMapper.update(activationCard);
    }


    public int delete(Integer id) {
        return apiActivationCardMapper.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return apiActivationCardMapper.deleteBatch(ids);
    }
}
