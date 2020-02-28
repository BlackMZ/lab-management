package com.cloud.lab.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
@TableName("t_camera_parameters")
public class CameraParameters {
    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "上次修改时间")
    @TableField(value = "modified_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    /** 参数类型 */
    @ApiModelProperty(value = "参数类型")
    @TableField("param_type")
    private String paramType;

    /** 参数编码 */
    @ApiModelProperty(value = "参数编码")
    @TableField("param_code")
    private String paramCode;

    /** 机构编号 */
    @ApiModelProperty(value = "机构编号")
    @TableField("mechanism_code")
    private String mechanismCode;

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
    private Integer isControl;

    /** 是否可编辑 */
    @ApiModelProperty(value = "是否可编辑")
    @TableField("is_edit")
    private Integer isEdit;

    /** 是否限制值范围 */
    @ApiModelProperty(value = "是否限制值范围")
    @TableField("is_range")
    private Integer isRange;

    /** 下限值 */
    @ApiModelProperty(value = "下限值")
    @TableField("param_min_value")
    private String paramMinValue;

    /** 上限值 */
    @ApiModelProperty(value = "上限值")
    @TableField("param_max_value")
    private String paramMaxValue;

}
