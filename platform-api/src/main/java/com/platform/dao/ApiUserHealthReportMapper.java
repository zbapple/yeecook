package com.platform.dao;

import com.platform.entity.UserHealthReportVo;

import java.util.List;
import java.util.Map;

/**
 * 用户健康报告表
 id
 用户id
 检测时间
 更新时间
 体重
 BMI
 体脂率
 皮下脂肪率
 基础代谢量
 内脏脂肪等级
 体水分率
 蛋白质
 骨量
 骨骼肌率Dao
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-06-16 11:08:30
 */
public interface ApiUserHealthReportMapper extends BaseDao<UserHealthReportVo> {
     List<UserHealthReportVo> queryvalue(Map<String,Object> params);
}
