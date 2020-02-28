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
 * @Description: 实验模型
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验模型", description = "实验模型")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_model_info")
public class ExModelInfo extends BaseEntity {

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

    @ApiModelProperty(value = "训练正确率")
    @TableField(value = "training_accuracy")
    private String trainingAccuracy;

    @ApiModelProperty(value = "训练损失率")
    @TableField(value = "training_loss_rate")
    private String trainingLossRate;

    @ApiModelProperty(value = "验证正确率")
    @TableField(value = "verification_accuracy")
    private String verificationAccuracy;

    @ApiModelProperty(value = "验证损失率")
    @TableField(value = "verification_loss_rate")
    private String verificationLossRate;

    @ApiModelProperty(value = "调和平均数")
    @TableField(value = "harmonic_mean")
    private String harmonicMean;

    @ApiModelProperty(value = "最佳模型")
    @TableField(value = "optimal_model")
    private String optimalModel;

    @ApiModelProperty(value = "参数说明")
    @TableField(value = "param_description")
    private String paramDescription;
    
}
