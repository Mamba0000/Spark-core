package com.lyc.spark.core.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class BaseEntity implements Serializable {
    @ApiModelProperty("创建人")
    private Long createUser;

    // 入参格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 出参格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    private Long updateUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("业务状态")
    private Integer status;

    @TableLogic
    @ApiModelProperty("是否已删除")
    private Integer isDeleted;

}
