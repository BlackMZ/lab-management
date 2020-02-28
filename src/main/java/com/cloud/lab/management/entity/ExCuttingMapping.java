package com.cloud.lab.management.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.lab.management.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("ex_cutting_mapping")
public class ExCuttingMapping extends BaseEntity implements Serializable {

    @Excel(name = "产品类型", isImportField = "true_st")
    @ApiModelProperty(value = "产品类型")
    @TableField(value = "product_code")
    private String productCode;

    @Excel(name = "切片策略版本号", isImportField = "true_st")
    @ApiModelProperty(value = "切片版本")
    @TableField(value = "cutting_version")
    private String cuttingVersion;

    @Excel(name = "相机编号", isImportField = "true_st")
    @ApiModelProperty(value = "相機編號")
    @TableField(value = "camera_code")
    private String cameraCode;

    @Excel(name = "照片位置", isImportField = "true_st")
    @ApiModelProperty(value = "照片位置")
    @TableField(value = "location")
    private String location;

    @Excel(name = "母片位置", isImportField = "true_st")
    @ApiModelProperty(value = "母片位置")
    @TableField(value = "base_location")
    private String baseLocation;

}
