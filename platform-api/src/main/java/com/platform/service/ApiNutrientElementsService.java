package com.platform.service;

import com.platform.dao.ApiNutrientElementsMapper;
import com.platform.entity.NutrientElementsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 营养元素表
 id
 营养成分名称
 元素图片
 单位
 营养元素功能描述Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:11
 */
@Service
public class ApiNutrientElementsService {
    @Autowired
    private ApiNutrientElementsMapper nutrientElementsDao;

    public NutrientElementsVo queryObject(Integer id) {
        return nutrientElementsDao.queryObject(id);
    }

    public List<NutrientElementsVo> queryList(Map<String, Object> map) {
        return nutrientElementsDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return nutrientElementsDao.queryTotal(map);
    }

    public int save(NutrientElementsVo nutrientElements) {
        return nutrientElementsDao.save(nutrientElements);
    }

    public int update(NutrientElementsVo nutrientElements) {
        return nutrientElementsDao.update(nutrientElements);
    }

    public int delete(Integer id) {
        return nutrientElementsDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return nutrientElementsDao.deleteBatch(ids);
    }
}
