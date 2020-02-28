package com.cloud.lab.management.entity.dto.explan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: John.ma
 * @Description: 实验计划
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验计划", description = "实验计划")
@Getter
@Setter
@Accessors(chain = true)
public class ExPlanUpdate {

    @ApiModelProperty(value = "是否逻辑删除")
    private Integer isDelete;

    @ApiModelProperty(value = "客户编号")
    private String customerCode;

    @ApiModelProperty(value = "产品类型")
    private String productCode;

    @ApiModelProperty(value = "來樣编号")
    private String sampleCode;

    @ApiModelProperty(value = "相機數量")
    private Integer cameraQty;

    @ApiModelProperty(value = "切片版本")
    private String cuttingVersion;

    @ApiModelProperty(value = "业务员")
    private String salesmanName;

    @ApiModelProperty(value = "产品尺寸說明")
    private String productSpecification;

    @ApiModelProperty(value = "实验计划说明")
    private String planDescription;

    @ApiModelProperty(value = "计划类型（静态/动态）")
    private Integer planType;

    @ApiModelProperty(value = "训练样品数量")
    private Integer trainingQty;

    @ApiModelProperty(value = "测试样品数量")
    private Integer testQty;

    @ApiModelProperty(value = "收件日期")
    private LocalDateTime receiveDate;

    @ApiModelProperty(value = "记录人")
    private String recorder;

    @ApiModelProperty(value = "算法分析师")
    private String analyst;

    @ApiModelProperty(value = "完成日期")
    private LocalDateTime completionDate;

}