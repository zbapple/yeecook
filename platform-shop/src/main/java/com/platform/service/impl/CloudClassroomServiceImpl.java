package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CloudClassroomDao;
import com.platform.entity.CloudClassroomEntity;
import com.platform.service.CloudClassroomService;

/**
 * 云课堂表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:35:47
 */
@Service("cloudClassroomService")
public class CloudClassroomServiceImpl implements CloudClassroomService {
    @Autowired
    private CloudClassroomDao cloudClassroomDao;

    @Override
    public CloudClassroomEntity queryObject(Integer id) {
        return cloudClassroomDao.queryObject(id);
    }

    @Override
    public List<CloudClassroomEntity> queryList(Map<String, Object> map) {
        return cloudClassroomDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return cloudClassroomDao.queryTotal(map);
    }

    @Override
    public int save(CloudClassroomEntity cloudClassroom) {
        return cloudClassroomDao.save(cloudClassroom);
    }

    @Override
    public int update(CloudClassroomEntity cloudClassroom) {
        return cloudClassroomDao.update(cloudClassroom);
    }

    @Override
    public int delete(Integer id) {
        return cloudClassroomDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return cloudClassroomDao.deleteBatch(ids);
    }
}
