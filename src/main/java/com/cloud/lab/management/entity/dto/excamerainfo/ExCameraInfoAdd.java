package com.cloud.lab.management.entity.dto.excamerainfo;

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
public class ExCameraInfoAdd {

    @ApiModelProperty(value = "是否逻辑删除")
    private Integer isDelete;

    @ApiModelProperty(value = "相机编号")
    private String cameraCode;

    @ApiModelProperty(value = "相机描述")
    private String cameraDescription;

}
