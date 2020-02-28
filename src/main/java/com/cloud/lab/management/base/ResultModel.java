package com.cloud.lab.management.base;

import com.cloud.lab.management.constant.ResultStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 自定义返回结果
 *
 * @author XieEnlong
 * @since 2015/7/14.
 */
@ApiModel(value = "返回值", description = "返回值")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> {

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "结果描述")
    private String message;

    @ApiModelProperty(value = "结果内容")
    private T content;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getContent() {
        return content;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResultModel(ResultStatus status, T content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static ResultModel success(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel success() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public static ResultModel create() {
        return new ResultModel(ResultStatus.CREATED);
    }

    public static ResultModel create(Object content) {
        return new ResultModel(ResultStatus.CREATED, content);
    }

    public static ResultModel internalServerError(Object content) {
        return new ResultModel(ResultStatus.INTERNAL_SERVER_ERROR, content);
    }

    @SuppressWarnings("unchecked")
    public static ResultModel internalServerError() {
        return new ResultModel(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResultModel ok() {
        return new ResultModel(ResultStatus.OK);
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.OK, content);
    }

    public static ResultModel userOrPasswordError(){
        return new ResultModel(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
    }

    public static ResultModel notFound() {
        return new ResultModel(ResultStatus.NOT_FOUND);
    }

    public static ResultModel failure(){
        return new ResultModel(ResultStatus.FAILURE);
    }

    public static ResultModel failure(Object content){
        return new ResultModel(ResultStatus.FAILURE, content);
    }

    public static ResultModel unProcessAbleEntity(){
        return new ResultModel(ResultStatus.UNPROCESABLE_ENTITY);
    }

    public static ResultModel noContent() {
        return new ResultModel(ResultStatus.NO_CONTENT);
    }

    public static ResultModel noParent(){
        return new ResultModel(ResultStatus.NO_PARENT);
    }
}
