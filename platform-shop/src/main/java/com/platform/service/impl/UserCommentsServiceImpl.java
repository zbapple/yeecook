package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.UserCommentsDao;
import com.platform.entity.UserCommentsEntity;
import com.platform.service.UserCommentsService;

/**
 * 用户评论表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:19:06
 */
@Service("userCommentsService")
public class UserCommentsServiceImpl implements UserCommentsService {
    @Autowired
    private UserCommentsDao userCommentsDao;

    @Override
    public UserCommentsEntity queryObject(Integer id) {
        return userCommentsDao.queryObject(id);
    }

    @Override
    public List<UserCommentsEntity> queryList(Map<String, Object> map) {
        return userCommentsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userCommentsDao.queryTotal(map);
    }

    @Override
    public int save(UserCommentsEntity userComments) {
        return userCommentsDao.save(userComments);
    }

    @Override
    public int update(UserCommentsEntity userComments) {
        return userCommentsDao.update(userComments);
    }

    @Override
    public int delete(Integer id) {
        return userCommentsDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userCommentsDao.deleteBatch(ids);
    }
}
