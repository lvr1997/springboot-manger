package com.lvr.springbootmanger.bean;

import java.util.Date;

public class ResourceFile {
    private Integer resourceId;

    private String resourceName;

    private String resourcePath;

    private String onloadUser;

    private Date createTime;

    private Integer resourceStatus;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public String getOnloadUser() {
        return onloadUser;
    }

    public void setOnloadUser(String onloadUser) {
        this.onloadUser = onloadUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }
}