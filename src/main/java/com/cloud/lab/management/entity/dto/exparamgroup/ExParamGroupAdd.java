package com.cloud.lab.management.entity.dto.exparamgroup;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: John.ma
 * @Description: 实验参数组
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数组", description = "实验参数组")
@Getter
@Setter
@Accessors(chain = true)
public class ExParamGroupAdd {

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

    @ApiModelProperty(value = "状态（合格/不合格）")
    private Integer groupStatus;
}
