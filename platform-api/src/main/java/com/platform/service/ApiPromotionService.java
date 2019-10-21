package com.platform.service;

import com.platform.dao.ApiPromotionMapper;
import com.platform.entity.PromotionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-18 10:26:51
 */
@Service
public class ApiPromotionService  {
    @Autowired
    private ApiPromotionMapper promotionDao;

    public PromotionVo queryObject(Integer id) {
        return promotionDao.queryObject(id);
    }

    public List<PromotionVo> queryList(Map<String, Object> map) {
        return promotionDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return promotionDao.queryTotal(map);
    }

    public int save(PromotionVo promotion) {
        return promotionDao.save(promotion);
    }

    public int update(PromotionVo promotion) {
        return promotionDao.update(promotion);
    }

    public int delete(Integer id) {
        return promotionDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return promotionDao.deleteBatch(ids);
    }
}
