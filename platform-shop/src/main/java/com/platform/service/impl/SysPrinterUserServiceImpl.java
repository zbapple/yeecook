package com.platform.service.impl;

import com.platform.annotation.DataFilter;
import com.platform.cache.J2CacheUtils;
import com.platform.entity.SysUserEntity;
import com.platform.utils.Constant;
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
 * @date 2018-11-09 15:47:35
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
    @DataFilter(userAlias = "sys_printer_user.user_id")
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
        return sysPrinterUserDao.save(sysPrinterUser);
    }

    @Override
    public int update(SysPrinterUserEntity sysPrinterUser) {
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        sysPrinterUser.setUserId(sysUserEntity.getUserId());
        sysPrinterUser.setUserName(sysUserEntity.getUsername());

        int i =sysPrinterUserDao.update(sysPrinterUser);
        if(i>0){
            J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK_USER+sysPrinterUser.getId(), sysPrinterUser);
        }
        return i;
    }

    @Override
    public int delete(Long id) {
        int i =sysPrinterUserDao.delete(id);
        if(i>0){
            J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK_USER+id);
        }
        return i;
    }

    @Override
    public int deleteBatch(Long[] ids) {
        int i=sysPrinterUserDao.deleteBatch(ids);
        if(i>0){
            for (Long id:ids) {
                J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK_USER + id);
            }
        }
        return i;
    }
}
