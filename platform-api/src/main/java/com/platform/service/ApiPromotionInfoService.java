package com.platform.service;

import com.platform.dao.ApiPromotionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.entity.PromotionInfoEntity;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 17:55:52
 */
@Service
public class ApiPromotionInfoService{
    @Autowired
    private ApiPromotionInfoMapper promotionInfoDao;

    public PromotionInfoEntity queryObject(Integer id) {
        return promotionInfoDao.queryObject(id);
    }

    public List<PromotionInfoEntity> queryList(Map<String, Object> map) {
        return promotionInfoDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return promotionInfoDao.queryTotal(map);
    }

    public int save(PromotionInfoEntity promotionInfo) {
        return promotionInfoDao.save(promotionInfo);
    }

    public int update(PromotionInfoEntity promotionInfo) {
        return promotionInfoDao.update(promotionInfo);
    }

    public int delete(Integer id) {
        return promotionInfoDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return promotionInfoDao.deleteBatch(ids);
    }
}
