package com.zyj010.huaba.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有业务类都要继承的基本类
 */
public abstract class BaseModel implements Comparable<BaseModel>, Serializable {

    private Long id;

    private String createdAt;

    private String updatedAt;

    @Override
    public int compareTo(BaseModel o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel baseModel = (BaseModel) o;

        if (id != null ? !id.equals(baseModel.id) : baseModel.id != null) return false;
        if (createdAt != null ? !createdAt.equals(baseModel.createdAt) : baseModel.createdAt != null)
            return false;
        return updatedAt != null ? updatedAt.equals(baseModel.updatedAt) : baseModel.updatedAt == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}