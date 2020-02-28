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
 * @Description: 实验计划
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验计划", description = "实验计划")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_plan")
public class ExPlan extends BaseEntity {

    @ApiModelProperty(value = "客户编号", required = true)
    @TableField(value = "customer_code")
    private String customerCode;

    @ApiModelProperty(value = "产品类型", required = true)
    @TableField(value = "product_code")
    private String productCode;

    @ApiModelProperty(value = "來樣编号", required = true)
    @TableField(value = "sample_code")
    private String sampleCode;

    @ApiModelProperty(value = "相機數量", required = true)
    @TableField(value = "camera_qty")
    private Integer cameraQty;

    @ApiModelProperty(value = "切片版本")
    @TableField(value = "cutting_version")
    private String cuttingVersion;

    @ApiModelProperty(value = "业务员")
    @TableField(value = "salesman_name")
    private String salesmanName;

    @ApiModelProperty(value = "产品尺寸說明")
    @TableField(value = "product_specification")
    private String productSpecification;

    @ApiModelProperty(value = "实验计划说明")
    @TableField(value = "plan_description")
    private String planDescription;

    @ApiModelProperty(value = "计划类型（静态/动态）")
    @TableField(value = "plan_type")
    private Integer planType;

    @ApiModelProperty(value = "训练样品数量")
    @TableField(value = "training_qty")
    private Integer trainingQty;

    @ApiModelProperty(value = "测试样品数量")
    @TableField(value = "test_qty")
    private Integer testQty;

    @ApiModelProperty(value = "收件日期")
    @TableField(value = "receive_date")
    private LocalDateTime receiveDate;

    @ApiModelProperty(value = "记录人")
    @TableField(value = "recorder")
    private String recorder;

    @ApiModelProperty(value = "算法分析师")
    @TableField(value = "analyst")
    private String analyst;

    @ApiModelProperty(value = "完成日期")
    @TableField(value = "completion_date")
    private LocalDateTime completionDate;



}
