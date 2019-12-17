package com.platform.dao;

import com.platform.entity.CollectVo;

/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:16:47
 */
public interface ApiCollectMapper extends BaseDao<CollectVo> {
	void Updateinfo(CollectVo collectVo);
    void deleteinfo(CollectVo collectVo);
}
