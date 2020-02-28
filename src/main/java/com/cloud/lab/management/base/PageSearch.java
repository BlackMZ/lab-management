package com.cloud.lab.management.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@ApiModel(value = "分页条件", description = "分页条件")
@Getter
@Setter
@Accessors(chain = true)
public class PageSearch {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNo;

    @ApiModelProperty(value = "显示条数")
    private Integer pageSize;
}
