package com.platform.service;

import com.platform.dao.ApiUserDetectionCycleMapper;
import com.platform.entity.UserDetectionCycleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 用户检测周期表
 id
 检测开始日期
 检测结束日期
 已检测次数
 检测执行日期
 检测周期
 用户idService实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:46
 */
@Service
public class ApiUserDetectionCycleService {
    @Autowired
    private ApiUserDetectionCycleMapper userDetectionCycleDao;

    public UserDetectionCycleVo queryObject(Integer id) {
        return userDetectionCycleDao.queryObject(id);
    }
    public List<UserDetectionCycleVo> queryList(Map<String, Object> map) {
        return userDetectionCycleDao.queryList(map);
    }
    public int queryTotal(Map<String, Object> map) {
        return userDetectionCycleDao.queryTotal(map);
    }
    public int save(UserDetectionCycleVo userDetectionCycle) {
        return userDetectionCycleDao.save(userDetectionCycle);
    }
    public int update(UserDetectionCycleVo userDetectionCycle) {
        return userDetectionCycleDao.update(userDetectionCycle);
    }
    public int delete(Integer id) {
        return userDetectionCycleDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return userDetectionCycleDao.deleteBatch(ids);
    }
}
