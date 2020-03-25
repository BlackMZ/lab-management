package com.cloud.lab.management.entity.dto.exphotoarchiving;

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
@ApiModel(value = "实验照片新增参数", description = "实验照片新增参数")
@Getter
@Setter
@Accessors(chain = true)
public class ArchivingAdd {

    @ApiModelProperty(value = "参数组id")
    private String groupId;

    @ApiModelProperty(value = "照片流水编号")
    private String photoNo;

    @ApiModelProperty(value = "相機編號")
    private List<String> cameraCode;

}
