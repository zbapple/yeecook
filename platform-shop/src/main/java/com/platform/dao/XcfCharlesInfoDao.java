package com.platform.dao;

import com.platform.charles.xcf.XcfCharlesInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-12-07 00:47:39
 */
public interface XcfCharlesInfoDao extends BaseDao<XcfCharlesInfoEntity> {


    int updateBatch(@Param("years") int years,@Param("month") int month,@Param("day") int day, @Param("list")List<XcfCharlesInfoEntity> list);

}
