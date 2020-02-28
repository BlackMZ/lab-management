package com.cloud.lab.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.lab.management.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 实验计划相机配置
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验计划相机配置", description = "实验计划相机配置")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_plan_camera_conf")
public class ExPlanCameraConf extends BaseEntity {

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

    @ApiModelProperty(value = "相机编号")
    @TableField(value = "camera_code")
    private String cameraCode;

}
