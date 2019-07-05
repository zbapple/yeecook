package com.platform.service;

import com.platform.dao.ApiCloudClassroomMapper;
import com.platform.entity.CloudClassroomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 云课堂表
 Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-21 12:58:05
 */
@Service
public class ApiCloudClassroomService {
    @Autowired
    private ApiCloudClassroomMapper cloudClassroomDao;

    public CloudClassroomVo queryObject(Integer id) {
        return cloudClassroomDao.queryObject(id);
    }

    public List<CloudClassroomVo> queryList(Map<String, Object> map) {
        return cloudClassroomDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return cloudClassroomDao.queryTotal(map);
    }

    public int save(CloudClassroomVo cloudClassroom) {
        return cloudClassroomDao.save(cloudClassroom);
    }

    public int update(CloudClassroomVo cloudClassroom) {
        return cloudClassroomDao.update(cloudClassroom);
    }

    public int delete(Integer id) {
        return cloudClassroomDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return cloudClassroomDao.deleteBatch(ids);
    }
}
