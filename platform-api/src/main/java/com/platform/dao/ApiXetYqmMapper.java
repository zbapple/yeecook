package com.platform.dao;

import com.platform.entity.XetYqmVo;

import java.util.List;
import java.util.Map;


/**
 * 用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:22:06
 */
public interface ApiXetYqmMapper extends BaseDao<XetYqmVo> {

    List<XetYqmVo> queryIsNoUse(Map<String, Object> map);
}
