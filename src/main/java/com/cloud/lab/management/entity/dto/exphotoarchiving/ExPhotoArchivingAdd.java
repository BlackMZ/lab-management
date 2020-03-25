package com.cloud.lab.management.entity.dto.exphotoarchiving;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
public class ExPhotoArchivingAdd {

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

    @ApiModelProperty(value = "参数组编号")
    private String groupCode;

    @ApiModelProperty(value = "照片流水编号")
    private String photoNo;

    @ApiModelProperty(value = "相機編號")
    private String cameraCode;

    @ApiModelProperty(value = "检验状态（待验：N/已验：Y，默认待验）")
    private String inspectFlag;

    @ApiModelProperty(value = "照片说明")
    private String photoDescription;
}
