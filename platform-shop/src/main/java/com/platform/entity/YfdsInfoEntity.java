package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 yfds_info
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
public class YfdsInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //研发大师图像
    private String yfdsPic;
    //研发大师名称
    private String yfdsName;
    //研发大师描述
    private String yfdsDesc;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：研发大师图像
     */
    public void setYfdsPic(String yfdsPic) {
        this.yfdsPic = yfdsPic;
    }

    /**
     * 获取：研发大师图像
     */
    public String getYfdsPic() {
        return yfdsPic;
    }
    /**
     * 设置：研发大师名称
     */
    public void setYfdsName(String yfdsName) {
        this.yfdsName = yfdsName;
    }

    /**
     * 获取：研发大师名称
     */
    public String getYfdsName() {
        return yfdsName;
    }
    /**
     * 设置：研发大师描述
     */
    public void setYfdsDesc(String yfdsDesc) {
        this.yfdsDesc = yfdsDesc;
    }

    /**
     * 获取：研发大师描述
     */
    public String getYfdsDesc() {
        return yfdsDesc;
    }
}
