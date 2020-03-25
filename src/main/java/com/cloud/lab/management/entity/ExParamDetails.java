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
 * @Description: 实验参数明细
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数明细", description = "实验参数明细")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_param_details")
public class ExParamDetails extends BaseEntity {

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

    /** 参数编码 */
    @ApiModelProperty(value = "参数名称 (相機＋參數）")
    @TableField("param_name")
    private String paramName;

    /** 参数编码 */
    @ApiModelProperty(value = "机构件编号")
    @TableField("mechanism_code")
    private String mechanismCode;


    /** 参数类型 */
    @ApiModelProperty(value = "参数类型")
    @TableField("param_type")
    private String paramType;

    /** 参数值（默认参数） */
    @ApiModelProperty(value = "参数值（默认参数）")
    @TableField("param_value")
    private String paramValue;

    /** 参数值类型 */
    @ApiModelProperty(value = "参数值类型")
    @TableField("value_type")
    private String valueType;

    /** 模组类型 */
    @ApiModelProperty(value = "模组类型")
    @TableField("module_type")
    private String moduleType;
}
