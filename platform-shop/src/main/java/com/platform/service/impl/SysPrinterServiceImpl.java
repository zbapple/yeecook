package com.platform.service.impl;

import com.platform.cache.J2CacheUtils;
import com.platform.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.SysPrinterDao;
import com.platform.entity.SysPrinterEntity;
import com.platform.service.SysPrinterService;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-09 12:15:00
 */
@Service("sysPrinterService")
public class SysPrinterServiceImpl implements SysPrinterService {
    @Autowired
    private SysPrinterDao sysPrinterDao;

    @Override
    public SysPrinterEntity queryObject(Long id) {
        return sysPrinterDao.queryObject(id);
    }

    @Override
    public List<SysPrinterEntity> queryList(Map<String, Object> map) {
        return sysPrinterDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysPrinterDao.queryTotal(map);
    }

    @Override
    public int save(SysPrinterEntity sysPrinter) {
        return sysPrinterDao.save(sysPrinter);
    }

    @Override
    public int update(SysPrinterEntity sysPrinter) {

        int i =sysPrinterDao.update(sysPrinter);
        if(i>0){
            J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK+sysPrinter.getId(), sysPrinter);
        }
        return i;
    }

    @Override
    public int delete(Long id) {
        int i =sysPrinterDao.delete(id);
        if(i>0){
            J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK+id);
        }
        return i;
    }

    @Override
    public int deleteBatch(Long[] ids) {
        int i=sysPrinterDao.deleteBatch(ids);
        if(i>0){
            for (Long id:ids) {
                J2CacheUtils.remove(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK + id);
            }
        }
        return i;
    }
}
