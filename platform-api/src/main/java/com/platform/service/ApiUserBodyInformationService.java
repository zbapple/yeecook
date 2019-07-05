package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.ApiUserBodyInformationMapper;
import com.platform.entity.UserBodyInformationVo;

/**
 * 用户身体信息表
 id
 用户id
 身高
 生日
 目标体重Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-15 17:43:02
 */
@Service
public class ApiUserBodyInformationService  {
    @Autowired
    private ApiUserBodyInformationMapper userBodyInformationDao;

    public UserBodyInformationVo queryObject(Integer id) {
        return userBodyInformationDao.queryObject(id);
    }
    public List<UserBodyInformationVo> queryList(Map<String, Object> map) {
        return userBodyInformationDao.queryList(map);
    }
    public int queryTotal(Map<String, Object> map) {
        return userBodyInformationDao.queryTotal(map);
    }

    public int save(UserBodyInformationVo userBodyInformation) {
        return userBodyInformationDao.save(userBodyInformation);
    }

    public int update(UserBodyInformationVo userBodyInformation) {
        return userBodyInformationDao.update(userBodyInformation);
    }

    public int delete(Integer id) {
        return userBodyInformationDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return userBodyInformationDao.deleteBatch(ids);
    }
}