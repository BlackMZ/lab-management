package com.cloud.lab.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cloud.lab.management.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: John.ma
 * @Description: 实验参数组
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数组", description = "实验参数组")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_param_group")
public class ExParamGroup extends BaseEntity {

    @ApiModelProperty(value = "客户编号")
    @TableField(value = "customer_code")
    private String customerCode;

    @ApiModelProperty(value = "产品类型")
    @TableField(value = "product_code")
    private String productCode;

    @ApiModelProperty(value = "來樣编号")
    @TableField(value = "sample_code")
    private String sampleCode;

    @ApiModelProperty(value = "相機數量")
    @TableField(value = "camera_qty")
    private Integer cameraQty;

    @ApiModelProperty(value = "参数组编号")
    @TableField(value = "group_code")
    private String groupCode;

    @ApiModelProperty(value = "状态（合格/不合格）")
    @TableField(value = "group_status")
    private Integer groupStatus;
}
