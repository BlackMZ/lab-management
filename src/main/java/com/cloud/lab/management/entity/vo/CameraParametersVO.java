package com.cloud.lab.management.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 相机参数
 * @Date: 2020/02/20
 */
@Data
@ApiModel(value = "相机参数", description = "相机参数")
@Getter
@Setter
@Accessors(chain = true)
public class CameraParametersVO {

    /** 参数类型 */
    @ApiModelProperty(value = "参数类型")
    private String paramType;

    /** 参数编码 */
    @ApiModelProperty(value = "参数编码")
    private String paramCode;

    /** 机构编号 */
    @ApiModelProperty(value = "机构编号")
    private String mechanismCode;

    /** 参数名称 */
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    /** 参数值（默认参数） */
    @ApiModelProperty(value = "参数值（默认参数）")
    private String paramValue;

    /** 是否可控 */
    @ApiModelProperty(value = "是否可控")
    private Integer isControl;

    /** 是否可编辑 */
    @ApiModelProperty(value = "是否可编辑")
    private Integer isEdit;

    /** 是否限制值范围 */
    @ApiModelProperty(value = "是否限制值范围")
    private Integer isRange;

    /** 下限值 */
    @ApiModelProperty(value = "下限值")
    private String paramMinValue;

    /** 上限值 */
    @ApiModelProperty(value = "上限值")
    private String paramMaxValue;

}
