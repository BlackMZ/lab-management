package com.cloud.lab.management.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "样品查询", description = "样品查询")
@Getter
@Setter
@Accessors(chain = true)
public class SampleVO {

    @ApiModelProperty(value = "來樣编号")
    private String photoNo;

    @ApiModelProperty(value = "检验状态")
    private String flag;

    @ApiModelProperty(value = "照片说明")
    private String photoDescription;

    @ApiModelProperty(value = "照片信息")
    private List<PhotoVO> photos;

    @ApiModelProperty(value = "照片信息Map")
    private Map<String, String> photoMap;

}
