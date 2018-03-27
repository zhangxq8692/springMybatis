package com.zhangxq.modules.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhangxq.modules.common.tool.IDGen;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:56
 * @description: 基础实体类
 */

/*
 * @JsonInclude(Include.NON_NULL)
 * 将该标记放在属性上，如果该属性为NULL则不参与序列化
 * 如果放在类上边,那对这个类的全部属性起作用
 * Include.Include.ALWAYS 默认
 * Include.NON_DEFAULT 属性为默认值不序列化
 * Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
 * Include.NON_NULL 属性为NULL 不序列化
 */

public class BaseEntity{
    /**
     * 实体编号（唯一ID)
     */
    private String id;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Date updateDate;
    /**
     * 删除标志
     */
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    //@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 实例对象是否为新记录
     *
     * @return true 是新记录，false 不是新记录
     */
    @JsonIgnore
    public boolean isNewRecord() {
        if (StringUtils.isEmpty(id)) {

            return true;
        }
        return false;
    }

    @JsonIgnore
    public void preInset() {
        if (this.isNewRecord()) {
            this.setId(IDGen.uuid());
        }
        Date date = new Date();
        this.setCreateDate(date);
        this.setDelFlag(DEL_FLAG_NORMAL);
    }

    @JsonIgnore
    public void preUpdate() {
        Date date = new Date();
        this.setUpdateDate(date);
    }

    /**
     * 删除标志: 0:正常
     */
    public static final String DEL_FLAG_NORMAL = "0";

    /**
     * 删除标志: 1:删除
     */
    public static final String DEL_FLAG_DELETE = "1";
}
