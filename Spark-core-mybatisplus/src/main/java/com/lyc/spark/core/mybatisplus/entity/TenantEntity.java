package com.lyc.spark.core.mybatisplus.entity;

import io.swagger.annotations.ApiModelProperty;

public class TenantEntity extends BaseEntity {

    @ApiModelProperty("租户ID")
    private String tenantId;

    public TenantEntity() {
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(final String tenantId) {
        this.tenantId = tenantId;
    }

    public String toString() {
        return "TenantEntity(tenantId=" + this.getTenantId() + ")";
    }

}