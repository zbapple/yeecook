package com.platform.service.impl;

import com.platform.entity.SysUserEntity;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.dao.GoodsSpecificationDao;
import com.platform.entity.GoodsSpecificationEntity;
import com.platform.service.GoodsSpecificationService;

/**
 * 商品对应规格表值表Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-31 11:15:55
 */
@Service("goodsSpecificationService")
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {
    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;

    @Override
    public GoodsSpecificationEntity queryObject(Integer id) {
        return goodsSpecificationDao.queryObject(id);
    }

    @Override
    public List<GoodsSpecificationEntity> queryList(Map<String, Object> map) {

        return goodsSpecificationDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return goodsSpecificationDao.queryTotal(map);
    }

    @Override
    public int save(GoodsSpecificationEntity goodsSpecification) {
        return goodsSpecificationDao.save(goodsSpecification);
    }

    @Override
    public int update(GoodsSpecificationEntity goodsSpecification) {
        return goodsSpecificationDao.update(goodsSpecification);
    }

    @Override
    public int delete(Integer id) {
        return goodsSpecificationDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return goodsSpecificationDao.deleteBatch(ids);
    }
    @Override
    public int enSale(Integer id) {
        GoodsSpecificationEntity goodsSpecificationEntity = queryObject(id);
        if (1 == goodsSpecificationEntity.getIsSale()) {
            throw new RRException("此规格已处于启用状态！");
        }
        goodsSpecificationEntity.setIsSale(1);
        return goodsSpecificationDao.update(goodsSpecificationEntity);
    }

    @Override
    public int unSale(Integer id) {
        GoodsSpecificationEntity goodsSpecificationEntity = queryObject(id);
        if (0 == goodsSpecificationEntity.getIsSale()) {
            throw new RRException("此规格已处于关闭状态！");
        }
        goodsSpecificationEntity.setIsSale(0);
        return goodsSpecificationDao.update(goodsSpecificationEntity);
    }
}
