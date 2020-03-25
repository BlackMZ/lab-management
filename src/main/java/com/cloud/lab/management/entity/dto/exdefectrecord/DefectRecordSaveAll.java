package com.cloud.lab.management.entity.dto.exdefectrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: John.ma
 * @Description: 实验照片缺陷记录
 * @Date: 2020/02/25
 */
@Data
@ApiModel(value = "缺陷记录新增", description = "缺陷记录新增")
@Getter
@Setter
@Accessors(chain = true)
public class DefectRecordSaveAll {

    @ApiModelProperty(value = "参数组id")
    private String groupId;

    @ApiModelProperty(value = "照片流水编号")
    private String photoNo;

    @ApiModelProperty(value = "缺陷列表")
    List<DefectParam> defectList;


}
