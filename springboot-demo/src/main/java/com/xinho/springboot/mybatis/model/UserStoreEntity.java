package com.xinho.springboot.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1715:38
 */
public class UserStoreEntity implements Serializable {


    private String userId;

    private String storeUserId;

    private String merchantId;

    private String merchantStoreId;

    private String storeId;

    private String deleted;

    private String createBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(String storeUserId) {
        this.storeUserId = storeUserId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(String merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserStoreEntity{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", storeUserId='").append(storeUserId).append('\'');
        sb.append(", merchantId='").append(merchantId).append('\'');
        sb.append(", merchantStoreId='").append(merchantStoreId).append('\'');
        sb.append(", storeId='").append(storeId).append('\'');
        sb.append(", deleted='").append(deleted).append('\'');
        sb.append(", createBy='").append(createBy).append('\'');
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastModifiedBy='").append(lastModifiedBy).append('\'');
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append('}');
        return sb.toString();
    }
}
