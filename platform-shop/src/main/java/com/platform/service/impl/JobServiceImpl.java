package com.platform.service.impl;

import com.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.dao.JobDao;
import com.platform.entity.JobEntity;
import com.platform.service.JobService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-11 10:36:32
 */
@Service("jobService")
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Override
    public JobEntity queryObject(Integer id) {
        return jobDao.queryObject(id);
    }

    @Override
    public List<JobEntity> queryList(Map<String, Object> map) {
        return jobDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return jobDao.queryTotal(map);
    }

    @Override
    public int save(JobEntity job) {
        return jobDao.save(job);
    }

    @Override
    public int update(JobEntity job) {
        return jobDao.update(job);
    }

    @Override
    public int delete(Integer id) {
        return jobDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return jobDao.deleteBatch(ids);
    }

    @Override
    public List<JobEntity> queryMessage() {
//        List<JobEntity> message=jobDao.queryMessage();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        if (message!=null){
//            for (JobEntity jobEntity:message) {
//                Date ReleaseTime=jobEntity.getReleaseTime();
//                String datetime=DateUtils.format(ReleaseTime, DateUtils.DATE_PATTERN);
//
//            }
//        }
//        List<JobEntity> jobInfo=new ArrayList();
//        if (message !=null){
//            for (JobEntity jobEntity:message) {
//                String jobinfo=jobEntity.getJobInfo();
//                String[] jobinfo1=jobinfo.split("。");
//            }
//        }
        return jobDao.queryMessage();
    }
}
