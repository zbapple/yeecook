package com.platform.service;

import com.platform.dao.ApiOrderMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.platform.entity.OrderMenuVo;
/**
 * Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-09-24 10:28:47
 */
@Service
public class ApiOrderMenuService  {
    @Autowired
    private ApiOrderMenuMapper orderMenuDao;

    public OrderMenuVo queryObject(Integer id) {
        return orderMenuDao.queryObject(id);
    }

    public List<OrderMenuVo> queryList(Map<String, Object> map) {
        return orderMenuDao.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return orderMenuDao.queryTotal(map);
    }

    public int save(OrderMenuVo orderMenu) {
        return orderMenuDao.save(orderMenu);
    }

    public int update(OrderMenuVo orderMenu) {
        return orderMenuDao.update(orderMenu);
    }

    public int delete(Integer id) {
        return orderMenuDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return orderMenuDao.deleteBatch(ids);
    }
}
