package com.lyc.spark.core.mybatisplus.support;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(
        description = "查询条件"
)
@Data
public class Query {
    @ApiModelProperty("当前页")
    private Integer current;
    @ApiModelProperty("每页的数量")
    private Integer size;
    @ApiModelProperty(
            hidden = true
    )
    private String ascs;
    @ApiModelProperty(
            hidden = true
    )
    private String descs;

    public Query() {
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getAscs() {
        return this.ascs;
    }

    public String getDescs() {
        return this.descs;
    }

    public Query setCurrent(final Integer current) {
        this.current = current;
        return this;
    }

    public Query setSize(final Integer size) {
        this.size = size;
        return this;
    }

    public Query setAscs(final String ascs) {
        this.ascs = ascs;
        return this;
    }

    public Query setDescs(final String descs) {
        this.descs = descs;
        return this;
    }

}
