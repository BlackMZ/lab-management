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
@ApiModel(value = "模型数据", description = "模型数据")
@Getter
@Setter
@Accessors(chain = true)
public class ModuleAdd {

    @ApiModelProperty(value = "模型编号")
    private String moduleNo;

    @ApiModelProperty(value = "标识视觉模组参数")
    private Map<String, Object> visual;

}
