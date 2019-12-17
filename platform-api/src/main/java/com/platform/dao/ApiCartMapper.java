package com.platform.dao;

import com.platform.entity.CartVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-11 09:14:25
 */
public interface ApiCartMapper extends BaseDao<CartVo> {
    void updateCheck(@Param("Specificationid") String[] Specificationid,
                     @Param("isChecked") Integer isChecked, @Param("userId") Long userId);

    void deleteByProductIds(@Param("Specificationid") String[] Specificationid);

    void deleteByUserAndProductIds(@Param("user_id") Long user_id,@Param("Specificationid") String[] Specificationid);

    void deleteByCart(@Param("user_id") Long user_id, @Param("session_id") Integer session_id, @Param("checked") Integer checked);
    void mealsave(CartVo cart);
    void deleteAll(@Param("user_id") Long user_id, @Param("stroid") Integer stroid);
    void deleteusercart(CartVo cartVo);
    void updateusercart(CartVo cartVo);
}
