package com.platform.entity;

import com.platform.utils.CsvField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 实体
 * 表名 nideshop_xet_yqm
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2018-11-18 13:21:53
 */
public class XetYqmVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //批次
    @CsvField(name = "批次")
    private String batchId;
    //批次名称
    @CsvField(name = "批次名称")
    private String batchName;
    //邀请码
    @CsvField(name = "邀请码")
    private String invitationCode;
    //邀请码链接
    @CsvField(name = "邀请码链接")
    private String invitationCodeUrl;
    //是否使用
    @CsvField(name = "是否使用")
    private String isUse;
    //使用人id
    @CsvField(name = "使用人id")
    private String useUserId;
    //使用人昵称
    @CsvField(name = "使用人昵称")
    private String useUserName;
    //邀请码标题
    @CsvField(name = "邀请码标题")
    private String invitationCodeTitle;
    //使用须知
    @CsvField(name = "使用须知")
    private String useNotice;
    //申请人
    @CsvField(name = "申请人")
    private String proposer;
    //申请原因
    @CsvField(name = "申请原因")
    private String pursueReason;
    //生效时间
    @CsvField(name = "生效时间")
    private String effectiveTime;
    //失效时间
    @CsvField(name = "失效时间")
    private String deadTime;
    //生成时间
    @CsvField(name = "生成时间")
    private String generatedTime;
    //主键
    private Integer id;
    //用户id
    private Long userId;
    @Getter@Setter
    //激活码可用总数
    private String countCd;

    /**
     * 设置：批次
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取：批次
     */
    public String getBatchId() {
        return batchId;
    }
    /**
     * 设置：批次名称
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * 获取：批次名称
     */
    public String getBatchName() {
        return batchName;
    }
    /**
     * 设置：邀请码
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    /**
     * 获取：邀请码
     */
    public String getInvitationCode() {
        return invitationCode;
    }
    /**
     * 设置：邀请码连接
     */
    public void setInvitationCodeUrl(String invitationCodeUrl) {
        this.invitationCodeUrl = invitationCodeUrl;
    }

    /**
     * 获取：邀请码连接
     */
    public String getInvitationCodeUrl() {
        return invitationCodeUrl;
    }
    /**
     * 设置：是否使用
     */
    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    /**
     * 获取：是否使用
     */
    public String getIsUse() {
        return isUse;
    }
    /**
     * 设置：使用人id
     */
    public void setUseUserId(String useUserId) {
        this.useUserId = useUserId;
    }

    /**
     * 获取：使用人id
     */
    public String getUseUserId() {
        return useUserId;
    }
    /**
     * 设置：使用人昵称
     */
    public void setUseUserName(String useUserName) {
        this.useUserName = useUserName;
    }

    /**
     * 获取：使用人昵称
     */
    public String getUseUserName() {
        return useUserName;
    }
    /**
     * 设置：邀请码标题
     */
    public void setInvitationCodeTitle(String invitationCodeTitle) {
        this.invitationCodeTitle = invitationCodeTitle;
    }

    /**
     * 获取：邀请码标题
     */
    public String getInvitationCodeTitle() {
        return invitationCodeTitle;
    }
    /**
     * 设置：使用须知
     */
    public void setUseNotice(String useNotice) {
        this.useNotice = useNotice;
    }

    /**
     * 获取：使用须知
     */
    public String getUseNotice() {
        return useNotice;
    }
    /**
     * 设置：申请人
     */
    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    /**
     * 获取：申请人
     */
    public String getProposer() {
        return proposer;
    }
    /**
     * 设置：申请原因
     */
    public void setPursueReason(String pursueReason) {
        this.pursueReason = pursueReason;
    }

    /**
     * 获取：申请原因
     */
    public String getPursueReason() {
        return pursueReason;
    }
    /**
     * 设置：生效时间
     */
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * 获取：生效时间
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }
    /**
     * 设置：失效时间
     */
    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    /**
     * 获取：失效时间
     */
    public String getDeadTime() {
        return deadTime;
    }
    /**
     * 设置：生成时间
     */
    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
    }

    /**
     * 获取：生成时间
     */
    public String getGeneratedTime() {
        return generatedTime;
    }
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
     * 设置：用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public Long getUserId() {
        return userId;
    }
}
