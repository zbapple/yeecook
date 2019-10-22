package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.PromotionDao;
import com.platform.entity.PromotionEntity;
import com.platform.service.PromotionService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-22 10:14:44
 */
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionDao promotionDao;

    @Override
    public PromotionEntity queryObject(Integer id) {
        return promotionDao.queryObject(id);
    }

    @Override
    public List<PromotionEntity> queryList(Map<String, Object> map) {
        return promotionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return promotionDao.queryTotal(map);
    }

    @Override
    public int save(PromotionEntity promotion) {
        return promotionDao.save(promotion);
    }

    @Override
    public int update(PromotionEntity promotion) {
        return promotionDao.update(promotion);
    }

    @Override
    public int delete(Integer id) {
        return promotionDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return promotionDao.deleteBatch(ids);
    }
}
