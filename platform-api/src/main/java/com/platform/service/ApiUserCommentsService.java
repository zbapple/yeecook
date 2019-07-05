package com.platform.service;

import com.platform.dao.ApiUserCommentsMapper;
import com.platform.entity.UserCommentsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 用户评论表
 用户id
 用户评论
 课件id
 评论分数
 评论时间
 回复类型 0是 1否
 回复id
 idService实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:50
 */
@Service
public class ApiUserCommentsService {
    @Autowired
    private ApiUserCommentsMapper userCommentsDao;


    public UserCommentsVo queryObject(Integer id) {
        return userCommentsDao.queryObject(id);
    }


    public List<UserCommentsVo> queryList(Map<String, Object> map) {
        return userCommentsDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return userCommentsDao.queryTotal(map);
    }

    public int save(UserCommentsVo userComments) {
        return userCommentsDao.save(userComments);
    }


    public int update(UserCommentsVo userComments) {
        return userCommentsDao.update(userComments);
    }


    public int delete(Integer id) {
        return userCommentsDao.delete(id);
    }


    public int deleteBatch(Integer[] ids) {
        return userCommentsDao.deleteBatch(ids);
    }
}
