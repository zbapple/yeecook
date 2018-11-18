package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.XetYqmDao;
import com.platform.entity.XetYqmEntity;
import com.platform.service.XetYqmService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-18 13:21:53
 */
@Service("xetYqmService")
public class XetYqmServiceImpl implements XetYqmService {
    @Autowired
    private XetYqmDao xetYqmDao;

    @Override
    public XetYqmEntity queryObject(String invitationCode) {
        return xetYqmDao.queryObject(invitationCode);
    }

    @Override
    public List<XetYqmEntity> queryList(Map<String, Object> map) {
        return xetYqmDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return xetYqmDao.queryTotal(map);
    }

    @Override
    public int save(XetYqmEntity xetYqm) {
        return xetYqmDao.save(xetYqm);
    }

    @Override
    public int update(XetYqmEntity xetYqm) {
        return xetYqmDao.update(xetYqm);
    }

    @Override
    public int delete(String invitationCode) {
        return xetYqmDao.delete(invitationCode);
    }

    @Override
    public int deleteBatch(String[] invitationCodes) {
        return xetYqmDao.deleteBatch(invitationCodes);
    }
}
