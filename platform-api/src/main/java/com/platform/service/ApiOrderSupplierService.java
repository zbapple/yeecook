package com.platform.service;

import com.platform.dao.ApiOrderSupplierMapper;
import com.platform.entity.OrderSupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-09-19 12:23:49
 */
@Service()
public class ApiOrderSupplierService  {
    @Autowired
    private ApiOrderSupplierMapper apiOrderSupplierMapper;

   
    public OrderSupplierVo queryObject(Integer id) {
        return apiOrderSupplierMapper.queryObject(id);
    }

   
    public List<OrderSupplierVo> queryList(Map<String, Object> map) {
        return apiOrderSupplierMapper.queryList(map);
    }

   
    public int queryTotal(Map<String, Object> map) {
        return apiOrderSupplierMapper.queryTotal(map);
    }

   
    public int save(OrderSupplierVo orderSupplier) {
        return apiOrderSupplierMapper.save(orderSupplier);
    }

   
    public int update(OrderSupplierVo orderSupplier) {
        return apiOrderSupplierMapper.update(orderSupplier);
    }

   
    public int delete(Integer id) {
        return apiOrderSupplierMapper.delete(id);
    }

   
    public int deleteBatch(Integer[] ids) {
        return apiOrderSupplierMapper.deleteBatch(ids);
    }
}
