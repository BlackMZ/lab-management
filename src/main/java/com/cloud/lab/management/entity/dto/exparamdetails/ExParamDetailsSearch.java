package com.cloud.lab.management.entity.dto.exparamdetails;

import com.cloud.lab.management.base.PageSearch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: John.ma
 * @Description: 实验参数明细
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "实验参数明细", description = "实验参数明细")
@Getter
@Setter
@Accessors(chain = true)
public class ExParamDetailsSearch extends PageSearch {

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

}
