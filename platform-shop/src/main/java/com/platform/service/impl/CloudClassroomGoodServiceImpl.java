package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CloudClassroomGoodDao;
import com.platform.entity.CloudClassroomGoodEntity;
import com.platform.service.CloudClassroomGoodService;

/**
 * 云课堂商品关联表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:34:57
 */
@Service("cloudClassroomGoodService")
public class CloudClassroomGoodServiceImpl implements CloudClassroomGoodService {
    @Autowired
    private CloudClassroomGoodDao cloudClassroomGoodDao;

    @Override
    public CloudClassroomGoodEntity queryObject(Integer id) {
        return cloudClassroomGoodDao.queryObject(id);
    }

    @Override
    public List<CloudClassroomGoodEntity> queryList(Map<String, Object> map) {
        return cloudClassroomGoodDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cloudClassroomGoodDao.queryTotal(map);
    }

    @Override
    public int save(CloudClassroomGoodEntity cloudClassroomGood) {
        return cloudClassroomGoodDao.save(cloudClassroomGood);
    }

    @Override
    public int update(CloudClassroomGoodEntity cloudClassroomGood) {
        return cloudClassroomGoodDao.update(cloudClassroomGood);
    }

    @Override
    public int delete(Integer id) {
        return cloudClassroomGoodDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return cloudClassroomGoodDao.deleteBatch(ids);
    }
}
