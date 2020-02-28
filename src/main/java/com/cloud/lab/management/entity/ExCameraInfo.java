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
 * @Description: 实验台相机信息
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验台相机信息", description = "实验台相机信息")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_camera_info")
public class ExCameraInfo extends BaseEntity {

    @ApiModelProperty(value = "相机编号")
    @TableField(value = "camera_code")
    private String cameraCode;

    @ApiModelProperty(value = "相机描述")
    @TableField(value = "camera_description")
    private String cameraDescription;

}
