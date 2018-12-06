package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.XcfCharlesInfoDao;
import com.platform.charles.xcf.XcfCharlesInfoEntity;
import com.platform.service.XcfCharlesInfoService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-12-07 00:47:39
 */
@Service("xcfCharlesInfoService")
public class XcfCharlesInfoServiceImpl implements XcfCharlesInfoService {
    @Autowired
    private XcfCharlesInfoDao xcfCharlesInfoDao;

    @Override
    public XcfCharlesInfoEntity queryObject(Long id) {
        return xcfCharlesInfoDao.queryObject(id);
    }

    @Override
    public List<XcfCharlesInfoEntity> queryList(Map<String, Object> map) {
        return xcfCharlesInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return xcfCharlesInfoDao.queryTotal(map);
    }

    @Override
    public int save(XcfCharlesInfoEntity xcfCharlesInfo) {
        return xcfCharlesInfoDao.save(xcfCharlesInfo);
    }

    @Override
    public int update(XcfCharlesInfoEntity xcfCharlesInfo) {
        return xcfCharlesInfoDao.update(xcfCharlesInfo);
    }

    @Override
    public int delete(Long id) {
        return xcfCharlesInfoDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return xcfCharlesInfoDao.deleteBatch(ids);
    }

    @Override
    public int saveBatch(List<XcfCharlesInfoEntity> list) {
        return xcfCharlesInfoDao.saveBatch(list);
    }

    @Override
    public int updateBatch(int years, int month, int day, List<XcfCharlesInfoEntity> list) {
        return xcfCharlesInfoDao.updateBatch(years,month,day,list);
    }


}
