package com.cloud.lab.management.constant;

/**
 * 自定义请求状态码
 *
 * @author lxiang
 * @date 2017/07/25.
 */
public enum ResultStatus {
    SUCCESS(100, "成功"),
    FAILURE(101,"失败"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    OK(200,"服务器成功返回用户请求的数据"),
    CREATED(201,"新建或修改数据成功"),
    ACCEPTED(202,"请求已经进入后台排队"),
    NO_CONTENT(204,"用户删除数据成功"),
    INVALID_REQUEST(400,"请求有错误，服务器没有进行新建或修改数据的操作"),
    UNAUTHORIZED(401,"用户没有权限"),
    FORBIDDEN(403,"用户得到授权（与401错误相对），但是访问是被禁止的"),
    NOT_FOUND(404,"请求针对的是不存在的记录，服务器没有进行操作"),
    NO_PARENT(405,"请求针对父对象不存在，服务器没有进行操作"),
    NOT_ACCEPTABLE(406,"请求的格式不可得"),
    GONE(410,"请求的资源被永久删除"),
    UNPROCESABLE_ENTITY(422,"当创建一个对象时，发生一个验证错误"),
    INTERNAL_SERVER_ERROR(500,"服务器发生错误，用户将无法判断发出的请求是否成功");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
