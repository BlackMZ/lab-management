package com.cloud.lab.management.entity.dto.excuttingmapping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 实验照片切片對照檔
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验照片切片對照檔", description = "实验照片切片對照檔")
@Getter
@Setter
@Accessors(chain = true)
public class ExCuttingMappingSearch {

    @ApiModelProperty(value = "产品类型")
    private String productCode;

    @ApiModelProperty(value = "切片版本")
    private String cuttingVersion;

    @ApiModelProperty(value = "相機編號")
    private String cameraCode;

    @ApiModelProperty(value = "照片位置")
    private String location;

    @ApiModelProperty(value = "母片位置")
    private String baseLocation;

}
