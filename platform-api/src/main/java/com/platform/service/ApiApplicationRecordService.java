package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiApplicationRecordMapper;
import com.platform.entity.ApiApplicationRecordVo;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-06 02:42:19
 */
@Service
public class ApiApplicationRecordService {
    @Autowired
    private ApiApplicationRecordMapper applicationRecordDao;


    public ApiApplicationRecordVo queryObject(Integer id) {
        return applicationRecordDao.queryObject(id);
    }


    public List<ApiApplicationRecordVo> queryList(Map<String, Object> map) {
        return applicationRecordDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return applicationRecordDao.queryTotal(map);
    }


    public int save(ApiApplicationRecordVo applicationRecord) {
        return applicationRecordDao.save(applicationRecord);
    }

    public int update(ApiApplicationRecordVo applicationRecord) {
        return applicationRecordDao.update(applicationRecord);
    }


    public int delete(Integer id) {
        return applicationRecordDao.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return applicationRecordDao.deleteBatch(ids);
    }
}
