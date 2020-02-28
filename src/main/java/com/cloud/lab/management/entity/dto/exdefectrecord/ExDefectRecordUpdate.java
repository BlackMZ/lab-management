package com.cloud.lab.management.entity.dto.exdefectrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷记录
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验照片缺陷记录", description = "实验照片缺陷记录")
@Getter
@Setter
@Accessors(chain = true)
public class ExDefectRecordUpdate {

    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "产品类型")
    private String productCode;

    @ApiModelProperty(value = "來樣编号")
    private String sampleCode;

    @ApiModelProperty(value = "相機數量")
    private Integer cameraQty;

    @ApiModelProperty(value = "参数组编号")
    private String groupCode;

    @ApiModelProperty(value = "照片流水编号")
    private String photoNo;

    @ApiModelProperty(value = "切片版本")
    private String cuttingVersion;

    @ApiModelProperty(value = "相機編號")
    private String cameraCode;

    @ApiModelProperty(value = "缺陷位置")
    private String defectLocation;

    @ApiModelProperty(value = "X坐标")
    private Integer coordinatesX;

    @ApiModelProperty(value = "Y坐标")
    private Integer coordinatesY;

    @ApiModelProperty(value = "缺陷类别")
    private String defectCategories;
}
