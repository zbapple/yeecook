package com.platform.service;

import com.platform.dao.ApiMenuDetailsMapper;
import com.platform.entity.MenuDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 餐单详情表
 id
 餐单id
 餐单类型
 菜品id
 菜品名称
 是否叶子节点
 父级id
 用餐时间
 餐单日期Service实现类
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:07:55
 */
@Service
public class ApiMenuDetailsService {
    @Autowired
    private ApiMenuDetailsMapper menuDetailsDao;
    public MenuDetailsVo queryObject(Integer id) {
        return menuDetailsDao.queryObject(id);
    }
    public List<MenuDetailsVo> queryList(Map<String, Object> map) {
        return menuDetailsDao.queryList(map);
    }
    public List<MenuDetailsVo> querListvo(Map<String,Object> map){return menuDetailsDao.querListvo(map);}
    public int queryTotal(Map<String, Object> map) {
        return menuDetailsDao.queryTotal(map);
    }
    public int save(MenuDetailsVo menuDetails) {
        return menuDetailsDao.save(menuDetails);
    }
    public int update(MenuDetailsVo menuDetails) {
        return menuDetailsDao.update(menuDetails);
    }
    public int delete(Integer id) {
        return menuDetailsDao.delete(id);
    }
    public int deleteBatch(Integer[] ids) {
        return menuDetailsDao.deleteBatch(ids);
    }
}
