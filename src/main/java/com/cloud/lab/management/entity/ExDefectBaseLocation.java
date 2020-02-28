package com.cloud.lab.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.lab.management.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷母片位置對照
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验照片缺陷母片位置對照", description = "实验照片缺陷母片位置對照")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_defect_base_location")
public class ExDefectBaseLocation extends BaseEntity {

    @ApiModelProperty(value = "客户编号")
    @TableField(value = "customer_code")
    private String customerCode;

    @ApiModelProperty(value = "产品类型")
    @TableField(value = "product_code")
    private String productCode;

    @ApiModelProperty(value = "來樣编号")
    @TableField(value = "sample_code")
    private String sampleCode;

    @ApiModelProperty(value = "相機數量")
    @TableField(value = "camera_qty")
    private Integer cameraQty;

    @ApiModelProperty(value = "参数组编号")
    @TableField(value = "group_code")
    private String groupCode;

    @ApiModelProperty(value = "照片流水编号")
    @TableField(value = "photo_no")
    private String photoNo;

    @ApiModelProperty(value = "切片版本")
    @TableField(value = "cutting_version")
    private String cuttingVersion;

    @ApiModelProperty(value = "相機編號")
    @TableField(value = "camera_code")
    private String cameraCode;

    @ApiModelProperty(value = "缺陷位置")
    @TableField(value = "defect_location")
    private String defectLocation;

    @ApiModelProperty(value = "母片位置")
    @TableField(value = "base_location")
    private String baseLocation;

    @ApiModelProperty(value = "X坐标")
    @TableField(value = "coordinates_x")
    private Integer coordinatesX;

    @ApiModelProperty(value = "Y坐标")
    @TableField(value = "coordinates_y")
    private Integer coordinatesY;
}
