package com.cloud.lab.management.entity.dto.exdefectrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷记录
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "缺陷参数", description = "缺陷参数")
@Getter
@Setter
@Accessors(chain = true)
public class DefectParam {

    @ApiModelProperty(value = "相机编号")
    private String cameraNo;

    @ApiModelProperty(value = "缺陷位置")
    private List<String> location;


}
