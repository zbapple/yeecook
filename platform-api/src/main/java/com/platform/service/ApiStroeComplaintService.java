package com.platform.service;

import com.platform.dao.ApiStroeComplaintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.platform.entity.StroeComplaintVo;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-29 14:42:33
 */
@Service
public class ApiStroeComplaintService {
    @Autowired
    private ApiStroeComplaintMapper stroeComplaintDao;

    public StroeComplaintVo queryObject(Integer id) {
        return stroeComplaintDao.queryObject(id);
    }

    public List<StroeComplaintVo> queryList(Map<String, Object> map) {
        return stroeComplaintDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return stroeComplaintDao.queryTotal(map);
    }

    public int save(StroeComplaintVo stroeComplaint) {


        return stroeComplaintDao.save(stroeComplaint);
    }

    public int update(StroeComplaintVo stroeComplaint) {
        return stroeComplaintDao.update(stroeComplaint);
    }

    public int delete(Integer id) {
        return stroeComplaintDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return stroeComplaintDao.deleteBatch(ids);
    }
}
