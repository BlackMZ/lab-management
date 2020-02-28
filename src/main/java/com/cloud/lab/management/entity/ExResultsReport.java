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
 * @Description: 实验报告
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验报告", description = "实验报告")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_results_report")
public class ExResultsReport extends BaseEntity {

    @ApiModelProperty(value = "实验计划id")
    @TableField(value = "plan_id")
    private String plan_id;

    @ApiModelProperty(value = "实验计划名称")
    @TableField(value = "plan_name")
    private String plan_name;

    @ApiModelProperty(value = "來樣编号")
    @TableField(value = "sample_code")
    private String sampleCode;

    @ApiModelProperty(value = "参数组编号")
    @TableField(value = "group_code")
    private String groupCode;

    @ApiModelProperty(value = "模型编号")
    @TableField(value = "model_code")
    private String modelCode;

    @ApiModelProperty(value = "实验样品数量")
    @TableField(value = "sample_qty")
    private String sampleQty;

    @ApiModelProperty(value = "实验结果")
    @TableField(value = "ex_results")
    private String ex_results;

    @ApiModelProperty(value = "实验日期")
    @TableField(value = "ex_date")
    private LocalDateTime exDate;

    @ApiModelProperty(value = "记录人")
    @TableField(value = "记录人")
    private String recorder;
}
