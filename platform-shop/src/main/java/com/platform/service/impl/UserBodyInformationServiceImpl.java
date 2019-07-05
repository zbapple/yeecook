package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.UserBodyInformationDao;
import com.platform.entity.UserBodyInformationEntity;
import com.platform.service.UserBodyInformationService;

/**
 * 用户身体信息表
Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 14:20:43
 */
@Service("userBodyInformationService")
public class UserBodyInformationServiceImpl implements UserBodyInformationService {
    @Autowired
    private UserBodyInformationDao userBodyInformationDao;

    @Override
    public UserBodyInformationEntity queryObject(Integer id) {
        return userBodyInformationDao.queryObject(id);
    }

    @Override
    public List<UserBodyInformationEntity> queryList(Map<String, Object> map) {
        return userBodyInformationDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userBodyInformationDao.queryTotal(map);
    }

    @Override
    public int save(UserBodyInformationEntity userBodyInformation) {
        return userBodyInformationDao.save(userBodyInformation);
    }

    @Override
    public int update(UserBodyInformationEntity userBodyInformation) {
        return userBodyInformationDao.update(userBodyInformation);
    }

    @Override
    public int delete(Integer id) {
        return userBodyInformationDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return userBodyInformationDao.deleteBatch(ids);
    }
}
