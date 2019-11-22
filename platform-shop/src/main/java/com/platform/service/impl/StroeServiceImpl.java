package com.platform.service.impl;

import com.platform.entity.StroeEntity;
import com.platform.entity.SysUserEntity;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.dao.StroeDao;
import com.platform.service.StroeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类
 *
 * @author 17
 * @email inaoie@163.com
 * @date 2019-11-01 11:00:09
 */
@Service("stroeService")
public class StroeServiceImpl implements StroeService {
    @Autowired
    private StroeDao stroeDao;

    @Override
    public StroeEntity queryObject(Integer id) {
        return stroeDao.queryObject(id);
    }

    @Override
    public List<StroeEntity> queryList(Map<String, Object> map) {

        return stroeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stroeDao.queryTotal(map);
    }

    @Override
    public int save(StroeEntity stroe) {
        List<String> storetimes=stroe.getStoreTimes();
        if (storetimes!=null && storetimes.size()>0) {
            StringBuilder sb = new StringBuilder();
            String separator = "-";
            for (int i = 0; i < storetimes.size(); i++) {
                sb.append(storetimes.get(i)).append(separator);
            }

        String s=sb.toString().substring(0,sb.toString().length()-1);
        stroe.setStoreTime(s);}
        List<StroeEntity> objects=stroe.getPicList();
//        if (objects !=null && objects.size()>0){
//            StringBuilder sb1=new StringBuilder();
//            String regex="|";
//            for (int j = 0; j <objects.size() ; j++) {
//                sb1.append(objects.get(j)).append(regex);
//            }
//            String real=sb1.toString().substring(0,sb1.toString().length()-1);
//            stroe.setRealisticPicture(real);
//        }
        if (objects !=null && objects.size()>0){
            StringBuilder sb1=new StringBuilder();
            for (StroeEntity stroes:objects){
                String pic=stroes.getRealisticPicture();
                sb1.append(pic).append(";");
            }
            String real=sb1.toString().substring(0,sb1.toString().length()-1);
            stroe.setRealisticPicture(real);
        }
        return stroeDao.save(stroe);
    }

    @Override
    public int update(StroeEntity stroe) {
        List<String> storetimes=stroe.getStoreTimes();
        if (storetimes!=null && storetimes.size()>0) {
            StringBuilder sb = new StringBuilder();
            String separator = "-";
            for (int i = 0; i < storetimes.size(); i++) {
                sb.append(storetimes.get(i)).append(separator);
            }
        String s=sb.toString().substring(0,sb.toString().length()-1);
        stroe.setStoreTime(s);}
        List<StroeEntity> objects=stroe.getPicList();
        if (objects !=null && objects.size()>0){
            StringBuilder sb1=new StringBuilder();
            for (StroeEntity stroes:objects){
                String pic=stroes.getRealisticPicture();
                sb1.append(pic).append(";");
            }
            String real=sb1.toString().substring(0,sb1.toString().length()-1);
            stroe.setRealisticPicture(real);
        }
        return stroeDao.update(stroe);
    }

    @Override
    public int delete(Integer id) {
        StroeEntity stroeEntity = queryObject(id);
        stroeEntity.setIsDelete(1);
        return stroeDao.update(stroeEntity);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        int result = 0;
        for (Integer id : ids) {
            result += delete(id);
        }
        return result;
    }
    @Override
    public int deleteIs(Integer id) {
        return stroeDao.delete(id);
    }

    @Override
    public int deleteAll(Integer[] ids) {
        return stroeDao.deleteBatch(ids);
    }
    @Override
    public int updateStatus(@Param(value = "id") Integer id) {
        StroeEntity stroe = stroeDao.queryObject(id);
        Integer storeStatus = stroe.getStoreStatus();
        if (0 == storeStatus) {
            stroe.setStoreStatus(1);
        }
        if (1 == storeStatus){
            stroe.setStoreStatus(0);
        }
        return stroeDao.update(stroe);
    }

    @Override
    @Transactional
    public int back(Integer[] ids) {
        int result = 0;
        for (Integer id : ids) {
            StroeEntity stroeEntity = queryObject(id);
            stroeEntity.setIsDelete(0);
            result += stroeDao.update(stroeEntity);
        }
        return result;
    }
}
