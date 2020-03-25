package com.cloud.lab.management.entity.dto.exdefectrecord;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷记录
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "导出缺陷列表", description = "导出缺陷列表")
@Getter
@Setter
@Accessors(chain = true)
public class DefectExport implements Serializable {


    @ApiModelProperty(value = "照片名称")
    @Excel(name = "PNG_Name", isImportField = "true_st", width = 15)
    private String photoName;

    @ApiModelProperty(value = "相机编号")
    @Excel(name = "camera", isImportField = "true_st")
    private String camera;

    @ApiModelProperty(value = "缺陷位置")
    @Excel(name = "Coordinate", isImportField = "true_st")
    private String coordinate;


}
