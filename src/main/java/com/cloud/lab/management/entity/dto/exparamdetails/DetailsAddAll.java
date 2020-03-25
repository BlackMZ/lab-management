package com.cloud.lab.management.entity.dto.exparamdetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验参数明细
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数新增", description = "实验参数新增")
@Getter
@Setter
@Accessors(chain = true)
public class DetailsAddAll {

    @ApiModelProperty(value = "实验计划ID")
    private String planId;

    @ApiModelProperty(value = "相机参数列表")
    private List<ParamAdd> cameraList;

    @ApiModelProperty(value = "标识视觉模组参数")
    private List<ModuleAdd> visualModule;

}
