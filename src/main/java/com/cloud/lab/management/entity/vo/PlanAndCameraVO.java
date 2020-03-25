package com.cloud.lab.management.entity.vo;

import com.cloud.lab.management.entity.ExPlan;
import com.cloud.lab.management.entity.ExPlanCameraConf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ApiModel(value = "实验计划和相机配置", description = "实验计划和相机配置")
@Getter
@Setter
@Accessors(chain = true)
public class PlanAndCameraVO {

    @ApiModelProperty(value = "实验计划")
    private ExPlan exPlan;
    @ApiModelProperty(value = "相机配置")
    private List<ExPlanCameraConf> confList;
}
