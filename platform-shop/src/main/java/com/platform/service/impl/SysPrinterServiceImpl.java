package com.platform.service.impl;

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
 * @date 2018-11-28 14:31:25
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
        return sysPrinterDao.update(sysPrinter);
    }

    @Override
    public int delete(Long id) {
        return sysPrinterDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[] ids) {
        return sysPrinterDao.deleteBatch(ids);
    }
}
