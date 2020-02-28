package com.cloud.lab.management.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "实验计划", description = "实验计划")
@Getter
@Setter
@Accessors(chain = true)
public class BaseEntity {

    @TableId(type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "modified_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedAt;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "modified_by", fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;

    @ApiModelProperty(value = "是否逻辑删除")
    @TableField(value = "is_delete", fill = FieldFill.INSERT_UPDATE)
    private Integer isDelete;

    @ApiModelProperty(value = "版本（乐观锁）")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;
}
