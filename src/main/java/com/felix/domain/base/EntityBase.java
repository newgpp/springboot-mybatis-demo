package com.felix.domain.base;

import java.util.Date;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 12:53
 */
public class EntityBase {

    private Date createdTime;

    private Date updatedTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
