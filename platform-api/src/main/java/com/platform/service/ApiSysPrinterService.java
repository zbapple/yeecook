package com.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.ApiSysPrinterMapper;
import com.platform.dao.ApiSysPrinterUserMapper;
import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;
import com.platform.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-09 12:53:26
 */
@Service
public class ApiSysPrinterService {
    @Autowired
    private ApiSysPrinterMapper apiSysPrinterMapper;

    @Autowired
    private ApiSysPrinterUserMapper apiSysPrinterUserMapper;

    JSONObject jsonObject;

    public ApiSysPrinterService(){
         jsonObject=new JSONObject();
    }

    public SysPrinterVo getSysPrinterVo(Long id) {
        SysPrinterVo sysPrinterVo = (SysPrinterVo) J2CacheUtils.get(Constant.PRINTER_PK + id);
        if(null==sysPrinterVo){
            sysPrinterVo = apiSysPrinterMapper.queryObject(id);
            if(null!=sysPrinterVo) {
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK+id, sysPrinterVo);
            }
        }
        return sysPrinterVo;
    }

    public List<SysPrinterUserVo> getSysPrinterUserVo(Long userId) {
        Map map = new HashMap();
        map.put("userId",userId);

        List<SysPrinterUserVo> list= (List<SysPrinterUserVo>) J2CacheUtils.get(Constant.PRINTER_PK_USER + userId);
        if(null==list||list.size()<=0){
            list = apiSysPrinterUserMapper.queryList(map);
            if(null!=list&&list.size()>0) {
                J2CacheUtils.put(J2CacheUtils.SHOP_CACHE_NAME, Constant.PRINTER_PK+userId, list);
            }
        }

        return list;
    }





}
