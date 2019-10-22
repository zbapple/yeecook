package com.platform.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 * 表名 nideshop_job
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-11 10:36:32
 */
@Data
public class JobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //职位名称
    private String jobTitle;
    //职位描述
    private String jobInfo;
    //职位描述
    private String jobRequire;
    //职位类别
    private String jobCategory;
    //发布时间
    private Date releaseTime;
    //
    private Long deptId;
    //
    private String deptName;
    //是否发布 0：false，1：true
    private Integer isRelease;
    //创建人
    private String createUser;
    //审核：0:false 1:true
    private Integer isStatus;

}