package com.cloud.lab.management.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@ApiModel(value = "照片", description = "照片")
@Getter
@Setter
@Accessors(chain = true)
public class PhotoVO {

    @ApiModelProperty(value = "來樣编号")
    private String url;

    @ApiModelProperty(value = "检验状态")
    private String type;

}
