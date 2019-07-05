package com.platform.service;

import com.platform.dao.ApiCloudClassroomGoodMapper;
import com.platform.entity.CloudClassroomGoodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 云课堂商品关联表
 id
 课件ID
 商品idService实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:17
 */
@Service
public class ApiCloudClassroomGoodService {
    @Autowired
    private ApiCloudClassroomGoodMapper cloudClassroomGoodDao;
    public CloudClassroomGoodVo queryObject(Integer id) {
        return cloudClassroomGoodDao.queryObject(id);
    }
    public List<CloudClassroomGoodVo> queryList(Map<String, Object> map) {
        return cloudClassroomGoodDao.queryList(map);
    }
    public int queryTotal(Map<String, Object> map) {
        return cloudClassroomGoodDao.queryTotal(map);
    }
    public int save(CloudClassroomGoodVo cloudClassroomGood) {
        return cloudClassroomGoodDao.save(cloudClassroomGood);
    }
    public int update(CloudClassroomGoodVo cloudClassroomGood) {
        return cloudClassroomGoodDao.update(cloudClassroomGood);
    }
    public int delete(Integer id) {
        return cloudClassroomGoodDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return cloudClassroomGoodDao.deleteBatch(ids);
    }
}
