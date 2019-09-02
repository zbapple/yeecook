package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.entity.SysUserEntity;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SysPrinterUserDao;
import com.platform.entity.SysPrinterUserEntity;
import com.platform.service.SysPrinterUserService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-28 14:31:26
 */
@Service("sysPrinterUserService")
public class SysPrinterUserServiceImpl implements SysPrinterUserService {
    @Autowired
    private SysPrinterUserDao sysPrinterUserDao;

    @Override
    public SysPrinterUserEntity queryObject(Long id) {
        return sysPrinterUserDao.queryObject(id);
    }

    @Override
    public List<SysPrinterUserEntity> queryList(Map<String, Object> map) {
        return sysPrinterUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysPrinterUserDao.queryTotal(map);
    }

    @Override
    public int save(SysPrinterUserEntity sysPrinterUser) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        sysPrinterUser.setUserId(sysUserEntity.getUserId());
        sysPrinterUser.setUserName(sysUserEntity.getUsername());
        sysPrinterUser.setDeptId(sysUserEntity.getDeptId());
        return sysPrinterUserDao.save(sysPrinterUser);
    }

    @Override
    public int update(SysPrinterUserEntity sysPrinterUser) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        sysPrinterUser.setUserId(sysUserEntity.getUserId());
        sysPrinterUser.setUserName(sysUserEntity.getUsername());
        sysPrinterUser.setDeptId(sysUserEntity.getDeptId());
        return sysPrinterUserDao.update(sysPrinterUser);
    }

    @Override
    public int delete(Long id) {
        return sysPrinterUserDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return sysPrinterUserDao.deleteBatch(ids);
    }
}
