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
 * @Description: 实验预设参数库
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验预设参数库", description = "实验预设参数库")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_preset_param_lib")
public class ExPresetParamLib extends BaseEntity {

    @ApiModelProperty(value = "产品类型")
    @TableField(value = "product_code")
    private String productCode;

    /** 参数类型 */
    @ApiModelProperty(value = "参数类型")
    @TableField("param_type")
    private String paramType;

    /** 参数名称 */
    @ApiModelProperty(value = "参数名称")
    @TableField("param_name")
    private String paramName;

    /** 参数值（默认参数） */
    @ApiModelProperty(value = "参数值（默认参数）")
    @TableField("param_value")
    private String paramValue;

    /** 是否可控 */
    @ApiModelProperty(value = "是否可控")
    @TableField("is_control")
    private String isControl;

    /** 是否可编辑 */
    @ApiModelProperty(value = "是否可编辑")
    @TableField("is_edit")
    private String isEdit;

    /** 是否限制值范围 */
    @ApiModelProperty(value = "是否限制值范围")
    @TableField("is_range")
    private String isRange;

    /** 下限值 */
    @ApiModelProperty(value = "下限值")
    @TableField("param_min_value")
    private String paramMinValue;

    /** 上限值 */
    @ApiModelProperty(value = "上限值")
    @TableField("param_max_value")
    private String paramMaxValue;
    
}
