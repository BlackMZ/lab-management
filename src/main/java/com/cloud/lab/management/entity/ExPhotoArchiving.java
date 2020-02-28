package com.cloud.lab.management.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cloud.lab.management.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: John.ma
 * @Description: 实验照片
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验照片", description = "实验照片")
@Getter
@Setter
@Accessors(chain = true)
@TableName("ex_photo_archiving")
public class ExPhotoArchiving extends BaseEntity {


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

    @ApiModelProperty(value = "相機編號")
    @TableField(value = "camera_code")
    private String cameraCode;

    @ApiModelProperty(value = "检验状态（待验：N/已验：Y，默认待验）")
    @TableField(value = "inspect_flag")
    private String inspectFlag;
}
