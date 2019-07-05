package com.platform.service;
import com.platform.dao.ApiUserHealthReportMapper;
import com.platform.entity.UserHealthReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户健康报告表
 id
 用户id
 检测时间
 更新时间
 体重
 BMI
 体脂率
 皮下脂肪率
 基础代谢量
 内脏脂肪等级
 体水分率
 蛋白质
 骨量
 骨骼肌率Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:30
 */
@Service
public class ApiUserHealthReportService  {
    @Autowired
    private ApiUserHealthReportMapper userHealthReportDao;
    public UserHealthReportVo queryObject(Integer id) {
        return userHealthReportDao.queryObject(id);
    }
    public List<UserHealthReportVo> queryList(Map<String, Object> map) {
        return userHealthReportDao.queryList(map);
    }
    public List<UserHealthReportVo> queryvalue(Map<String,Object> map){return userHealthReportDao.queryvalue(map);}
    public int queryTotal(Map<String, Object> map) {
        return userHealthReportDao.queryTotal(map);
    }
    public int save(UserHealthReportVo userHealthReport) {
        return userHealthReportDao.save(userHealthReport);
    }
    public int update(UserHealthReportVo userHealthReport) {
        return userHealthReportDao.update(userHealthReport);
    }
    public int delete(Integer id) {
        return userHealthReportDao.delete(id);
    }
    public int deleteBatch(Integer[] ids) {
        return userHealthReportDao.deleteBatch(ids);
    }
}
