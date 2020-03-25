package com.cloud.lab.management.entity.dto.exparamdetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @Author: John.ma
 * @Description: 实验参数明细
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数k-v", description = "实验参数k-v")
@Getter
@Setter
@Accessors(chain = true)
public class ParamAdd {

    @ApiModelProperty(value = "相机编号")
    private String cameraNo;

    @ApiModelProperty(value = "基本参数")
    private Map<String, Object> base;

    @ApiModelProperty(value = "相机参数")
    private Map<String, Object> camera;

    @ApiModelProperty(value = "光源参数")
    private Map<String, Object> light;

    @ApiModelProperty(value = "坐标轴")
    private Map<String, Object> axis;

    @ApiModelProperty(value = "交叉参数")
    private Map<String, Object> cross;

    @ApiModelProperty(value = "边缘参数")
    private Map<String, Object> border;


}
