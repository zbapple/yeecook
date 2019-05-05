package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.Test1Dao;
import com.platform.entity.Test1Entity;
import com.platform.service.Test1Service;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-04-03 15:20:28
 */
@Service("test1Service")
public class Test1ServiceImpl implements Test1Service {
    @Autowired
    private Test1Dao test1Dao;

    @Override
    public Test1Entity queryObject(Integer id) {
        return test1Dao.queryObject(id);
    }

    @Override
    public List<Test1Entity> queryList(Map<String, Object> map) {
        return test1Dao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return test1Dao.queryTotal(map);
    }

    @Override
    public int save(Test1Entity test1) {
        return test1Dao.save(test1);
    }

    @Override
    public int update(Test1Entity test1) {
        return test1Dao.update(test1);
    }

    @Override
    public int delete(Integer id) {
        return test1Dao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return test1Dao.deleteBatch(ids);
    }
}
