package com.cloud.lab.management.entity.dto.exphotoarchiving;

import com.cloud.lab.management.base.PageSearch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
public class ExPhotoArchivingSearch extends PageSearch {

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

    @ApiModelProperty(value = "相機編號")
    private String cameraCode;

    @ApiModelProperty(value = "相機編號")
    private List<String> cameraCodeList;
}
