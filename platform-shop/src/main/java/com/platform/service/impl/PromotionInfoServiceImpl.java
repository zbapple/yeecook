package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.PromotionInfoDao;
import com.platform.entity.PromotionInfoEntity;
import com.platform.service.PromotionInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 11:58:47
 */
@Service("promotionInfoService")
public class PromotionInfoServiceImpl implements PromotionInfoService {
    @Autowired
    private PromotionInfoDao promotionInfoDao;

    @Override
    public PromotionInfoEntity queryObject(Integer id) {
        return promotionInfoDao.queryObject(id);
    }

    @Override
    public List<PromotionInfoEntity> queryList(Map<String, Object> map) {
        return promotionInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return promotionInfoDao.queryTotal(map);
    }

    @Override
    public int save(PromotionInfoEntity promotionInfo) {
        return promotionInfoDao.save(promotionInfo);
    }

    @Override
    public int update(PromotionInfoEntity promotionInfo) {
        return promotionInfoDao.update(promotionInfo);
    }

    @Override
    public int delete(Integer id) {
        return promotionInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return promotionInfoDao.deleteBatch(ids);
    }
}
